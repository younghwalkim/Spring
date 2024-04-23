<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>teamleft</title>
<style type="text/css">

/* 버튼 */
.button_red {
  background-color: #E96354;
  border: none;
  color: white;
  text-align: center;
  display: inline-block;
  font-size: 16px;
  transition: all 0.5s;
  cursor: pointer;
  padding: 10px 24px;
  border-radius: 8px; /* 테두리 둥글게 */
}	

.button_red:hover {
  background-color: #E43E2C; /* 색상을 어둡게 조절한 hover 효과 */
  color: white;
}
	
.button_blue {
  background-color: rgb(107, 185, 237);
  border: none;
  color: white;
  text-align: center;
  display: inline-block;
  font-size: 16px;
  transition: all 0.5s;
  cursor: pointer;
  padding: 10px 24px;
  border-radius: 8px; /* 테두리 둥글게 */
}	

.button_blue:hover {
  background-color: rgb(77, 155, 207); /* 색상을 어둡게 조절한 hover 효과 */
  color: white;
}

.button_gray {
  background-color: #E0E0E0;
  border: none;
  color: white;
  text-align: center;
  display: inline-block;
  font-size: 16px;
  transition: all 0.5s;
  cursor: pointer;
  padding: 10px 24px;
  border-radius: 8px; /* 테두리 둥글게 */
}	

.button_gray:hover {
  background-color: #B0B0B0; /* 색상을 어둡게 조절한 hover 효과 */
  color: white;
}	

.button_green {
  background-color: #2ECC71;
  border: none;
  color: white;
  text-align: center;
  display: inline-block;
  font-size: 16px;
  transition: all 0.5s;
  cursor: pointer;
  padding: 10px 24px;
  border-radius: 8px; /* 테두리 둥글게 */
}	

.button_green:hover {
  background-color: #239A56; /* 색상을 어둡게 조절한 hover 효과 */
  color: white;
}	

/* 우하단 버튼 */
.btn_gotop { 
display: block; 
position: fixed; 
bottom: 35px; 	/* 아래 간격*/ 
right: 35px; 		/* 오른쪽 간격*/ 
z-index: 999; 
border: 2px solid rgba(0,0,0,0.25); 
outline: none; 
background-color: white; 
color: rgba(0,0,0,0.7); 
cursor: pointer; 
padding: 20px 20px 20px 20px; 
border-radius: 100%;

/* 레이어 팝업 */
#popup_wrap {
	width:560px ; 
	height:270px ; 
	background:#000000;
	border: solid 1px #666666; 
	position:fixed; top:50%; left:50%; 
	margin:-250px 0 0 -250px; z-index:9999; 
	display:none;
}	

#mask {
	width:100%; height:100%; position:fixed; background:rgba(0,0,0,0.7) repeat; top:0; left:0; z-index:999; 
	display:none;
}
.popup-cont01 {
	width:478px; margin: 40px auto; text-align: center;
}
.popup-cont01 button { 
	width: 138px; height: 36px; line-height: 36px; background: #9f2f60; color: #ffffff; text-align: center; 
	border: none; font-size: 16px;
}	
	
</style>

