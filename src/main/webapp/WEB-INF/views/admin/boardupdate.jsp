<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!-- REALTIME CHART -->
<h2 class="panel-title" id ="yourtitle">글 수정</h2>
	<div class="panel" id="innerpanel">
		<div class="panel-heading">
		</div>
			<div class="panel-body">
			<form name="frmWrite" method="post" enctype="multipart/form-data">
				
				<c:if test="${!empty boardNo }">
					<h4><input type="text" id="title" aria-describedby="emailHelp"
												placeholder="새 제목 입력" name="title" ></h4>
					<p id="yourli">글번호: <span id="yourspan">${boardNo}</span>
					</p>
					<input type = "hidden" id="boardNo" name ="boardNo" value="${boardNo}">
					 </c:if>
					
					<ul class="list-unstyled list-justify">
						<li id="yourli3"><textarea placeholder="내용을 입력하세요" id="content" name="content" rows="12" cols="40"></textarea>
						</li>
					</ul>
				
				<p id="yourli2"><input type="file" name="upfile" id="upfile"></p>
				<p id="yourli3">첨부파일은 최대 5M만 가능합니다</p>
			</div>
								
			<input class="btn btn-primary btn-user btn-block"  id="deletevo"  type = "submit" value="등록"/>
		</div>
	     </form>
								 
							
							<!-- END REALTIME CHART -->