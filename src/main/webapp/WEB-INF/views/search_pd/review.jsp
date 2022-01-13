<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	.review_section {
		max-width: 1170px;
		margin: auto;
		margin-top: 100px;
	}
	
	#profile_wrap {
		text-align: center;
	}
	
	#profile_wrap img {
		
	}
	
	#profile_wrap, #input_wrap {
		display: table-cell;
		vertical-align: middle;
	}
	
	#input_wrap {
		width: 65%;
	}
	
	#review_wrap {
		border-top: 3px solid black;
		padding: 10px;
	}
	
	#radio_btn_area span {
		padding: 10px;
		padding-left: 10px;
	}
	
	#inputbox_area {
		margin-top: 10px;
		width: 100%;
	}
	
	h3 {
		margin: 10px
	}
	
	textarea {
		width: 1000px;
		height: 100px;
		padding: 10px;
		box-sizing: border-box;
		border: solid 2px black;
		border-radius: 10px;
		font-size: 16px;
		resize: both;
	}
	
	#submit {
		width: 100px;
		height: 30px;
		border: 1px solid #2964e0;
		background: #fff;
		border-radius: 8px;
		margin-top: 20px;
		box-shadow: 0px 2px 8px #00000033;
		font-size: 20px;
		color: #2964e0;
		letter-spacing: -1.2px;
    	font-family: "Malgun Gothic",Dotum,"돋움","Apple SD Gothic Neo",Helvetica,Sans-serif;
	}
	
	.input_active {
		border: solid 2px #1E90FF;
	}
	
	.input_inactive {
		border: solid 2px black;
	}
</style>
<script type="text/javascript">
	$(function() {
		$('#textarea').on('focus', function() {
			$(this).removeClass('input_inactive');
			$(this).addClass('input_active');
		});
		
		$('#textarea').blur(function() {
			$(this).removeClass('input_active');
			$(this).addClass('input_inactive');
		});
	});
</script>
</head>
<body>
<section class="review_section">
	<h3>상품 의견</h3>
	<div id="review_wrap">
		<div id="profile_wrap">
			<img src='<c:url value="/resources/img/search_pd/common/profile_img_default.png"/>' />
		</div>
		<div id="input_wrap">
			<form id="review_form" action='<c:url value="/searchpd/review"/>' method="post">
				<div id="radio_btn_area">
					<span><input type="radio" name="type" value="opinion"/> 의견</span>
					<span><input type="radio" name="type" value="question"/> 질문</span>
					<span><input type="radio" name="type" value="review"/> 후기</span>
				</div>
				<div id="inputbox_area">
					<textarea id="textarea" name="content"></textarea>
				</div>
			</form>
			<button id="submit" type="submit">등록</button>
		</div>
	</div>
</section>
</body>
</html>