<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

 <%--<%@ include file="/WEB-INF/views/common/sideSample.jsp"%> --%> 

 <%@ include file="/WEB-INF/views/common/header.jsp"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${!empty requestScope.currentPage}">
	<c:set var="page" value="${requestScope.currentPage}" />
</c:if>

<c:if test="${!empty requestScope.message}">
	<c:set var="categoryId" value="${requestScope.categoryId}" />
</c:if>

<c:if test="${!empty requestScope.message}">
<c:set var="message" value="${requestScope.message}" />
</c:if>


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

<c:url var="fbdel" value="freeboarddelete.do">
	<c:param name="boardId" value="${ FreeBoard.boardId }" />
	<c:param name="freeRenameFileName"
		value="${ FreeBoard.freeRenameFileName }" />
	
</c:url>

<c:url var="fbup" value="fbupview.do">
	<c:param name="boardId" value="${ FreeBoard.boardId }" />
	<c:param name="page" value="${ currentPage }" />
	<c:param name="categoryId" value="${categoryId}"></c:param>
</c:url>

<c:url var="freeboardlist" value="freeboardlist.do">
	<c:param name="page" value="${page}" />
	
	
</c:url>

<c:url var="fbreport" value="incrementReportCount.do">
	<c:param name="page" value="${page}" />
	<c:param name="categoryId" value="${categoryId}" />
	<c:param name="boardId" value="${FreeBoard.boardId}" />
</c:url>

<script type="text/javascript"
	src="/noramore/resources/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
<script type="text/javascript">

