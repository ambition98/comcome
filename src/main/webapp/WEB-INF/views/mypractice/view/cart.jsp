<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../mypageinc/mypageheader.jsp" %>
 <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mypage/cart.css'/>" /> 
 <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mypage/cartbutton.css'/>" /> 


<%--  <c:if test="${!empty list }">
 <c:forEach var="vo" items="${list }">
 ${vo.wishlistNo}<br>
 ${vo.accountNo}<br>
 ${vo.saleProductNo}<br>
   
 </c:forEach>
 
 
 </c:if> 
  <c:if test="${!empty list2 }">
   <c:forEach var="vo" items="${list2 }">
 ${vo.saleProductNo}
 <fmt:formatNumber value="${vo.price}" pattern="#,###"/> 원

 <c:if test="${!empty vo.thumbNailImg }">
<img id ="yourimg" src="<c:url value='/resources/user_uploaded_file/testboard/${ vo.thumbNailImg }'/>">

</c:if>
 </c:forEach>
  </c:if> --%>
  
  
  <!-- wishlist content -->
  <!-- book store -->
 <!--  <div class="book-store">
  book slide
   <div class="book-slide">
  <div class="book js-flickity" data-flickity-options='{ "wrapAround": true }'>
   <div class="book-cell">
     <div class="book-img">
     <img src="https://images-na.ssl-images-amazon.com/images/I/81WcnNQ-TBL.jpg" alt="" class="book-photo">
    </div>
    <div class="book-content">
     <div class="book-title">BIG MAGIC</div>
     <div class="book-author">by Elizabeth Gilbert</div>
     <div class="rate">
      <fieldset class="rating">
       <input type="checkbox" id="star5" name="rating" value="5" />
       <label class="full" for="star5"></label>
       <input type="checkbox" id="star4" name="rating" value="4" />
       <label class="full" for="star4"></label>
       <input type="checkbox" id="star3" name="rating" value="3" />
       <label class="full" for="star3"></label>
       <input type="checkbox" id="star2" name="rating" value="2" />
       <label class="full" for="star2"></label>
       <input type="checkbox" id="star1" name="rating" value="1" />
       <label class="full" for="star1"></label>
      </fieldset>
      <span class="book-voters">1.987 voters</span>
     </div>
     <div class="book-sum">Readers of all ages and walks of life have drawn inspiration and empowerment from Elizabeth Gilbert’s books for years. </div>
     <div class="book-see">See The Book</div>
    </div>
   </div>
   <div class="book-cell">
    <div class="book-img">
     <img src="https://i.pinimg.com/originals/a8/b9/ff/a8b9ff74ed0f3efd97e09a7a0447f892.jpg" alt="" class="book-photo">
    </div>
    <div class="book-content">
     <div class="book-title">Ten Thousand Skies Above You</div>
     <div class="book-author">by Claudia Gray</div>
     <div class="rate">
      <fieldset class="rating blue">
       <input type="checkbox" id="star6" name="rating" value="5" />
       <label class="full1" for="star6"></label>
       <input type="checkbox" id="star7" name="rating" value="4" />
       <label class="full1" for="star7"></label>
       <input type="checkbox" id="star8" name="rating" value="3" />
       <label class="full1" for="star8"></label>
       <input type="checkbox" id="star9" name="rating" value="2" />
       <label class="full1" for="star9"></label>
       <input type="checkbox" id="star10" name="rating" value="1" />
       <label class="full1" for="star10"></label>
      </fieldset>
      <span class="book-voters">1.987 voters</span>
     </div>
     <div class="book-sum">The hunt for each splinter of Paul's soul sends Marguerite racing through a war-torn San Francisco.</div>
     <div class="book-see book-blue">See The Book</div>
    </div>
   </div>
   <div class="book-cell">
    <div class="book-img">
     <img src="https://www.abebooks.com/blog/wp-content/uploads/2016/08/10.jpg" alt="" class="book-photo">
    </div>
    <div class="book-content">
     <div class="book-title">A Tale For The Time Being</div>
     <div class="book-author">by Ruth Ozeki</div>
     <div class="rate">
      <fieldset class="rating purple">
       <input type="checkbox" id="star11" name="rating" value="5" />
       <label class="full" for="star11"></label>
       <input type="checkbox" id="star12" name="rating" value="4" />
       <label class="full" for="star12"></label>
       <input type="checkbox" id="star13" name="rating" value="3" />
       <label class="full" for="star13"></label>
       <input type="checkbox" id="star14" name="rating" value="2" />
       <label class="full" for="star14"></label>
       <input type="checkbox" id="star15" name="rating" value="1" />
       <label class="full" for="star15"></label>
      </fieldset>
      <span class="book-voters">1.987 voters</span>
     </div>
     <div class="book-sum">In Tokyo, sixteen-year-old Nao has decided there’s only one escape from her aching loneliness and her classmates’ bullying.</div>
     <div class="book-see book-pink">See The Book</div>
    </div>
   </div>
   <div class="book-cell">
    <div class="book-img">
     <img src="https://images-na.ssl-images-amazon.com/images/I/81af+MCATTL.jpg" alt="" class="book-photo">
    </div>
    <div class="book-content">
     <div class="book-title">The Great Gatsby</div>
     <div class="book-author">by F.Scott Fitzgerald</div>
     <div class="rate">
      <fieldset class="rating yellow">
       <input type="checkbox" id="star16" name="rating" value="5" />
       <label class="full" for="star16"></label>
       <input type="checkbox" id="star17" name="rating" value="4" />
       <label class="full" for="star17"></label>
       <input type="checkbox" id="star18" name="rating" value="3" />
       <label class="full" for="star18"></label>
       <input type="checkbox" id="star19" name="rating" value="2" />
       <label class="full" for="star19"></label>
       <input type="checkbox" id="star20" name="rating" value="1" />
       <label class="full" for="star20"></label>
      </fieldset>
      <span class="book-voters">1.987 voters</span>
     </div>
     <div class="book-sum">The Great Gatsby, F. Scott Fitzgerald’s third book, stands as the supreme achievement of his career.</div>
     <div class="book-see book-yellow">See The Book</div>
    </div>
   </div>
   <div class="book-cell">
    <div class="book-img">
     <img src="https://images-na.ssl-images-amazon.com/images/I/81UWB7oUZ0L.jpg" alt="" class="book-photo">
    </div>
    <div class="book-content">
     <div class="book-title">After You</div>
     <div class="book-author">by Jojo Moyes</div>
     <div class="rate">
      <fieldset class="rating dark-purp">
       <input type="checkbox" id="star21" name="rating" value="5" />
       <label class="full" for="star21"></label>
       <input type="checkbox" id="star22" name="rating" value="4" />
       <label class="full" for="star22"></label>
       <input type="checkbox" id="star23" name="rating" value="3" />
       <label class="full" for="star23"></label>
       <input type="checkbox" id="star24" name="rating" value="2" />
       <label class="full" for="star24"></label>
       <input type="checkbox" id="star25" name="rating" value="1" />
       <label class="full" for="star25"></label>
      </fieldset>
      <span class="book-voters">1.987 voters</span>
     </div> 
     <div class="book-sum">Louisa Clark is no longer just an ordinary girl living an ordinary life. After the transformative six months spent.</div>
     <div class="book-see book-purple">See The Book</div>
    </div>
   </div>
  </div>
 </div> 
 book slide end
 books of the year 
    <div class="week year">
    <div class="author-title">Books of the year</div>
    <div class="year-book">
     <img src="https://images-na.ssl-images-amazon.com/images/I/A1kNdYXw0GL.jpg" alt="" class="year-book-img">
     <div class="year-book-content">
      <div class="year-book-name">Disappearing Earth</div>
      <div class="year-book-author">by Julia Phillips</div>
     </div>
    </div>
    <div class="year-book">
     <img src="https://images-na.ssl-images-amazon.com/images/I/81eI0ExR+VL.jpg" alt="" class="year-book-img">
     <div class="year-book-content">
      <div class="year-book-name">Lost Children Archive</div>
      <div class="year-book-author">by Valeria Luiselli</div>
     </div>
    </div>
    <div class="year-book">
     <img src="https://images-na.ssl-images-amazon.com/images/I/81OF9eJDA4L.jpg" alt="" class="year-book-img">
     <div class="year-book-content">
      <div class="year-book-name">Phantoms: A Thriller </div>
      <div class="year-book-author">by Dean Koontz</div>
     </div>
    </div>
    <div class="year-book">
     <img src="https://m.media-amazon.com/images/I/515FWPyZ-5L.jpg" alt="" class="year-book-img">
     <div class="year-book-content">
      <div class="year-book-name">Midnight in Chernobyl</div>
      <div class="year-book-author">by Adam Higginbotham</div>
     </div>
    </div>
    <div class="year-book">
     <img src="https://images-na.ssl-images-amazon.com/images/I/91dBtgERNUL.jpg" alt="" class="year-book-img">
     <div class="year-book-content">
      <div class="year-book-name">10 Minutes 38 Seconds</div>
      <div class="year-book-author">by Elif Shafak</div>
     </div>
    </div>
   </div>  
    books of the year end  
  <  <div class="overlay"></div> 
  </div> -->
  <!-- book store end -->
  <!-- popular books -->
  <div class="popular-books">
   <div class="main-menu">
    <div class="genre">장바구니</div>
   
   </div>
   <div class="book-cards">
   <!-- popular books > book card 반복 시작 -->
   <!-- list2 시작 -->
 <c:if test="${!empty list }">
    <c:forEach var="map" items="${list }">
    		<div class="book-card">
    		 <div class="content-wrapper">
    		 ${map['thumbNailImg'] }
   		 	 <c:if test="${!empty vo.thumbNailImg }">
				<img class="book-card-img"  id ="yourimg" src="<c:url value='/resources/user_uploaded_file/testboard/${ vo.thumbNailImg }'/>">
      		</c:if><!--썸네일  -->
      		</div>
      <div class="card-content">
      <div class="book-name">${vo.name}</div>
      <div class="book-by"><fmt:formatNumber value="${vo.price}" pattern="#,###"/> 원</div>
      </div><!--card-content  -->
      <div class="rate">
		 
			
			<span class="book-voters card-vote"> <c:out value="${vo.quantity}" /></span>
			
   <!-- list2 -->
