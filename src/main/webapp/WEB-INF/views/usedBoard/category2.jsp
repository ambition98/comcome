<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="pos" value="40" />
<%-- <c:forEach var="vo" items="${brandList}"> --%>
<c:forEach var="vo" items="${category2}">
	<li class="category2">
		<%-- <a href="#">${vo.brand }</a> --%>
		<a href="<c:url value="/usedBoard/list?categoryNo=${vo.groupNo}" />">${vo.categoryName}</a>
	</li> 
</c:forEach>