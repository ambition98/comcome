<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="shortcut icon" type="image/x-icon" href="<c:url value='/resources/img/com_logo.png'/>">
<title>comcome 특가몰 상세보기</title>
<%-- <link rel="stylesheet"
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

<script src="<c:url value='/resources/js/base/query-ui.min.js' />"></script>
<script src="<c:url value='/resources/js/base/jquery.slicknav.js' />"></script>
<script src="<c:url value='/resources/js/base/owl.carousel.min.js' />"></script>
<script src="<c:url value='/resources/js/base/main.js' />"></script> --%>


<link rel="stylesheet" href="<c:url value='/resources/css/base/bootstrap.min.css' />" type="text/css">
    <link rel="stylesheet" href="<c:url value='/resources/css/base/font-awesome.min.css"' />" type="text/css">
    <link rel="stylesheet" href="<c:url value='/resources/css/base/nice-select.css' />" type="text/css">
    <link rel="stylesheet" href="<c:url value='/resources/css/base/jquery-ui.min.css' />" type="text/css">
    <link rel="stylesheet" href="<c:url value='/resources/css/base/owl.carousel.min.css' />" type="text/css">
    <link rel="stylesheet" href="<c:url value='/resources/css/base/slicknav.min.css' />" type="text/css">
    <link rel="stylesheet" href="<c:url value='/resources/css/base/style.css' />" type="text/css">
    
    <script src="<c:url value='/resources/js/base/jquery-3.3.1.min.js' />"></script>
    <script src="<c:url value='/resources/js/base/bootstrap.min.js' />"></script>
    <script src="<c:url value='/resources/js/base/jquery.nice-select.min.js' />"></script>
    <script src="<c:url value='/resources/js/base/jquery-ui.min.js' />"></script>
    <script src="<c:url value='/resources/js/base/jquery.slicknav.js' />"></script>
    <script src="<c:url value='/resources/js/base/mixitup.min.js' />"></script>
    <script src="<c:url value='/resources/js/base/owl.carousel.min.js' />"></script>
    <script src="<c:url value='/resources/js/base/main.js' />"></script>

<style type="text/css">
</style>


</head>
<body>
    <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2 style="color:black;">특가상품 상세페이지</h2>
                    </div>
                </div>
    <section class="product-details spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <div class="product__details__pic">
                        <div class="product__details__pic__item">
                            <img class="product__details__pic__item--large"
                                src="../resources/user_uploaded_file/testboard/${vo.thumbNailImg}" alt="">
                        </div>
                        <div class="product__details__pic__slider owl-carousel">
                       		<%-- <img src="../resources/user_uploaded_file/testboard/${vo.thumbNailImg}" alt=""
												style="width:auto; height:auto; max-width:150px; max-height:150px; "> --%>
                            <img data-imgbigurl="../resources/user_uploaded_file/testboard/${vo.thumbNailImg}"
                                src="../resources/user_uploaded_file/testboard/${vo.thumbNailImg}" alt="">
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <div class="product__details__text">
                        <h3>${vo.name }</h3>
                        <div class="product__details__price"><fmt:formatNumber value="${vo.price }" pattern="#,###" /> \</div>
                        <p>${vo.content}</p>
                        <a href='<c:url value="/event/wish?email=${email}&saleProductNo=${vo.saleProductNo}"/>' class="primary-btn">장바구니 추가</a>
                        <a href='<c:url value="/direct/checkout?saleProductNo=${vo.saleProductNo}"/>' class="primary-btn">바로구매</a>
                        <ul>
                            <li><b>재고 여부</b> <span>주문 가능</span></li>
                            <li><b>배송일</b> <span>평균 1~2일</span></li>
                            <li><b>주의사항</b> <span>교환 및 환불 불가</span></li>
                        </ul>
                    </div>
                </div>
                
            </div>
        </div>
    </section>
    <!-- Product Details Section End -->

 

    <!-- Js Plugins -->
    <!-- <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.nice-select.min.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/jquery.slicknav.js"></script>
    <script src="js/mixitup.min.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/main.js"></script> -->


</body>
</html>
<%@ include file="../include/footer.jsp"%>