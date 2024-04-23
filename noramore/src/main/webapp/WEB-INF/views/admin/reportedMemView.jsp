<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>신고된 회원 NoraMore : 나랑 함께 놀 사람~ 놀아!모아!</title>
    <script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/jquery-3.7.0.min.js"></script>
    <script type="text/javascript">
        function updateRestrict(memberID) {
            $.ajax({
                type: 'POST',
                url: 'restrict.do',
                data: { memberID: memberID, restrict : "1" },
                success: function(response) {
                    if(response === "restrict"){
                        alert("회원의 로그인을 제한합니다. 회원의 누적 신고 수를 0으로 초기화합니다.");
                        location.reload();
                    } else if(response === "failed") {
                        alert("회원 활동 제한 설정 에러");
                    }
                    $('tr:has(td:contains("' + memberID + '"))').remove();
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
                    if(response === "rollback"){
                        alert("회원의 로그인 제한이 해제되었습니다.");
                        location.reload();
                    } else if(response === "failed") {
                        alert("회원 활동 제한 설정 에러");
                    }
                    $('tr:has(td:contains("' + memberID + '"))').remove();
                },
                error: function(xhr, status, error) {
                    console.error("에러 : ", error);
                }
            });
        }
    </script>
    <style type="text/css">
        body {
            font-family: Arial, sans-serif;
            margin-top: 300px;
            padding: 0;
            background-color: #E0F8EC;
        }
        .container {
            max-width: 800px;
            margin: 50px auto;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }
        h2.title {
            text-align: center;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
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
        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 8px 16px;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
<c:import url="/WEB-INF/views/admin/adminSidebar.jsp" />
</head>
<body>
<br><br>
<div class="container">
    <h2 class="title">신고된 회원</h2>
    <table>
        <thead>
            <tr>
               	<th>등 급</th>
                <th>회원아이디</th>
                <th>닉네임</th>
                <th>성 별</th>
                <th>가입일</th>
                <th>상 태</th>
                <th>관 리</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${ requestScope.list }" var="a">
                <tr>
               	 	<td>${ a.grade }</td>
                    <td>${ a.memberID }</td>
                    <td>${ a.memberNicname }</td>
                    <td>${ a.gender }</td>
                    <td>${ a.registDate }</td>
                    <td>
                        <c:choose>
                            <c:when test="${ a.actLimit eq 0 }">
                                일 반
                            </c:when>
                            <c:otherwise>
                                로그인 제한
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${ a.actLimit eq 0 }">
                                <button onclick="updateRestrict('${ a.memberID }');">로그인 제한</button>
                            </c:when>
                            <c:otherwise>
                                <button onclick="rollbackRestrict('${ a.memberID }');">일반 전환</button>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach> 
        </tbody>
    </table>
</div>
<c:import url="/WEB-INF/views/common/pagingView.jsp" />
</body>
</html>
