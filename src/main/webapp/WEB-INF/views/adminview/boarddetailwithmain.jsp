<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../adminlayout/adminheader.jsp" %>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/admin/boarddetail.css'/>" />
<script type="text/javascript">

function del(boardNo) {
	var chk = confirm("정말 삭제하시겠습니까?");
	if (chk) {
		location.href='/comcome/admin/delete?boardNo='+boardNo;
	}
}	
</script>	
	
	 <div class="main">
			<div class="main-content">
				<div class="container-fluid">
					
					<div class="panel panel-headline">
						<div class="panel-heading">  
							<%@include file="../admin/boarddetail.jsp" %>
	
						</div>
					</div>	
				</div>	
			</div>
 </div>  
<%@include file="../adminlayout/adminfooter.jsp" %>