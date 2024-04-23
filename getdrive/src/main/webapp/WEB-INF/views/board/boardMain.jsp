<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="nowpage" value="1" />
<c:if test="${ !empty requestScope.currentPage }">
	<c:set var="nowpage" value="${ requestScope.currentPage }" />
</c:if>

 <c:set var="tNo" value="${param.tNo}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main List</title>
<style type="text/css">

.board-container {
	display: flex;
	justify-content: center; /* 수평 가운데 정렬*/
	align-items: center; /* 수직 가운데 정렬 */
	height: 100vh; /* 화면의 높이를 전체 화면으로 설정 */
}

/* 전체 감싸주는 div 태그에서 가운데 정렬 셋팅 */
.board-con-01{
	width: 80%;
	padding: 20px;
	border: 1px solid #ccc;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	text-align: center;
	font-family: Arial, sans-serif;
	background-color: #f9f9f9; /* 배경색 변경 */
	color: #333; /* 텍스트 색상 변경 */
	max-width: 800px;
}

.text_box {
	margin: 0 auto;
}

/*글쓰기 버튼 이미지 꾸미기*/
.writeBtn img {
	width:70px;
	height: 70px;
	border-radius: 50%; /* 버튼을 동그랗게 만듦 */
    transition: transform 0.3s ease; /* 호버 효과를 위한 애니메이션 추가 */

}

/* 버튼 스타일 변경 */
#writeBtn {
	background-color: #007bff; /* 버튼 배경색 변경 */
	border: none;
	color: white;
	padding: 10px 20px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
	border-radius: 5px; /* 버튼 모양 변경 */
	transition: background-color 0.3s ease;
	float: right;
	margin-bottom: 20px;
    }

#writeBtn:hover {
    background-color: #45a049; /* 호버 시 배경색 변경 */
}

/* 버튼과 테이블 사이 간격 추가 */
.list-footer {
    margin-top: 20px;
}

/* 테이블 스타일 */
table {
	width: 100%
	border-collapse: collapse;
    font-family: Arial, sans-serif; /* 테이블 내 폰트 변경 */
}

/* 테이블 내용 스타일 */
.table-bordered tr:nth-child(even) {
	background-color: #f2f2f2;'
}

/* 테이블 행의 배경색 변경 */
#boardList tbody tr:nth-child(odd) {
	background-color: #f2f2f2;
    }
    
/* 입력 폼의 스타일 변경 */
input[type="search"] {
	padding: 8px;
	border: 1px solid #ccc;
	border-radius: 5px;
    }
    
 /* 버튼 호버 효과 변경 */
#writeBtn:hover {
	background-color: #0056b3;
    }
    
/*목록 버튼 스타일 변경*/
.btn {
    background-color: #6DBFF2;
    color: white;
    border: none;
    border-radius: 5px;
    padding: 10px 20px;
    cursor: pointer;
    transition: background-color 0.3s ease;
    margin-right: 10px;
}
.btn:hover {
    background-color: #45a049;
}
    
/* 제목 스타일 변경 */
.board-title {
	font-size: 24px; /* 글꼴 크기 변경 */
	font-weight: bold; /* 글꼴 스타일 변경 */
	color: #444; /* 텍스트 색상 변경 */
    }

#boardList th,  #boardList td{

	border: 1px solid #dddddd;
	padding: 8px;
	text-align: left;
}

#boardList th {
	background-color: #f2f2f2;
}

/* 검색 폼  */
fieldset#ss {
	width: 600px;
	margin: 0 auto; /* 가운데정렬 */
	text-align: center; /* 텍스트 가운데 정렬 */
	margin-bottom: 20px; /*간격 추가*/
}
form fieldset {
	margin: 0 auto; /* 가운데 정렬 */
    width: 80%; /* 너비 설정 */
    border: 1px solid #ccc; /* 테두리 설정 */
    border-radius: 5px; /* 테두리 모서리 둥글게 설정 */
    padding: 10px; /* 내부 여백 설정 */
    margin-bottom: 20px; /* 하단 여백 설정 */
}

form fieldset input[type="search"] {
    padding: 8px; /* 입력 필드 내부 여백 설정 */
    border: 1px solid #ccc; /* 입력 필드 테두리 설정 */
    border-radius: 5px; /* 입력 필드 테두리 모서리 둥글게 설정 */
    width: 70%; /* 입력 필드 너비 설정 */
    }
    
form fieldset select {
	padding: 8px; /* 선택 필드 내부 여백 설정 */
	border: 1px solid #ccc; /* 선택 필드 테두리 설정 */
	border-radius: 5px; /* 선택 필드 테두리 모서리 둥글게 설정 */
}

form fieldset input[type="submit"] {
    background-color: #007bff; /* 버튼 배경색 설정 */
    border: none; /* 버튼 테두리 제거 */
    color: white; /* 버튼 텍스트 색상 설정 */
    padding: 10px 20px; /* 버튼 내부 여백 설정 */
    cursor: pointer; /* 포인터 커서 설정 */
    border-radius: 5px; /* 버튼 모서리 둥글게 설정 */
    transition: background-color 0.3s ease; /* 호버 효과 애니메이션 설정 */			
    }
    
form fieldset input[type="submit"]:hover {
	background-color: #0056b3; /* 호버 시 배경색 변경 */
    }
    
form.sform {
	background: lightgray;
	width: 630px;
	margin: 0 auto;
	text-align: center;
	margin-bottom: 20px;
	display: none;  /* 안 보이게 함 */
}

