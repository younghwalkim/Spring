<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/WEB-INF/views/common/header.jsp" />
<c:if test="${!empty requestScope.currentPage}">
	<c:set var="page" value="${requestScope.currentPage}" />
</c:if>
<c:if test="${!empty requestScope.categoryId}">
	<c:set var="categoryId" value="${requestScope.categoryId}" />
</c:if>
<c:if test="${!empty requestScope.message}">
	<c:set var="message" value="${message}" />
</c:if>
 <c:out value="${message}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NoraMore</title>
<c:url var="insertappl" value="insertappl.do">
	<c:param name="boardId" value="${RecrBoard.boardId}" />
	<c:param name="memberId" value="${sessionScope.loginMember.memberID}" />
	<c:param name="page" value="${page}" />
	<c:param name="categoryId" value="${categoryId}"></c:param>
</c:url>
<c:url var="updateBoard" value="updateboard.do">
	<c:param name="boardId" value="${RecrBoard.boardId}" />
	<c:param name="page" value="${page}" />
	<c:param name="categoryId" value="${categoryId}"></c:param>
</c:url>
<c:url var="deleteBoard" value="deleteboard.do">
	<c:param name="boardId" value="${RecrBoard.boardId}" />
	<c:param name="page" value="${page}" />
	<c:param name="categoryId" value="${categoryId}"></c:param>
</c:url>
<c:url var="rblist" value="rblist.do">
	<c:param name="page" value="${page}" />
	<c:param name="categoryId" value="${categoryId}" />
</c:url>
<c:url var="rbreport" value="rbreport.do">
	<c:param name="page" value="${page}" />
	<c:param name="categoryId" value="${categoryId}" />
	<c:param name="boardId" value="${RecrBoard.boardId}" />
</c:url>
<c:url var="closeRecr" value="closerecr.do">
	<c:param name="page" value="${page}" />
	<c:param name="categoryId" value="${categoryId}" />
	<c:param name="boardId" value="${RecrBoard.boardId}" />
</c:url>
<link rel="stylesheet" href="resources/css/style.css">
<script type="text/javascript"
	src="/noramore/resources/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
<script type="text/javascript">

async function translateText() {
    const textToTranslate = document.getElementById('context').value;
    const targetLanguage = "en";
    const subscriptionKey = 'e863bfea42804c318d498eaf7322525d'; 
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
	selectrecrcomment();
	var message = "${message}";
	if(message != ""){
		alert(message);
	}     
}; 



