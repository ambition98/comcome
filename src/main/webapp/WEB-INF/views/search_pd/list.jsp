<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ComCome 상품목록</title>
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
    <script src="<c:url value='/resources/js/base/jquery-ui.min.js' />"></script>
    <script src="<c:url value='/resources/js/base/jquery.slicknav.js' />"></script>
    <script src="<c:url value='/resources/js/base/mixitup.min.js' />"></script>
    <script src="<c:url value='/resources/js/base/owl.carousel.min.js' />"></script>
    <script src="<c:url value='/resources/js/base/main.js' />"></script>
<style type="text/css">
	.section {
		max-width: 1170px;
		margin: auto;
	}
	
	.search_pd {
		margin: 10px;
		padding: 5px;
		border-bottom: solid 1px rgb(210, 210, 210);
	}
	
	.search_pd div:not(.detail){
		display: inline-block;
	}
	.pd_thumbnail {
		width: 12%;
	}
	.pd_name {
		width: 70%;
		margin: 0px 0px 15px 19px;
	}
	.pd_name a {
		font-size: 1.1em;
		color: black;
	}
	.pd_name a:hover {
		color: black;
		
	}
	.pd_price {
		width: 15%;
	}
	
	.title {
		margin-bottom: 5px;
	}
	
	.change_list input {
		margin-left: 20px;
	}
	
	.optionBubble {
		display: inline-block;
		background: rgb(220, 220, 220);
		margin: 3px 5px;
		padding: 5px 10px;
		font-size: 0.8em;
		border-radius: 15px;
	}
	
	.type { 
		display: none;
	}
	.option {
		display: inline-block;
		margin-right: 7px;
	}
	.closeBubble {
		cursor: pointer;
	}
	
	.loading {
		text-align: center;
	}
	.detail {
		margin-top: 15px;
		font-size: 0.85em;
		color: rgb(180,180,180);
	}
	
	.detail th {
		width: 75px;
	}
	.pd_thumbnail{
		position: relative;
		top: 50px;
	}
	
	#list_count {
		margin-top: 20px;
		padding: 20px;
		font-size: 1.1em;
	}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('input[type=checkbox]').click(function() {
			var isChecked = $(this).is(":checked");
			var type = $(this).parent().parent().parent().children('div:eq(0)').text();
			var option = $(this).parent().text();
			var optionBubble = '<div class="optionBubble"><span class="type">'+type+'</span>'
								+ '<span class="option">'+option+'</span><span class="closeBubble">x</span></div>';
			
			//console.log(optionBubble);
			//console.log('type: '+type);
			//console.log('option: ' + option);
			//console.log(isChecked);
			if(isChecked) {
				$('.option_container').append(optionBubble);
			} else {
				//console.log('before: ' + $('.option_container').html());
				var optionHtml = $('.option_container').html();
				optionHtml = optionHtml.replace(optionBubble, '');
				//console.log('bubble: ' + optionBubble)
				$('.option_container').html(optionHtml);
				//console.log('after: ' + $('.option_container').html());
			}
			
			changeList();
		});
		
		/* 카테고리 클릭 시 초기 설정 */
		const brandNo = '${brandNo}';
		const screenSizeNo = '${screenSizeNo}';
		const keyword = '${keyword}';
		
		if(brandNo == 0 && screenSizeNo == 0) {
			var jsonData = {"keyword":"all"}
			ajax(jsonData);
		} else if(keyword != '') {
			var jsonData = {"keyword":keyword}
		}
		
		console.log('brandNo: '+ brandNo);
		console.log('screenSizeNo: ' + screenSizeNo);
		console.log('keyword: '+ keyword);
		
		if(brandNo != '') {
			$('#b'+brandNo).trigger('click');
			console.log('enter1');
		}
			
		if(screenSizeNo != '') {
			$('#s'+screenSizeNo).trigger('click');
			console.log('enter2');
		}
		
		$('.option_container').on('click', 'div .closeBubble', function() {
			var optionBubble = '<div class="optionBubble">'+$(this).parent().html()+'</div>'
			console.log(optionBubble);
			
			var optionHtml = $('.option_container').html();
			optionHtml = optionHtml.replace(optionBubble, '');
			$('.option_container').html(optionHtml);
			
		});
		
		//bubble을 기준으로 데이터를 ajax로 가져오는 메서드
		function changeList() {
			var brandList = [];
			var screenSizeList = [];
			var cpuList = [];
			var memoryList = [];
			
			$('input[name=brand]').each(function() {
				var isChecked = $(this).is(":checked");
				if(isChecked) {
					var value = $(this).val();
					brandList.push(value.toLowerCase());
				}
			});
			
			$('input[name=screen_size]').each(function() {
				var isChecked = $(this).is(":checked");
				if(isChecked) {
					var value = $(this).val();
					screenSizeList.push(value.toLowerCase());
				}
			});
			
			$('input[name=cpu]').each(function() {
				var isChecked = $(this).is(":checked");
				if(isChecked) {
					var value = $(this).val();
					cpuList.push(value.toLowerCase());
				}
			});
			
			$('input[name=memory]').each(function() {
				var isChecked = $(this).is(":checked");
				if(isChecked) {
					var value = $(this).val();
					memoryList.push(value.toLowerCase());
				}
			});
			
			var jsonData = {
				brand:brandList,
				screenSize:screenSizeList,
				cpu:cpuList,
				memory:memoryList
			}
			console.log(jsonData);
			
			ajax(jsonData);
		}
		
		function ajax(jsonData){
			var loadingImg = '<div class="loading"><img src="<c:url value="/resources/img/search_pd/common/lodding.gif" />" /></div>';
			$('.search_pd_container').html(loadingImg);
			$('#list_count').html('');
			
			$.ajax({
				url:"/comcome/searchpd/changelist"
				,type:"post"
				,data: JSON.stringify(jsonData)
				,dataType:"text"
				,contentType: "application/json"
				,success: function(data){
					//console.log(data);
					$('.search_pd_container').html('');
					var jsonObject = JSON.parse(data);
					var listSize = jsonObject.length;
					
					$('#list_count').html(listSize + '건 검색되었습니다.');
					jsonObject.forEach(function(pd, i) {
						//console.log(pd);
						var detail = JSON.parse(pd.detail);
						
						var pdThumbnail = '<div class="pd_thumbnail">'
											+'<a href="<c:url value="/searchpd/detail?pdNo='+pd.searchProductNo+'" />">'
											+'<img src="<c:url value="/resources/img/search_pd/thumbnail" />/'+ pd.thumbnail +'" />'
											+'</a></div>';
						/* var pdDetail = '<div class="detail">'
										+'<span class="detailName"><b>cpu</b></span>'+detail.cpu+'<br>'
										+'<span class="detailName"><b>memory</b></span>'+detail.memory+'<br>'
										+'<span class="detailName"><b>gpu</b></span>'+detail.gpu+'<br>'
										+'<span class="detailName"><b>무게</b></span>'+detail.weight+'<br>'
										+'<span class="detailName"><b>용도</b></span>'+detail.usage+'<br>'
										+'</div>' */
						var pdDetail = '<div class="detail">'
										+'<table>'
										+'<tr><th>cpu</th><td>'+detail.cpu+'</td></tr>'
										+'<tr><th>memory</th><td>'+detail.memory+'</td></tr>'
										+'<tr><th>gpu</th><td>'+detail.gpu+'</td></tr>'
										+'<tr><th>무게</th><td>'+detail.weight+'</td></tr>'
										+'<tr><th>용도</th><td>'+detail.usage+'</td></tr>'
										+'</table></div>'
						var pdName = '<div class="pd_name">'
					        			+'<a href="<c:url value="/searchpd/detail?pdNo='+pd.searchProductNo+'" />">'+ pd.name +'</a>'
					        			+ pdDetail
					        			+'</div>';
					    var pdPrice = '<div class="pd_price">1,111,111 원</div>';
					    
					    var pdDiv = '<div class="search_pd">'
					    			+ pdThumbnail + pdName + pdPrice + '</div>';
						
					    //console.log(pdDiv);
					    
					    $('.search_pd_container').append(pdDiv);
					});
					
				}
				,error: function(status, error){
					alert("에러발생 : "+status+", "+ error);
				}
			});
		}
	});
