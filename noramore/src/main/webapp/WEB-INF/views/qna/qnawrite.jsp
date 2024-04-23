<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/sideSample.jsp"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/style.css">
<script type="text/javascript"
	src="/noramore/resources/js/jquery-3.7.0.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<br>
	<tr>
		<th colspan="2">
			<button class="blueBtn"
				style="float: right; margin-right: 30px; margin-left: 10px"
				onclick="javascript:history.go(-1);">취소</button>
			<button class="whiteBtn" style="float: right;"
				onclick="javascript:history.go(-1);">목록</button>
		</th>
	</tr>
	<br>
	<br>
	<form action="qinsert.do" method="post" enctype="multipart/form-data">
		<%-- <input type="hidden" value="<%= vo.getUserId() %>" name="writer"> --%>
		<section id="write">
			<h1>글쓰기</h1>
			<div class="line"></div>
			<br>
			<p>글 제목</p>
			<input type="text" placeholder="글 제목을 입력하세요." name="title">
			<p>
				작성자 : <input type="text" name="memberID" readonly
					value="${ sessionScope.loginMember.memberID }">
			</p>
			<p>본문</p>
			<textarea rows="30" cols="70" placeholder="본문을 입력하세요."
				name="substance"></textarea>

			<p>첨부파일</p>
			<input type="file" id="file" name="ofile">

			<button class="blueBtn" onclick="checkInput()">작성</button>
			<input type="submit" class="none">
		</section>
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
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</html>