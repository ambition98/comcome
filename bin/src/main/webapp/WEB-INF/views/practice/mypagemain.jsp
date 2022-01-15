<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<style type="text/css">
.divNotice {
	width: 310px
}

.divNotice div table {
	width: 300px
}

.divNotice div span {
	padding: 0 0 0 160px;
}

.divNotice div .img1 {
	width: 310px;
	height: 6px
}
</style>
<div class="divNotice">
	<div>
		<img src="${pageContext.request.contextPath }/resources/images/notice2.JPG"
			alt="공지사항"> 
		<span>
			<a href="<c:url value='/board/list.do'/>"> 
				<img src="${pageContext.request.contextPath }/resources/images/more.JPG" 
					border="0" alt="more - 더보기">
			</a>
		</span>
	</div>
	<div>
		<img src="${pageContext.request.contextPath }/resources/images/Line.JPG" 
			class="img1">
	</div>
	<div>
		<!-- 공지사항 -->
		<table summary="최근 공지사항 6건을 보여주는 표입니다.">			
			<tbody>
				<!-- 반복시작 -->
				<c:forEach var="vo" items="${noticeList }"> 
					<tr>
						<td>
							<a href
							="<c:url value='/messagebox/messagebox.do?messageno=${vo.messageno }'/>">
								${vo.title}
							</a>
						</td>
					</tr>
				</c:forEach>
				<!-- 반복끝 -->
			</tbody>
		</table>
	</div>
</div>	
	
    