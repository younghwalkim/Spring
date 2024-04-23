<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>암호찾기 페이지</title>
<link rel="stylesheet" type="text/css" href="resources/css/findPWPage.css" />

<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript">


function dupIdCheck() {
    var memberID = $('#memberID').val(); // 입력된 아이디 가져오기

    // AJAX를 사용하여 서버에 중복 확인 요청 보내기
    $.ajax({  
		url: "idchk.do",
		type: "post",
		data: { memberID: $('#memberid').val() },
		success: function(data){ 
			console.log("success : " + data);
			if(data == "ok"){   
				alert('회원아이디가 아닙니다. 다시 입력해주세요.');
				$('#memberid').val('');
				$('#memberid').focus();
				
			}else{
				window.location.href = "findPW2.do";
			}
		},
		error: function(jqXHR, textStatus, errorThrown){
			console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
		}
	});
	
}



</script>
</head>
<body>
<h1>비밀번호 찾기</h1>

<div>
	<h3>아이디</h3>
	<input type="text" id="memberid" name="memberID" placeholder="아이디 입력"><br>
	<button id="daumbtn" onclick="return dupIdCheck();">다음</button>
</div>


</body>
</html>