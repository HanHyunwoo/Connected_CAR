<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE >
<html>
<head>
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
</head>
<body>
	<h2>effiChart.jsp</h2>
	<div id="effiChart"
		style="width: 100%; height: 100%; margin: 0 auto"></div>
	<script>
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
		$(document).ready(function func() {
			$.ajax({
				url : 'iviEffi1.do?id=1001',
				success : function(date) {
					$.ajax({
						url : 'iviEffi2.do?id=1001',
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

		});
	</script>

</body>
</html>