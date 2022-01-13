<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<div id="banner3">
 <img alt="사진" src="<c:url value='/resources/img/banner4.png'/>" id="banner3">
</div>
<div id="banner2">
 <img alt="사진" src="<c:url value='/resources/img/banner2.png'/>">
</div>
<%-- 

<div id="banner">
 <img alt="사진" src="<c:url value='/resources/img/banner3.png'/>">
</div>

    <!-- Featured Section Begin -->
    <section class="featured spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title">
                        <h2>특가 상품</h2>
                    </div>
                    <div class="featured__controls">
                        <ul>
                            <li class="active" data-filter="*">전체</li>
                            <li data-filter=".samsung">삼성</li>
                            <li data-filter=".LG">LG</li>
                            <li data-filter=".애플">애플</li>
                            <li data-filter=".기타">기타</li>
                        </ul>
                    </div>
                </div>
            </div>
             <c:if test="${!empty list }"><!-- 리스트가 비어있지 않으면 -->
              <c:forEach var="vo" items="${list }"><!-- for문 돌리기 -->
            		<div class="row featured__filter">
              			<c:set var ='brand' value="${vo.name }" ></c:set>
              			 <c:choose>
              					 <c:when test = "${fn:contains(brand,'삼성')}">
              				 	<div class="col-lg-3 col-md-4 col-sm-6 mix samsung" id="sam">
               					 </c:when>
               					 <c:when test = "${fn:contains(brand,'LG')}">
              				 	  <div class="col-lg-3 col-md-4 col-sm-6 mix LG" id="sam">
               					 </c:when>
               					  <c:when test = "${fn:contains(brand,'애플')}">
              				 	  <div class="col-lg-3 col-md-4 col-sm-6 mix 애플" id="sam">
               					 </c:when>
               					 
               					 <c:otherwise>
						          <div class="col-lg-3 col-md-4 col-sm-6 mix 기타" id="sam">
						         </c:otherwise>
               		     </c:choose>
                    <div class="featured__item">
                     
                        <div id="cart" class="featured__item__pic set-bg" data-setbg="<c:url value='/resources/img/samsung.jpg'/>"> 
                     
                            <ul class="featured__item__pic__hover" id="heart">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                         </div> 
                        <div class="featured__item__text" id="vovo">
                        	         <img alt="사진" src="<c:url value='/resources/img/samsung.jpg'/>">
                        	 
                            <h6><a href="#">${vo.name}</a></h6>
                            <h5><fmt:formatNumber value="${vo.price}" pattern="#,###"/> 원</h5>
                           
                          
                        </div>
                    </div>
                </div>
                </c:forEach>
                </c:if> --%>
               <!--  <div class="col-lg-3 col-md-4 col-sm-6 mix vegetables fastfood">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="img/featured/feature-2.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="#">Crab Pool Security</a></h6>
                            <h5>$30.00</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 mix vegetables fresh-meat">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="img/featured/feature-3.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="#">Crab Pool Security</a></h6>
                            <h5>$30.00</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 mix fastfood oranges">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="img/featured/feature-4.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="#">Crab Pool Security</a></h6>
                            <h5>$30.00</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 mix fresh-meat vegetables">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="img/featured/feature-5.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="#">Crab Pool Security</a></h6>
                            <h5>$30.00</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 mix oranges fastfood">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="img/featured/feature-6.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="#">Crab Pool Security</a></h6>
                            <h5>$30.00</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 mix fresh-meat vegetables">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="img/featured/feature-7.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="#">Crab Pool Security</a></h6>
                            <h5>$30.00</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 mix fastfood vegetables">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="img/featured/feature-8.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="#">Crab Pool Security</a></h6>
                            <h5>$30.00</h5>
                        </div>
                    </div>
                </div> -->
         <!--   <!--  </div> -->
         </div>  
    </section>
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
            	 <c:forEach var="vo" items="${list2 }"  begin="0" end="2">
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

  
  