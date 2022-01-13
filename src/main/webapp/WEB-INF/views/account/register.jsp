<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>sign up for developer</title>

<!-- Custom fonts for this template-->
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/account/sb-admin-2.min.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/account/all.min.css'/>" />

<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->


<style type="text/css">

form.user .form-control-user {
	font-size: 0.85rem;
}

.base {
	width: 150%;
}

.base2 {
	width: 50%;
}

form.user .btn-user {
	font-size: 0.9rem;
}

#btnChkEmail {
vertical-align:middle;
	float: right;
	width: 50%;
	height:100%;
}
#btnChkAddress{
vertical-align:middle;
	float: right;
	width: 50%;
	height:100%;
}
input#zipcode {
    width: 125px;
    margin-left: 11px;
}

input#btnChkAddress {
    width: 125px;
    height: 50px;
    margin-left: 3px;
}

</style>
<script type="text/javascript" src="<c:url value='/resources/js/base/jquery-3.6.0.min.js'/>"></script>
<script type="text/javascript">

		/* $(function(){
			$('#regbtn').click(function(){
				if ($('#exampleInputEmail').val().length < 1) {
					alert("이메일을(를) 입력하세요");
					$('#exampleInputEmail').focus();
					event.preventDefault();
				} else if ($('#exampleInputPassword').val().length < 1) {
					alert("비밀번호을(를) 입력하세요");
					$('#exampleInputPassword').focus();
					event.preventDefault();
				} else if ($('#exampleRepeatPassword').val().length < 1) {
					alert("비밀번호확인을(를) 입력하세요");
					$('#exampleRepeatPassword').focus();
					event.preventDefault();
				} else if ($('#name').val().length < 1) {
					alert("이름을(를) 입력하세요");
					$('#name').focus();
					event.preventDefault();
				}else if($('#chkEmail').val()!='Y'){
					alert('이메일 중복확인을 하세요!');
					$('#btnChkEmail').focus();
					event.preventDefault();
				} else if ($('#exampleRepeatPassword').val() != $(
						'#exampleInputPassword').val()) {
					alert("비밀번호가 일치하지 않습니다.");
					event.preventDefault();
				}  
			});
	}); */
		function emailChk(){
		    var width = '800';
		    var height = '700';
		    var left = Math.ceil(( window.screen.width - width )/2);
		    var top = Math.ceil(( window.screen.height - height )/2);

		    var email=$('#email').val();
		    open('checkUserEmail?email='+email,'dup',
			 'width='+width+',height='+height+',left='+left+',top='+top+',location=yes,resizable=yes');
		    //?email='+email
		}
	
		function addressChk(){
		    var width = '800';
		    var height = '500';
		    var left = Math.ceil(( window.screen.width - width )/2);
		    var top = Math.ceil(( window.screen.height - height )/2);

		    var email=$('#email').val();
		    open('checkAddress','dup',
			 'width='+width+',height='+height+',left='+left+',top='+top+',location=yes,resizable=yes');
		    //?email='+email
		}
	
</script>
</head>
</head>
<body class="bg-gradient-primary">
	<div class="container">
		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
					<div class="col-lg-7">
						<div class="p-5">
							<div class="text-center">
								<h1 class="h4 text-gray-900 mb-4">계정 생성!</h1>
							</div>
							<hr>
							<form class="user" name="frm1" method="post" action="register_ok.jsp">
								<!-- 이메일 -->
								<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
										<input type="email" class="form-control form-control-user base"
										id="email" placeholder="이메일 주소" name="email">
									</div>
									<div class="col-sm-6">
										<input type="button" value="중복확인" id="btnChkEmail" 
									class="btn btn-primary btn-user btn-block" onclick="emailChk()" title="새창열림">
									</div>
								</div>

								<!-- 비밀번호, 비밀번호확인 -->
								<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
										<input type="password" class="form-control form-control-user"
											id="pwd" placeholder="비밀번호" name="pwd">
									</div>
									<div class="col-sm-6">
										<input type="password" class="form-control form-control-user"
											id="pwdCheck" placeholder="비밀번호 확인">
									</div>
								</div>

								<!-- 이름 -->
								<div class="form-group">
									<input type="text" class="form-control form-control-user"
										id="name" placeholder="이름" name="name">
								</div>
								
								<!-- 주소 -->
								<div class="form-group row">
									<input type="email" class="form-control form-control-user base2"
										id="zipcode" placeholder="우편번호" name="zipcode">
									
									<div class="col-sm-6 mb-3 mb-sm-0">
										<input type="email" class="form-control form-control-user"
										id="address1" placeholder="주소" name="address">
									</div>
										<input type="button" value="주소찾기" id="btnChkAddress" 
									class="btn btn-primary btn-user btn-block" onclick="addressChk()" title="새창열림">
								</div>
								<div class="form-group">
									<input type="text" class="form-control form-control-user"
										id="address2" placeholder="주소 상세" name="addressDetale">
								</div>

								<!-- 연락처 -->
								<div class="form-group">
									<input type="number" class="form-control form-control-user"
										id="phoneNumber" placeholder="연락처(숫자만)" name="phone">
								</div>


								<!-- 카드번호 -->
								<div class="form-group" id="bn">
									<input type="text" class="form-control form-control-user"
										id="cardNo" placeholder="신용카드 번호"
										name="cardNo">
								</div>

								<!-- 등록버튼 -->
								<input type="submit" class="btn btn-primary btn-user btn-block" id="regbtn" value="계정 등록">
								
							</form>
							<hr>
							<div class="text-center">
								<a class="small" href="forgot-password.jsp">비밀번호 찾기</a>
							</div>
							<div class="text-center">
								<a class="small" href="../login/login.jsp">로그인</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<input type ="hidden" name="chkEmail" id="chkEmail">

</body>

</html>