<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<!-- title -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<%
String email=(String)session.getAttribute("email");


%>
<div class="container">
	<form name="f1" method="post"  action='<c:url value="/admin/popup-regi"/>'  >
   		 <div class="form-group">
   		    <h2>공지 등록</h2>
      		<label for="usr">제목:</label>
     		 <input type="text" class="form-control" id="title" name="title" >
     		 <input type="hidden" id="email" name="email" value="<%=email %>"/>
    	</div>

   		<div class="form-group">
     		 <label for="comment">내용:</label>
      		<textarea class="form-control" rows="5" id="content" name="content" ></textarea>
    	</div>
    	<button type="submit" class="btn btn-primary" onclick="loginChk()" >등록</button>
 	 </form>
 
</div>
</body>
</html>