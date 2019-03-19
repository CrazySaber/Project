<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>${keyword}的工资区间分析图</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/echarts/echarts.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/echarts/macarons.js"></script>

<link
	href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/htmleaf-demo.css">
</head>

<body>
	<div id="echartsBarMon" style="width: 1350px;height:450px;left: 250px"></div>
	<span
		style="text-align: center;font-size: 18px;display:block;font-weight: bold;">工资与岗位关系图</span>
	<hr>
	
	<div class="col-sm-8 col-sm-offset-2">
		<h3>
			<code>岗位详细表</code>
		</h3>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>职位</th>
					<th>公司名</th>
					<th>工作地点</th>
					<th>工资</th>
					<th>部分要求<th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${jobS.list != null}">
				<c:forEach var="job" items="${jobS.list}">
					<tr>
						<td>${job.position}</td>
						<td>${job.company}</td>
						<td>${job.compensation}</td>
						<td>${job.wages}</td>
						<td>${job.info}<td>
					</tr>
				</c:forEach>
			</c:if>
			</tbody>
		</table>
	</div>

	<script src="http://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"
		type="text/javascript"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="js/jquery-1.11.0.min.js"><\/script>')
	</script>
	<script type="text/javascript" src="js/paginathing.js"></script>
	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
		var barMonChart = echarts.init(
				document.getElementById('echartsBarMon'), 'macarons');
		// 指定图表的配置项和数据
		optionBarMon = {
			title : {
				text : '${keyword}岗位数',
				left : 'left',
				top : 20,
				textStyle : {
					color : 'black'
				}
			},
			tooltip : {
				trigger : 'axis',
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
					type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			legend : {
				data : [ '数量' ]
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			yAxis : {
				type : 'value'
			},
			xAxis : {
				type : 'category',
				data : [ '2千以下', '2-3千', '3-4.5千', '4.5-6千', '6-8千', '0.8-1万',
						'1-1.5万', '1.5-2万', '2-3万', '3-4万', '4-5万', '5万以上' ]
			},
			series : [ {
				name : '数量',
				type : 'bar',
				stack : '总量',
				label : {
					normal : {
						show : true,
						position : 'insideRight'
					}
				},
				data : [ ${one}, ${two}, ${three}, ${four}, ${five}, ${six},
						${seven}, ${eight}, ${nine}, ${ten}, ${eleven},
						${twelve} ]
			} ]
		};
		// 使用刚指定的配置项和数据显示图表。
		barMonChart.setOption(optionBarMon);

		jQuery(document).ready(
				function($) {
					$('.table tbody').paginathing({
						perPage : 15,
						insertAfter : '.table',
						pageNumbers : true
					});
				});
	</script>
</body>
</html>
