<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>스케줄 등록</title>
</head>

<style>

table{

  border-style: solid;

  border-color: #CECECE;
}

th{color: white;}

td{ background-color: white;}

textarea{ border-color: #CECECE; }

input{
	color: black;

	border-style: solid;


  	border-color: #CECECE;
  	
  	font-color : white
}

</style>

<script type="text/javascript" src="/getdrive/resources/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript">
$(function() {
//날짜 입력 필드에서 변경이 감지되면 실행
$('#calendarDate').change(function() {
    // 입력 필드의 값을 가져옴
    var input = $(this).val();    
    
    // 입력된 값을 Date 객체로 변환
    var date = new Date(input);

    // Date 객체에서 년, 월, 일을 추출
    var year = date.getFullYear();
    var month = date.getMonth() + 1; // getMonth()는 0부터 시작하므로 +1을 해줍니다.
    var day = date.getDate();

    // 추출한 년, 월, 일을 다른 입력 필드에 적용
    $('#year').val(year);
    $('#month').val(month);
    $('#day').val(day);
	});
});


</script>

<script>
  document.addEventListener('DOMContentLoaded', function () {
    var calendarStart = document.getElementsByName('calendarStart')[0];
    var calendarEnd = document.getElementsByName('calendarEnd')[0];

    calendarStart.addEventListener('blur', function() {
      if(this.value < 0 || this.value > 24) {
        alert('시작 시간은 0에서 24 사이의 숫자여야 합니다.');
        this.value = ''; //초기화
      }
    });

    calendarEnd.addEventListener('blur', function() {
      if(this.value < 0 || this.value > 24) {
        alert('종료 시간은 0에서 24 사이의 숫자여야 합니다.');
        this.value = ''; //초기화
      }
    });
  });
</script>
<body>

<div id="container">

 <div id="jb-header">      
<c:import url="/WEB-INF/views/common/teamtop.jsp" />        
 </div>
 
 <div id="sidebar">
<c:import url="/WEB-INF/views/common/teamleft.jsp" />
 </div>
 
 <div id="content">
 

<h1 align="center">스케줄 등록</h1>

<form action="clinsert.do" method="post">
<input type="hidden" name="calendarCRUid" value =${ loginMember.accountNo }>
<input type="hidden" name="calendarTid" value =${ tNo }>
<input type="hidden" name="year"  id ="year" value=${ year }>
<input type="hidden" name="month" id="month" value=${ month }>
<input type="hidden" name="day" id="day" value=${ day }>
<input type="hidden" name="calendarCheck" value="Y">

<table align="center" width="500" border="1" cellspacing="0" cellpadding="5">

	<tr style= "height: 20PX"> 
		<th style="background-color: #6DBFF2;">날짜</th>
		<td style="background-color: #6DBFF2;">
		<input type="date" name="calendarDate" id="calendarDate" value="${ year }-${month lt 10 ? '0' : ''}${month}-${day lt 10 ? '0' : ''}${day}"></td>
	</tr>
	<tr><!-- 숫자는 num -->
		<th style="background-color: #6DBFF2;">시작시간</th>
		<td style="background-color: #6DBFF2;">
			<input type="number" name="calendarStart" min="0" max="24"></td>
	</tr>
	<tr>
		<th style="background-color: #6DBFF2;">종료시간</th>
		<td style="background-color: #6DBFF2;">
			<input type="number" name="calendarEnd" min="0" max="24"></td>
	</tr>
	
<!-- 	
	<tr>
	<td colspan="2" align="center" style="background-color: #6DBFF2;" ><a href="javascript:void(0);" style="color: #000000;" onclick="openLayerWindow();">장소설정</a>
		<script>
		function openLayerWindow() {
		  window.open("${pageContext.servletContext.contextPath}/clmap.do", "지도창", "width=400, height=400, left=100, top=100");
		}
		</script></td>
	 </tr>
-->
	<tr>
		<th style="background-color: #6DBFF2;">제 목</th>
		<td>
			<input type="text" name="calendarTitle" size="50" style=background-color:white;></td>
	</tr>
	
	<tr>
		<th style="background-color: #6DBFF2;">작성자</th>
		<td>
			<input type="text" name="calendarCRUid" readonly 
			value="${ sessionScope.loginMember.name }">
		</td>
	</tr>
	
	<tr>
   	 <th style="background-color: #6DBFF2;">내 용</th>
   	 <td>
        <textarea name="calendarContent" style="width: 400px; height: 200px; resize: none;"></textarea>
   	 </td>
	</tr>
<!-- 	
	<tr>
		<th colspan="2" style="color: black;">
			<input type="radio" name="calendarCheck" value="Y" checked >공개 &nbsp; 
			<input type="radio" name="calendarCheck" value="N">비공개</th></tr>

 -->	
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="등록" style="color: white; background-color: #6DBFF2"> &nbsp; 
			<input type="button" value="이전페이지" 
			onclick="javascript:history.back(-1); return false;" style="color: white; background-color: #6DBFF2">
	</td>		
	</tr>
</table>
</form>	

	</div>
	
	<div id="footer">
    <c:import url="/WEB-INF/views/common/teamfooter.jsp" />
  </div>

</div>

</body>
</html>	