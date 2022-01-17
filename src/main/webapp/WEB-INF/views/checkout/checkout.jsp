<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
    	<form id="order_form" action="<c:url value="/checkout" />" method="post">
    		<div class="input_wrap">
    			<div class="input_label">이름</div>
    			<input class="order_input" id="name" type="text" placeholder="이름" />
    			
    			
    		</div>
    		<div class="input_wrap">
    			<div class="input_label">이메일</div>
    			<input class="order_input" id="email"type="text" placeholder="이메일"/>
    		</div>
    		<div class="input_wrap">
    			<div class="input_label">주소</div>
	    		<input class="order_input" id="zipcode"type="text" placeholder="우편번호" disabled="disabled"/>
	    		<input class="order_input" id="address"type="text" placeholder="주소" disabled="disabled"/>
	    		<button type="button" id="search_address" onclick="daumPostcode()">주소 찾기</button><br>
    			<input class="order_input" id="address_detail" type="text" placeholder="상세주소" />
    		</div>
    		<div class="input_wrap">
    			<div class="input_label">휴대폰번호</div>
    			<input class="order_input" id="tel"type="text" placeholder="010-0000-0000" />
    		</div>
    	</form>
    </div>
    <div id="bill_warp">
    	<h3>주문 상품 정보</h3>
    	<hr>
    	<div class="pd_block">
    		<div class="pd_img">
    			
    		</div>
    		<div class="pd_name">
    			
    		</div>
    		<div class="quantity">
    			
    		</div>
    		<div class="pd_price">
    			
    		</div>
    	</div>
    </div>
</section>
</body>
</html>
<%@ include file="../include/footer.jsp"%>