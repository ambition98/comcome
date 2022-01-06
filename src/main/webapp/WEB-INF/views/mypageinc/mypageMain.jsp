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
                <div class="container-message">
                    <h1 class="message">쪽지함</h1>
                    <table>
                    <td>
                    <p>프로필 </p>
 <%--                  	<article id="center">
					<%@ include file="messagebox/messagebox.jsp"%>
					</article> --%>
                    </td>
                    </table>
                </div>
            </div>
        </div>

</body>
</html>