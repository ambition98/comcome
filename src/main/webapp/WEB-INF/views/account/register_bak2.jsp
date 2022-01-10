<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/account/sb-admin-2.min.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/account/all.min.css'/>" />
<style type="text/css">
.divForm .firstDiv {
	border-top: 2px solid #e2e2e2 /* #aacc00 */;
}
</style>
<title>회원등록</title>
<script type="text/javascript"
	src="<c:url value='/resources/js/base/jquery-3.6.0.min.js'/>"></script>
<script type="text/javascript">
	/* $(function(){
		$('form[name=frmWrite]').submit(function(){
			$('.infobox').each(function(idx, item){
				if($(this).val().length<1){
					alert($(this).prev().html() + "을(를) 입력하세요");
					$(this).focus();
					event.preventDefault();
					return false;  //each 탈출
				}
			});
		}); */

	/* $('#btList').click(function(){
		location.href="<c:url value='/account/register.do'/>";	
	}); 
	});*/
	function emailChk() {
		var width = '800';
		var height = '500';
		var left = Math.ceil((window.screen.width - width) / 2);
		var top = Math.ceil((window.screen.height - height) / 2);

		var email = $('#email').val();
		open('checkUserEmail?email=' + email, 'dup', 'width=' + width
				+ ',height=' + height + ',left=' + left + ',top=' + top
				+ ',location=yes,resizable=yes');
		//?email='+email
	}
</script>

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
							<form name="frmWrite" method="post"
								action="<c:url value='/account/register'/>">
								<fieldset>
									<legend>글쓰기</legend>
									<div class="firstDiv">
										<label for="email">이메일</label> <input type="email" id="email"
											name="email" class="infobox" /> <input type="button"
											value="중복확인" id="btnChkEmail" onclick="emailChk()"
											title="새창열림">
									</div>
									<div>
										<label for="pwd">비밀번호</label> <input type="password" id="pwd"
											name="pwd" class="infobox" />
									</div>
									<!-- <div>
							            <label for="salt">비밀번호</label>
							            <input type="password" id="salt" name="salt" class="infobox" />
							        </div> -->
									<div>
										<label for="name">이름</label> <input type="text" id="name"
											name="name" class="infobox" />
									</div>
									<div>
										<label for="address">주소</label> <input type="text"
											id="address" name="address" class="infobox" />
									</div>
									<div>
										<label for="tel">연락처</label> <input type="text" id="tel"
											name="tel" class="infobox" />
									</div>
									<div>
										<label for="cardNo">카드번호</label> <input type="text"
											id="cardNo" name="cardNo" class="infobox" />
									</div>
									<div class="center">
										<input type="submit" value="등록" />
									</div>

								</fieldset>
							</form>
							<input type="text" name="chkEmail" id="chkEmail">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>