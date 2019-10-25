var publicKeyExponent = "";
var publicKeyModulus = "";
function getRaskey() {
	// 生成登陆用RSA公钥 密钥
	$.ajax({
		url : '../html/getKey.action',
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
//网页加载时获取公钥
getRaskey();


/**
 * 
 */
	function changeCode(e){
		e.src="../SecurityCodeImageAction.action?time="+new Date().getTime();
	}

		var canGetCookie = 0;//是否支持存储Cookie 0 不支持 1 支持
		var ajaxmockjax = 0;//是否启用虚拟Ajax的请求响 0 不启用  1 启用
		//默认账号密码

		var truelogin = "10001";
		var truepwd = "123456";

		$(document).keypress(function(e) {
			// 回车键事件  
			if (e.which == 13) {
				$('input[type="button"]').click();
			}
		});
		//粒子背景特效
		$('body').particleground({
			dotColor : '#E8DFE8',
			lineColor : '#133b88'
		});
		$('input[name="pwd"]').focus(function() {
			$(this).attr('type', 'password');
		});
		$('input[type="text"]').focus(function() {
			$(this).prev().animate({
				'opacity' : '1'
			}, 200);
		});
		$('input[type="text"],input[type="password"]').blur(function() {
			$(this).prev().animate({
				'opacity' : '.5'
			}, 200);
		});
		$('input[name="login"],input[name="pwd"]').keyup(function() {
			var Len = $(this).val().length;
			if (!$(this).val() == '' && Len >= 5) {
				$(this).next().animate({
					'opacity' : '1',
					'right' : '30'
				}, 200);
			} else {
				$(this).next().animate({
					'opacity' : '0',
					'right' : '20'
				}, 200);
			}
		});
		
		var open = 0;
		layui.use('layer', function() {
			var msgalert = '默认账号:' + truelogin + '<br/> 默认密码:' + truepwd;
			//非空验证
			$('input[type="button"]').click(function() {
				var username = $('input[name="uname"]').val();
				var password = $('input[name="upwd"]').val();
				var code = $('input[name="code"]').val();
				if (username == '') {
					ErroAlert('请输入您的账号');
				} else if (password == '') {
					ErroAlert('请输入密码');
				} else if (code == '' || code.length != 4) {
					ErroAlert('输入验证码');
				} else {
					//认证中..
					console.log('账号:'+username+',密码:'+password)
					$('.login').addClass('test'); //倾斜特效
					setTimeout(function() {
						$('.login').addClass('testtwo'); //平移特效
					}, 300);
					setTimeout(function() {
						$('.authent').show().animate({
							right : -320
						}, {
							easing : 'easeOutQuint',
							duration : 600,
							queue : false
						});
						$('.authent').animate({
							opacity : 1
						}, {
							duration : 200,
							queue : false
						}).addClass('visible');
					}, 500);

					//将数据发送到后端前加密
					RSAUtils.setMaxDigits(200);
					var key = new RSAUtils.getKeyPair(publicKeyExponent, "",
							publicKeyModulus);
					username = RSAUtils.encryptedString(key, username.split(
							"").reverse().join(""));
					password = RSAUtils.encryptedString(key, password.split(
							"").reverse().join(""));
					
					
					//登陆
					$.ajax({
						url : "../login.action",
						type : "post",
						data : {
							username : username,
							password : password,
							code : code
						},
						dataType : "json",
						success : function(data) {
							console.log('data：' + data);
							//认证完成
							setTimeout(function() {
								$('.authent').show().animate({
									right : 90
								}, {
									easing : 'easeOutQuint',
									duration : 600,
									queue : false
								});
								$('.authent').animate({
									opacity : 0
								}, {
									duration : 200,
									queue : false
								}).addClass('visible');
								$('.login').removeClass('testtwo'); //平移特效
							}, 1500);
							setTimeout(function() {

								$('.authent').hide();
								$('.login').removeClass('test');
								if (data == 1) {
									//登录成功
									$('.login div').fadeOut(100);
									$('.success').fadeIn(1000);
									$('.success').html("登陆成功<br /><br />欢迎回来");
									//跳转操作
									setTimeout(function() {
										window.location.href="../index.jsp"; 
									}, 2000);
								} else if (data == 0) {
									//账号名或密码有误
									$('.login div').fadeOut(100);
									$('.success').fadeIn(1000);
									$('.success').html("账号名或密码有误");
									//跳转操作
									setTimeout(function() {
										location = location;
									}, 2000);
								}else if (data == 2) {
									//验证码有误
									$('.login div').fadeOut(100);
									$('.success').fadeIn(1000);
									$('.success').html("验证码有误");
									//跳转操作
									setTimeout(function() {
										location = location;
									}, 2000);
								}else{
									//账号名或密码或验证码有误
									$('.login div').fadeOut(100);
									$('.success').fadeIn(1000);
									$('.success').html("没有登录权限");
									//跳转操作
									setTimeout(function() {
										location = location;
									}, 2000);
								}

							}, 2000);
						},
						error : function(e) {
							//验证码有误
							$('.login div').fadeOut(100);
							$('.success').fadeIn(1000);
							$('.success').html("服务器异常");
							//跳转操作
							setTimeout(function() {
								location = location;
							}, 2000);
						}
					});
				}
			});
		})
