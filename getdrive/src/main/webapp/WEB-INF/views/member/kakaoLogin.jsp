<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.0.js"></script>
<style type="text/css">
#full {
	width: 100%;
	height: 100%;
}

#header {
	width: 100%;
	height: 100px;
}

#main {
	width: 100%;
	height: 650px;
}

#left {
	width: 50%;
	height: 650px;
	float: left;
	background-color: #E1F5FE;
}

#right {
	width: 49.9%;
	height: 650px;
	float: right;
	text-align: center;
	background-color: #E1F5FE;
}

#footer {
	margin: 10px 0px 0px;
	width: 100%;
	height: 90px;
	text-align: center;
}
#kakaocomment{

 	position:relative;
 	top: 100px;
 	color: #6DBFF2;
}
a{
	text-decoration-line: none;
}
</style>
</head>
<body>
	<div id="full">
		<div id="header">
			<c:import url="/WEB-INF/views/common/mainTopbar.jsp"></c:import>
		</div>
		<div id="main">
			<div id="left">
				<c:import url="/WEB-INF/views/common/commonMenu.jsp"></c:import>
			</div>
			<div id="right">
				<div class="loginComplete_box">
				<a href="${ pageContext.servletContext.contextPath }/tmain.do" >
					<h3 id="kakaocomment">KAKAO계정으로 로그인되었습니다</h3>
					<img alt="getdrive" width="500px" src="/getdrive/resources/images/kakaopage.png">
				</a>
			</div>
			</div>
		</div>
		<div id="footer">
			<c:import url="/WEB-INF/views/common/mainFooter.jsp"></c:import>
		</div>
	</div>
</body>
</html>