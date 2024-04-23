<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    

<%@ include file="/WEB-INF/views/common/sideSample.jsp"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%> 

<%--
<c:set var="currentLimit" value="${ requestScope.limit }" />
<c:set var="nowpage" value="1" />
<c:if test="${ !empty requestScope.currentPage }">
	<c:set var="nowpage" value="${ requestScope.currentPage }" />
</c:if>
 --%>
<c:if test="${!empty requestScope.currentPage}">
	<c:set var="page" value="${requestScope.currentPage}" />
</c:if>


<%--<c:set var="categoryId" value="1"/> --%> 
<c:if test="${!empty requestScope.categoryId}">
	<c:set var="categoryId" value="${requestScope.categoryId}" />
</c:if>

<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="resources/css/style.css">
	<link rel="stylesheet" href="resources/css/dropdown.css">
	<script type="text/javascript" src="/resources/js/jquery-3.7.0.min.js"></script>
	
		<title>NoraMore</title>


<script type="text/javascript">
    window.onload = function () {
        var sortingSelect = document.getElementById("sortingSelect");
        var itemSelect = document.getElementById("itemSelect");

        sortingSelect.onchange = function () {
            var selectedOption = sortingSelect.value;

            if (selectedOption === "recent") {
                sortRecent();
            } else if (selectedOption === "views") {
                sortViews();
            } else if (selectedOption === "likes") {
                sortLikes();
            }
        };
        
       
    };

    function sortRecent() {
    	 location.href = "freerecentlist.do?page=" + ${page} + '&categoryId=' + ${categoryId};
    	 console.log("최신순 정렬 실행");
    }

    function sortViews() {
    	location.href = "freeviewslist.do?page=" + ${page} + '&categoryId=' + ${categoryId};
    	console.log("조회순 정렬 실행");
    }

    function sortLikes() {
    	 location.href = "freelikeslist.do?page=" + ${page} + '&categoryId=' + ${categoryId};
    	 console.log("좋아요순 정렬 실행");
    }
    
    
    function selectCategory(value){
        $("#category").text(value).val(value);
    }
    
    function selectCategory(value){
    	$("#category").text(value).val(value);
    }
    
    function fbwriteform(){
    	if(${empty sessionScope.loginMember}){
    		alert("로그인을 해야 합니다.");
    		return;
    	}
    	var page = ${page};
        var categoryId = ${categoryId};
        
    	location.href = "freeboardwrite.do?page=" + page + '&categoryId=' + categoryId;
    	
    	return false;
    }
    
    
    function check(){
    	var limitSelect = document.getElementById('limitSelect');
        var limi = limitSelect.value;
        console.log(limi);
        
        var keywordE = document.getElementById('keyword');
        var keyword = keywordE.value;
        console.log(keyword);
        
        var actionE = document.getElementById('action');
        var action = actionE.value;
        console.log(action);
    }
    

    function changeFormAction() {
        var selectedValue = document.getElementById("action").value;
        document.getElementById("searchaction").action = selectedValue;
    }
    
    
</script>

<style>

.search{
	border: 1px solid #15bef5;
    border-radius: 7px;
    padding: 0 3px;
    display: flex;
    flex-direction: row;
    align-items: center;
    height: 27px;
}

<%--
.header {

  z-index: 100; /* 헤더를 위로 */
}

.sidebar {

  z-index: 100; /* 사이드바를 위로 */
}

.content {
	position: relative;
    margin-right: 100px; /* 사이드바의 폭에 따라 조정할 수 있습니다 */
    display: flex; 
}
--%>

#boardfree {
	top: 150px;
	left: 10%;
	margin-left: 200px;
}


</style>

<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href="resources/css/dropdown.css">

</head>
<body>

<%-- 항목별 검색 기능 추가 --%>
<%--
<fieldset id="ss">
	<legend>검색할 항목을 선택하세요.</legend>
	<input type="radio" name="item" id="title"> 제목 &nbsp;
	<input type="radio" name="item" id="writer"> 작성자 &nbsp;	
</fieldset>
 --%>


 
<%-- 제목, 글작성자 검색 폼 --%>
 <%--
<section style='width: 1200px; border: 0px; margin: 10px 0;'>
	검색 기준: <div class="dropdown">
			    <select id="action" name="action" onchange="changeFormAction()">
			        <option value="fbsearchTitle.do">글제목</option>
			        <option value="fbsearchWriter.do">작성자ID</option>
			    </select>
			</div>
		<form id="searchaction" action="fbsearchTitle.do" method="post" >
			<fieldset style='width: 1200px; border: 0px;'>
				<div class="search" style='width: 180px;'>
					<input id="keyword" name="keyword" style="width:140; height:25;">
					<button>검색</button>
				</div> &nbsp; 
				한 페이지에 출력할 목록 갯수 : <select name="limit" id="limitSelect">
					<option value="10" selected>10</option>
					<option value="15">15</option>
					<option value="20">20</option>
					<c:set var="limi" value="${limit}" ></c:set>
				</select> &nbsp; 
			</fieldset>
			<input  type="hidden" name="categoryId" value="${categoryId}">
		</form>
	</section>
