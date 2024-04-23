<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--<%@ include file="/WEB-INF/views/common/sideSample.jsp"%> --%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <c:set var="categoryId" value="1"/>--%>
<c:if test="${!empty requestScope.message}">
	<c:set var="categoryId" value="${requestScope.categoryId}" />
</c:if>

<%--
<% request.setCharacterEncoding("UTF-8");
<% response.setContentType("text/html; charset=UTF-8");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

--%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="resources/css/style.css">
		<script type="text/javascript"
			src="/noramore/resources/js/jquery-3.7.0.min.js"></script>	
			
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script type="text/javascript">

$(function(){
    $('input[name="title"]').on('input', function(){
        var title = $(this).val();
        if (title == '') {
            $('#title').text('글 제목*');
        } else {
            $('#title').text('글 제목');
        }
    });

    $('textarea[name="context"]').on('input', function(){
        var context = $(this).val();
        if (context == '') {
            $('#context').text('본문*');
        } else {
            $('#context').text('본문');
        }
    });


    function validateInput() {
        var title = $('input[name="title"]').val();
        var context = $('textarea[name="context"]').val();
        
        
        if (title.trim() === '') {
            alert('글 제목을 입력하세요.');
            return false;
        }
        if (context.trim() === '') {
            alert('본문을 입력하세요.');
            return false;
        }
        
        
        return true;
    }

    // 폼 제출 시 입력 값 검증
    $('form').submit(function() {
        return validateInput();
    });

});

</script>


<title>Insert title here</title>


</head>
<body>
	
<%-- <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"> --%>
		
    <form action="freeboardinsert.do" method="post" enctype="multipart/form-data" >
	<%-- <input type="hidden" value="<%= vo.getUserId() %>" name="writer"> --%>
	<input  type="hidden" name="categoryId" value="${categoryId}">
	<input  type="hidden" name="page" value="${page}">
	<section id="write">
		<!-- <h1>글쓰기</h1>
		<div class="line"></div> -->
		
		<p>작성자</p>
		<input type="text" name="memberId" readonly value="${ sessionScope.loginMember.memberID }">
		
		
		<p>글 제목</p>
		<input type="text" placeholder="글 제목을 입력하세요." name="title" <%--id = "title"--%>>
		<!--
		<p>카테고리번호</p>
		<input type="text" placeholder="카테고리 번호를 입력하세요." name="categoryId" <%--id = "title"--%>>
		-->
		
		<p>본문</p>
		<textarea rows="50" cols="100" placeholder="본문을 입력하세요." name="context" ></textarea> 
		<%-- 10 5 --%>
		<p>첨부파일</p>
		<input type="file" id="file" name="upfile">
						
		
		<!-- <button type="button"  onclick="checkInput()"  >글쓰기</button>
		<input type="submit" class="none"> -->
		
		
		<button type="submit" id="savebutton">글쓰기</button> &nbsp; 
		<button type="reset">취소</button> &nbsp; 
		
		
	</section>
</form>




<%-- <script>
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
	 --%>
</body>
</html>
