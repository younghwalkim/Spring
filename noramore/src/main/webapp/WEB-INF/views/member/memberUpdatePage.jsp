<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>(회원)정보 수정 페이지</title>
<link rel="stylesheet" type="text/css" href="resources/css/memberUpdatePage.css" />


</head>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
               
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;

                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
</script>

<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/jquery-3.7.0.min.js"></script> <!--  절대경로를 el로 처리함 -->
<script type="text/javascript">

	 



function dupNicnameCheck(){
	//사용 가능한 닉네임인지 확인하는 함수 : ajax 기술 사용해야 함
	$.ajax({  
		url: "nicnamechk.do",
		type: "post",
		data: { memberNicname: $('#membernicname').val() },
		success: function(data){ 
			console.log("success : " + data);
			
			if(data =="null"){
				alert("닉네임을 입력해주세요.");
				$('#membernicname').select();
				$('#save').attr("disabled", true); 	
			
			}
			 if(data == "ok"){   
					alert("사용 가능한 닉네임입니다.");
					$('#membernicname').attr("readonly", true); 
					$('#save').attr("disabled", false); 
			
			}
			if(data == "dup"){
				alert("");
				$('#membernicname').select();
				$('#save').attr("disabled", true); 
			
			}
			
		
			
		},
		error: function(jqXHR, textStatus, errorThrown){
			console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
		}
	});
	return false;
	
}



</script>



<script type="text/javascript">


	//닉네임 제출 클릭시 중복 막음
	$(document).ready(function() {
	$('#save').on('submit', function(e) {
	    e.preventDefault(); // 폼 기본 제출 동작을 막습니다.
	    
	    var memberNicname = $('#membernicname').val(); 
	    
	    // AJAX 요청
	    $.ajax({
	        url: 'nicnamechk.do', // 중복 검사를 처리할 서버의 URL
	        type: 'POST',
	        data: { memberNicname: memberNicname }, // 서버로 보낼 데이터
	        success: function(data) {
	            // 여기서 data는 서버에서 보낸 응답입니다.
	            // 서버 응답 예: {isDuplicated: true} or {isDuplicated: false}
	            if(data == "dup") {
	                alert('이미 사용 중인 닉네임입니다.'); // 중복 알림
	                consol.log("dup : " + data)
	            } else {
	                // ID 중복이 아닌 경우, 폼 제출을 진행합니다.
	                // 이를 위해 폼의 제출 이벤트를 다시 트리거하거나,
	                // 폼 데이터를 AJAX로 전송할 수 있습니다.
	               $('#save')[0].submit();// 폼 제출
	            }
	        },
	        error: function(request, status, error) {
	            // 오류 처리
	            alert('오류가 발생했습니다. 다시 시도해주세요.');
	        }
	        
	    });
	    
	});
	return false;

});
	
</script>











<body>

<c:import url="/WEB-INF/views/common/header.jsp" />

<c:import url="/WEB-INF/views/member/mypageSidebar.jsp" /> 

<form action="memberUpdate.do" method="post" id="form"  onsubmit="return validate();" >

	<input type="hidden" name="originPwd" value="${ member.memberPWD }"> 

