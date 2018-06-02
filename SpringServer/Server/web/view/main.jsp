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
                           "series" :data2
                        }
                     });
      }

      function effiChart(data) {
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
            series : data
         });
      }
      function distChart(data1, data2) {
         

         var ranges = data1, 
         averages = data2;

         Highcharts.chart('distChart', {

            title : {
               text : 'DistChart'
            },

            xAxis : {
               type : 'linear'
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
               margin : "30 0 0 0"
            },
            series : [{"backgroundColor":"#FF5656","values":[10],"text":"SUN"},
               {"backgroundColor":"#ff9933","values":[12],"text":"MON"},
               {"backgroundColor":"#ffcc00","values":[14],"text":"TUE"},
               {"backgroundColor":"#00cc44","values":[15],"text":"WED"},
               {"backgroundColor":"#50ADF5","values":[9],"text":"THU"},
               {"backgroundColor":"#000066","values":[20],"text":"FRI"},
               {"backgroundColor":"#660066","values":[5],"text":"SAT"}]


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
            success : function(donutData) {

               donut(donutData);
            },
            error : function() {
               alert('donut fail1');
            }
         });
         $.ajax({
            url : 'effi.do?id=1001',
            success : function(data) {

               alert(data);
               effiChart(data);
            },
            error : function() {
               alert('effi fail1');
            }
         });
          $.ajax({
            url : 'dist1.do?id=1001',
            success : function(data1) {
               $.ajax({
                  url : 'dist2.do?id=1001',
                  success : function(data2) {
                     distChart(data1, data2);
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
         /*$.ajax({
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