</div>
       
       <div class="book-sum card-sum"><a href ="<c:url value='/mypage/delete-cart?accountNo=${accountNo}&saleProductNo=${vo.saleProductNo}'/>">
									삭제
								</a></div>
      </div>
   </c:forEach><!-- list2 -->
</c:if>
       
   
    </div>
    
    
    
   <!--   <div class="likes">
     <div class="like-profile">
    <img src="https://randomuser.me/api/portraits/women/63.jpg" alt="" class="like-img">
   </div>
     <div class="like-profile">
    <img src="https://pbs.twimg.com/profile_images/2452384114/noplz47r59v1uxvyg8ku.png" alt="" class="like-img">
   </div>
     <div class="like-profile">
    <img src="https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1400&q=80" alt="" class="like-img">
   </div>
     <div class="like-name"><span>Samantha William</span> and<span> 2 other friends</span> like this</div>
   </div>
    </div> -->
    
  
    <!-- popular books > book card 반복 끝 -->
   </div>
  </div>
  <!--  popular books end -->
 </div>
</div>
<!-- 합계 -->
<div class="btn-contain2">
	<c:set var = "total" value = "0" />
	<c:forEach var="vo" items="${list3}" varStatus="status">     
	
	<c:set var= "total" value="${total + vo.price}"/>
	</c:forEach>
<p id="sum">총 <fmt:formatNumber value="${total}" pattern="#,###"/>원</p>

 
</div>

<div class="btn-contain">
  <button class="btn">구매</button>
  <div class="btn-particles">
  </div>
</div>
 
  <!-- wishlist content end -->
  <script src="<c:url value='/resources/js/mypage/cartbutton.js' />"></script>
  
  
  
  
  

</body>
</html>