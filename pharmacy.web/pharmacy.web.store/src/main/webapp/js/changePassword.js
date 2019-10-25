$("#refer").click(function() {
	var account = $("#account").attr("loginid");
	var oldpassword = $("#oldpassword").val();
	var newpassword1 = $("#newpassword1").val();
	var newpassword2 = $("#newpassword2").val();
	console.log("账号：" + account + ",旧密码：" + oldpassword + ",新密码：" + newpassword1 + ",确认密码：" + newpassword2);
	if (oldpassword == "" || newpassword1 == "" || newpassword2 == "") {
		new $.zui.Messager('提示消息：请填写信息', {
			type: 'warning' // 定义颜色主题
		}).show();
		//清空文本框信息
		$("#oldpassword").val(" ");
		$("#newpassword1").val(" ");
		$("#newpassword2").val(" ");
		return;
	} else if (oldpassword.length < 6 || newpassword1.length < 6 || newpassword1.length < 6) {
		new $.zui.Messager('提示消息：密码长度必须大于六位数', {
			type: 'warning' // 定义颜色主题
		}).show();
		//清空文本框信息
		$("#oldpassword").val("");
		$("#newpassword1").val("");
		$("#newpassword2").val("");
		return;
	} else if (!oldpassword.replace(/[^\w\/]/ig, '') || !newpassword1.replace(/[^\w\/]/ig, '') || !newpassword2.replace(
			/[^\w\/]/ig, '')) {
		new $.zui.Messager('提示消息：请输入数字和字母', {
			type: 'warning' // 定义颜色主题
		}).show();
		//清空文本框信息
		$("#oldpassword").val("");
		$("#newpassword1").val("");
		$("#newpassword2").val("");
		return;
	} else if (newpassword1 != newpassword2) {
		new $.zui.Messager('提示消息：两次密码不一致', {
			type: 'warning' // 定义颜色主题
		}).show();
		//清空文本框信息
		$("#oldpassword").val("");
		$("#newpassword1").val("");
		$("#newpassword2").val("");
		return;
	}else if (oldpassword == newpassword2) {
		new $.zui.Messager('提示消息：原密码不能与新密码相同', {
			type: 'warning' // 定义颜色主题
		}).show();
		//清空文本框信息
		$("#oldpassword").val("");
		$("#newpassword1").val("");
		$("#newpassword2").val("");
		return;
	}
	//发送请求
	$.ajax({
		url: "../changpwd.action", //跳转的页面
		data: "account="+account+"&oldpassword=" + oldpassword + "&newpassword=" + newpassword1, //发送请求的数据
		dataType: 'json', //服务器返回json格式数据
		type: 'post', //HTTP请求类型
		timeout: 10000, //超时时间设置为10秒；
		success: function(data) {
			console.log("data:" + data);
			if (data.login == "err") { //如果没有登录（第一种）
				window.location.href = "home/login.html";
			}
			if(data=="密码不一致"){
				new $.zui.Messager('提示消息：原密码不一致', {
					type: 'warning' // 定义颜色主题
				}).show();
				//清空文本框信息
				$("#oldpassword").val("");
				$("#newpassword1").val("");
				$("#newpassword2").val("");
			}else if(data=="0"){
				new $.zui.Messager('提示消息：修改失败', {
					type: 'warning' // 定义颜色主题
				}).show();
				//清空文本框信息
				$("#oldpassword").val("");
				$("#newpassword1").val("");
				$("#newpassword2").val("");
			}else if(data==1){
				new $.zui.Messager('提示消息：修改成功', {
					type: 'succeed' // 定义颜色主题
				}).show();
				//清空文本框信息
				$("#oldpassword").val("");
				$("#newpassword1").val("");
				$("#newpassword2").val("");
			}
		},
		error: function(xhr, type, errorThrown) {
			new $.zui.Messager('提示消息：服务器繁忙', {
				type: 'danger' // 定义颜色主题
			}).show();
		}
	});


});
