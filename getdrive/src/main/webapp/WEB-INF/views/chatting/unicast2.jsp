<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅 응답</title>

<!-- <link href="resources/css/chat.css" rel="stylesheet"> -->

<style type="text/css">
.chat {
    position: static;
    border: 1px solid red;
    align:center;
    width: 750px; /* 원하는 너비로 조정 */
    /* top: 50%; */
    /* left: 50%; */
    /* transform: translate(-50%, -50%); */
    background-color: #f2f2f2;
    padding: 20px;
    /* box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); */
}

.startchat {
    text-align: center;
}

.startchat input[type="text"] {
    width: 200px; /* 입력 필드 너비 조정 */
    margin-bottom: 10px;
}

.startchat button {
    background-color: #4CAF50;
    border: none;
    color: white;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    border-radius: 8px;
    transition-duration: 0.4s;
}

.startchat button:hover {
    background-color: #45a049;
}

#chatbox {
    margin-top: 20px; /* 채팅 창과 시작 버튼 사이 여백 조정 */
}

#messageWindow {
    height: 200px;
    overflow-y: scroll;
    border: 1px solid #ccc;
    padding: 10px;
    border-radius: 3px;
    background-color: #f9f9f9;
}

.chat_content {
    background-color:#BCF5A9;
    text-align:right;
    display: inline-block;
    padding:10px;
    margin:10px;
    border-radius:10px;
}

.other-side {
    background-color:#A9F5E1;
    text-align:left;
    display: inline-block;
    padding:10px;
    margin:10px;
    border-radius:10px;
}

#inputMessage {
    width: calc(80% - 90px);
    margin-right: 10px;
    marging:20px;
}

#endBtn {
    margin-left: 10px;
}


</style>

<script type="text/javascript" src="resources/js/jquery-3.7.0.min.js"></script>
</head>
<body>

<div id="container">

	<div id="jb-header">      
		<c:import url="/WEB-INF/views/common/teamtop.jsp" />        
	</div>
	 
	<div id="sidebar">
		<c:import url="/WEB-INF/views/common/teamleft.jsp" />
	</div>
	 
	<div id="content">

		<div class="chat">
		    <h2>요청 온 채팅</h2>
		    <h4>챗팅을 위해서는 포트번호 변경 필요. 현재 8180 으로 되어 있음.</h4>
		    <div class="startchat" id="startchat">
		    	내 대화명 (수신자) : <input type="text" id="chat_id" value="${ loginMember.name }"/> 
		    	<span>초대자명 : </span>
		    	<input type="text" id="recvUser" style="width:100px;" value="${ sender }" readonly/> &nbsp;
		    	<button type="button" id="startBtn">응답하기</button><br>
		    </div>
		    
		    <!-- 채팅 창 구현 부분 -->    	
		    <div style="display:none;" id="chatbox">
		        <fieldset>
		            <div id="messageWindow"></div><br>
		            <input type="text" id="inputMessage" onkeyup="enterKey()"/>
		            <input type="submit" value="보내기" onclick="send()">
		            <button type="button" id="endBtn">나가기</button>
		        </fieldset>
		    </div>
		</div>
	</div>	
	
	<div id="footer">
	    <c:import url="/WEB-INF/views/common/teamfooter.jsp" />
	</div>

</div>


<script type="text/javascript">

/*  수신자용 */
$('#startBtn').on('click',function(){
	alert("채팅 요청을 승인하셨습니다.");
	
    $('#chatbox').css('display', 'block');
	$(this).css('display', 'none');
	$('#startBtn').css('display', 'block');
	
    /* 채팅하기 시작 영역 히든처리  */
    $('#startchat').css('display', 'none'); 
	    
	connection();
});

