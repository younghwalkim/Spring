<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 확인 페이지</title>
<link rel="stylesheet" type="text/css" href="resources/css/idChkPage.css" />



</head>
<body>
<h1>아이디 찾기</h1>
<h5>고객님의 정보와 일치하는 아이디 목록입니다</h5>
<div id="idBox">
	아이디 : ${ member2.memberID }  <p>
    가입날짜 : ${ member2.registDate }
</div>

<a href="${ pageContext.servletContext.contextPath }/moveLoginPage.do"><button>로그인 하기</button></a>
<a href="${ pageContext.servletContext.contextPath }/findPWPage.do"><button>비밀번호 찾기</button></a>
</body>
</html>