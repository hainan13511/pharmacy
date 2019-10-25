<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>用户管理</title>
		<link rel="stylesheet" href="../3rd/zui/css/zui.min.css">
		<link href="../3rd/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
		<style>
		html,body{
				width: 100%;
				height: 100%;
				margin: 0;
				padding: 2.5px;
			}
			.form-lf-time{
				width: 100%;
			}
			.top-btn{
				width: 100%;
			}
			th{
				text-align: center;
				width: 25%;
			}
			td{
				text-align: center;
			}
			.btn-fy{
				margin-left: 10px;
				margin-right: 10px;
			}
			.grid-container{
				width: 100%;
				display:-webkit-flex;
				display: flex;
			}
			.div_page {
				width: 100%;
				position:absolute;
				top:90%;
				text-align: center;
			}
			.div-box{
			width: 100%;
			height: 100%;
			}
		</style>
	</head>
	<body>
		<div class="panel panel-primary div-box" >
			<div class="panel-heading">
				用户管理
			</div>
		
		<div class="modal fade" id="div-adduser">
			<div class="modal-dialog">
				<div class="modal-content">
					<div style="width: 100%; height: 500px;">
						<div class="panel panel-primary">
							<div class="panel-heading">
								新增用户 
							</div>
						</div>
						<div class="" style="height: 60%;" >
							<table class="table table-hover table-bordered">
								<thead>
									<tr><td>用户名：</td><td><input id="uname" type="text" class="form-control form-lf-time" placeholder="用户名"></td></tr>
									<tr><td>账号：</td><td><input id="uacc" type="text" class="form-control form-lf-time" placeholder="账号"></td></tr>
										<tr><td>密码：</td><td><input id="upwd" type="text" class="form-control form-lf-time" placeholder="密码"></td></tr>
								</thead> 
							</table>
							<div style=" text-align: center; margin-top: 10px;margin-bottom: 10px" ><h3>角色</h3></div>
							<table id="table-role" class="table table-hover">
								
							</table>
						</div>
						<div style="text-align: center;">
							<button onclick="adduser()" class="btn btn-primary " type="button"><i class="icon icon-plus"></i> 新 增</button>
							<button onclick="adduserback()" class="btn btn-primary " type="button"><i class="icon icon-share-alt"></i> 返 回</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="div-userser">
			<div class="modal-dialog">
				<div class="modal-content" >
					<div style="width: 100%; height: 500px;">
						<div class="panel panel-primary">
							<div class="panel-heading">
								用户编辑
							</div>
						</div>
						<div style=" text-align: center; margin-top: 10px;margin-bottom: 10px" ><h3 id="user_name"></h3></div>
						<div style=" text-align: center; margin-top: 10px;margin-bottom: 10px" ><h3>角色</h3></div>
							<table id="table-role1" class="table table-hover">
								
							</table>
						<div style="text-align: center;">
							<button onclick="userMgrSet()" class="btn btn-primary" type="button"><i class="icon icon-save"></i> 保 存</button>
							<button onclick="usersetback()" class="btn btn-primary" type="button"><i class="icon icon-share-alt"></i> 返 回</button>
						</div>
					</div>
				</div>
			</div>
		</div>
			<div class="panel-body grid-container">		
				<div class="btn-fy" style="width: 10%;">
					<button onclick="adduserblock()" class="btn btn-primary top-btn" type="button"><i class="icon icon-plus"></i> 新 增</button>
				</div>
				<div class="btn-fy" style="width: 10%;">
					<button onclick="Refresh()" class="btn btn-warning  top-btn" type="button"><i class="icon icon-spin icon-refresh"></i> 刷 新</button>
				</div>
				<div class="input-control has-icon-left btn-fy">
				  	<input id="username" type="text" class="form-control" style="padding-left: 85px">
  					<label for="username" class="input-control-label-left" style="margin-left: 20px;width: auto;">用户名：</label>
  					<label for="username" class="input-control-icon-left"><i class="icon icon-user "></i></label>
				</div>
				<div class="input-control has-icon-left btn-fy">
						<input id="stime" type="text" class="form-control form-date time form-lf-time"  style="padding-left: 90px" placeholder="请选择时间">  
	  					<label for="stime" class="input-control-label-left" style="margin-left: 20px;width: auto;">开始时间：</label>
	  					<label for="stime" class="input-control-icon-left"><i class="icon icon-calendar"></i></label>
					</div>
					<div class="input-control has-icon-left btn-fy">
						<input id="etime" type="text" class="form-control form-date time form-lf-time"  style="padding-left: 90px" placeholder="请选择时间">  
	  					<label for="etime" class="input-control-label-left" style="margin-left: 20px;width: auto;">结束时间：</label>
	  					<label for="etime" class="input-control-icon-left"><i class="icon icon-calendar"></i></label>
					</div>
				<div  class="btn-fy" style="width: 10%;">
					<button onclick="seluser()" class="btn btn-info  top-btn" type="button"><i class="icon icon-search"></i> 搜 索</button>
				</div>
				<div  class="btn-fy" style="width: 10%;">
						<button onclick="clearon()" class="btn btn-danger  top-btn" type="button"><i class="icon icon-trash"></i> 清 空</button>
				</div>
		</div>
		<div class="container-fluid" style="height: 80%;">
			<table id="g-table" class="table table-hover table-bordered">
				<thead>
					<tr>
						<th>用户名</th>
						<th>注册时间</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
			</table>
		</div>
			<div class="div_page div-button">
				<ul class="pager pager-pills">
					<li class="previous "><a  onclick="upys()">上一页</a></li>
					<li><span id="sp-ym">1/1</span></li>
					<li class="next"><a  onclick="doys()">下一页</a></li>
				</ul>
			</div>
		</div>
		<!-- <div style="text-align: center; margin-right: 20px;">
			<button  class="btn btn-primary btn-fy" type="button">上一页</button><span ></span><button  class="btn btn-primary btn-fy" type="button">下一页</button>
		</div> -->
		<script src="../3rd/zui/lib/jquery/jquery.js"></script>
		<script src="../3rd/zui/js/zui.min.js"></script>
		<script src="../3rd/zui/lib/datetimepicker/datetimepicker.min.js"></script>
		<script src="../js/drugTime.js"></script>
		
	</body>