function selectrecrcomment() {
    var bId;
    if (${!empty RecrBoard.boardId}) {
        bId = "${RecrBoard.boardId}";
    } else {
        bId = "${boardId}";
    }
    $.ajax({
        url: "selectrecrcomment.do",
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
                if(comment.memberId === "${sessionScope.loginMember.memberID}"){
                commentDiv.append("<button onclick='updatecomment(" + comment.commentId + ", \"" + comment.context + "\")'>수정하기</button>");
                commentDiv.append("<button onclick='deletecomment(" + comment.commentId + ")'>삭제하기</button>");
                }
                var refCommentId1 = parseInt(comment.commentId);
                
                
                if(comment.refCommentId == 0 && ${!empty sessionScope.loginMember}){
                commentDiv.append('<div id="cocomment" style="width: 500px; height: 200px;">' +
                	    '<form id="cocommentForm" action="insertrecrcocomment.do" method="post" style="">' +
                	    '<input type="hidden" name="memberId" value="' + "${sessionScope.loginMember.memberID}" + '">' +
                	    '<input type="hidden" name="boardId" value="' + "${RecrBoard.boardId}" + '">' +
                	    '<input type="hidden" name="refCommentId1" value="' + comment.commentId + '">' +
                	    '<input type="hidden" name="page" value="' + "${page}" + '">' +
                	    '<input type="hidden" name="categoryId" value="' + "${categoryId}" + '">' +
                	    '<textarea class="commentWriteForm" name="context" cols="50" rows="5" required></textarea>' +
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




function moveListPage(){
	location.href = "${rblist}";
}



function deletecomment(commentId) {
    // deleteComment URL 생성
    var boardId = "${RecrBoard.boardId}"; // JSP 변수를 JavaScript 문자열로 가져옵니다.
    var page = "${page}"; // JSP 변수를 JavaScript 문자열로 가져옵니다.
    var categoryId = "${categoryId}";
    var deleteCommentUrl = "deletecomment.do?boardId=" + boardId + "&commentId=" + commentId + "&page=" + page + "&categoryId=" + categoryId;
	
    // 생성된 URL로 리디렉션
    location.href = deleteCommentUrl;
}

function checkRecrCondition(){
	var minCon;
	var maxCon;
	var genderCon;
	if("${RecrBoard.ageMinCondition}" === "0"){
		minCon = "없음";
	}else{
		minCon = "${RecrBoard.ageMinCondition}"
	}
	
	if("${RecrBoard.ageMaxCondition}" === "0"){
		maxCon = "없음";
	}else{
		maxCon = "${RecrBoard.ageMaxCondition}"
	}
	
	if(${empty RecrBoard.genderCondition}){
		genderCon = "없음";
	}else{
		if(${RecrBoard.genderCondition eq "M"}){
			genderCon = "남자만";
		}else if(${RecrBoard.genderCondition eq "F"}){
			genderCon = "여자만";
		}
	}
	
	var con = "신청 최소나이 : " + minCon + "/  신청 최대 나이 : " + maxCon + "\n 성별 제한 : " + genderCon;
	
	alert(con);
}

function calculateAge(birthDate) {
    // 현재 날짜
    var currentDate = new Date();
    
    // 생일 날짜로부터 현재 날짜까지의 시간차 계산
    var timeDiff = currentDate.getTime() - birthDate.getTime();
    
    // 생일로부터 현재까지의 시간차를 밀리초에서 연도로 변환
    var ageDate = new Date(timeDiff); 
    
    // 나이 계산
    var age = Math.abs(ageDate.getUTCFullYear() - 1970);
    
    return age;
}

function insertappl(){
	if(${empty sessionScope.loginMember}){
		alert("로그인을 해야합니다.");
		return
	}
	location.href = "${insertappl}";
}//


function updateBoard(){
	location.href = "${updateBoard}";
}

function deleteBoard(){
	location.href = "${deleteBoard}";
}

function rbreport(){
	if(${empty sessionScope.loginMember}){
		alert("로그인을 해야합니다.");
		return
	}
	location.href = "${rbreport}"
}

function applyAppl(memberId, boardId){
	location.href = "applyAppl.do?memberId=" + memberId + "&boardId=" + boardId + "&page=" + ${page} + "&categoryId=" + ${categoryId};
}

function dropAppl(memberId, boardId){
	location.href = "dropAppl.do?memberId=" + memberId + "&boardId=" + boardId + "&page=" + ${page} + "&categoryId=" + ${categoryId};
}

function cancelAppl(memberId, boardId){
	console.log(memberId);
	console.log(boardId);
	location.href = "cancelAppl.do?memberId=" + memberId + "&boardId=" + boardId + "&page=" + ${page} + "&categoryId=" + ${categoryId};
}

function deletecomment(commentId1) {
    var commentId = commentId1;
	console.log(commentId);
    // 삭제할 댓글의 commentId 값을 서버로 전송하는 AJAX 요청
    $.ajax({
        url: 'deletecomment.do',
        type: 'POST',
        data: { commentId: commentId, boardId: "${RecrBoard.boardId}", page: "${page}", categoryId: "${categoryId}" },
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
        url: 'updatecomment.do',
        type: 'POST',
        data: { commentId: commentId, boardId: "${RecrBoard.boardId}", page: "${page}", context: context, categoryId: "${categoryId}" },
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

function toggleApplList(){
	var applList = document.getElementById("applList");
	var appledList = document.getElementById("appledList");
	if(appledList.style.display == "block"){
		appledList.style.display = "none";
	}
	if(applList.style.display == "none"){
		applList.style.display = "block";
	}else{
		applList.style.display = "none";
	}
         
}

function toggleAppledList(){
	var applList = document.getElementById("applList");
	var appledList = document.getElementById("appledList");
	if(applList.style.display == "block"){
		applList.style.display = "none";
	}
	if(appledList.style.display == "none"){
		appledList.style.display = "block";
	}else{
		appledList.style.display = "none";
	}
}

function closeRecr() {
    const confirmation = confirm("모집을 종료하시겠습니까?");
    if (confirmation) {
        location.href="${closeRecr}";
    } else {
        return;
    }
}

</script>
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

th{
	text-align: center;
}
</style>
</head>
<body>
	<div class="container" style="margin-left: 30%; height: 1600;">
		<div class="boardRecr-div">
			<div id="write" style="margin-bottom: 20px;">
				<h1 style="text-align: left;">${RecrBoard.title}</h1>
				<div style="text-align: left; margin-bottom: 10px;">
					<button class="whiteBtn" onclick="moveListPage(); return false;">목록으로</button>
					
					<button onclick="translateText()">translate context</button>
					<c:if
						test="${sessionScope.loginMember.memberID ne RecrBoard.memberId and RecrBoard.recrStatus eq '모집중'}">
						<button class="whiteBtn"
							style="float: right; background-color: pink; color: black;"
							onclick="rbreport(); return false;">신고하기</button>
						<button class="whiteBtn" style="float: right; margin-right: 10px;"
							onclick="insertappl(); return false;">모집신청</button>
						<button class="whiteBtn" style="float: right; margin-right: 10px;"
							onclick="checkRecrCondition(); return false;">모집조건</button>
					</c:if>

					<c:if
						test="${sessionScope.loginMember.memberID eq RecrBoard.memberId}">
						
						<c:if test="${RecrBoard.recrStatus eq '모집중'}">
							<button class="whiteBtn" style="float: right;" onclick="toggleAppledList()">모집목록 보기</button>
							<button class="whiteBtn" style="float: right;" onclick="toggleApplList()">신청자목록 보기</button>
							<button class="whiteBtn" style="float: right;" onclick="closeRecr()">모집종료</button>
						</c:if>
						<div id="scrollableTable" style="width: 800px;overflow-y: auto;">
						
						<table id="applList" style='width: 600px; display: none;'>
							<tr>
								<th>번호</th>
								<th width="150px">신청자ID</th>
								<th width="120px">수락</th>
								<th width="120px">거절</th>
							</tr>
							<c:if test="${empty applList}">
								<tr>
									<th>신청자가 없습니다</th>
								</tr>
							</c:if>
							<c:if test="${!empty applList}">								
								<c:forEach var="appl" items="${applList}">
								<c:if test="${appl.recrState eq 1}">
									<tr>
										<th>${loop.index + 1}</th>
										<th>${appl.memberId}</th>
										<th><button
												onclick="applyAppl('${appl.memberId}', ${appl.boardId})">수락</button></th>
										<th><button
												onclick="dropAppl('${appl.memberId}', ${appl.boardId})">거절</button></th>
									</tr>
								</c:if>
								</c:forEach>								
							</c:if>
						</table>
						
						<table id="appledList" style='width: 600px; display: none;'>
							<tr>
								<th>번호</th>
								<th width="150px">모집인원ID</th>
								<th width="120px">모집 날짜</th>	
								<th width="120px">모집취소</th>
							</tr>
							<c:if test="${empty applList}">
								<tr>
									<th>모집인원이 없습니다</th>
								</tr>
							</c:if>
							<c:if test="${!empty applList}">								
								<c:forEach var="appl" items="${applList}">
								<c:if test="${appl.recrState eq 2}">
									<tr>
										<th style="text-align: center;">${loop.index + 1}</th>
										<th>${appl.memberId}</th>
										<th>${appl.lastUpdDate}</th>
										<th><button onclick="cancelAppl('${appl.memberId}', ${appl.boardId})">모집취소</button></th>
									</tr>
								</c:if>
								</c:forEach>								
							</c:if>
						</table>
						
						</div>
					</c:if>
					<br>
				
				</div>
					<section>
						<p1 style="font-weight: bold;">작성자: ${RecrBoard.memberId}</p1>
						<p2 style="float: right; font-weight: bold;">   모집상태 : [${RecrBoard.recrStatus}] (${RecrBoard.nowRecr}명/ ${RecrBoard.maxRecr}명)</p2>
					</section>
				<textarea id="context" cols="30" rows="40" readonly>${RecrBoard.context}</textarea>
				<div>
					<p>첨부파일:</p>
					<c:if test="${ !empty RecrBoard.recrOriginalFilename}">
						<c:url var="rbdown" value="rbdown.do">
							<c:param name="ofile" value="${RecrBoard.recrOriginalFilename}" />
							<c:param name="rfile" value="${RecrBoard.recrRenameFilename}" />
						</c:url>
						<a href="${rbdown}">${RecrBoard.recrOriginalFilename}</a>
					</c:if>
					<c:if test="${ empty RecrBoard.recrOriginalFilename}">
						<p>첨부파일 없음</p>
					</c:if>
					<p>시작 날짜 : ${RecrBoard.recrStartDate}</p>
					<p>마감 날짜 : ${RecrBoard.recrEndDate}</p>
					<p>장소 : ${RecrBoard.recrLocation}</p>

					<c:if
						test="${sessionScope.loginMember.memberID eq RecrBoard.memberId}">
						<button class="whiteBtn" onclick="updateBoard() ">수정하기</button>
						<button class="whiteBtn" onclick="deleteBoard() ">삭제하기</button>
					</c:if>

					<div class="comment-div">
						<button onclick="toggleCommentForm(); return false;">댓글(${RecrBoard.commentCount})개</button>
						<!-- 댓글 작성 폼 -->

						<div id="writecommentForm" style="display: none;">
							<c:if test="${!empty sessionScope.loginMember}">
								<form action="insertrecrcomment.do" method="post">
									<input type="hidden" name="memberId"
										value="${sessionScope.loginMember.memberID}"> <input
										type="hidden" name="boardId" value="${RecrBoard.boardId}">
									<input type="hidden" name="page" value="${page}">
									<input type="hidden" name="categoryId" value="${categoryId}">
									<textarea class="commentWriteForm" name="context" cols="50" rows="5"
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

				</div>

			</div>


		</div>
	</div>


	6
</body>
</html>
