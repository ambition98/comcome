<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  

<!-- 	<link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css"> -->
	<link rel="stylesheet" href="<c:url value='/resources/css/assets/font-awesome.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/assets/style.css'/>">
	<!-- MAIN CSS -->
	<link rel="stylesheet" href="<c:url value='/resources/css/assets/main.css'/>">
	<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
	<link rel="stylesheet" href="<c:url value='/resources/css/assets/demo.css'/>">
	<!-- GOOGLE FONTS -->
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
	<!-- ICONS -->
<!-- 	<link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96" href="assets/img/favicon.png"> -->
	 <link rel="shortcut icon" type="image/x-icon" href="<c:url value='/resources/img/com_logo.png'/>">
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value='/resources/img/com_logo.png'/>">
    
<style>
#wrapper #sidebar-nav, #wrapper .main {
    padding-top: 0px;
}
.main-content {
    padding: 0px 0px; 
}
</style>
<%
String email=(String)session.getAttribute("email");
String name=(String)session.getAttribute("name");
String tel=(String)session.getAttribute("tel");
String address=(String)session.getAttribute("address");
String cardNo=(String)session.getAttribute("cardNo");
%>

		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<div class="panel panel-profile">
						<div class="clearfix">
							<!-- LEFT COLUMN -->
							<div class="profile-left">
								<!-- PROFILE HEADER -->
								<div class="profile-header">
									<div class="overlay"></div>
									<div class="profile-main">
										<!-- <img src="assets/img/user-medium.png" class="img-circle" alt="Avatar"> -->
										<h3 class="name"><%=name%></h3>
										<span class="online-status status-available">Available</span>
									</div>
									<div class="profile-stat">
										<div class="row">
											<div class="col-md-4 stat-item">
												45 <span>장바구니</span>
											</div>
											<div class="col-md-4 stat-item">
												15 <span>관심상품</span>
											</div>
											<div class="col-md-4 stat-item">
												2174 <span>구매목록</span>
											</div>
										</div>
									</div>
								</div>
								<!-- END PROFILE HEADER -->
								<!-- PROFILE DETAIL -->
								<div class="profile-detail">
									<div class="profile-info">
										<h4 class="heading">프로필</h4>
										<ul class="list-unstyled list-justify">
											<li>Birthdate <span>24 Aug, 2016</span></li>
											<% if(tel==null || tel.isEmpty()){ %>
											<li>Mobile <span>등록해주세요</span></li>
											<%}else{ %>
											<li>Mobile <span><%=tel%></span></li>
											<%} %>
											<li>Email <span><%=email%></span></li>
											<li>Card <span><%=cardNo%></span></li>
										</ul>
									</div>

									<div class="profile-info">
										<h4 class="heading">About</h4>
										<p>Interactively fashion excellent information after distinctive outsourcing.</p>
									</div>
									<div class="text-center"><a href="<c:url value='/mypage/profile/profileEdit'/>" class="btn btn-primary">프로필수정</a></div>
								</div>
								<!-- END PROFILE DETAIL -->
							</div>
							</div>
							</div>
							</div>
							</div>
							</div>
							<!-- END WRAPPER -->
	<!-- Javascript -->
	<script src="<c:url value='/resources/css/assets/demo.css'/>"></script>
	<script src="<c:url value='/resources/js/assets/bootstrap.min.js'/>"></script>
	<script src="<c:url value='/resources/js/assets/jquery.slimscroll.min.js'/>"></script>
	<script src="<c:url value='/resources/js/assets/klorofil-common.js'/>"></script>
</body>

</html>
	
	