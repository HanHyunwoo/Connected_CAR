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
					<div class="infoDiv"
						style="height: 39%; background-color: white; padding: 2%">
						<div
							style="height: 100%; width: 100%; padding: 2%; display: flex;">
							<div style="height: 100%; width: 49%;">
								<div
									style="background-color: #3f51b5; height: 10%; width: 100%; vertical-align: center; color: white; padding-top: 1%; margin-bottom: 8%">
									<h5>USER INFO
								</div>
								<div
									style="background-color: white; height: 82%; width: auto; overflow: hidden">
									<img src="img/profile.png"
										style="height: 100%; width: auto; transform: translate(-6.8%);" />
								</div>
							</div>

							<div style="height: 100%; width: 2%;"></div>
							<div style="height: 100%; width: 49%; padding-top: 14%;">
								<div style="height: 7%;"></div>
								<div
									style="height: 21%; width: 100%; text-align: right; padding-top: 10%; padding-right: 3%; text-size: 10px">
									<div style="float: right;">Gangdol Kim</div>
									<div style="font-size: 10px; float: right; color: #b3b3b3;">Name
										&nbsp;</div>
								</div>
								<div style="background-color: black; height: 1%; width: 100%;"></div>
								<div
									style="height: 21%; width: 100%; text-align: right; padding-top: 10%; padding-right: 3%; text-size: 10px;">
									<div style="float: right;">1001</div>
									<div style="font-size: 10px; float: right; color: #b3b3b3;">CarID
										&nbsp;</div>

								</div>
								<div style="background-color: black; height: 1%; width: 100%;"></div>
								<div
									style="height: 21%; width: 100%; text-align: right; padding-top: 10%; padding-right: 3%; text-size: 10px">
									<div style="float: right;">ASRADA</div>
									<div style="font-size: 10px; float: right; color: #b3b3b3;">Model
										&nbsp;</div>
								</div>
								<div style="background-color: black; height: 1%; width: 100%;"></div>
								<div
									style="height: 21%; width: 100%; text-align: right; padding-top: 10%; padding-right: 3%; text-size: 10px">
									<div style="float: right;">July 27, 2018</div>
									<div style="font-size: 10px; float: right; color: #b3b3b3;">RegDate
										&nbsp;</div>
								</div>
								<div style="background-color: black; height: 1%; width: 100%;"></div>
							</div>

						</div>
					</div>
					<div class="infoDiv"
						style="width: 100%; height: 59%; background-color: white; padding-top: 4%; color: #404040;">
						<h2>DRIVING SCORE</h2>
						<jsp:include page="/charts/hexaChart.jsp"></jsp:include>
						<!-- <div style="margin: 2%;"></div> -->
						<h1 style="width: 100%; height: 15%;">345/13,042</h1>
					</div>
				</div>
				<div class="inner-right float_right">
					<div class="infoDiv" style="height: 23%; background-color: white;">
						<div style="background-color: #3f51b5; height: 5%"></div>
						<div style="height: 80%; padding-top: 5%;">
							<div style="position: relative; z-index: 1;">
								<img id="wifi" style="width: 100%; height: auto%;" src="img/wifioff.png" />
							</div>
						</div>	
					</div>
					<div class="infoDiv" style="height: 44%; background-color: white;">
						<div id='donutChart'></div>
					</div>
					<div class="infoDiv" style="height: 30%; background-color: white;">
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
		function scoreChart(data1, data2) {
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
											enabled : true
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
									"data" : data1
								} ],
								"drilldown" : {
									"series" : data2
								}
							});
		}
		function effiChart(date, data) {
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
					categories : date,
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
				series : data
			});
		}
		function distChart(rangeFromController, dataFromController) {
			var ranges = rangeFromController, averages = dataFromController;
			Highcharts.chart('distChart', {
				title : {
					text : 'DistChart'
				},
			    xAxis: {
			        type: 'datetime'
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
		function donut(donutData) {
			var myConfig = {
				 	type: "pie", 
				 	backgroundColor: "#ffffff",
				 	plot: {
				 	  borderColor: "#f8f9fa",
				 	  borderWidth: 3,
				 	  slice: 30,
				 	  valueBox: {
				 	    placement: 'in',
				 	    text: '%t %npv',
				 	    fontFamily: "Open Sans"
				 	  },
				 	  tooltip:{
				 	    fontSize: '18',
				 	    fontFamily: "Open Sans",
				 	    padding: "5 10",
				 	    text: "%npv"
				 	  },
				 	  animation:{
				      effect: 2, 
				      method: 5,
				      speed: 500,
				      sequence: 1
				    }
				 	},
				 	title: {
				 	  fontColor: "#404040",
				 	  text: "Score Each Date",
				 	  align: "left",
				 	  offsetX: 90,
				 	  fontFamily: "Open Sans",
				 	  fontSize: 21
				 	},
				 	plotarea: {
				 	  margin: "33 0 0 0"  
				 	},
					series :[ {
						"backgroundColor" : "#FF5656",
						"values" : [ 90 ],
						"text" : "SUN"
					}, {
						"backgroundColor" : "#ff9933",
						"values" : [ 82 ],
						"text" : "MON"
					}, {
						"backgroundColor" : "#ffcc00",
						"values" : [ 86 ],
						"text" : "TUE"
					}, {
						"backgroundColor" : "#00cc44",
						"values" : [ 85 ],
						"text" : "WED"
					}, {
						"backgroundColor" : "#50ADF5",
						"values" : [ 91 ],
						"text" : "THU"
					}, {
						"backgroundColor" : "#000066",
						"values" : [ 80 ],
						"text" : "FRI"
					}, {
						"backgroundColor" : "#660066",
						"values" : [ 95 ],
						"text" : "SAT"
					} ]
				};

				zingchart.render({ 
					id : 'donutChart', 
					data : myConfig, 
					height: 260, 
					width: 350 
				});
		}
		$(document).ready(function func() {
			$.ajax({
				url : 'donut.do?id=1001',
				success : function(donutData) {
					donut(donutData);
				},
				error : function() {
					alert('donut fail1');
				}
			});
			$.ajax({
				url : 'effi1.do?id=1001',
				success : function(date) {
					$.ajax({
						url : 'effi2.do?id=1001',
						success : function(data) {
							effiChart(date, data);
						},
						error : function() {
							alert('effi2 fail1');
						}
					});
				},
				error : function() {
					alert('effi1 fail1');
				}
			});
			$.ajax({
				url : 'dist1.do',
				success : function(range) {
					$.ajax({
						url : 'dist2.do?id=1001',
						success : function(data) {
							distChart(range, data);
						},
						error : function() {
							alert('dist2 fail');
						}
					});
				},
				error : function() {
					alert('dist1 fail');
				}
			});
			$.ajax({
				url : 'score1.do?id=1001',
				success : function(data1) {
					$.ajax({
						url : 'score2.do?id=1001',
						success : function(data2) {
							scoreChart(data1, data2);
						},
						error : function() {
							alert('score2 fail');
						}
					});
				},
				error : function() {
					alert('score1 fail');
				}
			});
			checkWifiState();
		});
		
		
		 
	      
        var worker;

        function checkWifiState() {

            if(worker) {                  
                worker.terminate(); 
            };

           
            worker = new Worker("wifiState.js");

            worker.onmessage = function(evt) {
				if(evt.data == 1){
			    	document.getElementById("wifi").src = "img/wifion.png";
				}
				else{
			    	document.getElementById("wifi").src = "img/wifioff.png";					
				}
            };  
 
            worker.onerror=function(evt) {
                alert("Error : On Worker");
            };

            worker.postMessage(1); //postMessage로 jsp와 js간에 dataㄹ 주고 받는다.

        }

        
	</script>
	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>

</html>
