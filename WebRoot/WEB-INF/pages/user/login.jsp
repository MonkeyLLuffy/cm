<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<script type="text/javascript" src="../easyui/jquery.min.js"></script>
<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="../easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="../js/jQueryRotate.js"></script>
<script type="text/javascript" src="../js/index.js"></script>

<link rel="stylesheet" type="text/css"
	href="../easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
<link type="text/css" rel="stylesheet" href="../css/index.css">
</head>
<body   style="overflow:-Scroll;overflow-y:hidden">
	<div class="topbac">
		<div class="log">
			<img src="../img/logo.jpg" class="logimg">
		</div>
		<div class="shuxian"
			style="height: 75px; width: 1px; border-left: 1px #ccc solid"></div>
		<div class="loginFont">
			<font color="#29B7E7">合同管理系统</font>
		</div>
	</div>

	<div>
		<div>
			<img src="../img/bluebackground.png" class="mainImg">
		</div>
		<div>
			<img src="../img/city.png" class="cityImg">
		</div>
	</div>

	<div class="login_shuxian"></div>

	<div class="loginLocation">
		<strong><font class="login_font">用户登录</font></strong>
			
		<form action='<c:url value="/user/login.action"></c:url>' class="login_form" method="post" >
		
			<img alt="" src="../img/login_user.png" class="login_user_img" style="display: none;">
			<div class="login_username_head" ></div>
			<input type="text" name="username" class="login_username"
				placeholder="  用户名">
				
			<img alt="" src="../img/login_password.png" class="login_password_img" style="display: none;">
			<div class="login_password_head"></div>
			<input type="password" name="password" class="login_password"
				placeholder="  密码">
			<div class="login_rememberPass_location" ">
				<input type="checkbox" name="rememberPassword"
					class="login_rememberPass"> <font
					class="login_rememberPass_font" style="font-size: 14px;">记住密码</font>
			</div>
			<input type="submit" class="login_submit" value="登录">
		</form>
	</div>

	<img alt="" src="../img/loading.png" class="test" style="display: none;">
	<img alt="" src="../img/yes.png" class="yes" style="display: none;">
</body>
</html>