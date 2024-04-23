<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<link rel="stylesheet" type="text/css" href="resources/css/resignPage.css" />

</head>
<body>

 <c:import url="/WEB-INF/views/common/header.jsp" /> 

 <c:import url="/WEB-INF/views/member/mypageSidebar.jsp" /> 



<h1>회원 탈퇴</h1>
 
 
<form action="ResignProcess.do" method="post" id="resign_box"> 
	<h3>비밀번호 확인</h3>
	<input type="password" name="memberPWD" id="memberpwd">
	
	<input type="hidden" name="originPWD" value="${ member.memberPWD }">
	<input type="hidden" name="memberID" value="${ member.memberID }">
	<p>
	<div id='warn'>※회원님의 정보로 30일 동안 회원가입을 할 수없습니다.</div>
</form>


</body>
</html>