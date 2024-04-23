<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="../common/error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}
#main {
	width: 100%;
}

#completeForm {
	width: 500px;
	margin: auto;
}

ul>li {
	list-style: none
}

.complete_text {
	width: 500px;
	border: none;
	background-color: #fff;
	box-sizing: border-box;
	margin: 20px 0px;
	text-align: center;
}

.complete_text {
	margin-top: 45%;
	box-sizing: border-box;
}


.Complete_btn>button {
	background: #6DBFF2;
	border-radius: 5px;
	color: white;
	cursor: pointer;
	padding-bottom: 4px;
	border: none;
	width: 500px;
	height: 40px;
	text-align: center;
	font-weight: 600;
	font-size: 12pt;
}

.Complete_btn>button:active {
	color: white;
	background: rgb(200, 230, 255);
}

.Complete_btn>button:disabled {
	color: #696969;
	background: #DCDCDC;
	cursor: default;
}

#completelogo{
	width: 150px;
}
</style>
<script type="text/javascript">
function moveMainPage(){
	location.href = "mainPage.do";
}
</script>
</head>
<body>
<form action="registerComplete.do" method ="post" id="completeForm">
	<div id="main">
		<ul class="complete_box">
			<li class="complete_text">
				<img id="completelogo" src="/getdrive/resources/images/complete.jpg">
				<h1>Class.getDrive</h1>
				<h1>회원가입이 완료되었습니다</h1>
				<br>
				<h3>최고의 협업툴로</h3>
				<h3>최선의 업무효율을 올려보세요</h3>
			</li>
			<li class="Complete_btn">
				<button onclick="moveMainPage(); return false"  id="registerComplete_button" type="submit">로그인 화면으로 이동하기</button>
			</li>
		</ul>
	</div>
</form>
</body>
</html>