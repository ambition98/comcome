<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<body>
    <table style="width:700px">
        <c:forEach var="cm" items="${commentList}">
        <tr>    
            <td>
                ${cm.name}(<fmt:formatDate value="${cm.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/>)
                <br>
                ${cm.content}
            </td>
        </tr>
        </c:forEach>
    </table>
</body>