<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="shortcut icon" type="image/x-icon" href="<c:url value='/resources/img/com_logo.png'/>">
<title>comcome 중고게시판 상세보기</title>
<link rel="stylesheet"
	href="<c:url value='/resources/css/base/bootstrap.min.css' />"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/resources/css/base/font-awesome.min.css"' />"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/resources/css/base/nice-select.css' />"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/resources/css/base/jquery-ui.min.css' />"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/resources/css/base/owl.carousel.min.css' />"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/resources/css/base/slicknav.min.css' />"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/resources/css/base/style.css' />" type="text/css">

<script src="<c:url value='/resources/js/base/jquery-3.3.1.min.js' />"></script>
<script src="<c:url value='/resources/js/base/bootstrap.min.js' />"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.9.2/jquery.contextMenu.css"
	integrity="sha512-EF5k2tHv4ShZB7zESroCVlbLaZq2n8t1i8mr32tgX0cyoHc3GfxuP7IoT8w/pD+vyoq7ye//qkFEqQao7Ofrag=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script
	src="<c:url value='/resources/js/base/jquery.nice-select.min.js' />"></script>
<script src="<c:url value='/resources/js/base/query-ui.min.js' />"></script>
<script src="<c:url value='/resources/js/base/jquery.slicknav.js' />"></script>
<script src="<c:url value='/resources/js/base/mixitup.min.js' />"></script>
<script src="<c:url value='/resources/js/base/owl.carousel.min.js' />"></script>
<script src="<c:url value='/resources/js/base/main.js' />"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.min.js"></script>
<script src="/resouces/js/contextMenu/context-menu.js"></script>
<link href="/resouces/css/contextMenu/context-menu.css" rel="stylesheet">
<style type="text/css">
a{ color: black; text-decoration: none;}
a:hover { color: blue; text-decoration: none;}
a:active{color:black; text-decoration: none;}
a:visited{color:black; text-decoration: none;}
p{color:black;}
</style>
<script>
$(function(){
    <!-- hover -->
    $.contextMenu({
        selector: '.hover',
        trigger: 'hover',
        callback: function(key, options) {
            var m = "clicked: " + key;
            window.console && console.log(m) || alert(m);
        },
        items: {
            "edit": {name: "Edit", icon: "edit"},
            "cut": {name: "Cut", icon: "cut"},
            "copy": {name: "Copy", icon: "copy"},
            "paste": {name: "Paste", icon: "paste"},
            "delete": {name: "Delete", icon: "delete"},
            "sep1": "---------",
            "quit": {name: "Quit", icon: function($element, key, item){ return 'context-menu-icon context-menu-icon-quit'; }}
        }
    });
 
    <!-- 왼쪽 클릭 -->
    $.contextMenu({
        selector: '.left',
        trigger: 'left',
        callback: function(key, options) {
            var m = "clicked: " + key;
            window.console && console.log(m) || alert(m);
        },
        items: {
            "edit": {name: "Edit", icon: "edit"},
            "cut": {name: "Cut", icon: "cut"},
            "copy": {name: "Copy", icon: "copy"},
            "paste": {name: "Paste", icon: "paste"},
            "delete": {name: "Delete", icon: "delete"},
            "sep1": "---------",
            "quit": {name: "Quit", icon: function($element, key, item){ return 'context-menu-icon context-menu-icon-quit'; }}
        }
    });
    
    $(document).ready(function(){
    	  $('#example').contextmenu({
    	    target:"#context-menu"
    	  });
    	});
    
    
    
    
    </script>

<style>
a:focus{
	color:#f00;
}
</style>


</head>
<body>
	<section class="breadcrumb-section set-bg"
		data-setbg="../resources/img/breadcrumb.jpg">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<div class="breadcrumb__text">

						<h2 style="color: black;">중고 장터</h2>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Breadcrumb Section End -->

	 <!-- Product Details Section Begin -->
    <section class="product-details spad ">
        <div class="container">
            <div class="row"   >
            <div class="col-lg-1 col-md-1"></div>
                <div class="col-lg-6 col-md-6">
                    <div class="product__details__pic">
                        <div class="product__details__pic__item">
                            <img src="../resources/user_uploaded_file/usedboard/${vo.fileName}" alt=""
					style="width: auto; height: auto;">
                        </div>
                        <div class="product__details__pic__slider owl-carousel">
                            <img data-imgbigurl="img/product/details/product-details-2.jpg"
                                src="img/product/details/thumb-1.jpg" alt="">
                            <img data-imgbigurl="img/product/details/product-details-3.jpg"
                                src="img/product/details/thumb-2.jpg" alt="">
                            <img data-imgbigurl="img/product/details/product-details-5.jpg"
                                src="img/product/details/thumb-3.jpg" alt="">
                            <img data-imgbigurl="img/product/details/product-details-4.jpg"
                                src="img/product/details/thumb-4.jpg" alt="">
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4" >
                    <div class="product__details__text">
                        <h3>${vo.title }</h3>
                        
                        <div class="product__details__price"><fmt:formatNumber value="${vo.price }" pattern="#,###" /> 원</div>
                     
                        
                        <ul>
                       	    <li><b>작성자</b> <span><a href='' id="profile">${vo.email}</a></span></li>
                            <li><b>등록일</b> <span><fmt:formatDate
							value="${vo.regdate}" pattern="yyyy-MM-dd HH:mm" /></span></span></li>
                            <li><b>조회수</b> <span>${vo.readcount }</span></li>
                            
                         
                        </ul>
                    </div>
                </div>
                
            </div>
        </div>
    </section>
    <!-- Product Details Section End -->
	
	<div class="col-lg-10  text-center" style="float: none; margin: 0 auto;">
		
			<%
			pageContext.setAttribute("newLine", "\r\n");
			%>

			<div class="lastDiv">
				<p class="content">${fn:replace(vo.content, newLine, "<br>")} </p>
			</div>


			<div class="col-lg-7">
			<form id="delform2" name="delform2" method="post" action="/usedBoard/delete">
				<input type="hidden" name="BoardNo" value="${vo.boardNo}">
				<input type="hidden" name="fileName" value="${vo.fileName}">
				<a href='<c:url value="/usedBoard/list"/>'>목록</a>
				<c:if test = "${sessionScope.email == vo.email}">|					
				<a href='<c:url value="/usedBoard/edit?boardNo=${param.boardNo}"/>'> 수정</a> |	
				<a id="delete"	href='<c:url value="#"/>'>삭제</a>		
				</c:if>		
			</form>	
					
			</div>

			<div class="card col-lg-6 text-center"
				style="float: none; margin: 0 auto;">
				
				<c:if test="${sessionScope.email != null }">
				<div class="card-header bg-light">
					<i class="fa fa-comment fa"></i> 댓글
				</div>
				<form name="commentForm" method="post">
				<div class="card-body">
					<ul class="list-group list-group-flush">
						<input type="hidden" id="boardNo" name="boardNo" value="${vo.boardNo}" />
  						<input type="hidden" id="name" name="name" value="${sessionScope.email}" />
					
									
						<li class="list-group-item">
							<div class="form-inline mb-1"></div> <textarea
								class="form-control" id="content" name="content" rows="3"></textarea>
							<button type="button" class="btn btn-dark mt-3" id="commentSubmit"
								>댓글 등록</button>
						</li>
					</ul>
				</div>
				</form>
				</c:if>
			</div>
			
			<script type="text/javascript">
			$("#commentSubmit").on("click", function(){
				if($('#name').val().length<1){ 
					alert("로그인 후에 입력 가능합니다."); 
					$('#name').focus(); 
					event.preventDefault(); 
				}else if($('#content').val().length<1){ 
					alert("내용을 입력하세요"); 
					$('#content').focus(); 
					event.preventDefault(); 	
				}else{
				var formObj = $("form[name='commentForm']");
				  formObj.attr("action", "/comcome/comment/write");
				  alert("댓글 등록 완료");
				  formObj.submit();
				}
				});
			
			
				$(function(){
					$('#delete').click(function(){
						if(confirm("정말 삭제하시겠습니까?")){
							var formObj = $("form[name='delform2']");
							  formObj.attr("action", "/comcome/usedBoard/delete");						
							  formObj.submit();
							}
						
					});
				});
			</script>
		
			


			<div id="reply">
				<ol class="replyList col-lg-6 text-left"
					style="float: none; margin: 0 auto;">
					<c:forEach items="${list2}" var="replyList">
						<p id='asdf' style='display:none'>
							asdfasdf
						</p>
						<p>
							<a href='' id="profile"> ${replyList.name} </a> 작성 날짜:
							<fmt:formatDate value="${replyList.regdate}" pattern="yyyy-MM-dd HH:mm" />
						</p>
						<div data-replyNo='${replyList.no}' class='replyLi' display="none">
							
						<p class="replyText">${replyList.content}</p>					
						
						<c:if test = "${sessionScope.email == replyList.name}">	
						<a href="#" class=" btn-box-tool replyModBtn" data-toggle="modal" data-target="#modModal">
                 	   <i class="fa fa-edit"> 수정 </i>  
              			  </a>		 
						<a href="#" class=" btn-box-tool replyDelBtn" data-toggle="modal" data-target="#delModal">
                 	   <i class="fa fa-times"> 삭제</i>
          		 	     </a>
						</c:if>
						</div>		
						<hr>
					</c:forEach>
				</ol>
			</div>
			<script type="text/javascript">
			$(".replyModBtn").click(function () {
				var reply = $(this).parent();
				var replyNo = reply.attr("data-replyNo");
			    var replyText = reply.children(".replyText").text();
			    

			    $("#replyNo").val(replyNo);
			    $("#replyText").val(replyText);
			    

			});
			
				$(".replyDelBtn").click(function () {
			
				
				var reply = $(this).parent();
				
				var replyNo = reply.attr("data-replyNo");
			    var replyText = reply.children(".replyText").text();
			    

			    $("#replyNo2").val(replyNo);
			    $("#replyText").val(replyText);
			    

			});
			
			
			</script>
			
							<!-- Script -->
				<script src="https://cdn.jsdelivr.net/npm/jquery@3.3.1/dist/jquery.min.js"></script>
				<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
				<script src="https://cdn.bootcss.com/bootstrap-treeview/1.2.0/bootstrap-treeview.min.js"></script>
				<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.7.1/jquery.contextMenu.min.js"></script>
				<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.7.1/jquery.ui.position.js"></script>
				
				<script>
				$(function(){
				
				    $.contextMenu({
				        selector: '#profile',
				        trigger: 'left',
				        callback: function(key, options) {
				            var m = "clicked: " + key;
				            //window.console && console.log(m) || alert(m);
				            
				            if( key == "massage" ){

				            	var reply = $(this);
								var p_account_id = ''
								if(reply.length > 0){
									p_account_id = reply[0].innerText.trim();
								}
								else{
									alert('버그 발생');
									return;
								}
								

								var url = "message?account_id="+p_account_id;
								var popup = window.open(url, '쪽지보내기', 'width=660px,height=565px,scrollbars=no');

							}else if( key == "profile" ){

								var reply = $(this);
								var p_account_id = ''
								if(reply.length > 0){
									p_account_id = reply[0].innerText.trim();
								}
								else{
									alert('버그 발생');
									return;
								}
								

								var url = "profile?account_id="+p_account_id;
								var popup = window.open(url, '회원정보 보기', 'width=500px,height=500px,scrollbars=no');

							}

				        },
				        items: {
				           "massage": {name: "쪽지보내기", icon: "edit"},
				            "profile": {name: "프로필보기", icon: "paste"},
				            "quit": {name: "Quit", icon: function($element, key, item){ return 'context-menu-icon context-menu-icon-quit'; }}
				        }
				    });
				 
				
				});
				 
			</script>
		</div>
	</div>

	<%--댓글 수정 modal 영역--%>
	<div class="modal fade" id="modModal">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                
	                <h4 class="modal-title">댓글수정</h4>
	            </div>
	            <form name="modifyForm" method="post" action="/comcome/comment/edit">
	            <div class="modal-body" data-rno>
	                <input type="hidden" class="replyNo" id="replyNo" name="no"/>
	                <input type="hidden" id="boardNo" name="boardNo" value="${vo.boardNo}" />
	                <%--<input type="text" id="replytext" class="form-control"/>--%>
	                <textarea class="form-control" id="replyText" name="content" rows="3" style="resize: none"></textarea>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">닫기</button>
	                <button type="button" class="btn btn-primary modalModBtn" id=modifySubmit>수정</button>
	            </div>
	            </form>
	        </div>
	    </div>
	</div>
	
	<script type="text/javascript">
				$("#modifySubmit").on("click", function(){
					if($('#replyText').val().length<1){ 
						alert("내용을 입력하세요"); 
						$('#replyText').focus(); 
						event.preventDefault(); 	
					}else{
					var formObj = $("form[name='modifyForm']");
					  formObj.attr("action", "/comcome/comment/edit");
					  alert("댓글 수정 완료");
					  formObj.submit();
					}
					});
				</script>
	
	
	
	<%--댓글 삭제 modal 영역--%>
	<div class="modal fade" id="delModal">
	    <div class="modal-dialog">
	        <div class="modal-content">
	        	 <form name="delForm" method="post" action="/comcome/comment/delete">
	            <div class="modal-header">
	                <h4 class="modal-title">댓글 삭제</h4>
	                <input type="hidden" class="rno"/>
	                <input type="hidden" class="replyNo" id="replyNo2" name="no"/>
	                <input type="hidden" id="boardNo" name="boardNo" value="${vo.boardNo}" />
	            </div>
	            <div class="modal-body" data-rno>
	                <p>댓글을 삭제하시겠습니까?</p>
	                <input type="hidden" class="rno"/>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">아니요.</button>
	                <button type="submit" class="btn btn-primary modalDelBtn" id="modalDelBtn">네. 삭제합니다.</button>
	            </div>
	            </form>
	        </div>
	    </div>
	</div>
	
	
	
	
	
	

			

</body>
</html>
<%@ include file="../include/footer.jsp"%>