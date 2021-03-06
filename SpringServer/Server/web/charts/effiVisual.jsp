<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE >
<html>
<head>
<title>Insert title here</title>
<style>
.progress-bar {
	background-color: #ffffff;
	height: 32px;
	padding: 5px;
	width: 100%;
	border-radius: 1px;
	text-align:right;
}

.progress-bar span {
	display: inline-block;
	height: 100%;
	border-radius: 3px;
	transition: width .4s ease-in-out;
}

.grey span {
	background-color: #c6cceb;
	background-image: linear-gradient(top, #fecf23, #fd9215);
}

.red span {
	background-color: #FF5656;
	background-image: linear-gradient(top, #fecf23, #fd9215);
}

.blue span {
	background-color: #5566c3;
	background-image: linear-gradient(top, #a5df41, #4ca916);
}

.green span {
	background-color: #00cc44;
	background-image: linear-gradient(top, #a5df41, #4ca916);
}

stripes span {
	background-size: 30px 30px;
	background-image: linear-gradient(135deg, rgba(255, 255, 255, .15) 25%,
		transparent 25%, transparent 50%, rgba(255, 255, 255, .15) 50%,
		rgba(255, 255, 255, .15) 75%, transparent 75%, transparent);
	animation: animate-stripes 3s linear infinite;
}

@keyframes animate-stripes { 
0% {background-position: 0 0;}
100%{background-position:60px 0;}
}
.shine span {
	position: relative;
}

.shine span::after {
	content: '';
	opacity: 0;
	position: absolute;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	background: #fff;
	border-radius: 3px;
	animation: animate-shine 2s ease-out infinite;
}

@keyframes animate-shine {
0% {	opacity: 0;	width: 0;}
50%{opacity:.9;}
100%{opacity:0;width:95%;}
}
</style>
</head>
<body> 
	<div style="padding-top: 2%;">
		<h4 style="color:#404040;">BATTERY per a DRIVE</h4>
		<div style="width: 100%; height: 100%; padding: 1%; display: flex">
			<div style="width: 100%; height: 100%">
				<div class="progress-bar green shine">
					<span style="width: 90%; padding-right:4%;">MAX : 90%</span>
				</div>
				<div class="progress-bar blue shine" >
					<span style="width: 75%; padding-right:4%;">AVG : 75%</span>
				</div>
				<div class="progress-bar grey">
					<span style="width: 60%; padding-right:4%; color: #b3b3b3; text-align:center; padding-left:4%; font-weight: bold;">STANDARD : 60%</span>
				</div>
				<div class="progress-bar red shine">
					<span style="width: 50%; padding-right:4%;">MIN : 50%</span>
				</div>			
			</div>
		</div>
	</div>
</body>
</html>