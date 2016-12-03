<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>基础统计</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="./css/baseStatistics.css">
	<script type="text/javascript" src="./easyui/jquery.min.js"></script>
<script type="text/javascript" src="./easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="./easyui/locale/easyui-lang-zh_CN.js"></script>

<link rel="stylesheet" type="text/css"
	href="./easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="./easyui/themes/icon.css">

  </head>
  
  <body>
   		<div class="statistics_top_font">个人中心&gt;数据统计&gt;基础统计</div>
   		<div class="statistics_top_textBox">
   				<div class="startTimePosition">起始时间:<input type="text" name="startTime" class="startTime"></div>
   				<div class="endTimePosition">截止时间:<input type="text" name="endTime" class="endTime"></div>
   		</div>
		<div>
			<table class="top_header">
				<tr>
					<td>公司名称(份)</td>
					<td>配送</td>
					<td>租赁</td>
					<td>运输</td>
					<td>仓储</td>
					<td>购销</td>
					<td>技术</td>
					<td>合作</td>
					<td>协议</td>
					<td>服务</td>
					<td>许可</td>
					<td>劳务</td>
					<td>建工</td>
					<td>统计</td>
					<td>各公司合同占比%</td>
				</tr>
			</table>
			<table class="left_header">
				<tr><td>万向发展</td></tr>
				<tr><td>万象配送</td></tr>
				<tr><td>上海递拎宝</td></tr>
				<tr><td>广州万象</td></tr>
				<tr><td>云南万象</td></tr>
				<tr><td>杭州联报</td></tr>
				<tr><td>北京联报</td></tr>
				<tr><td>天津联报</td></tr>
				<tr><td>江苏联报</td></tr>
				<tr><td>长沙联报</td></tr>
				<tr><td>辽宁联报</td></tr>
				<tr><td>贵阳联报</td></tr>
				<tr><td>成都报网</td></tr>
				<tr><td>福州报网</td></tr>
				<tr><td>武汉报网</td></tr>
				<tr><td>济南报网</td></tr>
				<tr><td>河南报网</td></tr>
				<tr><td>石家庄报网</td></tr>
				<tr><td>天津理物</td></tr>
				<tr><td>江西传世</td></tr>
				<tr><td>统计</td></tr>
				<tr><td>合同类型占比%</td></tr>
			</table>
			
			<table class="table_body">
				<tr>
					<td> </td>
					<td> </td>
					<td> </td>
					<td> </td>
					<td> </td>
					<td> </td>
					<td> </td>
					<td> </td>
					<td> </td>
					<td> </td>
					<td> </td>
					<td> </td>
					<td> </td>
					<td class="body_last"> </td>
				</tr>
			</table>
		</div>	
  </body>
</html>
