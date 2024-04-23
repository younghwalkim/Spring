<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고된 게시글 NoraMore : 나랑 함께 놀 사람~ 놀아!모아!</title>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/jquery-3.7.0.min.js"></script>
<c:import url="/WEB-INF/views/admin/adminSidebar.jsp" />
<style>
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f5f5f5;
}
.container {
    max-width: 1200px;
    margin: 50px auto;
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    padding: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
}
h2.title {
    text-align: center;
    margin-bottom: 20px;
}
table {
    width: 100%;
    border-collapse: collapse;
}
th, td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: center;
    white-space: nowrap; /* 텍스트가 한 줄로 표시되도록 설정 */
    overflow: hidden; /* 텍스트가 넘칠 경우 자르고 숨김 */
    text-overflow: ellipsis; /* 텍스트가 넘칠 경우 점 세 개로 생략 표시 */
}
th {
    background-color: #f2f2f2;
}
tr:nth-child(even) {
    background-color: #f9f9f9;
}
button {
    background-color: #4CAF50;
    color: white;
    border: none;
    padding: 5px 5px;
    border-radius: 4px;
    cursor: pointer;
}
button:hover {
    background-color: #45a049;
}
</style>
<script type="text/javascript">
function deleteRboard(boardId, categoryId) {
    $.ajax({
        type: 'GET',
        url: 'deleteboard.do',
        data: { page: 1,
        		boardId: boardId,
        		categoryId: categoryId },
        success: function(response) {
                alert("게시글이 삭제되었습니다.");
                location.reload();
        },
        error: function(xhr, status, error) {
            	console.error("에러 : ", error);
        }
    });
}
function deleteFboard(boardId, categoryId) {
    $.ajax({
        type: 'GET',
        url: 'freeboarddelete.do',
        data: { page: 1,
        		boardId: boardId,
        		categoryId: categoryId },
        success: function(response) {
                alert("게시글이 삭제되었습니다.");
                location.reload();
        },
        error: function(xhr, status, error) {
            	console.error("에러 : ", error);
        }
    });
}
</script>
</head>
<body>
<br><br><br><br><br><br>
    <div class="container">
        <h2 class="title">신고된 게시글</h2>
        <table>
            <thead>
                <tr>
                    <th>게시판</th>
                    <th>카테고리</th>
                    <th>글 번호</th>
                    <th>글 제목</th>
                    <th>작성자</th>
                    <th>조회수</th>
                    <th>게시일</th>
                    <th>신 고</th>
                    <th>관 리</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${ list }" var="a">
                    <tr>
                        <td>${ a.boardRef }</td>
                        <td>${ a.categoryName }</td>
                        <td>${ a.boardId }</td>
                        <td>
                            <c:choose>
                                <c:when test="${ a.boardRef eq '모집' }">
                                    <a href="${ pageContext.servletContext.contextPath }/rbdetail.do?boardId=${ a.boardId }&page=1&categoryId=${ a.categoryId }">${ a.title }</a>
                                </c:when>
                                <c:when test="${ a.boardRef eq '자유' }">
                                    <a href="${ pageContext.servletContext.contextPath }/fbdetail.do?boardId=${ a.boardId }&page=1&categoryId=${ a.categoryId }">${ a.title }</a>
                                </c:when>
                            </c:choose>
                        </td>
                        <td>${ a.memberID }</td>
                        <td>${ a.readCount }</td>
                        <td>${ a.registDate }</td>
                        <td>${ a.reportCount }</td>
	                        <c:choose>
								<c:when test="${ a.boardRef eq '모집' }">
									<td><button onclick="deleteRboard(${ a.boardId }, ${ a.categoryId });">삭제</button><td>
								</c:when>
								<c:when test="${ a.boardRef eq '자유' }">
									<td><button onclick="deleteFboard(${ a.boardId }, ${ a.categoryId });">삭제</button><td>
								</c:when>
							</c:choose>                   
                    </tr>
                </c:forEach> 
            </tbody>
        </table>
    </div>
    <c:import url="/WEB-INF/views/common/pagingView.jsp" />
</body>
</html>
