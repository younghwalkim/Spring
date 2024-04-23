<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/css/findIDPage.css" />

<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript">

function dupEmailCheck(){
	//사용 가능한 아이디인지 확인하는 함수 : ajax 기술 사용해야 함
	$.ajax({  
		url: "emailchk.do",
		type: "post",
		data: { email: $('#email').val() },
		success: function(data){ 
			console.log("success : " + data);
			if(data == "ok"){   
				alert("없는 이메일 입니다. 다시 입력해 주세요.");
				$('#email').select();
			}else{
				alert("이용가능한 이메일입니다.");
				$('#email').attr("readonly", true); 
				$('#emailAuth').attr("disabled", false); 
				
			
			}
		},
		error: function(jqXHR, textStatus, errorThrown){
			console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
		}
	});
	return false;
	
}


</script>
</head>
<body>

<h1 >비밀번호 찾기</h1>


	<h3 id="title">본인확인 이메일로 인증</h3>
	
	<form action="emailIdChk2.do" id="form" method="post" >
	
		<input type="text" name="memberName"  id="membername" class="form-control" placeholder="이름"   >
		<div id="nameAuthWarn"></div>
		<div class="form-group">
	     <div>
	  		<input class="form-control" placeholder="이메일을 입력해주세요." name="email" id="email" type="email">
	  			<input type="button" value="중복체크" onclick="return dupEmailCheck();">
	      		<input type="button" value="인증번호 받기" disabled="disabled" class="btn btn-primary" id="emailAuth">

	    	<div>
	  			<input class="form-control" placeholder="인증 코드 6자리" maxlength="6" disabled="disabled" name="authCode" id="authCode" type="text" autofocus>
	  			<div id="emailAuthWarn"></div>
	  			
	          	<label for="Timer">남은 시간:</label>
 				<input id="Timer" type="text" value="" width= "20px" readonly/>
	          <button class="complete__target" id="complete" disabled="disabled" >인증완료</button>
	     	</div>
	     </div> 

	</div>
	
	<input type="submit" id="next" disabled="disabled" value="다음" >
</form>





<script type="text/javascript">


var codeNum = null;


//인증번호 받기 버튼을 눌렀을 때 동작
$("#emailAuth").click(function() { 
	const email = $("#email").val(); //사용자가 입력한 이메일 값 얻어오기
		
	//Ajax로 전송
    $.ajax({
    	url : 'emailAuth2.do',
    	data : {
    		email : email
    	},
    	type : 'POST',
    	dataType : 'json',
    	success : function(result) {
    		console.log("result : " + result);
    		
    		var strCode = JSON.stringify(result);
    		
    		var json = JSON.parse(strCode);
    		
    		codeNum = json.code;
    		
    		
    		$("#authCode").attr("disabled", false); 
    	
    		alert("인증 코드가 입력하신 이메일로 전송 되었습니다.");
   		}
    }); //End Ajax
});


//인증 코드 비교
$("#authCode").on("keyup", function() {
	
	var inputCode = $("#authCode").val(); //인증번호 입력 칸에 작성한 내용 가져오기
	
	console.log("입력코드 : " + inputCode);
	console.log("인증코드 : " + codeNum);
		
	
	
	if(inputCode == codeNum){
    	$("#emailAuthWarn").html('인증번호가 일치합니다.');
    	$("#emailAuthWarn").css('color', 'green');
		$('#emailAuth').attr('disabled', true);  //인증하기 버튼 비활성화
		$('#email').attr('readonly', true);  //이메일 읽기전용으로 변환
		$("#complete").attr("disabled", false); 
		
		

	}else{
    	$("#emailAuthWarn").html('인증번호가 불일치 합니다. 다시 확인해주세요!');
    	$("#emailAuthWarn").css('color', 'red');
    	$("#next").attr("disabled", true);
    	$("#complete").attr("disabled", true); 
    
	}
	
	return false;
});

//인증완료 클릭 시 
$("#complete").on("click", function() {
	var inputCode = $("#authCode").val();
	
	if(inputCode == codeNum){
		$("#emailAuthWarn").html('인증이 완료되었습니다.');
		$("#emailAuthWarn").css('color', 'green');
		$("#next").attr("disabled", false); 
		$("#complete").attr("disabled", true); 
		$("#next").attr("disabled", false); 
		clearInterval(PlAYTIME);

	}else{
		$("#emailAuthWarn").html('인증번호가 불일치 합니다. 다시 확인해주세요!');
		$("#emailAuthWarn").css('color', 'red');
		$("#emailAuth").attr("disabled", false); 
		$("#next").attr("disabled", true); 
		
	
	}
	
	return false;
});


</script>


 <script type="text/javascript">

//-------------------------타이머-----



let time = 180000;
let min = 3;
let sec = 60;
let PlAYTIME;

Timer.value = min + ":" + '00';
$("#emailAuth").on("click", function () {
    time = 180000; // 클릭할 때마다 타이머 초기화
    min = 3;
    sec = 60;
    clearInterval(PlAYTIME); // 기존 타이머 정지
    PlAYTIME = setInterval(function () {
        time = time - 1000; // 1초씩 줄어듦
        min = time / (60 * 1000); // 초를 분으로 나눠준다.

        if (time <= 0) {
            clearInterval(PlAYTIME); // 타이머 정지
            Timer.value = "만료되었습니다";
        } else {
            if (sec > 0) { // sec=60 에서 1씩 빼서 출력해준다.
                sec = sec - 1;
                Timer.value = Math.floor(min) + ':' + sec; // 실수로 계산되기 때문에 소숫점 아래를 버리고 출력해준다.
            }
            if (sec === 0) {
                // 0에서 -1을 하면 -59가 출력된다.
                // 그래서 0이 되면 바로 sec을 60으로 돌려주고 value에는 0을 출력하도록 해준다.
                sec = 60;
                Timer.value = Math.floor(min) + ':' + '00'
            }
        }

    }, 1000); // 1초마다 
});
</script>


</body>

</html>