<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>암호 재설정 페이지</title>
<link rel="stylesheet" type="text/css" href="resources/css/pwUpdatePage.css" />

<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/jquery-3.7.0.min.js"></script> <!--  절대경로를 el로 처리함 -->
<script type="text/javascript">
function validate() {

	var pwdValue = $('#memberpwd').val();
	var pwdValue2 = $('#memberpwd2').val();

	if (pwdValue !== pwdValue2) { // == : 값만 일치하는지, === : 값과 자료형이 일치하는지
		alert("암호와 암호확인이 일치하지 않습니다. 다시 입력하세요.");
		document.getElementById("memberpwd2").value = ""; // 두 번째 비밀번호 필드의 값을 비웁니다.
		document.getElementById("memberpwd").select(); // 첫 번째 비밀번호 필드를 선택합니다.
		return false; //전송 취소함
	}

	if (!/^[A-Z][a-z\d]{5,11}[!@#]$/.test(pwdValue)) {
		alert("비밀번호 : 첫글자는 영문 대문자, 마지막에 특수문자 넣어주세요. 6~12자 입력할 것!");
		document.getElementById("memberpwd").value = "";
		document.getElementById("memberpwd2").value = "";
		document.getElementById("memberpwd").select();
		return false;
	}
	


		return true;
	}
	
	

</script>
</head>
<body>
<div>
	<h1>비밀번호 재설정</h1>
	<h5>비밀번호를 변경해주세요</h5>
	<form action="pwChange.do" method="post" id="idBox" id="form" onsubmit="return validate();">
		아이디 : <input type="text" name="memberID" value="${ member2.memberID }"readonly><br> 
		
		비밀번호 : <input type="password" name="memberPWD" id="memberpwd" class="input" maxlength="20" required><br>
		<div class="success-message hide"></div>
		<div class="strongPassword-message hide">8글자 이상, 첫글자 영대문자, 소문자와숫자 그리고 특수문자(@$!%*#?&)를 사용하세요</div>
		
		재 확인 : <input type="password" id="memberpwd2" class="input" maxlength="20" required><br>
		<div class="success-message hide"></div>
		<div class="mismatch-message hide">비밀번호가 일치하지 않습니다</div>
		
		<input type="submit" id="done" value="확인">
	</form>
</div>	
	

<script type="text/javascript">

//1. 비밀번호 입력창 정보 가져오기
let elInputPassword = document.querySelector('#memberpwd'); // input#password
//2. 비밀번호 확인 입력창 정보 가져오기
let elInputPasswordRetype = document.querySelector('#memberpwd2'); // input#password-retype
//3. 실패 메시지 정보 가져오기 (비밀번호 불일치)
let elMismatchMessage = document.querySelector('.mismatch-message'); // div.mismatch-message.hide
//4. 실패 메시지 정보 가져오기 (8글자 이상, 영문, 숫자, 특수문자 미사용)
let elStrongPasswordMessage = document.querySelector('.strongPassword-message'); // div.strongPassword-message.hide


function idLength(value) {
	  return value.length >= 6 && value.length <= 12
	}


function onlyNumberAndEnglish(str) {
	  return /^[a-z][A-Za-z0-9]*$/.test(str);
	}
	

function strongPassword (str) {
	  return /^[A-Z](?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/.test(str);
	}
	
function isMatch (password1, password2) {
	  return password1 === password2;
	}
	
elInputUsername.addEventListener('keyup', function(){
//elInputUsername.onkeyup = function () {
	console.log("keyup");
	  // 값을 입력한 경우
	  if (elInputUsername.value.length !== 0) {
	    // 영어 또는 숫자 외의 값을 입력했을 경우
	    if(onlyNumberAndEnglish(elInputUsername.value) === false) {
	      elSuccessMessage.classList.add('hide');
	      elFailureMessage.classList.add('hide');
	      elFailureMessageTwo.classList.remove('hide'); // 영어 또는 숫자만 가능합니다
	    }
	    // 글자 수가 4~12글자가 아닐 경우
	    else if(idLength(elInputUsername.value) === false) {
	      elSuccessMessage.classList.add('hide'); // 성공 메시지가 가려져야 함
	      elFailureMessage.classList.remove('hide'); // 아이디는 4~12글자이어야 합니다
	      elFailureMessageTwo.classList.add('hide'); // 실패 메시지2가 가려져야 함
	    }
	    // 조건을 모두 만족할 경우
	    else if(idLength(elInputUsername.value) || onlyNumberAndEnglish(elInputUsername.value)) {
	      elSuccessMessage.classList.remove('hide'); // 사용할 수 있는 아이디입니다
	      elFailureMessage.classList.add('hide'); // 실패 메시지가 가려져야 함
	      elFailureMessageTwo.classList.add('hide'); // 실패 메시지2가 가려져야 함
	    }
	  }
	  // 값을 입력하지 않은 경우 (지웠을 때)
	  // 모든 메시지를 가린다.
	  else {
	    elSuccessMessage.classList.add('hide');
	    elFailureMessage.classList.add('hide');
	    elFailureMessageTwo.classList.add('hide');
	  }
	});
	
	
elInputPassword.onkeyup = function () {

	  // console.log(elInputPassword.value);
	  // 값을 입력한 경우
	  if (elInputPassword.value.length !== 0) {
	    if(strongPassword(elInputPassword.value)) {
	      elStrongPasswordMessage.classList.add('hide'); // 실패 메시지가 가려져야 함
	    }
	    else {
	      elStrongPasswordMessage.classList.remove('hide'); // 실패 메시지가 보여야 함
	    }
	  }
	  // 값을 입력하지 않은 경우 (지웠을 때)
	  // 모든 메시지를 가린다.
	  else {
	    elStrongPasswordMessage.classList.add('hide');
	  }
	};
	
	
	elInputPasswordRetype.onkeyup = function () {

		  // console.log(elInputPasswordRetype.value);
		  if (elInputPasswordRetype.value.length !== 0) {
		    if(isMatch(elInputPassword.value, elInputPasswordRetype.value)) {
		      elMismatchMessage.classList.add('hide'); // 실패 메시지가 가려져야 함
		    }
		    else {
		      elMismatchMessage.classList.remove('hide'); // 실패 메시지가 보여야 함
		    }
		  }
		  else {
		    elMismatchMessage.classList.add('hide'); // 실패 메시지가 가려져야 함
		  }
		};

	
</script>


</body>
</html>