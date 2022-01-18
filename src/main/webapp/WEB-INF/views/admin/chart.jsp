<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  


	<!-- WRAPPER -->
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<h3 class="page-title">사이트 요약</h3>
					<h4>금일 날짜: ${date} <br><br>가입:${b[6]}명 &nbsp&nbsp&nbsp 매출:<fmt:formatNumber value="${c[6]}" pattern="#,###" />원 &nbsp&nbsp&nbsp
												 거래:${d[6]}건 &nbsp&nbsp&nbsp 게시판등록:${e[6]}개 &nbsp&nbsp&nbsp 수익:<fmt:formatNumber value="${sum}" pattern="#,###" />원</h4>
					<div class="row">
						<div class="col-md-6">
							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">가입자 현황(단위:명)</h3>
								</div>
								<div class="panel-body">
									<div id="bar1" class="ct-chart"></div>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">특가상품 매출 현황(단위:원)</h3>
								</div>
								<div class="panel-body">
									<div id="bar2" class="ct-chart"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">특가상품 판매 현황(단위:건)</h3>
								</div>
								<div class="panel-body">
									<div id="bar3" class="ct-chart"></div>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">중고거래게시판 등록 현황(단위:개)</h3>
								</div>
								<div class="panel-body">
									<div id="bar4" class="ct-chart"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- END MAIN CONTENT -->
		<!-- END MAIN -->
		<div class="clearfix"></div>

	<script>
	$(function() {
		var options;
		// bar chart
		options = {
			height: "300px",
			axisX: {
				showGrid: false
			},
		};
		var data = {
				labels: ['${a[0]}','${a[1]}','${a[2]}','${a[3]}','${a[4]}','${a[5]}','${a[6]}'],
				series: [
					[${b[0]}, ${b[1]}, ${b[2]}, ${b[3]}, ${b[4]}, ${b[5]},${b[6]}],
				]
		};
		new Chartist.Bar('#bar1', data, options);
		data = {
				labels: ['${a[0]}','${a[1]}','${a[2]}','${a[3]}','${a[4]}','${a[5]}','${a[6]}'],
				series: [
					[${c[0]}, ${c[1]}, ${c[2]}, ${c[3]}, ${c[4]}, ${c[5]},${c[6]}],
				]
		};
		new Chartist.Bar('#bar2', data, options);
		
		data = {
				labels: ['${a[0]}','${a[1]}','${a[2]}','${a[3]}','${a[4]}','${a[5]}','${a[6]}'],
				series: [
					[${d[0]}, ${d[1]}, ${d[2]}, ${d[3]}, ${d[4]}, ${d[5]},${d[6]}],
				]
		};
		new Chartist.Bar('#bar3', data, options);
		
		data = {
				labels: ['${a[0]}','${a[1]}','${a[2]}','${a[3]}','${a[4]}','${a[5]}','${a[6]}'],
				series: [
					[${e[0]}, ${e[1]}, ${e[2]}, ${e[3]}, ${e[4]}, ${e[5]},${e[6]}],
				]
		};
		
		new Chartist.Bar('#bar4', data, options);

		
		// line chart
		/* options = {
			height: "300px",
			showPoint: true,
			axisX: {
				showGrid: false
			},
			lineSmooth: false,
		};

		new Chartist.Line('#lineAccount', data, options);
		new Chartist.Line('#lineAccount2', data, options); */
	});
	</script>