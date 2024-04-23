<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>알림 확인 NoraMore : 나랑 함께 놀 사람~ 놀아!모아!</title>
<link href="resources/css/alarmlist.css" rel="stylesheet">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/jquery-3.7.0.min.js"></script>
<c:import url="/WEB-INF/views/common/header.jsp" />
</head>
<body>

<div class="table-container">
    <h2 class="title">알림</h2>
    <br>
    <table class="table">
        <tr>
            <th>번호</th>
            <th>내 용</th>
            <th>발신일</th>
            <th>상 태</th>
        </tr>
        <!-- title, context, senderId 모두 a태그 적용 + a 태그 onclick 시 확인여부 'Y'로 update 요청보내기 -->
        <c:forEach items="${ requestScope.list }" var="a">
            <tr>
                <td>${ a.alarmId }</td>
                <c:if test="${a.alarmKind.trim() eq 'COMM_RECR'}">
                    <td>
                        <c:if test="${ !( a.refCommentId > 0 )}">
                            글 <a class="aTag"
                            href="${ pageContext.servletContext.contextPath }/rbdetail.do?boardId=${ a.boardId }&categoryId=${ a.categoryId }&page=1"
                            onclick="markAsRead(${a.alarmId});">
                                    <c:if test="${a.title.length() > 10 }">
                                        '${a.title.substring(0, 10)} ... '
                                    </c:if>
                                    <c:if test="${a.title.length() <= 10 }">
                                        '${a.title}'
                                    </c:if>
                                </a> 에 새로운 댓글이 달렸습니다.
                            <br>
                            └ '${ a.senderId }' : 
                                        <a class="aTag"
                                        href="${ pageContext.servletContext.contextPath }/rbdetail.do?boardId=${ a.boardId }&categoryId=${ a.categoryId }&page=1"
                                        onclick="markAsRead(${a.alarmId});">
                                            <c:if test="${a.context.length() > 10 }">
                                                '${a.context.substring(0, 10)} ... '
                                            </c:if>
                                            <c:if test="${a.context.length() <= 10 }">
                                                '${a.context}'
                                            </c:if>
                                        </a>
                        </c:if>
                        <c:if test="${ a.refCommentId > 0}">
                            '${ a.senderId }' 님의 답글 :    
                            <a class="aTag"
                            href="${ pageContext.servletContext.contextPath }/rbdetail.do?boardId=${ a.boardId }&categoryId=${ a.categoryId }&page=1"
                            onclick="markAsRead(${a.alarmId})">
                                <c:if test="${a.context.length() > 10 }">
                                    '${a.context.substring(0, 10)} ... '
                                </c:if>
                                <c:if test="${a.context.length() <= 10 }">
                                    '${a.context}'
                                </c:if>
                            </a>
                        </c:if>
                    </td>
                </c:if>
                <c:if test="${ a.alarmKind.trim() eq 'COMM_FREE'}">
                    <td>
                        <c:if test="${ !( a.refCommentId > 0 )}">
                            글 <a class="aTag"
                            href="${ pageContext.servletContext.contextPath }/fbdetail.do?boardId=${ a.boardId }&categoryId=${ a.categoryId }&page=1"
                            onclick="markAsRead(${a.alarmId});">
                                    <c:if test="${a.title.length() > 10 }">
                                        '${a.title.substring(0, 10)} ... '
                                    </c:if>
                                    <c:if test="${a.title.length() <= 10 }">
                                        '${a.title}'
                                    </c:if>
                                </a> 에 새로운 댓글이 달렸습니다.
                            <br>
                            └ '${ a.senderId }' : 
                                        <a class="aTag"
                                        href="${ pageContext.servletContext.contextPath }/fbdetail.do?boardId=${ a.boardId }&categoryId=${ a.categoryId }&page=1"
                                        onclick="markAsRead(${a.alarmId});">
                                            <c:if test="${a.context.length() > 10 }">
                                                '${a.context.substring(0, 10)} ... '
                                            </c:if>
                                            <c:if test="${a.context.length() <= 10 }">
                                                '${a.context}'
                                            </c:if>
                                        </a>
                        </c:if>
                        <c:if test="${ a.refCommentId > 0}">
                            '${ a.senderId }' 님의 답글 :    
                            <a class="aTag"
                            href="${ pageContext.servletContext.contextPath }/fbdetail.do?boardId=${ a.boardId }&categoryId=${ a.categoryId }&page=1"
                            onclick="markAsRead(${a.alarmId})">
                                <c:if test="${a.context.length() > 10 }">
                                    '${a.context.substring(0, 10)} ... '
                                </c:if>
                                <c:if test="${a.context.length() <= 10 }">
                                    '${a.context}'
                                </c:if>
                            </a>
                        </c:if>
                    </td>
                </c:if>
                
                <c:if test="${ a.alarmKind.trim() eq 'RECR_APPL' }">
                    <td>
                       '${ a.senderId }' 님께서 모집 
                                <a class="aTag"
                                href="${ pageContext.servletContext.contextPath }/rbdetail.do?boardId=${ a.boardId }&categoryId=${ a.categoryId }&page=1"
                                onclick="markAsRead(${a.alarmId});"  >
                                    <c:if test="${a.title.length() > 10 }">
                                        '${a.title.substring(0, 10)} ... '
                                    </c:if>
                                    <c:if test="${a.title.length() <= 10 }">
                                        '${a.title}'
                                    </c:if>
                                </a> 에 신청하였습니다.
                    </td>
                </c:if>
                
                <td>${ a.registDate }</td>
                
                <c:if test="${ a.checkedYN eq 'Y' }">
                    <td>읽음</td>
                </c:if>
                <c:if test="${ a.checkedYN eq 'N' }">
                    <td>안 읽음</td>
                </c:if>

                
            </tr>
        </c:forEach>
    </table>
    <br>
    <c:import url="/WEB-INF/views/common/pagingView.jsp" />
</div>
<script>
    function markAsRead(alarmId) {
        $.ajax({
            type: "POST",
            url: "alarmCheck.do",
            data: { alarmId: alarmId}
        });
    }
</script>


</body>
</html>
