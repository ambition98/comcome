<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
   <!--Made with love by Mutiullah Samim -->
   
	<!--Bootsrap 4 CDN-->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    
    <!--Fontawesome CDN-->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

	<!--Custom styles-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/login/loginForm.css'/>" />
</head>
<body>
<%
	String msg=(String)request.getAttribute("msg");
	String veriCode=(String)request.getAttribute("veriCode");
	String email=(String)request.getAttribute("email");
%>
	<script type="text/javascript">
		alert("<%=msg%>");
	</script>
	
<div class="container">
	<div class="d-flex justify-content-center h-100">
		<div class="card">
			<div class="card-header">
				<h3>Find your Email</h3>
				
			</div>
			
			<div class="card-body">
				<form name="frm1" method="post" action="<c:url value='/login/verified'/>">
					<div class="input-group form-group">
					
					
						
					</div>
					<b id ="yourhp">인증번호</b>
					<div class="input-group form-group">
						<input type ="hidden" id ="veriCode" name ="veriCode" value="<%=veriCode%>">
						<input type ="hidden" id ="email" name ="email" value="<%=email%>">
						<input type="text" class="form-control"  id="yourveriCode" name="yourveriCode">
					</div>
					<div class="form-group">
						
						<input type="submit" value="Find" class="btn float-right login_btn">
					</div>
				</form>
			</div>
			<div class="card-footer">
				<div class="d-flex justify-content-center links">
					Don't have an account?<a href="#">Sign Up</a>
				</div>
				<div class="d-flex justify-content-center">
					<a href="<c:url value='/login/find-email'/>">Forgot your Email?</a>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>