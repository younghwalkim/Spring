<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="../common/error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.0.js"></script>
<style type="text/css">
#full{
	width: 100%;
	height: 100%;
	
}
#header {
	width: 100%;
	height: 100px;
	background-color: #FFFFFF;
	
}
#main {
	width: 100%;
	height: 650px;
}
#left {
	width: 50%;
	height: 650px;
	float: left;
	background-color: #E1F5FE;
}

#right {
	width: 49.9%;
	height: 650px;
	float: right;
	background-color: #E1F5FE;
}
#footer {
	margin: 10px 0px 0px;
	width: 100%;
	height: 90px;
	text-align: center;
}
#passwordFindZone{
	width :	500px;
	margin : auto;
	position: relative;
	top : 200px;
	margin-bottom: 0px;
	padding : 30px;
	border: 1px solid #ddd;
	background-color: white;
	border-radius: 5px;
	font-weight: 600;
}
.inputText {
	width: 360px;
	height: 40px;
	border: 1px solid #ddd;
	border-radius: 5px;
	background-color: #fff;
	box-sizing: border-box;
	margin: 10px 0px;
	font-weight: 600;
}
.button {
	background: #6DBFF2;
	border-radius: 5px;
	color: white;
	cursor: pointer;
	padding-bottom: 4px;
	border: none;
	width: 120px;
	height: 40px;
	text-align: center;
	font-weight: 600;
	font-size: 12pt;
	margin-left: 5px;
}
.button:hover {
	color: white;
	background: rgb(200, 230, 255);
}

.button:disabled {
	color: #696969;
	background: #DCDCDC;
	cursor: default;
}
#comment {
	font-size: 9pt;
	margin: 0px;
	font-weight: 0;
}
#fail{
	font-size: 9pt;
}
</style>
<script type="text/javascript">
$(function(){
    $('#emailBtn').on('click', function(){
        $.ajax({
            url: 'passwordGo.do',
            type: 'post', 
            data: { email: $('#email').val() },
            success: function(data){
                console.log("success : " + data);
                if(data == "ok"){
                    alert("이메일이 성공적으로 전송되었습니다.");
                }else{
                    alert("이메일 전송이 실패하였습니다.");
                }
            },
            error: function(jqXHR, textStatus, errorThrown){
                console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
            }
        });
    });  //on

    $(".inputText").on("input", function() {
        emailCheck();
    });
});

function emailCheck(){
    $.ajax({
        url: "idchk.do",
        type: "post",
        data: { email: $('#email').val() },
        success: function(data){
            console.log("success : " + data);
            if(data == "ok"){
                $('#fail').show();
            }else{
                $('#fail').hide();
            }
        },
        error: function(jqXHR, textStatus, errorThrown){
            console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
        }
    });
}
</script>
</head>
<body id="full">
<div id="header">
	<c:import url="/WEB-INF/views/common/mainTopbar.jsp"></c:import>
</div>
<div id="main">
<div id="left">
	<c:import url="/WEB-INF/views/common/commonMenu.jsp"></c:import>
</div>
<div id="right">
	<form id="passwordFindForm" >
		<div id="passwordFindZone">
			<div class="emailInput">
				<label for="userEmail" class="registerText">이메일</label><br>
						<form id="emailInput" action="passwordGo.do" method="post">
							<input type="email" id="email" class="inputText" name="email" placeholder="이메일 입력" required>
							<button id="emailBtn" class="button" type="button">인증메일전송</button>
							<div id="fail" style="display: none; color: red">가입되지 않은 이메일입니다</div>
						</form>
					</div>
				</div>
	</form>
</div>
</div>
<div id="footer">
	<c:import url="/WEB-INF/views/common/mainFooter.jsp"></c:import>
</div>
</body>
</html>