</script>
</head>
<body>
<section class="section">
	<div><h3>상품목록</h3></div>
	<div class="change_list">
		<div class="brand">
			<div class="title">브랜드</div>
			<div>
				<label><input type="checkbox" name="brand" id="b1" value="삼성전자"/>삼성전자</label>
				<label><input type="checkbox" name="brand" id="b2" value="Apple"/>Apple</label>
				<label><input type="checkbox" name="brand" id="b3" value="LG전자"/>LG전자</label>
				<label><input type="checkbox" name="brand" id="b4" value="DELL"/>DELL</label>
				<label><input type="checkbox" name="brand" id="b5" value="MSI"/>MSI</label>
				<label><input type="checkbox" name="brand" id="b6" value="레노버"/>레노버</label>
				<label><input type="checkbox" name="brand" id="b7" value="한성컴퓨터"/>한성컴퓨터</label>
			</div>
		</div>
		<div class="screen_size">
			<div class="title">화면크기</div>
			<div>
				<label><input type="checkbox" name="screen_size" id="s1" value="17"/>17인치 이상</label>
				<label><input type="checkbox" name="screen_size" id="s2" value="16"/>16인치대</label>
				<label><input type="checkbox" name="screen_size" id="s3" value="15"/>15인치대</label>
				<label><input type="checkbox" name="screen_size" id="s4" value="14"/>14인치대</label>
				<label><input type="checkbox" name="screen_size" id="s5" value="13"/>13인치 이하</label>
			</div>
		</div>
		<div class="cpu">
			<div class="title">CPU</div>
			<div>
				<label><input type="checkbox" name="cpu" value="ARM"/>ARM</label>
			</div>
			<div>
				<label><input type="checkbox" name="cpu" value="인텔 코어i3"/>인텔 i3</label>
				<label><input type="checkbox" name="cpu" value="인텔 코어i5"/>인텔 i5</label>
				<label><input type="checkbox" name="cpu" value="인텔 코어i7"/>인텔 i7</label>
				<label><input type="checkbox" name="cpu" value="인텔 코어i9"/>인텔 i9</label>
			</div>
			<div>
				<label><input type="checkbox" name="cpu" value="라이젠3"/>라이젠 3</label>
				<label><input type="checkbox" name="cpu" value="라이젠5"/>라이젠 5</label>
				<label><input type="checkbox" name="cpu" value="라이젠7"/>라이젠 7</label>
				<label><input type="checkbox" name="cpu" value="라이젠9"/>라이젠 9</label>
			</div>
		</div>
		<div class="memory">
			<div class="title">Memory</div>
			<div>
				<label><input type="checkbox" name="memory" value="16"/>16GB 이상</label>
				<label><input type="checkbox" name="memory" value="8"/>8GB</label>
				<label><input type="checkbox" name="memory" value="4"/>4GB 이하</label>
			</div>
		</div>
	</div>
	<div class="option_container">
	</div>
	<div id="list_count">
		
	</div>
	<div class="search_pd_container">
		<!-- 검색의 경우에만 해당 구문 실행 (나머진 ajax) -->
		<%-- <c:forEach var="vo" items="${searchPdList}">
			<div class="search_pd">
				<div class="pd_thumbnail">
					<a href="<c:url value="/searchpd/detail?pdNo=${vo.searchProductNo}" />">
						<img src="<c:url value="/resources/img/search_pd/thumbnail" />/${vo.thumbnail}" />
					</a>
				</div>
				<div class="pd_name">
					<a href="<c:url value="/searchpd/detail?pdNo=${vo.searchProductNo}" />">${vo.name}</a>
				</div>
				<div class="detail">'
					<table>
						<tr><th>cpu</th><td>${vo.detail}</td></tr>
						<tr><th>memory</th><td>'+detail.memory+'</td></tr>
						<tr><th>gpu</th><td>'+detail.gpu+'</td></tr>
						<tr><th>무게</th><td>'+detail.weight+'</td></tr>
						<tr><th>용도</th><td>'+detail.usage+'</td></tr>
					</table>
				</div>
				<div class="pd_price">
					1,111,111
				</div>
				<br>
			</div>
		</c:forEach> --%>
	</div>
</section>
</body>
</html>
<%@ include file="../include/footer.jsp"%>