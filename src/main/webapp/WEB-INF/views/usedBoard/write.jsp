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
<title>comcome 중고게시판 글쓰기</title>
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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

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
<script src="https://cdn.ckeditor.com/ckeditor5/29.1.0/classic/ckeditor.js"></script>
<style>
	.ck.ck-editor {
    	max-width: 800px;
    	color:black;
	}
	.ck-editor__editable {
	    min-height: 300px;
	}
	</style>

</head>
<body>
<script type="text/javascript">
	$(function(){
		$('form[name=frmWrite]').submit(function(){
			$('.infobox').each(function(idx, item){
				if($(this).val().length<1){
					alert($(this).prev().html() + "을(를) 입력하세요");
					$(this).focus();
					event.preventDefault();
					return false;  //each 탈출
				}else if(${sessionScope.email}==null){
					alert("로그인 후에 가능합니다");
				}
			});
		});
		
		$('#btList').click(function(){
			location.href="<c:url value='/reBoard/list.do'/>";	
		});
		
	});
</script>

<section class="1">
        <div class="container">
            
            <div class="checkout__form w-75  align-items-center ">
                <h4>중고게시판 글쓰기</h4>
                <form action='<c:url value="/usedBoard/write"/>' name="frmWrite" method="post" enctype="multipart/form-data" >
                    <div class="col-lg-10 ">
                        <div class="col-lg-12 col-md-8 ">                   
                            <div class="checkout__input">
                                <p>제목</p>
                                <input type="text" style="color:black;" id="title" name="title" class="infobox">
                                <hr>
                            </div>
                            <div class="checkout__input">
                                <p>분류</p>
                                  <select class="form-select" name="groupNo" aria-label="Default select example">
                                  
								    <option>노트북</option>
								    <option>노트북 주변기기</option>
								    <option>기타 pc부품</option>
								  </select>	
								  <hr>							                                
                            </div>
                            <div class="checkout__input" >
                            	<p>가격</p>
                            	<input type="text" style="color:black;" name="price">
                            	<hr>
                            </div>      
					         <div>
					            <label for="upfile">썸네일 이미지</label>            
						        <input type="file" name="upfile" id="upfile">(최대 5M)
						        <br>
						        <hr>
					        </div>	     
                            <div >
						        <textarea id="content" name="content" rows="" cols="" style="text-color:black;" ></textarea>
						    </div>
						    <script>
						        ClassicEditor
						            .create( document.querySelector( '#content' ),{height:100})				            
						            .catch( error => {
						                console.error( error );
						            } );
						    </script>
						    <hr>						  
						    <div>
						    	<button type="submit" class="btn btn-primary">제출</button>
						    	
						    </div>
						    <input type="hidden" name="accountNo" value="${sessionScope.accountNo}">
						    					   
						    <br><br><br><br><br>
						    
						    <input type="hidden" name="email" id="email" value="${sessionScope.email}">	
                        </div>
                        
                      
                    </div>
                </form>
            </div>
        </div>
    </section>
</body>
</html>
<%@ include file="../include/footer.jsp"%>