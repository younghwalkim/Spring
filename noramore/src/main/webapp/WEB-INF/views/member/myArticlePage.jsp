<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>




<link rel="stylesheet" type="text/css" href="resources/css/myArticle.css" />
<style>
.scrollable-div {
    height: 350px;
    overflow: auto; /* 내용이 넘칠 경우 스크롤바 표시 */
}
</style>

</head>
<body>
 <c:import url="/WEB-INF/views/common/header.jsp" /> 

 <c:import url="/WEB-INF/views/member/mypageSidebar.jsp" />
 


<div id="box_act">
<h1>활동기록</h1>



<div id="recrBtn" class="moveBtn">
		<c:url var="moveRecrBoard" value="selectRecrBoadMemberId.do"><!-- url변수를 만듦, 연결할 대상 컨트롤러 매핑값 -->		
			<c:param name="memberID" value="${ loginMember.memberID }"></c:param>
		</c:url>
		<a href="${ moveRecrBoard }">활동기록</a>
		
	</div>

<div id="freeBtn" class="moveBtn">
		<c:url var="moveFreeBoard" value="selectFreeBoadMemberId.do"><!-- url변수를 만듦, 연결할 대상 컨트롤러 매핑값 -->		
			<c:param name="memberID" value="${ loginMember.memberID }"></c:param>
		</c:url>
		<a href="${ moveFreeBoard }" onclick="loadFreeBoard();">자유게시판</a>
</div>

<script>
function scrollToTop() {
    window.scrollTo(0, 0);
}
</script>

 
<hr id="hr">

<div class="scrollable-div">
	<table >
		<tr>
			<th class="title">활동 시작일</th>   
			<th class="title">제목</th>
			<th class="title">활동 마감일</th>
		</tr>
		<c:forEach var="rl" items="${list}">
		<c:url var="rbd" value="rbdetail.do">
			<c:param name="boardId" value="${rl.boardId}" />
			<c:param name="page" value="${page}" />
			<c:param name="categoryId" value="${categoryId}" />
		</c:url>
		<tr>	
			<th>${rl.recrActStartDate}</th>
			<th><a href="${rbd}">${rl.title}</a></th>
			<th>${rl.recrActEndDate}</th>
		</tr>
		</c:forEach>
	</table>
</div>
	<button onclick="scrollToTop()">맨 위로</button>
	

	
	
	
	
	 
	
	
	
</div>


</body>
</html>