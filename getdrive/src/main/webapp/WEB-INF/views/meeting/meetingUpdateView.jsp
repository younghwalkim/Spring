<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>first</title>
<style type="text/css">
/* 2024.04.10 eunbi : 팀공통 상단영역과 충돌하여 수정함  */
	#meetingupdate {
        width: 100%;
        margin: 0 auto;
        border-collapse: collapse;
    }
	
	#meetingupdate th, #meetingupdate td {
        padding: 8px;
        border: 1px solid #dddddd;
        text-align: left;
       /*  width: 25%; /* 테이블의 열의 길이를 균등하게 조정합니다. */ */
    }
    
	/* 테이블 컬럼 배경색 적용 : 밝은 회색 */
    #meetingupdate th:first-child{
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
	  
	
	<h1 align="center">
	${ meeting.mtId } 번 회의록 수정 페이지</h1>
	<br>
	<!-- form 에서 입력값들과 파일을 함께 전송하려면 반드시 속성을 추가해야 함 : enctype="multipart/form-data" -->
	<form action="mupdate.do" method="post" enctype="multipart/form-data">
	
		<input type="hidden" name="mtId" value="${ meeting.mtId }">	
		<input type="hidden" name="mtOriginalFileName" value="${meeting.mtOriginalFileName }">
		<input type="hidden" name="mtRenameFileName" value="${meeting.mtRenameFileName }">
		
		<table id="meetingupdate" align="center" width="500" border="1" cellspacing="0" cellpadding="5">
		<tr>
			<th>제 목</th>
			<td><input type="text" name="mtTitle" size="50" value="${ meeting.mtTitle }"></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="mtWriter" readonly value="${ sessionScope.loginMember.name }"></td></tr>
		<tr>
		    <th>중요도</th>
		    <td>
		        <input type="checkbox" name="mtImportance" value="Y" 
		            <c:if test="${!empty meeting.mtImportance and meeting.mtImportance eq 'Y'}">checked</c:if>
		        > 중요
		    </td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
		   		<c:if test="${ !empty meeting.mtOriginalFileName }">
					${ meeting.mtOriginalFileName } &nbsp; 
					<input type="checkbox" name="deleteFlag" value="yes"> 파일삭제 <hr>
					변경 : <input type="file" name="upfile">
				</c:if>
				<c:if test="${ empty meeting.mtOriginalFileName }">
					첨부된 파일 없음 <br>
					추가 : <input type="file" name="upfile">	
				</c:if>		
			</td>
		</tr>
	
		<tr>
			<th>내 용</th>
			<td><textarea rows="5" cols="50" name="mtContent">${ meeting.mtContent }</textarea></td>
		</tr>
		<tr>
			<th colspan="2">
			<input type="submit" value="수정하기"> &nbsp; 
			<input type="reset" value="수정취소"> &nbsp;
			<input type="button" value="이전페이지로" onclick="javascript:history.back(-1); return false;"></th>
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