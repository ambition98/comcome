<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ComCome 상품상세</title>
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
    <script src="<c:url value='/resources/js/base/query-ui.min.js' />"></script>
    <script src="<c:url value='/resources/js/base/jquery.slicknav.js' />"></script>
    <script src="<c:url value='/resources/js/base/mixitup.min.js' />"></script>
    <script src="<c:url value='/resources/js/base/owl.carousel.min.js' />"></script>
    <script src="<c:url value='/resources/js/base/main.js' />"></script>
    
    <link rel="stylesheet" href="<c:url value='/resources/css/category.css' />" type="text/css">
	<script src="<c:url value='/resources/js/category.js' />"></script>
<style type="text/css">
	#mall_list a {
		color: black;
	}
	
	#mall_list a:hover {
		color: black;
		
	}
	
	.section {
		max-width: 1170px;
		margin: auto;
	}

	.top_element {
		display: inline-block;
		width: 46%;
		margin: auto;
	}
	
	#mall_table {
		width: 100%;
		height: 100%;
		margin-top: 50px;
	}
	
	#main_img img {
		margin-left: 20%
	}
	
	td {
		padding: 10px;
	}
	
	#detail_img {
		margin: auto;
		text-align: center;
	}
	
	#remote_cont {
		position: fixed;
		width: 60px;
		height: 20px;
		right: 20px;
		bottom: 20px;
		display: none;
	}
</style>

</head>
<body>
<div id="remote_cont">
	<div>맨위로</div>
	<div>맨아래로</div>
</div>
<section class="section">
	<div id="section_top">
		<h3>${vo.name}</h3>
		<div id="main_img" class="top_element">
			<img src="${imgLinkList[0]}" />
		</div>
		<div id="mall_list" class="top_element">
			<table id="mall_table">
				<tr id="lowest_price">
					<td class="mall">최저가</td>
					<td class="price"><fmt:formatNumber type="number" maxFractionDigits="3" value="${pdList[0].price}"/>원</td>
				</tr>
				<c:forEach var="pd" items="${pdList}">
						<tr>
							<td class="mall"><a href="${pd.link}">${pd.mallName}</a></td>
							<td class="price"><a href="${pd.link}"><fmt:formatNumber type="number" maxFractionDigits="3" value="${pd.price}"/>원</a></td>
						</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	
	<div id="detail_img">
		<c:forEach var="i" begin="1" end="${fn:length(imgLinkList)-1}">
			<div>
				<img src="${imgLinkList[i]}" />
			</div>
		</c:forEach>
	</div>
</section>
</body>
</html>
<%@ include file="../include/footer.jsp"%>