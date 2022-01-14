<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp"%> 

<html>
<head>
<!-- index -->
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
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/index/indexcontent.css'/>" />
                                                          
    <link rel="stylesheet" href="<c:url value='/resources/css/category.css' />" type="text/css">
  	<script src="<c:url value='/resources/js/category.js' />"></script>
  	<!-- index끝 -->
</head>
<body>
	<div id="banner3">
		<img alt="사진" src="<c:url value='/resources/img/banner4.png'/>" id="banner3">
	</div>
	<div id="banner2">
		<img alt="사진" src="<c:url value='/resources/img/banner2.png'/>">
	</div>
</body>
</html>

<%@include file="indexcontent/indexcontent.jsp" %> 

<jsp:include page="admin/popup3.jsp"/>

<%@ include file="include/footer.jsp"%>