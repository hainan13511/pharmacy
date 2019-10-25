<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>药房登录界面</title>
<link href="../3rd/css/default.css" rel="stylesheet" type="text/css" />
<!--必要样式-->
<link href="../3rd/css/styles.css" rel="stylesheet" type="text/css" />
<link href="../3rd/css/demo.css" rel="stylesheet" type="text/css" />
<link href="../3rd/css/loaders.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class='login'>
		<div class='login_title'>
			<span>药房登录</span>
		</div>
		<div class='login_fields'>
			<div class='login_fields__user'>
				<div class='icon'>
					<img alt="" src='../3rd/img/user_icon_copy.png'>
				</div>
				<input name="uname" placeholder='用户名' maxlength="16" type='text'
					autocomplete="off" value="" />
				<div class='validation'>
					<img alt="" src='../3rd/img/tick.png'>
				</div>
			</div>
			<div class='login_fields__password'>
				<div class='icon'>
					<img alt="" src='../3rd/img/lock_icon_copy.png'>
				</div>
				<input name="upwd" placeholder='密码' maxlength="16" type='password'
					autocomplete="off">
				<div class='validation'>
					<img alt="" src='../3rd/img/tick.png'>
				</div>
			</div>
			<div class='login_fields__password'>
				<div class='icon'>
					<img alt="" src='../3rd/img/key.png'>
				</div>
				<input name="code" placeholder='验证码' maxlength="4" type='text'>
				<div class='validation' style="opacity: 1; right: -5px; top: -3px;">
					<img alt="" class ="J_codeimg"  src="../SecurityCodeImageAction.action" onclick="changeCode(this)">
				</div>
			</div>
			<div class='login_fields__submit'>
				<input type='button' id="btn_login" value='登 录'>
			</div>
		</div>
		<div class='success'></div>
		<div class='disclaimer'>
			<p>欢迎登陆药房管理系统</p>
		</div>
	</div>
	<div class='authent'>
		<div class="loader"
			style="height: 44px; width: 44px; margin-left: 28px;">
			<div class="loader-inner ball-clip-rotate-multiple">
				<div></div>
				<div></div>
				<div></div>
			</div>
		</div>
		<p>认证中...</p>
	</div>
	<div class="OverWindows"></div>
	<link href="../3rd/layui/css/layui.css" rel="stylesheet" type="text/css" />
	<script src="../3rd/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="../3rd/js/jquery-ui.min.js"></script>
	<script type="text/javascript"
		src='../3rd/js/stopExecutionOnTimeout.js'></script>
	<script src="../3rd/layui/layui.js" type="text/javascript"></script>
	<script src="../3rd/js/Particleground.js" type="text/javascript"></script>
	<script src="../3rd/js/Treatment.js" type="text/javascript"></script>
	<script src="../3rd/js/jquery.mockjax.js" type="text/javascript"></script>
	<script src="../js/security.js" type="text/javascript" charset="utf-8"></script>
	<script src="../js/login.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>