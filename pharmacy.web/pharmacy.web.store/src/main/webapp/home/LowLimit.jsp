<%@page import="pharmacy.common.model.UserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<%
    UserInfo users = (UserInfo) session.getAttribute("user");
    if(users==null){
    %>
     <script type="text/javascript">
        window.top.location.href = "login.jsp";
        </script>
    <%
            return;
        }
    %>
		<meta charset="utf-8">
		<title>药品低限报警</title>
		<link rel="stylesheet" href="../3rd/zui/css/zui.min.css">
		<link href="../3rd/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
		<style>
		html,body{
				width: 100%;
				height: 99%;
				margin: 0;
				padding: 2.5px;
			}
			.grid-container{
				width: 100%;
				display:-webkit-flex;
				display: flex;
			}
			.btn-fy{
				margin-left: 10px;
				margin-right: 10px;
			}
			.top-btn{
				width: 100%;
			}
			th{
				text-align: center;
				width: 33.3%;
			}
			td{
				text-align: center;
			}
			.div_btn {
				text-align: center;
			}
			.div_page {
				width: 100%;
				position:absolute;
				top:85%;
				text-align: center;
			}
			.div-box{
				width: 100%;
				height: 100%;
			}
		</style>
	</head>
	<body>
	<div class="modal fade" id="div-applty">
		<div class="modal-dialog">
			<div class="modal-content">
				<div style="width: 100%; height: 500px;">
					<div class="panel panel-primary">
						<div class="panel-heading">
							药品请领
						</div>
					</div>
					<div class="" style="height: 60%;" >
						<table style="margin: 20px auto;">
							<tr><td>请领药品：</td><td><input id="ipt-drum" type="text" class="form-control form-lf-time" placeholder="药名" disabled="disabled"></td></tr>
							<tr><td>请领数量：</td><td><input id="number" type="text" class="form-control form-lf-time" placeholder="报损数量"></td></tr>
						</table>
					</div>
					<div style="text-align: center;">
						<button onclick="applty()" class="btn btn-primary " type="button"><i class="icon icon-plus"></i>申请</button>
						<button onclick="divbsback()" class="btn btn-primary " type="button"><i class="icon icon-share-alt"></i> 返 回</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="panel panel-primary div-box" >
		<div class="panel-heading">
			药品低限报警
		</div>
	
		<div class="panel-body grid-container">		
				<div class="btn-fy" style="width: 10%;">
					<button onclick="Refresh()" class="btn btn-warning  top-btn" type="button"><i class="icon icon-spin icon-refresh"></i> 刷 新</button>
				</div>
				<div class="input-control has-icon-left btn-fy">
				  	<input id="drugname" type="text" class="form-control" style="padding-left: 90px">
  					<label for="drugname" class="input-control-label-left" style="margin-left: 20px;width: auto;">药品名称：</label>
  					<label for="drugname" class="input-control-icon-left"><i class="icon icon-user "></i></label>
				</div>
				<div  class="btn-fy" style="width: 10%;">
					<button onclick="seldrug()" class="btn btn-info  top-btn" type="button"><i class="icon icon-search"></i> 搜 索</button>
				</div>
				<div  class="btn-fy" style="width: 10%;">
						<button onclick="clearon()" class="btn btn-danger  top-btn" type="button"><i class="icon icon-trash"></i> 清 空</button>
				</div>
		</div>
		<div class="container-fluid" style="height: 80%;">
			<table class="table table-hover table-bordered" id="g-table">
				<thead>
					<tr>
						<th>药品名称</th>
						<th>库存数量</th>
						<th>操作</th>
					</tr>
				</thead> 
			</table>
		</div>
		<div class="div_page div-button">
			<ul class="pager pager-pills">
				<li class="previous "><a  onclick="upys()">上一页</a></li>
				<li><span id="sp-ym"></span></li>
				<li class="next"><a  onclick="doys()">下一页</a></li>
			</ul>
		</div>
		<script src="../3rd/zui/lib/jquery/jquery.js"></script>
		<script src="../3rd/zui/js/zui.min.js"></script>
		<script src="../3rd/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	</div>
	</body>
