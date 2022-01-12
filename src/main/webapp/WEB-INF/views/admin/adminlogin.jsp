<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  


 <!--  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"> -->
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/admin/adminlogin.css'/>" />

<div class="container">
  <h2>관리자 로그인</h2>
  
  <form  method="post" action="<c:url value='/admin/login'/>">
    <div class="form-group">
      <label for="usr">Email:</label>
      <input type="text" class="form-control" id="email" name="email">
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="password" name="password">
    </div>
    <div id="remember">
		<input type="checkbox" name="chkSave" id="chkSave" 
			<c:if test="${!empty cookie.ck_email }"> checked="checked" 
			</c:if>
		>
		<label for="chkSave">Remember Me</label>
	</div>
    <button type="submit" class="btn btn-primary">Submit</button>
  </form>
</div>

</body>
</html>