<script type="text/javascript">
	$(".form-date").datetimepicker(
	{
		language:  "zh-CN",
		weekStart: 1,
		todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0,
		format: "yyyy-mm-dd"
	});
	//取消显示添加用户
	function adduserback(){
		$('#div-adduser').modal('toggle', 'center')
	}
	//取消显示添加用户
	function userinfback(){
		$('#div-userinf').modal('toggle', 'center')
	}
	//取消显示添加用户
	function usersetback(){
		$('#div-userser').modal('toggle', 'center')
	}
	//显示编辑用户
	function usersetblock(){
		$('#div-userser').modal('show', 'fit')
	}
	//显示添加用户
	function adduserblock(){
		$('#div-adduser').modal('show', 'fit')
	}
	//显示用户详细
	function userinfblock(){
		$('#div-userinf').modal('show', 'fit')
	}
	//清空输入框
	function clearon(){
		document.getElementById("username").value= '';
		document.getElementById("stime").value= '';
		document.getElementById("etime").value= '';
		
	}
	//表格点击事件
	document.getElementById("g-table").onclick = function(e) {
		
		var temp = e.target;
		if(temp.innerText=='禁用'){
			var uid = temp.parentElement.parentElement.children[0].getAttribute('Name');
			userProhibit(uid)
		}else if(temp.innerText=='启用'){
			var uid = temp.parentElement.parentElement.children[0].getAttribute('Name');
			userEnable(uid)
		}else if(temp.innerText=='重置密码'){
			var uid = temp.parentElement.parentElement.children[0].getAttribute('Name');
			resetPassword(uid)
		}else if(temp.innerText=='编辑'){
			var arr = document.getElementsByName('checkbox1');
			for (var i = 0; i < arr.length; i++) {
				arr[i].checked = false;
			}
			var uid = temp.parentElement.parentElement.children[0].getAttribute('Name');
			var uname = temp.parentElement.parentElement.parentElement.children[0].innerText;
			document.getElementById("user_name").innerText  = uname
			document.getElementById("user_name").setAttribute("Name", uid)
			userMgrRoleTrue(uid)
			usersetblock();
		}
		
	}
