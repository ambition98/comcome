<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="vo" items="pdList">
	<div class="pd_thumbnail">
		<a href="#">
			<img src="<c:url value="/resources/img/search_pd/thumbnail" />/${vo.thumbnail}" />
		</a>
	</div>
	<div class="pd_name">
		<a href="#">${vo.name}</a>
	</div>
	<div class="pd_price">
		0
	</div>
</c:forEach>