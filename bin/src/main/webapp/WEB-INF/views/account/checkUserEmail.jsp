<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<title>checkUserid</title>
	
<style type="text/css">
body {
	background-image: linear-gradient( 180deg,#0D0D09 10%,#545D90 100%);
}
	div {
    margin: 0;
    background-color: white;
    position: absolute;
    width: 80%;
    height: 60%;
    left: 10%;
    top: 20%;
    /* margin: 0; */
    /* padding: 0; */
    text-align: center;
	box-shadow: 0 0 3rem rgb(0 0 0 / 18%) !important;
    border-radius: 0.35rem;
}

input#email {
    border-radius: 10rem;
    width: 60%;
    margin: 0 0 1rem 7%;
    float: left;
    border: 1px solid #d1d3e2;
    color: #6e707e;
}

p#p1 {
    clear: both;
    text-align: left;
    color: #e95c5c;
    margin: 0 0 1rem 8%;
}

p#p2 {
    clear: both;
    text-align: center; 
} 

input#submit {
	border-radius: 10rem;
	float: right;margin: 0 7% 1rem 0;
	width: 150px;
}


input#btUse {
    border-radius: 10rem;
    width: 50%;
}

h2 {
    padding-top: 20px;
    color:#3a3b45 !important;
}
</style>
<script type="text/javascript" src="<c:url value='/resources/js/base/jquery-3.6.0.min.js'/>"></script>
<script type="text/javascript">

	$(function(){
		$("#submit").click(function(){	
			if($('#email').val().length<1){ 
				alert("이메일을 입력하세요"); 
				$('#email').focus(); 
				event.preventDefault(); 
			}
		});

		$('#btUse').click(function(){
			$(opener.document).find('#email').val($('#email').val());
			$(opener.document).find('#chkEmail').val('Y'); //중복확인 완료
			$(opener.document).find('#email').attr("readonly",true); 
			self.close();
		});
	});
	
</script>	
</head>
<body>
	<div>
	<h2>이메일 중복 체크</h2><br>
	<form name="frmId" method="post" action="<c:url value='/account/checkUserEmail'/>">
		
		<input type="email" name="email" id="email" class="form-control form-control-user" 
			title="아이디입력" value="${param.email}">
		<input type="submit" id="submit" class="btn btn-primary btn-user" value="이메일 확인"/>
			<%-- OnClick="location.href='<c:url value="/account/checkUserEmail?email="${email}/>'"/> --%>
		<c:if test="${result==1 || result==0}">
			<c:if test="${result == EXIST_EMAIL}">		
				<p>이미 등록된 아이디입니다. 다른 아이디를 입력하세요</p>
			</c:if>		
			<c:if test="${result == NON_EXIST_EMAIL}">		
				<p id="p2">사용가능한 이메일입니다. [사용하기] 버튼을 클릭하세요</p>			
				<input type="button" value="사용하기" id="btUse" class="btn btn-primary btn-user">
				<!-- <input type="button" value="사용하기" id="btUse">
				<p>사용가능한 아이디입니다. [사용하기] 버튼을 클릭하세요</p> -->
			</c:if>
		</c:if>		
		<%-- <%if(${cookie.ck_result.value }=="0"){%>
			<p id="p1">이미 등록된 이메일입니다. 다른 이메일을 입력하세요</p>	
		<%}else if(result==1){	%>
			<p id="p2">사용가능한 이메일입니다. [사용하기] 버튼을 클릭하세요</p>			
			<input type="button" value="사용하기" id="btUse" class="btn btn-primary btn-user">
		<%} %> --%>
	</form>
	</div>
</body>
</html>






