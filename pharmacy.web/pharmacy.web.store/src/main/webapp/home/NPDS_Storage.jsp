<%@page import="pharmacy.common.model.UserInfo"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
		<title>麻醉精神药品库存</title>
		<link rel="stylesheet" href="../3rd/zui/css/zui.min.css">
		<link href="../3rd/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
		<style type="text/css">
			html,body{
				width: 100%;
				height: 100%;
				padding: 2.5px;
			}
			.myPanel {
				width: 100%;
				height: 100%;
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
				width: 50%;
			}
			td{
				text-align: center;
			}
		</style>
	</head>
	<body>
	<div class="panel panel-primary myPanel">
		<div class="panel-heading">
			<h4>麻醉精神药品库存表</h4>
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
				<!-- <div class="input-control has-icon-left btn-fy">
					<input id="stime" type="text" class="form-control form-date time form-lf-time"  style="padding-left: 90px" placeholder="请选择时间">  
  					<label for="stime" class="input-control-label-left" style="margin-left: 20px;width: auto;">开始时间：</label>
  					<label for="stime" class="input-control-icon-left"><i class="icon icon-user "></i></label>
				</div>
				<div class="input-control has-icon-left btn-fy">
					<input id="etime" type="text" class="form-control form-date time form-lf-time"  style="padding-left: 90px" placeholder="请选择时间">  
  					<label for="etime" class="input-control-label-left" style="margin-left: 20px;width: auto;">结束时间：</label>
  					<label for="etime" class="input-control-icon-left"><i class="icon icon-user "></i></label>
				</div> -->
				<div  class="btn-fy" style="width: 10%;">
					<button onclick="seldrug()" class="btn btn-info  top-btn" type="button"><i class="icon icon-search"></i> 搜 索</button>
				</div>
				<div  class="btn-fy" style="width: 10%;">
						<button onclick="clearon()" class="btn btn-danger  top-btn" type="button"><i class="icon icon-trash"></i> 清 空</button>
				</div>
		</div>
			<div class="table-responsive">
				<table class="table table-hover table-bordered" id="g-table">
					<thead>
						<tr>
							<th>药品名称</th>
							<th>数量</th>
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
		</div>
		<script src="../3rd/zui/lib/jquery/jquery.js"></script>
		<script src="../3rd/zui/js/zui.min.js"></script>
		<script src="../3rd/zui/lib/datetimepicker/datetimepicker.min.js"></script>
		<script src="../js/drugTime.js"></script>
	</body>
<script type="text/javascript">
	// 选择时间和日期
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
	var zs = 0;
	var ys = 1;
	var ks = 1;
	var jt = 5;
	var stime = ""
	var etime = "";
	var name = ""
	function table(){
		$.ajax({
			url : "../NpdsStorageTable.action",
			type : "post",
			data : "ks="+ks+"&jt="+jt+"&stime="+stime+"&etime="+etime+"&dname="+name,
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


					tr.appendChild(td1);
					tr.appendChild(td2);
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
			url : "../NpdsStorageTableCount.action",
			type : "post",
			data : "&stime="+stime+"&etime="+etime+"&dname="+name,
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
				new $.zui.Messager('提示消息：用户表格页码获取失败', {
					type: 'danger' // 定义颜色主题
					}).show();
			}
		})
	}
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
	function clearon(){
		document.getElementById("drugname").value= '';
	}
	function seldrug(){
		name = document.getElementById("drugname").value
			ks=1;
			table();
	}
</script>
</html>