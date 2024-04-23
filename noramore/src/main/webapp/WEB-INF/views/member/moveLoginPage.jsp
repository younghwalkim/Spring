<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="../common/error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
<link rel="stylesheet" type="text/css"
	href="resources/css/loginPage.css" />
	<c:if test="${ !empty requestScope.message }">
		<c:set var="message" value="${ requestScope.message }" />
	</c:if>

<script type="text/javascript"src="${ pageContext.servletContext.contextPath }/resources/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript">
	function enroll() {
		location.href = "enrollPage.do";
	}
	/* <script src="/noramore/resources/js/kakao.min.js"> */


/* 	
 $(() => {
	   const urlParams = new URLSearchParams(window.location.search);
	    const message = urlParams.get('message');
	  
	    console.log(message);
	}); //doc ready;
	 */
	
  window.onload = function() {
	
		 var message = "${message}";
		 if(message != ""){
		 alert(message);
		 }
	}; 
	
	
</script>


<script type="text/javascript">
	$(document).ready(function() {
		var key = getCookie("idChk"); //쿠키에 아이디를 key변수로 가져옴
		if (key != "") {   
			$("#mid").val(key);
		}

		if ($("#mid").val() != "") {
			$("#idSaveCheck").attr("checked", true);
		}

		$("#idSaveCheck").change(function() {
			if ($("#idSaveCheck").is(":checked")) {
				setCookie("idChk", $("#mid").val(), 7);
			} else {
				deleteCookie("idChk");
			}
		});

		$("#mid").keyup(function() {
			if ($("#idSaveCheck").is(":checked")) {
				setCookie("idChk", $("#mid").val(), 7);
			}
		});
	});
	function setCookie(cookieName, value, exdays) {
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + exdays);
		var cookieValue = escape(value)
				+ ((exdays == null) ? "" : "; expires=" + exdate.toGMTString());
		document.cookie = cookieName + "=" + cookieValue;
	}

	function deleteCookie(cookieName) {
		var expireDate = new Date();
		expireDate.setDate(expireDate.getDate() - 1);
		document.cookie = cookieName + "= " + "; expires="
				+ expireDate.toGMTString();
	}

	function getCookie(cookieName) {
		cookieName = cookieName + '=';
		var cookieData = document.cookie;
		var start = cookieData.indexOf(cookieName);
		var cookieValue = '';
		if (start != -1) {
			start += cookieName.length;
			var end = cookieData.indexOf(';', start);
			if (end == -1)
				end = cookieData.length;
			cookieValue = cookieData.substring(start, end);
		}
		return unescape(cookieValue);
	}
</script>

</head>
<body>

	<div id="moveMain">
		<a href="javascript:history.go(-1);">메인으로 이동</a> &nbsp;
	</div>
	<div id="loginForm">
		<div id="box_in">
			<form name="로그인" action="login.do" method="post">
				<h1>로그인</h1>
				<label><input type="text" name="memberID" id="mid"
					class="pos" placeholder=" Id" autocomplete="off"></label> <label>
					<input type="password" name="memberPWD" id="mpwd" class="pos"
					placeholder=" Password" autocomplete="off"></label> 
					<label for="remember" id="remember-check">아이디 저장</label> 
					<input type="checkbox" id="idSaveCheck"><br> 
					<input type="submit" value="로그인">
					
					
			</form>
			${ param.message }

			<hr>
			<div id="find">
				<a href="findIDPage.do">아이디찾기</a> / <a href="findPWPage.do">비밀번호찾기</a>
			</div>


			<div id="k_id_login" class="social_login" style="text-align: center;">
				<c:url var="kurl" value="${ requestScope.kakaourl }" />
				<a href="${ kurl }" class="cp"> <img
					src="${ pageContext.servletContext.contextPath }/resources/images/kakao_login.png"
					alt="카카오 로그인" id="kimg">
				</a>
			</div>
			<!-- 네이버 로그인 창으로 이동 -->
			<div id="naver_id_login" class="social_login"
				style="text-align: center;">
				<c:url var="nurl" value="${ requestScope.naverurl }" />
				<a href="${ nurl }" class="cp"> <img
					src="${ pageContext.servletContext.contextPath }/resources/images/naver_login.png"
					alt="네이버 로그인" id="nimg">
				</a>
			</div>


			<div id="enroll">아직도 회원이 아니신가요?</div>
			<button class='button' onclick="enroll();">회원가입</button>
			<br> <br>

		</div>

	</div>

</body>
</html>