<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<script src="https://cdn.zingchart.com/zingchart.min.js"></script>
<script>
	zingchart.MODULESDIR = "https://cdn.zingchart.com/modules/";
	ZC.LICENSE = [ "569d52cefae586f634c54f86dc99e6a9",
			"ee6b7db5b51705a13dc2339db3edaf6d" ];
</script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/highcharts-more.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/drilldown.js"></script>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>Landing Page - Start Bootstrap Theme</title>

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom fonts for this template -->
<link href="vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="vendor/simple-line-icons/css/simple-line-icons.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">

<!-- Custom styles for this template -->
<link href="css/landing-page.min.css" rel="stylesheet">

<link rel="stylesheet" href="Nwagon.css" type="text/css">
<script src="Nwagon.js"></script>
<style>
html, body {
	height: 100%;
}

.section-size {
	width: 100%;
	height: 95.5%;
	overflow-y: -webkit-paged;
}

div.dashBoard {
	background-color: white;
	padding-top: 1rem;
	padding-left: 1rem;
	padding-right: 1rem;
	padding-bottom: 0.5rem;
	height: 100%;
}

div.dash-upper {
	margin: 0.5%;
	padding: 2%;
	height: 59.5%;
	background-color: #f8f9fa !important;
	dispaly: flex;
}

div.dash-below {
	margin: 0.5%;
	padding: 2%;
	height: 40%;
	background-color: #f8f9fa !important;
	height: 39.5%;
}

.float_right {
	float: right;
}

.float_left {
	float: left;
}

div.inner-left {
	width: 49%;
	height: 100%;
}

div.inner-right {
	width: 48%;
	height: 100%;
}

div.infoDiv {
	width: 100%;
	margin-bottom: 2%;
	padding: 1%;
}

div.zc-ref {
	display: none;
}

div.chartDiv {
	margin: 0.5%;
	float: left;
	width: 35rem;
	height: 95%;
	padding-top: 0.6%;
	padding-left: 1%;
	padding-right: 1%;
	background-color: #f8f9fa;
}

img.fullSize {
	width: 100%;
	height: 100%;
}

#overlay {
	position: fixed;
	display: none;
	width: 100%;
	height: 100%;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background-color: rgba(0, 0, 0, 0.5);
	z-index: 2;
	cursor: pointer;
	padding: 10%;
}

#text {
	position: absolute;
	top: 50%;
	left: 50%;
	font-size: 50px;
	color: white;
	transform: translate(-50%, -50%);
	-ms-transform: translate(-50%, -50%);
}
</style>
</head>

