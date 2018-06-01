<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE >
<html>
<head>
<title>Insert title here</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
</head>
<body>
<h2>effiChart.jsp</h2>
	<div id="effiChart"	style="min-width: 310px; height: 400px; margin: 0 auto"></div>
	<script>
		function chart() {
			Highcharts
					.chart(
							'effiChart',
							{
								chart : {
									type : 'areaspline'
								},
								title : {
									text : 'Average fruit consumption during one week'
								},
								legend : {
									layout : 'vertical',
									align : 'left',
									verticalAlign : 'top',
									x : 150,
									y : 100,
									floating : true,
									borderWidth : 1,
									backgroundColor : (Highcharts.theme && Highcharts.theme.legendBackgroundColor)
											|| '#FFFFFF'
								},
								xAxis : {
									categories : [ 'Monday', 'Tuesday',
											'Wednesday', 'Thursday', 'Friday',
											'Saturday', 'Sunday' ],
									plotBands : [ { // visualize the weekend
										from : 4.5,
										to : 6.5,
										color : 'rgba(68, 170, 213, .2)'
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
								series : [ {
									name : 'John',
									data : [ 3, 4, 3, 5, 4, 10, 12 ]
								}, {
									name : 'Jane',
									data : [ 1, 3, 4, 3, 3, 5, 4 ]
								} ]
							});
		}
		$(document).ready(function func(){
			 $.ajax({ url:'hexa.do?carid=1001',
			      success:function(data){ 			    	 
			    	  chart();
			      },
			      error:function(){
			         alert('data1 fail');
			      }
				});
			
		});
	</script>
	
</body>
</html>