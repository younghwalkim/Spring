<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입페이지</title>

<link rel="stylesheet" type="text/css" href="resources/css/enrollPage.css" />



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
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
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

	 


function validate(){
	
	var idValue = $('#memberid').val();
	var pwdValue = $('#memberpwd').val();
	var pwdValue2 = $('#memberpwd2').val();
	var nicnameValue= $('#memberNicname').val(); 
	var nameValue = $('#membername').val();
	var birthValue = $('#birth').val();


	
	
	if(!$("#agreement2").prop("checked")) {
		$("label[for=agreement2]").text("동의 해주시기 바랍니다.");
		$("label[for=agreement2]").css("color","red");
		return false;
	}
	
	
	if(!$("#agreement1").prop("checked")) {
		$("label[for=agreement1]").text("동의 해주시기 바랍니다.");
		$("label[for=agreement1]").css("color","red");
		return false;
	}


	
	//아이디
	  if (!/^[A-Za-z0-9][a-z\d]{5,50}$/.test(idValue)){
	    document.getElementById("memberid").value = "";
		document.getElementById("memberid").select();
	    return false;
	} 
	
	
	  if(birthValue == null){   
			alert("생일을 지정해 주세요!");
		
			document.getElementById("birth").select();  
			return false; 
		}
	
	
	
	
	
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
	if(!/^[가-힝a-zA-Z]{2,}$/.test(nicnameValue)){
		alert("닉네임 : 2글자 이상을 넣으세요");
		document.getElementById("memberNicname").value = "";  
		document.getElementById("memberNicname").select(); 
		return false;
	}
	
	

	//이름
	if(!/^[가-힝]{2,}$/.test(nameValue)){
		alert("성명 : 한글로 2글자 이상을 넣으세요");
		document.getElementById("membername").value = "";  
		document.getElementById("membername").select();
		return false;
	}
	
	

	//아이디의 값 형식이 요구한 대로 작성되었는지 검사
	//암호의 값 형식이 요구한 대로 작성되었는지 검사
	//정규표현식(Regular Expression) 사용함	
	return true;  //전송보냄
}
 


	
	

