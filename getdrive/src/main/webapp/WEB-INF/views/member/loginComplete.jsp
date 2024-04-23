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
#loginCompleteZone{
	display: inline-block;
	margin-top:120px; 
}
ul.loginComplete_box {
	vertical-align: middle;
	border: 1px solid #ddd;
	background-color: #fff;
	margin: auto;
}
img{
	width: 700px;
}
ul>li {
	list-style: none
}
</style>
</head>
<body>
	<form action="loginComplete.do" method ="post" id="loginCompleteForm" >
		<div id="loginCompleteZone">
			<div class="loginComplete_box">
				<a href="${ pageContext.servletContext.contextPath }/tmain.do">
					<img alt="getdrive" width="500px" src="/getdrive/resources/images/hello.png">
				</a>
			</div>
		</div>
	</form>
</body>
</html>