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
<style>
.container {
	display: flex;
	flex-direction: column;
	align-items: center;
}

.boardRecr-div {
	width: 1000px;
	padding: 20px;
	margin: 10px;
	margin-left: 10px;
	border: 1px;
}

.comment-div {
	width: 100%; /* 원하는 너비로 조정하세요 */
	margin-top: 20px; /* 위쪽 여백 */
	margin-bottom: 20px; /* 아래쪽 여백 */
	/* 그 외 필요한 스타일링 추가 */
}

.comment-list {
	width: 700px;
	margin: 10px;
	margin-top: 20px;
	/*margin-left: '.boardRecr-div'와 동일한 값;  '.boardRecr-div'의 왼쪽 여백과 일치하도록 값 조정 */
}

#commentForm {
	text-align: left;
	margin-bottom: 15px;
}

#cocommentForm {
	text-align: left;
	margin-left: 100px;
}

button {
	padding: 8px 16px; /* 버튼 내부 여백 설정 */
	font-size: 12px; /* 글자 크기 설정 */
	border: 2px solid #45d3b6; /* 테두리 설정 */
	background-color: white; /* 기본 배경색 설정 */
	color: black; /* 기본 글자 색 설정 */
	border-radius: 4px; /* 버튼 모서리 둥글게 만들기 */
	cursor: pointer; /* 마우스 커서를 포인터로 설정하여 클릭 가능한 상태로 만듦 */
	transition: background-color 0.3s, color 0.3s;
	/* 배경색과 글자색이 바뀔 때 부드럽게 전환 */
	margin-right: 10px;
	text-align: right;
}

button:hover {
	background-color: #45d3b6; /* 마우스 오버시 배경색 변경 */
	color: white; /* 마우스 오버시 글자색 변경 */
}

textarea.commentWriteForm {
	width: 100%;
	height: 150px;
	padding: 10px;
	font-size: 16px;
	border: 2px solid #ccc;
	border-radius: 5px;
	box-sizing: border-box;
	resize: none; /* 크기 조절 비활성화 */
	font-family: Arial, sans-serif; /* 폰트 설정 */
}

textarea.commentForm {
	width: 100%;
	height: 150px;
	padding: 10px;
	font-size: 16px;
	border: 2px solid #666;
	border-radius: 5px;
	box-sizing: border-box;
	resize: none; /* 크기 조절 비활성화 */
	font-family: Arial, sans-serif; /* 폰트 설정 */
}


/* 마우스 호버 효과 */
textarea.commentForm:hover {
	border-color: #666;
}
</style>
<script type="text/javascript"
	src="/noramore/resources/js/jquery-3.7.0.min.js"></script>
<title>Insert title here</title>


<c:url var="qd" value="qdelete.do">
	<c:param name="boardId" value="${ qna.boardId }" />
	<c:param name="rfile" value="${ qna.renameFilePath }" />
</c:url>
<c:url var="qup" value="qmoveup.do">
	<c:param name="boardId" value="${ qna.boardId }" />
</c:url>

<!-- button 기능들  -->
<script>

