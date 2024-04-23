<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="../common/error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.0.js"></script>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

#header {
	width: 100%;
	margin-left:10px;
}

#main {
	width: 100%;
}

#registerForm {
	width: 460px;
	margin: 50px auto;
}

ul.register_box {
	border: 1px solid #ddd;
	background-color: #fff;
}

.inputInfo, .inputInfo>ul {

}

.inputInfo>ul>li {
	float: left;
}

.inputInfo>ul>li:first-child {
	width: 80%;
	padding-left: 30px;
	font-weight: 600;
	color: #888;
}

ul>li {
	list-style: none
}

a {
	text-decoration: none;
}

span {
	font-size: 10pt;
	color: red;
}

#comment {
	font-size: 9pt;
	margin-left: 9px;
}

.inputText {
	width: 400px;
	border: 1px solid #ddd;
	border-radius: 5px;
	background-color: #fff;
	box-sizing: border-box;
	margin: 10px 0px;
}

.inp_text {
	position: relative;
	margin-top: 10px;
	box-sizing: border-box;
}

.inp_text label {
	margin-left: 8px;
}

.inp_text+.inp_text {
	border-top: 1px solid #ddd;
}

.inp_text input {
	display: block;
	height: 40px;
	font-size: 18px;
	font-family: sans-serif;
	font-weight: 600;
	color: #444444;
	outline: 0;
	-webkit-appearance: none;
	background-color: transparent;
	cursor: pointer;
	padding-left: 6px;
}

.inp_text input::placeholder {
	color: #999999;
}

.clearfix::after {
	content: "";
	display: block;
	clear: both;
	margin-bottom: 10px;
}

.registerComplete {
	margin-top: 15px;
	float: left;
}

.registerComplete>button {
	background: #6DBFF2;
	border-radius: 5px;
	color: white;
	cursor: pointer;
	padding-bottom: 4px;
	border: none;
	width: 460px;
	height: 40px;
	text-align: center;
	font-weight: 600;
	font-size: 12pt;
}

.registerComplete>button:hover {
	color: white;
	background: rgb(200, 230, 255);
}

.registerComplete>button:disabled {
	color: #696969;
	background: #DCDCDC;
	cursor: default;
}

.email_div {
    display: inline-block;
    border: 1px solid #ddd;
    border-radius: 5px;
    margin-top: 5px;
    
}

#email {
    background-color: transparent;
    width: 400px;
    border: 0;
    float: left;
}

#loginpagezone{
	text-align: center;
	font-family: sans-serif;
	font-weight: 600;
	cursor: pointer;
	color:#41AEF2;
}
</style>

<script type="text/javascript">
function moveMainPage(){
	location.href = "mainPage.do";
}
</script>
<script type="text/javascript">

//회원가입 버튼 활성화/비활성화 함수
function checkForm() {
    var emailValid = $(".success").is(":visible"); // 이메일 중복 확인 결과
    var passwordValid = checkPassword(); // 비밀번호 조건 체크 결과

    // 이메일 중복 확인 결과와 비밀번호 조건 체크 결과가 모두 true이면 버튼 활성화
    if (emailValid && passwordValid) {
        $("#rgisterComplete_button").prop("disabled", false);
    } else {
        $("#rgisterComplete_button").prop("disabled", true);
    }
}

var inputEmailValue = $(".inputEmail").val();
//입력 이메일입력 값 변수선언

// 문서 로드 후 실행되는 함수
$(function() {
    // 폼 체크 함수 호출하여 버튼 상태 설정
    checkForm();
    
    $(".inputEmail").on("input", function() {
        // 현재 이메일 값
        var nowEmailValue = $(this).val();

        // 이메일 값이 변경되었는지 확인
        if (nowEmailValue !== inputEmailValue) {
            // 변경된 경우: 중복 확인 실행, 문구변경
            emailCheck();
            inputEmailValue = nowEmailValue; // 이전 값 갱신
        }
    });

    // 비밀번호 입력란에 입력이 발생할 때마다 폼 체크 함수 호출
    $("#userPwd, #userPwd2").on("input", function() {
        checkForm();
    });
 
});

