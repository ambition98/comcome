<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
					
 

<!-- REALTIME CHART -->
<h2 class="panel-title" id ="yourtitle">글 상세보기</h2>
							<div class="panel" id="innerpanel">
								<div class="panel-heading">
										  
									
									
									
								</div>
								<div class="panel-body">
									<c:if test="${!empty vo }">
									<h4>${vo.title}</h4>
									<p id="yourli">글번호: <span id="yourspan">${vo.boardNo}</span></p>
									<p id="yourli">작성일: <span id="yourspan"><fmt:formatDate value="${vo.regdate}" pattern="yyyy-MM-dd"/></span></p>
									<ul class="list-unstyled list-justify">
										
										
										<li id="yourli2">내용</li>
										<li id="yourli3">${vo.content}내용내용내용내용내용내용내용<br>내용내용내용내용내용내용내용</li>
										
									</ul>
								</div>
								<button id="deletevo" class="btn btn-primary btn-user btn-block" 
								onclick="del(${vo.boardNo})" >
									글삭제
								</button>
								 
								 <button id="deletevo" class="btn btn-primary btn-user btn-block"  ><a href ="<c:url value='/admin/board-update?boardNo=${vo.boardNo}'/>">
									글수정
								</a></button>
								 
								</c:if>
							</div>
							<!-- END REALTIME CHART -->