<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>main</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	
	<script type="text/javascript" src="../easyui/jquery.min.js"></script>
	<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript"
	src="../easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="../js/jQueryRotate.js"></script>
	<script type="text/javascript" src="../js/main.js"></script>

	<link rel="stylesheet" type="text/css"
	href="../easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../css/main.css">
	<link rel="stylesheet" type="text/css" href="../css/index.css">
	
  </head>
  
  <body style="overflow:-Scroll;overflow-x:hidden">
  <div class="showLocation" id="showLocation">
  <div class="main_textBoxLocation" id="main_textBoxLocation">
			<input type="text" class="main_searchTextBox clickable" readonly="readonly" id="serachBox"><input
				type="button" class="main_searchBtn" value="搜索">
		</div>
<!-- <img src="../img/jiantou.png" class="main_jianTou" id="jiantou"> -->

   <div class="gaojiSerach" style="display: none;" id="serachLocation">
   <form action='<c:url value="/contractRecord/advancedSearch.action"></c:url>' method="get" target="body">
   		<div class="contractNameLocation"><font>按合同名称检索</font><input type="text" name="contractName" class="contractName inputBox"></div>
   		<div class="idLocation"><font>按合同编号检索</font><input type="text" name="id" class="contract_id inputBox"></div>
   		<div class="partyALocation"><font>按合同甲方检索</font><input type="text" name="partyA" class="partyA inputBox" ></div>
   		<div class="partyBLocation"><font>按合同乙方检索</font><input type="text" name="partyB" class="partyB inputBox" ></div>
   		<div class="departLocation"><font>按经办部门检索</font><select name="depart" class="click">
   			<option value="">请选择</option>
   			<c:forEach items="${depart }" var="d">
   			<option value="${d.codeName }">${d.codeName }</option>
   			</c:forEach>
   		</select></div>
   		<div class="operatorLocation"><font>按经办人检索</font><input type="text" name="operator" class="operator inputBox"></div>
   		<div class="contractAmountLocation"><font>按合同金额检索</font><input type="text" name="contractAmount" class="contractAmount inputBox"></div>
   		<div class="contractTypeLocation"><font>按合同类型检索</font><select name="contractType" class="click">
   			<option value="">请选择</option>
   			<c:forEach items="${contractType }" var="c">
   			<option value="${c.codeName }">${c.codeName }</option>
   			</c:forEach>
   		</select></div>
   		<div class="signingDateStartLocation"><font>按签约日期检索&nbsp;</font><input type="text" name="signingDateStart" class="signingDate inputBox dateBox signingDateStart" placeholder="起始时间" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="signingDateEnd" class="end inputBox dateBox signingDateEnd" placeholder="结束时间" ></div>
   		<div class="importDateStartLocation"><font>按导入日期检索&nbsp;</font><input type="text" name="importDateStart" class="importDate inputBox dateBox importDateStart" placeholder="起始时间">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="importDateEnd" class="end inputBox dateBox importDateEnd" placeholder="结束时间" ></div>
   		<div class="deadlineStartLocation"><font>按合同期限检索&nbsp;</font><input type="text" name="deadlineStart" class="deadline inputBox dateBox deadlineStart" placeholder="起始时间">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="deadlineEnd" class="end inputBox dateBox deadlineEnd"  placeholder="结束时间"></div>
   		<input type="reset" class="reset">
   		<input type="button" class="closeable" value="关闭">
    </form>
   </div>
   </div>
<table class="table" align="center">
	<tr>
		<td  align="center" class="main_top">
			<iframe frameborder="0" scrolling="no" src='<c:url value="/user/showTop.action"></c:url>' name="top" class="topFrame" style="margin-top:0px;margin-bottom:0px margin:0px; padding:0px"></iframe>
		</td>
	</tr>
	<tr>
		<td class="main_body">
			<iframe frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src='<c:url value="/contractRecord/advancedSearch.action"></c:url>' name="body" class="bodyFrame" style="margin-top:0px;margin-bottom:0px margin:0px; padding:0px"></iframe>
		</td>
	</tr>
</table>



  
  
 
</html>