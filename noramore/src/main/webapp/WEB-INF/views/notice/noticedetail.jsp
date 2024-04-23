<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:import url="/WEB-INF/views/common/sideSample.jsp" />
<c:import url="/WEB-INF/views/common/header.jsp" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/style.css">
<script type="text/javascript"
	src="/noramore/resources/js/jquery-3.7.0.min.js"></script>
<title>Insert title here</title>


<c:url var="nd" value="ndelete.do">
	<c:param name="boardId" value="${ notice.boardId }" />
	<c:param name="rfile" value="${ notice.renameFilePath }" />
</c:url>
<c:url var="nup" value="nmoveup.do">
	<c:param name="boardId" value="${ notice.boardId }" />
</c:url>

<!-- button 기능들  -->
<script>
	function moveUpdatePage() {
		// 수정 페이지로 이동하는 코드
		/* var form = document.createElement('form');
		form.method = 'POST';
		form.action = '${nu}';
		document.body.appendChild(form);
		form.submit(); */
		location.href = "${nup}";
	}
	function requestDelete() {
		location.href = "${nd}";
	}
	function goToList() {
		// 목록 페이지로 이동하는 코드
		window.location.href = 'nlist.do';
	}
</script>
</head>
<body>
	<br>


	<%-- <input type="hidden" value="<%= vo.getUserId() %>" name="writer"> --%>
	<section id="write">
		<h1 align="center">${ notice.boardId }번공지글상세보기</h1>
		<div class="line"></div>
		<br>
		<table>
			<tr>
				<th colspan="2">
					<c:if
						test="${!empty sessionScope.loginMember and sessionScope.loginMember.adminYN == 'Y'}">
						<button class="blueBtn"
							style="float: right; margin-right: 30px; margin-left: 10px;"
							onclick="requestDelete(); return false;">삭제</button>
						<button class="blueBtn" style="float: right; margin-left: 10px;"
							onclick="moveUpdatePage(); return false;">수정</button>
					</c:if>
					<button class="whiteBtn" style="float: right;"
						onclick="goToList();">목록</button></th>
			</tr>
		</table>
		<br>


		<p>
			<span style="float: left;">글 제목 : ${ notice.title }</span> <span
				style="float: right;">작성자 : ${ notice.memberID }</span>
		</p>
		<br> <br>
		<p>작성일</p>
		<h3>${ notice.registDt }</h3>
		<br>
		<p>내용</p>
		<textarea rows="30" cols="70" name="sub" readonly>${ notice.substance }</textarea>

		<p>첨부파일</p>
		<td><c:if test="${ !empty notice.originalFilePath }">
				<c:url var="nfd" value="nfdown.do">
					<c:param name="ofile" value="${notice.originalFilePath }" />
					<c:param name="rfile" value="${notice.renameFilePath }" />
				</c:url>
				<a href="${ nfd }">${notice.originalFilePath }</a>
			</c:if> <c:if test="${ empty notice.originalFilePath }">&nbsp;	</c:if></td>


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