/*검색 항목 체크 라인 가운데로 정렬*/
#ss{
	text-align: center;
	margin-bottom: 20px; /*간격 추가*/
}

.radio-group {
    border: 1px solid #ccc;
    border-radius: 5px;
    padding: 10px;
    display: flex;
    flex-direction: row;
    align-items: center;
}

.radio-option {
    margin-right: 20px;
}

.radio-option:last-child {
    margin-right: 0;
}

.radio-option input[type="radio"] {
    display: none;
}

.radio-option label {
    display: inline-block;
    padding: 5px 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    cursor: pointer;
}

.radio-option label:hover {
    background-color: #f0f0f0;
}

.radio-option input[type="radio"]:checked + label {
    background-color: #007bff;
    color: #fff;
}
</style>

<script type="text/javascript" src="/getdrive/resources/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript">
$(function(){
	//input 태그의 name 이 item 의 값이 바뀌면(change) 작동되는 이벤트 핸들러 작성
	$('input[name=item]').on('change', function(){
		//여러 개의 태그 중에서 체크표시가 된 태그를 선택
		$('input[name=item]').each(function(index){
			//선택된 radio 순번대로 하나씩 checked 인지 확인!
			if($(this).is(':checked')){
				//체크 표시된 아이템에 대한 폼이 보여지게 처리함
				$('form.sform').eq(index).css('display', 'block');
			}else {
				//체크 표시 안된 아이템의 폼은 안보이게 처리한다.
				$('form.sform').eq(index).css('display', 'none');
			}
		}); //each
	}); //on
}); //document ready


	function moveWritePage(){
		
		location.href="bwrite.do";
	}
	
	
</script>
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



		<div class="board_box-primary">
		
			<div class="board-header with-border">
			
				<h2 class="board-title" align="center">게시글 목록</h2>
				
											<!-- 항목별 검색 기능 추가 -->
						<fieldset id="ss" class="radio-group">
							<legend>검색할 항목을 선택하세요.</legend>
							
							    <div class="radio-option">
							        <input type="radio" name="item" id="title">
							        	<label for="title">제 목</label>
							    </div>
							    
							    <div class="radio-option">
							        <input type="radio" name="item" id="writer">
							        <label for="writer">작 성 자</label>
							    </div>
						</fieldset>	
						<br>
						
							<!-- 제목 검색  -->
						<form id="titleform" class="sform" action="bsearchTitle.do" method="post">
							<input type="hidden" name="action" value="title">
						<fieldset>	
							<legend>제목으로 검색</legend>
							<input type="hidden" name="tNo" value="${ tNo }">
							<input type="search" name="keyword"> &nbsp;
							<input type="submit" value="검색">
						</fieldset>	
						</form>
						
							<!-- 작성자 검색 -->
						<form id="wirterform" class="sform" action="bsearchWriter.do" method="post">
							<input type="hidden" name="action" value="writer">
						<fieldset>
							<legend>작성자로 검색</legend>
							<input type="hidden" name="tNo" value="${ tNo }">
							<input type="search" name="keyword"> &nbsp;
							<input type="submit" value="검색">
						</fieldset>
						</form>
			</div>
			
		</div>
	<!-- 여기까지 게시글 목록 타이틀 -->
		
		<!-- 목록 밑에 게시글 리스트 정렬 파트 -->
		<div class="board-body" align="center">
			<table Id=boardList style="width:100%;">
				
					<tbody>
					<tr>	
						<th style="width: 40px:"> 번호 </th>
						<th>제목</th>
						<th style="width: 100px"> 작성자 </th>
						<th style="width: 100px"> 등록일 </th>	
						<th style="width:150px"> 첨부파일 </th>
					</tr>
					<!--  forEach 문으로 DB 리스트 불러오기 -->
					<c:forEach items="${ list }" var="b" >
					<tr>						
					<c:if test="${ !empty message }">
						
					</c:if>
					
						<td> ${ b.bNo } <!-- EL문으로 변경 ${ B_NO } --></td>
						<!-- 밑에 문장은 JSTL url태그로 실행 -->
						<c:url var="bdetail" value="bdetail.do">
							<c:param name="bNo" value="${ b.bNo }" />
							<c:param name="tNo" value="${ tNo }" />
							<c:param name="page" value="${ nowpage }" />
						</c:url>
						<td align="center"> <a href="${ bdetail }">${ b.bTitle }</a></td><!-- EL문으로 변경 B_TITLE --> 
						<td align="center"> ${ b.bName } <!-- EL문으로 변경 B_NAME --></td>
						<td align="center"> ${ b.bcDate } <!-- EL문으로 변경 B_CDATE  --></td>
						<td align="center">
						
						<c:if test="${ !empty b.bOriginFileName }">
						${ b.bOriginFileName }
						</c:if>
						<c:if test="${ empty b.bOriginFileName }">
						&nbsp;
						</c:if>
						</td>
					</tr>
					</c:forEach>		
					</tbody>			
			</table>	
		</div>	
	
		<div class="board-footer">
			<div class="board-right">
				<button type="button" class="btn-success" id="writeBtn">
					<div class="writeBtn">	
							<a href="bwrite.do?tNo=${ tNo }">글쓰기</a>						
					<br>
					</div>
				</button>
			</div>
			<br>
		
			<c:import url="/WEB-INF/views/common/pagingView.jsp" />
		</div>
	</div>


 <div id="footer">
   <c:import url="/WEB-INF/views/common/teamfooter.jsp" />
 </div>

</div>



</body>
</html>