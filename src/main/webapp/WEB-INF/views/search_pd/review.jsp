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
		width: 70px;
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
		resize: none;
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
    	left: 900px;
    	position: relative;
	}
	
	.input_active {
		border: solid 2px #1E90FF;
	}
	
	.input_inactive {
		border: solid 2px black;
	}
	
	#type_list {
	    display: inline-block;
	    margin: 5px;
	    padding: 5px;
	    width: 20%;
	}
	
	#review_area {
		display: inline-block;
		margin: 5px;
		padding: 5px;
	}
	
	.type_block {
		background: rgb(214 227 255);
	    border-radius: 25px;
	    margin: 10px;
	}
	
	.type_title {
	    width: 70%;
	    display: inline-block;
	    padding: 9px;
	    padding-left: 25px;
	}
	
	.type_cnt {
		padding: 3px;
	}
	.review_block {
		position: relative;
		width: 900px;
		top: -228px;
		left: 243px;
		padding: 10px;
		padding-bottom: 25px;
		border-bottom: 1px solid rgb(220,220,220);
	}
	.profile_area {
	    position: relative;
	    bottom: -50px;
	    left: 12px;
	}
	.content_area {
		padding: 10px;
	    padding-left: 75px;
	}
	.reviewer_info {
		
	}
	.content {
		margin-top: 20px;
	}
	.reply_area {
		background: #f3f3f3;
	}
	.reply_area .profile_area {
		position: relative;
	    left: 80px;
	}
	.reply_area .content_area {
		position: relative;
	    left: 80px;
	}
	.review_date {
		color: #707070;
    	font-size: 0.9em;
	}
	.reply img {
		width: 40px;
	    position: absolute;
	    top: 203px;
	    left: 32px;
	}
	
	.active_type {
		background: rgb(44 72 133);
		color: white;
	}
	
</style>
<script type="text/javascript">
	$(function() {
		console.log($('#textarea').text());
		$('#textarea').text('');
		
		$('#textarea').on('focus', function() {
			var accountNo = '${sessionScope.accountNo}';
			if(accountNo == null || accountNo == '') {
				if(confirm('로그인이 필요한 서비스입니다. 로그인 하시겠습니까?')) {
					$(this).blur();
					location.href = '<c:url value="/login/login-form" />';
				} else {
					$(this).blur();
					return false;
				}
			}
			
			$(this).removeClass('input_inactive');
			$(this).addClass('input_active');
		});
		
		$('#textarea').blur(function() {
			$(this).removeClass('input_active');
			$(this).addClass('input_inactive');
		});
		
		$('.type_block').click(function() {
			$('.type_block').each(function(index, e) {
				$(this).removeClass('active_type');
			});
			
			$(this).addClass('active_type');
			
			var type = $(this).children(':first').text();
			var searchProductNo = ${vo.searchProductNo};
			console.log(type);
			console.log(searchProductNo);
			
			var jsonData = {
				"type":type,
				"searchProductNo":searchProductNo
			}
			$.ajax({
				url:'/comcome/searchpd/change_review'
				,type:'post'
				,data: JSON.stringify(jsonData)
				,dataType:'text'
				,contentType: "application/json"
				,success: function(data) {
					console.log("success");
					console.log(data);
					
					var jsonObject = JSON.parse(data);
					jsonObject.forEach(function(e, index) {
						
					});
				}
			});
		});
	});
</script>
</head>
<body>
<section class="review_section">
	<h3>상품 의견</h3>
	<!-- 리뷰 입력 -->
	<div id="review_wrap">
		<div id="profile_wrap">
			<img src='<c:url value="/resources/img/search_pd/common/profile_img_default.png"/>' />
		</div>
		<div id="input_wrap">
			<form id="review_form" action='<c:url value="/searchpd/review"/>' method="post">
				<input class="hide" type="text" name="searchProductNo" value="${vo.searchProductNo}" />
				<input class="hide" type="text" name="accountNo" value="${sessionScope.accountNo}" />
				<div id="radio_btn_area">
					<span><input type="radio" name="type" value="opinion"/> 의견</span>
					<span><input type="radio" name="type" value="question"/> 질문</span>
					<span><input type="radio" name="type" value="review"/> 후기</span>
				</div>
				<div id="inputbox_area">
					<textarea id="textarea" name="content"></textarea>
				</div>
			<button id="submit" type="submit">등록</button>
			</form>
		</div>
		
		<!-- 리뷰 출력 -->
		<div>
			<div id="type_list">
				<div class="type_block">
					<label class="hide">all</label>
					<span class="type_title">전체보기</span>
					<span class="type_cnt">${countMap.all}</span>
				</div>
				<div class="type_block">
					<label class="hide">opinion</label>
					<span class="type_title">의견</span>
					<span class="type_cnt">${countMap.opinion}</span>
				</div>
				<div class="type_block">
					<label class="hide">question</label>
					<span class="type_title">질문</span>
					<span class="type_cnt">${countMap.question}</span>
				</div>
				<div class="type_block">
					<label class="hide">review</label>
					<span class="type_title">후기</span>
					<span class="type_cnt">${countMap.review}</span>
				</div>
			</div>
			<div id="review_area">
				<div class="review_block">
					<div class="profile_area">
						<img src='<c:url value="/resources/img/search_pd/common/profile_img_default.png"/>' />
					</div>
					<div class="content_area">
						<div class="review_info">
							<span class="review_id">아이디</span>
							<span class="review_date">1111.11.11 11:11:11</span>
						</div>
						<div>
							<div class="content"><span class="review_type"></span>컨텐츠</div>
						</div>
					</div>
					<div class="reply_area">
						<div class="reply">
							<img src='<c:url value="/resources/img/search_pd/common/reply.png"/>' />
						</div>
						<div class="profile_area">
							<img src='<c:url value="/resources/img/search_pd/common/profile_img_default.png"/>' />
						</div>
						<div class="content_area">
							<div class="review_info">
								<span class="review_id">아이디</span>
								<span class="review_date">1111.11.11 11:11:11</span>
							</div>
							<div>
								<div class="content"><span class="review_type"></span>컨텐츠</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
</body>
</html>