<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>amCharts examples</title>
        <script src="${pageContext.request.contextPath}/components/zTree/js/jquery-1.4.4.min.js"></script>
        <!-- <link rel="stylesheet" href="style.css" type="text/css"> -->
        <script src="${pageContext.request.contextPath}/components/amcharts/amcharts.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/components/amcharts/pie.js" type="text/javascript"></script>
        <script>
	        var chart;
	        var legend;
           $(function(){
        	   $.post("${pageContext.request.contextPath}/stat/statChartAction_getData",function(data){
        		  createPie(data);
        	   },"json");
           });
          

           function createPie(chartData){
            	
                chart = new AmCharts.AmPieChart();
                chart.dataProvider = chartData;
                chart.titleField ="FactoryName";
                chart.valueField = "amount";
                chart.outlineColor = "#FFFFFF";
                chart.outlineAlpha = 0.8;
                chart.outlineThickness = 2;
                chart.balloonText = "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>";
                chart.depth3D = 15;
                chart.angle = 30;

                // WRITE
                chart.write("chartdiv");
            };
        </script>
    </head>

    <body>
        <div id="chartdiv" style="width: 100%; height: 400px;"></div>
    </body>

</html>