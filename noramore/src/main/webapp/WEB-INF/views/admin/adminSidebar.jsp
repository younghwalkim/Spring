<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="resources/css/adminSidebar.css" rel="stylesheet">
<script src="/noramore/resources/js/jquery-3.7.0.min.js"></script>
<title>noramore</title>
</head>
<body>
	<aside class="side-bar">
		<section class="side-bar__icon-box">
			<section class="side-bar__icon-1">
				<div></div>
				<div></div>
				<div></div>
			</section>
		</section>
		<ul>
			<li><a href="${ pageContext.servletContext.contextPath }/home.do"><i class="fa-solid fa-cat"></i> 홈으로</a> <br>
			<li><a href="${ pageContext.servletContext.contextPath }/adminPage.do"><i class="fa-solid fa-cat"></i> 관리자 홈</a> <br>
			<li><a href="${ pageContext.servletContext.contextPath }/memberlist.do"><i class="fa-solid fa-cat"></i> 회원</a>
				<ul>
					<li><a href="${ pageContext.servletContext.contextPath }/memberlist.do">회원 목록</a></li>
					<li><a href="${ pageContext.servletContext.contextPath }/reportedMemlist.do">신고된 회원</a></li>
				</ul></li>
			<li><a href="${ pageContext.servletContext.contextPath }/reportedBoardlist.do">신고된 게시글</a></li>
			<li><a href="${ pageContext.servletContext.contextPath }/fblist.do">금지어 관리</a></li>
			<li><a href="${ pageContext.servletContext.contextPath }/goCategoryWriteForm.do">카테고리 추가</a></li>			
			<li><a href="${ pageContext.servletContext.contextPath }/nlist.do">공지사항</a></li>				
			<li><a href="${ pageContext.servletContext.contextPath }/qlist.do">QnA</a></li>				
		</ul>
	</aside>
</body>
</html>