<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">

<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mypage/styles.css'/>" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/mypage/scripts.js"></script>
 <link rel="shortcut icon" type="image/x-icon" href="<c:url value='/resources/img/com_logo.png'/>">
</head>
<body>
<%
String email=(String)session.getAttribute("email");
String name=(String)session.getAttribute("name");
%>
         <div class="d-flex" id="wrapper">
            <!-- Sidebar -->
            <div class="border-end bg-white" id="sidebar-wrapper">
                <div class="sidebar-heading border-bottom bg-light" >ComCome</div>
                <div class="list-group list-group-flush">
              <%--       <a class="list-group-item list-group-item-action list-group-item-light p-3" href="<c:url value='/mypage/profile/profileEdit'/>">기본정보수정</a> --%>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">구매목록</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">장바구니</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">관심상품</a>
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
<div class="container">
  <h2>프로필 수정</h2>
  <!--  폼에서 여러가지를 불러올때 한테이블에 있는 정보면 -->
  <form  method="post" action="<c:url value='/mypage/profile/profileEdit'/>">
  	<div class="form-group">
  	
  	<p>수정할 닉네임을 입력해주세요</p>
 			<label for="name">닉네임 수정</label>
            <input type="text" id="name" name="name" placeholder="<%=name%>" />
    </div>
    <br>
    <br>
    <!-- <p>비밀번호를 입력해주세요</p> -->
    <div class="form-group">
     <input type ="hidden" id ="email" name ="email" value="<%=email%>">
     <!--  <label for="pwd">비밀번호:</label> -->
<!--       <input type="password" class="form-control" id="password" name="password"> -->
    </div>
    <!-- <div class="form-group">
      <label for="pwd">비밀번호 확인:</label>
      <input type="password" class="form-control" id="password1" name="password1">
    </div> -->
 
    <button type="submit" class="btn btn-primary">수정</button>
  </form>
</div>
                
                
</body>
</html>