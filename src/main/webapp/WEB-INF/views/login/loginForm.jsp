<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ComCome 로그인</title>
<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/resources/img/com_logo.png'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/account/sb-admin-2.min.css'/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/account/all.min.css'/>" />
	<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
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
            alert("아이디을 입력 해주세요."); 
            $('#email').focus(); 
            event.preventDefault(); 
        }else if($('#password').val().length<1){ 
            alert("비밀번호를 입력하세요"); 
            $('#password').focus(); 
            event.preventDefault();
        }else{
        var formObj = $("form[name='frm1']");
          formObj.attr("action", "/comcome/login/sign-in");
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
										<h1 class="h4 text-gray-900 mb-4">로그인</h1>
									</div>
									<form class="user" <%-- action="<c:url value='/login/sign-in'/>" --%> name="frm1" method="post">
										<div class="form-group">
											<input type="email" class="form-control form-control-user"
												id="email" aria-describedby="emailHelp"
												placeholder="email" name="email" value="${cookie.ck_email.value }">
										</div>
										<div class="form-group">
											<input type="password" class="form-control form-control-user"
												id="password" placeholder="password" name="password">
										</div>
										<div class="form-group">
										
											<div class="custom-control custom-checkbox small">
												<!-- 이메일 기억하기 체크박스 -->
												<input type="checkbox" class="custom-control-input" name="chkSave" id="chkSave" 
													<c:if test="${!empty cookie.ck_email }"> checked="checked"</c:if>
												>
												<label class="custom-control-label" for="chkSave">Remember Me</label>
											</div>
										</div>
                                        <input type="submit" id="deletevo" value="로그인" class="btn btn-primary btn-user btn-block">
                                        <input type="button" value="카카오 로그인" id="kakaoLogin" class="btn btn-primary btn-user btn-block" 
                                        onclick="location.href='https://kauth.kakao.com/oauth/authorize?client_id=228cb11c8786e83ab545482f006fefe1&redirect_uri=http://localhost:9091/comcome/login/auth/kakao/callback&response_type=code' ">
                                        <!-- onclick="location.href='https://kauth.kakao.com/oauth/authorize?client_id=228cb11c8786e83ab545482f006fefe1&redirect_uri=http://danyleee.com:9090/comcome/login/auth/kakao/callback&response_type=code' -->
									</form>
									<hr>
									<div class="text-center">
										<a class="small" href="<c:url value="/account/register" />">회원가입</a>
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