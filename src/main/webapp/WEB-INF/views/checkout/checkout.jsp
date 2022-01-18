<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.security.MessageDigest" %>
<%@ page import="org.apache.commons.codec.binary.Hex" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매</title>
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
    
	<script type="text/javascript" src="<c:url value="/resources/js/api/daumPostCode.js" />"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    
    <script src="https://web.nicepay.co.kr/v3/webstd/js/nicepay-3.0.js" type="text/javascript"></script>
	<style type="text/css">
		#section {
			max-width: 1170px;
			margin: auto;
		}
		
		#form_wrap, #bill_warp{
			margin: 10px;
			padding: 10px;
		}
		
		.order_input {
			border: 1px solid #8b8b8b;
		    border-radius: 5px;
		    height: 40px;
		    width: 300px;
		    padding: 10px;
		    margin: 5px;
		}
		
		#address_detail{
			width: 615px;
		}
		
		.input_wrap {
			margin-bottom: 15px;
		}
		
		#search_address {
			background: #dbdfff;
		    border: 1px solid #8b8b8b;
		    border-radius: 10px;
		    padding: 7px;
		}
		
		#check_wrap {
			margin: 10px;
		}
		.pd_block {
			background: skyblue;
			margin: 10px;
			padding: 10px;
			display: flex;
			justify-content: space-between;
			align-items: center;
		}
		
		.pd_img {
			width: 100px;
		}
		
		.pd_name {
			flex-basis: 70%;
		}
		
		.quantity {
			
		}
		
		.pd_price {
			flex-basis: 10%;
		}
	</style>
	<script type="text/javascript">
		$(function() {
			var name = '${accountVo.name}';
			var email = '${accountVo.email}';
			var zipcode = '${accountVo.zipcode}';
			var address = '${accountVo.address}';
			var addressDetail = '${accountVo.addressDetail}';
			var tel = '${accountVo.tel}';
			
			$('#order_info_checked').click(function() {
				if($('#order_info_checked').is(":checked")) {
					$('#name').val(name);
					$('#email').val(email);
					$('#zipcode').val(zipcode);
					$('#address').val(address);
					$('#addressDetail').val(addressDetail);
					$('#tel').val(tel);
				} else {
					$('#order_form input').val('');
				}
			});
			
		});
		
			function nicepayStart(){
				var buyerName = document.getElementById('name').value;
				var buyerEmail = document.getElementById('email').value;
				var buyerTel = document.getElementById('tel').value;
				
				console.log(buyerName);
				console.log(buyerEmail);
				console.log(buyerTel);
				
				document.getElementById('b_name').value = buyerName;
				document.getElementById('b_email').value = buyerEmail;
				document.getElementById('b_tel').value = buyerTel;
				
				goPay(document.payForm);
			}
			
			function nicepaySubmit(){
				document.payForm.submit();
			}
	</script>
</head>
<body>
<section id="section">
    <div id="form_wrap">
    	<h3>받는 사람 정보</h3>
    	<hr>
    	<div id="check_wrap">
    		<input id="order_info_checked" type="checkbox">
    		회원 정보와 동일합니다.
    	</div>
   		<div class="input_wrap">
   			<div class="input_label">이름</div>
   			<input class="order_input" id="name" type="text" placeholder="이름"  name="buyerName"/>
   		</div>
   		<div class="input_wrap">
   			<div class="input_label">이메일</div>
   			<input class="order_input" id="email"type="text" placeholder="이메일" name="buyerEmail"/>
   		</div>
   		<div class="input_wrap">
   			<div class="input_label">주소</div>
    		<input class="order_input" id="zipcode"type="text" placeholder="우편번호" disabled="disabled"/>
    		<input class="order_input" id="address"type="text" placeholder="주소" disabled="disabled"/>
    		<button type="button" id="search_address" onclick="daumPostcode()">주소 찾기</button><br>
   			<input class="order_input" id="address_detail" type="text" placeholder="상세주소"/>
   		</div>
   		<div class="input_wrap">
   			<div class="input_label">휴대폰번호</div>
   			<input class="order_input" id="tel" type="text" placeholder="- 제외"/>
   		</div>
    </div>
    <div id="bill_warp">
    	<h3>주문 상품 정보</h3>
    	<hr>
    	<c:set var="totalPrice" value="0"/>
    	<c:forEach items="${cartMapList}" var="map">
	    	<div class="pd_block">
	    		<div class="pd_img">
	    			<img src="<c:url value='/resources/user_uploaded_file/testboard/${map.THUMB_NAIL_IMG}'/>">
	    		</div>
	    		<div class="pd_name">
	    			${map.NAME}
	    		</div>
	    		<div class="quantity">
	    			${map.QUANTITY}개
	    		</div>
	    		<div class="pd_price">
	    			<fmt:formatNumber type="number" maxFractionDigits="3" value="${map.PRICE * map.QUANTITY}"/>
	    			원
	    			<c:set var="totalPrice" value="${ totalPrice + (map.PRICE * map.QUANTITY) }"/>
	    		</div>
	    	</div>
    	</c:forEach>
    	<div>
    		<fmt:formatNumber type="number" maxFractionDigits="3" value="${totalPrice}"/>원
    	</div>
    	<button type="button" id="checkout" onclick="nicepayStart()">결제하기</button>
    </div>
</section>

<!-- 결제 폼 -->
<form name="payForm" method="post" action="<c:url value="/checkout/result" />">
	<!-- 결제 상품명 -->
	<input type="hidden" name="GoodsName" value="${goodsName}">
	<!-- 결제 상품금액 -->
	<input type="hidden" name="Amt" value="${price}">
	<!-- 상점 아이디 -->
	<input type="hidden" name="MID" value="${merchantID}">
	<!-- 상품 주문번호 -->
	<input type="hidden" name="Moid" value="${moid}">
	<!-- 구매자명 -->
	<input id="b_name" type="hidden" name="BuyerName" value="">
	<!-- 구매자명 이메일 -->
	<input id="b_email" type="hidden" name="BuyerEmail" value="">
	<!-- 구매자 연락처 -->
	<input id="b_tel" type="hidden" name="BuyerTel" value="">
				
	<!-- 변경 불가능 -->
	<input type="hidden" name="EdiDate" value="${ediDate}"/>			<!-- 전문 생성일시 -->
	<input type="hidden" name="SignData" value="${hashString}"/>		<!-- 해쉬값 -->
</form>
</body>
</html>
<%@ include file="../include/footer.jsp"%>