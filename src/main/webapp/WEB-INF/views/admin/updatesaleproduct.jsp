<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
	
					
<!--  addsaleproduct
 1. 폼 만들기 
 saleProductNo
 categoryNo
 name
 price 
 
 2. postmapping controller 만들기 
 -->
 <!-- REALTIME CHART -->
<h2 class="panel-title" id ="yourtitle">특가 상품 수정</h2>
	<div class="panel" id="innerpanel">
		
			<div class="panel-body">
			<h4>이름, 가격, 카테고리 번호를 입력해주세요</h4>
			<form name="commentForm" method="post" 
			<%-- action="<c:url value='/admin/post-sale-product'/>" --%> >
				
					<input type="hidden" value="${saleProductNo}" name = "saleProductNo"
					id="saleProductNo">
					<ul class="list-unstyled list-justify">
					<c:if test="${!empty saleProductNo }">
						<li id="yourli2">상품 번호 : ${saleProductNo} </li>
						
						</c:if>
						
						<li id="yourli2">상품 이름 : <input class=".infobox" type="text" id="name"
												 name="name" ></li>
						<li id="yourli2">상품 가격 : <input class=".infobox" type="text" id="price" 
												 name="price" numberOnly> 원</li>						
					
						<li id="yourli4">카테고리 번호 : </li>
						<select id ="categoryno" name = "categoryNo">
						 <c:if test="${!empty list }">
						    <c:forEach var="vo" items="${list }">
						    <option  value="${vo.categoryNo}" 
			            	>${vo.categoryNo}</option>
						    </c:forEach>
						 </c:if>  
						</select>
					
					</ul>
					
				
			
			</div>
								
			<input class="btn btn-primary btn-user btn-block"  id="deletevo"  type = "submit" value="등록"/>
		</div>
	     </form>
								 
							
							<!-- END REALTIME CHART -->