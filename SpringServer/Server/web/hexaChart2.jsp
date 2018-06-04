<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<!DOCTYPE >
<html>
<head>

<link rel="stylesheet" href="Nwagon.css" type="text/css">
<script src="Nwagon.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="Nwagon.css" type="text/css">
<script src="Nwagon.js"></script>
<title>Insert title here</title>
<style>
body, html {
	width: 100%;
	height:100%;
	margin: 0 auto;
}
</style> 
</head>
<body>
	<div style="width: 100%; padding-left:27%;">
		<div style="width: 100%; height: 100%;" id="hexa2"></div>
	</div>
	<script>
		function displayHexa(data) {
			var options = {
				'legend' : {
					names : [ '�ް���', '�����', '������', '�ް���', '��������', '�����Ÿ�Ȯ��' ]
				},
				'dataset' : {
					title : 'Web accessibility status',
					values : data,
					bgColor : '#e6e6e6',
					fgColor : '#5566c3',
					opacity : [ 1 ]
				},
				'chartDiv' : 'hexa2',
				'chartType' : 'radar',
				'chartSize' : {
					width : 300,
					height : 300
				}
			};
			Nwagon.chart(options);

		}
		$(document).ready(function func() {
			$.ajax({
				url : 'ivihexa.do',
				success : function(data) {
					displayHexa(data);
				},
				error : function() {
					alert('data1 fail');
				}
			});
		});
	</script>
</body>
</html>