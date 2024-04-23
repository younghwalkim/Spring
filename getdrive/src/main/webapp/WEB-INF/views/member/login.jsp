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
#loginzone {
	margin:auto;
	width: 40%;
	height: 80%;
	vertical-align: middle;
	display: inline-block;
	margin-top:120px; 
}

#loginfield{
	color : white;
	width: 400px;
}
input {
	-webkit-writing-mode: horizontal-tb !important;
	text-rendering: auto;
	color: initial;
	letter-spacing: normal;
	word-spacing: normal;
	text-transform: none;
	text-indent: 0px;
	text-shadow: none;
	display: inline-block;
	text-align: start;
	-webkit-appearance: textfield;
	background-color: white;
	-webkit-rtl-ordering: logical;
	cursor: text;
	margin: 0em;
	padding: 1px 0px;
	border-width: 2px;
	border-style: inset;
	border-color: initial;
	border-image: initial;
}

.inner_login {
	position: absolute;
	left: 50%;
	top: 50%;
	margin: -145px 0 0 -160px;
}
.screen_out {
	position: absolute;
	width: 0;
	height: 0;
	overflow: hidden;
	line-height: 0;
	text-indent: -9999px;
}


body, button, input, select, td, textarea, th {
	font-size: 13px;
	line-height: 1.5;
}

fieldset, img {
	border: 0;
}

.box_login {
	width: 100%;
	border: 1px solid #ddd;
	border-radius: 5px;
	background-color: #fff;
	box-sizing: border-box;
}


.inp_text {
	position: relative;
	width: 100%;
	margin: 0;
	padding: 8px 19px 8px;
	box-sizing: border-box;
	font-size : 50pt;
}

.inp_text+.inp_text {
	border-top: 1px solid #ddd;
}

.inp_text input {
	display: block;
	width: 100%;
	height: 40px;
	font-size: 18px;
	font-family :sans-serif;
	font-weight: 600;
	color: #444444;
	border: none;
	outline: 0;
	-webkit-appearance: none;
	background-color: transparent;
	cursor: pointer;
}

.inp_text input::placeholder{
	color: #999999;
}

.btn_login {
	background: #6DBFF2;
	border-radius: 5px;
	color: white;
	cursor: pointer;
	border: 1px solid rgb(107, 185, 237);
	padding: 7px;
	width: 100%;
	height: 40px;
	text-align: center;
	font-weight: 600;
	font-size: 12pt;
}

.btn_login:hover {
	color: white;
	background: rgb(200, 230, 255);
	border:none;
}

.login_append.txt_find {
	float: right;
	color: #666666;
	text-align: center;
	font-weight: 600;
	text-decoration-line: none;
}

.login_append.txt_find:hover {
	color: #999999;
	border:none;
}

.QR_login {
	color: #666666;
	line-height: 2.1;
	font-weight: 600;
	text-decoration-line: none;
}

.QR_login:hover {
	color: #999999;
}

#kakao {
	margin: 10px;
	width: 300px;
	cursor: pointer;
}


.join_button {
	background: #6DBFF2;
	border-radius: 5px;
	color: white;
	cursor: pointer;
	border: 1px solid rgb(107, 185, 237);
	padding: 7px;
	width: 100%;
	height: 40px;
	text-align: center;
	font-weight: 600;
	font-size: 12pt;
	margin-top: 10px;
}

.join_button:hover {
	color: white;
	background: rgb(200, 230, 255);
	border:none;
}

#loginErrorMessage {
	width: 100%;
	color: red;
	text-align: left;
}
#passwordFind{
	border: none;
	background-color : #E1F5FE;
	cursor: pointer;
	float: right;
}
</style>

<script type="text/javascript">
function moveContractPage(){
	location.href = "contractPage.do";
}
function moveTeamMain(){
	location.href = "tmain.do";
}
function getPasswordFindPage(){
	event.preventDefault();
	location.href = "getPasswordFindPage.do";
}
</script>
<script src="/getdrive/resources/js/kakao.min.js"></script>
<script>
	Kakao.init('4d2b700f21f5db14e8df9701c31eef5e');
	console.log(Kakao.isInitialized()); // sdk초기화여부판단
	//로그인 후 정보 받기
	//카카오톡 로그인
	function kakaoLogin() {
    Kakao.Auth.login({
        success: function(response) { 
            Kakao.API.request({
                url: '/v2/user/me',
                success: function(response) {
                    console.log(response);
                    console.log("카톡 로그인 아이디 : " + response.id)   
                    console.log("카톡 닉네임 : " + response.properties.nickname)
                    console.log("카톡 이메일 : " + response.kakao_account.email)

                    $.get("kakao_register.do?" + "id=" + response.id + "&" + "nickname=" 
                    		+ response.properties.nickname + "&" + "email=" + response.kakao_account.email)
                    console.log("kakao_register.do?" + "id=" + response.id + "&" + "nickname=" 
                    		+ response.properties.nickname + "&" + "email=" + response.kakao_account.email);
                    window.location.href = "kakaoLogin.do";
					},
					fail : function(error) {
						console.log("Kakao API 요청 실패: " + error);
					}
				});
			},
			fail : function(error) {
				console.log("Kakao 로그인 실패: " + error);
			}
		});
	}
	
	//카카오 로그아웃
	function kakaoLogout() {
		if (Kakao.Auth.getAccessToken()) {
			Kakao.API.request({
				url : '/v1/user/unlink',
				success : function(response) {
					console.log(response)
					
				},
				fail : function(error) {
					console.log(error);
				},
			})
			Kakao.Auth.setAccessToken(undefined);
		}
		location.href = "logout.do";
	}
</script>

</head>
<body>
		<div id="loginzone">
			<fieldset id="loginfield">
				<legend class="screen_out">로그인 정보 입력폼</legend>
			<form id="loginForm" action="login.do" method="post">
				<div class="box_login">
					<div class="inp_text">
						<label for="loginId" class="screen_out"> 아이디</label> 
						<input type="email" id="loginEmail" name="email" placeholder="이메일" class="pos">
					</div>
					<div class="inp_text">
						<label for="loginPw" class="screen_out"> 비밀번호</label> 
						<input type="password" id="loginPw" name="pwd" placeholder="비밀번호" class="pos">
					</div>
				</div>
				<h3 id="loginErrorMessage"> ${ requestScope.message }</h3>
				<input type="submit" class="btn_login" value="Sign in">
			</form>
			<form action="getPasswordFindPage.do">
   				<button type="button" class="QR_login" id="passwordFind" onclick="getPasswordFindPage();">→ 비밀번호 찾기</button>
				<br>
			</form>
				<div>
					<button onclick="moveContractPage(); return false" type="submit" class="join_button">아직 계정이 없으신가요?</button>
				</div>
				<hr>
				<div class="login_bottom">
					<div class="snsicon">
						<img id="kakao" alt="getdrive" type="submit" onclick="kakaoLogin()" src="/getdrive/resources/images/kakao.png">
					</div>
					
				</div>
			</fieldset>
			</div>

</body>
</html>