<script type="text/javascript">
$(function(){
	
	// 팀 메인 > 왼쪽 메뉴 > 팀 목록 - HomeController 
	$.ajax({
		url: "teamSelect.do?",
		type: "post",
		dataType: "json",
		success: function(data){
			// 확인
			console.log("팀선택 success : " + data);
			
			// object --> string
			var str = JSON.stringify(data);
			
			// string --> json
			var json = JSON.parse(str);
			
			// 확인
			console.log("json : " + str);
			
			var num = 1;			
			
			values = "";			
			for(var i in json.list){
			
				// 확인
				// console.log(json.list[i].tno + ", ${ tNo }, ${ tno }");
				
				if (json.list[i].teamno == '${ tNo }' ) {
					values += "<option value='teammain.do?tNo="+ json.list[i].teamno
					+"' selected>"
					+ decodeURIComponent(json.list[i].teamname).replace(/\+/gi, " ") 
					+ "</option>";					
				} else {
					values += "<option value='teammain.do?tNo="+ json.list[i].teamno
					+"'>"
					+ decodeURIComponent(json.list[i].teamname).replace(/\+/gi, " ") 
					+ "</option>";					
				}
			}
			
			$('#teamSelect').html(values);
		}, 
		error: function(jqXHR, textStatus, errorThrown){
			console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
		}
		
	});  //ajax
	
	/*
	// 게시판 목록
	$.ajax({
		url: "boardList.do?",
		type: "post",
		dataType: "json",
		success: function(data){
			// 확인
			console.log("success : " + data);
			
			// object --> string
			var str = JSON.stringify(data);
			
			// string --> json
			var json = JSON.parse(str);
			
			// 확인
			console.log("json : " + str);
			
			var num = 1;			
			
			values = "";			
			for(var i in json.list){

				values += "<li><a href='bmain.do?tno="+ json.list[i].no
					+"&cno="+ json.list[i].no
					+"#footer' class='item'>"
					+ decodeURIComponent(json.list[i].title).replace(/\+/gi, " ") 
					+ "</a></li>";					

			}
			
			$('#boardList').html(values + "<li><a href='#' class='item'>카테고리 생성 + </a></li>");
		}, 
		error: function(jqXHR, textStatus, errorThrown){
			console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
		}
		
	});  //ajax	
	
	// 챗 목록
	$.ajax({
		url: "chatList.do?",
		type: "post",
		dataType: "json",
		success: function(data){
			// 확인
			console.log("success : " + data);
			
			// object --> string
			var str = JSON.stringify(data);
			
			// string --> json
			var json = JSON.parse(str);
			
			// 확인
			console.log("json : " + str);
			
			var num = 1;			
			
			values = "";			
			for(var i in json.list){

				values += "<li><a href='' class='item'>"
					+ decodeURIComponent(json.list[i].title).replace(/\+/gi, " ") 
					+ "</a></li>";	
			}
			
			$('#chatList').html(values + "<li><a href='#' class='item'>챗팅방 생성 + </a></li>");
		}, 
		error: function(jqXHR, textStatus, errorThrown){
			console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
		}
		
	});  //ajax	
	
	// 드라이브 목록
	$.ajax({
		url: "driveList.do?",
		type: "post",
		dataType: "json",
		success: function(data){
			// 확인
			console.log("success : " + data);
			
			// object --> string
			var str = JSON.stringify(data);
			
			// string --> json
			var json = JSON.parse(str);
			
			// 확인
			console.log("json : " + str);
			
			var num = 1;			
			
			values = "";			
			for(var i in json.list){

				values += "<li><a href='' class='item'>"
					+ decodeURIComponent(json.list[i].title).replace(/\+/gi, " ") 
					+ "</a></li>";	
			}
			
			$('#driveList').html(values + "<li><a href='dmain.do' class='item'>드라이브 생성 + </a></li>");
		}, 
		error: function(jqXHR, textStatus, errorThrown){
			console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
		}
		
	});  //ajax	
	*/
	
});  //document ready	

</script>


</head>
<body>
	<!-- 팀 목록  -->
	<b>My Team List</b>
	
	<div style="width:100%; align:center">		
		<select id="teamSelect" onchange="window.open(value,'_self');" style="width: 150px; height: 30px; background-color: #FFFFFF; color: black; border: 2px solid #6DBFF2; padding: 5px; border-radius: 5px;" ></select>	
	</div>		

	<hr>
	<b><a href="bmain.do?tNo=${ tNo }">Board</a></b>	
	<div style="width:100%; align:left">
		<ul id="boardList"></ul>
	</div>	
	<hr>
	<b><a href="mlist.do?tNo=${ tNo }">Meeting</a></b>
	<div style="width:100%; align:left">
		<ul id="chatList"></ul>
	</div>	
	<hr>
	<b><a href='dmain.do?tNo=${ tNo }'>드라이브</a></b>
	<div style="width:100%; align:left">
		<ul id="driveList"></ul>
	</div>

	<hr>
	<b><a href="calendar.do?tNo=${tNo}">캘린더</a></b>
	
</body>
</html>