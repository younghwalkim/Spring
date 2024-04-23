<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/style.css">
<script type="text/javascript"src="/noramore/resources/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$('input[name=categoryName]').on('change', function(){
		var categoryName = $(this).val();
        if (categoryName == '') {
            $('#name').text('카테고리 이름*');
        } else {
            $('#name').text('카테고리 이름');
        }
	});
	
	$('input[name=PNGimageFile]').on('change', function(){
		var imageFile = $(this).val();
        if (imageFile -= null) {
            $('#imgFile').text('카테고리 베너 이미지 파일*');
        } else {
            $('#imgFile').text('카테고리 베너 이미지 파일');
        }
	});
	
	
});


</script>
<title>카테고리 생성</title>
</head>
<body>
<button class="whiteBtn" ><a href="${ pageContext.servletContext.contextPath }/adminPage.do">돌아가기</a></button>
	
		<form action="insertCategory.do" method="post" enctype="multipart/form-data">
			<section id="write">
			<p id="name">카테고리 이름*</p>
			<input  type="text" placeholder="카테고리 이름을 입력하세요." name="categoryName" style="display: inline-block;">
		
			
			<p id="imgFile">카테고리 베너 이미지 파일*</p>
			<input type="file" id="file" name="PNGimageFile">
			<button type="submit">작성 완료</button>
			</section>
			
			
		</form>
	
	
</body>
</html>