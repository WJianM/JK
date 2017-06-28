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
        <script src="${pageContext.request.contextPath}/components/amcharts/serial.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/components/amcharts/themes/light.js"" type="text/javascript"></script>
       <script>
       var chart = AmCharts.makeChart("chartdiv", {
                "type": "serial",
                "theme": "dark",
                "dataDateFormat": "HH",

                "dataProvider": [{
                    "date": "14",
                    "value": 104
                }, {
                    "date": "16",
                    "value": 108
                }],
                "valueAxes": [{
                    "maximum": 140,
                    "minimum": 100,
                    "axisAlpha": 0,
                    "guides": [{
                        "fillAlpha": 0.1,
                        "fillColor": "#CC0000",
                        "lineAlpha": 0,
                        "toValue": 120,
                        "value": 0
                    }, {
                        "fillAlpha": 0.1,
                        "fillColor": "#0000cc",
                        "lineAlpha": 0,
                        "toValue": 200,
                        "value": 120
                    }]
                }],
                "graphs": [{
                    "bullet": "round",
                    "dashLength": 4,
                    "valueField": "value"
                }],
                "chartCursor": {
                    "cursorAlpha": 0
                },
                "categoryField": "date",
                "categoryAxis": {
                    "parseDates": true
                }
            };
        </script>
    </head>

    <body style="background-color:#3f3f4f;">
        <div id="chartdiv" style="width: 100%; height: 400px;"></div>
    </body>

</html>