//表格初始化方法
var zs = 0;
var ys = 1;
var ks = 1;
var jt = 5;
var stime = ""
var etime = "";
var name = ""
function table(){
	$.ajax({
		url : "../userMgrTable.action",
		type : "post",
		data : "ks="+ks+"&jt="+jt+"&stime="+stime+"&etime="+etime+"&uname="+name,
		dataType : "json",
		success : function(data) {
			var arr = document.getElementsByName('table-tr');
			for (var i = arr.length - 1; i >= 0; i--) {
				arr[0].remove();
			}
			for (var i = 0; i < data.length; i++) {
				var tr = document.createElement('tr');
				tr.setAttribute("Name", "table-tr")

				var td1 = document.createElement('td');
				td1.innerText = data[i].uname;
				td1.setAttribute("Name", data[i].uid)

				var td2 = document.createElement('td');
				var time = new Date(data[i].utime).Format('yyyy-MM-dd hh:mm:ss')
                td2.innerText = time;
				
				var td3 = document.createElement('td');
				var state = ''
				if(data[i].ustate!="禁用"){
					state = "正常"
				}else{
					state = "冻结"
				}
				td3.innerText = state

				var td4 = document.createElement('td');
				if(data[i].ustate!="禁用"){
					td4.innerHTML = '<a>禁用</a> <a>重置密码</a> <a>编辑</a>';
				}else {
					td4.innerHTML = '<a>启用</a> <a>重置密码</a> <a>编辑</a>';
				}
				tr.appendChild(td1);
				tr.appendChild(td2);
				tr.appendChild(td3);
				tr.appendChild(td4);
				document.getElementById('g-table').appendChild(tr);
			}
			count();
		},
		error : function(e) {
			new $.zui.Messager('提示消息：用户表格获取失败', {
				type: 'danger' // 定义颜色主题
				}).show();
		}
	})
}
table();
function count(){
	$.ajax({
		url : "../userMgrTableCount.action",
		type : "post",
		data : "&stime="+stime+"&etime="+etime+"&name="+name,
		dataType : "json",
		success : function(data) {
			if(data!=""){
				if(data==0){
					ks = 1
					ys = 1
				}else{
					zs = data
					if(zs%jt==0){
						ys=zs/jt
					}else{
						ys=Math.floor(zs/jt)+1
					}
				}
				document.getElementById("sp-ym").innerText=ks+"/"+ys
			}
			
		},
		error : function(e) {
			new $.zui.Messager('提示消息：用户表格页码获取失败', {
				type: 'danger' // 定义颜色主题
				}).show();
		}
	})
}
function seluser(){
	stime = document.getElementById("stime").value
	etime = document.getElementById("etime").value
	name = document.getElementById("username").value
	if(stime!=""){
		if(etime!=""){
			if(Date.parse(etime) - Date.parse(stime)>=0){
				ks=1;
				table();
				return;
			}else{
				new $.zui.Messager('提示消息：开始时间不能大于结束时间', {
					type: 'danger' // 定义颜色主题
					}).show();
				return;
			}
		}else{
			new $.zui.Messager('提示消息：请选择一个时间段', {
				type: 'success' // 定义颜色主题
				}).show();
			return;
		}
	}
	if(etime!=""){
		if(stime!=""){
			if(Date.parse(etime) - Date.parse(stime)>=0){
				ks=1;
				table();
				return;
			}else{
				new $.zui.Messager('提示消息：开始时间不能大于结束时间', {
					type: 'danger' // 定义颜色主题
					}).show();
				return;
			}
		}else{
			new $.zui.Messager('提示消息：请选择一个时间段', {
				type: 'success' // 定义颜色主题
				}).show();
			return;
		}
	}
	if(stime==""&&etime==""){
		ks=1;
		table();
	}
}
function upys(){
	if(ks>1){
		ks--;	
	}else{
		new $.zui.Messager('提示消息：已经是首页了', {
			type: 'success' // 定义颜色主题
			}).show();
	}
	table();
}
function doys(){
	if(ks<ys){
		ks++;	
	}else{
		new $.zui.Messager('提示消息：已经是尾页了', {
			type: 'success' // 定义颜色主题
			}).show();
	}
	table();
}
function userProhibit(uid){
	$.ajax({
		url : "../userMgrProhibit.action",
		type : "post",
		data : "&uid="+uid,
		dataType : "json",
		success : function(data) {
			if(data=="yes"){
				new $.zui.Messager('提示消息：禁用成功', {
					type: 'success' // 定义颜色主题
					}).show();
				table();
			}else{
				new $.zui.Messager('提示消息：用户禁用失败', {
					type: 'danger' // 定义颜色主题
					}).show();
			}
			
		},
		error : function(e) {
			new $.zui.Messager('提示消息：用户禁用申请失败', {
				type: 'danger' // 定义颜色主题
				}).show();
		}
	})
}
function userEnable(uid){
	$.ajax({
		url : "../userMgrEnable.action",
		type : "post",
		data : "&uid="+uid,
		dataType : "json",
		success : function(data) {
			if(data=="yes"){
				new $.zui.Messager('提示消息：启用成功', {
					type: 'success' // 定义颜色主题
					}).show();
				table();
			}else{
				new $.zui.Messager('提示消息：用户启用失败', {
					type: 'danger' // 定义颜色主题
					}).show();
			}
			
		},
		error : function(e) {
			new $.zui.Messager('提示消息：用户启用申请失败', {
				type: 'danger' // 定义颜色主题
				}).show();
		}
	})
}
function resetPassword(uid){
	$.ajax({
		url : "../resetPassword.action",
		type : "post",
		data : "&uid="+uid,
		dataType : "json",
		success : function(data) {
			if(data=="yes"){
				new $.zui.Messager('提示消息：密码重置成功', {
					type: 'success' // 定义颜色主题
					}).show();
				table();
			}else{
				new $.zui.Messager('提示消息：用户密码重置失败', {
					type: 'danger' // 定义颜色主题
					}).show();
			}
			
		},
		error : function(e) {
			new $.zui.Messager('提示消息：用户密码重置申请失败', {
				type: 'danger' // 定义颜色主题
				}).show();
		}
	})
}
function Refresh(){
	location.reload();
}
function adduser(){
	var uname = document.getElementById("uname").value;
	var uacc = document.getElementById("uacc").value;
	var upwd = document.getElementById("upwd").value;
	var arr = document.getElementsByName('checkbox');
	var list = new Array()
	for (var i = 0; i < arr.length; i++) {
		if(arr[i].checked){
        	list.push(arr[i].getAttribute("Value")) 
       	}
	}
	var types = JSON.stringify(list)
	if(uname!=''&&uacc!=''&&upwd!=''){
		$.ajax({
			url : "../userMgrAdd.action",
			type : "post",
			data : "&uname="+uname+"&uacc="+uacc+"&upwd="+upwd+"&list="+types,
			dataType : "json",
			success : function(data) {
				if(data=="yes"){
					new $.zui.Messager('提示消息：添加成功', {
						type: 'success' // 定义颜色主题
						}).show();
					$('#div-adduser').modal('toggle', 'center')
					var arr = document.getElementsByName('checkbox');
					for (var i = 0; i < arr.length; i++) {
						arr[i].checked = false
					}
					table();
				}else if(data=='cz'){
					new $.zui.Messager('提示消息：账号已经存在', {
						type: 'warning' // 定义颜色主题
						}).show();
				}else{
					new $.zui.Messager('提示消息：用户添加失败', {
						type: 'danger' // 定义颜色主题
						}).show();
				}
				document.getElementById("uname").value = '';
				document.getElementById("uacc").value = '';
				document.getElementById("upwd").value = '';
			},
			error : function(e) {
				new $.zui.Messager('提示消息：用户添加申请失败', {
					type: 'danger' // 定义颜色主题
					}).show();
				document.getElementById("uname").value = '';
				document.getElementById("uacc").value = '';
				document.getElementById("upwd").value = '';
			}
		})
	}else{
		new $.zui.Messager('提示消息：请输入完整的信息', {
			type: 'warning' // 定义颜色主题
			}).show();
	}
}
function userMgrRole(){
	$.ajax({
		url : "../userMgrRole.action",
		type : "post",
		data : "",
		dataType : "json",
		success : function(data) {
			var arr = document.getElementsByName('table-tr2');
			for (var i = arr.length - 1; i >= 0; i--) {
				arr[0].remove();
			}
			if(data.length%5==0){
				zs = data.length/5
			}else{
				zs=Math.floor(data.length/5)+1
			}
			for (var i = 0; i < zs; i++) {
				var tr = document.createElement('tr');
				tr.setAttribute("Name", "table-tr2");
				for (var j = i*5; j < (i+1)*5; j++) {
					if(data[j]!=undefined){
						var td = document.createElement('td');
						td.style.cssText='text-align: left'
						var checkbox = document.createElement("input");
						var span =  document.createElement("span");
						span.innerText = data[j].rolename;
						checkbox.setAttribute("type","checkbox"); 
						td.appendChild(checkbox)
						td.appendChild(span)
						checkbox.setAttribute("Name", "checkbox")
						checkbox.setAttribute("Value", data[j].roleid)
						tr.appendChild(td);
						document.getElementById('table-role').appendChild(tr);
						/* checkbox.id="checkbox"+data[j].roleid
						checkbox.setAttribute("Name", "checkbox1")
						document.getElementById('table-role1').appendChild(tr); */
					}
				}
			}
		},
		error : function(e) {
			new $.zui.Messager('提示消息：角色获取失败', {
				type: 'danger' // 定义颜色主题
				}).show();
		}
	})
}
function userMgrRole1(){
	$.ajax({
		url : "../userMgrRole.action",
		type : "post",
		data : "",
		dataType : "json",
		success : function(data) {
			var arr = document.getElementsByName('table-tr3');
			for (var i = arr.length - 1; i >= 0; i--) {
				arr[0].remove();
			}
			if(data.length%5==0){
				zs = data.length/5
			}else{
				zs=Math.floor(data.length/5)+1
			}
			for (var i = 0; i < zs; i++) {
				var tr = document.createElement('tr');
				tr.setAttribute("Name", "table-tr3");
				for (var j = i*5; j < (i+1)*5; j++) {
					if(data[j]!=undefined){
						var td = document.createElement('td');
						td.style.cssText='text-align: left'
						var checkbox = document.createElement("input");
						var span =  document.createElement("span");
						span.innerText = data[j].rolename;
						checkbox.setAttribute("type","checkbox"); 
						td.appendChild(checkbox)
						td.appendChild(span)
						checkbox.setAttribute("Name", "checkbox")
						checkbox.setAttribute("Value", data[j].roleid)
						tr.appendChild(td);
						checkbox.id="checkbox"+data[j].roleid
						checkbox.setAttribute("Name", "checkbox1")
						document.getElementById('table-role1').appendChild(tr);
					}
				}
			}
		},
		error : function(e) {
			new $.zui.Messager('提示消息：角色获取失败', {
				type: 'danger' // 定义颜色主题
				}).show();
		}
	})
}
userMgrRole();
userMgrRole1();
function userMgrRoleTrue(uid){
	$.ajax({
		url : "../userMgrRoleGet.action",
		type : "post",
		data : "uid="+uid,
		dataType : "json",
		success : function(data) {
			for (var i = 0; i < data.length; i++) {
				document.getElementById("checkbox"+data[i]).checked=true;
			}
		},
		error : function(e) {
			new $.zui.Messager('提示消息：用户角色获取失败', {
				type: 'danger' // 定义颜色主题
				}).show();
		}
	})
}
function userMgrSet(){
	var arr = document.getElementsByName('checkbox1');
	var list = new Array()
	for (var i = 0; i < arr.length; i++) {
		if(arr[i].checked){
        	list.push(arr[i].getAttribute("Value")) 
       	}
	}
	var types = JSON.stringify(list)
	var uid = document.getElementById('user_name').getAttribute("Name")
	$.ajax({
		url : "../userMgrRoleSet.action",
		type : "post",
		data : "uid="+uid+"&arr="+types,
		dataType : "json",
		success : function(data) {
			if(data=='yes'){
				new $.zui.Messager('提示消息：修改成功', {
					type: 'success' // 定义颜色主题
					}).show();
				usersetback();
			}else{
				new $.zui.Messager('提示消息：用户角色修改失败', {
					type: 'danger' // 定义颜色主题
					}).show();
				usersetback();
			}
		},
		error : function(e) {
			new $.zui.Messager('提示消息：用户角色获取失败', {
				type: 'danger' // 定义颜色主题
				}).show();
		}
	})
}
</script>
</html>