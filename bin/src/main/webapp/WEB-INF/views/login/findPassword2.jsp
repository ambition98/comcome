<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ComCome 비밀번호 찾기</title>
<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/resources/img/com_logo.png'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/account/sb-admin-2.min.css'/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/account/all.min.css'/>" />
	<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
	
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/login/findPassword.css'/>" /> 
<style type="text/css">
	#kakaoLogin {
	    background: #F7DE2E;
	    border: #F7DE2E;
	    color: #3C1B1B;
	    font-weight: bold;
    }
}
</style>

</head>
<body class="bg-gradient-primary">
<script src="<c:url value='/resources/js/assets/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/assets/bootstrap.min.js'/>"></script>
	<script src="<c:url value='/resources/js/assets/jquery.slimscroll.min.js'/>"></script>
	<script src="<c:url value='/resources/js/assets/jquery.easypiechart.min.js'/>"></script>
	<script type="text/javascript">

$(function(){
	

	
	$("#deletevo").on("click", function(){
        if($('#email').val().length<1){ 
            alert("이메일을 입력 해주세요."); 
            $('#email').focus(); 
            event.preventDefault(); 
        }else{
        var formObj = $("form[name='frm1']");
          formObj.attr("action", "/comcome/login/find-password");
          formObj.submit();
        }
        });
	
});

</script>
	<div class="container">

		<!-- Outer Row -->
		<div class="row justify-content-center1">

			<div class="col-xl-10 col-lg-12 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="row">
							<!-- 좌측이미지 방법1: css이용 -->
							<!-- <div class="col-lg-5 d-none d-lg-block bg-login-image"></div> -->
							
							<!-- 좌측이미지 방법2: img태그 이용 -->
							<div class="col-lg-6 d-none d-lg-block">
								<a href="#"><img alt="로그인 이미지" src="<c:url value='/resources/img/account/login1.jpg'/>" style="width: 450px;height:500px"></a>
							</div>
							
							
							
							<div class="col-lg-6">
								<div class="p-5">
									<div class="text-center">
										<h1 class="h4 text-gray-900 mb-4">비밀번호 찾기</h1>
									</div>
									<form class="user" <%-- action="<c:url value='/login/find-password'/>" --%> name="frm1" method="post">
										<div class="form-group">
											<input type="text" class="form-control form-control-user"
												id="email" aria-describedby="emailHelp"
												placeholder="이메일" name="email" >
										</div>
										
                                        <input type="submit" id="deletevo" value="비밀번호 찾기" class="btn btn-primary btn-user btn-block">
                                       
									<hr>
									<div class="text-center">
										<a class="small" href="#">회원가입</a>
									</div>
									<div class="text-center">
										<a class="small" href="<c:url value='/login/find-email'/>">이메일 찾기</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>

		</div>

	</div>



</body>
</html>