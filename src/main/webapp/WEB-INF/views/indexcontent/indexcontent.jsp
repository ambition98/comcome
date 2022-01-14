<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
 

<%
String email=(String)session.getAttribute("email");
String name=(String)session.getAttribute("name");
%> 
<div id="banner3">
 <img alt="사진" src="<c:url value='/resources/img/banner4.png'/>" id="banner3">
</div>
<div id="banner2">
 <img alt="사진" src="<c:url value='/resources/img/banner2.png'/>">
</div>
<!-- Related Product Section Begin -->
    <section class="related-product">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title related__product__title">
                        <h2>특가 상품</h2>
                    </div>
                </div>
            </div>
            <div class="row">
            	<c:if test="${!empty list }">
            	<c:forEach var="vo" items="${list }">
                <div class="col-lg-3 col-md-4 col-sm-6">
                    <div class="product__item">
                        <div class="product__item__pic set-bg" data-setbg="img/product/product-1.jpg">
                            <c:if test="${!empty vo.thumbNailImg }">
								<img id ="yourimg" src="<c:url value='/resources/user_uploaded_file/testboard/${ vo.thumbNailImg }'/>">
							</c:if>
                            <ul class="product__item__pic__hover">
                           
                              <% if(email==null || email.isEmpty()){ %>
                             <li><a href="<c:url value='/event/wish?email=1&saleProductNo=${vo.saleProductNo}'/>"><i class="fa fa-heart"></i></a></li>
                             <%}else{ %>
                             <c:set var="email" value="<%=email %>"></c:set>
                               <li><a href="<c:url value='/event/wish?saleProductNo=${vo.saleProductNo}&email=${email}'/>"><i class="fa fa-heart"></i></a></li>
                               <%} %>
                               
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="product__item__text">
                            <h6><a href="#">${vo.name }</a></h6>
                            <h5><fmt:formatNumber value="${vo.price}" pattern="#,###"/> 원</h5>
                        </div>
                    </div>
                </div>
                 </c:forEach>
                </c:if>
               
            </div>
        </div>
    </section> 
    <!-- Related Product Section End -->
  
  
    

 
 <div id="banner">
 <img alt="사진" src="<c:url value='/resources/img/banner3.png'/>">
</div> 
 
 
     <!-- Blog Section Begin -->
    <section class="from-blog spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title from-blog__title">
                        <h2>중고 거래 게시판</h2>
                    </div>
                </div>
            </div>
            <div class="row">
            	 <c:if test="${!empty list2 }">
            	 <c:forEach var="vo" items="${list2 }"  begin="0" end="6">
                <div class="col-lg-4 col-md-4 col-sm-6">
                    <div class="blog__item">
	                    <c:if test="${!empty vo.fileName }">
	                    	<div class="blog__item__pic">
								<img id ="yourimg" src="<c:url value='/resources/user_uploaded_file/testboard/${ vo.fileName }'/>">
						    </div>
						</c:if>
                        <div class="blog__item__text">
                            <ul>
                                <li><i class="fa fa-calendar-o"></i><fmt:formatDate value="${vo.regdate}" pattern="yyyy-MM-dd"/></li>
                                <li><i class="fa fa-comment-o"></i>${vo.readcount}</li>
                            </ul>
                            <h5><a href="#">${vo.title}</a></h5>
                            <p>${vo.content}</p>
                        </div>
                    </div>
                </div>
                </c:forEach>
                </c:if>
                
                
                
            
              
            </div>
        </div>
    </section>
    <!-- Blog Section End -->
    
   