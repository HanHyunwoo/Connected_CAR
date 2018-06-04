<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
	<style>.zc-ref {
  display: none;
}</style>
		<script src= "https://cdn.zingchart.com/zingchart.min.js"></script>
		<script> zingchart.MODULESDIR = "https://cdn.zingchart.com/modules/";
		ZC.LICENSE = ["569d52cefae586f634c54f86dc99e6a9","ee6b7db5b51705a13dc2339db3edaf6d"];</script></head>
	<body>
		<div id='myChart'><a class="zc-ref" href="https://www.zingchart.com/">Charts by ZingChart</a></div>
		
		
		<script>var myConfig = {
			 	type: "pie", 
			 	backgroundColor: "#ffffff",
			 	
			 	source: {
			 	  text: 'gsasdfm',
			 	  fontColor: "#8e99a9",
			 	  fontFamily: "Open Sans"
			 	},
			 	title: {
			 	  fontColor: "#fff",
			 	  text: 'Global Browser Usage',
			 	  align: "left",
			 	  offsetX: 10,
			 	  fontFamily: "Open Sans",
			 	  fontSize: 25
			 	},
			 	plotarea: {
			 	  margin: "20 0 0 0"  
			 	},
				series : [
					{
						values : [11.38],
						text: "Internet Explorer",
					  backgroundColor: '#50ADF5',
					},
					{
					  values: [56.94],
					  text: "Chrome",
					  backgroundColor: '#FF7965'
					},
					{
					  values: [14.52],
					  text: 'Firefox',
					  backgroundColor: '#FFCB45'
					},
					{
					  text: 'Safari',
					  values: [9.69],
					  backgroundColor: '#6877e5'
					},
					{
					  text: 'Other',
					  values: [7.48],
					  backgroundColor: '#6FB07F'
					}
				]
			};

			zingchart.render({ 
				id : 'myChart', 
				data : myConfig, 
				height : 230,
				width : 350
			});</script>
	</body>
</html>