<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>팀 생성</title>
<!-- 2024.04.05 kimyh : 공통 css 처리 -->
<link rel="stylesheet" href="/getdrive/resources/css/common.css">

<style type="text/css">

#full {
	width: 100%;
	height: 100%;
}

#header {
	width: 100%;
	height: 100px;
}
	
body {
	font-size: 1.5rem;
}

center {
	margin-bottom: 3rem;
}

p {
	margin-top: 10rem;
}

input {
	border-radius: 5px;
	padding: 0.5rem;
	width: 12%;
}

/* 생성하기 버튼 */
#tmain {
	background-color: #6DBFF2;
	color: white;
	font-size: 1.5rem;
	border-radius: 5px;
	border: none;
	padding: 0.5rem 1rem;
	/* margin-top: 10rem; */
}

#tmain:hover {
	cursor: pointer;
}
	
</style>

</head>
<body>
<div id="full">
	<div id="header">
		<c:import url="/WEB-INF/views/common/mainTopbar.jsp"></c:import>
	</div>
	<hr>
	
	<div id="main" style="width:100%">
		<div align="center">
		<h2>팀 생성</h2>
		<form action="iteam.do" method="post">
			<p>새로운 팀 이름</p>
			<input name="tName" type="text" placeholder="새로운 팀명을 입력해주세요.">
			<br><br>
			<input type="submit" id="tmain" value="생성하기">
		</form>
		</div>
	</div>	
	<hr>
	
	<div id="footer">
		<c:import url="/WEB-INF/views/common/mainFooter.jsp"></c:import>
	</div>	
</div>

</body>
</html>