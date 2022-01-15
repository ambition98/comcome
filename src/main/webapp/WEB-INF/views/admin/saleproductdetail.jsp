<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
					
 
<!-- REALTIME CHART -->
<h2 class="panel-title" id ="yourtitle">특가 상품 상세보기</h2>
							<div class="panel" id="innerpanel">
								<div class="panel-heading">
										  
									
									
									
								</div>
								<div class="panel-body">
									<c:if test="${!empty vo }">
									<h4>${vo.name}</h4>
									<p id="yourli">상품 번호 : <span id="yourspan">${vo.saleProductNo}</span></p>
									<p id="yourli">카테고리 번호 : <span id="yourspan">${vo.categoryNo}</span></p>
									<p id="yourli">가격 : <span id="yourspan"><fmt:formatNumber value="${vo.price}" pattern="#,###"/> 원</span></p>
									
									<ul class="list-unstyled list-justify">
										<div id="forimg">
										<c:if test="${!empty vo.thumbNailImg }">
									<img id ="yourimg" src="<c:url value='/resources/user_uploaded_file/testboard/${ vo.thumbNailImg }'/>">
									</c:if>
										</div>
										<li id="yourli2">내용</li>
										<li id="yourli3">${vo.content}</li>
										
									</ul>
								</div>
								<button id="deletevo" class="btn btn-primary btn-user btn-block" 
								onclick="del(${vo.saleProductNo})"
								  > 상품 삭제</button>
								 
								 <button id="deletevo" class="btn btn-primary btn-user btn-block"  ><a href ="<c:url value='/admin/sale-product-update?saleProductNo=${vo.saleProductNo}'/>">
									상품 수정
								</a></button>
								 
								</c:if>
							</div>
					