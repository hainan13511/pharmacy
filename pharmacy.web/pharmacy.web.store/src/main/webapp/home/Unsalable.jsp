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
		<title>药品滞销警告</title>
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
				width: 20%;
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
			.lable-text{
				margin-left: 30px;
				width: 70px;
			}
			.inp-text{
				width: 300px;
				padding-left: 100px;
			}
		</style>
	</head>
	<body>
	<div class="modal fade" id="div-Return">
			<div class="modal-dialog">
				<div class="modal-content">
					<div style="width: 100%; height: 500px;">
						<div class="panel panel-primary">
							<div class="panel-heading">
								药品报损
							</div>
						
						<div class="" style="height: 60%;" >
							<table style="margin: 20px auto;">
								<tr><td>药名：</td><td><input id="ipt-drum" type="text" class="form-control form-lf-time" placeholder="药名" disabled="disabled"></td></tr>
								<tr><td>退库数量：</td><td><input id="number" type="text" class="form-control form-lf-time" placeholder="退库数量" disabled="disabled"></td></tr>
								<tr><td>退库原因：</td><td><input id="str" value="滞销" type="text" class="form-control form-lf-time" placeholder="退库原因" disabled="disabled"></td></tr>
							</table>
						</div>
						<div style="text-align: center;">
							<button onclick="drugbs()" class="btn btn-primary " type="button"><i class="icon icon-plus"></i>报损</button>
							<button onclick="divbsback()" class="btn btn-primary " type="button"><i class="icon icon-share-alt"></i> 返 回</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>	
	<div class="panel panel-primary div-box" >
		<div class="panel-heading">
			药品滞销警告
		</div>
	
		<div class="panel-body grid-container">		
				<div class="btn-fy" style="width: 10%;">
					<button onclick="Refresh()" class="btn btn-warning  top-btn" type="button"><i class="icon icon-spin icon-refresh"></i> 刷 新</button>
				</div>
				<!-- <div class="btn-fy" style="width: 15%;">
					<input class="form-control ng-pristine ng-valid ng-touched" id="drugname" type="text" class="form-control form-lf-time" placeholder="请输入药品名">
					<label for="mcmStartTime" class="input-control-label-left "></label>
                    <label for="inputAccountExample1" class="input-control-icon-left"><i class="icon icon-file-text"></i></label>
				</div> -->
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
						<th>入库日期</th>
						<th>到期时间</th>
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
		<script src="../js/drugTime.js"></script>
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
			url : "../drugUnsalableTable.action",
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
					td1.setAttribute("Name", "" + data[i].did + "")

					var td2 = document.createElement('td');
					td2.innerText = data[i].number
					td2.setAttribute("Name", data[i].dwdid)
					
					var td3 = document.createElement('td');
					var time = new Date(data[i].ptime).Format('yyyy-MM-dd hh:mm:ss')
                    td3.innerText = time;
					
					var td4 = document.createElement('td');
					var time = new Date(data[i].etime).Format('yyyy-MM-dd hh:mm:ss')
                    td4.innerText = time;
					
					var td5 = document.createElement('td');
					td5.innerHTML = '<a>退库</a>';
					
					
					tr.appendChild(td1);
					tr.appendChild(td2);
					tr.appendChild(td3);
					tr.appendChild(td4);
					tr.appendChild(td5);
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
			url : "../drugUnsalableTableCount.action",
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
		$('#div-Return').modal('toggle', 'center')
	}
	//显示编辑用户
	function divbsblock(){
		$('#div-Return').modal('show', 'fit')
	}
	document.getElementById("g-table").onclick = function(e) {
		
		var temp = e.target;
		if(temp.innerText=='退库'){
			var drugname = temp.parentElement.parentElement.children[0].innerText
			var number = temp.parentElement.parentElement.parentElement.children[1].innerText
			var houseid = temp.parentElement.parentElement.parentElement.children[0].getAttribute('Name')
			document.getElementById("ipt-drum").value = drugname
			document.getElementById("number").value = number
			document.getElementById("ipt-drum").setAttribute('Name',drugid);
			divbsblock()
		}
	}
	function drugbs(dwdid){
		var r = /^\+?[1-9][0-9]*$/;
		var number = document.getElementById("number").value
		var str = document.getElementById("str").value
		var drumname = document.getElementById("ipt-drum").value
		var houseid = document.getElementById("ipt-drum").getAttribute('Name');
		if(number!=null&&str!=null){
			if(r.test(number)){
				$.ajax({
					url:"../modifyInventory1.action",
					type:"post",
					data:"houseid="+houseid+"&number="+number+"&dwdid="+dwdid+"&drumname="+drumname,
					dataType:"json",
					success:function(data){	
						console.log(data);
						if(data.result == "yes"){
							new $.zui.Messager('退库成功', {
							   icon: 'icon-check-circle',
							    type: 'success' // 定义颜色主题
							}).show();
							$('#userModal').modal('hide', 'fit')
							init();
						}else if(data.result == "insufficient"){
							new $.zui.Messager('库存大于现有库存请核对库存！', {
								   icon: 'icon-exclamation-sign',
									type: 'warning' // 定义颜色主题
							}).show();
						}else{
							new $.zui.Messager('退库失败！', {
								   icon: 'icon-exclamation-sign',
									type: 'warning' // 定义颜色主题
							}).show();
						}
					},error:function(e){
						console.log(e);
						new $.zui.Messager('服务器繁忙。', {
							   icon: 'icon-exclamation-sign',
								type: 'warning' // 定义颜色主题
						}).show();
					}
				})
			}else{
				new $.zui.Messager('提示消息：请输入一个正整数', {
					type: 'danger' // 定义颜色主题
					}).show();
			}
		}else{
			new $.zui.Messager('提示消息：请输入退库数量和退库原因', {
				type: 'danger' // 定义颜色主题
				}).show();
		}	
		document.getElementById("number").value = ""
		divbsback();
			
	}
</script>
</html>