function checkPassword() {
    var password = $("#userPwd").val();
    var confirmPassword = $("#userPwd2").val();
  
    var empty = password.trim() === '';
    var length = password.length >= 8 && password.length <= 20;
    var alphabet = /[a-zA-Z]/.test(password);
    var digit = /\d/.test(password);
    var special = /[!@#$%^&*]/.test(password);
    var match = password === confirmPassword;

    // 각 조건을 검사하고 메시지를 표시
    $(".empty").toggle(empty);
    $(".terms").toggle(!empty && (!length || !alphabet || !digit || !special));
    $(".mismatch").toggle(!empty && !match && !$(".terms").is(":visible")); 
    $(".match").toggle(match && !empty && !$(".terms").is(":visible") && !$(".mismatch").is(":visible"));

    // 비밀번호가 조건을 모두 충족하는지 확인하여 회원가입 버튼 활성화/비활성화
    var passwordTerms = !empty && length && alphabet && digit && special && match;
    
    return passwordTerms;
    
}

function emailCheck(){
	$.ajax({
		url: "idchk.do",
		type: "post",
		data: { email: $('.inputEmail').val() },
		success: function(data){
			console.log("success : " + data);
			if(data == "ok"){
				$('.success').show();
				$('.fail').hide();
				$('.inputEmail').focus();
			}else{
				$('.fail').show();
				$('.success').hide();
				$('.inputEmail').select();
			}
			checkForm();
		},
		error: function(jqXHR, textStatus, errorThrown){
			console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
		}
	});
}

</script>
</head>
<body>
<form action="register.do" method ="post" id="registerForm" >
	<header>
		<div id="header">
			<c:import url="/WEB-INF/views/common/emptyTopbar.jsp"></c:import>
		</div>
	</header>
	<div id="main">
		<ul class="register_box">
			<li class="inputInfo" >
				<ul class="clearfix">
					<li class="inp_text">
						<label for="userName" class="registerText">이름</label><br>
						<input type="text" id="username" class="inputText" name="name" placeholder="이름 입력" required>
						<p id="username_msg"></p>
					</li>
				</ul>
			</li>
			<li class="inputInfo">
				<ul class="clearfix">
					<li class="inp_text">
						<label for="userEmail" class="registerText">이메일</label><br>
						<div class="email_div">
							<input type="email" id="email" class="inputEmail" name="email" placeholder="이메일 입력" required>
						</div>
						<div class="success" style="display: none; color: #6DBFF2">사용할 수 있는 이메일입니다</div>
						<div class="fail" style="display: none; color: red">이미 사용중인 이메일입니다</div>
   						<div class="failure" style="display: none;">아이디는 4~12글자이어야 합니다</div>
   						<div class="failure2" style="display: none;">영어 또는 숫자만 가능합니다</div>
					</li>
				</ul>
			</li>
			<li class="inputInfo">
				<ul class="clearfix">
					<li class="inp_text">
						<label for="userEmail" class="registerText">비밀번호 입력
							<p id="comment">비밀번호는 8~20자 영문, 숫자, 특수문자로 입력하세요</p>
						</label>
						<input type="Password" class="inputText" name="pwd" id="userPwd" placeholder="비밀번호 입력" required>
             		    <input type="Password" class="inputText" id="userPwd2" placeholder="비밀번호 확인" required>
             		    <div class="terms" style="display: none; color: red">비밀번호 입력 조건과 일치하지 않습니다</div>
               			<div class="mismatch" style="display: none; color: red">비밀번호가 일치하지 않습니다</div>
               			<div class="match" style="display: none; color: #6DBFF2">비밀번호가 일치합니다.</div>
					</li>
				</ul>
			</li>
			<li class="registerComplete">
				<button id="rgisterComplete_button" type="submit" disabled="true" onclick="return emailCheck();">회원가입 완료</button>
			</li>
		</ul>
	</div>
</form>
<br>
<div id="loginpagezone">
	<a onclick="moveMainPage();">이미가입하셨나요?</a>
</div>
</body>
</html>