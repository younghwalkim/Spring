<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>mainFooter.jsp</title>
<style type="text/css">
#footer-content{
	width : 100%;
 	text-align:center; 
 	height: 100px;
 	vertical-align: middle;
 	padding: 3px 0px;
 	
} 
</style>
</head>
<body>

	<footer>
		<div id="footer-content">
		copyright@getDrive.com &nbsp; 클라우드 AI 도우미 서비스 개발자 과정 - getDrive<br>
		서울시 서초구 신논현동  ICT기술협회 강남지원, TEL : 02-1234-5678, Fax : 02-1234-5678
		</div>
	</footer>

			
	<%--2024.04.05 kimyh 세션확인용 삭제대상 --%>	
<%-- 				
	<div align="left" style="color:red;">
		## 수신받는 정보 ## <br>
		<!--  로그인세션정보(loginMember) : ${ loginMember } -->
		로그인세션정보 (loginMember) : 고유번호(accountNo) ${ loginMember.accountNo } |
		이메일(email) : ${ loginMember.email } | 이름(name) : ${ loginMember.name }		
	</div>
 --%>
</body>
</html>