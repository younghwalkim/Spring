<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>teamAdmin</title>
<style type="text/css">

#full {
	width: 100%;
	height: 100%;
}

#header {
	width: 100%;
	height: 100px;
}

header {
	border:0px solid;
	text-align: center;
}

section {
	border:0px solid;	
	align:center;
	display: flex;
	
	justify-content: center; /* 수평 가운데 정렬 */
	/* align-items: center; /* 수직 가운데 정렬 */	 */
}

aside {
	border:0px solid;
	margin-top: 10px;
	width: 250px;
	text-align: center;
}	

.wrapper {
	border:0px solid;
	margin-top: 10px;	
	width: 700px;
	text-algin: center;		
}

.content {
	border:0px solid;
	width: 80%;
	/* margin: 0 auto; */
}	

a {
	text-decoration: none;
	color: black;
}

#member_btn {
	width: 80%;
	background-color: #e6e6e6;
	margin: 0 auto;
	font-size: 1.5rem;
	height: 5rem;
	line-height: 5rem;
}

#member_btn:hover {
	cursor: pointer;
} 

#team_btn {
	width: 80%;
	background-color: #6DBFF2;
	color: white;
	margin: 0 auto;
	font-size: 1.5rem;
	height: 5rem;
	line-height: 5rem;
}

#team_btn:hover {
	cursor: pointer;
}

input {
	width: 250px;
	height: 50px;
	border: 1px solid #6DBFF2; /* 블루 계열 테두리 색상 설정 */
}

button {
	padding: 1rem;
	color: white;
	background-color: #6DBFF2;
	border-radius: 5px;
	border: none;
	font-size: 1.2rem;
}

button:hover {
	cursor: pointer;
}


#submitName {
	width: 3rem;
	color: white;
	background-color: #6DBFF2;
	border-radius: 5px;
	border: none;
	font-weight: bold;
	margin-left: 11rem;
}

#submitName:hover {
	cursor: pointer;
}	
</style>

<script type="text/javascript" src="/getdrive/resources/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript">
$(function(){
	// 중복 체크 변수
    var isDuplicate = false;

    // 입력 필드의 값이 변경될 때마다 중복 검사
    $('#newTeamName').on('blur', function(){
        var teamName = $(this).val();
        $.ajax({
            url: "duplicateCheck.do",
            type: "post",
            data: {tName: teamName},
            success: function(response){
                if(response === "duplicate") {
                    isDuplicate = true;
                    alert("이미 사용 중인 팀 이름입니다.");
                } else {
                    isDuplicate = false;
                }
            }
        });
    });

    // 입력된 값으로 팀명 수정
    $('#utname').click(function(event){
        event.preventDefault();
        var newTeamName = $('#newTeamName').val();
        $('#modalTeamName').text("작성하신 팀명으로 변경하시겠습니까?");
        $('#modalContainer').removeClass('hidden');
    });
    
    // 팀 삭제할 때 비밀번호가 일치하지 않는 경우
    $('#password').on('blur', function(){
        $.ajax({
            url: "pwdchk.do",
            type: "post",
            data: {pwd: $(this).val()},
            success: function(response){
            	if(response === "wrong") {
                    alert("비밀번호가 일치하지 않습니다.");
                    $('#deleteButton').prop('disabled', true);
                } else {
                    $('#deleteButton').prop('disabled', false);
                }
            }
        });
    });
    
    // 팀 삭제
    $('#deleteButton').click(function(){
    	$.ajax({
            url: "dteam.do",
            type: "post",
            data: {tNo: $('#teamNo').val()},
            success: function(response){
            	if(response === "success") {
                    alert("팀이 정상적으로 삭제되었습니다.");
                    location.href = "tmain.do";
                } else {
                	alert("다른 팀원이 존재합니다. 먼저 팀장의 권한을 이양하세요.");
                }
            }
        });
    });
});
</script>
</head>
<body>

<div id="full">
	<div id="header">
		<c:import url="/WEB-INF/views/common/mainTopbar.jsp"></c:import>
	</div>
	<hr>
		
		<!-- 헤더 영역 -->
		<header>
			<h2>"${ team.tName }" 팀 관리</h2>
		</header>
		<hr>
		
		<section>			
			<!-- 사이드 메뉴 -->
			<aside>
				<div id="team_btn">
					<span>팀 관리</span>
				</div>
				<div id="member_btn">
					<c:url var="tinfo" value="tinfo.do">
						<c:param name="tNo" value="${ team.tNo }"/>
					</c:url>
					<a href="${ tinfo }"><span>팀원 관리</span></a>
				</div>
				<div id="member_btn">
					<c:url var="tadmin" value="teammain.do">
						<c:param name="tNo" value="${ team.tNo }"/>
					</c:url>
					<a href="${ tadmin }"><span>팀 입장</span></a>
				</div>					
			</aside>
			
			<div class="wrapper">
				<div class="content">
					<h3>팀 이름 변경</h3>
					<p>새로운 팀 이름</p>
					<form id="teamNameForm" action="uteam.do">
					    <input type="text" id="newTeamName" name="tName" placeholder="새로운 팀 이름을 입력해주세요"> &nbsp;
					    <input type="hidden" id="teamNo" name="tNo" value="${team.tNo}">
					    <button>팀 이름 변경하기</button>
					</form>
					<h3>팀 삭제</h3>
					<p style="color: red;">비밀번호 입력</p>
						<input id="password" type="password" name="pwd" placeholder="비밀번호를 입력해주세요"> &nbsp;
						<button id="deleteButton">팀 삭제하기</button>
				</div>
				
			</div>
		</section>
		<br><bR>
	<hr>
	
	<div id="footer">
		<c:import url="/WEB-INF/views/common/mainFooter.jsp"></c:import>
	</div>	
	
</div>
		
</body>
</html>