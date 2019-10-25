RASkey();// 调用
var publicKeyExponent = "";
var publicKeyModulus = "";
function RASkey() {
	// 生成登陆用RSA公钥 密钥
	$.ajax({
		url : '../getKey.action',
		type : 'post',
		dataType : 'json',
		success : function(data) {
			console.log("data:" + data);
			publicKeyExponent = data[0];
			publicKeyModulus = data[1];
			console.log("获取的模：" + publicKeyExponent);
			console.log("获取公钥：" + publicKeyModulus);
		}
	})

};
//回车提交
$(document).keypress(function(e) {
	// 回车键事件  
	if (e.which == 13) {
		$('input[type="button"]').click();
	}
});
// 点击事件
$("input[type=button]").click(
		function() {

			// 获取文本框的
			var username = $("input[name=username]").val().trim();
			var password = $("input[name=password]").val().trim();
			var code = $("input[name=code]").val().trim();

			console.log("加密前的信息" + username + "," + password + "," + code);

			if (username == "" || password == "" || code == "") {
				new $.zui.Messager('提示消息：请完善信息', {
					type : 'warning' // 定义颜色主题
				}).show();
				return;
			} else if (!username.replace(/[^\w\/]/ig, '')
					|| !password.replace(/[^\w\/]/ig, '')
					|| !code.replace(/[^\w\/]/ig, '')) {
				new $.zui.Messager('提示消息：请输入数字和字母', {
					type : 'warning' // 定义颜色主题
				}).show();
				$("input[name=username]").val("");// 清空文本框的信息
				$("input[name=password]").val("");
				$("input[name=code]").val("");
				return;
			}
			// RSA加密
			RSAUtils.setMaxDigits(200);
			var key = new RSAUtils.getKeyPair(publicKeyExponent, "",
					publicKeyModulus);
			username = RSAUtils.encryptedString(key, username.split(
					"").reverse().join(""));
			password = RSAUtils.encryptedString(key, password.split(
					"").reverse().join(""));
			console.log("加密的后账号：" + username);
			console.log("加密的后密码：" + password);

			// 发送请求
			$.ajax({
				url : "../login.action", // 跳转的页面
				data : "username=" + username + "&password=" + password
						+ "&code=" + code, // 发送请求的数据
				dataType : 'json', // 服务器返回json格式数据
				type : 'post', // HTTP请求类型
				timeout : 10000, // 超时时间设置为10秒；
				success : function(data) {
					console.log("data:" + data);
					if (data == 1) {
						new $.zui.Messager('提示消息：登录成功', {
							type : 'success' // 定义颜色主题
						}).show();
						window.location.href = "../index.jsp";
					} else if (data == 2) {
						new $.zui.Messager('提示消息：验证码错误', {
							type : 'warning' // 定义颜色主题
						}).show();
						$("input[name=code]").val("");// 清空数据
					} else if (data == 0) {
						new $.zui.Messager('提示消息：账号密码错误', {
							type : 'warning' // 定义颜色主题
						}).show();
						$("input[name=username]").val("");// 清空文本框的信息
						$("input[name=password]").val("");
					} else if (data == 3) {
						new $.zui.Messager('提示消息：非法操作', {
							type : 'danger' // 定义颜色主题
						}).show();
					} else if (data == 4) {
						new $.zui.Messager('提示消息：该用户暂时没有权限，无法登录', {
							type : 'warning' // 定义颜色主题
						}).show();
					}
				},
				error : function(xhr, type, errorThrown) {
					new $.zui.Messager('提示消息：服务器繁忙', {
						type : 'danger' // 定义颜色主题
					}).show();
				}
			});

		});
