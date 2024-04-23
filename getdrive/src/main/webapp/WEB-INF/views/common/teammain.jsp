<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="ko">
<head> 
<title> teammain </title>
<body>

<div id="container">

  <div id="jb-header">      
	<c:import url="/WEB-INF/views/common/teamtop.jsp" />        
  </div>
  
  <div id="sidebar">
	<c:import url="/WEB-INF/views/common/teamleft.jsp" />
  </div>
  
  <div id="content">
  
    <h2>"${ team.tName }" 팀 메인 화면입니다. </h2>
    
    <c:if test="${ team.tName eq '프로젝트' }">
	    <div><h4>팀/팀원 관련</h4>
	    	<ul>
	    		<li>팀 개설</li>
	    		<li>팀원 초대</li>
	    		<li>팀정보 수정</li>
	    		<li>팀장권한 이양</li>
	    		<li>강퇴</li>
	    	</ul>
	    </div>
	 
	     <div><h4>팀 공통사항</h4>
	    	<ul>
	    		<li>게시판 등록/수정/삭제</li>
	    		<li>회의록 등록/수정/삭제</li>
	    		<li>드라이브 생성/수정/삭제</li>
	    		<li>드라이브내 폴더 생성/수정/삭제 </li>
	    		<li>드라이브내 폴더내 파일 생성/수정/삭제</li>
	    		<li>드라이브내 휴지통</li>
	    		<li>캘린더 등록/수정/삭제</li>
	    		<li>통합검색 (게시판, 회의록)</li>
	    		<li>통합검색결과 해당게시물로 이동</li>
	    		<li>알림</li>
	    	</ul>
	    </div> 
   
    </c:if>
   
	<c:if test="${ team.tName eq '강의실' }">
	    <div><h4>기능별 진행상황</h4>
	    	<ul>
	    		<li>회원가입 관련</li>
	    		<li>로그인 - 일반</li>
	    		<li>로그인 - 소셜로그인(카톡)</li>
	    		<li>비밀번호 찾기</li>
	    		<li>회원정보수정</li>    		
	    		<li>로그아웃</li>
	    		<li>회원탈퇴</li>    		
	    	</ul>
	    </div>	
	</c:if>
 
  </div>

  <div id="footer">
    <c:import url="/WEB-INF/views/common/teamfooter.jsp" />
  </div>

</div>

</body>
</html>