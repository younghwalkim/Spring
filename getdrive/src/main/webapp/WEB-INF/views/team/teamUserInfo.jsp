<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>teamUserInfo</title>
<style>

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
	border:0x solid;	
	align:center;
	display: flex;
	text-align: center;
	
	justify-content: center; /* 수평 가운데 정렬 */
	/* align-items: center; /* 수직 가운데 정렬 */ */
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
	
	a {
		text-decoration: none;
		color: black;
	}
	
	#member_btn {
		width: 80%;
		background-color: #6DBFF2;
		color: white;
		margin: 0 auto;
		font-size: 1.5rem;
		height: 5rem;
		line-height: 5rem;
	}
	
	#team_btn {
		width: 80%;
		background-color: #e6e6e6;
		margin: 0 auto;
		font-size: 1.5rem;
		height: 5rem;
		line-height: 5rem;
	}
	
	#team_btn:hover {
		cursor: pointer;
	}
	
	.wrapper {
		border:0px solid;
		margin-top: 10px;
		width: 700px;
		text-algin: center;
	}
	
	.section_header {
		display: flex;
		display: flex;		
		justify-content: space-between;
	}
	
	table {
		width: 100%;
		font-size: 1.2rem;
	}
	
	.member {
		display: flex;
		justify-content: space-between;
	}
	
	tr:nth-last-child(1) {
		text-align: center;
	}
	
	#invite {
		background-color: #656565;
		color: white;
		width: 30	%;
		margin: 0 auto;
		padding: 0.5rem 0;
	}
	
	#invite:hover {
		cursor: pointer;
	}
	
	/* 모달창 */
	#tuInviteCloseButton {
  		cursor: pointer;
	}

	#tuInvite {
	  width: 100%;
	  height: 100%;
	  position: fixed;
	  top: 0;
	  left: 0;
	  display: flex;
	  justify-content: center;
	  align-items: center;
	  background: rgba(0, 0, 0, 0.5);
	}
	
	#tuInviteModal {
	  position: absolute;
	  background-color: #ffffff;
	  width: 300px;
	  height: 150px;
	  padding: 2rem;
	}
	
	#tuInvite.hidden {
	  display: none;
	}
	
</style>
<script type="text/javascript" src="/getdrive/resources/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		var tNo = $('#tno').val();
		
	    // 팀원 초대 버튼, 모달창 노출
	    $('#invite').click(function(){
	    	$('#tuInvite').removeClass('hidden');
	    	$('#tuInviteCloseButton').click(function(){
		        $('#tuInvite').addClass('hidden');
		    });
	    });
	    
	    // 팀원 초대 모달창
	    $('#inviteUserBtn').click(function(){
	    	$.ajax({
	    		url: "ituser.do",
	    		type: "post",
	    		data: {email: $('#email').val(),
	    			   tno: tNo},
	    		success: function(){
	    			alert("성공적으로 팀원이 추가되었습니다.");
	    			location.href = "tuinfo.do?tNo=" + tNo;
	    		},
	    		error: function(errorData){
	    			console.log("Error : " + errorData);
	    		}
	    	});
	    });
	    
	    // 팀 탈퇴 버튼
	    $('#team_btn').click(function(){
	    	$.ajax({
	    		url: "dtuser.do",
	    		type: "post",
	    		data: {tno: tNo},
	    		success: function(){
	    			alert("팀에서 탈퇴하셨습니다.");
	    			location.href = "tmain.do";
	    		},
	    		error: function(errorData){
	    			console.log("Error : " + errorData);
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
			<h2>${ team.tName }</h2>
		</header>
		<hr>
		
		<section>
			<!-- 사이드 메뉴 -->
			<aside>
				<div id="member_btn">
					<span>멤버 정보</span>
				</div>
				<div id="team_btn">
					<span>팀 탈퇴</span>
				</div>
			</aside>
			
			<div class="wrapper">
				<h2>멤버 정보</h2>
				<div class="section_header">
					<div class="span"><span>참여 중인 멤버</span></div>
				</div>
				<hr>
				<table>
				<c:forEach items="${ teamUser }" var="tulist">
					<tr>
						<td>
							<div class="member">
								<div class="profile">
									<span>${ tulist.tuEmail }</span>&nbsp;
								</div>
								<!-- 팀장이라면 팀장으로 팀원이라면 팀원으로 출력되고, 버튼 노출 -->
								<c:if test="${ tulist.tuLeader eq 'Y' }">
									<div class="auth">
										<span>팀장</span>
									</div>
								</c:if>
								<c:if test="${ tulist.tuLeader eq 'N' }">
									<div class="auth">
										<span>팀원</span>
									</div>
								</c:if>
							</div>
						</td>
					</tr>
					<tr><td><hr></td></tr>
				</c:forEach>
					<tr>
						<td>
							<div id="invite"><span>팀원 추가</span></div>
						</td>
					</tr>
				</table>
			</div>
		</section>
		<!-- 초대 모달 창 -->
		<div id="tuInvite" class="hidden">
		  <div id="tuInviteModal">
		    <h3>팀원 초대</h3>
		    <p></p>
		    <div>
		    	<p>이메일 발송</p>
		    	<input type="email" id="email">
		    	<input type="hidden" id="tno" value="${ team.tNo }"><br>
		    	<button id="tuInviteCloseButton">취소</button>
		    	<button id="inviteUserBtn">확인</button>
		    </div>
		  </div>
		</div>
	
	<br><bR>
	<hr>
	
	<div id="footer">
		<c:import url="/WEB-INF/views/common/mainFooter.jsp"></c:import>
	</div>	
	
</div>		
		
</body>
</html>