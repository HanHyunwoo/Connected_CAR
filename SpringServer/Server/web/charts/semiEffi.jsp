<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE >
<html>
<head>

<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="Nwagon.css" type="text/css">
<script src="Nwagon.js"></script>
<title>Insert title here</title>
</head>
<body>
<div id="Nwagon"></div>
<script>
	var options = {
		'dataset':{
			title: 'Web accessibility status',
			values:[22, 22 , 22, 33],
			colorset: ['#2EB400', '#2BC8C9', "#666666", '#ffffff'],
			fields: ['Asdf', 'B',  'C', 'D'],
		},
		'donut_width' : 35,
		'core_circle_radius':50,
		'chartDiv': 'Nwagon',
		'chartType': 'donut',
		'chartSize': {width:300, height:200}
	};
	Nwagon.chart(options);
</script>
</body>
</html>