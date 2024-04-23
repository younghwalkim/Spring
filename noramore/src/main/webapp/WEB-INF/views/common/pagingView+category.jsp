<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     

<%-- 페이지 표시에 사용될 값들을 변수 선언 --%>
<c:set var="currentPage" value="${ requestScope.paging.currentPage }" />
<c:set var="urlMapping" value="${ requestScope.paging.urlMapping }" />
<c:set var="startPage" value="${ requestScope.paging.startPage }" />
<c:set var="endPage" value="${ requestScope.paging.endPage }" />
<c:set var="maxPage" value="${ requestScope.paging.maxPage }" />

<c:set var="action" value="${ requestScope.action }" />
<c:set var="keyword" value="${ requestScope.keyword }" />
<c:set var="begin" value="${ requestScope.begin }" />
<c:set var="end" value="${ requestScope.end }" />
<c:set var="categoryId" value="${requestScope.categoryId}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<%-- 목록 페이징 처리 : 검색 목록이 아닌 경우 --%>
<c:if test="${ empty action }">
<div class = "pagingview" style="text-align:center;">
	<%-- 첫 페이지로 이동 --%>	
	<c:if test="${ currentPage eq 1 }">
		<img src="resources/images/firstPage.jpg" width="13" height="13"> &nbsp;
	</c:if>	
	<c:if test="${ currentPage gt 1 }">
		<a href="/noramore/${ urlMapping }?page=1&categoryId=${categoryId}&keyword=${keyword}"><img src="resources/images/firstPage.jpg" width="13" height="13"></a> &nbsp;
	</c:if>
	
	<%-- 이전 페이지 그룹으로 이동 --%>
	<%-- 이전그룹이 있다면 --%>
	<c:if test="${ currentPage > 10 }">
		<a href="/noramore/${ urlMapping }?page=${ startPage - 1 }&categoryId=${categoryId}&keyword=${keyword}"><img src="resources/images/prePage.jpg" width="13" height="13"></a> &nbsp;
	</c:if>	
	<%-- 이전그룹이 없다면 --%>
	<c:if test="${ !(currentPage > 10 ) }">
		<img src="resources/images/prePage.jpg" width="13" height="13"> &nbsp;
	</c:if>	
	
	<%-- 현재 페이지가 속한 페이지그룹 페이지 숫자 출력 --%>	
	<c:forEach begin="${ startPage }" end="${ endPage }" step="1" var="p">
		<c:if test="${ p eq currentPage }">
			<font color="blue" size="4"><b>${ p }</b></font>
		</c:if>	
		<c:if test="${ p ne currentPage }">
			<a href="/noramore/${ urlMapping }?page=${ p }&categoryId=${categoryId}&keyword=${keyword}">${ p }</a>
		</c:if>
	</c:forEach>
	
	
	<%-- 다음 페이지 그룹으로 이동 --%>
	<%-- 다음그룹이 있다면 --%>
	<c:if test="${ endPage < maxPage }">
		<a href="/noramore/${ urlMapping }?page=${ startPage + 10 }&categoryId=${categoryId}&keyword=${keyword}">&nbsp;<img src="resources/images/nextPage.png" width="13" height="13"></a> &nbsp;
	</c:if>
	<%--다음그룹이 없다면 --%>
	<c:if test="${ !( endPage < maxPage ) }">
		&nbsp;<img src="resources/images/nextPage.png" width="13" height="13"> &nbsp;
	</c:if>
	
	<%-- 맨끝 페이지로 이동 --%>
	<c:if test="${ currentPage >= maxPage }">
		<img src="resources/images/endPage.png" width="13" height="13"> &nbsp;
	</c:if>
	<c:if test="${ !(currentPage >= maxPage) }">
		<a href="/noramore/${ urlMapping }?page=${ maxPage }&categoryId=${categoryId}&keyword=${keyword}"><img src="resources/images/endPage.png" width="13" height="13"></a> &nbsp;
	</c:if>
</div>
</c:if>


