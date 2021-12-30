<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="pos" value="0" />
<%-- <c:forEach var="vo" items="${brandList}"> --%>
<c:forEach var="vo" items="${brandList}">
	<li class="brand_list">
		<%-- <a href="#">${vo.brand }</a> --%>
		<a href="<c:url value="/searchpd/list?brandNo=${vo.brandNo}" />">${vo.brand }</a>
		<div id="size_cat_container">
			<ul id="size_${i}" class="size_cat" style="top:${pos}px; left:260px">
				<c:forEach var="vo2" items="${screenSizeList}">
					<li class="size_list">
						<a href="<c:url value="/searchpd/list?brandNo=${vo.brandNo}&screenSizeNo=${vo2.screenSizeNo}" />">${vo2.screenSize}</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</li> 
	<c:set var="pos" value="${pos+40}" />
</c:forEach>