<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mypage/styles.css'/>" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/mypage/scripts.js"></script>
</head>
<body>
         <div class="d-flex" id="wrapper">
            <!-- Sidebar -->
            <div class="border-end bg-white" id="sidebar-wrapper">
                <div class="sidebar-heading border-bottom bg-light">comcome</div>
                <div class="list-group list-group-flush">
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">기본정보수정</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">구매목록</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">장바구니</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">관심상품</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">쪽지함</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">회원탈퇴</a>
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
                                <li class="nav-item active"><a class="nav-link" href="#!">comcome</a></li>
                                <li class="nav-item"><a class="nav-link" href="#!">마이페이지</a></li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" 
                                    role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">로그인</a>
                                    <div class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                        <a class="dropdown-item" href="#!">로그인</a>
                                        <a class="dropdown-item" href="#!">비밀번호찾기</a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="#!">회원가입</a>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
               <!--  Page content -->
                <div class="container-fluid">
                    <h1 class="mt-4">나의 정보</h1>
                    <table>
                    <td>
                    <p>프로필 </p>
                    <p>
                        프로필
                        <code>프로필</code>
                       프로필
                        <code>프로필</code>
                        프로필
                    </p>
                    </td>
                    </table>
                </div>
            </div>
        </div>

</body>
</html>