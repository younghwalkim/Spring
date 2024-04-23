<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  

<c:set var="nowpage" value="1" />
<c:if test="${ !empty requestScope.currentPage }">
	<c:set var="nowpage" value="${ requestScope.currentPage }" />
</c:if>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>searchList</title>
<script type="text/javascript" src="/getdrive/resources/js/jquery-3.7.0.min.js"></script>

<script type="text/javascript">

/* 키워드 중심으로 앞 10, 뒤 100자로 요약처리 */
$(document).ready(function() {
    var keyword = "${ keyword }"; // 키워드 설정
    
    $("#sContent").each(function() {
        var text = $(this).text();
        var keywordIndex = text.indexOf(keyword);

        if (keywordIndex !== -1) {
            var start = Math.max(0, keywordIndex - 10);
            var end = Math.min(text.length, keywordIndex + keyword.length + 100);
            var extractedText = text.substring(start, end);

            $(this).text(extractedText + " ...");
        }
    });    
    
    var replacement = "<font color='red'>" + keyword + "</font>"; // 키워드 강조

    $("#sContent").html(function() {
        return $(this).html().replace(keyword, replacement);
    });
});   

var replacement = "<font color='red'>" + keyword + "</font>";

$("#sContent").html(function() {
    return $(this).html().replace(keyword, replacement);
});
/* 키워드 중심으로 앞 10, 뒤 100자로 요약처리 */


function showWriteForm(){
	//게시글 원글 쓰기 페이지로 이동 요청
	location.href = "${ pageContext.servletContext.contextPath }/bwform.do";
}
</script>

<style type="text/css">
.tool-loading-info {
-webkit-tap-highlight-color: rgba(0,0,0,0);
    -webkit-text-size-adjust: 100%;
    font-size: 14px;
    color: #333;
    line-height: 1em;
    -webkit-font-smoothing: antialiased;
    --jnd-base-color: #00C473;
    --jnd-base-opac045-color: rgba(0,196,115,.45);
    --jnd-base-opac025-color: rgba(0,196,115,.25);
    --jnd-base-opac012-color: rgba(0,196,115,.12);
    --jnd-base-opac008-color: rgba(0,196,115,.08);
    --jnd-base-hover-color: #00B474;
    --jnd-base-hover-opac095-color: rgba(0,180,116,.95);
    --jnd-base-border-opac095-color: rgba(0,192,121,.95);
    --jnd-base-focus-color: #8CC3BE;
    --jnd-base-active-color: #00A66D;
    --jnd-base-pressed-color: #96CFBA;
    --jnd-base-self-deep-color: rgba(20,55,50,.6);
    --jnd-base-self-content-color: #E0F8EE;
    --jnd-base-self-dark-color: #3C5752;
    --jnd-base-link-color: #00CA7C;
    user-select: none;
    box-sizing: border-box;
    outline: 0;
    margin: 0;
    word-break: keep-all;
    word-wrap: break-word;
    position: relative;
    z-index: 1;
    top: 0;
    width: 100%;
    padding: 15px 10px 15px 16px;
    background-color: #F2F4F8;
    font-family: color-emoji,Arial,'Malgun Gothic','Microsoft JhengHei',Meiryo,Dotum,sans-serif,fontAwesome;
}
       
</style>
</head>
<body>
<div id="container">

  <div id="jb-header">      
	<c:import url="/WEB-INF/views/common/teamtop.jsp" />        
  </div>
  
  <div id="sidebar">
	<c:import url="/WEB-INF/views/common/teamleft.jsp" />
  </div>
    
  <div id="content">
	      

	<div class="tool-loading-info with-search-txt ng-scope" ng-if="isShow()" ng-switch="" on="searchStatus.type" search-status="searchStatus" style="">
	<!-- ngSwitchWhen: progress -->
	<!-- ngSwitchWhen: keywordSearch -->
	<div class="display-box ng-scope" ng-switch-when="keywordSearch" style="">
	<p class="display-txt" translate="">
	<b>${ keyword }</b> 에 대한 검색 결과가 <b>${ listCount }</b>건 있습니다.</p></div>
	<!-- end ngSwitchWhen: -->
	<!-- ngSwitchWhen: search -->
	</div>
	
	
	<%-- 조회된 게시글 목록 출력 --%>
	<br>
	<table style="width:100%; cellspacing:0; align:center; border:0; border-spacing: 0 10px;">
		<c:forEach items="${ requestScope.list }" var="b">
			<tr style="border-bottom: 2px dotted #000;align:center;">
				<td>[${ b.s_no }]</td>
				<td align="left">					
					<!-- Meeting -->
					<c:if test="${ b.s_menu eq 'MT' }">
						<b>[Meeting]</b> 
						<c:url var="bd" value="mdetail.do">
							<c:param name="no" value="${ b.s_id }" />
							<c:param name="tNo" value="${ tNo }" />
						</c:url>
						<a href="${ bd }">${ b.s_title }</a>
					</c:if>
					<!-- Board -->
					<c:if test="${ b.s_menu eq 'BD' }">
						<b>[Board]</b> 
						<c:url var="bd" value="bdetail.do">
							<c:param name="bNo" value="${ b.s_id }" />
							<c:param name="tNo" value="${ tNo }" />
						</c:url>
						<a href="${ bd }">${ b.s_title }</a>
					</c:if>	
					
					<!-- calendar -->
					<c:if test="${ b.s_menu eq 'CL' }">
						<b>[Calendar]</b> 
						<c:url var="bd" value="cldetail.do">
							<c:param name="clnum" value="${ b.s_id }" />
							<c:param name="tNo" value="${ tNo }" />
						</c:url>
						<a href="${ bd }">${ b.s_title }</a>
					</c:if>	
				</td>
				<td>${ b.s_date }</td>				
			</tr>
			<tr align="left" height=50 valing="top">
				<td colspan=6><div id="sContent"><a href="${ bd }">${ b.s_content }</a></div></td>				
			</tr>
			<tr><td colspan=6><hr></td></tr>							
		</c:forEach>		
	</table>
	
	<%-- 페이징 처리 뷰 포함 처리 --%>
	<c:import url="/WEB-INF/views/common/pagingView.jsp" />
	
	</div>

  <div id="footer">
    <c:import url="/WEB-INF/views/common/teamfooter.jsp" />
  </div>

</div>
</body>
</html>




