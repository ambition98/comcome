
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../mypageinc/mypageheader.jsp" %>
<style type="text/css">
	#divCart{
		width: 700px;
	}
	#divCart table{
		width: 700px;
	}
</style>
<script type="text/javascript">
	$(function(){
		$('form[name=frmCart]').submit(function(){
			if($(this).find('input[name=quantity]').val().length<1){
				alert('수량을 입력하세요');
				$(this).find('input[name=quantity]').focus();
				event.preventDefault();
			}			
		});
	});
</script>

<h2>장바구니</h2>
<div id="divCart">
<table class="box2" 
	summary="장바구니 목록에 관한 표로써 상품명,가격,수량,금액 등의 정보를 제공합니다.">
	<colgroup>
		<col style="width:40%">
		<col style="width:13%">
		<col style="width:20%">
		<col style="width:13%">
		<col style="width:*">		
	</colgroup>
	
	<thead>
		<tr>
			<th scope="col">상품명</th>
			<th scope="col">가격</th>
			<th scope="col">수량</th>
			<th scope="col">금액</th>
			<th scope="col">삭제</th>
		</tr>	
	</thead>
	<tbody>
		<c:if test="${empty list}">
			<tr>
				<td colspan="5" class="align_center">장바구니가 비었습니다.</td>
			</tr>
		</c:if>
		<c:if test="${!empty list}">
			<c:set var="totalPrice" value="0" />
			<c:set var="delivery" value="0" />
			<c:set var="sumPrice" value="0" />
			
			<!--반복 시작 -->
			<c:forEach var="map" items="${list }">
				<c:set var="sum" value="${map['PRICE'] * map['QUANTITY']}"/>
				
				<tr class="align_center">
					<td class="align_left">
					 <c:if test="${!empty  map['THUMB_NAIL_IMG'] }">
				<img class="book-card-img"  id ="yourimg" src="<c:url value='/resources/user_uploaded_file/testboard/${ map["THUMB_NAIL_IMG"] }'/>">
      				</c:if>
						${map['NAME'] }
					</td>
					<td class="align_right">
						<fmt:formatNumber value="${map['PRICE'] }" 
							pattern="#,###"/>원
					</td>
					<td>
						<form action="<c:url value='/shop/cart/cartEdit'/>" 
							method="post" name="frmCart">
							<input type="hidden" name="cartNo" 
								value="${map['WISHLIST_NO'] }">
							<input type="text" name="quantity" size="4" 
								value="${map['QUANTITY'] }">
							<button id="btEdit">수정</button>	
						</form>
					</td>
					<td class="align_right">
						<fmt:formatNumber value="${sum}" pattern="#,###"/>원
					</td>
					<td>
						<a href="<c:url value='/mypage/delete-cart?accountNo=${ map["ACCOUNT_NO"] }&saleProductNo=${ map["SALE_PRODUCT_NO"]}'/>">삭제 </a>
					</td>
				</tr>
				
				<c:set var="totalPrice" value="${totalPrice+sum }" />			
			</c:forEach>
			<!--반복 끝 -->
			
			<!-- 총 구매금액이 30000원 미만이면 배송비 3000원 -->
			<c:if test="${totalPrice < 30000 }">
				<c:set var="delivery" value="3000" />
			</c:if>
			
			<c:set var="sumPrice" value="${ totalPrice + delivery }" />

			<tr>
				<td colspan="4" class="align_right" style="border-right: none">
					총 구매금액 : <br>
					배송비 : <br>
					총 주문합계 : 
				</td>
				<td class="align_right" style="border-left: none">
					<fmt:formatNumber value="${totalPrice }" pattern="#,###"/>원<br>
					<fmt:formatNumber value="${delivery }" pattern="#,###"/>원<br>
					<fmt:formatNumber value="${sumPrice }" pattern="#,###"/>원<br>
				</td>
			</tr>
		</c:if>
	</tbody>
</table>

	<div class="align_center" style="margin:10px 0">
		<c:if test="${! empty list }">
			<a href="<c:url value='/shop/order/orderSheet'/>">[주문하기]</a>
		</c:if>
		
		<a href="<c:url value='/index'/>">[계속 쇼핑하기]</a>
	</div>	
</div>
</body>
</html>