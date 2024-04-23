<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail</title>
<style type="text/css">


/* 전체 페이지 스타일 */
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f8f9fa;
}

h1 {
    text-align: center;
    margin-top: 20px;
}

.board-container {
    margin: 20px auto;
    width: 40%;
}

.board-con-01 {
    padding: 20px;
    border: 1px solid #ced4da;
    border-radius: 10px;
    background-color: #fff;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.board-title {
    margin: 0;
    color: #343a40;
}

.board-body {
    margin-top: 10px;
    padding: 10px;
    border-top: 1px solid #ced4da;
}

.board-footer {
    margin-top: 20px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

/* 버튼 스타일 */
.btn {
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
    font-size: 16px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
}

.btn-primary {
    background-color: #007bff;
    color: #fff;
}

.btn-danger {
    background-color: #dc3545;
    color: #fff;
}

.btn:hover {
    background-color: #0056b3;
}

.img-circle {
    border-radius: 50%;
    margin-right: 10px;
    width: 30px;
}

.username {
    margin-left: 10px;
}

.board-footer{
	margin-bottom: 20px;
	display: block;
    font-weight: bold;
    margin-bottom: 5px;
}

   /* 내용영역 */
textarea {
   resize: none;
   width: 93%;
   height: calc(5 * 5em);
   padding: 10px 10px;
   border: 1px solid #000;
   border-radius: 7px;
   /* margin-bottom: 1px; */
 } 


	#meetingdetail {
        width: 100%;
        margin: 0 auto;
        border-collapse: collapse;
    }

    #meetingdetail th, #meetingdetail td {
        padding: 8px;
        border: 1px solid #dddddd;
        text-align: center;
        width: 25%; 
    }
    
    /* 테이블 컬럼 배경색 적용 : 밝은 회색 */
    #meetingdetail th:first-child,
    #meetingdetail th:nth-child(3) {
        background-color: #f2f2f2; 
        text-align: center;
    }
    
    button {
        background-color: #41AEF2; 
        color: white;
        border: none;
        padding: 5px 20px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        font-weight: bold;
        margin: 10px 4px;
        cursor: pointer;
    }   
    
/* 모달 CSS */
.boardDelModal {
    display: none; /* 기본적으로 숨김 */
    position: fixed; /* 고정 위치 */
    z-index: 1; /* 최상위에 배치 */
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto; /* 필요한 경우 스크롤 가능 */
    background-color: rgba(0, 0, 0, 0.4); /* 투명한 검은색 배경 */
}

/* 모달 내용 */
.board-modal-content {
    background-color: #fefefe;
    margin: 15% auto; /* 화면 상단에서 15% 떨어진 위치에 중앙 정렬 */
    padding: 20px;
    border: 1px solid #888;
    width: 250px; /* 화면 크기에 따라 조절 가능 */
    border-radius: 5px;
    box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
}


</style>


<!-- 삭제 처리  -->
<c:url var="bdel" value="bdelete.do">
	<c:param name="bNo" value="${ board.bNo }" />
	<c:param name="tNo" value="${ tNo }" />
</c:url>

<!--  목록으로 이동 처리 -->
<c:url var="blist" value="bmain.do">
	<c:param name="tNo" value="${ tNo }" />
</c:url>


<script type="text/javascript" src="/getdrive/resources/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript">	

function moveListPage(){
	location.href="${ blist }";
}

//href 이나 a 태그 방식은 무조건 GET 방식이다.
function moveUpdatePage(){
	location.href="bupdate.do?tNo=${ tNo }&page=${ currentPage }&bNo=${ board.bNo }";
}

function showDeleteModal() {
    var modal = document.getElementById("boardDeleteModal");
    modal.style.display = "block";
}

function hideDeleteModal() {
    var modal = document.getElementById("boardDeleteModal");
    modal.style.display = "none";
}

function confirmDelete() {
    location.href = "${ bdel }";
    hideDeleteModal();
}

</script>
</head>
<body>

<div id="container">

  <div id="jb-header">      
	<c:import url="/WEB-INF/views/common/teamtop.jsp" />        
  </div>
  
  <div id="sidebar">
	<c:import url="/WEB-INF/views/common/teamleft.jsp" />
  </div>
  
  <div id="content">
	
		<hr>
		<h2 align="center">${ board.bNo }  번 상세보기</h2>
		<br>
		
		<table id=meetingdetail align="center" width="500" border="1" cellspacing="5" >
		    <tr>
		        <th>제 목</th>
		        <td colspan="3">${ board.bTitle }</td>
		    </tr>
		    <tr>
		        <th>작성자</th>
		        <td>${ board.bName }</td>
				<th>첨부파일</th>
		        <td>				        
					<c:if test="${ !empty board.bOriginFileName }">
						<c:url var="bdown" value="bdown.do">
							<c:param name="ofile" value="${ board.bOriginFileName }"/>
							<c:param name="rfile" value="${ board.bRenameFileName }"/>
						</c:url>
						<a href="${ bdown }">${ board.bOriginFileName }</a>
					</c:if>
					<c:if test="${ empty board.bOriginFileName }">
					&nbsp;
					</c:if>								            
		        </td>
		    </tr>
		    <tr>
		        <th>등록날짜</th>
		        <td><fmt:formatDate pattern="yyyy-MM-dd a HH:mm" value="${ board.bcDate }" /></td>
		        <th>수정날짜</th>
		        <td><fmt:formatDate pattern="yyyy-MM-dd a HH:mm" value="${ board.buDate }" /></td>
		    </tr>
		    <tr>
		       <th>내 용</th>
		      <td colspan="3"><textarea style="border:none;outline: none;" rows="3" cols="50" name="mtContent" readonly="readonly">${ board.bContent }</textarea></td>
		    </tr>		   
		    <tr>
			    <%-- 로그인한 경우 : 본인 글 상세보기 일때는 수정페이지로 이동과 삭제 버튼 표시함 --%>
			    <td colspan="4" style="text-align: center;">

					<div class="board-footer" style="display: flex; justify-content: space-between;">
						<div><button  class="btn btn-primary" onclick="javascript:history.go(-1); return false;">목록</button></div>
						<div class="pull-right">
							<button class="btn btn-primary" onclick="moveUpdatePage(); return false;"> 수정 </button>
							<button class="btn btn-danger" onclick="showDeleteModal(); return false;"> 삭제 </button>
						</div>
					</div>				        
		        
		    	</td>
			</tr>
		</table>
		
		<!-- 모달 창 -->
		<div id="boardDeleteModal" class="boardDelModal">
			<!-- 모달 내용 -->
			<div class="board-modal-content">
				<p>삭제 후 복구는 불가능합니다. &nbsp; <br>
				정말 삭제하시겠습니까?</p>
				<button onclick="confirmDelete();">확인</button>
				<button onclick="hideDeleteModal();">취소</button>
			</div>
		</div>
		
  </div>

  <div id="footer">
    <c:import url="/WEB-INF/views/common/teamfooter.jsp" />
  </div>

</div>

</body>
</html>
