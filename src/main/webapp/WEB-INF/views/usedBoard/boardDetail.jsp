<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<c:url value='/resources/css/base/bootstrap.min.css' />"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/resources/css/base/font-awesome.min.css"' />"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/resources/css/base/nice-select.css' />"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/resources/css/base/jquery-ui.min.css' />"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/resources/css/base/owl.carousel.min.css' />"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/resources/css/base/slicknav.min.css' />"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/resources/css/base/style.css' />" type="text/css">

<script src="<c:url value='/resources/js/base/jquery-3.3.1.min.js' />"></script>
<script src="<c:url value='/resources/js/base/bootstrap.min.js' />"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.9.2/jquery.contextMenu.css" integrity="sha512-EF5k2tHv4ShZB7zESroCVlbLaZq2n8t1i8mr32tgX0cyoHc3GfxuP7IoT8w/pD+vyoq7ye//qkFEqQao7Ofrag==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script
	src="<c:url value='/resources/js/base/jquery.nice-select.min.js' />"></script>
<script src="<c:url value='/resources/js/base/query-ui.min.js' />"></script>
<script src="<c:url value='/resources/js/base/jquery.slicknav.js' />"></script>
<script src="<c:url value='/resources/js/base/mixitup.min.js' />"></script>
<script src="<c:url value='/resources/js/base/owl.carousel.min.js' />"></script>
<script src="<c:url value='/resources/js/base/main.js' />"></script>

<script src="/resouces/js/contextMenu/context-menu.js"></script>
<link href="/resouces/css/contextMenu/context-menu.css" rel="stylesheet">


<script>
$(function(){
    <!-- hover -->
    $.contextMenu({
        selector: '.hover',
        trigger: 'hover',
        callback: function(key, options) {
            var m = "clicked: " + key;
            window.console && console.log(m) || alert(m);
        },
        items: {
            "edit": {name: "Edit", icon: "edit"},
            "cut": {name: "Cut", icon: "cut"},
            "copy": {name: "Copy", icon: "copy"},
            "paste": {name: "Paste", icon: "paste"},
            "delete": {name: "Delete", icon: "delete"},
            "sep1": "---------",
            "quit": {name: "Quit", icon: function($element, key, item){ return 'context-menu-icon context-menu-icon-quit'; }}
        }
    });
 
    <!-- 왼쪽 클릭 -->
    $.contextMenu({
        selector: '.left',
        trigger: 'left',
        callback: function(key, options) {
            var m = "clicked: " + key;
            window.console && console.log(m) || alert(m);
        },
        items: {
            "edit": {name: "Edit", icon: "edit"},
            "cut": {name: "Cut", icon: "cut"},
            "copy": {name: "Copy", icon: "copy"},
            "paste": {name: "Paste", icon: "paste"},
            "delete": {name: "Delete", icon: "delete"},
            "sep1": "---------",
            "quit": {name: "Quit", icon: function($element, key, item){ return 'context-menu-icon context-menu-icon-quit'; }}
        }
    });
    
    $(document).ready(function(){
    	  $('#example').contextmenu({
    	    target:"#context-menu"
    	  });
    	});
    
    
    function listReply(){
        $.ajax({
            type: "get",
            url: "comcome/comment/list?boardNo=${vo.boardNo}",
            success: function(result){
            // responseText가 result에 저장됨.
                $("#listReply").html(result);
            },
            error:function(request, status, error) {
                alert("status : " + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
              }
        });
    }

    
    </script>



</head>
<body>
	<section class="breadcrumb-section set-bg"
		data-setbg="../resources/img/breadcrumb.jpg">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<div class="breadcrumb__text">

						<h2 style="color: black;">중고 장터</h2>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Breadcrumb Section End -->


	<div class="col-lg-12  text-center">
		<h2>글 상세보기</h2>
		<div class="divForm">
			<div class="firstDiv">
				<span class="sp1">제목 : </span> <span>${vo.title}</span>
			</div>
			<div>
				<span class="sp1" >작성자 : </span> <span>${vo.email}</span>
			</div>
			<div>
				<span class="sp1">등록일 : </span> <span><fmt:formatDate
						value="${vo.regdate}" pattern="yyyy-MM-dd HH:mm" /></span>
			</div>

			<div>
				<span class="price">가격 : <fmt:formatNumber
						value="${vo.price }" pattern="#,###" /></span>
			</div>
			<div>
				<span class="sp1">조회수</span> <span>${vo.readcount}</span>
			</div>
			
			<div id="context-menu" style="display:none">
			  <ul class="dropdown-menu">
			    <li><a href="#">Action 1</a></li>
			    <li><a href="#">Action 2</a></li>
			    <li><a href="#">Action 3</a></li>
			    <li class="divider"></li>
			    <li><a href="#">Action 4</a></li>
			    <li><a href="#">Action 5</a></li>
			    <li><a href="#">Action 6</a></li>
			  </ul>
			</div>
			
			

			<%
			pageContext.setAttribute("newLine", "\r\n");
			%>

			<div class="lastDiv">
				<img src="../resources/img/${vo.fileName}" alt=""
					style="width: auto; height: auto;">
				<p class="content">${fn:replace(vo.content, newLine, "<br>")}</p>
			</div>


			<div class="">

				<a href='<c:url value="/board/edit.do?no=${param.no }"/>'>수정</a> | <a
					href='<c:url value="/board/delete.do?no=${param.no }"/>'>삭제</a> | <a
					href='<c:url value="/usedBoard/list"/>'>목록</a>
			</div>

			<div class="card col-lg-6 text-center"
				style="float: none; margin: 0 auto;">
				<div class="card-header bg-light">
					<i class="fa fa-comment fa"></i> REPLY
				</div>
				<div class="card-body">
					<ul class="list-group list-group-flush">
						<li class="list-group-item">
							<div class="form-inline mb-2"></div> <textarea
								class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
							<button type="button" class="btn btn-dark mt-3"
								onClick="javascript:addReply();">post reply</button>
						</li>
					</ul>
				</div>
			</div>

			<div id="listReply"></div>
			

		</div>
	</div>

</body>
</html>
<%@ include file="../include/footer.jsp"%>