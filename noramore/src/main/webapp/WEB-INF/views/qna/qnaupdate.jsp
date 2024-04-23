<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="/WEB-INF/views/common/sideSample.jsp" />
<c:import url="/WEB-INF/views/common/header.jsp" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/style.css">
<script src="/noramore/resources/js/jquery-3.7.0.min.js"></script>
<script>
	function moveUpdatePage() {
		// 수정 페이지로 이동하는 코드
		/* var form = document.createElement('form');
		form.method = 'POST';
		form.action = '${nu}';
		document.body.appendChild(form);
		form.submit(); */
		location.href = "${qup}";
	}
	function requestDelete() {
		location.href = "${qd}";
	}
	function goToList() {
		// 목록 페이지로 이동하는 코드
		window.location.href = 'qlist.do';
	}
</script>
<title>Qna 수정</title>
</head>
<body>
	<br>
	<form action="qupdate.do?boardId=${qna.boardId}" method="post"
		enctype="multipart/form-data">
		<input type="hidden" name="boardId" value="${ qna.boardId }">
		<input type="hidden" name="originalFilePath"
			value="${qna.originalFilePath }"> <input type="hidden"
			name="renameFilePath" value="${qna.renameFilePath }">

		<section id="write">
			<!-- 공지 수정 제목 및 버튼 -->
			<h1 align="center">${qna.title}수정하기</h1>
			<div class="line"></div>
			<br>
			<table>
				<tr>
					<th colspan="2">
						<!-- 삭제 버튼 -->
						<button class="blueBtn"
							style="float: right; margin-right: 30px; margin-left: 10px;"
							onclick="requestDelete(); return false;">삭제</button> <!-- 목록 버튼 -->
						<button class="whiteBtn" style="float: right;"
							onclick="goToList();">목록</button>
					</th>
				</tr>
			</table>
			<br>

			<!-- 공지사항 정보 표시 -->
			<p>
				<!-- 글 제목 -->
			<p>글 제목</p>
			<input type="text" placeholder="글 제목을 입력하세요." name="title">

			<!-- 작성자 -->
			<p>
				작성자 : <input type="text" name="memberID" readonly
					value="${ sessionScope.loginMember.memberID }">
			</p>

			<br>
			<!-- 작성일 -->
			<p>작성일</p>
			<h3>${qna.registDt}</h3>
			<br>
			<!-- 공지사항 내용 -->
			<p>내용</p>
			<textarea rows="30" cols="70" name="substance">${qna.substance}</textarea>

			<!-- 첨부파일 영역 -->
			<p>첨부파일</p>
			<c:if test="${!empty qna.originalFilePath}">
				<!-- 첨부파일 정보 표시 -->
            ${qna.originalFilePath}&nbsp;
            <input type="checkbox" name="deleteFlag" value="yes">
            파일 삭제 <br>
            변경 : <input type="file" name="upfile">
			</c:if>
			<c:if test="${empty qna.originalFilePath}">
				<!-- 첨부파일이 없는 경우 -->
            첨부된 파일 없음 <br>
				<br>
            추가 : 
            <br>
				<br>
				<input type="file" name="upfile">
			</c:if>

			<!-- 수정 및 취소 버튼 -->
			<tr>
				<th colspan="2"><input type="submit" value="수정하기">&nbsp;
					<input type="reset" value="수정 취소">&nbsp;</th>
			</tr>
		</section>
	</form>
</body>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</html>
