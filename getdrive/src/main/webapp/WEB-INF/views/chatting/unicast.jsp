<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html>
<html lang="KO">
<head>
<meta charset="UTF-8">
<title>채팅 - unicast</title>

<!-- <link rel="stylesheet" type="text/css" href="resources/css/chat.css" /> -->

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
		
		    <h2>채팅</h2>
	    	<h4>챗팅을 위해서는 포트번호 변경 필요. 현재 8180 으로 되어 있음.</h4>
		    <div class="startchat" id="startchat">
			    대화명 : <input type="text" id="chat_id" value="${ loginMember.name }"/>
			    <span>상대방 ID : </span>
			    <select>
			    
			    </select>		    
			    <input type="text" id="recvUser" style="width:100px;"/> &nbsp;
			    <button type="button" id="startBtn">채팅하기</button><br>
		    </div>
	    	
	    	<!-- 채팅 창 구현 부분 -->
		    <div style="display:none;" id="chatbox">
		        <fieldset>
		            <div id="messageWindow"></div><br>
		            <!-- <teaxare id="Message" onkeyup="enterKey()" cols=2 rows=2></teaxare> -->
		            <input type="text" id="inputMessage" onkeyup="enterKey()"/>
		            <input type="submit" value="보내기" onclick="send()">
		            <button type="button" id="endBtn">채팅창 나가기</button>
		        </fieldset>
		    </div>
		</div>
	</div>
	
	<div id="footer">
    <c:import url="/WEB-INF/views/common/teamfooter.jsp" />
  </div>
</div>

	
<script type="text/javascript">
/* 초대자 목록 */

/* 초대자용 */
$('#startBtn').on('click',function(){
 	if ($('#recvUser').val() == $('#chat_id').val() ) {
		alert("자신을 초대하지 마세요.");
 	} else {
 		 $.ajax({ 			
 			
 			/* 초대 - 채팅 대상 확인 */
 	    	url: "checkMember.do",
 	    	type: "POST",
 	    	data: {memberID: $('#recvUser').val()},
 	    	success: function(response) {
 	    		
 	    		/* 상대자가 있으면 채팅창 노출 */
 	    		if(response == "exist"){
 	    			alert("연결 완료! 상대방이 접속할 때까지 기다려주십시오.");
 	    			
 	   			    /* 채팅하기 시작 영역 히든처리  */
 	   			    $('#startchat').css('display', 'none'); 	    			
 	    			
 	   			    /*  채팅창 활성화 */
 	    			$('#chatbox').css('display', 'block');
 	    			$(this).css('display', 'none');
 	    			$('#startBtn').css('display', 'block');
 	    		    
 	    			/* 채팅방 개설 */
 	    			$.ajax({
 	    		    	url:"chatRequest.do",
 	    		    	type: "POST",
 	    		    	data: { textMessage : $('#chat_id').val() +'과 '+ $('#recvUser').val() +'의 채팅', 
 	    		    			sender : $('#chat_id').val(),
 	    		    			receiver : $('#recvUser').val()}
 	    		    }); 
 	    			
 	    			connection();
			
 	    		}else{
 	    			alert("없는 회원이거나 잘못된 입력입니다. 다시 입력해주세요.");
 	    			$('#recvUser').val("");
 	    		}
 	    	},
 	        error: function(xhr, status, error) {
 	            console.error("에러 : ", error);
 	        }
 	    }); 		
 	} 
    
})

$('#endBtn').on('click',function(){
    /* 채팅하기 시작 영역 노출 처리  */
    $('#startchat').css('display', 'block'); 	
	
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
    
});

var $textarea = $('#messageWindow');

var webSocket = null;

var $inputMessage = $('#inputMessage');


function connection(){
    /* 소캣연결 */
	webSocket = new WebSocket('ws://localhost:8180' + '<%=request.getContextPath()%>/chat');
    
    /* 초대자의 화면에 상대방 입장안내 구문 표시 */
    webSocket.onopen = function(event){
        webSocket.send($('#chat_id').val() + "님이 입장하셨습니다.");
    };
    
    webSocket.onmessage = function(event){
        onMessage(event);
    }
    
    webSocket.onerror = function(event){
         console.log('닫힘 코드:', event.code);
    }
    
    webSocket.onclose = function(event){
         console.log('닫힘 코드:', event.code);
    }
}

function enterKey(){
    if(window.event.keyCode == 13)
        send();
}

function send(){
    if ($inputMessage.val() == ""){
        alert("메시지를 입력해 주세요!");
    } else {

        /* 나의 대화 내용을 내 대화창에 표시 */
    	$textarea.html(
            $textarea.html()
            + "<table style='width:100%;'><tr><Td>"
            + "<p class='chat_content' style='border:1px solid red; float: right;'>"
            + $inputMessage.val() +"</p>" 
            + "</td></tr></table>");
      
        webSocket.send($('#chat_id').val()+"|"+$inputMessage.val());
        
        /* 나의 대화 내용을 상대방 대화창에 표시 */
        $.ajax({
        	url:"chatRequest.do",
        	type: "POST",
        	data: { textMessage : $inputMessage.val(), 
        			sender : $('#chat_id').val(),
        			receiver : $('#recvUser').val()}
        }); 
        
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
    } else {
    	
    	/* alert("상대방의 메세지가 전달되었습니다. "); */
    	
        $textarea.html(
            $textarea.html()
           +"<p class='other-side'>"
           + sender +" : "
           + content
           +"</p><br>");
        
        /* 신규 글이 보이도록  */
        $textarea.scrollTop($textarea.prop('scrollHeight'));
    }
}

function onError(event) {
	event.code
    alert(event.data);
}

function onClose(event) {
	event.code
    alert(event);
}

</script>


</body>
</html>

