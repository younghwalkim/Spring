<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript" src="/getdrive/resources/js/jquery-3.7.0.min.js"></script>

<style type="text/css">
#logo {
	width: 190px;
	position: relative;
	left: 40px;
}
#logospace {
	margin: 10px;
	width: 180px;
	cursor: pointer;
	float: left;
}

.dropdown{
	margin: 20px;
	width: 180px;
	cursor: pointer;
	float: right;
	text-align: center;
	position: relative;
	right:30px;
}

.dropbtn{ 
  background-color: #FFFFFF;
  border : none;
  border-radius: 25px 25px 90px 500px / 25px 25px 0px 0px;
  color: #6DBFF2;
  font-weight: 400;
  padding : 12px;
  width :200px;
  cursor : pointer;
  font-size : 18px;
  font-weight: 600;
}

.dropdown-content{
  display : none;
  position : absolute;
  z-index : 1;
  font-weight: 400;
  background-color: #9BD1F2;
  min-width : 200px;
  border-radius: 90px 500px 25px 25px / 0px 0px 25px 25px;
}

.dropdown-content>#a1{
  display : block;
  text-decoration : none;
  color : white;
  font-size : 18px;
  padding : 0px 10px 10px 10px;
}

.dropdown-content>#a2{
  display : block;
  text-decoration : none;
  color : white;
  font-size : 18px;
  padding : 0px 10px 12px 10px;
  border-radius: 90px 500px 25px 25px / 0px 0px 25px 25px;
}

.dropdown-content a:hover{
  background-color : #C9E2F2;
}

.dropdown:hover .dropdown-content {
  display: block;
}

#user{
	width: 25px;
	position: relative;
	top: 5px;
}

#setting{
	width: 30px;
	position: relative;
	top: 8px;
}

#logout{
	width: 28px;
	position: relative;
	top: 7px;
}

.dropdown-content {
    display: none;
    opacity: 0; /* 초기에는 투명하게 설정 */
    transition: opacity 0.3s ease; /* opacity 변화에 대한 transition 효과 추가 */
}

.dropdown:hover .dropdown-content {
    display: block;
    opacity: 1; /* 드롭다운 메뉴가 활성화될 때 투명도를 조절하여 부드럽게 보이도록 함 */
}

</style>

<script type="text/javascript">
function moveAccountSetting(){
	location.href = "accountSettingPage.do";
}
</script>

<script src="/getdrive/resources/js/kakao.min.js"></script>
<script type="text/javascript">
//카카오로그아웃  
function kakaoLogout() {
	
	//발급받은 키 중 javascript키를 사용해준다.
	Kakao.init('780473bbd7b187c82f902ed7cb766777');
	console.log(Kakao.isInitialized()); 
	
	if (Kakao.Auth.getAccessToken()) {
		Kakao.API.request({
			url : '/v1/user/unlink',
			success : function(response) {
				console.log(response)
				
			},
			fail : function(error) {
				console.log(error);
			},
		})
		Kakao.Auth.setAccessToken(undefined);
	}
	
	location.href = "logout.do";
}
</script>

<!-- 드롭다운 부드럽게 -->
<script type="text/javascript">
$(function(){
    $(".dropdown").hover(function(){
        $(".dropdown-content", this).stop().slideDown(3000); // 드롭다운 메뉴가 천천히 내려오도록 300밀리초로 변경
    },
    function(){
        $(".dropdown-content", this).stop().slideUp(3000); // 드롭다운 메뉴가 천천히 올라가도록 300밀리초로 변경
    });
});
</script>

</head>
<body>
	
	<div id="logospace">
		<a href="${ pageContext.servletContext.contextPath }/mainPage.do">
		<img id="logo" alt="getdrive"src="/getdrive/resources/images/logo.png">
		</a>
	</div>
		
	<c:if test="${ !empty sessionScope.loginMember }">
		<div class="dropdown">
	      <button class="dropbtn"><img id="user" alt="user" src="/getdrive/resources/images/user.png">
	        <span class="dropbtn_icon">${ sessionScope.loginMember.name }</span>
	      </button>
	      
	      <div class="dropdown-content">
	        <a id="a1" onclick="moveAccountSetting();return;"><img id="setting" alt="setting" src="/getdrive/resources/images/setting.png">계정설정</a>
	        <a id="a2" onclick="kakaoLogout();return;"><img id="logout" alt="logout" src="/getdrive/resources/images/logout.png">로그아웃</a>
	      </div>
	    </div>
    </c:if>   
    
</body>

</html>