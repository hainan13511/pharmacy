<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="pharmacy.common.model.UserInfo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>修改密码</title>
		<link rel="stylesheet" href="../3rd/zui/css/zui.min.css">
		<script src="../3rd/jq/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
	<%
    UserInfo users=(UserInfo)request.getSession().getAttribute("user");
    if(users==null){
    	response.sendRedirect("login.jsp");
    	return;
    }
    %>
		<div style="margin:  50px auto; width: 500px; ">
		<form class="form-horizontal">
			<div class="form-group">
				<label for="exampleInputAccount9" class="col-sm-2 required">账号</label>
				<div class="col-md-4 col-sm-6">
					<input type="text" id="account" class="form-control"loginid="<%=users.getUid() %>" value="<%=users.getUaccount() %>" readonly="true">
				</div>
			</div>
			<div class="form-group">
				<label for="exampleInputPassword4" class="col-sm-2 required">旧密码</label>
				<div class="col-md-4 col-sm-6">
					<input type="password" class="form-control" id="oldpassword" placeholder="旧密码">
				</div>
			</div>
			<div class="form-group">
				<label for="exampleInputPassword4" class="col-sm-2 required">新密码</label>
				<div class="col-md-4 col-sm-6">
					<input type="password" class="form-control" id="newpassword1" placeholder="新密码">
				</div>
			</div>
			<div class="form-group">
				<label for="exampleInputPassword4" class="col-sm-2 required">确认新密码</label>
				<div class="col-md-4 col-sm-6">
					<input type="password" class="form-control" id="newpassword2" placeholder="确认新密码">
				</div>
			</div>
			<div class="form-group form-btn">
				<div class="col-md-4 col-sm-8">
					<button type="button" id="refer" class="btn btn-default" style="width: 100%;margin-top: 5px">确认修改</button>
				</div>
			</div>
		</form>
		
		</div>
	</body>
	<!-- ZUI Javascript 依赖 jQuery -->
	<script src="../3rd/zui/lib/jquery/jquery.js"></script>
	<!-- ZUI 标准版压缩后的 JavaScript 文件 -->
	<script src="../3rd/zui/js/zui.min.js"></script>
	<script src="../js/changePassword.js" type="text/javascript" charset="utf-8"></script>
</html>
