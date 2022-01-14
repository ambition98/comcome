<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ include file="include/header.jsp"%> 
<div id="banner3">
 <img alt="사진" src="<c:url value='/resources/img/banner4.png'/>" id="banner3">
</div>
<div id="banner2">
 <img alt="사진" src="<c:url value='/resources/img/banner2.png'/>">
</div>



    
   

<%-- <%@ include file="include/category.jsp"%> --%>
<%-- <%@ include file="inc/body.jsp"%> --%>

 <%@include file="indexcontent/indexcontent.jsp" %> 

<jsp:include page="admin/popup3.jsp"/>

<%@ include file="include/footer.jsp"%>