<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>(회원)정보 보기 페이지</title>
<link rel="stylesheet" type="text/css" href="resources/css/memberDetailPage.css" />
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.1.0/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/jquery-3.7.0.min.js"></script> <!--  절대경로를 el로 처리함 -->
</head>

<body>

<!-- <script type="text/javascript">
function showmodal(memberId){
	 
	 const modal = document.querySelector("#changeModal");
	 modal.style.display = "flex";
	 
	 $.ajax({
			url : "otherMember.do",  //DispatcherServlet로 보냄
			type : "post",     
			data: { memberId : memberId },  
			dataType : "json",
			success : function(data) {
				console.log("success : " + data);   //String 리턴되온것을 Object로 받음

				console.log("success11 : " + data.grade); 
				console.log("success11 : " + data.heart); 
				console.log("success11 : " + data.id); 
				console.log("success11 : " + data.photoFile); 
				
				
				values += data.grade + " " + data.heart + " " + data.id + " " + data.photoFile
				
				

				$('#context').html($('#context').html() + values);
				
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log("error : " + jqXHR + ", " + textStatus + ", "
						+ errorThrown);
			}
		}); //ajax

		
	 const canclebtn = document.querySelector("#canclebtn");
	 canclebtn.addEventListener('click', function (event) {
	     const modal = document.querySelector("#changeModal");
	     modal.style.display = "none";
	     $("#passwordinput").val(''); // 입력 값 초기화
	 });

	 document.getElementById("passwordinput").addEventListener("keypress", function(event) {
	     // 엔터 키가 눌렸는지 확인
	     if (event.key === "Enter") {
	         // 엔터 키의 기본 동작을 막습니다 (폼 제출 등) 
	         event.preventDefault();
	         // 버튼 클릭 이벤트를 발생시킵니다
	         document.getElementById("changebtn").click();
	     }
	 });
		
});
	 
	 
 
 
 
/* const the_other_person = document.querySelector("#the_other_person");
the_other_person.addEventListener('click', function (event) {
    const modal = document.querySelector("#changeModal");
    modal.style.display = "flex";
    
    
    
});  */




/* document.getElementById("changebtn").addEventListener("click", function() {
    if(validate()){ // validate 함수가 true를 반환하는 경우에만 폼 제출
        document.getElementById("passwordChangeForm").submit();
    }
}); */


</script>  -->
<c:import url="/WEB-INF/views/common/header.jsp" />
<c:import url="/WEB-INF/views/member/mypageSidebar.jsp" /> 


<div id="info_box">
 <form action="updatePage.do" method="post" id="passwordChangeForm" >
	<h1>내 프로필</h1>

	<input type="hidden" name="memberID" value="${ member.memberID }">
	<div class="form">
	<span class="title">아이디</span> <span class="input">${ member.memberID }</span> 
	</div>
	
	<div class="form">
		<span class="title">이름</span> <span class="input">${ member.memberName }</span>
	</div>
	
	<div class="form">
		<span class="title">닉네임</span> <span class="input">${ member.memberNicname }</span>
	</div>
	
	<div class="form">
		<span class="title">생일</span> <span class="input">${ member.birth }</span>
	</div>
	
	<div class="form">
		<span class="title">이메일</span> <span class="input">${ member.email }</span>
	</div>
	
	<div>
		<span class="title">주소</span> <span id="adress" class="input">${ member.address }</span>
	</div>
	
	<input type="submit" id="modifyBtn" value="수정하기">
	
	</form>
</div> 


<!-- <button id="otherID" onclick="showmodal('member005')"  >안녕ㅎㅎ</button>







	<div class="modal" id="changeModal" tabindex="-1" aria-labelledby="ChangeModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="changeModalLabel">회원정보</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	      
	      	  <div id="context">
	      	  		
	      	  </div>
	      
	          <div class="mb-3">
	            <label for="input" class="col-form-label"></label>
	            <input type="password" class="form-control" id="passwordinput" name="memebrPWD" autocomplete="off" placeholder="기존 비빌번호 입력" style="width: 160px;">
	          </div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" id="canclebtn" data-bs-dismiss="modal">취소</button>
	         <button type="button" class="btn btn-primary" id="changebtn">확인</button>
	      </div>
	    </div>
	  </div>
	</div>
	 


 -->


 




</body>



</html>