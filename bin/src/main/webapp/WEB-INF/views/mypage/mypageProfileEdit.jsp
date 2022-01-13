<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 수정 -comcome</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mypage/styles.css'/>" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/mypage/scripts.js"></script>
</head>
<body>
<div class="divForm">
<form name="frmEdit" method="post" 
	action="<c:url value='/mypage/mypageProfileEdit.do'/>"> 
	<!-- 수정시 no가 필요하므로 히든 필드에 담아서 넘겨준다 -->
	<input type="hidden" name="accountNo" value="${param.accountNo}">
    <fieldset>
	<legend>프로필 수정</legend>
        <div>
            <label for="name">작성자</label>
            <input type="text" id="name" name="name" value="${vo.name}" />
        </div>
        <div class="center">
            <input type = "submit" value="수정"/>
            <input type = "Button" value="글목록" 
            	onclick="location.href	='<c:url value="/mypage/mypageList.do"/>'" />         
        </div>
	</fieldset>
</form>    
</div>
</body>
</html>