<script type="text/javascript">
	//清空输入框
	function clearon(){
		document.getElementById("drugname").value= '';
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
		var dname = document.getElementById("drugname").value
		$.ajax({
			url : "../drugLowLimitTable.action",
			type : "post",
			data : "dname="+dname+"&ks="+ks+"&jt="+jt,
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
					td1.innerText = data[i].dname;
					td1.setAttribute("Name", data[i].did)

					var td2 = document.createElement('td');
					td2.innerText = data[i].inventoryCount

					var td3 = document.createElement('td');
					td3.innerHTML = '<a>请领</a>';
					
					
					tr.appendChild(td1);
					tr.appendChild(td2);
					tr.appendChild(td3);
					document.getElementById('g-table').appendChild(tr);
				}
				count();
			},
			error : function(e) {
				new $.zui.Messager('提示消息：药品低限表获取失败', {
					type: 'danger' // 定义颜色主题
					}).show();
			}
		})
	}
	function count(){
		var dname = document.getElementById("drugname").value
		$.ajax({
			url : "../drugLowLimitTableCount.action",
			type : "post",
			data : "dname="+dname,
			dataType : "json",
			success : function(data) {
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
			},
			error : function(e) {
				new $.zui.Messager('提示消息：药品低限表页码获取失败', {
					type: 'danger' // 定义颜色主题
					}).show();
			}
		})
	}
	table();
	function upys(){
		if(ks>1){
			ks--;	
			table();
		}else{
			new $.zui.Messager('提示消息：已经是首页了', {
				type: 'success' // 定义颜色主题
				}).show();
		}
	}
	function doys(){
		if(ks<ys){
			ks++;	
			table();
		}else{
			new $.zui.Messager('提示消息：已经是尾页了', {
				type: 'success' // 定义颜色主题
				}).show();
		}
	}
	function Refresh(){
		location.reload();
	}
	function seldrug(){
		name = document.getElementById("drugname").value
		ks=1;
		table();
	}
	//取消显示添加用户
	function divbsback(){
		$('#div-applty').modal('toggle', 'center')
	}
	//显示编辑用户
	function divbsblock(){
		$('#div-applty').modal('show', 'fit')
	}
	document.getElementById("g-table").onclick = function(e) {
		
		var temp = e.target;
		if(temp.innerText=='请领'){
			var drugname = temp.parentElement.parentElement.children[0].innerText
			var drugid = temp.parentElement.parentElement.parentElement.children[0].getAttribute('Name')
			document.getElementById("ipt-drum").value = drugname
			document.getElementById("ipt-drum").setAttribute('Name',drugid);
			divbsblock()
		}
	}
	function applty(){
		var r = /^\+?[1-9][0-9]*$/;
		var batch = document.getElementById("ipt-drum").getAttribute('Name')
		var number = document.getElementById("number").value
		var drumname = document.getElementById("ipt-drum").value
		if(number==""){
			new $.zui.Messager('提示消息：请输入请领数量', {
				type: 'success' // 定义颜色主题
				}).show();
		}else{
			if(r.test(number)){
				$.ajax({
					url : "../drugapplty1.action",
					type : "post",
					data : "batch="+batch+"&number="+number+"&drumname="+drumname,
					dataType : "json",
					success : function(data) {
						if(data == 'yes'){
							new $.zui.Messager('提示消息：请领成功', {
								type: 'success' // 定义颜色主题
								}).show();
						}else{
							new $.zui.Messager('提示消息：请领失败', {
								type: 'success' // 定义颜色主题
								}).show();
						}
					},
					error : function(e) {
						new $.zui.Messager('提示消息：请领申请失败', {
							type: 'danger' // 定义颜色主题
							}).show();
					}
				})
			}else{
				new $.zui.Messager('提示消息：请输入一个正整数', {
					type: 'danger' // 定义颜色主题
					}).show();
			}
		}
		document.getElementById("number").value = ""
		divbsback();
	}
</script>
</html>