<h1 id="h1">회원정보 수정</h1>

	<div id="info">
	등록된 회원정보는 아래와 같습니다.<br> 
	수정할 내용이 있으면 변경하고 수정하기 버튼을 누르세요.<br>
	암호를 입력해야 수정가능합니다.(변경사항 없을 시 기존 암호입력)
	</div>
	
    <div id="idDiv">
	    <span>아이디</span>
		<input type="text" name="memberID" id="memberid" value="${ member.memberID }" readonly>  <!-- readonly: 수정못함 -->
	</div>
	<div id="name">
		<span>이름</span>
		<input type="text" name="memberName" id="membername" value="${ member.memberName }" readonly>
	</div>	
	<div id="nickname">
		<span >닉네임</span>
		<input type="text" name="memberNicname" id="membernicname" value="${ member.memberNicname }">
	</div >
	<input type="button" id="dupchk" value="중복체크" onclick="return dupNicnameCheck();">
	
	<div id="birth">
		<h3 class="list">생년월일</h3>
		<span>
			<input type="date" name="birth" id="birth"  required></span>
	</div>
	
	<br>
	
	
	
	<div id="pw">
		<span>비밀번호</span>
		<input type="password" name="memberPWD" id= "memberpwd" required>
	</div>
	<div class="strongPassword-message hide">8글자 이상, 첫글자 영대문자, 소문자와 숫자 그리고 특수문자(@$!%*#?&)를 사용하세요</div>
	
	<div id="pwchk">
		<span >비밀번호 재확인</span>
		<input type="password" id= "memberpwd2">  
	</div> 
	<div class="mismatch-message hide">비밀번호가 일치하지 않습니다</div>          
	                        
	<div id="gender">                                 
		<span>성별</span>

		<c:if test="${ member.gender eq 'M' }">
		<input type="radio" name="gender" value="M" checked> 남자 &nbsp;
		<input type="radio" name="gender" value="F"> 여자
		</c:if>
	
		<c:if test="${ member.gender eq 'F' }">
		<input type="radio" name="gender" value="M"> 남자 &nbsp;
		<input type="radio" name="gender" value="F" checked> 여자
	
		</c:if>
	</div> 

	<div id="email">
	<span>이메일</span>
	<input type="email" name="email"  id="email" value="${ member.email }">
	</div>
	
	<div id="address">
	   <h3 class="list">자택주소</h3>
	   <input type="text" name="address" value="${ member.address }" readonly>
	   <div>수정할 자택 주소를 아래에 입력해 주세요.</div>
	   <div id="address" class="int_id">
	       <span>
		       <input type="text" id="sample4_postcode" class="d_form mini line addressCheck" placeholder="우편번호" readonly>
		       <input type="button" id="addressButton" class="d_form mini" onclick="sample4_execDaumPostcode()" value="우편번호 찾기" readonly>
	       </span>
	       <br>
			<input type="text" id="sample4_roadAddress" name="road" placeholder="도로명주소">
			<input type="text" id="sample4_jibunAddress" name="street" placeholder="지번주소">
			<span id="guide" style="color:#999;display:none"></span><br>
			<input type="text" id="sample4_detailAddress" name="detail" placeholder="상세주소" autocomplete="off">
			<input type="text" id="sample4_extraAddress" name="ref" placeholder="참고항목" autocomplete="off">
			       
    	</div>
	</div>
			수정하기 전에 닉네임 중복체크를 해주세요!
		<input type="submit" id="save" disabled = "disabled" value="저장하기" > &nbsp;
	<a href="javascript:history.go(-1);" id="back">이전 페이지로 이동</a> &nbsp;



</form>



<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript">
function validate(){
	var pwdValue = $('#memberpwd').val(); 
	var pwdValue2 = $('#memberpwd2').val();
	var nicnameValue= $('#membernicname').val(); 

	
	
	if(pwdValue !== pwdValue2){   // == : 값만 일치하는지, === : 값과 자료형이 일치하는지
		alert("암호와 암호확인이 일치하지 않습니다. 다시 입력하세요.");
		document.getElementById("memberpwd2").value = "";  // 두 번째 비밀번호 필드의 값을 비웁니다.
		document.getElementById("memberpwd").select();  // 첫 번째 비밀번호 필드를 선택합니다.
		return false;  //전송 취소함
	}
	
	if (!/^[A-Z][a-z\d]{5,11}[!@#]$/.test(pwdValue)) {
	    alert("비밀번호 : 첫글자는 영문 대문자, 마지막에 특수문자 넣어주세요. 6~12자 입력할 것!");
	    document.getElementById("memberpwd").value = "";
	    document.getElementById("memberpwd2").value = "";
		document.getElementById("memberpwd").select();
	    return false;
	}
	

		//닉네임
		/* if(!/^[가-힝]{2,}$/.test(nicnameValue)){ */
		if(!/^[가-힝a-zA-Z]{2,}$/.test(nicnameValue)){
			alert("닉네임 : 2글자 이상을 넣으세요");
			document.getElementById("membernicname").value = "";  
			document.getElementById("membernicname").select(); 
			return false;
		}
		
		
		

	  if(birthValue == null){   
			alert("생일을 지정해 주세요!");
		
			document.getElementById("birth").select();  
			return false; 
		}
	

	return true;
}
</script>









<script type="text/javascript">
//1. 비밀번호 입력창 정보 가져오기
let elInputPassword = document.querySelector('#memberpwd'); // input#password
//2. 비밀번호 확인 입력창 정보 가져오기
let elInputPasswordRetype = document.querySelector('#memberpwd2'); // input#password-retype
//3. 실패 메시지 정보 가져오기 (비밀번호 불일치)
let elMismatchMessage = document.querySelector('.mismatch-message'); // div.mismatch-message.hide
//4. 실패 메시지 정보 가져오기 (8글자 이상, 영문, 숫자, 특수문자 미사용)
let elStrongPasswordMessage = document.querySelector('.strongPassword-message'); // div.strongPassword-message.hide

function strongPassword (str) {
	  return /^[A-Z](?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/.test(str);
	}
	
function isMatch (password1, password2) {
	  return password1 === password2;
	}
	
	

		
	elInputPassword.onkeyup = function () {

		  // console.log(elInputPassword.value);
		  // 값을 입력한 경우
		  if (elInputPassword.value.length !== 0) {
		    if(strongPassword(elInputPassword.value)) {
		      elStrongPasswordMessage.classList.add('hide'); // 실패 메시지가 가려져야 함
		    }
		    else {
		      elStrongPasswordMessage.classList.remove('hide'); // 실패 메시지가 보여야 함
		    }
		  }
		  // 값을 입력하지 않은 경우 (지웠을 때)
		  // 모든 메시지를 가린다.
		  else {
		    elStrongPasswordMessage.classList.add('hide');
		  }
		};
		
		
	elInputPasswordRetype.onkeyup = function () {

		  // console.log(elInputPasswordRetype.value);
		  if (elInputPasswordRetype.value.length !== 0) {
		    if(isMatch(elInputPassword.value, elInputPasswordRetype.value)) {
		      elMismatchMessage.classList.add('hide'); // 실패 메시지가 가려져야 함
		    }
		    else {
		      elMismatchMessage.classList.remove('hide'); // 실패 메시지가 보여야 함
		    }
		  }
		  else {
		    elMismatchMessage.classList.add('hide'); // 실패 메시지가 가려져야 함
		  }
		};
		
</script>

</body>
</html>