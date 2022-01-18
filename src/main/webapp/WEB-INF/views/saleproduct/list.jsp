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
<title>ComCome 특가게시판</title>
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
					
					<script src="http://code.jquery.com/jquery-latest.min.js"></script>
					<script type="text/javascript">
 
				
					$(function(){
					    
					    $("input:button[name='button']").on('click',function(){
					        var kind = $(this).val();       //버튼이 클릭 되었을 시, 개별 버튼의 값이 kind 변수에 담겨집니다.
					        $.ajax({
					            url : "/comcome/usedBoard/list_ajax",
					            type : "post",
					            cache : false,
					            headers : {"cache-control":"no-cache","pragma":"no-cache"},
					            data : {
					                 id : $(this).val(),
					                "kind":kind    // 버튼의 value값에 따라 작동합니다.
					                
					            },
					            success : function(data){
					                console.log(data);
					               $('body').html(data); // 성공 시, body부분에 data라는 html 문장들을 다 적용시킵니다.
					            },
					            error:function(request, status, error) {
					                alert("status : " + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
					               }
					        })//ajax
					    });//button click
					    
					}); 
					 
					</script>
					
					
					
					
					
							<div class="searchdiv" >
						<form name="frmSerch" method="post"
								action='<c:url value="/sale/list"/>'>
								<hr>
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
						<div>
						<hr>
						<c:if test="${sessionScope.email!=null}">
						<a href="/comcome/usedBoard/write" style="float:right;"><h5><b>글쓰기</b></h5></a>
						</c:if>	
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
											<img src="../resources/user_uploaded_file/usedboard/${vo.fileName}" alt=""
												style="width:auto; height:auto; max-width:150px; max-height:100px; ">
										</div>
										<div class="blog__item__text">
											<h5> ${vo.title}</h5>
											<ul>
												<li><i class="fa category"></i>${vo.groupNo}</li>
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