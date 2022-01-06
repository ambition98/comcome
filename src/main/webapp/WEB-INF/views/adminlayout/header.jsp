<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mypage/styles.css'/>" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/mypage/scripts.js"></script>

  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
	
	<!-- pagination design -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mypage/member.css'/>" />
	     <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/admin/adminheader.css'/>" />
   <!-- adminmain -->
  

</head>

<body>
<%
String email=(String)session.getAttribute("email");
%>

         <div class="d-flex" id="wrapper">
            <!-- Sidebar -->
            <div class="border-end bg-white" id="sidebar-wrapper">
                <div class="sidebar-heading border-bottom bg-light"><img alt="컴컴로고" id="comcomlogo" src="<c:url value='/resources/img/comcome_wlogo.jpg'/>"></div>
                <div class="list-group list-group-flush">
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="<c:url value='/admin/main'/>">회원 조회 및 검색</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">쪽지 보내기</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">게시글 관리</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">특가몰 관리</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">매출 통계</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="<c:url value='/admin/popup-regi-with-main'/>">팝업 공지 등록</a>
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
                                <li class="nav-item active"><a class="nav-link" href="#!">
                                <% if(email==null || email.isEmpty()){ %>
                                comcome
                                <%}else{ %>
                                <%=email %>
                                <%} %>
                                <% if(email==null || email.isEmpty()){ %>
                                
                                <%}else{ %>
                                </a></li>
                                <li class="nav-item"></li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" 
                                    role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></a>
                                    <div class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                        <a class="dropdown-item" href="<c:url value='/admin/logout'/>">로그아웃</a>
                                    </div>
                                </li>
                                 <%} %>
                            </ul>
                        </div>
                    </div>
                </nav>
