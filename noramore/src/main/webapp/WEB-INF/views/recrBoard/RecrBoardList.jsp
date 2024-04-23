<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/WEB-INF/views/common/header.jsp" />
<c:import url="/WEB-INF/views/common/sideSample.jsp" />
<c:if test="${!empty requestScope.currentPage}">
	<c:set var="page" value="${requestScope.currentPage}" />
</c:if>
<c:set var="categoryId" value="1"/>
<c:if test="${!empty requestScope.categoryId}">
	<c:set var="categoryId" value="${requestScope.categoryId}" />
</c:if>
<c:set var="href" value="searchrecrtitle.do"/>
<c:if test="${!empty requestScope.href}">
	<c:set var="href" value="${requestScope.href}" />
</c:if>
<c:if test="${!empty requestScope.message}">
	<c:set var="message" value="${requestScope.message}" />
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NoraMore</title>
<style type="text/css">
#boardrecr {
	top: 150px;
	left: 10%;
	margin-left: 200px;
}
</style>
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href="resources/css/dropdown.css">
<script type="text/javascript"
	src="/noramore/resources/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=	c5e1909af5c34f4d02319699b9f0e261&libraries=services"></script>
<script type="text/javascript">

 
 
//마커 표시 함수 호출
window.onload = function(){
	var message = "${message}";
	if(message != ""){
		alert(message);
	}
}

window.onload = function(){
	
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = {
	    center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	    level: 3 // 지도의 확대 레벨
	};  
	
	//지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	
	

	//주소로부터 좌표를 얻어 마커를 표시하는 함수
	function displayMarkers() {
	var geocoder = new kakao.maps.services.Geocoder();
	
	
	// 모집게시판에 등록한 조수들 표시
	var locationList = JSON.parse('${locationListJSON}');
	
	locationList.forEach(function(RecrBoard) {
	    geocoder.addressSearch(RecrBoard.recrLocation, function(result, status) {
	        if (status === kakao.maps.services.Status.OK) {
	            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

	            var marker = new kakao.maps.Marker({
	                map: map,
	                position: coords
	            });

	            var infowindow = new kakao.maps.InfoWindow({
	                content: '<a href="rbdetail.do?page=1&categoryId=' + RecrBoard.categoryId + '&boardId=' + RecrBoard.boardId + '" target="_blank"><div style="padding:5px;">' + RecrBoard.title + '</a></div>'
	            });
	            infowindow.open(map, marker);
	        } 
	    });
	});
	
	// 집주소 마크
	geocoder.addressSearch("${sessionScope.loginMember.address}", function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            map.setCenter(coords);
            var marker = new kakao.maps.Marker({
                map: map,
                position: coords
            });

            var infowindow = new kakao.maps.InfoWindow({
                content: '<div style="padding:5px; color:blue;">우리집</div>'
            });
            infowindow.open(map, marker);
        } 
    });
	
	
	
	}
	
	displayMarkers();
	
	
	
	
}//



function rbwriteform(){
	if(${empty sessionScope.loginMember}){
		alert("로그인을 해야 합니다.");
		return;
	}
        var page = ${page};
        var categoryId = ${categoryId};
        location.href = 'rbwriteform.do?page=' + page + '&categoryId=' + categoryId;
        
        return false;
}



</script>
<style type="text/css">
.search{
	border: 1px solid #15bef5;
    border-radius: 7px;
    padding: 0 3px;
    display: flex;
    flex-direction: row;
    align-items: center;
    height: 27px;
}

</style>
</head>
<body>
	<section id="board" style="margin-left: 200px; height:1800px;">
		<h1>모집게시판 전체 목록</h1>
	
	<div id="map" style="width:1200px;height:350px; left: 20%;"></div>   

	<!-- <div class="dropdown">
		    <button class="dropbtn" id="category" name="category" value="">Dropdown</button>
		    <div class="dropdown-content">
		        <a onclick="selectCategory('볼링')">볼링</a>
		        <a onclick="selectCategory('클라이밍')">클라이밍</a>
		        <a onclick="selectCategory('싸이클')">싸이클</a>
		        <a onclick="selectCategory('헬스')">헬스</a>
		        <a onclick="selectCategory('수상레저')">수상레저</a>
		        <a onclick="selectCategory('등산')">등산</a>
		    </div>
		</div> -->
		

	<%-- 검색 --%>
		<section style='width: 1200px; border: 0px; margin: 10px 0; left: 20%;'>
		검색 기준: <div class="dropdown">
				    <select id="action" name="action" onchange="changeFormAction()">
				        <option value="searchrecrtitle.do">글제목</option>
				        <option value="searchrecrwriter.do">작성자ID</option>
				    </select>
				</div>
			<form id="searchaction" action="searchrecrtitle.do" method="post" >
				<fieldset style='width: 1200px; border: 0px;'>
					<div class="search" style='width: 180px;'>
						<input id="keyword" name="keyword" style="width:140; height:25;" value="${keyword}">
						<button type="submit">검색</button>
					</div> &nbsp; 
					한 페이지에 출력할 목록 갯수 : <select name="limit" id="limitSelect">
						<option value="10" selected>10</option>
						<option value="15">15</option>
						<option value="20">20</option>
						<c:set var="limi" value="${limit}" ></c:set>
					</select> &nbsp; 
					
				</fieldset>
				<input  type="hidden" name="categoryId" value="${categoryId}">
			</form>
			
		</section>
		
		<section style='width: 1200px; left: 20%;'>
			<button class="whiteBtn" style="float: right;" onclick="rbwriteform()">글작성</button>
			<table style='width: 1200px;'>
				<tr>
					<th>게시글 번호</th>
					<th>제목</th>
					<th>작성자ID</th>
					<th>모집상황</th>
					<th>조회수</th>
				</tr>
				<c:forEach var="rl" items="${list}">
					<c:url var="rbd" value="rbdetail.do">
						<c:param name="boardId" value="${rl.boardId}" />
						<c:param name="page" value="${page}" />
						<c:param name="categoryId" value="${categoryId}" />
					</c:url>
					<tr>
						<th>${rl.boardId}</th>
						<th><a href="${rbd}">${rl.title}</a></th>
						<th>${rl.memberId}</th>
						<th>${rl.nowRecr}명 / ${rl.maxRecr}명 [${rl.recrStatus}]</th>
						<th>${rl.readCount}</th>
					</tr>
				</c:forEach>
	
			</table>
		
		</section>
		<br>
		<c:import url="/WEB-INF/views/common/pagingView+category.jsp" />
		
	</section>

</body>
<script type="text/javascript">
document.getElementById("limitSelect").addEventListener("change", function() {
    var limitValue = this.value; // 변경된 값 가져오기
    var url = "${href}?categoryId=" + ${categoryId} + "&page=" + ${page} + "&limit=" + limitValue;
    location.href = url; // 새로운 URL로 이동
});
</script>
</html>