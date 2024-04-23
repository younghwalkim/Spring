<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko" class="no-js">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>NoraMore : 나랑 함께 놀 사람~ 놀아!모아!</title>
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="author" content="Codrops" />
<link rel="stylesheet" type="text/css" href="resources/css/default.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/component.css" />
<script src="resources/js/modernizr.custom.js"></script>
<script type="text/javascript">
function movePage() {
    var form = document.createElement('form');
    form.method = 'POST';
    form.action = 'moveLoginPage.do';
    document.body.appendChild(form);
    form.submit();
}

function enroll() {
    var form = document.createElement('form');
    form.method = 'POST';
    form.action = 'enrollPage.do';
    document.body.appendChild(form);
    form.submit();
}

function logout() {
    var form = document.createElement('form');
    form.method = 'POST';
    form.action = 'logout.do';
    document.body.appendChild(form);
    form.submit();
}

function my() {
    var form = document.createElement('form');
    form.method = 'POST';
    form.action = 'selectRecrBoadMemberId.do';
    document.body.appendChild(form);
    form.submit();
}
    
/* $(function(){
    // 최근 등록된 공지글 5개를 받아와서 출력 처리
    $.ajax({
        url: "ntop5.do",
        type: "post",
        dataType: "json",
        success: function(data){
            console.log("success : " + data);
            
            // JSON 데이터를 처리하여 HTML 코드로 변환
            var html = "";
            $.each(data.nlist, function(index, notice){
                html += "<li><a href='ndetail.do?no=" + notice.no + "'>" + decodeURIComponent(notice.title).replace(/\+/gi, " ") + "</a></li>";
            });
            
            // ul 요소에 변환된 HTML 코드 추가
            $('#noticeList').html(html);
        },
        error: function(jqXHR, textStatus, errorThrown){
            console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
        }
    });
}); */
</script>
</head>

<body>


	<div class="container">

		<header class="headline">

			<h1 class="logo">
				<img src="resources/images/noramore_main.png" width=460 height=220>
			</h1>
			<div class="line"></div>

		</header>
		<c:if test="${ empty sessionScope.loginMember }">
			<div class="loginMenu">
				<form method="POST" action="moveLoginPage.do">
					<button type="submit" class="button">로그인</button>
				</form>
				<form method="POST" action="enrollPage.do">
					<button type="submit" class="button">회원가입</button>
				</form>
			</div>
		</c:if>
		<c:if
			test="${ !empty sessionScope.loginMember && loginMember.adminYN eq 'N' }">
			<form id="logoutForm" method="POST" action="logout.do">
				<div class="loginMenu">
					<c:url var="mypage" value="selectRecrBoadMemberId.do">
						<c:param name="memberID"
							value="${ sessionScope.loginMember.memberID }"></c:param>
					</c:url>
					<a href="${ mypage }" style="margin-right: 10px; margin-top: 20px;">${ sessionScope.loginMember.memberName }
						님</a>
					<button type="submit" class='button'>로그아웃</button>
				</div>
			</form>
		</c:if>
		<c:if
			test="${ !empty sessionScope.loginMember && loginMember.adminYN eq 'Y' }">
			<form id="logoutForm" method="POST" action="logout.do">
				<div class="loginMenu">
					<c:url var="mypage" value="selectRecrBoadMemberId.do">
						<c:param name="memberID"
							value="${ sessionScope.loginMember.memberID }"></c:param>
					</c:url>
					<a href="${ mypage }" style="margin-right: 10px; margin-top: 20px;">${ sessionScope.loginMember.memberName }
						님</a> <a
						href="${ pageContext.servletContext.contextPath }/adminPage.do"
						style="margin-right: 10px; margin-top: 20px;"> 관리자 페이지 </a>
					<button type="submit" class='button'>로그아웃</button>
				</div>
			</form>
		</c:if>

		<ul class="grid cs-style-3">
			<c:forEach var="category" items="${categoryList}">
				<c:url var="goBaord" value="rblist.do">
					<c:param name="categoryId" value="${category.categoryId}" />
				</c:url>
				<li>
					<figure>
						<a href="${goBaord}" > <img
							src="resources/categoryImg/${category.renameFileName}"
							alt="${category.originalFileName}" >
						</a>
						<figcaption>
							<h3 style="text-align: center;">${category.categoryName}</h3>
						</figcaption>
					</figure>
				</li>
			</c:forEach>
		</ul>
	</div>
	<!-- /container -->
	<script src="resources/js/toucheffects.js"></script>


	<div class=bottom_contents>
		<div class="announcement">

			<div class="an1">
				<a href="nlist.do">공지사항</a>
			</div>

			<div class="inan1">
				<a href="nlist.do"> <img
							src="resources/images/notice.png">
						</a>
			</div>
		</div>
		<div class="qna">

			<div class="qna1">
				<a href="qlist.do">QnA</a>
			</div>

			<div class="inqnal">
				<a href="qlist.do"> <img
							src="resources/images/Q&A.png"
							>
						</a>
			</div>
		</div>

	</div>
	<hr>

	<!-- <div class="adminF">
		<a href="goCategoryWriteForm.do">카테고리 추가</a>
	</div> -->
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<div class="admin"></div>

</body>
</html>
