<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="/WEB-INF/views/common/sideSample.jsp" />
<c:import url="/WEB-INF/views/common/header.jsp" />

<c:set var="currentLimit" value="${ requestScope.limit }" />
<c:set var="nowpage" value="1" />
<c:if test="${ !empty requestScope.currentPage }">
	<c:set var="nowpage" value="${ requestScope.currentPage }" />
</c:if>


<!DOCTYPE html >
<html>
<head>
<style type="text/css">
form fieldset {
	width: 600px;
}

form.sform {
	background: lightgray;
	width: 650px;
	position: relative;
	left: 450px;
	display: none; /* 안 보이게 함 */
}
</style>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/style.css">
<script type="text/javascript" src="/resources/js/jquery-3.7.0.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	$(function() {
		//input 태그의 name 이 item 의 값이 바뀌면(change) 작동되는 이벤트 핸들러 작성
		$('input[name=item]').on('change', function() {
			//여러 개의 태그 중에서 체크표시가 된 태그를 선택
			$('input[name=item]').each(function(index) {
				//선택된 radio 순번대로 하나씩 checked 인지 확인함
				if ($(this).is(':checked')) {
					//체크 표시된 아이템에 대한 폼이 보여지게 처리함
					$('form.sform').eq(index).css('display', 'block');
				} else {
					//체크 표시 안된 아이템의 폼은 안 보이게 처리함
					$('form.sform').eq(index).css('display', 'none');
				}
			}); //each
		}); //on
	}); //document ready

	function changeLimit(limit) {
		//alert(limit);
		location.href = "${ pageContext.servletContext.contextPath }/nlist.do?page=1&limit="
				+ limit;
	}
</script>
</head>
<body>
	<section id="board">
		<h1>공지사항</h1>
		<div class="line"></div>
		
		<c:if
			test="${!empty sessionScope.loginMember and sessionScope.loginMember.adminYN == 'Y'}">
			<button onclick="location.href='qnawrite.do';" class="blueBtn">글쓰기</button>
		</c:if>
		
		<%-- 항목별 검색 기능 추가 --%>
		<fieldset id="ss" style="border:black; width:220px">
			<legend>검색할 항목을 선택하세요.</legend>
			<br>
			<input type="radio" name="item" id="title"> 제목 &nbsp; <input
				type="radio" name="item" id="content"> 내용 &nbsp; <input
				type="radio" name="item" id="date"> 등록날짜 &nbsp; 
				<br><br>
				<b style="color: blue;">출력할 목록 갯수를 선택하세요</b> 
				<br>		
				<select id="limit"
				onchange="changeLimit(this.value);">
				<c:if test="${ currentLimit eq 10 }">
					<option value="10" selected>10개씩 출력</option>
				</c:if>
				<c:if test="${ currentLimit ne 10 }">
					<option value="10">10개씩 출력</option>
				</c:if>
				<c:if test="${ currentLimit eq 15 }">
					<option value="15" selected>15개씩 출력</option>
				</c:if>
				<c:if test="${ currentLimit ne 15 }">
					<option value="15">15개씩 출력</option>
				</c:if>
				<c:if test="${ currentLimit eq 20 }">
					<option value="20" selected>20개씩 출력</option>
				</c:if>
				<c:if test="${ currentLimit ne 20 }">
					<option value="20">20개씩 출력</option>
				</c:if>
			</select>
		</fieldset>
		<br>

		<%-- 검색 항목별 값 입력 전송용 폼 만들기 --%>
		<%-- 제목 검색 폼 --%>
		<form id="titleform" class="sform" action="nsearchTitle.do"
			method="get">
			<input type="hidden" name="action" value="title"> <input
				type="hidden" name="limit" value="${ currentLimit }">
			<fieldset>
				<legend>검색할 제목을 입력하세요.</legend>
				<input type="search" name="keyword" size="50"> &nbsp; <input
					type="submit" value="검색">
			</fieldset>
		</form>

		<%-- 내용 검색 폼 --%>
		<form id="contentform" class="sform" action="nsearchContent.do"
			method="get">
			<input type="hidden" name="action" value="content"> <input
				type="hidden" name="limit" value="${ currentLimit }">
			<fieldset>
				<legend>검색할 내용을 입력하세요.</legend>
				<input type="search" name="keyword" size="50"> &nbsp; <input
					type="submit" value="검색">
			</fieldset>
		</form>

		<%-- 등록날짜 검색 폼 --%>
		<form id="dateform" class="sform" action="nsearchDate.do" method="get">
			<input type="hidden" name="action" value="date"> <input
				type="hidden" name="limit" value="${ currentLimit }">
			<fieldset>
				<legend>검색할 등록날짜를 선택하세요.</legend>
				<input type="date" name="begin"> ~ <input type="date"
					name="end"> &nbsp; <input type="submit" value="검색">
			</fieldset>
		</form>
		<table>
			<tr>
				<th>No</th>
				<th>제목</th>
				<th>작성자</th>
				<th>내용</th>
				<th>조회수</th>
				<th>작성일자</th>
			</tr>
			<c:forEach items="${ requestScope.list }" var="n">
				<tr>
					<td align="right">${ n.boardId }</td>
					<td align="right"><a
						href="${ pageContext.servletContext.contextPath }/noticedetail.do?no=${ n.boardId }">
							${ n.title }</a></td>
					<td align="right">${ n.memberID }</td>
					<td align="right">${ n.substance }</td>
					<td align="right">${ n.readCount }</td>
					<td align="center"><fmt:formatDate value="${ n.registDt }"
							pattern="yyyy-MM-dd" /></td>
			</c:forEach>
		</table>
		
	<br><br>
	<c:import url="/WEB-INF/views/common/pagingView.jsp" />
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	</section>



	
</body>


</html>