<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  


	<!-- WRAPPER -->
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<h3 class="page-title">Charts</h3>
					<div class="row">
						<div class="col-md-6">
							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">누적</h3>
								</div>
								<div class="panel-body">
									<div id="demo-line-chart" class="ct-chart"></div>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">일별</h3>
								</div>
								<div class="panel-body">
									<div id="demo-bar-chart" class="ct-chart"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						
					</div>
				</div>
			</div>
			<!-- END MAIN CONTENT -->
		<!-- END MAIN -->
		<div class="clearfix"></div>

	<script>
	$(function() {
		var options;
		var listAccount = "${accountCount[6]}";
		alert(listAccount);
	
		var data = {
			labels: ['${a[0]}','${a[1]}','${a[2]}','${a[3]}','${a[4]}','${a[5]}','${a[6]}'],
			series: [
				[${b[0]}, ${b[1]}, ${b[2]}, ${b[3]}, ${b[4]}, ${b[5]},${b[6]}],
			]
		};
		
		// line chart
		options = {
			height: "300px",
			showPoint: true,
			axisX: {
				showGrid: false
			},
			lineSmooth: false,
		};

		new Chartist.Line('#demo-line-chart', data, options);

		// bar chart
		options = {
			height: "300px",
			axisX: {
				showGrid: false
			},
		};

		new Chartist.Bar('#demo-bar-chart', data, options);


	});
	</script>