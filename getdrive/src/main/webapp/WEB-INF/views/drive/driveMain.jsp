<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>driveMain</title>

<style type="text/css">

	header {
		display: flex;
		justify-content: space-between;
	}
	
	header h2 {
		display: inline-block;
	}
	
	section {
		display: flex;
	}

	aside {
		width: 13vw;
		padding: 1rem 0 1rem 2.5rem;
		background-color: #6DBFF2;
		color: white;
	}
	
	/*
	.wrapper {
		width: 87vw;
		margin-left: 2rem;
	}	
	*/
	
	.functions {
		display: flex;
		justify-content: space-between;
	}
	
	.list {
	    display: none; 
	}

	.list.active {
	    display: block; 
	}
	
	/* 드라이브 생성 모달창 */
	#modalContainer {
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
	
	#modalContent {
	  position: absolute;
	  background-color: #ffffff;
	  width: 20rem;
	  height: 10rem;
	  padding: 1.5rem;
	}
	
	#modalContainer.hidden {
	  display: none;
	}
	
	#modalCloseButton {
		background-color: #e6e6e6;
		color: black;
	}	
</style>

<script type="text/javascript" src="/getdrive/resources/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript">
    $(function(){
    	$('#expand').click(function(){
    		console.log("clicked");
    		$('.list-container').toggleClass('active');
    		$('ul.list').toggleClass('active');
            $(this).text(function(i, text){
                return text === '+' ? '-' : '+';
            });
    	});
        
         // 새 드라이브 생성 모달창
        $('#add').click(function() {
            $('#modalContainer').removeClass('hidden');
        });
        // 모달창 확인 버튼
        $('#submitDriveName').click(function(event){
            event.preventDefault();
            $('#driveNameForm').submit();
        });
        // 모달창 닫기
        $('#modalCloseButton').click(function(event){
            event.preventDefault();
            $('#modalContainer').addClass('hidden');
        });
        
        // 새 폴더 생성 모달창
        $('#newFolder').click(function() {
            $('#modalFolderContainer').removeClass('hidden');
        });
        // 모달창 확인 버튼
        $('#submitFolderName').click(function(event){
            event.preventDefault();
            $('#folderNameForm').submit();
        });
        // 모달창 닫기
        $('#modalFolderCloseButton').click(function(event){
            event.preventDefault();
            $('#modalFolderContainer').addClass('hidden');
        });
        
        // 이전 버튼을 누르면 대시보드로 이동
        $('#arrow').click(function(){
            location.href="teammain.do?tNo=${ tNo }";
        });
        
    });
</script>
</head>

<body>
<div id="container">

  <div id="jb-header">      
	<c:import url="/WEB-INF/views/common/teamtop.jsp" />        
  </div>

  <div id="sidebar">
	
	<c:import url="/WEB-INF/views/common/teamleft_drive.jsp" />
		
     <div style="display: flex; align-items: center;">
         <p style="font-weight: bold; margin-bottom: 1rem;">새 드라이브 생성</p>
         <img id="add" src="/getdrive/resources/images/add.png" style="width:30px; margin-left: 0.5rem">
     </div>
     
     <span>팀 드라이브</span> &nbsp; 
     <button id="expand">+</button>
     <div class="list-container">
       <ul class="list">
         <c:forEach items="${ list }" var="dlist">
             <c:if test="${ dlist.dPub eq 'Y' }">
					<c:url var="drive" value="dpage.do">
						<c:param name="tNo" value="${ dlist.dTID }"/>
						<c:param name="dNo" value="${ dlist.dNo }"/>
						<c:param name="tUID" value="${ loginMember.accountNo }"/>
					</c:url>
                 <li><a href="${ drive }">${ dlist.dName }</a></li>
             </c:if>
         </c:forEach>
       </ul>
     </div>
     
     <br>     
     <div style="border:0px solid; width:100%;">
     	<img id="trash" src="/getdrive/resources/images/trash.png" style="width:30px">
     </div>   
	<!-- 새 드라이브 생성 모달창 -->
	<form id="driveNameForm" action="idrive.do" method="post">
	    <div id="modalContainer" class="hidden">
	        <div id="modalContent">
	            <h4>새 드라이브 생성</h4>
	            <div style="margin-top: 3rem;">
	                <input name="dName" type="text" placeholder="새 드라이브 이름을 입력하세요."><br>
	                <input name="tNo" type="hidden" value="${ tNo }">
	                <button id="modalCloseButton">취소</button> &nbsp;
	                <input id="submitDriveName" type="submit" value="확인">
	            </div>
	        </div>
	    </div>
	</form>	    
	
  </div>	
	    
  <div id="content">	    
		<header>
		    <div style="display: flex; align-items: center;">
		        <img id="arrow" src="/getdrive/resources/images/chevron_left.png" style="width:30px;" >
		        <h2>드라이브</h2>
		    </div>
		    <!-- 
		    <div>
		        <input id="search" placeholder="파일 검색">
		    </div>
		     -->
		</header>
			  
	    <div class="wrapper">
	        <div class="wrapper_header">
	            <p>${ loginMember.name } 님의 드라이브</p>
	        </div>
	    </div>
  </div>

  <div id="footer">
    <c:import url="/WEB-INF/views/common/teamfooter.jsp" />
  </div>

</div>


</body>
</html>
