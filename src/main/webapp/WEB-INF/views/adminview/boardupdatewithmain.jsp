<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../adminlayout/adminheader.jsp" %>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/admin/boardupdate.css'/>" />
<script type="text/javascript">
$(function(){
	$("#deletevo").on("click", function(){
        if($('#title').val().length<1){ 
            alert("제목을 입력 해주세요."); 
            $('#title').focus(); 
            event.preventDefault(); 
        }else if($('#content').val().length<1){ 
            alert("내용을 입력하세요"); 
            $('#content').focus(); 
            event.preventDefault();
        }else{
        var formObj = $("form[name='frmWrite']");
          formObj.attr("action", "/comcome/admin/updateboard");
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
						
						<%@include file="../admin/boardupdate.jsp" %>
						</div>
					</div>	
				</div>	
			</div>
 </div>  
<%@include file="../adminlayout/adminfooter.jsp" %>