async function translateText() {
    const textToTranslate = document.getElementById('context').value;
    const targetLanguage = "en";
    const subscriptionKey = 'e863bfea42804c318d498eaf7322525d'; // Azure에서 발급받은 구독 키를 입력하세요.
    const endpoint = 'https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&to=' + targetLanguage;

    try {
        const response = await fetch(endpoint, {
            method: 'POST',
            body: JSON.stringify([{Text: textToTranslate}]),
            headers: {
                'Ocp-Apim-Subscription-Key': subscriptionKey,
                'Content-Type': 'application/json',
                'Ocp-Apim-Subscription-Region': 'koreacentral', // 리소스 위치를 입력하세요.
            }
        });

        const data = await response.json();
        const translatedText = data[0].translations[0].text;
        document.getElementById('context').innerText = translatedText;
    } catch (error) {
        console.error('Translation error:', error);
    }
    
    alert("translate complete!");
}

	function requestDelete() {
		//게시글(원글, 댓글, 대댓글) 삭제 요청 함수
		location.href = "${ fbdel }";
	}

	function moveUpdatePage() {
		//게시글 (원글, 댓글, 대댓글) 수정 페이지로 이동 처리 함수
		location.href = "${ fbup }";
	}
	
	function report(boardId) {
		// Ajax를 사용하여 서버로 해당 값을 전송하여 DB에 저장
		$.ajax({
	        type: "POST",
	        url: "incrementReportCount.do",
	        data: { boardId: boardId },
	        /*
	        success: function(response) {
	            // 성공적으로 신고가 처리되었을 때 수행할 코드
	            alert("게시물이 신고되었습니다.");
	            // 페이지 새로고침 또는 신고 버튼 비활성화 등의 추가적인 처리 가능
	        },
	        error: function(xhr, status, error) {
	            // 서버와의 통신 중 오류가 발생했을 때 수행할 코드
	            alert("오류가 발생했습니다.");
	        }
	        */
	        
	        success: function(response) {
	            if (response === "Success") {
	                alert("게시물이 신고되었습니다.");
	                // 페이지 새로고침 또는 신고 버튼 비활성화 등의 추가적인 처리 가능
	            } else if (response === "Already Viewed") {
	                alert("이미 신고하기를 누르셨습니다.");
	            } else {
	                alert("오류가 발생했습니다.");
	            }
	        },
	        error: function(xhr, status, error) {
	            // 서버와의 통신 중 오류가 발생했을 때 수행할 코드
	            alert("오류가 발생했습니다.");
	        }
	    });
    }
	
	function like(boardId) {
		// Ajax를 사용하여 서버로 해당 값을 전송하여 DB에 저장
		$.ajax({
	        type: "POST",
	        url: "incrementLikeCount.do",
	        data: { boardId: boardId },
	        /*
	        success: function(response) {
	            // 성공적으로 신고가 처리되었을 때 수행할 코드
	            alert("좋아요를 누르셨습니다.");
	            // 페이지 새로고침 또는 신고 버튼 비활성화 등의 추가적인 처리 가능
	        },
	        error: function(xhr, status, error) {
	            // 서버와의 통신 중 오류가 발생했을 때 수행할 코드
	            alert("오류가 발생했습니다.");
	        }
	        */
	        
	        success: function(response) {
	            if (response === "Success") {
	                alert("좋아요를 누르셨습니다.");
	                // 페이지 새로고침 또는 신고 버튼 비활성화 등의 추가적인 처리 가능
	            } else if (response === "Already Viewed") {
	                alert("이미 좋아요를 누르셨습니다.");
	            } else {
	                alert("오류가 발생했습니다.");
	            }
	        },
	        error: function(xhr, status, error) {
	            // 서버와의 통신 중 오류가 발생했을 때 수행할 코드
	            alert("오류가 발생했습니다.");
	        }
	        
	    });
    }
	
	
	
	// 댓글 추가한 부분 *********************************************************
	
	function selectfreecomment() {
	    var bId;
	    if (${!empty FreeBoard.boardId}) {
	        bId = "${FreeBoard.boardId}";
	    } else {
	        bId = "${boardId}";
	    }
	    $.ajax({
	        url: "selectfreecomment.do",
	        type: "POST",
	        dataType: "json",
	        data: { BoardId: bId },
	        success: function(data) {
	        	
	            for (var i = 0; i < data.length; i++) {
	                var comment = data[i];
	                
	                var memberIdInput = $('<input type="hidden" name="memberId" value="' + comment.memberId + '">');
	                var commentIdInput = $('<input type="hidden" name="commentId" value="' + comment.commentId + '">');
	                var contextTextarea = $('<textarea class="commentForm" rows="5" cols="20" readonly>' + comment.context + '</textarea><br>');
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
	                if("${FreeBoard.memberId}" === "${sessionScope.loginMember.memberID}"){
	                commentDiv.append("<button onclick='updatecomment(" + comment.commentId + ", \"" + comment.context + "\")'>수정하기</button>");
	                commentDiv.append("<button onclick='deletecomment(" + comment.commentId + ")'>삭제하기</button>");
	                }
	                var refCommentId1 = parseInt(comment.commentId);
	                
	                
	                if(comment.refCommentId == 0){
	                commentDiv.append('<div id="cocomment" style="width: 500px; height: 200px;">' +
	                	    '<form id="cocommentForm" action="insertfreecocomment.do" method="post" style="">' +
	                	    '<input type="hidden" name="memberId" value="' + "${sessionScope.loginMember.memberID}" + '">' +
	                	    '<input type="hidden" name="boardId" value="' + "${FreeBoard.boardId}" + '">' +
	                	    '<input type="hidden" name="refCommentId1" value="' + comment.commentId + '">' +
	                	    '<input type="hidden" name="page" value="' + "${page}" + '">' +
	                	    '<textarea class="commentForm" name="context" cols="50" rows="5" required></textarea>' +
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

	
	function deletecomment(commentId) {
	    // deleteComment URL 생성
	    var boardId = "${FreeBoard.boardId}"; // JSP 변수를 JavaScript 문자열로 가져옵니다.
	    var page = "${page}"; // JSP 변수를 JavaScript 문자열로 가져옵니다.
	    var deleteCommentUrl = "deletefreecomment.do?boardId=" + boardId + "&commentId=" + commentId + "&page=" + page;

	    // 생성된 URL로 리디렉션
	    location.href = deleteCommentUrl;
	}
	
	

	function deletecomment(commentId1) {
	    var commentId = commentId1;
		console.log(commentId);
	    // 삭제할 댓글의 commentId 값을 서버로 전송하는 AJAX 요청
	    $.ajax({
	        url: 'deletefreecomment.do',
	        type: 'POST',
	        data: { commentId: commentId, boardId: "${FreeBoard.boardId}", page: "${page}" },
	        success: function(response) {
	            alert('댓글이 성공적으로 삭제되었습니다.');
	        },
	        error: function(xhr, status, error) {
	            // 오류 처리
	            console.error('댓글 삭제 중 오류가 발생했습니다:', error);
	        }
	    });

	    location.reload(); 
	}

	function updatecomment(commentId1, context1){
		var commentId = commentId1;
		var ncontext = context1
		var context = prompt("수정할 내용", ncontext);
		console.log(context);
		$.ajax({
	        url: 'updatefreecomment.do',
	        type: 'POST',
	        data: { commentId: commentId, boardId: "${FreeBoard.boardId}", page: "${page}", context: context },
	        success: function(response) {
	            alert('댓글이 성공적으로 수정되었습니다.');
	        },
	        error: function(xhr, status, error) {
	            // 오류 처리
	            console.error('댓글 수정 중 오류가 발생했습니다:', error);
	        }
	    });
		
		location.reload(); 
	}
	
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

	// 페이지 로딩 시 alert 창 띄우기
	window.onload = function() {
		selectfreecomment()
		if(${!empty message}){
	    alert("${message}");
		}
	};
	
	function Alert(message) {
	    alert(message);
	}
	
	function moveListPage(){
		location.href = "${freeboardlist}";
	}

	
</script>
<title>NoraMore</title>


<style>
/*
div {
	margin-bottom: 20px;
}
*/

.container {
	display: flex;
	flex-direction: column;
	align-items: center;
}

.boardFree-div {
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
</head>
<body>

	<div class="container">
		<div class="boardFree-div">
	
<%--<form action="freeboardlist.do" method="post"
		enctype="multipart/form-data">  --%>
		<%-- <input type="hidden" value="<%= vo.getUserId() %>" name="writer"> --%>
	
		<div id="write" style="margin-bottom: 20px;">
			<h1 style="text-align: left;">${FreeBoard.title}</h1>
			<div style="text-align: left; margin-bottom: 10px;">
			<br>
			<%-- <div class="line"></div>--%>
			<div>
				<button class="whiteBtn" onclick="moveListPage(); return false;">목록으로</button>
				<button onclick="translateText()">translate context</button>
				<button style="float: right; background-color: pink; color: black;"
					class="whiteBtn" onclick="report(${FreeBoard.boardId}); return false;"><span style="font-weight: normal;">신고하기</span></button>
				<button id="likeButton_${FreeBoard.boardId}" style="float: right; background-color: pink; color: black;"
					class="whiteBtn" onclick="like(${FreeBoard.boardId}); return false;"><span style="font-weight: normal;">좋아요</span></button>
			</div>
			<br>
			<textarea id="context" cols="100" rows="50" readonly>${FreeBoard.context}</textarea>
			</div><%-- 30 / 40 --%>
			<c:if test="${ !empty FreeBoard.freeOriginalFileName}">
				<p>첨부파일</p>
				<c:url var="fbdown" value="fbdown.do">
					<c:param name="ofile" value="${FreeBoard.freeOriginalFileName}" />
					<c:param name="rfile" value="${FreeBoard.freeRenameFileName}" />
				</c:url>
				<a href="${fbdown}"> ${FreeBoard.freeOriginalFileName}</a>
			</c:if>
			<c:if test="${ empty FreeBoard.freeOriginalFileName}">
				<p>첨부파일 없음</p>
			</c:if>
			<!-- 
			<button onclick="moveUpdatePage(); return false;">수정페이지로 이동</button>
			&nbsp;
			<button onclick="requestDelete(); return false;">글삭제</button>
			&nbsp;
			 -->
		
			
			<%-- 로그인한 경우 : 본인 글 상세보기 일때는 수정페이지로 이동과 삭제 버튼 표시함 --%>
		
			<c:if test="${ !empty loginMember }">
				<c:if test="${ loginMember.memberID eq FreeBoard.memberId }">
				<div>
					<button class="whiteBtn" onclick="moveUpdatePage(); return false;">수정하기</button> &nbsp;
					<button class="whiteBtn" onclick="requestDelete(); return false;">삭제하기</button> &nbsp;
					</div>
				</c:if>
				
				<%-- 로그인한 경우 : 관리자인 경우 글삭제 버튼과 댓글달기 버튼 표시함 --%>
			 
				<c:if test="${ loginMember.adminYN eq 'Y' and loginMember.memberID ne FreeBoard.memberId  }">
				<div>
					<button class="whiteBtn" onclick="requestDelete(); return false;">삭제하기</button> &nbsp;
				</div>	
					<%-- <button onclick="requestReply(); return false;">댓글달기</button> &nbsp;--%>
					
				</c:if>
				
				<%-- 로그인한 경우 : 본인 글이 아니고, 레벨이 3보다 작은 경우에만 댓글달기 버튼 표시함 --%>
				
				<c:if test="${ loginMember.adminYN eq 'N' and loginMember.memberID ne FreeBoard.memberId }">
					
						<%--<button onclick="requestReply(); return false;">댓글달기</button> &nbsp;--%>
					</c:if>
				
			</c:if>
			
			
		
		
		
	
		
	<%-- </form> --%>


	<%-- 가져온 부분 ****************************** --%>
			
		<div class="comment-div">
		<button onclick="toggleCommentForm(); return false;">댓글(${FreeBoard.commentCount})개</button>
		<!-- 댓글 작성 폼 -->
		<div id="writecommentForm" style="display: none;">
			<form action="insertfreecomment.do" method="post">
				<input type="hidden" name="memberId"
					value="${sessionScope.loginMember.memberID}"> <input
					type="hidden" name="boardId" value="${FreeBoard.boardId}">
				<input type="hidden" name="page" value="${page}">
				<textarea class="commentWriteForm" name="context" cols="50" rows="5"
					required></textarea>
				<br>
				<button type="submit">댓글 등록</button>
			</form>
			<div id="commentList"></div>
			<div class="comment-list"
				style='display: none; text-align: left; padding: 0;'>
				<br>
			</div>
		</div>
		
	</div>
	</div>
</div>
</div>
			 
	
</body>
</html>