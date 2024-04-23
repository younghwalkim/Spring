<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>팀 목록</title>

<!-- 2024.04.05 kimyh : 공통 css 처리 -->
<link rel="stylesheet" href="/getdrive/resources/css/common.css">

<style type="text/css">
#full {
	width: 100%;
	height: 100%;
}

#header {
	width: 100%;
	height: 100px;
}
</style>

<style>

	center {
		margin-bottom: 5rem;
		font-size: 1.2rem;
	}
	
	p {
		display: inline-block;
		width: 15rem;
		font-weight: bold;
	}

	button {
		background-color: #e6e6e6;
		color: black;
		border-radius: 5px;
		border: none;
		padding: 1rem;
		font-size: 1.2rem;
	}
	
	button#tadmin {
		background-color: lawngreen;
	}
	
	button#tmain {
		/* getdrive 블루계열 컬러 */
		background-color: #6DBFF2;
		color: white;
	}
	
	button#mteam {
		background-color: limegreen;
	}
	
	button#cteam {
		background-color: #323232;
		color: white;
		margin-top: 1rem;
		padding: 1rem 12rem;
	}
	
	button#itmember {
		background-color: #c71585;
		color: white;
	}
	
	button:hover {
		cursor: pointer;
	}
	
	table {
		margin-top: 3rem;
		border-collapse: collapse;
	}
	
	th, td {
		padding: 1rem;
		border-bottom: 2px solid black;
	}
	
</style>

<script type="text/javascript" src="/getdrive/resources/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript">	

$(function(){
	// 팀 가입 버튼
	$('#itmember').click(function(){
		$.ajax({
			url: "itmember.do",
			type: "post",
			data: {email: $('#tlist_email').val(),
				   tno: $('#tlist_tno').val()},
			success: function(){
				alert("가입에 성공하셨습니다.");
				location.reload();
			}
		});
	});
});

// 대시보드 입장
function moveMain(){
	location.href = "template.do";
}

// 팀 생성 화면 입장
function createTeam(){
	location.href = "tcreate.do";
}
</script>
</head>
<body>

<div id="full">
	<div id="header">
		<c:import url="/WEB-INF/views/common/mainTopbar.jsp"></c:import>
	</div>
	<hr>
	
	<table width=600 align="center">
		<tr>
			<th>${ loginMember.name }님의 초대/가입된 목록입니다. </th>
		</tr>
		<c:forEach items="${ list }" var="tlist">
			<c:if test="${ tlist.tuEmail eq loginMember.email }">
				<tr>
					<td align="center">
						<p>${ tlist.tName }</p> &nbsp;
						
						
						<!-- 팀장이라면 팀 관리 버튼이 노출 -->
						<c:url var="tadmin" value="tadmin.do">
							<c:param name="tNo" value="${ tlist.tNo }" />
						</c:url>
						<c:if test="${ tlist.tuLeader eq 'Y' }">
							<button id="tadmin" onclick="javascript:location.href='${ tadmin }';">팀 관리</button> &nbsp;			
						</c:if>
						
						<!-- 팀원이라면 팀 정보 버튼이 노출 -->
						<c:url var="tuinfo" value="tuinfo.do">
							<c:param name="tNo" value="${ tlist.tNo }" />
						</c:url>
						<c:if test="${ tlist.tuLeader eq 'N' && tlist.tuInvited eq 'Y' }">
							<button onclick="javascript:location.href='${ tuinfo }';">팀 정보</button> &nbsp;			
						</c:if>	
						
						<!-- 팀 가입이 되어 있다면 -->
						<c:if test="${ tlist.tuInvited eq 'Y' }">
							<c:url var="enter" value="teammain.do">
								<c:param name="tNo" value="${ tlist.tNo }"/>
							</c:url>
							<button id="tmain" onclick="javascript:location.href='${ enter }';">팀 입장</button>
						</c:if>
						
						<!-- 팀 가입이 안 되어 있다면 -->
						<c:if test="${ tlist.tuInvited eq 'N' }">
							<input type="hidden" id="tlist_email" value="${ tlist.tuEmail }">
							<input type="hidden" id="tlist_tno" value="${ tlist.tNo }">
							<button id="itmember">팀 가입</button>
						</c:if>
					</td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
	<br><br>
	<div align=center>
		<button id="cteam" onclick="createTeam();">팀 생성</button>
	</div>
	<br><br>
	<hr>
	
	<div id="footer">
		<c:import url="/WEB-INF/views/common/mainFooter.jsp"></c:import>
	</div>	
	
</div>

</body>
</html>