<body>

	<!-- Navigation -->
	<nav class="navbar navbar-light bg-light static-top">
		<div class="container">
			<a class="navbar-brand" href="#">Main Title</a>
			<div>
				<a class="btn btn-primary" href="#" onclick="on()">User List</a> <a
					class="btn btn-primary" href="main.do">Sign In</a>
			</div>
		</div>
	</nav>

	<div id="overlay" onclick="off()">
		<div
			style="width: 100%; height: 80%; background-color: #f8f9fa; padding: 5%;">
			<div class="w3-container">
				<h2>User List</h2>
				<ul class="w3-ul">
					<li class="w3-display-container w3-green w3-hover-red">USER1</li>
					<li class="w3-green w3-hover-red">USER2</li>
					<li class="w3-green w3-hover-red">USER3</li>
					<li class="w3-grey w3-hover-red">USER1</li>
					<li class="w3-grey w3-hover-red">USER2</li>
					<li class="w3-grey w3-hover-red">USER3</li>
					<!-- .do로 요청보냈다가 main return하면 될듯  -->
				</ul>
			</div>
		</div>
	</div>

	<script>
		function on() {
			document.getElementById("overlay").style.display = "block";
		}

		function off() {
			document.getElementById("overlay").style.display = "none";
		}
	</script>


	<!-- Icons Grid -->
	<section class="bg-light text-center section-size">
		<div class="dashBoard">

			<div class="dash-upper">
				<div class="inner-left float_left">
					<div class="infoDiv" style="height: 39%; background-color: white;">개인정보</div>
					<div class="infoDiv"
						style="width: 100%; height: 59%; background-color: white; padding-top: 10%; padding-bottom: 20%;">
						<jsp:include page="/charts/hexaChart.jsp"></jsp:include>
						<div style="margin: 5%"></div>
						<h1>345/13,042</h1>
					</div>
				</div>
				<div class="inner-right float_right">
					<div class="infoDiv" style="height: 28%; background-color: white;">
						<div style="background-color: #3f51b5; height: 5%"></div>
						<div style="height: 80%; padding-top: 5%;">
							<div>
								<img style="width: 100%; height: auto;" src="img/carImg.png" />
							</div>
						</div>
						<div style="height: 10%; padding-right: 5%"></div>
						<!-- 
					<div style="background-color: #3f51b5; height: 5%; padding-top: 1%;">
							<div style="padding:5%; height:80%;"><img style="width: 100%; height: auto;" src="img/carImg.png"/></div>
							<div style="height:50%; background-color:black;"></div>
						</div> -->

					</div>
					<div class="infoDiv" style="height: 41%; background-color: white;">
						<div id='donutChart'></div>
					</div>
					<div class="infoDiv" style="height: 28%; background-color: white;">
						<jsp:include page="/charts/effiVisual.jsp"></jsp:include>
					</div>
				</div>
			</div>

			<div class="dash-below">
				<div
					style="width: 100%; height: 100%; background-color: white; overflow-x: overlay;">
					<div style="width: 108.5rem; height: 100%; overflow: hidden">
						<div class="chartDiv">
							<div id="distChart"></div>
						</div>
						<div class="chartDiv">

							<div id="scoreChart"></div>
						</div>
						<div class="chartDiv">
							<div id="effiChart"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>




	<script>
		function scoreChart() {
			// Create the chart
			Highcharts
					.chart(
							'scoreChart',
							{
								chart : {
									type : 'column'
								},
								title : {
									text : 'SCORE CHART'
								},
								subtitle : {
									text : 'Click the columns to view versions. Source: <a href="http://statcounter.com" target="_blank">statcounter.com</a>'
								},
								xAxis : {
									type : 'category'
								},
								yAxis : {
									title : {
										text : 'Total percent market share'
									}

								},
								legend : {
									enabled : false
								},
								plotOptions : {
									series : {
										borderWidth : 0,
										dataLabels : {
											enabled : true,
											format : '{point.y:.1f}%'
										}
									}
								},

								tooltip : {
									headerFormat : '<span style="font-size:11px">{series.name}</span><br>',
									pointFormat : '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
								},

								colors : [ "#a1abdd" ],

								"series" : [ {
									"name" : "Browsers",
									"colorByPoint" : true,
									"data" : [ {
										"name" : "Firefox",
										"y" : 10.57,
										"drilldown" : "Firefox"
									}, {
										"name" : "Internet Explorer",
										"y" : 7.23,
										"drilldown" : "Internet Explorer"
									}, {
										"name" : "Safari",
										"y" : 5.58,
										"drilldown" : "Safari"
									}, {
										"name" : "Edge",
										"y" : 4.02,
										"drilldown" : "Edge"
									}, {
										"name" : "Opera",
										"y" : 1.92,
										"drilldown" : "Opera"
									}, {
										"name" : "Other",
										"y" : 7.62,
										"drilldown" : null
									} ]
								} ],
								"drilldown" : {
									"series" : [
											{
												"name" : "Firefox",
												"id" : "Firefox",
												"data" : [ [ "v58.0", 1.02 ],
														[ "v57.0", 7.36 ],
														[ "v56.0", 0.35 ],
														[ "v55.0", 0.11 ],
														[ "v54.0", 0.1 ],
														[ "v52.0", 0.95 ],
														[ "v51.0", 0.15 ],
														[ "v50.0", 0.1 ],
														[ "v48.0", 0.31 ],
														[ "v47.0", 0.12 ] ]
											},
											{
												"name" : "Internet Explorer",
												"id" : "Internet Explorer",
												"data" : [ [ "v11.0", 6.2 ],
														[ "v10.0", 0.29 ],
														[ "v9.0", 0.27 ],
														[ "v8.0", 0.47 ] ]
											},
											{
												"name" : "Safari",
												"id" : "Safari",
												"data" : [ [ "v11.0", 3.39 ],
														[ "v10.1", 0.96 ],
														[ "v10.0", 0.36 ],
														[ "v9.1", 0.54 ],
														[ "v9.0", 0.13 ],
														[ "v5.1", 0.2 ] ]
											},
											{
												"name" : "Edge",
												"id" : "Edge",
												"data" : [ [ "v16", 2.6 ],
														[ "v15", 0.92 ],
														[ "v14", 0.4 ],
														[ "v13", 0.1 ] ]
											},
											{
												"name" : "Opera",
												"id" : "Opera",
												"data" : [ [ "v50.0", 0.96 ],
														[ "v49.0", 0.82 ],
														[ "v12.1", 0.14 ] ]
											} ]
								}
							});
		}

		function effiChart() {
			Highcharts.chart('effiChart', {
				chart : {
					type : 'areaspline'
				},
				title : {
					text : 'EffiChart'
				},
				legend : {
					layout : 'vertical',
					align : 'left',
					verticalAlign : 'top',
					x : 150,
					y : 100,
					floating : true,
					borderWidth : 1,
				/* backgroundColor : (Highcharts.theme && Highcharts.theme.legendBackgroundColor)
						|| '#FFFFFF' */
				},
				xAxis : {
					categories : [ 'Monday', 'Tuesday', 'Wednesday',
							'Thursday', 'Friday', 'Saturday', 'Sunday' ],
					plotBands : [ { // visualize the weekend
						from : 4.5,
						to : 6.5,
					/* color : 'rgba(68, 170, 213, .2)' */
					} ]
				},
				yAxis : {
					title : {
						text : 'Fruit units'
					}
				},
				tooltip : {
					shared : true,
					valueSuffix : ' units'
				},
				credits : {
					enabled : false
				},
				plotOptions : {
					areaspline : {
						fillOpacity : 0.5
					}
				},

				colors : [ "#3f51b5", "#737373", "#ffffff" ],
				series : [ {
					name : 'John',
					data : [ 3, 4, 3, 5, 4, 10, 12 ]
				}, {
					name : 'Jane',
					data : [ 1, 3, 4, 3, 3, 5, 4 ]
				} ]
			});
		}
		function distChart() {

			var ranges = [ [ 1246406400000, 14.3, 27.7 ],
					[ 1246492800000, 14.5, 27.8 ],
					[ 1246579200000, 15.5, 29.6 ],
					[ 1246665600000, 16.7, 30.7 ],
					[ 1246752000000, 16.5, 25.0 ],
					[ 1246838400000, 17.8, 25.7 ],
					[ 1246924800000, 13.5, 24.8 ],
					[ 1247011200000, 10.5, 21.4 ],
					[ 1247097600000, 9.2, 23.8 ],
					[ 1247184000000, 11.6, 21.8 ],
					[ 1247270400000, 10.7, 23.7 ],
					[ 1247356800000, 11.0, 23.3 ],
					[ 1247443200000, 11.6, 23.7 ],
					[ 1247529600000, 11.8, 20.7 ],
					[ 1247616000000, 12.6, 22.4 ],
					[ 1247702400000, 13.6, 19.6 ],
					[ 1247788800000, 11.4, 22.6 ],
					[ 1247875200000, 13.2, 25.0 ],
					[ 1247961600000, 14.2, 21.6 ],
					[ 1248048000000, 13.1, 17.1 ],
					[ 1248134400000, 12.2, 15.5 ],
					[ 1248220800000, 12.0, 20.8 ],
					[ 1248307200000, 12.0, 17.1 ],
					[ 1248393600000, 12.7, 18.3 ],
					[ 1248480000000, 12.4, 19.4 ],
					[ 1248566400000, 12.6, 19.9 ],
					[ 1248652800000, 11.9, 20.2 ],
					[ 1248739200000, 11.0, 19.3 ],
					[ 1248825600000, 10.8, 17.8 ],
					[ 1248912000000, 11.8, 18.5 ],
					[ 1248998400000, 10.8, 16.1 ] ], 
			averages = [
					[ 1246406400000, 21.5 ], [ 1246492800000, 22.1 ],
					[ 1246579200000, 23 ], [ 1246665600000, 23.8 ],
					[ 1246752000000, 21.4 ], [ 1246838400000, 21.3 ],
					[ 1246924800000, 18.3 ], [ 1247011200000, 15.4 ],
					[ 1247097600000, 16.4 ], [ 1247184000000, 17.7 ],
					[ 1247270400000, 17.5 ], [ 1247356800000, 17.6 ],
					[ 1247443200000, 17.7 ], [ 1247529600000, 16.8 ],
					[ 1247616000000, 17.7 ], [ 1247702400000, 16.3 ],
					[ 1247788800000, 17.8 ], [ 1247875200000, 18.1 ],
					[ 1247961600000, 17.2 ], [ 1248048000000, 14.4 ],
					[ 1248134400000, 13.7 ], [ 1248220800000, 15.7 ],
					[ 1248307200000, 14.6 ], [ 1248393600000, 15.3 ],
					[ 1248480000000, 15.3 ], [ 1248566400000, 15.8 ],
					[ 1248652800000, 15.2 ], [ 1248739200000, 14.8 ],
					[ 1248825600000, 14.4 ], [ 1248912000000, 15 ],
					[ 1248998400000, 13.6 ] 
					
					];

			Highcharts.chart('distChart', {

				title : {
					text : 'DistChart'
				},

				xAxis : {
					type : 'datetime'
				},

				yAxis : {
					title : {
						text : null
					}
				},

				tooltip : {
					crosshairs : true,
					shared : true,
					valueSuffix : '°C'
				},

				legend : {},

				series : [ {
					name : 'Temperature',
					color : "#3f51b5",
					data : averages,
					zIndex : 1,
					marker : {
						fillColor : 'white',
						lineWidth : 2,
						lineColor : "#3f51b5"
					}
				}, {
					name : 'Range',

					data : ranges,
					type : 'arearange',
					lineWidth : 0,
					linkedTo : ':previous',
					color : "#a1abdd",
					fillOpacity : 0.3,
					zIndex : 0,
					marker : {
						enabled : false
					}
				} ]
			});
		}
		
		
		function donut(data) {

			var myConfig = {
				type : "pie",
				backgroundColor : "#ffffff",
				source : {
					fontColor : "#8e99a9",
					fontFamily : "Open Sans"
				},
				title : {
					fontColor : "#fff",
					text : 'Global Browser Usage',
					align : "left",
					offsetX : 10,
					fontFamily : "Open Sans",
					fontSize : 25
				},
				plotarea : {
					margin : "20 0 0 0"
				},
				series : data
			};

			zingchart.render({
				id : 'donutChart',
				data : myConfig,
				height : 250,
				width : 360
			});
			
			
		}
		
		$(document).ready(function func() {
			$.ajax({
				url : 'donut.do?id=1001',
				success : function(data) {
					donut(data);
				},
				error : function() {
					alert('data1 fail');
				}
			});
			/* $.ajax({
				url : 'effichart.do?id=1001',
				success : function(data) {
					effiChart(data);
				},
				error : function() {
					alert('data1 fail');
				}
			});
			$.ajax({
				url : 'distchart.do?id=1001',
				success : function(data) {
					distChart(data);
				},
				error : function() {
					alert('data1 fail');
				}
			});
			$.ajax({
				url : 'scorechart.do?id=1001',
				success : function(data) {
					scoreChart(data);
				},
				error : function() {
					alert('data1 fail');
				}
			}); */
			effiChart();
			scoreChart();
			distChart();
		});

	</script>
	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>

</html>
