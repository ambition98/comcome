<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../include/header.jsp"%>

<%-- <%@ include file="include/searchform.jsp"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>comcome</title>
<link rel="stylesheet"
	href="<c:url value='/resources/css/base/bootstrap.min.css' />"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/resources/css/base/font-awesome.min.css"' />"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/resources/css/base/nice-select.css' />"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/resources/css/base/jquery-ui.min.css' />"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/resources/css/base/owl.carousel.min.css' />"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/resources/css/base/slicknav.min.css' />"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/resources/css/base/style.css' />" type="text/css">

<script src="<c:url value='/resources/js/base/jquery-3.3.1.min.js' />"></script>
<script src="<c:url value='/resources/js/base/bootstrap.min.js' />"></script>
<script
	src="<c:url value='/resources/js/base/jquery.nice-select.min.js' />"></script>
<script src="<c:url value='/resources/js/base/query-ui.min.js' />"></script>
<script src="<c:url value='/resources/js/base/jquery.slicknav.js' />"></script>
<script src="<c:url value='/resources/js/base/mixitup.min.js' />"></script>
<script src="<c:url value='/resources/js/base/owl.carousel.min.js' />"></script>
<script src="<c:url value='/resources/js/base/main.js' />"></script>
</head>
<body>
	<%-- <%@ include file="include/category.jsp"%> --%>
	<%-- <%@ include file="inc/body.jsp"%> --%>

	<!-- Breadcrumb Section Begin -->
	<section class="breadcrumb-section set-bg" data-setbg="../resources/img/breadcrumb.jpg">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<div class="breadcrumb__text">
					
						<h2 style="color: black;">중고 장터</h2>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Breadcrumb Section End -->

	<!-- Blog Section Begin -->
	<section class="product spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-2 col-md-5">
					<div class="sidebar">
						<div class="blog__sidebar__search">
							
						</div>
						<div class="blog__sidebar__item">
							<h4>Categories</h4>
							<ul>
								<li><a href="<c:url value='/usedBoard/categoryList?groupNo=1'/>">노트북</a></li>
								<li><a href="<c:url value='/usedBoard/categoryList?groupNo=2'/>">노트북 주변기기</a></li>
								<li><a href="<c:url value='/usedBoard/categoryList?groupNo=3'/>">기타 pc부품</a></li>

							</ul>
						</div>
					</div>
					
							<div class="searchdiv" >
						<form name="frmSerch" method="post"
								action='<c:url value="/usedBoard/list"/>'>
								
								 <input type="text" name="searchKeyword" title="검색어 입력"
									value="${param.searchKeyword}" size="15" style=" margin-bottom:10px;"> 
									
								<select name="searchCondition">
									
									<option value="title"
										<c:if test="${param.searchCondition=='title' }">            	
  							          		selected="selected"
          							  	</c:if>>제목</option>
									<option value="content"
										<c:if test="${param.searchCondition=='content' }">            	
            								selected="selected"
            							</c:if>>내용</option>

								</select>	
		
								<input type="submit"value="검색">
							</form>
						</div>					
				</div>
				


				<div class="col-lg-9 col-md-9">
					<div class="row">

						<!--게시판 내용 반복문 시작  -->
						<c:forEach var="vo" items="${list}">
							<div class="col-lg-3 col-md-4 col-sm-6">
								<div class="blog__item">
									<a href="<c:url value='/usedBoard/countUpdate?boardNo=${vo.boardNo}'/>">
										<div class="blog__item__pic" >
											<img src="../resources/img/${vo.fileName}" alt=""
												style="width:auto; height:auto; max-width:150px; max-height:100px; ">
										</div>
										<div class="blog__item__text">
											<h5> ${vo.title}</h5>
											<ul>
												<li><i class="fa category"></i>카테고리 ${vo.groupNo}</li>
												<li><i class="calendar"></i> <fmt:formatDate value="${vo.regdate}" pattern="yyyy-MM-dd HH:mm"/></li>
												<li><i class="price"></i><fmt:formatNumber value="${vo.price }" pattern="#,###" /></li><br>
												<li><i class="count "></i> 조회수 : ${vo.readcount}</li>
											</ul>
											
										</div>
									</a>
								</div>
							</div>
						</c:forEach>
						<!--반복처리 끝  -->


					</div>
					<div class="divPage">
						<!-- 페이지 번호 추가 -->
						<!-- 이전 블럭으로 이동 -->
						<c:if test="${pagingInfo.firstPage>1 }">
							<a
								href="<c:url value='/usedBoard/list?currentPage=${pagingInfo.firstPage-1}&searchCondition=${param.searchCondition}&searchKeyword=${param.searchKeyword}'/>">
								<img src="<c:url value='/resources/images/first.JPG'/>"
								alt="이전블럭">
							</a>
						</c:if>

						<!-- [1][2][3][4][5][6][7][8][9][10] -->
						<c:forEach var="i" begin="${pagingInfo.firstPage}"
							end="${pagingInfo.lastPage }">
							<c:if test="${i==pagingInfo.currentPage }">
								<span style="color: blue; font-weight: bold; font-size: 1em">
									${i}</span>
							</c:if>
							<c:if test="${i!=pagingInfo.currentPage }">
								<a
									href="<c:url value='/usedBoard/list?currentPage=${i}&searchCondition=${param.searchCondition}&searchKeyword=${param.searchKeyword}'/>">
									[${i }]</a>
							</c:if>
						</c:forEach>

						<!-- 다음 블럭으로 이동 -->
						<c:if test="${pagingInfo.lastPage < pagingInfo.totalPage }">
							<a
								href="<c:url value='/usedBoard/list?currentPage=${pagingInfo.lastPage+1}&&searchCondition=${param.searchCondition}&searchKeyword=${param.searchKeyword}'/>">
								<img src="<c:url value='/resources/images/last.JPG'/>"
								alt="다음블럭">
							</a>
						</c:if>
						<!--  페이지 번호 끝 -->
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Blog Section End -->


</body>
</html>
<%@ include file="../include/footer.jsp"%>