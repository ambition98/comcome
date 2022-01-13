<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<div>
			<h2>상품 등록 폼</h2>
		</div>
		<h4>상품 입력</h4>

		<form action="/comcome/upload" method="post" enctype="multipart/form-data">
			<ul>
				<li>파일1<input type="file" name="file1"></li>
				<li>파일2<input type="file" name="file2"></li>
			</ul>
			<button>전송</button>
		</form>
	</div>
</body>
</html>