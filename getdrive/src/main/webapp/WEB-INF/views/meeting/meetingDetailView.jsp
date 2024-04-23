<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회의록 상세보기</title>
<style type="text/css">
/* 2024.04.10 eunbi : 팀공통 상단영역과 충돌하여 수정함  */
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
 
 

   /* 모달 CSS */
   #deleteModal {
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
   #modal-mt {
       background-color: #fefefe;
       margin: 15% auto; /* 화면 상단에서 15% 떨어진 위치에 중앙 정렬 */
       padding: 20px;
       border: 1px solid #888;
       width: 40%; /* 화면 크기에 따라 조절 가능 */
       border-radius: 5px;
       box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
   } 
</style>

<%-- 아래의 자바스크립트 함수에서 사용하는 url 요청 처리를 별도로 변수화 시킴 --%>
<!-- 수정페이지 이동 -->
<c:url var="mup" value="mmoveup.do">
	<c:param name="mtId" value="${ meeting.mtId }" />
</c:url>

<!-- 삭제 -->
<c:url var="mdel" value="mdelete.do">
	<c:param name="mtId" value="${ meeting.mtId }" />
	<c:param name="rfile" value="${ meeting.mtRenameFileName }" />
</c:url>

<script type="text/javascript">
//게시글(원글, 댓글) 수정 페이지로 이동 처리 함수
function moveUpdatePage(){
   location.href = "${ mup }";
}

//게시글(원글, 댓글) 삭제 요청 함수
function showDeleteModal() {
    var modal = document.getElementById("deleteModal");
    modal.style.display = "block";
}

function hideDeleteModal() {
    var modal = document.getElementById("deleteModal");
    modal.style.display = "none";
}

function confirmDelete() {
    location.href = "${ mdel }";
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
	<h2 align="center">${ meeting.mtId } 번 회의록 상세보기</h2>
	<br>
	
	<table id=meetingdetail align="center" width="500" border="1" cellspacing="5" >
	    <tr>
	        <th>제 목</th>
	        <td colspan="3">${ meeting.mtTitle }</td>
	    </tr>
	    <tr>
	        <th>작성자</th>
	        <td>${ meeting.mtWriter }</td>
	        <th>작성자 아이디</th>
	        <td>${ meeting.mtWriterId }</td>
	    </tr>
	    <tr>
	        <th>등록날짜</th>
	        <td><fmt:formatDate value="${ meeting.mtDate }" pattern="yyyy-MM-dd" /></td>
	        <th>수정날짜</th>
	        <td>
	        	<fmt:formatDate value="${ meeting.mtUpdate }" pattern="yyyy-MM-dd" />
	        	<c:if test="${ empty meeting.mtUpdate }">-</c:if>
	        </td>
	    </tr>
	    <tr>
	    	<th>중요도</th>
	        <td>${ meeting.mtImportance }</td>
	        <th>첨부파일</th>
	        <td>
	            <c:if test="${ !empty meeting.mtOriginalFileName }">
	                <c:url var="nfd" value="mfdown.do">
	                    <c:param name="ofile" value="${meeting.mtOriginalFileName}" />
	                    <c:param name="rfile" value="${meeting.mtRenameFileName}" />
	                </c:url>
	                <a href="${ nfd }">${meeting.mtOriginalFileName}</a>
	            </c:if>
	            <c:if test="${ empty meeting.mtOriginalFileName }">-</c:if>
	        </td>
	    </tr>
	    <tr>
	       <th>내 용</th>
	      <td colspan="3"><textarea style="border:none;outline: none;" rows="3" cols="50" name="mtContent" readonly="readonly">${ meeting.mtContent }</textarea></td>
	    </tr>
	    <tr>
	    </tr>
	    
	    <tr>
		    <%-- 로그인한 경우 : 본인 글 상세보기 일때는 수정페이지로 이동과 삭제 버튼 표시함 --%>
		    <td colspan="4" style="text-align: center;">
	        <c:if test="${ !empty loginMember }">
	            <c:if test="${ loginMember.name eq meeting.mtWriter }">
	                <button style="display: inline-block; margin-right: 10px;" onclick="moveUpdatePage(); return false;">수정페이지로 이동</button>
	                <button style="display: inline-block; margin-right: 10px;" onclick="showDeleteModal(); return false;">글삭제</button>
	            </c:if>
	        </c:if>
	        <button style="display: inline-block;" onclick="javascript:history.go(-1);">목록</button>
	    	</td>
		</tr>
	</table>

		   <!-- 모달 창 -->
		   <div id="deleteModal">
			     <!-- 모달 내용 -->
			     <div id="modal-mt">
			       <p>이 글을 삭제하시겠습니까? 삭제하시면 복구할 수 없습니다. </p>
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