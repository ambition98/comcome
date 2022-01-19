<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
 

<%
String email=(String)session.getAttribute("email");
String name=(String)session.getAttribute("name");
%> 

<!-- index content2 -->
 <section class="wrap-3d"> 
 <c:if test="${!empty list }">
 <c:forEach var="vo" items="${list }"  begin="0" end="0">
  <div class="item-3d">
   <!--  <span class="ground"></span> -->
    <figure class="item-content group">
      <div class="item-img">
         <c:if test="${!empty vo.thumbNailImg }">
			<img id ="yourimg" src="<c:url value='/resources/user_uploaded_file/testboard/${ vo.thumbNailImg }'/>">
		</c:if>
      </div>
      <figcaption class="item-caption">
        <p>
          <strong id="123">오늘의 대박 특가</strong><br>
          
         
        </p>
        <p>
          <strong>${vo.name}</strong><br>
          <p id="oprice" >2,000,000원</p>
           <fmt:formatNumber value="${vo.price}" pattern="#,###"/> 원<br>
           ${vo.content} 
        </p>
        <p>
          <a href="#">View 38mm in the store</a><br>
        </p>
      </figcaption>
    </figure>
  </div>
  </c:forEach>
  </c:if>
  </section> 
  
  
  
  
  
  
  <!-- index content2 END -->

<!-- index content3  -->

<!-- <article class="flow"> -->
  <div class="team">
    <ul class="auto-grid" role="list">
      <li>
        <a href="<c:url value="/searchpd/list?brandNo=1" />" target=_blank" class="profile">
          <h2 class="profile__name">SAMSUNG</h2>
          <img alt="Anita Simmons" src="<c:url value='/resources/img/sam_lo2.png'/>" />
        </a>
      </li>
      <li>
        <a href="<c:url value="/searchpd/list?brandNo=2" />" target=_blank" class="profile">
          <h2 class="profile__name">APPLE</h2>
          <img alt="Profile shot for Celina Harris" src="<c:url value='/resources/img/apple_lo2.PNG'/>" />
        </a>
      </li>
      <li>
        <a href="<c:url value="/searchpd/list?brandNo=3" />" target=_blank" class="profile">
          <h2 class="profile__name">LG</h2>
          <img alt="Profile shot for Ruby Morris" src="<c:url value='/resources/img/lg_lo.jpg'/>" />
        </a>
      </li>
      <li>
        <a href="<c:url value="/searchpd/list?brandNo=7" />" target=_blank" class="profile">
          <h2 class="profile__name">HANSUNG</h2>
          <img alt="Profile shot for Nicholas Castro" src="<c:url value='/resources/img/han_lo.jpg'/>" />
        </a>
      </li>
      <li>
        <a href="<c:url value="/searchpd/list?brandNo=5" />" target=_blank" class="profile">
          <h2 class="profile__name">MSI</h2>
          <img alt="Profile shot for Marc Dixon" src="<c:url value='/resources/img/msi_lo.png'/>" />
        </a>
      </li>
      <li>
        <a href="<c:url value="/searchpd/list?brandNo=6" />" target=_blank" class="profile">
          <h2 class="profile__name">LENOVO</h2>
          <img alt="Profile shot for Chad" src="<c:url value='/resources/img/leno_lo.png'/>" />
        </a>
      </li>
    </ul>
</div>
<div id="brandImg">
	
</div>  
<!-- </article> -->
<!-- index content2 END -->



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
  
<div id="banner1">
	<img alt="사진" src="<c:url value='/resources/img/banner2.png'/>">
	<img alt="사진" src="<c:url value='/resources/img/banner4.png'/>">
</div>

 
<div id="banner2">
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
		                <a href="<c:url value='/usedBoard/countUpdate?boardNo=${vo.boardNo}'/>">
		                <div class="blog__item">
		                 <c:if test="${!empty vo.fileName }">
		                 	<div class="blog__item__pic">
								<img id ="yourimg" src="<c:url value='/resources/user_uploaded_file/usedboard/${ vo.fileName }'/>">
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
		                </a>
		            </div>
	            </c:forEach>
            </c:if>
        </div>
    </div>
</section>
<!-- Blog Section End -->
    
   