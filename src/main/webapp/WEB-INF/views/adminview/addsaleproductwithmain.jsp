<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../adminlayout/adminheader.jsp" %>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/admin/addsaleproduct.css'/>" />
<script type="text/javascript">

$(function(){
	
	$("input:text[numberOnly]").on("keyup", function() {
	      $(this).val($(this).val().replace(/[^0-9]/g,""));
	   });
	
	$("#deletevo").on("click", function(){
        if($('#name').val().length<1){ 
            alert("이름을 입력 해주세요."); 
            $('#name').focus(); 
            event.preventDefault(); 
        }else if($('#price').val().length<1){ 
            alert("가격을 입력하세요"); 
            $('#price').focus(); 
            event.preventDefault();
        }else if($('#content').val().length<1){ 
            alert("내용을 입력하세요"); 
            $('#content').focus(); 
            event.preventDefault();
        }else{
        var formObj = $("form[name='commentForm']");
          formObj.attr("action", "/comcome/admin/addsaleproduct");
          formObj.submit();
        }
        });
	


	
});




</script>
	 <div class="main">
			<div class="main-content">
				<div class="container-fluid">
					
					<div class="panel panel-headline">
						<div class="panel-heading">  
						
						<%@include file="../admin/addsaleproduct.jsp" %>
						
						</div>
					</div>	
				</div>	
			</div>
 </div>  
<%@include file="../adminlayout/adminfooter.jsp" %>