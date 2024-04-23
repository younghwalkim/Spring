<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WriteForm</title>
<style type="text/css">
/* 2024.04.10 eunbi : 팀공통 상단영역과 충돌하여 수정함  */
	#meetingwrite {
        width: 100%;
        margin: 0 auto;
        border-collapse: collapse;
    }
	
	#meetingwrite th, #meetingwrite td {
        padding: 8px;
        border: 1px solid #dddddd;
        text-align: left;
       /*  width: 25%; /* 테이블의 열의 길이를 균등하게 조정합니다. */ */
    }
    
	/* 테이블 컬럼 배경색 적용 : 밝은 회색 */
    #meetingwrite th:first-child{
        background-color: #f2f2f2; 
        text-align: center;
    }
    
    /* 버튼 스타일 */
    input[type="submit"],
    input[type="reset"],
    input[type="button"] {
        background-color: #41AEF2; 
        color: white;
        border: none;
        padding: 5px 20px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        font-weight: bold;
        margin: 10px 4px;
        cursor: pointer;
    }
   
   /* 내용영역 */
   textarea {
   resize: none;
   width: 93%;
   height: calc(5 * 5em);
   padding: 10px 10px;
   border: 1px solid #000;
   border-radius: 7px;
   /* margin-bottom: 1px; */
}
</style>

<script type="text/javascript" src="/getdrive/resources/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript"></script>
</head>

<body>

<div id="container">

  <div id="jb-header">      
	<c:import url="/WEB-INF/views/common/teamtop.jsp" />        
  </div>
  
  <div id="sidebar">
	<c:import url="/WEB-INF/views/common/teamleft.jsp" />
  </div>
  
  <div id="content">
	  
	<h1 align="center">새 회의록 등록 페이지</h1>
	<br>
	<!-- form 에서 입력값들과 파일을 함께 전송하려면 반드시 속성을 추가해야 함 : enctype="multipart/form-data" -->
	<form action="minsert.do" method="post" enctype="multipart/form-data">
	    <input type="hidden" name="mtMid" value="${ loginMember.accountNo }">
	    <input type="hidden" name="mtWriterId" value="${ loginMember.email }">
	    <input type="hidden" name="mtTid" value="${ tNo }">
		<table id="meetingwrite" align="center" width="500" border="1" cellspacing="0">
			<tr><th>제 목</th><td><input type="text" name="mtTitle" size="95"></td></tr>
			<tr><th>작성자</th>
				<td><input type="text" name="mtWriter" size="20%" readonly 
					value="${ loginMember.name }"></td></tr>
			<tr><th>중요도</th>
				<td><input type="checkbox" name="mtImportance" value="Y"> 중요</td>
			</tr>
			<tr><th>첨부파일</th><td><input type="file" name="ofile"></td></tr>
			<tr><th>내 용</th>
			    <td><textarea rows="5" cols="50" name="mtContent">${meeting.mtContent}</textarea></td>
			<tr><th colspan="2">
				<input type="submit" value="등록하기"> &nbsp; 
				<input type="reset" value="작성취소"> &nbsp;
				<input type="button" value="목록" 
					onclick="javascript:history.go(-1); return false;">
			</th></tr>
		</table>
	</form>

  </div>

  <div id="footer">
    <c:import url="/WEB-INF/views/common/teamfooter.jsp" />
  </div>

</div>

</body>
</html>