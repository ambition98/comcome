<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  

<title>ComCome 보낸쪽지함</title>
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

	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mypage/styles.css'/>" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">

<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mypage/styles.css'/>" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/mypage/scripts.js"></script>
 <link rel="shortcut icon" type="image/x-icon" href="<c:url value='/resources/img/com_logo.png'/>">
<%
String email=(String)session.getAttribute("email");
%>
         <div class="d-flex" id="wrapper">
            <!-- Sidebar -->
            <div class="border-end bg-white" id="sidebar-wrapper">
                <div class="sidebar-heading border-bottom bg-light">쪽지함</div>
                <div class="list-group list-group-flush">
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="<c:url value='/mypage/messageboxrec'/>">받은쪽지함</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="<c:url value='/mypage/messagebox'/>">보낸쪽지함</a>
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
  <h2>보낸쪽지함</h2>
  <c:if test="${!empty param.searchKeyword }">
	<p>검색어 : ${param.searchKeyword },  
		${pagingInfo.totalRecord} 건 검색되었습니다. </p>
</c:if>
  <table class="table table-striped">
    <thead>
      <tr>
        <th>메세지번호</th>
        <th>회원번호</th>
        <th>제목</th>
        <th>발송일</th>
      </tr>
    </thead>
    <tbody>
    	<c:if test="${empty list }">
	  	<tr>
	  		<td colspan="5">데이터가 없습니다.</td>
	  	</tr>
	  </c:if>	
      <c:if test="${!empty list }">	  
		  <!--게시판 내용 반복문 시작  -->		  
		  <c:forEach var="vo" items="${list }">
			<tr>
				<td>${vo.messageno}</td>
				<td>${vo.accountno}</td>
				<td style="text-align:left">
					<a href="#"
	<%-- 	="<c:url value='/admin/detail?account_no=${vo.account_no}'/>" --%>>
						${vo.title}
					</a>
				</td>
				<td><fmt:formatDate value="${vo.senddate}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
		  </c:forEach>
		  <!--반복처리 끝  -->
		</c:if>
    </tbody>
  </table>
</div>
<div class="container">
	<!-- 페이지 번호 추가 -->		
	<!-- 이전 블럭으로 이동 -->
	 <ul class="pagination">
	<c:if test="${pagingInfo.firstPage>1 }">
    <li class="page-item"><a class="page-link" href="<c:url value='/mypage/messagebox?currentPage=${pagingInfo.firstPage-1}&searchCondition=${param.searchCondition}&searchKeyword=${param.searchKeyword}'/>">Previous</a></li>
    </c:if>	
    <!-- [1][2][3][4][5][6][7][8][9][10] -->
    <c:forEach var="i" begin="${pagingInfo.firstPage}" end="${pagingInfo.lastPage }">
    	<c:if test="${i==pagingInfo.currentPage }">
   		<li class="page-item"><a class="page-link" href="javascript:void(0);">${i}</a></li>
   		</c:if>	
		<c:if test="${i!=pagingInfo.currentPage }">	
		<li class="page-item"><a class="page-link" href="<c:url value='/mypage/messagebox?currentPage=${i}&searchCondition=${param.searchCondition}&searchKeyword=${param.searchKeyword}'/>">${i}</a></li>
		</c:if>	
    </c:forEach>
    <!-- 다음 블럭으로 이동 -->
    <c:if test="${pagingInfo.lastPage < pagingInfo.totalPage }">	
    <li class="page-item"><a class="page-link" href="<c:url value='/mypage/messagebox?currentPage=${pagingInfo.lastPage+1}&&searchCondition=${param.searchCondition}&searchKeyword=${param.searchKeyword}'/>">Next</a></li>
  	</ul>
	</c:if>	
	
	
					
	<!--  페이지 번호 끝 -->	
</div>

 <div class="divSearch">
   	<form name="frmSearch" method="post" id ="search"
   		action='<c:url value="/mypage/messagebox"/>'>
        <select name="searchCondition" id ="searchsel">
            <option value="title" 
            	<c:if test="${param.searchCondition=='title' }">            	
            		selected="selected"
            	</c:if>
            >제목</option>
            <option value="account_no" 
            	<c:if test="${param.searchCondition=='accountno' }">            	
            		selected="selected"
            	</c:if>
            >회원번호</option>
        </select>   
        <input type="text" id ="searchinput" name="searchKeyword" title="검색어 입력"
        	value="${param.searchKeyword}">   
		<input type="submit"  id ="searchbutton" value="검색">
    </form>
</div>