function dupIDCheck(){
	//사용 가능한 아이디인지 확인하는 함수 : ajax 기술 사용해야 함
	$.ajax({  
		url: "idchk.do",
		type: "post",
		data: { memberID: $('#memberid').val() },
		success: function(data){ 
			console.log("success : " + data);
			
			if(data =="null"){
				alert("아이디를 입력해주세요.");
				$('#memberid').select();
				$('#registerBtn').attr("disabled", true); 	
			
			}
			if(data == "ok"){   
					alert("사용 가능한 아이디입니다.");
					 $('#memberpwd').focus();
				     $('#memberid').attr("readonly", true); 
					 $('#registerBtn').attr("disabled", false); 
			}
			if(data == "dup"){
				alert("이미 사용중인 아이디입니다.");
				$('#memberid').select();
				$('#registerBtn').attr("disabled", true); 
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

 
function dupNicnameCheck(){
	//사용 가능한 닉네임인지 확인하는 함수 : ajax 기술 사용해야 함
	$.ajax({  
		url: "nicnamechk.do",
		type: "post",
		data: { memberNicname: $('#memberNicname').val() },
		success: function(data){ 
			console.log("success : " + data);
			
			if(data =="null"){
				alert("닉네임을 입력해주세요.");
				$('#nicname').select();
				$('#registerBtn').attr("disabled", true); 	
			
			}
			 if(data == "ok"){   
					alert("사용 가능한 닉네임입니다.");
					$('#nicname').attr("readonly", true); 
					$('#registerBtn').attr("disabled", false); 
			
			}
			if(data == "dup"){
				alert("이미 사용중인 닉네임입니다.");
				$('#nicname').select();
				$('#registerBtn').attr("disabled", true); 
			
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

 

 
 
 
 
 
 
 window.onload = function() {
		
	 var message = "${message}";
	 if(message != ""){
	 alert(message);
	 }
}; 
 
 
 

 $(() => {
	   const urlParams = new URLSearchParams(window.location.search);
	    const id = urlParams.get('id');
	    const email = urlParams.get('email');
	    const signType = urlParams.get('signType');
	    
	    console.log(id);
	    console.log(email);

	    
	    if (!!id) {
	        $("#userId").prop('readonly', true);
	    }
	    if (!!email) {
	        $("#email").prop('readonly', true);
	    }
 
	}); //doc ready;
	
</script>


<!-- <script type="text/javascript">
//아이디 중복 될시 제출막음
$(document).ready(function() {
    $('#limit').on('submit', function(e) {
        e.preventDefault(); // 폼 기본 제출 동작을 막습니다.
        
        var memberID = $('#memberid').val(); 
        
        // AJAX 요청
        $.ajax({
            url: 'idchk.do', // 중복 검사를 처리할 서버의 URL
            type: 'POST',
            data: { memberID: memberID }, // 서버로 보낼 데이터
            success: function(data) {
                // 여기서 data는 서버에서 보낸 응답입니다.
                // 서버 응답 예: {isDuplicated: true} or {isDuplicated: false}
                if(data == "dup") {
                    alert('이미 사용 중인 ID입니다.'); // 중복 알림
                } else {
                    // ID 중복이 아닌 경우, 폼 제출을 진행합니다.
                    // 이를 위해 폼의 제출 이벤트를 다시 트리거하거나,
                    // 폼 데이터를 AJAX로 전송할 수 있습니다.
                   $('#limit')[0].submit();// 폼 제출
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





<script type="text/javascript">
//닉네임 제출 클릭시 중복 막음
$(document).ready(function() {
    $('#limit').on('submit', function(e) {
        e.preventDefault(); // 폼 기본 제출 동작을 막습니다. 
        
        var memberNicname = $('#memberNicname').val(); 
        
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
                } else {
                    // ID 중복이 아닌 경우, 폼 제출을 진행합니다.
                    // 이를 위해 폼의 제출 이벤트를 다시 트리거하거나, 
                    // 폼 데이터를 AJAX로 전송할 수 있습니다.
                   $('#limit')[0].submit();// 폼 제출
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

</script> -->




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

$("input:checkbox").click(checkedChange);
function checkedChange() {
    if($(this).prop("checked")){
        $("label[for="+this.id+"]").text("동의되었습니다.");
        $("label[for="+this.id+"]").css("color","blue");
    }else{
        $("label[for="+this.id+"]").text("동의 해주시기 바랍니다.");
        $("label[for="+this.id+"]").css("color","red");
    }
}


</script>


</head>
<body>

<div id="entire" class="container">
<h1 align="center">회원가입</h1>
<br>
<!-- 사진파일 첨부시 enctype="multipart/form-data" 속성 추가함 -->
<form action="enroll.do" id="limit" class="enrollForm" method="post" enctype="multipart/form-data" onsubmit="return validate();">  <!--  -->
<!-- form에는 submit버튼 1개만 만들수 있음 --> <!--  return을 붙여야 이 값을 보낼지 말지 가능함. -->

	<h5>회원 정보를 입력해 주세요. (* 표시는 필수입력 항목입니다.)</h5>
	

	
	
	
	
	<div>
	<h3 class="list">*아이디<span id="idError"></span></h3>
		<span class="box int_id" >
		 <c:if test="${ !empty param.id }">
				<input type="text" name="memberID" id="memberid" class="input" autocomplete="off" value="${ param.id }" >
			</c:if> 
			
			<c:if test="${ empty param.id }">
				<input type="text" name="memberID" id="memberid" autocomplete="off" class="input" required>
			</c:if>
		</span>  <!-- name은 vo의 필드값과 같아야 함 --> <!-- required : 필수항목 -->	
		<input type="button" value="중복체크" onclick="return dupIDCheck();">
		<div class="success-message hide"></div>
    	<div class="failure-message hide">아이디는 6글자이상이어야 합니다</div>
    	<div class="failure-message2 hide">영어 또는 숫자만 가능합니다</div>
	</div>
	
		
	<div>
		<h3 class="list">*비밀번호<span id="pwError" ></span></h3>
		<span class="box int_id">
			<input type="password" name="memberPWD" id="memberpwd" class="input" autocomplete="off" maxlength="20" required>
		</span>
	</div>
	<div class="strongPassword-message hide">8글자 이상, 첫글자 영대문자, 소문자와 숫자 그리고 특수문자(@$!%*#?&)를 사용하세요</div>
	
	<br>
	
	<div>
		<h3 class="list">*비밀번호 재확인<span id="pwCheckError"></span></h3>
		<span class="box int_id">
			<input type="password" id="memberpwd2" class="input" maxlength="20" autocomplete="off" required><br>
		</span>	
		<div class="mismatch-message hide">비밀번호가 일치하지 않습니다</div>
	</div>
	
	<div>
		<h3 class="list">*닉네임<span id="nameError"></span></h3>
		<span class="box int_id">
			<input type="text" name="memberNicname" id="memberNicname" class="input" autocomplete="off" maxlength="20">
		</span>
		<input type="button" value="중복체크" onclick="return dupNicnameCheck();">
	</div>
	
	

	
	
 <div id="myphoto" 
			style="margin:0;width:150px;height:160px;padding:0;border:1px solid navy;">
				<img src="/noramore/resources/images/photofile.png" id="photo" 				
				style="width:150px;height:160px;border:1px solid navy;display:block;"
				alt="사진을 드래그 드롭하세요." 
				style="padding:0;margin:0;"><br>				
			</div>	
	
	<div align="right"><input type="file" name="photoFile" id="photofile" value=""></div>

	
	<div>
		<h3 class="list">*성명<span id="nameError"></span></h3>
		<span class="box int_id">
			<input type="text" name="memberName" id="membername" class="input" autocomplete="off" maxlength="20" required><br>
		</span>
	</div>
	
	<div>
		<h3 class="list">*생년월일<span id="birthError"></span></h3>
		<span class="box int_id">
			<input type="date" name="birth" id="birth" class="input" required></span>
	</div>
	
	
	
	<div>
		<h3 class="list">*성별</h3>
		<span class="box int_id">
			<input type="radio" name="gender" value="M" id="m" checked > 남자 &nbsp; 
			<input type="radio" name="gender" value="F" id="f" > 여자 
		</span>
	</div>
	<br>
     
     <div class="form-group">
	     <div>
	     	  <c:if test="${ !empty param.email }">
	     		<input class="form-control" placeholder="이메일을 입력해주세요." name="email" value="${param.email}" id="email" type="email" autocomplete="off">
	     	</c:if> 
	     	
	  		<c:if test="${ empty param.email }">
	     		<input class="form-control" placeholder="이메일을 입력해주세요." name="email" id="email" type="email" autocomplete="off">
	     	</c:if> 
	      		<input type="button" value="인증번호 받기" class="btn btn-primary" id="emailAuth">

	    	<div>
	  			<input class="form-control" placeholder="인증 코드 6자리" maxlength="6" disabled="disabled" name="authCode" id="authCode" type="text" autofocus>
	  	
	          	<label for="Timer">남은 시간:</label>
 				<input id="Timer" type="text" value="" width= "20px" readonly/>
	          <button class="complete__target" id="complete" disabled="disabled" >인증완료</button>
	     	</div>
	     </div> 
	      
  		<div id="emailAuthWarn"></div>
	</div>


	
	
	
	<div>
	   <h3 class="list">*자택주소<span id="addressError"></span></h3>
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
    
	<br><br>
	<!-- 개인정보 수집 동의 -->
    <fieldset class="fieldarea f2">
	<legend><span>*이용</span>약관</legend>
	<p class="agreeText">
		<label for="agreement1">아래 사항에 동의 합니다.</label>
		<input id="agreement1" type="checkbox" name="agreement1"/>
		<textarea id="text1" readonly>
			이용약관
		</textarea>
	</p>
	</fieldset>
	<fieldset class="fieldarea f3">
		<legend><span>*개인정보</span>취급방침</legend>
		<p class="agreeText">
			<label for="agreement2">아래 사항에 동의 합니다.</label>
			<input id="agreement2" type="checkbox" name="agreement2"/>
			<textarea id="text2" readonly>
				개인정보 방침 및 안내
			</textarea>
		</p>
	</fieldset>

   
   <div >
   		<c:if test="${ !empty param.signType }">
   		<input type="text" id="signType" name="signType" value="${ param.signType }" class="hide">
   		</c:if>
   		<c:if test="${ empty param.signType }">
   		<input type="text" id="signType" name="signType" value="direct" class="hide" >
   		</c:if>
   </div>
      
      


<br><br>
<input type="submit"  value="가입하기"  disabled = "disabled" class="btn btn-lg btn-success btn-block" id="registerBtn"> &nbsp;
<!-- id="submit_button" -->
<input type="reset" value="작성취소"> &nbsp;
<a href="home.do">시작페이지로 이동</a>


</form>


<script type="text/javascript">


var codeNum = null;


//인증하기 버튼을 눌렀을 때 동작
$("#emailAuth").click(function() {
	const email = $("#email").val(); //사용자가 입력한 이메일 값 얻어오기
		
	//Ajax로 전송
    $.ajax({
    	url : 'emailAuth.do',
    	data : {
    		email : email
    	},
    	type : 'POST',
    	dataType : 'json',
    	success : function(result) {
    		console.log("result : " + result);
    		
    		var strCode = JSON.stringify(result);
    		
    		var json = JSON.parse(strCode);
    		
    		codeNum = json.code; 
    		
    		
    		$("#authCode").attr("disabled", false); 
    		
    		/*  $("#authCode").prop("disabled", false); */
    	
    		alert("인증 코드가 입력하신 이메일로 전송 되었습니다.");
    		
    		
   		}
    }); //End Ajax
});


//인증 코드 비교
$("#authCode").on("keyup", function() {
	
	var inputCode = $("#authCode").val(); //인증번호 입력 칸에 작성한 내용 가져오기
	
	console.log("입력코드 : " + inputCode);
	console.log("인증코드 : " + codeNum);
		
	
	
	if(inputCode == codeNum){
    	$("#emailAuthWarn").html('인증번호가 일치합니다.');
    	$("#emailAuthWarn").css('color', 'green');
		$('#emailAuth').attr('disabled', true);  //인증하기 버튼 비활성화
		$('#email').attr('readonly', true);  //이메일 읽기전용으로 변환
		$("#complete").attr("disabled", false); 
		

	}else{
    	$("#emailAuthWarn").html('인증번호가 불일치 합니다. 다시 확인해주세요!');
    	$("#emailAuthWarn").css('color', 'red');
    	$("#registerBtn").attr("disabled", true);
    	$("#complete").attr("disabled", true); 
    
	}
	
	return false;
});

//인증완료 클릭 시 
$("#complete").on("click", function() {
	var inputCode = $("#authCode").val();
	
	if(inputCode == codeNum){
		$("#emailAuthWarn").html('인증이 완료되었습니다.');
		$("#emailAuthWarn").css('color', 'green');
		$("#registerBtn").attr("disabled", false); 
		$("#complete").attr("disabled", true); 
		 clearInterval(PlAYTIME);

	}else{
		$("#emailAuthWarn").html('인증번호가 불일치 합니다. 다시 확인해주세요!');
		$("#emailAuthWarn").css('color', 'red');
		$("#emailAuth").attr("disabled", false); 
		
	
	}
	
	return false;
});


</script>


<script type="text/javascript">

//-------------------------타이머-----



let time = 180000;
let min = 3;
let sec = 60;
let PlAYTIME;

Timer.value = min + ":" + '00';
$("#emailAuth").on("click", function () {
    time = 180000; // 클릭할 때마다 타이머 초기화
    min = 3;
    sec = 60;
    clearInterval(PlAYTIME); // 기존 타이머 정지
    PlAYTIME = setInterval(function () {
        time = time - 1000; // 1초씩 줄어듦
        min = time / (60 * 1000); // 초를 분으로 나눠준다.

        if (time <= 0) {
            clearInterval(PlAYTIME); // 타이머 정지
            Timer.value = "만료되었습니다";
        } else {
            if (sec > 0) { // sec=60 에서 1씩 빼서 출력해준다.
                sec = sec - 1;
                Timer.value = Math.floor(min) + ':' + sec; // 실수로 계산되기 때문에 소숫점 아래를 버리고 출력해준다.
            }
            if (sec === 0) {
                // 0에서 -1을 하면 -59가 출력된다.
                // 그래서 0이 되면 바로 sec을 60으로 돌려주고 value에는 0을 출력하도록 해준다.
                sec = 60;
                Timer.value = Math.floor(min) + ':' + '00'
            }
        }

    }, 1000); // 1초마다 
});
</script>
</div>


<script type="text/javascript">

//1. 아이디 입력창 정보 가져오기
let elInputUsername = document.querySelector('#memberid'); // input#username
//2. 성공 메시지 정보 가져오기
let elSuccessMessage = document.querySelector('.success-message'); // div.success-message.hide
//3. 실패 메시지 정보 가져오기 (글자수 제한 4~12글자)
let elFailureMessage = document.querySelector('.failure-message'); // div.failure-message.hide
//4. 실패 메시지2 정보 가져오기 (영어 또는 숫자)
let elFailureMessageTwo = document.querySelector('.failure-message2'); // div.failure-message2.hide


//1. 비밀번호 입력창 정보 가져오기
let elInputPassword = document.querySelector('#memberpwd'); // input#password
//2. 비밀번호 확인 입력창 정보 가져오기
let elInputPasswordRetype = document.querySelector('#memberpwd2'); // input#password-retype
//3. 실패 메시지 정보 가져오기 (비밀번호 불일치)
let elMismatchMessage = document.querySelector('.mismatch-message'); // div.mismatch-message.hide
//4. 실패 메시지 정보 가져오기 (8글자 이상, 영문, 숫자, 특수문자 미사용)
let elStrongPasswordMessage = document.querySelector('.strongPassword-message'); // div.strongPassword-message.hide


function idLength(value) {
	  return value.length >= 6 && value.length <= 50
	}


function onlyNumberAndEnglish(str) {
	  return /^[A-Za-z0-9][A-Za-z0-9]*$/.test(str);
	}
	

function strongPassword (str) {
	  return /^[A-Z](?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/.test(str);
	}
	
function isMatch (password1, password2) {
	  return password1 === password2;
	}
	
elInputUsername.addEventListener('keyup', function(){
//elInputUsername.onkeyup = function () {
	console.log("keyup");
	  // 값을 입력한 경우
	  if (elInputUsername.value.length !== 0) {
	    // 영어 또는 숫자 외의 값을 입력했을 경우
	    if(onlyNumberAndEnglish(elInputUsername.value) === false) {
	      elSuccessMessage.classList.add('hide');
	      elFailureMessage.classList.add('hide');
	      elFailureMessageTwo.classList.remove('hide'); // 영어 또는 숫자만 가능합니다
	    }
	    // 글자 수가 6~50글자가 아닐 경우
	    else if(idLength(elInputUsername.value) === false) {
	      elSuccessMessage.classList.add('hide'); // 성공 메시지가 가려져야 함
	      elFailureMessage.classList.remove('hide'); // 아이디는 4~12글자이어야 합니다
	      elFailureMessageTwo.classList.add('hide'); // 실패 메시지2가 가려져야 함
	    }
	    // 조건을 모두 만족할 경우
	    else if(idLength(elInputUsername.value) || onlyNumberAndEnglish(elInputUsername.value)) {
	      elSuccessMessage.classList.remove('hide'); // 사용할 수 있는 아이디입니다
	      elFailureMessage.classList.add('hide'); // 실패 메시지가 가려져야 함
	      elFailureMessageTwo.classList.add('hide'); // 실패 메시지2가 가려져야 함
	    }
	  }
	  // 값을 입력하지 않은 경우 (지웠을 때)
	  // 모든 메시지를 가린다.
	  else {
	    elSuccessMessage.classList.add('hide');
	    elFailureMessage.classList.add('hide');
	    elFailureMessageTwo.classList.add('hide');
	  }
	});
	
	
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