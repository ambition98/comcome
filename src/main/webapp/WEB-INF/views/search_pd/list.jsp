<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>comcome</title>
<style type="text/css">
.search_pd_container {
	background: skyblue;
}

.pd_thumbnail div{
	display: inline-block;
}
</style>
</head>
<body>

<div class="search_pd_container">
	<div><h3>상품목록</h3></div>
	<div class="search_pd">
		<c:import url="/searchpd/pd" />
	</div>
</div>
</body>
</html>
<%@ include file="../include/footer.jsp"%>