window.onload = function() {
    selectqnacomment();
};

	function moveUpdatePage() {
		// 수정 페이지로 이동하는 코드
		/* var form = document.createElement('form');
		form.method = 'POST';
		form.action = '${nu}';
		document.body.appendChild(form);
		form.submit(); */
		location.href = "${qup}";
	}
	function requestDelete() {
		location.href = "${qd}";
	}
	function goToList() {
		// 목록 페이지로 이동하는 코드
		window.location.href = 'qlist.do';
	}
	
	// 댓글 작성 function
	function toggleCommentForm() {
		// writecommentForm 요소를 선택
		var writecommentForm = document.getElementById("writecommentForm");

		// writecommentForm의 display 속성 값을 확인하여 표시되어 있는지 여부를 확인
		if (writecommentForm.style.display === "none") {
			// 표시되어 있지 않다면 보이도록 설정
			writecommentForm.style.display = "block";
		} else {
			// 표시되어 있다면 숨김
			writecommentForm.style.display = "none";
		}

		// comment-list 요소를 선택
		var commentList = document.querySelector(".comment-list");

		// comment-list의 display 속성 값을 확인하여 표시되어 있는지 여부를 확인
		if (commentList.style.display === "none") {
			// 표시되어 있지 않다면 보이도록 설정
			commentList.style.display = "block";
		} else {
			// 표시되어 있다면 숨김
			commentList.style.display = "none";
		}
	}
	
	function selectqnacomment() {
	    var bId;
	    if (${!empty qna.boardId}) {
	        bId = "${qna.boardId}";
	    } else {
	        bId = "${no}";
	    }
	    $.ajax({
	        url: "selectqnacomment.do",
	        type: "POST",
	        dataType: "json",
	        data: { boardId: bId },
	        success: function(data) {
	        	
	            for (var i = 0; i < data.length; i++) {
	                var comment = data[i];
	                
	                var memberIdInput = $('<input type="hidden" name="memberId" value="' + comment.memberId + '">');
	                var commentIdInput = $('<input type="hidden" name="commentId" value="' + comment.commentId + '">');
	                var contextTextarea = $('<textarea class="commentForm" rows="5" cols="20" readonly>' + comment.substance + '</textarea><br>');
	                var lastUpdateDateParagraph = $('<p style="font-size: 10pt;">' + "작성자ID : " + comment.memberId + "&nbsp;&nbsp;&nbsp; 작성/수정 날짜: " + comment.lastUpdateDate + '</p>');

	                if(comment.refCommentId == 0){
	                	var commentDiv = $("<div id='commentForm' style=''>");
	                }else{
	                	var commentDiv = $("<div id='cocommentForm' style='left-margin: 100px;'>");
	                }
	                commentDiv.append(lastUpdateDateParagraph);
	                commentDiv.append(memberIdInput);
	                commentDiv.append(commentIdInput);
	                commentDiv.append(contextTextarea);  
	                if("${qna.memberID}" === "${sessionScope.loginMember.memberID}"){
	                commentDiv.append("<button onclick='updatecomment(" + comment.commentId + ", \"" + comment.substance + "\")'>수정하기</button>");
	                commentDiv.append("<button onclick='deletecomment(" + comment.commentId + ")'>삭제하기</button>");
	                }
	                var refCommentId1 = parseInt(comment.commentId);
	                
	                
	                if(comment.refCommentId == 0 && ${!empty sessionScope.loginMember}){
	                commentDiv.append('<div id="cocomment" style="width: 500px; height: 200px;">' + 
	                	    '<form id="cocommentForm" action="insertqnacocomment.do" method="post" style="">' +
	                	    '<input type="hidden" name="memberId" value="' + "${sessionScope.loginMember.memberID}" + '">' +
	                	    '<input type="hidden" name="boardId" value="' + "${qna.boardId}" + '">' +
	                	    '<input type="hidden" name="refCommentId1" value="' + comment.commentId + '">' + 
	                	    '<input type="hidden" name="page" value="' + "${page}" + '">' +
	                	    '<textarea class="commentForm" name="substance" cols="50" rows="5" required></textarea>' +
	                	    '<br>' +
	                	    '<button type="submit" >대댓글 등록</button>' +
	                	    '</form>' +
	                	    '<div id="commentList"></div>' +
	                	    '</div>');
	                }
	                
	                
	                $('.comment-list').append(commentDiv);
	            }
	            
	            
	        },
	        error: function(xhr, status, error) {
	            console.error("Error occurred:", error);
	        }
	    });
	}

	function deletecomment(commentId1) {
	    var commentId = commentId1;
		console.log(commentId);
	    // 삭제할 댓글의 commentId 값을 서버로 전송하는 AJAX 요청
	    $.ajax({
	        url: 'deleteqnacomment.do',
	        type: 'POST',
	        data: { commentId: commentId, boardId: "${qna.boardId}", page: "${page}"},
	        success: function(response) {
	            alert('댓글이 성공적으로 삭제되었습니다.');
	        },
	        error: function(xhr, status, error) {
	            // 오류 처리
	            console.error('댓글 삭제 중 오류가 발생했습니다:', error);
	        }
	    });

	    location.reload(); 
	    location.reload(); 
	}
</script>
</head>
<body>
	<br>
	

	<%-- <input type="hidden" value="<%= vo.getUserId() %>" name="writer"> --%>
	<section id="write">
		<h1 align="center">${ qna.boardId }번공지글상세보기</h1>
		<div class="line"></div>
		<br>
		<table>
			<tr>
				<th colspan="2"><c:if
						test="${!empty sessionScope.loginMember and 
						sessionScope.loginMember.memberID == qna.memberID or
						sessionScope.loginMember.adminYN == 'Y'}">
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
			<span style="float: left;">글 제목 : ${ qna.title }</span> <span
				style="float: right;">작성자 : ${ qna.memberID }</span>
		</p>
		<br> <br>
		<p>작성일</p>
		<h3>${ qna.registDt }</h3>
		<br>
		<p>내용</p>
		<textarea rows="30" cols="70" name="sub" readonly>${ qna.substance }</textarea>

		<p>첨부파일</p>
		<td><c:if test="${ !empty qna.originalFilePath }">
				<c:url var="nfd" value="nfdown.do">
					<c:param name="ofile" value="${qna.originalFilePath }" />
					<c:param name="rfile" value="${qna.renameFilePath }" />
				</c:url>
				<a href="${ qfd }">${qna.originalFilePath }</a>
			</c:if> <c:if test="${ empty qna.originalFilePath }">&nbsp;	</c:if></td>
		<div class="comment-div">
			<button onclick="toggleCommentForm(); return false;">댓글(${qna.commentCount})개</button>
			<!-- 댓글 작성 폼 -->

			<div id="writecommentForm" style="display: none;">
				<c:if test="${!empty sessionScope.loginMember}">
					<form action="insertqnacomment.do" method="post">
						<input type="hidden" name="memberId"
							value="${sessionScope.loginMember.memberID}"> <input
							type="hidden" name="boardId" value="${qna.boardId}">
						<input type="hidden" name="page" value="${page}">
						<textarea class="commentForm" name="substance" cols="50" rows="5"
							required></textarea>
						<br>
						<button type="submit">댓글 등록</button>
					</form>
				</c:if>
				<div id="commentList"></div>
				<div class="comment-list"
					style='display: none; text-align: left; padding: 0;'>
					<br>
				</div>
			</div>


		</div>

	</section>



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
<%-- 
<%@ include file="/WEB-INF/views/common/footer.jsp"%> --%>
</html>