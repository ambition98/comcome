<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../mypageinc/mypageMain.jsp" %>
<style type="text/css">

.containser{
	width:100%;
	margin-right:auto;
	margin-left:auto;
}

</style>

<script type="text/javascript">	
	$(function(){
		$('#btMultiDel').click(function(){
			$('form[name=frmList]')
				.prop('action','<c:url value='/admin/product/deleteMulti'/>')
			$('form[name=frmList]').submit();	
		});
		
	});
</script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="containser">
		<div>
			<table>
				<thead>
					<tr>
						<th>#</th>
						<th>이름</th>
					</tr>
				</thead>
				
				<tbody>
					<tr th:each="member : ${ACCOUNT}">
						<td th:text="${ACCOUNT_NO }"></td>
						<td th:text="${name } "></td>					
					</tr>
				</tbody>
			</table>
		</div>
	
	</div> <!-- /container -->
</body>
</html>