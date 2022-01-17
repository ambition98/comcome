<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>
<br/>
	<section class="1">
		<div class="checkout__form ">
			<h2 class="text-center">쪽지 보내기</h2>
			<form action='<c:url value="/message/write"/>' name="frmWrite"
				method="post" enctype="multipart/form-data">
				<div class="checkout__input">
					<p style="margin:15px"><b>수신인</b></p>
					<p style="width:85%; margin-left:10%;"><b> ${vo.email} </b></p>
					<hr>
				</div>
				<div class="checkout__input">
					<p style="margin:15px"><b>제목</b></p>
					<input type="text" style="color: black;width:85%; margin-left:10%" id="title" name="title"
						class="infobox">
					<hr>
				</div>
				<div class="checkout__input">
					<p style="margin:15px"><b>내용</b></p>
					<textarea id="content" style="width:85%; margin-left:10%" name="content" rows="5" cols="40"></textarea>
				</div>
				<hr>
				<div>
					<button type="submit" style="width:30%; margin-left:65%"class="btn btn-primary">보내기</button>
				</div>
				<input type="hidden" name="accountNo" value="${sessionScope.accountNo}"> 
				<input type="hidden" name="email" id="email" value="${sessionScope.email}">
		</form>
		</div>
	</section>
</body>
</html>