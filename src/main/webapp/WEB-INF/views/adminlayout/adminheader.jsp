<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>

	<title>ComCome 관리자 페이지</title>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	 <link rel="shortcut icon" type="image/x-icon" href="<c:url value='/resources/img/com_logo.png'/>">
	<!-- VENDOR CSS -->
	 <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/assets/bootstrap.min.css'/>" />
	 <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/assets/font-awesome.min.css'/>" />
	 <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/assets/style.css'/>" />
	 <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/assets/chartist-custom.css'/>" />
	<!-- MAIN CSS -->
	 <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/assets/main.css'/>" />
	<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
	 <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/assets/demo.css'/>" />
	<!-- GOOGLE FONTS -->
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
	<!-- ICONS -->
	<%-- <link rel="apple-touch-icon" sizes="76x76" href="<c:url value='/resources/img/apple-icon.png'/>">
	<link rel="icon" type="image/png" sizes="96x96" href="<c:url value='/resources/img/favicon.png'/>"> --%>
</head>
<body>
<%
String email=(String)session.getAttribute("emailadmin");
%>
	<script src="<c:url value='/resources/js/assets/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/assets/bootstrap.min.js'/>"></script>
	<script src="<c:url value='/resources/js/assets/jquery.slimscroll.min.js'/>"></script>
	<script src="<c:url value='/resources/js/assets/jquery.easypiechart.min.js'/>"></script>
	<script src="<c:url value='/resources/js/assets/chartist.min.js'/>"></script>
	<%-- <script src="<c:url value='/resources/js/assets/klorofil-common.js'/>"></script> --%>
	 <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/admin/admincss2.css'/>" />
	
	<!-- WRAPPER -->
	<div id="wrapper">
		<!-- NAVBAR -->
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="brand">
				<a href="<c:url value='/admin/allmemberview'/>" id="wpahr">관리자 페이지</a>
			</div>
			<div class="container-fluid">
			
				
			
				<div id="navbar-menu">
					<ul class="nav navbar-nav navbar-right">
						
						
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								<% if(email==null || email.isEmpty()){ %>
								 comcome🔧
                                <%}else{ %>
								<img src="<c:url value='/resources/img/set_logo.jpg'/>" class="img-circle" alt="Avatar"> <span> <%=email %></span></a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value='/admin/logout'/>"><span>Logout</span></a></li>
							</ul>
							<%} %>
						</li>
						
					</ul>
				</div>
			</div>
		</nav>
		<!-- END NAVBAR -->
		<!-- LEFT SIDEBAR -->
		<div id="sidebar-nav" class="sidebar">
			<div class="sidebar-scroll">
				<nav>
					<ul class="nav">
						<li><a href="<c:url value='/admin/allmemberview'/>" class="active"> <span>회원 조회 및 검색</span></a></li>
						<li><a href="<c:url value='/admin/boardwithmain'/>" class=""> <span>게시글 관리</span></a></li>
						<li>
							<a href="#subPages" data-toggle="collapse" class="collapsed"><span>특가몰 관리</span></a>
							<div id="subPages" class="collapse ">
								<ul class="nav">
									<li><a href="<c:url value='/admin/allsaleproduct'/>" class="">상품 목록</a></li>
									<li><a href="<c:url value='/admin/insert-sale-product'/>" class="">상품 등록</a></li>
								</ul>
							</div>
						</li>
						<li><a href="<c:url value='/admin/popup-regi-with-main'/>" class=""> <span>팝업 공지 등록</span></a></li>
						<li>
							<a href="#subPages" data-toggle="collapse" class="collapsed"><span>매출통계</span></a>
							<div id="subPages" class="collapse ">
								<ul class="nav">
									<li><a href="<c:url value='/admin/chart'/>" >일별</a></li>
									<li><a href="/admin/chart2" >월별</a></li>
									<li><a href="/admin/allsaleproduct" class="">기간별</a></li>
								</ul>
							</div>
						</li>
						
					</ul>
				</nav>
			</div>
		</div>
		<!-- END LEFT SIDEBAR -->