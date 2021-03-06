<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
	<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/resources/img/com_logo.png'/>">
	<title>ComCome</title>
	
	<link rel="stylesheet" href="<c:url value='/resources/css/category.css' />" type="text/css">
	<script src="<c:url value='/resources/js/category.js' />"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	
	<script type="text/javascript">
		$(function() {
			$('#shopping_bag_ico').click(function() {
				var accountNo = '${sessionScope.accountNo}';
				if(accountNo == null || accountNo == '') {
					if(confirm('로그인 후 이용가능합니다. 로그인하시겠습니까?')) {
						location.href = '<c:url value="/login/login-form" />';
					}
				} else {
					location.href = '<c:url value="/mypage/cart" />';
				}
			});
		});
	</script>
</head>
<body>
    <header class="header">
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="header__logo">
                        <a href="./index.html"><img src="<c:url value='' />" alt=""></a>
                    </div>
                </div>
                <div class="col-lg-6">
                    <nav class="header__menu">
                        <ul>
                            <li class="active"><a href="/comcome">Home</a></li>                 
                            <li><a href="/comcome/usedBoard/list">중고거래</a></li>
                            <li><a href="/comcome/saleProduct/list">특가상품</a>
                                <ul class="header__menu__dropdown">
                                    <li><a href="./shop-details.html">Shop Details</a></li>
                                    <li><a href="./shoping-cart.html">Shoping Cart</a></li>
                                    <li><a href="./checkout.html">Check Out</a></li>
                                    <li><a href="./blog-details.html">Blog Details</a></li>
                                </ul>
                            </li>
                          <c:if test="${empty sessionScope.email}">
                            <li><a href="<c:url value='/login/login-form'/>">로그인</a></li>
                             </c:if>
                             <c:if test="${!empty sessionScope.email}">
                             <li><a href="<c:url value='/login/logout'/>">로그아웃</a></li>
                             <li><a href="<c:url value='/mypage/index'/>">마이페이지</a></li>
                             </c:if>
                        </ul>
                    </nav>
                </div>
                <div class="col-lg-3">
                    <div class="header__cart">
                        <ul>
                            <!-- <li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li> -->
                            <li>
                            	<a href="#"><i id="shopping_bag_ico" class="fa fa-shopping-bag"></i>
                             	<span>
                             		<c:if test="${empty sessionScope.email}">
                        				0
                        			</c:if>
                             		<c:if test="${not empty sessionScope.email}">
                        				
                        			</c:if>
                             	</span></a>
                            </li>
                            <li><i class="fa-brands fa-app-store-ios"></i></li>
                        </ul>
                       <%--  <c:if test="${empty sessionScope.email}">
                        	<div class="header__cart__price">item: <span>$150.00</span></div>
                        </c:if> --%>
                        <c:if test="${!empty sessionScope.email}">
                        	<div class="header__cart__price">${sessionScope.name} 님💻</div>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="humberger__open">
                <i class="fa fa-bars"></i>
            </div>
        </div>
    </header>
    
   	<section class="hero hero-normal">
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="hero__categories">
                        <div class="hero__categories__all">
                            <i class="fa fa-bars"></i>
                            <span>카테고리</span>
                            <span id="cat_cnt">${sessionScope.brandSize}</span>
                        </div>
                        <ul>
                        	<li><a href="<c:url value="/searchpd/list"/>" >노트북 전체</a></li>
                        	<c:import url="/category" />
                        </ul>
                    </div>
                </div>
                <div class="col-lg-9">
                    <div class="hero__search">
                        <div class="hero__search__form">
                            <form action="<c:url value="/searchpd/list" />">
                               <!--  <div class="hero__search__categories">
                                    전체카테고리
                                    <span class="arrow_carrot-down"></span>
                                </div> -->
                                <input type="text" name="keyword" placeholder="검색">
                                <button type="submit" class="site-btn">SEARCH</button>
                            </form>
                        </div>
                        <!-- <div class="hero__search__phone">
                            <div class="hero__search__phone__icon">
                                <i class="fa fa-phone"></i>
                            </div>
                            <div class="hero__search__phone__text">
                                <h5>+65 11.188.888</h5>
                                <span>support 24/7 time</span>
                            </div>
                        </div> -->
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>