<button onclick="check()">정보 확인</button>
 --%>
<!--  
<%-- 정렬 기준 조회순 --%>

<form id="viewform" class="sform" action="freeviewslist.do" method="post">
	<input type="hidden" name="action" value="view">
	
<fieldset>
	<input type="submit" value="최신순">
	
</fieldset>

</form>	

-->
<%-- <section style='width: 1200px; left: 20%;'>--%>
<div class=content>
	<section id="board" style="margin-left: 200px;">

		<h1>자유게시판</h1>
		<div class="line"></div>
		<%-- 
		<%
		if (request.getParameter("pos") != null) {
		%>
		<h3>
			'<%=request.getParameter("pos")%>' 근처 검색 결과
		</h3>
		<%
		}
		%>
		--%>
		<%-- 제목, 글작성자 검색 폼 --%>
 
<section style='width: 1200px; border: 0px; margin: 10px 0;'>
	검색 기준: <div class="dropdown">
			    <select id="action" name="action" onchange="changeFormAction()">
			        <option value="fbsearchTitle.do">글제목</option>
			        <option value="fbsearchWriter.do">작성자ID</option>
			    </select>
			</div>
		<form id="searchaction" action="fbsearchTitle.do" method="post" >
		
			<fieldset style='width: 1200px; border: 0px;'>
				<div class="search" style='width: 180px;'>
					<input id="keyword" name="keyword" style="width:140; height:25;">
					<button>검색</button>
				</div> &nbsp; 
				한 페이지에 출력할 목록 갯수 : <select name="limit" id="limitSelect">
					<option value="10" selected>10</option>
					<option value="15">15</option>
					<option value="20">20</option>
					<c:set var="limi" value="${limit}" ></c:set>
				</select> &nbsp; 
			</fieldset>
			<input  type="hidden" name="categoryId" value="${categoryId}">
		</form>
		<button class="whiteBtn" style="float: right;  margin-right:10;" onclick="fbwriteform()">글 작성</button>
		
	</section>
	
	
<%-- <button onclick="check()">정보 확인</button>--%>
		
		
		<!--  <button onclick="location.href='freeboardwrite.do';" class="blueBtn">글쓰기</button>-->
		
		
			<div class="position2">
        <%--<p>정렬 기준</p> --%>
    <div class="dropdown">
        <select id="sortingSelect">
        	<option value="index">정렬기준</option>
            <option value="recent">최신순</option>
            <option value="views">조회순</option>
            <option value="likes">좋아요순</option>
        </select>
    </div>
    
   	 </div>
   	     
   	 
   	<!-- 추가한 부분 *********************************** -->
   	<%-- 
   	<br>
   	<br>
   	
   	 <div class="dropdown">
    <button class="dropbtn" id="category" name="category" value="">Dropdown</button>
    <div class="dropdown-content">
        <a onclick="selectCategory('볼링')">볼링</a>
        <a onclick="selectCategory('클라이밍')">클라이밍</a>
        <a onclick="selectCategory('싸이클')">싸이클</a>
        <a onclick="selectCategory('헬스')">헬스</a>
        <a onclick="selectCategory('수상레저')">수상레저</a>
        <a onclick="selectCategory('등산')">등산</a>
    </div>
</div>

--%>

		<!-- ****************************************** -->
		
		<table style='width: 1200px;'>
			<thead>
			
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자ID</th>
				<th>조회수</th>
				<th>좋아요수</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="fl" items="${ requestScope.list }">
	            <c:url var="fbd" value="fbdetail.do">
					<c:param name="boardId" value="${fl.boardId}" />
					<c:param name="page" value="${nowpage}" />
					<c:param name="categoryId" value="${categoryId}" />
				</c:url>
                <tr>
                    <td>${fl.boardId}</td>
                    <td><a href="${fbd}">${fl.title}</a></td>
                    <td>${fl.memberId}</td>
                    <td>${fl.readCount}</td>
                    <td>${fl.likeCount}</td>
                </tr>
            </c:forEach>	
				
			
			</tbody>
			

		</table>
		
		<!-- 추가한 부분 **************** -->
<br>
 <c:import url="/WEB-INF/views/common/pagingView+category.jsp" />

<!-- 추가한 부분 **************** -->
	</section>
</div>
<%--</section>--%>
</body>
</html>