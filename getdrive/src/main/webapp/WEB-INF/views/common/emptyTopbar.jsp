<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
#logo {
	margin: 10px;
	width: 150px;
}
#logospace {
	margin: 10px;
	width: 180px;
	cursor: pointer;
	float: left;
}
.dropdown{
	margin: 20px;
	width: 180px;
	cursor: pointer;
	float: right;
	text-align: center;
	position: relative;
	right:30px;
}

.dropbtn{ 
  color: white;
  border : none;
  border-radius: 25px 25px 90px 500px / 25px 25px 0px 0px;
  background-color: #6DBFF2;
  font-weight: 400;
  padding : 12px;
  width :200px;
  cursor : pointer;
  font-size : 18px;
  font-weight: 600;
}
.dropdown-content{
  display : none;
  position : absolute;
  z-index : 1; /*다른 요소들보다 앞에 배치*/
  font-weight: 400;
  background-color: #9BD1F2;
  min-width : 200px;
  border-radius: 90px 500px 25px 25px / 0px 0px 25px 25px;
}
.dropdown-content>#a1{
  display : block;
  text-decoration : none;
  color : white;
  font-size : 18px;
  padding : 12px 20px;
}
.dropdown-content>#a2{
  display : block;
  text-decoration : none;
  color : white;
  font-size : 18px;
  padding : 12px 20px;
  border-radius: 90px 500px 25px 25px / 0px 0px 25px 25px;
}

.dropdown-content a:hover{
  background-color : #C9E2F2;
}

.dropdown:hover .dropdown-content {
  display: block;
}
</style>
<script type="text/javascript">
function moveAccountSetting(){
	location.href = "accountSettingPage.do";
}
</script>
</head>
<body>
	<div id="logospace">
	<%--로그인 하지 않았을 때 --%>
	<c:if test="${ empty sessionScope.loginMember }">
		<a href="${ pageContext.servletContext.contextPath }/mainPage.do">
		<img id="logo" alt="getdrive"src="/getdrive/resources/images/logo.png">
		</a>
	</c:if>
	<%-- 로그인 했을 때 --%>
	<c:if test="${ !empty sessionScope.loginMember }">
		<a href="${ pageContext.servletContext.contextPath }/mainPage.do">
		<img id="logo" alt="getdrive"src="/getdrive/resources/images/logo.png">
		</a>
	</c:if>
</body>
</html>