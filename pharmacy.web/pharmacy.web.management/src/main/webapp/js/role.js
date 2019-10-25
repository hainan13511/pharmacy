//定义变量
	var start = 0;//开始条数
	var end = 5;//一页查询几条
	var count = 1;//总数据
	var present = 1;// 当前页面
	var allpage=1; // 总页 
	var rolename = "";
	//初始化
	function init() {
		//发送请求
		$.ajax({
					url : "../initRole.action", //跳转的页面
					data : "start=" + start + "&end=" + end + "&rolename="
							+ rolename, //发送请求的数据
					dataType : 'json', //服务器返回json格式数据
					type : 'post', //HTTP请求类型
					timeout : 10000, //超时时间设置为10秒；
					success : function(data) {
						//console.log("data:" + data);
						if (data.login == "err") { //如果没有登录（第一种）
							window.location.href = "home/login.html";
						}
						//绘制表
						var $table = $("#table-edit");
						// 清空除th外所有行
						$("#table-edit tr:not(:first)").empty();
						for (var i = 0; i < data.length; i++) {
							var $tr = $("<tr class='remove-tr'></tr>");
							var $td1 = $("<td>" + (i + 1) + "</td>");
							var $td2 = $("<td>" + data[i].rolename + "</td>");
							var $td3 = $("<td>" + data[i].roleid + "</td>");
							$td3.css("display", "none");
							var $td4 = $('<td><button class="btn btn-link" type="button"><i class="icon icon-edit"></i>编辑</button>'
									+ '<button class="btn btn-link" type="button"><i class="icon icon-trash"></i>删除</button></td>');
							$tr.append($td1, $td2, $td3, $td4);
							$table.append($tr);
						}
						page()//页码
					},
					error : function(xhr, type, errorThrown) {
						new $.zui.Messager('提示消息：服务器繁忙', {
							type : 'danger' // 定义颜色主题
						}).show();
					}
				});

	}
	init();//调用初始化

	
	//打开新增界面
	document.getElementById('btn-add').onclick = function() {
		//清空复选框的勾选
		$("input[type=checkbox]").attr("checked", false);
		$.ajax({
			url : "../roleRedact.action", //跳转的页面
			data : "", //发送请求的数据
			dataType : 'json', //服务器返回json格式数据
			type : 'post', //HTTP请求类型
			timeout : 10000, //超时时间设置为10秒；
			success : function(data) {
				console.log("data:" + data);
				if (data.login == "err") { //如果没有登录（第一种）
					window.location.href = "home/login.html";
				}
				//初始值
				var myTreeData = treeData(data);
				$('#myTree').tree({
					data : myTreeData
				});
			},
			error : function(xhr, type, errorThrown) {
				new $.zui.Messager('提示消息：服务器繁忙', {
					type : 'danger' // 定义颜色主题
				}).show();
			}
		});
		//弹出窗口
		$('#myModal').modal({
			backdrop : 'static',
			show : true
		})
	}
	// //关闭新增界面
	document.getElementById('add-exit').onclick = function() {
		$('#myModal').modal('hide', 'fit')
	}
	//清空
	document.getElementById('btn-null').onclick = function() {
		document.getElementById('text-search').value = "";
	}
	//刷新
	document.getElementById('btn_refresh').onclick = function() {
		location.reload();
	}
	// //关闭修改界面
	document.getElementById('alter-exit').onclick = function() {
		$('#userModal').modal('hide', 'fit')
	}

	//编辑删除
	var table = document.getElementById('table-edit');
	table.onclick = function(e) {
		el = e.target;
		//打开修改角色界面
		if (el.innerText == "编辑") {//编辑
			var name = el.parentElement.parentElement.children[1].innerText;
			var id = el.parentElement.parentElement.children[2].innerText;
			//发送请求
			//发送ajax请求
			$.ajax({
				url : "../roleRedact.action", //跳转的页面
				data : "id=" + id, //发送请求的数据
				dataType : 'json', //服务器返回json格式数据
				type : 'post', //HTTP请求类型
				timeout : 10000, //超时时间设置为10秒；
				success : function(data) {
					console.log("data:" + data);
					if (data.login == "err") { //如果没有登录（第一种）
						window.location.href = "home/login.html";
					}
					//初始值
					var myTreeData = treeData(data);
					$('#userTree').tree({
						data : myTreeData
					});
					getPower(id);//判读对应的角色的权限
				},
				error : function(xhr, type, errorThrown) {
					new $.zui.Messager('提示消息：服务器繁忙', {
						type : 'danger' // 定义颜色主题
					}).show();
				}
			});
			//弹窗口
			$("#updateRoleName").attr('rid',id)//保存id
			$("#updateRoleName").val(name);//分别框赋值
			$('#userModal').modal({
				backdrop : 'static',
				show : true
			})
		} else if (el.innerText == "删除") {//删除
			var id = el.parentElement.parentElement.children[2].innerText;
			var name = el.parentElement.parentElement.children[1].innerText;
			console.log("账号：" + id + ",name:" + name);
			//确定操作判断

			//发送ajax请求
			$.ajax({
				url : "../RoleDel.action", //跳转的页面
				data : "id=" + id, //发送请求的数据
				dataType : 'json', //服务器返回json格式数据
				type : 'post', //HTTP请求类型
				timeout : 10000, //超时时间设置为10秒；
				success : function(data) {
					console.log("data:" + data);
					if (data.login == "err") { //如果没有登录（第一种）
						window.location.href = "home/login.html";
					}else if(data==1){
						new $.zui.Messager('提示消息：删除成功', {
							type : 'succeed' // 定义颜色主题
						}).show();
						init();//刷新页面
					}else if(date==0){
						new $.zui.Messager('提示消息：删除失败', {
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
		}
	}
   //初始化的信息tree数据
function treeData(data) {
	var menus = [];
	for (var i = 0; i < data.length; i++) {
		var items = data[i].secondMenu;
		console.log("二级："+data[i].menuid)
		var itemarr = [];
		// 初始化子选项
		if (items != null && items.length > 0) {
			for ( var j in items) {
			console.log("erj:"+items[j].limitname)
				var item = {
					html : '<div class="checkbox-primary"><input type="checkbox" name="sublevel"class="checkbox_'
							+ data[i].menuid
							+ '" onclick="checkbox(\''
							+ data[i].menuid
							+ '\',\''
							+ items[j].limitid
							+ '\',this)"  value="'
							+ items[j].limitid
							+ '"  lname='+items[j].limitname+items[j].limitid+'><label for="primaryCheckbox1">'
							+ items[j].limitname + '</label></div>',
				};
				itemarr.push(item);
			}
		}
		// 父级选项
		var menu = {
			html : '<div class="checkbox-primary"><input type="checkbox"name="parentLevel" class="menu_'
					+ data[i].menuid
					+ '" onclick="checkbox(\''
					+ 0
					+ '\',\''
					+ data[i].menuid
					+ '\',this)"  value="'
					+ data[i].menuid
					+ '" lname='+data[i].menuname+data[i].menuid+' ><label for="primaryCheckbox1">'
					+ data[i].menuname + '</label></div>',
			open : true,
			children : itemarr
		};
		menus.push(menu);

	}
	return menus;
}
	
//获取选择角色已拥有权限
function getPower(id) {
	// 清除所有复选框选中状态
	$("input[type='checkbox']").prop("checked", false);
	role_id = id;
	$.ajax({
		url : "../initRolePower.ajax",
		data : "id="+id,
		type : "post",
		dataType : "json",
		success : function(data) {
			if (data == null) {
				return;
			}
			for (var i = 0; i < data.length; i++) {
				for (var j = 0; j < data[i].secondMenu.length; j++) {
				console.log("ssej:"+data[i].secondMenu[j].limitname)
				// 遍历所有复选框
				$.each($('input:checkbox'), function() {
					if ($(this).attr("lname") == data[i].menuname+data[i].menuid || $(this).attr("lname")==data[i].secondMenu[j].limitname+data[i].secondMenu[j].limitid) {
						$(this).prop("checked", true);
						console.log("sss:"+data[i].secondMenu[j].limitname+data[i].secondMenu[j].limitid)
					}
				});
			  }
			}
		},
		error : function(e) {
			new $.zui.Messager('提示消息：服务器繁忙', {
				type : 'danger' // 定义颜色主题
			}).show();
		}
	});
}
   
   //添加角色
   function addRole(){
	   //获取文本框的信息
	   var roleName=document.getElementById("inputAccountExample1").value.replace(/\s*/g,"");
	   var choose=	document.getElementsByName('sublevel');//父类复选框打钩
	   var parent =	document.getElementsByName('parentLevel');//子级复选框打钩
		var arr = new Array();//定义一个数组（子级）
		var arr1 = new Array();//定义一个数组（父级）
	   console.log("角色名："+roleName);
	   if(roleName==""){
		   new $.zui.Messager('提示消息：请输入角色名', {
				type : 'warning' // 定义颜色主题
			}).show();
		   return;
	   }
		for (var j = 0; j < choose.length; j++) {//获取子级勾选
			//console.log("子级"+choose.length)
			if(choose[j].checked==true){
				arr.push(choose[j].value)
				console.log("子级选中的："+choose[j].value);
			}
		}
		for (var i = 0; i < parent.length; i++) {//获取父级勾选
			//console.log("父级"+parent.length)
			if(parent[i].checked==true){
				arr1.push(parent[i].value)
				console.log("父级选中的："+parent[i].value);
			}
		}
		console.log("子级的集合长度："+arr.length+",父级的数组的长度："+arr1.length);
		
		if(arr.length==0){//判断是否有选择权限
			   new $.zui.Messager('提示消息：请选择权限', {
					type : 'warning' // 定义颜色主题
				}).show();
			   return;
		   }
		
		//发送ajax请求
		$.ajax({
			url : "../addRole.action",//跳转的页面
			data :"rolename="+roleName+"&sonArr="+JSON.stringify(arr)+"&parentArr="+JSON.stringify(arr1), //发送请求的数据
			dataType : 'text',//服务器返回json式数据
			type : 'post',//HTTP请求类型
			success : function(data) {
				console.log("添加角色的data："+data)
				if(data==1){
					 new $.zui.Messager('提示消息：添加成功', {
							type : 'succeed' // 定义颜色主题
						}).show();
					 $('#myModal').modal('hide', 'fit')//关闭窗口
					 init();//刷新页面
				}else if(data==2){
					 new $.zui.Messager('提示消息：角色名已存在', {
							type : 'warning' // 定义颜色主题
						}).show();
					 document.getElementById("inputAccountExample1").value="";//清空文本框
				}else {
					 new $.zui.Messager('提示消息：添加失败', {
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
   }
   
   
   //修改权限
   function updateRole(){
	 var $rid= $("#updateRoleName").attr("rid");
	 console.log("角色id："+$rid);
	 var choose=document.getElementsByName('sublevel');//父类复选框打钩
	   var parent =	document.getElementsByName('parentLevel');//子级复选框打钩
		var arr = new Array();//定义一个数组（子级）
		var arr1 = new Array();//定义一个数组（父级）
		for (var j = 0; j < choose.length; j++) {//获取子级勾选
			if(choose[j].checked==true){
				arr.push(choose[j].value)
				console.log("子级的："+choose[j].value)
			}
		}
		for (var i = 0; i < parent.length; i++) {//获取父级勾选
			if(parent[i].checked==true){
				arr1.push(parent[i].value)
				console.log("父级的："+parent[i].value)
			}
		}
		console.log("子级的集合长度："+arr.length+",父级的数组的长度："+arr1.length);
		
		if(arr.length==0){//判断是否有选择权限
			   new $.zui.Messager('提示消息：请选择权限', {
					type : 'warning' // 定义颜色主题
				}).show();
			   return;
		   }
		//发送ajax请求
		$.ajax({
			url : "../updateRole.action",//跳转的页面
			data :"rid="+$rid+"&sonArr="+JSON.stringify(arr)+"&parentArr="+JSON.stringify(arr1), //发送请求的数据
			dataType : 'text',//服务器返回json式数据
			type : 'post',//HTTP请求类型
			success : function(data) {
				console.log("添加角色的data："+data)
				if(data==1){
					 new $.zui.Messager('提示消息：修改成功', {
							type : 'succeed' // 定义颜色主题
						}).show();
					 getPower($rid);//刷新已有的权限
					  //init();//刷新页面
					  $('#userModal').modal('hide', 'fit');//关闭窗口
				}else {
					 new $.zui.Messager('提示消息：修改失败', {
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
   }
   
   
   
   
	//页码
	function page(){
		$.ajax({
			url : "../Rolecount.action",//跳转的页面
			data :"rolename="+rolename, //发送请求的数据
			dataType : 'text',//服务器返回json式数据
			type : 'post',//HTTP请求类型
			success : function(data) {
				//如果没有值
				if(data=='0'){
					data=1;
				}
				//计算总页数
				allpage = data;
				console.log("allpage:" + allpage);
				document.getElementById('nowpage').innerText=present 
				document.getElementById('allpage').innerText=allpage;
			},
			error : function(xhr, type, errorThrown) {
				new $.zui.Messager('提示消息：服务器繁忙', {
					type : 'danger' // 定义颜色主题
				}).show();
			}
		});
	
	}
	
	
	//上一页
	function pre() {
		if (present == 1) {
			new $.zui.Messager('首页啦！', {
				type : 'warning' // 定义颜色主题
			}).show();
		} else {
			start = start - 5;
			init();
		}
	}
	//下一页
	function next() {
		if (present == allpage) {
			new $.zui.Messager('尾页啦！', {
				type : 'warning' // 定义颜色主题
			}).show();
		} else {
			start = start + 5;
			present = present + 1;
			init();
		}
	}

	//搜索
	function search() {
		var $textSearch = $("#text-search").val();
		rolename = $textSearch;
		init();
	}
	
	
	// 全选和撤销选择
	function checkbox(pid, mid, obj) {
		var arr =[];
		if(pid=="0"){
		arr = document.getElementsByClassName('checkbox_' + mid);
		}
		console.log("arr:"+arr[0])
		if (obj.checked == true) {
			for (var i = 0; i < arr.length; i++) {
				arr[i].checked = true;
			}
			// 设置父类勾选
			if (pid != "0") {
				$('.menu_' + pid).prop("checked", true);
			}
		} else {
			for (var i = 0; i < arr.length; i++) {
				arr[i].checked = false;
			}
			// 若同级子类全未选中,父类取消勾选
			var len = $('input[class=' + 'checkbox_' + pid + ']:checked').length;
			if (len == 0) {
				$('.menu_' + pid).prop("checked", false);
			}
		}
	}
	function Secondcheckbox(pid, mid, obj) {
		var arr = document.getElementsByClassName('Secondcheckbox_' + mid);

		if (obj.checked == true) {
			for (var i = 0; i < arr.length; i++) {
				arr[i].checked = true;
			}
			// 设置父类勾选
			if (pid != "0") {
				$('.menu_' + pid).prop("checked", true);
			}
		} else {
			for (var i = 0; i < arr.length; i++) {
				arr[i].checked = false;
			}
			// 若同级子类全未选中,父类取消勾选
			var len = $('input[class=' + 'checkbox_' + pid + ']:checked').length;
			if (len == 0) {
				$('.menu_' + pid).prop("checked", false);
			}
		}
	}