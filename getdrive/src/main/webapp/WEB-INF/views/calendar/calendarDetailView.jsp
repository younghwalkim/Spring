<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import = "java.util.*"  %>

<%

int action = 0; //up(1) down(0)
int year = 0;
int month = 0;  // 0 ~ 11

// 년도, 월 중 하나라도 지정(넘겨져 오지)되지 않으면 오늘날짜 기준으로 월달력 출력
if(request.getParameter("year") == null || request.getParameter("month") == null) {
	Calendar today = Calendar.getInstance();
	year = today.get(Calendar.YEAR);
	month = today.get(Calendar.MONTH);
} else {
	// 출력하고자 하는 달력의 년도와 월
	year = Integer.parseInt(request.getParameter("year")); // 2024
	month = Integer.parseInt(request.getParameter("month")); // 0 ~ 11
	
	// 이전달 클릭 year, month-1 / 다음달 클릭 year, month+1
	// -1 -> 11,year--  12 -> 0,year++
	if(month == -1) {
		month = 11;
		year = year-1;
	}
	if(month == 12) {
		month = 0;
		year = year+1;
	}
}	


%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스케줄 일정보기</title>

 <c:url var ="cldel" value="cldelete.do?">
	<c:param name="clnum" value="${ calendar.calendarNo }"/>
	<c:param name="tNo" value="${ calendar.calendarTid }"/>
</c:url>

 <c:url var ="clup" value="clupview.do?">
	<c:param name="clnum" value="${ calendar.calendarNo }"/>
	<c:param name="tNo" value="${ calendar.calendarTid }"/>	
 </c:url>
 
<script type="text/javascript" src="/getdrive/resources/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript">
function requestDelete(){
	    //스케줄 삭제 요청 함수
	    if(confirm("삭제하시겠습니까?")) {
	        location.href = "${cldel}&year=<%=year%>&month=<%=month%>";
	    }
	}
	
	function moveUpdatePage(){
	    //스케줄 수정 페이지로 이동 처리 함수
	    location.href = "${clup}&year=<%=year%>&month=<%=month%>";
	}  
			
</script>

</head>

<style>

table{

  border-style: solid;

  border-color: #CECECE;
}

th{color: white;}

textarea{ border-color: #CECECE; }

input{
	color: white;

	border-style: solid;
	
	background-color: #6DBFF2;

  	border-color: #CECECE;
  	
  	font-color : white
}

/* #delete{
	display: none;
}
 */

</style>
<body id="teambody">

<div id="container">

	<div id="jb-header">      
		<c:import url="/WEB-INF/views/common/teamtop.jsp" />        
	</div>
	
	<div id="sidebar">
		<c:import url="/WEB-INF/views/common/teamleft.jsp" />
	</div>
	
	<div id="content">
		
		<h1 align="center">일정보기</h1>
		
		<table align="center" width="700" border="1" cellspacing="0" cellpadding="5">		
			
			<tr> 
				<th style="background-color: #6DBFF2;">날짜</th>
				<td>${ calendar.calendarDate }</td>
			</tr>
			<tr><!-- 숫자는 num -->
				<th style="background-color: #6DBFF2;">시작시간</th>
				<td>${ calendar.calendarStart }</td>
			</tr>
			<tr>
				<th style="background-color: #6DBFF2;">종료시간</th>
				<td>${ calendar.calendarEnd } </td>
			</tr>
		
			 
			<tr>
				<th style="background-color: #6DBFF2;">제 목</th>
				<td>${ calendar.calendarTitle }</td>
			</tr>
			
			<tr>
				<th style="background-color: #6DBFF2;">작성자</th>
				<td>${ sessionScope.loginMember.name }</td>
			</tr>
			
			<tr>
				<th style="background-color: #6DBFF2; white-space:nowrap">내 용</th>
				<td>${ calendar.calendarContent }</td>
			</tr>
			<tr>
				<th colspan="2">
					<c:if test="${ loginMember.accountNo eq calendar.calendarCRUid}">
					<input type="submit" value="수정" onclick="moveUpdatePage();" style="color: white;"> &nbsp;
					</c:if> 
					<c:if test="${ loginMember.accountNo eq calendar.calendarCRUid}">
					<input type="reset" value="삭제" id="delete" onclick="requestDelete(); return false;"> &nbsp;
					</c:if>
					<input type="button" value="달력" 
					onclick="javascript:location.href='calendar.do?tNo=${ tNo }&year=<%=year%>&month=<%=month-1%>'; return false;">
				</th>		
			</tr>
		</table>
	</div>	
	
	<div id="footer">
    	<c:import url="/WEB-INF/views/common/teamfooter.jsp" />
  	</div>

</div>
	
	
</body>
</html>	