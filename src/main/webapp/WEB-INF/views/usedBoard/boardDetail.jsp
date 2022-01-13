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
 <link rel="shortcut icon" type="image/x-icon" href="<c:url value='/resources/img/com_logo.png'/>">
<title>comcome 중고게시판 상세보기</title>
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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.9.2/jquery.contextMenu.css"
	integrity="sha512-EF5k2tHv4ShZB7zESroCVlbLaZq2n8t1i8mr32tgX0cyoHc3GfxuP7IoT8w/pD+vyoq7ye//qkFEqQao7Ofrag=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script
	src="<c:url value='/resources/js/base/jquery.nice-select.min.js' />"></script>
<script src="<c:url value='/resources/js/base/query-ui.min.js' />"></script>
<script src="<c:url value='/resources/js/base/jquery.slicknav.js' />"></script>
<script src="<c:url value='/resources/js/base/mixitup.min.js' />"></script>
<script src="<c:url value='/resources/js/base/owl.carousel.min.js' />"></script>
<script src="<c:url value='/resources/js/base/main.js' />"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.min.js"></script>
<script src="/resouces/js/contextMenu/context-menu.js"></script>
<link href="/resouces/css/contextMenu/context-menu.css" rel="stylesheet">
<style type="text/css">
a{ color: black; text-decoration: none;}
a:hover { color: blue; text-decoration: none;}
</style>
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


	<div class="col-lg-10  text-center" style="float: none; margin: 0 auto;">
		<h2>글 상세보기</h2>
		<div class="divForm">
			<br>
			<br>
			<div class="col-lg-3" style="float: none; margin: 0 auto;">
				<div class="firstDiv">
					<span class="sp1">제목 : </span> <span>${vo.title}</span>
				</div>
				<hr>
				<div>
					<span class="sp1">작성자 : </span> <span>${vo.email}</span>
				</div>
				<hr>
				<div>
					<span class="sp1">등록일 : </span> <span><fmt:formatDate
							value="${vo.regdate}" pattern="yyyy-MM-dd HH:mm" /></span>
				</div>
				<hr>

				<div>
					<span class="price">가격 : <fmt:formatNumber
							value="${vo.price }" pattern="#,###" /></span>
				</div>
				<hr>
				<div>
					<span class="sp1">조회수</span> <span>${vo.readcount}</span>
				</div>
				<hr>
			</div>
			<div id="context-menu" style="display: none">
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
				<p class="content">${fn:replace(vo.content, newLine, "<br>")} </p>
			</div>


			<div class="col-lg-7">
				<a href='<c:url value="/usedBoard/list"/>'>목록</a>
				<c:if test = "${sessionScope.email == vo.email}">|					
				<a href='<c:url value="/board/edit.do?no=${param.no }"/>'> 수정</a> | <a
					href='<c:url value="/board/delete.do?no=${param.no }"/>'>삭제</a>
				</c:if>		
					
					
			</div>

			<div class="card col-lg-6 text-center"
				style="float: none; margin: 0 auto;">
				
				<c:if test="${sessionScope.email != null }">
				<div class="card-header bg-light">
					<i class="fa fa-comment fa"></i> 댓글
				</div>
				<form name="commentForm" method="post">
				<div class="card-body">
					<ul class="list-group list-group-flush">
						<input type="hidden" id="boardNo" name="boardNo" value="${vo.boardNo}" />
  						<input type="hidden" id="name" name="name" value="${sessionScope.email}" />
					
									
						<li class="list-group-item">
							<div class="form-inline mb-1"></div> <textarea
								class="form-control" id="content" name="content" rows="3"></textarea>
							<button type="button" class="btn btn-dark mt-3" id="commentSubmit"
								>댓글 등록</button>
						</li>
					</ul>
				</div>
				</form>
				</c:if>
			</div>
			
			<script type="text/javascript">
			$("#commentSubmit").on("click", function(){
				if($('#name').val().length<1){ 
					alert("로그인 후에 입력 가능합니다."); 
					$('#name').focus(); 
					event.preventDefault(); 
				}else if($('#content').val().length<1){ 
					alert("내용을 입력하세요"); 
					$('#content').focus(); 
					event.preventDefault(); 	
				}else{
				var formObj = $("form[name='commentForm']");
				  formObj.attr("action", "/comcome/comment/write");
				  formObj.submit();
				}
				});
			</script>
			


			<div id="reply">
				<ol class="replyList col-lg-6 text-left"
					style="float: none; margin: 0 auto;">
					<c:forEach items="${list2}" var="replyList">
						
						<p>
							<a href=""> ${replyList.name} </a> 작성 날짜:
							<fmt:formatDate value="${replyList.regdate}" pattern="yyyy-MM-dd HH:mm" />
						</p>
						
							
						<p class="replyText">${replyList.content}</p>					
						
						<c:if test = "${sessionScope.email == replyList.name}">	
						<a href="#" class=" btn-box-tool replyModBtn" data-toggle="modal" data-target="#modModal">
                 	   <i class="fa fa-edit"> 수정 </i>  
              			  </a>		 
						<a href="#" class=" btn-box-tool replyDelBtn" data-toggle="modal" data-target="#delModal">
                 	   <i class="fa fa-times"> 삭제</i>
          		 	     </a>
				    	 <div data-replyNo='${replyList.no}' class='replyLi' display="none">
				    	 	
						</c:if>		
						<hr>
					</c:forEach>
				</ol>
			</div>
			
			<script type="text/javascript">
			
			
			
			$(".replyModBtn").click(function () {
			
				
				var reply = $(this).parent();
				
				var replyNo = reply.attr("data-replyNo");
			    var replyText = reply.children(".replyText").text();
			    

			    $("#replyNo").val(replyNo);
			    $("#replyText").val(replyText);
			    

			});
			
			$(".modalModBtn").click( function () {
				console.log("수정ajax");
			    // 댓글 선택자
			    var reply = $(this).parent().parent();
			    // 댓글번호
			    var replyNo = reply.find("#replyNo").val();
			    // 수정한 댓글내용
			    var replyText = reply.find("#replyText").val();

			    // AJAX통신 : PUT
			    $.ajax({
			        type : "put",
			        url : "/comment/" + replyNo,
			        headers : {
			            "Content-type" : "application/json",
			            "X-HTTP-Method-Override" : "PUT"
			        },
			        data : JSON.stringify(
			            {replyText : replyText}
			        ),
			        dataType : "text",
			        success : function (result) {
			            console.log("result : " + result);
			            if (result == "modSuccess") {
			                alert("댓글 수정 완료!");
			                $("#modifyModal").modal("hide"); // Modal 닫기
			                
			            }
			        }
			    });

			});

			</script>
		</div>
	</div>

<%--댓글 수정 modal 영역--%>
<div class="modal fade" id="modModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                
                <h4 class="modal-title">댓글수정</h4>
            </div>
            <div class="modal-body" data-rno>
                <input type="hidden" class="replyNo" id="replyNo"/>
                <%--<input type="text" id="replytext" class="form-control"/>--%>
                <textarea class="form-control" id="replyText" rows="3" style="resize: none"></textarea>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">닫기</button>
                <button type="button" class="btn btn-primary modalModBtn">수정</button>
            </div>
        </div>
    </div>
</div>

<%--댓글 삭제 modal 영역--%>
<div class="modal fade" id="delModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                
                <h4 class="modal-title">댓글 삭제</h4>
                <input type="hidden" class="rno"/>
            </div>
            <div class="modal-body" data-rno>
                <p>댓글을 삭제하시겠습니까?</p>
                <input type="hidden" class="rno"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">아니요.</button>
                <button type="button" class="btn btn-primary modalDelBtn">네. 삭제합니다.</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<%@ include file="../include/footer.jsp"%>