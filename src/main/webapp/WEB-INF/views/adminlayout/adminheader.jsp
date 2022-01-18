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
	
	<!-- <script>
	$(function() {
		var data, options;

		// headline charts
		data = {
			labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
			series: [
				[23, 29, 24, 40, 25, 24, 35],
				[14, 25, 18, 34, 29, 38, 44],
			]
		};

		options = {
			height: 300,
			showArea: true,
			showLine: false,
			showPoint: false,
			fullWidth: true,
			axisX: {
				showGrid: false
			},
			lineSmooth: false,
		};

		new Chartist.Line('#headline-chart', data, options);


		// visits trend charts
		data = {
			labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
			series: [{
				name: 'series-real',
				data: [200, 380, 350, 320, 410, 450, 570, 400, 555, 620, 750, 900],
			}, {
				name: 'series-projection',
				data: [240, 350, 360, 380, 400, 450, 480, 523, 555, 600, 700, 800],
			}]
		};

		options = {
			fullWidth: true,
			lineSmooth: false,
			height: "270px",
			low: 0,
			high: 'auto',
			series: {
				'series-projection': {
					showArea: true,
					showPoint: false,
					showLine: false
				},
			},
			axisX: {
				showGrid: false,

			},
			axisY: {
				showGrid: false,
				onlyInteger: true,
				offset: 0,
			},
			chartPadding: {
				left: 20,
				right: 20
			}
		};

		new Chartist.Line('#visits-trends-chart', data, options);


		// visits chart
		data = {
			labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
			series: [
				[6384, 6342, 5437, 2764, 3958, 5068, 7654]
			]
		};

		options = {
			height: 300,
			axisX: {
				showGrid: false
			},
		};

		new Chartist.Bar('#visits-chart', data, options);


		// real-time pie chart
		var sysLoad = $('#system-load').easyPieChart({
			size: 130,
			barColor: function(percent) {
				return "rgb(" + Math.round(200 * percent / 100) + ", " + Math.round(200 * (1.1 - percent / 100)) + ", 0)";
			},
			trackColor: 'rgba(245, 245, 245, 0.8)',
			scaleColor: false,
			lineWidth: 5,
			lineCap: "square",
			animate: 800
		});

		var updateInterval = 3000; // in milliseconds

		setInterval(function() {
			var randomVal;
			randomVal = getRandomInt(0, 100);

			sysLoad.data('easyPieChart').update(randomVal);
			sysLoad.find('.percent').text(randomVal);
		}, updateInterval);

		function getRandomInt(min, max) {
			return Math.floor(Math.random() * (max - min + 1)) + min;
		}

	});
	</script> -->
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