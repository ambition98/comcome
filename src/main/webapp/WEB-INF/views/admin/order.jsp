<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
 <!--  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">  -->
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  <style type="text/css">
	a.button {
	    margin-left: 70px;
	    background-color: #2d4755;
	    color: white;
	    font-weight: bold;
	    padding: 6px;
	    border-radius: 5px;
	    margin-left: 200px;
	}
  </style>
	<!-- pagination design -->
 <!--  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"> -->
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>


   <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/admin/member.css'/>" /> 
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/admin/member2.css'/>" /> 
	<div class="container">
  <h2>주문 내역</h2>
  
  <table class="table table-striped">
    <thead>
      <tr>
        <th>주문번호</th>
        <th>회원번호</th>
        <th>제품번호</th>
        <th>주문일</th>
        <th>가격</th>
      </tr>
    </thead>
    <tbody>
    	<c:if test="${empty list }">
	  	<tr>
	  		<td colspan="5">데이터가 없습니다.</td>
	  	</tr>
	  </c:if>	
      <c:if test="${!empty list }">	  
		  <!--게시판 내용 반복문 시작  -->		  
		  <c:forEach var="vo" items="${list }">
			<tr>
				<td>${vo.pdOrderNo}</td>
				<td>${vo.accountNo}</td>
				<td>${vo.saleProductNo}</td>
				<td><fmt:formatDate value="${vo.orderDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatNumber value="${vo.price }" pattern="#,###" /></td>
			</tr>
		  </c:forEach>
		  <!--반복처리 끝  -->
		</c:if>
    </tbody>
  </table>
</div>
<div class="container" id="page">
	<!-- 페이지 번호 추가 -->		
	<!-- 이전 블럭으로 이동 -->
	 <ul class="pagination">
	<c:if test="${pagingInfo.firstPage>1 }">
    <li class="page-item"><a class="page-link" href="<c:url value='/adminview/orderwithmain?currentPage=${pagingInfo.firstPage-1}'/>">Previous</a></li>
    </c:if>	
    <!-- [1][2][3][4][5][6][7][8][9][10] -->
    <c:forEach var="i" begin="${pagingInfo.firstPage}" end="${pagingInfo.lastPage }">
    	<c:if test="${i==pagingInfo.currentPage }">
   		<li class="page-item"><a class="page-link" href="javascript:void(0);">${i}</a></li>
   		</c:if>	
		<c:if test="${i!=pagingInfo.currentPage }">	
		<li class="page-item"><a class="page-link" href="<c:url value='/adminview/orderwithmain?currentPage=${i}'/>">${i}</a></li>
		</c:if>	
    </c:forEach>
    <!-- 다음 블럭으로 이동 -->
    <c:if test="${pagingInfo.lastPage < pagingInfo.totalPage }">	
	    <li class="page-item"><a class="page-link" href="<c:url value='/adminview/orderwithmain?currentPage=${pagingInfo.lastPage+1}'/>">Next</a></li>
	  	</ul>
	</c:if>	
	
					
	<!--  페이지 번호 끝 -->	
</div>
<a href='<c:url value="/admin/excelDownload"/>' class="button">전체데이터 엑셀다운</a>