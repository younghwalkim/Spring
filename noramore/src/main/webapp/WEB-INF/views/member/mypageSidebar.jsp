<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="resources/css/mypageSidebar.css" />
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/jquery-3.7.0.min.js"></script>

</head>
<body>

<script type="text/javascript">
window.onload = function(){
	//선택한 사진파일 이미지 미리보기 처리
	var photofile = document.getElementById("photofile");
	photofile.addEventListener('change', function(event){		
		const files = event.currentTarget.files;
	    const file = files[0];
	    const myphoto = document.getElementById("photo");	    
	    console.log(file.name);
	    
	    const reader = new FileReader();
        reader.onload = (e) => {          
          myphoto.setAttribute('src', e.target.result);
          myphoto.setAttribute('data-file', file.name);
        };
        reader.readAsDataURL(file);    
	});
}






</script>

<script type="text/javascript">

$(document).ready(function() {

 
 
$.ajax({
	url : "memberProfile.do",  //DispatcherServlet로 보냄
	type : "post",     
	data: { memberid : "${ loginMember.memberID}" },  
	dataType : "json",
	success : function(data) {
		console.log("success : " + data);   //String 리턴되온것을 Object로 받음

		console.log("success11 : " + data.id); 
		console.log("success11 : " + data.photoFile); 
		
		
		values = "/noramore/resources/images/" +data.photoFile;
		value = data.membernicname;
		console.log("success22 : " + values);
		console.log("success22 : " + value);
		
		$('#photo').attr('src', values);
		
		$('#nicname').html(value);
		
	},
	error : function(jqXHR, textStatus, errorThrown) {
		console.log("error : " + jqXHR + ", " + textStatus + ", "
				+ errorThrown);
	}
}); //ajax


});



</script>

<script type="text/javascript">

$(document).ready(function() {
    $('#customButton').click(function() {
        $('#photofile').click();
    });
    $('#photofile').change(function() {
        var filePath = $(this).val();
        var fileName = filePath.split('\\').pop(); // 파일 경로에서 파일 이름만 추출
        $('#fileName').text(fileName); // 파일 이름을 표시
    });
});

</script>

<div id="boxLine">
	<div>
		<span id="nicname"></span>
	</div>
	
	
	<form action = "profileUpdate.do" method="post" enctype="multipart/form-data" enctype="multipart/form-data">	
		<div class="box" id="myphoto" style="background: #BDBDBD;">
			<img src="/noramore/resources/images/photofile.png" id="photo" class="box" alt="사진을 드래그 드롭하세요."><br>				
		</div>	
		<input type="hidden" name="memberID" value="${ loginMember.memberID }">
		
		<div align="left" style="position: relative; overflow: hidden;">		
			<button type="button" id="customButton">프로필이미지 수정</button>
		<input type="file" name="photoFile" id="photofile" style="position: absolute; top: 0; right: 0; margin: 0; opacity: 0; font-size: 100px; cursor: pointer;">
		</div>
		<div id="fileName"></div>
	</form>
		

	<hr id="hr1">
	
	<div id="profileBtn" class="moveBtn">
		<c:url var="memberDetail" value="myinfo.do"><!-- url변수를 만듦, 연결할 대상 컨트롤러 매핑값 -->		
			<c:param name="memberID" value="${ loginMember.memberID }"></c:param>
		</c:url>
		<a href="${ memberDetail }">내 프로필</a>
	</div>
	
	<div id="gradeBtn" class="moveBtn">
		<c:url var="grade" value="grade.do"><!-- url변수를 만듦, 연결할 대상 컨트롤러 매핑값 -->		
			<c:param name="memberID" value="${ loginMember.memberID }"></c:param>
		</c:url>
		<a href="${ grade }">나의 등급</a>
	</div>
	
	<div id="acticleBtn" class="moveBtn">
		<c:url var="moveRecrBoard" value="selectRecrBoadMemberId.do"><!-- url변수를 만듦, 연결할 대상 컨트롤러 매핑값 -->		
			<c:param name="memberID" value="${ loginMember.memberID }"></c:param>
		</c:url>
		<a href="${ moveRecrBoard }">활동기록</a>
		
	</div>
	
	<div id="resign">
		<c:url var="resign" value="moveResign.do"><!-- url변수를 만듦, 연결할 대상 컨트롤러 매핑값 -->		
			<c:param name="memberID" value="${ loginMember.memberID }"></c:param>
		</c:url>
		<a href="${ resign }">회원탈퇴</a>
		
	</div>

</div>


</body>

<script type="text/javascript">





$(document).ready(function() {
    // 'photofile' input 요소에 대한 참조를 가져옵니다.
    $('#photofile').change(function() {
        // 사용자가 파일을 선택했는지 확인합니다.
        if(this.files.length > 0) {
            // 파일이 선택되면, 폼을 제출합니다.
            $(this.form).submit();
        }
    });
});




	 
	
	<%-- window.onload = function(){
		//사용 가능한 아이디인지 확인하는 함수 : ajax 기술 사용해야 함
		
		 <% var memberid = "${loginMember.memberID}"; %> 
		
		$.ajax({  
			url: "gradeImage.do",
			type: "post",
			data: { memberid: $('memberid').val() },
			success: function(data){ 
				console.log("success : " + data);
				
				if(data =="sprout"){
					$('#grade').html('<img src="resources/images/sprout.png" alt="sprout">');
				
				}
				 if(data == "grass"){   
					 $('#grade').html('<img src="resources/images/grass.png" alt="sprout">'); 
						
				}
				if(data == "flower"){
					$('#grade').html('<img src="resources/images/flower.png" alt="sprout">'); 
				}
				if(data == "tree"){
					$('#grade').html('<img src="resources/images/tree.png" alt="sprout">'); 
				
				}
				if(data == "forest"){
					$('#grade').html('<img src="resources/images/forest.png" alt="sprout">'); 
				}
			},
			error: function(jqXHR, textStatus, errorThrown){
				console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
			}
		});
		return false;
	}

 --%>

</script>


</html>