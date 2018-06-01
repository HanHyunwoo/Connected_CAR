<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<style>
#main_center {
	margin: 0 20px;
	width: 760px;
	height: 480px;
	background: white;
}
</style>

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/highcharts-more.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>

<script>
	function display(input) {
		Highcharts.chart('container',{
							chart : {
								polar : true,
								type : 'line'
							},		

							title: {
							        text: '',
							        x: -100
							},
							
							pane : {
								size : '50%'
							},

							xAxis : {
								categories : [ '급출발', '급감속',
										'급정지', '급가속',
										'미확보',
										'졸음운전' ],
								tickmarkPlacement : 'on',
								lineWidth : 0
							},

							yAxis : {
								gridLineInterpolation : 'polygon',
								lineWidth : 0,
								min : 0
							},
							
							series : [
									{
										data : input,
										pointPlacement : 'on'
									}
								 ]
							});
	}
	
	$(document).ready(function(){
		// Server에 데이터를 요청한다.
		// AJAX로
		/* $.ajax({
			url:'chart1impl.do',
			success:function(data){
				alert(data);
				display(data);
			},
			error:function(){
				alert('fail');
			}
		});
		 */
		var datas = [43000,19000,60000,35000,17000,10000];
		display(datas);
	});
</script>

<div id="main_center">
	<div id="container"	style="min-width: 250px; height: 400px; margin: 0 auto"></div>
</div>
