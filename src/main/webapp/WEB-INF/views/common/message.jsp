<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>common/message.jsp</title>
</head>
<body>
<%
	/* String msg=(String)request.getAttribute("msg");
	String url=(String)request.getAttribute("url");
	String ctxPath=request.getContextPath();
	url=ctxPath+url; //=> /mymvc + /pd/pdEdit.do => /mymvc/pd/pdEdit.do */ 
%>
	<script type="text/javascript">
		alert("${msg}");
		if("${url}" == "goToBack")
			history.go(-1);
		else
			location.href = "<c:url value="${url}" />";
	</script>
	
</body>
</html>




