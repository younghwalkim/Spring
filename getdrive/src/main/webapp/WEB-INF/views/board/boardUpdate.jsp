<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
    
    <!--  목록으로 이동 처리 -->
<c:url var="blist" value="bmain.do">
	<c:param name="tNo" value="${ tNo }" />
</c:url>
        
<c:set var="currentPage" value="${ requestScope.page }" />
   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update</title>
<style type="text/css">

/* 전체 페이지 스타일링 */
body {
    font-family: Arial, sans-serif;
    background-color: #f5f5f5
    margin: 0;
    padding: 0;
}

.board-container {
    max-width: 800px;
    margin: 20px auto;
    background-color: #fff;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.board-container h2 {
    margin-top: 0;
}

/* 폼 요소 스타일링 */
.form-group {
    margin-bottom: 20px;
}

label {
    display: block;
    font-weight: bold;
    margin-bottom: 5px;
}

input[type="text"],
input[type="file"],
textarea {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-sizing: border-box;
}

textarea {
    resize: none;
    height: 200px;
}

/* 버튼 스타일링 */
.pull-right {
    text-align: right;
}

input[type="submit"],input[type="reset"],
input[type="button"] {
    padding: 10px 20px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
}

input[type="submit"]:hover,
input[type="button"]:hover {
    background-color: #0056b3;
}


</style>



<script type="text/javascript">

function moveListPage(){
	
	location.href="${ blist }";
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

<section class="board-container">

	<div class="board-container">
	
		<form id="updateForm" action="boriginupdate.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="bNo" value="${ board.bNo }">
			<input type="hidden" name="page" value="${ currentPage }">
			<input type="hidden" name="bOriginFileName"value="${ board.bOriginFileName }">
			<input type="hidden" name="bRenameFileName"value="${ board.bRenameFileName }">
			<input type="hidden" name="tNo" value="${ tNo }">
			
			<div class="board-box_primary">
			
				<div class="board-header with-border">
				
					<h2 class="board-title">${ board.bNo } 번 게시글 수정</h2>
				</div>
				
				<div class="board-body">
				
					
					
					<div class="form-group">
						<label for="bName"> 작성자 </label>
						<input class="form-control" type="text"  id="bName" name="bName" readonly value="${ board.bName }" >				
					</div>
					
					<div class="form-group">
						<label for="bTitle"> 제목 </label>
						<input class="form-control" type="text" id="bTitle" name="bTitle" placeholder="제목을 입력해주세요..." value="${ board.bTitle }">
					</div>
					
					<div class="form-group">
						<th>첨부파일</th>
						<td>
						
						<c:if test="${ !empty board.bOriginFileName }"	>
							${ board.bOriginFileName } &nbsdp;
							<input type="checkbox" name="deleteFlag"	value="yes"> 파일삭제 <br>
							변경 : <input type="file" name="upfile">
						</c:if>
						<c:if test="${ empty board.bOriginFileName }">
							첨부된 파일 없음 <br>
							추가 : <input type="file" name="upfile">
						</c:if>
						</td>
					</div>
					
					<div class="form-group">
						<label for="bContent"> 내용 </label>
						<textarea class="form-control"  id="bContent" name="bContent" rows="30"
						placeholder="수정할 내용을 입력하세요......" style="resize: none;">${ board.bContent }</textarea>
					</div>
					
				</div>
				
					<div class="pull-right">
						<input type="submit" value="수정하기"> &nbsp;
						<input type="reset" value="수정취소">
						<input type="button" value="목록" onclick="moveListPage(); return false;">
					</div>		
			</div>
		</form>
	</div>
</section>

  </div>

  <div id="footer">
    <c:import url="/WEB-INF/views/common/teamfooter.jsp" />
  </div>

</div>

</body>
</html>