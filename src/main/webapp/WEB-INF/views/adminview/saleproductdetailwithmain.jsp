<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../adminlayout/adminheader.jsp" %>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/admin/saleproductdetail.css'/>" />
<script type="text/javascript">

function del(saleProductNo) {
	var chk = confirm("정말 삭제하시겠습니까?");
	if (chk) {
		location.href='/comcome/admin/delete-sale-product?saleProductNo='+saleProductNo;
	}
}	
</script>




	 <div class="main">
			<div class="main-content">
				<div class="container-fluid">
					
					<div class="panel panel-headline">
						<div class="panel-heading">  
							<%@include file="../admin/saleproductdetail.jsp" %>
	
						</div>
					</div>	
				</div>	
			</div>
 </div>  
<%@include file="../adminlayout/adminfooter.jsp" %>