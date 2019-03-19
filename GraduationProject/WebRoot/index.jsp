<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML >
<html>
<head>
<title>2018年51job网人才需求分析</title>
<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/default.css">
<script src='js/prefixfree.min.js'></script>
<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>

<body>
	<div id="logo">
		<h1 class="hogo">
			<i>职位查询</i>
		</h1>
	</div>
	<section class="stark-login">
		<form action="jobServlet" method="post">
			<div id="fade-box">
				<input type="text" name="keyword" id="keyword"
					placeholder="岗位名称" required> 
				<input type="text" name="compensation" id="compensation"
					placeholder="公司位置" >
				<input type="text" name="info" id="info"
					placeholder="相关要求" >
				<input type="text" name="wages" id="wages"
					placeholder="薪资" >
				<button>搜索</button>
			</div>
		</form>
		<div class="hexagons">
			<span>&#x2B22;</span> <span>&#x2B22;</span> <span>&#x2B22;</span> <span>&#x2B22;</span>
			<span>&#x2B22;</span> <span>&#x2B22;</span> <span>&#x2B22;</span> <span>&#x2B22;</span>
			<span>&#x2B22;</span> <span>&#x2B22;</span> <span>&#x2B22;</span> <span>&#x2B22;</span>
			<br> <span>&#x2B22;</span> <span>&#x2B22;</span> <span>&#x2B22;</span>
			<span>&#x2B22;</span> <span>&#x2B22;</span> <span>&#x2B22;</span> <span>&#x2B22;</span>
			<span>&#x2B22;</span> <span>&#x2B22;</span> <span>&#x2B22;</span> <span>&#x2B22;</span>
			<br> <span>&#x2B22;</span> <span>&#x2B22;</span> <span>&#x2B22;</span>
			<span>&#x2B22;</span> <span>&#x2B22;</span> <span>&#x2B22;</span> <span>&#x2B22;</span>
			<span>&#x2B22;</span> <span>&#x2B22;</span> <span>&#x2B22;</span> <span>&#x2B22;</span>
			<span>&#x2B22;</span> <br> <span>&#x2B22;</span> <span>&#x2B22;</span>
			<span>&#x2B22;</span> <span>&#x2B22;</span> <span>&#x2B22;</span> <span>&#x2B22;</span>
			<span>&#x2B22;</span> <span>&#x2B22;</span> <span>&#x2B22;</span> <span>&#x2B22;</span>
			<span>&#x2B22;</span> <br> <span>&#x2B22;</span> <span>&#x2B22;</span>
			<span>&#x2B22;</span> <span>&#x2B22;</span> <span>&#x2B22;</span> <span>&#x2B22;</span>
			<span>&#x2B22;</span> <span>&#x2B22;</span> <span>&#x2B22;</span> <span>&#x2B22;</span>
			<span>&#x2B22;</span> <span>&#x2B22;</span>
		</div>
	</section>

	<div id="circle1">
		<div id="inner-cirlce1">
			<h2></h2>
		</div>
	</div>
	<ul>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
	<header class="htmleaf-header">
		<h1>
			基于JAVA爬虫数据抓取的求职数据报表展示 <span></span>
		</h1>
	</header>
</body>
</html>
