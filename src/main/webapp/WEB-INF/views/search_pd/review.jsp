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
	
	#no_review {
		position: relative;
	    bottom: 75px;
	    left: 263px;
	    color: #b1b1b1;
	    font-size: 1.2em;
	}
	
</style>
<script type="text/javascript">
	$(function() {
		//console.log($('#textarea').text());
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
			var loadingImg = '<div class="loading"><img src="<c:url value="/resources/img/search_pd/common/lodding.gif" />" /></div>';
			$('#review_area').html(loadingImg);
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
					$('#review_area').html('');
					
					var jsonObject = JSON.parse(data);
					
					console.log('jsonObject: '+jsonObject.length);
					if(jsonObject.length == 0) {
						var noReview = '<div id="no_review">'
										+'이 상품에 대한 리뷰가 존재하지 않습니다'
									  +'</div>'
									  
						$('#review_area').html(noReview);
						return;
					}
					
					
					jsonObject.forEach(function(reviewObj, index) {
						//console.log(e);
						var accountObj = reviewObj.accountVo;
						//var replyObj = reviewObj.searchPdReviewReplyVo;
						//var adminObj = replyObj.adminVo;
						var email = accountObj.email;
						var content = reviewObj.content;
						var regdate = reviewObj.regdate;
						
						var date = regdate.substr(0, 10);
						var time = regdate.substr(11, 8);
						regdate = date + ' ' + time;
						
						if(reviewObj.searchPdReviewReplyVo != null) {
							var replyContent = reviewObj.searchPdReviewReplyVo.content;
							var replyRegdate = reviewObj.searchPdReviewReplyVo.regdate;
							date = replyRegdate.substr(0, 10);
							time = replyRegdate.substr(11, 8);
							replyRegdate = date + ' ' + time;
							
							if(reviewObj.searchPdReviewReplyVo.adminVo != null) {
								var replyEmail = reviewObj.searchPdReviewReplyVo.adminVo.email;
							}
						}
						
						console.log('email: ' + email);
						console.log('content: ' + content);
						console.log('regdate: ' + regdate);
						console.log('replyEmail: ' + replyEmail);
						console.log('replyContent: ' + replyContent);
						console.log('replyRegdate: ' + replyRegdate);
						console.log('');
						
						var profileArea = '<div class="profile_area">'
											+'<img src="<c:url value="/resources/img/search_pd/common/profile_img_default.png"/>" />'
										 +'</div>';
						
						var contentArea = '<div class="content_area">'
											+'<div class="review_info">'
												+'<span class="review_id">'+email+'</span>'
												+'<span class="review_date">'+regdate+'</span>'
											+'</div>'
											+'<div>'
												+'<div class="content"><span class="review_type"></span>'+content+'</div>'
											+'</div>'
									      +'</div>'
						var replyArea = '';
						if(replyContent != undefined) {
							var replyProfile = '<div class="reply">'
													+'<img src="<c:url value="/resources/img/search_pd/common/reply.png"/>" />'
												+'</div>'
												+'<div class="profile_area">'
													+'<img src="<c:url value="/resources/img/search_pd/common/profile_img_default.png"/>" />'
												+'</div>'
												
							var replyContent = '<div class="content_area">'
												 +'<div class="review_info">'
													+'<span class="review_id">'+replyEmail+'</span>'
													+'<span class="review_date">'+replyRegdate+'</span>'
												 +'</div>'
											 	 +'<div>'
													+'<div class="content"><span class="review_type"></span>'+replyContent+'</div>'
													+'</div>'
												 +'</div>'
												 
							replyArea += '<div class="reply_area">'
											+ replyProfile + replyContent
										+'</div>'
							
						}
						
						var reviewBlock = '<div class="review_block">'
											+ profileArea + contentArea + replyArea
										+ '</div>'
						
						$('#review_area').append(reviewBlock);
					});
				}
			});
		});
		$('.type_block:first').trigger('click');
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
					<span><input type="radio" name="type" value="opinion" checked="checked"/> 의견</span>
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
				<%-- <div class="review_block">
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
				</div> --%>
			</div>
		</div>
	</div>
</section>
</body>
</html>