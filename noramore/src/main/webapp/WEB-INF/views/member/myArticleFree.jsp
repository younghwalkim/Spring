<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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


<hr>
<div class="scrollable-div">
<table >
	<tr >
		<th class="title">좋아요</th>   
		<th class="title">제목</th>
		<th class="title">등록일</th>
	</tr>
	<c:forEach var="fl" items="${list}">
	<c:url var="fbd" value="fbdetail.do">
		<c:param name="boardId" value="${fl.boardId}" />
		<c:param name="page" value="${page}" />
		<c:param name="categoryId" value="${categoryId}" />
	</c:url>
	<tr>
		<th>${fl.likeCount}</th>
		<th><a href="${fbd}">${fl.title}</a></th>
		<th>${fl.registDate}</th>
	</tr>
	</c:forEach>
</table>
</div>
<button onclick="scrollToTop()">맨 위로</button>

</div>

</body>
</html>