<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">

<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mypage/styles.css'/>" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/mypage/scripts.js"></script>
<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/resources/img/com_logo.png'/>">
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String email=(String)session.getAttribute("email");
%>
         <div class="d-flex" id="wrapper">
            <!-- Sidebar -->
            <div class="border-end bg-white" id="sidebar-wrapper">
                <div class="sidebar-heading border-bottom bg-light" >ComCome</div>
                <div class="list-group list-group-flush">
              <a class="list-group-item list-group-item-action list-group-item-light p-3" href="<c:url value='/mypage/pwd/pwd'/>">비밀번호 재설정</a> 
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">구매목록</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="<c:url value='/mypage/cart'/>">장바구니</a>
        <!--             <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">관심상품</a> -->
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="<c:url value='/mypage/messageboxrec'/>">쪽지함</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="<c:url value='/mypage/member/memberDelete'/>">회원탈퇴</a>
                </div>
            </div>
           <!--  Page content wrapper -->
            <div id="page-content-wrapper">
             <!--    Top navigation -->
                <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
                    <div class="container-fluid">
                       <!--  <button class="btn btn-primary" id="sidebarToggle">일단 냅둠</button> -->
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav ms-auto mt-2 mt-lg-0">
                                <li class="nav-item active"><a class="nav-link" href="<c:url value='/'/>">comcome</a></li>
                                <li class="nav-item"><a class="nav-link" href="<c:url value='/mypage/index'/>">마이페이지</a></li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" 
                                    role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><%=email%></a>
                                    <div class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">

                                        <a class="dropdown-item" href="#!">프로필</a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="<c:url value='/login/logout'/>">로그아웃</a>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
                
                <form name="frm1" method="post" action="<c:url value='/mypage/pwd/pwd'/>">
					<div class="input-group form-group">
						<input type ="hidden" id ="email" name ="email" value="<%=email%>">
						<input type="password" class="form-control" id="password" name="password" 
						>
						
					</div>
					<b id ="yourhp">비밀번호 확인</b>
					<div class="input-group form-group">
						
						
						<input type="password" class="form-control"  id="passwordCk" name="passwordCk">
					</div>
					<div class="form-group">
						
						<input type="submit" value="Find" class="btn float-right login_btn">
					</div>
				</form>
<!-- <div class="container">
  <h2>프로필 수정</h2> -->
</body>
</html>