$('#endBtn').on('click',function(){
    $('#chatbox').css('display', 'none');
    $('#startBtn').css('display', 'inline');
    
    webSocket.send($('#chat_id').val() +"|님이 채팅방을 퇴장하였습니다.");
    
    $.ajax({
    	url: "removeRequest.do",
    	type: "POST",
    	data: {receiver: $('#recvUser').val(),
    			sender: $('#chat_id').val()},
    	success: function(response) {
    		if(response == "exit"){
    			webSocket.close();
    		}
    	},
        error: function(xhr, status, error) {
            console.error("에러 : ", error);
        }
    });
    $.ajax({
    	url: "cleanChat.do",
    	type: "POST"
    });
});

// 채팅창 내용 부분
var $textarea = $('#messageWindow');

// 채팅 서버
var webSocket = null;

// 내가 보낼 문자열을 담은 input 태그
var $inputMessage = $('#inputMessage');

function connection(){
    webSocket = new WebSocket('ws://localhost:8180' + '<%=request.getContextPath()%>/chat');
    
    // 웹 소켓을 통해 연결이 이루어 질 때 동작할 메소드
    webSocket.onopen = function(event){
        
        /* 채팅하기 시작 영역 히든처리  */
        $('#startchat').css('display', 'none'); 
        
       	$textarea.html("<table style='width:100%;'><tr><Td>"
       	+ "<p class='chat_content' style='border:1px solid red; float: right;'>"
       	/* + $('#chat_id').val() */
       	+ "초대받은 대화방에 입장했습니다. </p>"
       	+ "</td></tr></table>");	
        
        // 웹 소켓을 통해 만든 채팅 서버에 참여한 내용을 초대자 대화창에 전달        
        // 내가 보낼 때에는 send / 서버로부터 받을 때에는 message
        webSocket.send($('#chat_id').val()+"님이 입장하셨습니다.");
    };
    
    // 서버로부터 메시지를 전달 받을 때 동작하는 메소드
    webSocket.onmessage = function(event){
        onMessage(event);
    }
    
    // 서버에서 에러가 발생할 경우 동작할 메소드
    webSocket.onerror = function(event){
        onError(event);
        console.log('닫힘 코드:', event.code);
    }
    
    // 서버와의 연결이 종료될 경우 동작하는 메소드
    webSocket.onclose = function(event){
        //onClose(event);
        console.log('닫힘 코드:', event.code);
    }
}

// 엔터키를 누를 경우 메세지 보내기
function enterKey(){
    if(window.event.keyCode == 13)
        send();
}

// 서버로 메시지를 전달하는 메소드
function send(){
    if ($inputMessage.val() == ""){
        // 메시지를 입력하지 않을 경우
        alert("메시지를 입력해 주세요!");
    } else {
       
    	// 메시지가 입력 되었을 경우
        $textarea.html(
            $textarea.html()
            +"<table style='width:100%;'><tr><Td><p class='chat_content' style='border:1px solid red; float: right;'>"
            + $inputMessage.val() +"</p></td></tr></table>");
        
        webSocket.send($('#chat_id').val()+"|"+$inputMessage.val());
        
        $inputMessage.val("");  
        
    }
    
    /* 신규 글이 보이도록  */
    $textarea.scrollTop($textarea.prop('scrollHeight'));
}

// 서버로부터 메시지를 받을 때 수행할 메소드
function onMessage(event) {
    /* 상대방의 대화명, 내용  */
	var message = event.data.split("|");
    var sender = message[0];    
    var content = message[1];
    
    if(content == "" || !sender.match($('#recvUser').val())){
        // 전달 받은 글이 없거나, 전달한 사람이
        // 내가 연결하려는 상대방이 아닐 경우
        // 아무 내용도 실행하지 않겠다.
    } else {
    	
    	/* alert("초대자의 메세지가 전달되었습니다. "); */
    	
        $textarea.html(
            $textarea.html()
           +"<p class='chat_content other-side'>"
           +sender+" : "
           +content
           +"</p><br>");
        
        /* 신규 글이 보이도록  */
        $textarea.scrollTop($textarea.prop('scrollHeight'));
    }
}

function onError(event) {
    alert(event.data);
}

function onClose(event) {
    alert(event);
}
</script>

</body>
</html>