<%-- 검색(제목, 작성자, 내용) 페이징 처리 --%>
<c:if test="${ !empty action and !empty keyword }">
<div style="text-align:center;align-items:center;">
	
	<c:if test="${ currentPage eq 1 }">
		<img src="resources/images/firstPage.jpg" width="13" height="13"> &nbsp;
	</c:if>	
	<c:if test="${ currentPage gt 1 }">
		<a href="/noramore/${ urlMapping }?page=1&action=${ action }&keyword=${ keyword }&limit=${ limit }&categoryId=${categoryId}&keyword=${keyword}"><img src="resources/images/firstPage.jpg" width="13" height="13"></a> &nbsp;
	</c:if>
	
	<%-- 이전 페이지 그룹으로 이동 --%>
	<%-- 이전그룹이 있다면 --%>
	<c:if test="${ currentPage > 10 }">
		<c:url var="um1" value="${ urlMapping }">
			<c:param name="page" value="${ startPage - 10 }" />
			<c:param name="action" value="${ action }" />
			<c:param name="keyword" value="${ keyword }" />
			<c:param name="limit" value='${ limit }'/>
			<c:param name="categoryId" value="${categoryId}"></c:param>
		</c:url>
		<a href="${ um1 }"><img src="resources/images/prePage.jpg" width="13" height="13"></a> &nbsp;
	</c:if>
	<%-- 이전그룹이 없다면 --%>
	<c:if test="${ !(currentPage > 10 ) }">
		<img src="resources/images/prePage.jpg" width="13" height="13"> &nbsp;
	</c:if>	
	
	<%-- 현재 페이지가 속한 페이지그룹 숫자 출력 --%>	
	<c:forEach var="p" begin="${ startPage }" end="${ endPage }" step="1">
		<c:if test="${ p eq currentPage }">
			<font color="blue" size="4"><b>${ p }</b></font>
		</c:if>	
		<c:if test="${ p ne currentPage }">
			<a href="/noramore/${ urlMapping }?page=${ p }&action=${ action }&keyword=${ keyword }&limit=${ limit }&categoryId=${categoryId}&keyword=${keyword}">${ p }</a>
		</c:if>
	</c:forEach>	
	
	<%-- 다음 페이지 그룹으로 이동 --%>
	<%-- 다음그룹이 있다면 --%>
	<c:if test="${ endPage < maxPage }">
		<a href="/noramore/${ urlMapping }?page=${ startPage + 10 }&action=${ action }&keyword=${ keyword }&limit=${ limit }&categoryId=${categoryId}&keyword=${keyword}">&nbsp;<img src="resources/images/nextPage.png" width="13" height="13"></a> &nbsp;
	</c:if>
	<%-- 다음그룹이 없다면 --%>
	<c:if test="${ !( endPage < maxPage ) }">
		&nbsp;<img src="resources/images/nextPage.png" width="13" height="13"> &nbsp;
	</c:if>		
	
	<c:if test="${ currentPage >= maxPage }">
		<img src="resources/images/endPage.png" width="13" height="13"> &nbsp;
	</c:if>	
	<c:if test="${ currentPage < maxPage }">
		<a href="/noramore/${ urlMapping }?page=${ maxPage }&action=${ action }&keyword=${ keyword }&limit=${ limit }&categoryId=${categoryId}&keyword=${keyword}"><img src="resources/images/endPage.png" width="13" height="13"></a> &nbsp;
	</c:if>
	
</div>
</c:if>

<%-- 검색(등록날짜) 페이징 처리 --%>
<c:if test="${ !empty action and action eq 'date' or action eq 'enrolldate' }">
<div style="text-align:center;align-items:center;">
	
	<c:if test="${ currentPage eq 1 }">
		<img src="resources/images/firstPage.jpg" width="13" height="13"> &nbsp;
	</c:if>	
	<c:if test="${ currentPage gt 1 }">
		<a href="/noramore/${ urlMapping }?page=1&action=${ action }&begin=${ begin }&end=${ end }&categoryId=${categoryId}&keyword=${keyword}"><img src="resources/images/firstPage.jpg" width="13" height="13"></a> &nbsp;
	</c:if>
	
	<%-- 이전 페이지 그룹으로 이동 --%>
	<%-- 이전그룹이 있다면 --%>
	<c:if test="${ currentPage > 10 }">
		<a href="/noramore/${ urlMapping }?page=${ startPage - 10 }&action=${ action }&begin=${ begin }&end=${ end }&categoryId=${categoryId}&keyword=${keyword}"><img src="resources/images/prePage.jpg" width="13" height="13"></a> &nbsp;
	</c:if>
	<%-- 이전그룹이 없다면 --%>
	<c:if test="${ !(currentPage > 10 ) }">
		<img src="resources/images/prePage.jpg" width="13" height="13"> &nbsp;
	</c:if>	
	
	<%-- 현재 페이지가 속한 페이지그룹 숫자 출력 --%>	
	<c:forEach var="p" begin="${ startPage }" end="${ endPage }" step="1">
		<c:if test="${ p eq currentPage }">
			<font color="blue" size="4"><b>${ p }</b></font>
		</c:if>	
		<c:if test="${ p ne currentPage }">
			<a href="/noramore/${ urlMapping }?page=${ p }&action=${ action }&begin=${ begin }&end=${ end }&categoryId=${categoryId}&keyword=${keyword}">${ p }</a>
		</c:if>	
	</c:forEach>
	
	<%-- 다음 페이지 그룹으로 이동 --%>
	<%-- 다음그룹이 있다면 --%>
	<c:if test="${ endPage < maxPage }">
		<a href="/noramore/${ urlMapping }?page=${ startPage + 10 }&categoryId=${categoryId}&keyword=${keyword}">&nbsp;<img src="resources/images/nextPage.png" width="13" height="13"></a> &nbsp;
	</c:if>
	<%-- 다음그룹이 없다면 --%>
	<c:if test="${ !( endPage < maxPage ) }">
		&nbsp;<img src="resources/images/nextPage.png" width="13" height="13"> &nbsp;
	</c:if>	
	
	<%-- 맨끝 페이지로 이동 --%>
	<c:if test="${ currentPage >= maxPage }">
		<img src="resources/images/endPage.png" width="13" height="13"> &nbsp;
	</c:if>	
	<c:if test="${ currentPage < maxPage }">
		<a href="/noramore/${ urlMapping }?page=${ maxPage }&action=${ action }&begin=${ begin }&end=${ end }&categoryId=${categoryId}&keyword=${keyword}"><img src="resources/images/endPage.png" width="13" height="13"></a> &nbsp;
	</c:if>
	
</div>
</c:if>


</body>
</html>