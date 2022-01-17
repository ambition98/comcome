<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ComCome 상품상세</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/base/bootstrap.min.css' />" type="text/css">
    <link rel="stylesheet" href="<c:url value='/resources/css/base/font-awesome.min.css"' />" type="text/css">
    <link rel="stylesheet" href="<c:url value='/resources/css/base/nice-select.css' />" type="text/css">
    <link rel="stylesheet" href="<c:url value='/resources/css/base/jquery-ui.min.css' />" type="text/css">
    <link rel="stylesheet" href="<c:url value='/resources/css/base/owl.carousel.min.css' />" type="text/css">
    <link rel="stylesheet" href="<c:url value='/resources/css/base/slicknav.min.css' />" type="text/css">
    <link rel="stylesheet" href="<c:url value='/resources/css/base/style.css' />" type="text/css">
    
    <script src="<c:url value='/resources/js/base/jquery-3.3.1.min.js' />"></script>
    <script src="<c:url value='/resources/js/base/bootstrap.min.js' />"></script>
    <script src="<c:url value='/resources/js/base/jquery.nice-select.min.js' />"></script>
    <script src="<c:url value='/resources/js/base/query-ui.min.js' />"></script>
    <script src="<c:url value='/resources/js/base/jquery.slicknav.js' />"></script>
    <script src="<c:url value='/resources/js/base/mixitup.min.js' />"></script>
    <script src="<c:url value='/resources/js/base/owl.carousel.min.js' />"></script>
    <script src="<c:url value='/resources/js/base/main.js' />"></script>
<style type="text/css">
	#mall_list a { color: black; }
	#mall_list a:hover { color: black; }
	#section_top div { display: table-cell; }
	.section {
		max-width: 1170px;
		margin: auto;
	}

	.top_element {
		display: table-cell;
		text-align: center;
		vertical-align: middle;
	}
	
	#mall_list {
		width: 45%;
	}
	
	#mall_table {
		text-align: left;
		border-collapse: separate;
		border-spacing: 50px 0px;
	}
	
	#main_img img {
		margin-left: 20%
	}
	
	td {
		padding: 10px;
	}
	
	#detail_img {
		margin: auto;
		text-align: center;
	}
	
	#showMoreImgWrap {
	    position: relative;
	    top: -150px;
	    width: 100%;
	    height: 150px;
	    bottom: 0;
	    background: rgb(255,255,255);
	    background: linear-gradient(180deg
								    , rgba(255,255,255,0) 0%
								    , rgba(255,255,255,0.07) 13%
								    , rgba(255,255,255,0.2) 24%
								    , rgba(255,255,255,1) 62%);
	}
	
	#showMoreInfo {
	    width: 400px;
	    height: 68px;
	    border: 1px solid #2964e0;
	    background: #fff;
	    border-radius: 8px;
	    margin-top: 100px;
	    box-shadow: 0px 2px 8px #00000033;
	    font-size: 20px;
	    color: #2964e0;
	    font-weight: bold;
	    letter-spacing: -1.2px;
	    font-family: "Malgun Gothic",Dotum,"돋움","Apple SD Gothic Neo",Helvetica,Sans-serif;
	}
	
	#remote_cont {
		position: fixed;
		width: 106px;
		height: 20px;
		right: 14%;
		bottom: 25%;
		text-align: center;
	}
	
	#remote_cont button {
		background: #2d4755;
		margin: 3px;
		padding: 5px;
		border: 1px solid black;
		border-radius: 10px;
		color: white;
	}
	#lowest_price {
		font-size: 1.4em;
	    color: #dd0000;
	}
	
	.hide {
		display: none;
	}
</style>
<script type="text/javascript">
	$(function() {
		var scrollHeight = 50000;
		/*
			ajax로 받아온 데이터를 이용하여 html을 document내에 추가 해도
			body의 scrollSize가 변화가 없어 기존 설계가 실패
		*/
		
		var imgTagList = [];
		<c:forEach var="i" begin="2" end="${fn:length(imgLinkList)-2}">
			imgTagList.push('<img src="${imgLinkList[i]}" />');
		</c:forEach>
		
		$(document).scroll(function(){
			var con = $("#remote_cont");
			var position = $(window).scrollTop();

			if(position > 250){ con.fadeIn(500); }
			else if(position < 250){ con.fadeOut(500); }
		});

		$("#go_to_top").click(function(){
			//$("html, body").animate({scrollTop: 0}, 500);
			$("html, body").scrollTop(0);
		});
		
		$("#go_to_bottom").click(function(){
			//$("html, body").animate({scrollTop: scrollHeight}, 500);
			$("html, body").scrollTop(scrollHeight);
		});
		 
		$('#showMoreInfo').click(function() {
			$('#showMoreImgWrap').addClass('hide');
			
			imgTagList.forEach(function(e) {
				$('#others_img').append(e);
			});
			//console.log('div: '+$('#others_img').prop('scrollHeight'));
			//console.log('body: '+document.body.scrollHeight);
		});
	});
</script>
</head>
<body>
<div id="remote_cont">
	<button id="go_to_top">맨위로 ∧</button>
	<button id="go_to_bottom">맨아래로 ∨</button>
</div>
<section class="section">
	<div id="section_top">
		<h3>${vo.name}</h3>
		<div id="main_img" class="top_element">
			<img src="${imgLinkList[0]}" />
		</div>
		<div id="mall_list" class="top_element">
			<table id="mall_table">
				<tr id="lowest_price">
					<td class="mall"><b>최저가</b></td>
					<td class="price"><b><fmt:formatNumber type="number" maxFractionDigits="3" value="${pdList[0].price}"/>원</b></td>
				</tr>
				<c:forEach var="pd" items="${pdList}">
					<tr>
						<td class="mall"><a href="${pd.link}">${pd.mallName}</a></td>
						<td class="price"><a href="${pd.link}"><fmt:formatNumber type="number" maxFractionDigits="3" value="${pd.price}"/>원</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	
	<div id="detail_img">
		<div id="first_img">
			<img src="${imgLinkList[1]}" />
		</div>
		<div id="others_img">
		</div>
		<div id="showMoreImgWrap">
			<button id="showMoreInfo">상품 정보 더보기</button>
		</div>
	</div>
</section>
<c:import url="/searchpd/review?no=${vo.searchProductNo}" />
</body>
</html>
<%@ include file="../include/footer.jsp"%>