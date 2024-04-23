<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>driveDetail</title>
<style type="text/css">

	header {
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
	
	header h2 {
		display: inline-block;
	}
	
	img#arrow {
		width: 3rem;
		height: 3rem;
	}
	
	img#arrow:hover {
		cursor: pointer;
	}
	
	img#add {
		width: 1rem;
		height: 1rem;
	}
	
	img#add:hover {
		cursor: pointer;
	}
	
	img#trash {
		width: 2rem;
		height: 2rem;
		margin-top: 15rem;
	}
	
	img#trash:hover {
		cursor: pointer;
	}
	
	img#sort {
		width: 1.5rem;
		height: 1.5rem;
	}
	
	img#sort:hover {
		cursor: pointer;
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
	
	/* 폴더 생성 모달창 */
	#modalFolderContainer {
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
	
	#modalFolderContent {
	  position: absolute;
	  background-color: #ffffff;
	  width: 20rem;
	  height: 10rem;
	  padding: 1.5rem;
	}
	
	#modalFolderContainer.hidden {
	  display: none;
	}
	
	#modalFolderCloseButton {
		background-color: #e6e6e6;
		color: black;
	}
	
</style>
<script type="text/javascript" src="/getdrive/resources/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript">
    $(function(){
        
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
        
        // 이전 버튼
        $('#arrow').click(function(){
        	history.back();
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
        <img id="add" src="/getdrive/resources/images/add.png" style="margin-left: 0.5rem">
    </div>
    
     <span>팀 드라이브</span> &nbsp; 
     <button id="expand">+</button>
     <div class="list-container">
       <ul class="list"></ul>
     </div> 
    
     <br>     
     <div style="border:0px solid; width:100%;">
     	<img id="trash" src="/getdrive/resources/images/trash.png" style="width:30px">
     </div>   
    
  </div>	
	    
  <div id="content">
  	
		<header>
		    <div style="display: flex; align-items: center;">
		        <img id="arrow" src="/getdrive/resources/images/chevron_left.png">
		        <h2>${ drive.dName } 드라이브</h2>
		    </div>
		</header>
						
		<div class="wrapper">
	        <div class="wrapper_header">
	            <p>${ drive.dName }</p>
	            <div class="functions">
	                <div>
	                    <button id="newFolder">새 폴더 생성</button> &nbsp;
	                </div>
	                <div style="display: flex; align-items: center;">
	                    <img id="sort" src="/getdrive/resources/images/sort.png">
	                </div>
	            </div>
	        </div>
	        <hr>
	        <!-- 폴더가 있다면 출력 -->
	        <ul>
	        	<c:forEach items="${ list }" var="folder">
	        		<c:url var="folderpage" value="fpage.do">
	        			<c:param name="fdTID" value="${ folder.fdTID }"/>
						<c:param name="fdDID" value="${ folder.fdDID }"/>
						<c:param name="fdCRUID" value="${ folder.fdCRUID }"/>
						<c:param name="fdNo" value="${ folder.fdNo }"/>
	        		</c:url>
	        		<li><img src="/getdrive/resources/images/folder.png" style="width:30px;">
	        		<a href="${ folderpage }">${ folder.fdName }</a></li>
	        	</c:forEach>
	        </ul>
	    </div>				
				
		<!-- 새 드라이브 생성 모달창 -->
		<form id="driveNameForm" action="idrive.do">
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
		
		<!-- 새 폴더 생성 모달창 -->
		<form id="folderNameForm" action="ifolder.do" method="post">
		    <div id="modalFolderContainer" class="hidden">
		        <div id="modalFolderContent">
		            <h4>새 폴더 생성</h4>
		            <div style="margin-top: 3rem;">
		                <input name="flName" type="text" placeholder="새 폴더 이름을 입력하세요."><br>
		                <input name="dTID" type="hidden" value="${ drive.dTID }">
		                <input name="dNo" type="hidden" value="${ drive.dNo }">
		                <input name="dCRUID" type="hidden" value="${ loginMember.accountNo }">
		                <button id="modalFolderCloseButton">취소</button> &nbsp;
		                <input id="submitFolderName" type="submit" value="확인">
		            </div>
		        </div>
		    </div>
		</form>
		
	</div>
  <div id="footer">
    <c:import url="/WEB-INF/views/common/teamfooter.jsp" />
  </div>

</div>
	
</body>
</html>
