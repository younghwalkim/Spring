<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원관리 NoraMore : 나랑 함께 놀 사람~ 놀아!모아!</title>
    <script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/jquery-3.7.0.min.js"></script>
<c:import url="/WEB-INF/views/admin/adminSidebar.jsp" />
 <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
        }

        .container {
            width: 70%;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h2.title {
            text-align: center;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f2f2f2;
        }

        .search-form {
            margin-bottom: 20px;
            text-align: center;
        }

        .search-form input[type="search"], .search-form input[type="submit"], .search-form select {
            padding: 8px;
            border-radius: 4px;
            border: 1px solid #ccc;
            margin-right: 10px;
        }

        .search-form input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            cursor: pointer;
        }

        .search-form input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .action-buttons button {
            padding: 8px 12px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .action-buttons button.restrict {
            background-color: #ff9800;
            color: #fff;
        }

        .action-buttons button.restrict:hover {
            background-color: #f57c00;
        }

        .action-buttons button.rollback {
            background-color: #4caf50;
            color: #fff;
        }

        .action-buttons button.rollback:hover {
            background-color: #388e3c;
        }
        .listRollback, .form{
        display: inline-block;
            vertical-align: middle;
        }
        
        .listRollback {
		  background-color: #E6E6E6; /* 기본 배경색 */
		  color: black; /* 기본 텍스트 색상 */
		  margin-top: 10px;
		  margin-bottom: 5px;
		  padding: 5px 20px; /* 내부 여백 */
		  font-size: 15px; /* 텍스트 크기 */
		  border: none; /* 테두리 없음 */
		  border-radius: 5px; /* 테두리 반경 */
		  cursor: pointer; /* 포인터 커서 */
		  text-decoration: none; /* 텍스트에 밑줄 제거 */
		  font-weight: bold;
		}
    </style>
    <script type="text/javascript">
 function updateRestrict(memberID) {
     $.ajax({
         type: 'POST',
         url: 'restrict.do',
         data: { memberID: memberID, restrict : "1" },
         success: function(response) {
        	 if(response == "restrict"){
      			alert( "회원의 로그인을 제한합니다." );
      			location.reload();
      		 }else if(response == "failed"){
      			 alert( "회원 활동 제한 설정 에러");
     	 	 };
         },
         error: function(xhr, status, error) {
             console.error("에러 : ", error);
         }
     });
 }
 function rollbackRestrict(memberID) {
     $.ajax({
         type: 'POST',
         url: 'restrictrollback.do',
         data: { memberID: memberID, restrict : "0" },
         success: function(response) {
           	 if(response == "rollback"){
           		alert( "회원의 로그인 제한이 해제되었습니다." );
           		location.reload();
           	 }else if(response == "failed"){
           		 alert( "회원 활동 제한 설정 에러");
           	 };
         },
         error: function(xhr, status, error) {
             console.error("에러 : ", error);
         }
     });
 }

</script>
</head>
<body>
<br><br>
<div class="container">
    <h2 class="title">전체 회원</h2>
    <div class="search-form">
    	<a class="listRollback" href="${ pageContext.servletContext.contextPath }/memberlist.do">전체 목록</a>&nbsp; &nbsp;
        <form class="form" action="memSearch.do" >
           	<select name="action">
                <option value="id">아이디</option>
                <option value="gender">성별</option>
                <option value="enrolldate">가입일자</option>
            </select>
            <input type="search" name="keyword" id="memsearch" placeholder="검색할 회원 정보를 입력하세요">
            <input type="submit" class="searchMem" value="검 색">
            <select name="limit">
                <option value="10" selected>10</option>
                <option value="15">15</option>
                <option value="20">20</option>
            </select>
        </form>
    </div>
    <table>
        <thead>
        <tr>
            <th>등 급</th>
            <th>회원아이디</th>
            <th>닉네임</th>
            <th>성 별</th>
            <th>이메일</th>
            <th>가입일</th>
            <th>하 트</th>
            <th>활동 건수</th>
            <th>상 태</th>
            <th>관 리</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.list}" var="a">
            <tr>
                <td>${a.grade}</td>
                <td>${a.memberID}</td>
                <td>${a.memberNicname}</td>
                <td>${a.gender}</td>
                <td>${a.email}</td>
                <td>${a.registDate}</td>
                <td>${a.heartBeat}</td>
                <td>${a.articleCount}</td>
                <td>
                    <c:choose>
                        <c:when test="${a.actLimit eq 0}">
                            일 반
                        </c:when>
                        <c:otherwise>
                            로그인 제한
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="action-buttons">
                    <c:if test="${a.actLimit eq 0}">
                        <button class="restrict" onclick="updateRestrict('${a.memberID}');">로그인 제한</button>
                    </c:if>
                    <c:if test="${a.actLimit eq 1}">
                        <button class="rollback" onclick="rollbackRestrict('${a.memberID}');">일반 전환</button>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<br>
<c:import url="/WEB-INF/views/common/pagingView.jsp" />
</body>
</html>
