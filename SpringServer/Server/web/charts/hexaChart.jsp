<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%> 

<!DOCTYPE >
<html>
<head>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="Nwagon.css" type="text/css">
<script src="Nwagon.js"></script>
<title>Insert title here</title>
<style>
</style>
</head>
<body>
	<div style="width:400;" id="hexa"></div>
	<script>
	function displayHexa(data) {
		var options = {
				'legend':{
					names: [
						'�ް���',
						'�����',
						'����ġ',
						'�ް���',
						'��������',
						'�����Ÿ�Ȯ��'
					]
				},
				'dataset': {
					title: 'Web accessibility status',
					values: data,
					bgColor: '#e6e6e6',
					fgColor: '#5566c3',
					opacity:[1]
				},
				'chartDiv': 'hexa',
				'chartType': 'radar',
				'chartSize': { width: 300, height: 240 }
			};
			Nwagon.chart(options);
	
	}
	$(document).ready(function func(){
		  $.ajax({ url:'hexa.do?id=1001',
		      success:function(data){      	 
		    	  displayHexa(data); 
		      },
		      error:function(){
		         alert('data1 fail');
		      }
			});		 
	});
	</script>
</body>
</html>