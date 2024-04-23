<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style>
	button {
		border-radius: 5px;
		background-color: #6DBFF2;
		color: white;
		font-size: 1.5rem;
		margin-top: 10rem;
	}
	
	button:hover {
		cursor: pointer;
	}
</style>
<script type="text/javascript">
	// 팀 생성 화면 입장
	function createTeam(){
		location.href = "tcreate.do";
	}
</script>
</head>
<body>
<c:import url="/WEB-INF/views/common/mainTopbar.jsp"></c:import>
<center>
<h2>초대된 팀이 존재하지 않습니다</h2>
<h2>팀에 초대받거나, 새로운 팀을 생성할 수 있습니다</h2>
<button onclick="createTeam();">새로운 팀 생성하기</button>
</center>
</body>
</html>