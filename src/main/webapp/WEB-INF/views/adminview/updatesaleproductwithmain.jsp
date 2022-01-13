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
        }else{
        var formObj = $("form[name='commentSubmit']");
          formObj.attr("action", "/comcome/admin/post-sale-product");
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
						
						<%@include file="../admin/updatesaleproduct.jsp" %>
						
						</div>
					</div>	
				</div>	
			</div>
 </div>  
<%@include file="../adminlayout/adminfooter.jsp" %>