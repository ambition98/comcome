<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../adminlayout/adminheader.jsp" %>
	 <div class="main">
			<div class="main-content">
				<div class="container-fluid">
					
					<div class="panel panel-headline">
						<div class="panel-heading">  
													
							<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
							<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
							<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
							 <!--  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">  -->
							  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
							  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
							  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
								
								<!-- pagination design -->
							 <!--  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"> -->
							  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
							  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
							  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
							
							
							   <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/admin/member.css'/>" /> 
							    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/admin/member2.css'/>" /> 
							
							<div class="container">
							  <h2>게시글 관리</h2>
							  <c:if test="${!empty param.searchKeyword }">
								<p>검색어 : ${param.searchKeyword },  
									${pagingInfo.totalRecord} 건 검색되었습니다. </p>
							</c:if>
							  <table class="table table-striped">
							    <thead>
							      <tr>
							        <th>글번호</th>
							        <th>제목</th> 
							        <th>내용</th>
							        <th>조회수</th>
							        
							        <th>작성일</th>
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
											<td>${vo.boardNo}</td>
											<td style="text-align:left">
												<a href
									="<c:url value='/admin/detailwithmain?boardNo=${vo.boardNo}'/>">
													${vo.title}
												</a>
											</td> 
											<td>${vo.content}</td>
											<td>${vo.readcount}</td>
											
											<td><fmt:formatDate value="${vo.regdate}" pattern="yyyy-MM-dd"/>
											</td>
										</tr>
									  </c:forEach>
									  <!--반복처리 끝  -->
									</c:if>
							    </tbody>
							  </table>
							</div>
							<div class="container">
								<!-- 페이지 번호 추가 -->		
								<!-- 이전 블럭으로 이동 -->
								 <ul class="pagination">
								<c:if test="${pagingInfo.firstPage>1 }">
							    <li class="page-item"><a class="page-link" href="<c:url value='/admin/boardwithmain?currentPage=${pagingInfo.firstPage-1}&searchCondition=${param.searchCondition}&searchKeyword=${param.searchKeyword}'/>">Previous</a></li>
							    </c:if>	
							    <!-- [1][2][3][4][5][6][7][8][9][10] -->
							    <c:forEach var="i" begin="${pagingInfo.firstPage}" end="${pagingInfo.lastPage }">
							    	<c:if test="${i==pagingInfo.currentPage }">
							   		<li class="page-item"><a class="page-link" href="javascript:void(0);">${i}</a></li>
							   		</c:if>	
									<c:if test="${i!=pagingInfo.currentPage }">	
									<li class="page-item"><a class="page-link" href="<c:url value='/admin/boardwithmain?currentPage=${i}&searchCondition=${param.searchCondition}&searchKeyword=${param.searchKeyword}'/>">${i}</a></li>
									</c:if>	
							    </c:forEach>
							    <!-- 다음 블럭으로 이동 -->
							    <c:if test="${pagingInfo.lastPage < pagingInfo.totalPage }">	
							    <li class="page-item"><a class="page-link" href="<c:url value='/admin/boardwithmain?currentPage=${pagingInfo.lastPage+1}&&searchCondition=${param.searchCondition}&searchKeyword=${param.searchKeyword}'/>">Next</a></li>
							  	</ul>
								</c:if>	
								
								
												
								<!--  페이지 번호 끝 -->	
							</div>
							
							 <div class="divSearch">
							   	<form name="frmSearch" method="post" id ="search"
							   		action='<c:url value="/admin/boardwithmain"/>'>
							        <select name="searchCondition" id ="searchsel">
							            <option value="title" 
							            	<c:if test="${param.searchCondition=='title' }">            	
							            		selected="selected"
							            	</c:if>
							            >제목</option>
							            <option value="content" 
							            	<c:if test="${param.searchCondition=='content' }">            	
							            		selected="selected"
							            	</c:if>
							            >내용</option>
							          
							        </select>   
							        <input type="text" id ="searchinput" name="searchKeyword" title="검색어 입력"
							        	value="${param.searchKeyword}">   
									<input type="submit"  id ="searchbutton" value="검색">
							    </form>
							</div>

						</div>
					</div>	
				</div>	
			</div>
 </div>  
<%@include file="../adminlayout/adminfooter.jsp" %>