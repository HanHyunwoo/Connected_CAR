<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>



<div id="highcharts-cd8655f2-bd30-43c7-8c4f-8347019d82b0"></div>
<script>
(function(){ var files = ["https://code.highcharts.com/stock/highstock.js","https://code.highcharts.com/highcharts-more.js","https://code.highcharts.com/highcharts-3d.js","https://code.highcharts.com/modules/data.js","https://code.highcharts.com/modules/exporting.js","https://code.highcharts.com/modules/funnel.js","https://code.highcharts.com/modules/annotations.js","https://code.highcharts.com/modules/solid-gauge.js"],loaded = 0; if (typeof window["HighchartsEditor"] === "undefined") {window.HighchartsEditor = {ondone: [cl],hasWrapped: false,hasLoaded: false};include(files[0]);} else {if (window.HighchartsEditor.hasLoaded) {cl();} else {window.HighchartsEditor.ondone.push(cl);}}function isScriptAlreadyIncluded(src){var scripts = document.getElementsByTagName("script");for (var i = 0; i < scripts.length; i++) {if (scripts[i].hasAttribute("src")) {if ((scripts[i].getAttribute("src") || "").indexOf(src) >= 0 || (scripts[i].getAttribute("src") === "http://code.highcharts.com/highcharts.js" && src === "https://code.highcharts.com/stock/highstock.js")) {return true;}}}return false;}function check() {if (loaded === files.length) {for (var i = 0; i < window.HighchartsEditor.ondone.length; i++) {try {window.HighchartsEditor.ondone[i]();} catch(e) {console.error(e);}}window.HighchartsEditor.hasLoaded = true;}}function include(script) {function next() {++loaded;if (loaded < files.length) {include(files[loaded]);}check();}if (isScriptAlreadyIncluded(script)) {return next();}var sc=document.createElement("script");sc.src = script;sc.type="text/javascript";sc.onload=function() { next(); };document.head.appendChild(sc);}function each(a, fn){if (typeof a.forEach !== "undefined"){a.forEach(fn);}else{for (var i = 0; i < a.length; i++){if (fn) {fn(a[i]);}}}}var inc = {},incl=[]; each(document.querySelectorAll("script"), function(t) {inc[t.src.substr(0, t.src.indexOf("?"))] = 1; }); function cl() {if(typeof window["Highcharts"] !== "undefined"){var options={"chart":{"type":"area","polar":true},"xAxis":[{"tickmarkPlacement":"on","lineWidth":0}],"yAxis":[{"lineWidth":0,"gridLineInterpolation":"polygon"}],"title":{"text":"My Chart"},"subtitle":{"text":"My Untitled Chart"},"exporting":{},"series":[{"name":"Column 2","turboThreshold":0,"_colorIndex":0,"_symbolIndex":0}],"plotOptions":{"series":{"animation":false}},"data":{"csv":"\"Column 1\";\"Column 2\"\n\"START\";70\n\"DECEL\";50\n\"STOP\";50\n\"ACCEL\";60\n\"SAFETYDIS\";90\n\"NOOZE\";80","googleSpreadsheetKey":false,"googleSpreadsheetWorksheet":false}};


// Sample of extending options:
Highcharts.merge(true, options, {
    chart: {
        backgroundColor: "#bada55"
    },
    plotOptions: {
        series: {
            cursor: "pointer",
            events: {
                click: function(event) {
                    alert(this.name + " clicked\n" +
                          "Alt: " + event.altKey + "\n" +
                          "Control: " + event.ctrlKey + "\n" +
                          "Shift: " + event.shiftKey + "\n");
                }
            }
        }
    }
});
new Highcharts.Chart("highcharts-cd8655f2-bd30-43c7-8c4f-8347019d82b0", options);}}})();
</script>