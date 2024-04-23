<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/header.jsp"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="currentPage" value="${ requestScope.page }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--  
<c:url var="insertAppl" value="insertApp.do">
	<c:param name="boardId" value="${FreeBoard.boardId}" />
	<c:param name="memberID" value="${loginMember.memberID}" />
</c:url>
-->


</script>
<title>NoraMore</title>


<style>
    div {
    	margin-bottom: 20px;
    }
</style>
</head>
<body>
<form action="freeboardoriginupdate.do" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="boardId" value="${ freeBoard.boardId }">
	<input type="hidden" name="page" value="${ currentPage }">
	<input type="hidden" name="freeOriginalFileName" value="${ freeBoard.freeOriginalFileName }">
	<input type="hidden" name="freeRenameFileName" value="${ freeBoard.freeRenameFileName }">
	
	<%-- <input type="hidden" value="<%= vo.getUserId() %>" name="writer"> --%>
	<div id="write">
	
		<h1>자유게시판글</h1>
		<div class="line"></div>
		
		<!-- <h1 style="text-align: left;">${freeBoard.title}</h1> -->
		<%--<th>제 목</th> --%>
		<p>글제목</p>
		<%--<td>--%>
		<input type="text" name="title"  size="100"  style="display: inline-block;" value="${ freeBoard.title }">
		
		<%-- </td>--%>
		
		<%--<div class="line"></div> --%>
		
		<%--<th>내 용</th> --%>
		<p>내 용</p>
		<textarea rows="30" cols="70" name="context">${ freeBoard.context }</textarea>
		<!-- <textarea cols="30" rows="40" name="context">${freeBoard.context}</textarea> -->
		
		<p>첨부파일</p>
		<c:if test="${ !empty freeBoard.freeOriginalFileName }">
		
		${ freeBoard.freeOriginalFileName } &nbsp; 
		
		<input type="checkbox" name="deleteFlag" value="yes" style="width: 16px; height: 16px;"> 파일삭제 <br>
			변경 : <input type="file" name="upfile">
		</c:if>
		<c:if test="${ empty freeBoard.freeOriginalFileName }">
			첨부된 파일 없음 <br>
			추가 : <input type="file" name="upfile">
		</c:if>			
		
		<br><br>
		<button type="submit">수정하기</button> &nbsp; 
		<button type="reset">수정취소</button> &nbsp; 

	</div>
</form>


<!-- <script>
	$("#where").on("click",function(e){
		new daum.Postcode({
		    oncomplete: function(data) {
		        // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
		        
		        $("#where").val(data.address);
		        console.log(data);
		        console.log(data.address);
		    }
		}).open();
	})
	
	document.addEventListener('keydown', function(event) {
	  if (event.keyCode === 13) {
	    event.preventDefault();
	    checkInput();
	  };
	}, true);
	let title = $("input[name='title']");
	let sub = $("input[name='sub']");
	let file = $("input[name='file']");
	let price = $("input[name='price']");
	let start = $("input[name='start']");
	let end = $("input[name='end']");
	let mainlocation = $("input[name='mainlocation']");
	let sublocation = $("input[name='sublocation']");
	function checkInput() {
		if(title.val() == "") {
			alert("제목란이 비어져있습니다.");
			title.focus();
			return;
		}
		if(sub.val() == "") {
			alert("본문이 비어져있습니다.");
			sub.focus();
			return;
		}
		if(file.val() == "") {
			alert("사진이 비어져있습니다.");
			file.focus();
			return;
		}
		if(price.val() == "") {
			alert("가격이 비어져있습니다.");
			price.focus();
			return;
		}if(mainlocation.val() == "") {
			alert("주소가 비어져있습니다.");
			mainlocation.focus();
			return;
		}
		if(sublocation.val() == "") {
			alert("상세주소가 비어져있습니다.");
			sublocation.focus();
			return;
		}
		
		$("input[type='submit']").click();
	}
	
	$("#file").on("change", function(e){
		var f = e.target.files[0];
		console.log(f);
		if(!f.type.match("image*")){ //match도 사용 가능
			$("#img__preview").val("");
			alert('이미지만 첨부할 수 있습니다.');
			return;
		}
		var filename = f.name;
		var reader = new FileReader();
		reader.onload = function(e) {
			$("#img").attr("src", e.target.result);
		}

		reader.readAsDataURL(f);


	});
</script>
	 -->
</body>
</html>