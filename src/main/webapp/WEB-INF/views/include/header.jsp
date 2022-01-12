<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/resources/img/com_logo.png'/>">
</head>
<body>
<%
String email=(String)session.getAttribute("email");
String name=(String)session.getAttribute("name");
%>
    <header class="header">
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="header__logo">
                        <a href="./index.html"><img src="img/logo.png" alt=""></a>
                    </div>
                </div>
                <div class="col-lg-6">
                    <nav class="header__menu">
                        <ul>
                            <li class="active"><a href="../">Home</a></li>
                            <li><a href="/comcome/usedBoard/list">중고거래</a></li>
                            <li><a href="#">특가상품</a>
                                <ul class="header__menu__dropdown">
                                    <li><a href="./shop-details.html">Shop Details</a></li>
                                    <li><a href="./shoping-cart.html">Shoping Cart</a></li>
                                    <li><a href="./checkout.html">Check Out</a></li>
                                    <li><a href="./blog-details.html">Blog Details</a></li>
                                </ul>
                            </li>
                            <% if(email==null || email.isEmpty()){ %>
                            <li><a href="<c:url value='/login/login-form'/>">로그인</a></li>
                             <%}else{ %>
                             <li><a href="<c:url value='/login/logout'/>">로그아웃</a></li>
                            <li><a href="./contact.html">마이페이지</a></li>
                              <%} %>
                        </ul>
                    </nav>
                </div>
                <div class="col-lg-3">
                    <div class="header__cart">
                        <ul>
                            <li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
                            <li><a href="#"><i class="fa fa-shopping-bag"></i> <span>3</span></a></li>
                            <li><i class="fa-brands fa-app-store-ios"></i></li>
                        </ul>
                          <% if(email==null || email.isEmpty()){ %>
                        <div class="header__cart__price">item: <span>$150.00</span></div>
                         <%}else{ %>
                          <div class="header__cart__price"><%=name %>님💻</div>
                         <%} %>
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
                        </div>
                        <ul>
                        	<li><a href="<c:url value="/searchpd/list"/>" >노트북 전체</a></li>
                        	<c:import url="/category" />
                        </ul>
                    </div>
                    <!-- <div>
	                    <ul id="size_cat">
							<li>sub1</li>
							<li>sub2</li>
							<li>sub3</li>
						</ul>
                    </div> -->
                    <!-- <div class="hero__categories">
                    	<ul>
                    		<li><a href="#">Fresh Meat</a></li>
                            <li><a href="#">Vegetables</a></li>
                            <li><a href="#">Fruit &amp; Nut Gifts</a></li>
                            <li><a href="#">Fresh Berries</a></li>
                            <li><a href="#">Ocean Foods</a></li>
                            <li><a href="#">Butter &amp; Eggs</a></li>
                            <li><a href="#">Fastfood</a></li>
                            <li><a href="#">Fresh Onion</a></li>
                            <li><a href="#">Papayaya &amp; Crisps</a></li>
                            <li><a href="#">Oatmeal</a></li>
                            <li><a href="#">Fresh Bananas</a></li>
                    	</ul>
                    </div> -->
                </div>
                <div class="col-lg-9">
                    <div class="hero__search">
                        <div class="hero__search__form">
                            <form action="<c:url value="/searchpd/list" />">
                                <div class="hero__search__categories">
                                    All Categories
                                    <span class="arrow_carrot-down"></span>
                                </div>
                                <input type="text" name="keyword" placeholder="검색">
                                <button type="submit" class="site-btn">SEARCH</button>
                            </form>
                        </div>
                        <div class="hero__search__phone">
                            <div class="hero__search__phone__icon">
                                <i class="fa fa-phone"></i>
                            </div>
                            <div class="hero__search__phone__text">
                                <h5>+65 11.188.888</h5>
                                <span>support 24/7 time</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>