<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mypage/styles.css'/>" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/mypage/scripts.js"></script>

<script type="text/javascript">	
	$(function(){
		$('.divList .box2 tbody tr').hover(function(){
			$(this).css('background','skyblue');
		}, function(){
			$(this).css('background','');
		});
	});
</script>
<style type="text/css">
	body{
		padding:5px;
		margin:5px;
	 }	
</style>

</head>
<body>
<div class="divList">
<table class="box2"
	 	summary="기본 게시판에 관한 표로써, 번호, 제목, 작성자, 작성일, 조회수에 대한 정보를 제공합니다.">
	<caption>기본 게시판</caption>
	<colgroup>
		<col style="width:10%;" />
		<col style="width:50%;" />
		<col style="width:15%;" />
		<col style="width:15%;" />
		<col style="width:10%;" />		
	</colgroup>
	<thead>
	  <tr>
	    <th scope="col">번호</th>
	    <th scope="col">제목</th>
	    <th scope="col">작성자</th>
	    <th scope="col">작성일</th>
	    <th scope="col">조회수</th>
	  </tr>
	</thead> 
	<tbody>  
	  <c:if test="${empty mypageList }">
	  	<tr>
	  		<td colspan="5">데이터가 없습니다.</td>
	  	</tr>
	  </c:if>	
	  <c:if test="${!empty mypageList }">	  
		  <!--게시판 내용 반복문 시작  -->		  
		  <c:forEach var="vo" items="${mypageList }">
			<tr  style="text-align:center">
				<td>${vo.accountno}</td>
				<td style="text-align:left">
<%-- 					<a href
		="<c:url value='/board/countUpdate.do?no=${vo.no}'/>">
						${vo.title}
					</a> --%>
				</td>
				<td>${vo.name}</td>
				<td><fmt:formatDate value="${vo.regdate}" pattern="yyyy-MM-dd"/>
				</td>	
			</tr>
		  </c:forEach>
		  <!--반복처리 끝  -->
		</c:if>
	</tbody>
</table>	   
</div>
<div class="divPage">
	<!-- 페이지 번호 추가 -->		
	<!-- 이전 블럭으로 이동 -->
	<c:if test="${pagingInfo.firstPage>1 }">
		<a href
="<c:url value='/mypage/mypageList.do?currentPage=${pagingInfo.firstPage-1}&searchCondition=${param.searchCondition}&searchKeyword=${param.searchKeyword}'/>">
			<img src="<c:url value='/resources/images/first.JPG'/>" alt="이전블럭">
		</a>	
	</c:if>		
						
	<!-- [1][2][3][4][5][6][7][8][9][10] -->
	<c:forEach var="i" begin="${pagingInfo.firstPage}" end="${pagingInfo.lastPage }">
		<c:if test="${i==pagingInfo.currentPage }">
			<span style="color:blue;font-weight: bold;font-size: 1em">
				${i}</span>			
		</c:if>	
		<c:if test="${i!=pagingInfo.currentPage }">	
				<a href
="<c:url value='/mypage/mypageList.do?currentPage=${i}&searchCondition=${param.searchCondition}&searchKeyword=${param.searchKeyword}'/>">
				[${i }]</a>			
		</c:if>
	</c:forEach>
	
	<!-- 다음 블럭으로 이동 -->					
	<c:if test="${pagingInfo.lastPage < pagingInfo.totalPage }">	
			<a href
="<c:url value='/mypage/mypageList.do?currentPage=${pagingInfo.lastPage+1}&&searchCondition=${param.searchCondition}&searchKeyword=${param.searchKeyword}'/>">
				<img src="<c:url value='/resources/images/last.JPG'/>" alt="다음블럭">
			</a>	
	</c:if>					
	<!--  페이지 번호 끝 -->	
</div>


</body>
</html>