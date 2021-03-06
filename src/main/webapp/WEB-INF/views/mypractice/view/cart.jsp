<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../mypageinc/mypageheader.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mypage/cart.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mypage/cartbutton.css'/>" />
<script type="text/javascript">
	$(function() {
		$('#AllAgree').click(function() {
			$('.' + this.id).prop('checked', this.checked);

			var $items = $(".book-cards").find("input:checkbox:checked");
			var $total = $("#price");
			var cur_total = 0;

			$items.each(function() {
				var $this = $(this);
				var $price = $('#price1').val();
				var $quantityforcal = $('#quantityforcal').val();
				var eachprice = $price * $quantityforcal;
				/*  var item_value = +($target.html().replace("$", "") || 0); */

				cur_total += eachprice;
			});

			var tostringprice = priceToString(cur_total);

			$total.html("총 " + tostringprice + " 원");

		});

		$(".book-cards").on("click", "input:checkbox", function() {
			var $items = $(".book-cards").find("input:checkbox:checked");
			var $total = $("#price");
			var cur_total = 0;

			$items.each(function() {
				var $this = $(this);
				var $price = $('#price1').val();
				var $quantityforcal = $('#quantityforcal').val();
				var eachprice = $price * $quantityforcal;
				/*  var item_value = +($target.html().replace("$", "") || 0); */

				cur_total += eachprice;
			});

			var tostringprice = priceToString(cur_total);

			$total.html("총 " + tostringprice + " 원");
		});

		function priceToString(cur_total) {
			return cur_total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
		}
		
		$('#checkout_form').submit(function() {
			var wishlistNoList = [];
			$('.book-card').each(function() {
				if($(this).find('#S-Agree').is(':checked')) {
					var no = $(this).find('.wishlistNo').val();
					wishlistNoList.push(no);
				}
				
			});
			$('#wishlist_no_list').val(wishlistNoList);
			$(this).submit();
		});
		
	});
</script>

<!-- 장바구니 시작 -->
<div class="popular-books">
	<!-- <div id="selectbox"> -->
	<c:if test="${empty list}">
		<tr>
			<td colspan="5" class="align_center">장바구니가 비었습니다.</td>
		</tr>
	</c:if>

	<!-- </div> -->
	<c:if test="${!empty list }">
		<div class="book-cards">
			<!-- popular books > book card 반복 시작 -->
			<!-- list2 시작 -->

			<c:set var="totalPrice" value="0" />
			<c:set var="delivery" value="0" />
			<c:set var="sumPrice" value="0" />
			<!--반복 시작 -->
			<c:forEach var="map" items="${list }">
				<c:set var="sum" value="${map['PRICE'] * map['QUANTITY']}" />
				<!-- 총합 계산용 -->
				<c:set var="price1" value="${map['PRICE']}" />
				<c:set var="quantityforcal" value="${map['QUANTITY']}" />
				<input type="hidden" name="price1" id="price1" value="${price1}">
				<input type="hidden" name="quantityforcal" id="quantityforcal"
					value="${quantityforcal}">
				<!-- 총합 계산용 끝 -->
				<div class="book-card">
					<div class="content-wrapper">
						<c:if test="${!empty  map['THUMB_NAIL_IMG'] }">
							<img class="book-card-img" id="yourimg"
								src="<c:url value='/resources/user_uploaded_file/testboard/${ map["THUMB_NAIL_IMG"] }'/>">
						</c:if>
					</div>
					<div class="card-content">
						<div class="book-name">${map['NAME'] }</div>
						<div class="book-by">
							<fmt:formatNumber value="${map['PRICE'] }" pattern="#,###" />
							원
						</div>
					</div>
					<!--card-content  -->
					<div class="rate">

						<input type="checkbox" id="S-Agree" class="AllAgree">
						<!--  <span class="book-voters card-vote">  -->
						<form class="book-voters card-vote" action="<c:url value='/mypage/cartEdit'/>" method="post"
							name="frmCart">
							<input name="wishlistNo" class="wishlistNo" type="hidden"
								value="${map['WISHLIST_NO'] }">
							<input type="text" id="quantity" name="quantity" size="4"
								value="${map['QUANTITY'] }">

							<button id="updatebutton">수정</button>
						</form>

						<!--  </span> -->
						<!-- list2 -->
					</div>

					<div class="book-sum card-sum">
						<a href="<c:url value='/mypage/delete-cart?accountNo=${ map["ACCOUNT_NO"] }&saleProductNo=${ map["SALE_PRODUCT_NO"]}'/>">
							삭제 </a>
					</div>
				</div>
			</c:forEach>
			<!-- list2 -->
		</div>

		<div class="btn-contain2" id="price"></div>
		<div id="select2">
			<input type="checkbox" id="AllAgree">
			<label for="AllAgree" id="allsel">전체 선택</label><br>
			<form id="checkout_form" action="<c:url value="/cart/checkout" />" method="get">
				<input id="wishlist_no_list" name="wishNoList" type="hidden" />
				<button type="submit" class="btn" id="checkout">구매</button>
			</form>
		</div>
	</c:if>
	<!-- 장바구니 끝 -->
</div>
</body>
</html>