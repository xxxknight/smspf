<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>統計視圖</title>
		<script type="text/javascript" src="<%=path%>/resources/js/jQuery/jquery-1.9.1.min.js"></script>
		<style type="text/css">
			${demo.css}
		</style>
		<script type="text/javascript">
	$(function() {
		$('#container').highcharts(
				{
					data : {
						table : document.getElementById('datatable')
					},
					chart : {
						type : 'column'
					},
					title : {
						text : 'Data extracted from a HTML table in the page'
					},
					yAxis : {
						allowDecimals : false,
						title : {
							text : 'Units'
						}
					},
					 tooltip: {
            formatter: function() {
                return '<b>'+ this.series.name +'</b><br/>'+
                    this.point.y +' '+ this.point.name.toLowerCase();
            }
        }
   });
 });
 </script>
	</head>
	<body>
		<script src="<%=path%>/resources/js/Chart/highcharts.js"></script>
		<script src="<%=path%>/resources/js/Chart/modules/data.js"></script>
		<script src="<%=path%>/resources/js/Chart/modules/exporting.js"></script>
		<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
		<table id="datatable">
			<thead>
				<tr>
					<th></th>
					<th>
						Jane
					</th>
					<th>
						John
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>
						Apples
					</th>
					<td>
						3
					</td>
					<td>
						4
					</td>
				</tr>
				<tr>
					<th>
						Pears
					</th>
					<td>
						2
					</td>
					<td>
						0
					</td>
				</tr>
				<tr>
					<th>
						Plums
					</th>
					<td>
						5
					</td>
					<td>
						11
					</td>
				</tr>
				<tr>
					<th>
						Bananas
					</th>
					<td>
						1
					</td>
					<td>
						1
					</td>
				</tr>
				<tr>
					<th>
						Oranges
					</th>
					<td>
						2
					</td>
					<td>
						4
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>
