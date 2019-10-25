<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="pharmacy.common.model.UserInfo"%>
<!DOCTYPE html>
<html>
    <%
    UserInfo users=(UserInfo)request.getSession().getAttribute("login");
    %>
	<head>
		<meta charset="utf-8">
		<title>配伍禁忌</title>
		<link rel="stylesheet" type="text/css" href="../css/drugset.css" />
		<link rel="stylesheet" href="../3rd/zui/css/zui.min.css" />
		<link href="../3rd/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
	</head>
	<body>
	<div style="padding: 5px">
		<!-- 禁忌药物界面 -->
		<div class="modal fade" id="userModal">
			<div class="modal-dialog" style="width: 300px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true" href="javascript:void(0);"onclick="removeshop()">×</span><span class="sr-only">关闭</span></button>
						<h4 class="modal-title"><i class="icon icon-edit"></i>禁忌药物</h4>
					</div>
					<div class="panel-body">
						<table class="table table-hover table-bordered" id="stop-table">
							<thead>
							<tr align="center">					
								<th>禁忌药物</th>	
								<th>操作</th>						
							</tr>
							<thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<!-- 配置药物禁忌界面 -->
		<div class="modal fade" id="editModal">
			<div class="modal-dialog" style="width: 300px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true" href="javascript:void(0);"onclick="removeincomp()">×</span><span class="sr-only">关闭</span></button>
						<h4 class="modal-title"><i class="icon icon-edit"></i>药品禁忌配置</h4>
					</div>
					<div class="panel-body">
						<div class="drug-list1">
							<input id="text-dname" type="text" class="form-control" disabled="disabled" placeholder="">
							<!-- <select name="" class="form-control" id="sel-incomp" style="width: 100%;height: 32px;">
								<option value="" disabled selected>选择配置禁忌药品</option>
							</select> -->
							<table class="table table-hover table-bordered" id="incomp-table">
								<thead>
								<tr align="center">					
									<th>选择</th>	
									<th>禁忌药物</th>						
								</tr>
								<thead>
								<tbody>
								</tbody>
							</table>							
						</div>
						<div id="" align="center">
							<button class="btn btn-primary" id="btn-incomp" type="button" style="width: 100px;height: 30px;"><i class="icon icon-save"></i>
								保 存</button>
							<button class="btn btn-primary" id="exit-incomp" type="button" style="width: 100px;height: 30px;"><i class="icon icon-reply"></i>
								返 回</button>
						</div>
					</div>

				</div>
			</div>
		</div>
		<!-- 头部标题 -->
		<div class="panel panel-primary panelMargin">
			<div class="panel-heading">
				配伍禁忌
			</div>
			<div id="text-uname" style="display: none;"><%=users.getUname()%></div>
			<!-- 主界面 -->
			<div class="panel-body">
				 <div class="container-fluid div_seek">
	                    <div class="row">
	                        <div class="col-lg-1 col-md-2 col-sm-6"><button class="btn btn-warning" type="button" id="btn-refresh" data-toggle="tooltip" title="刷新"><i
	                                 class="icon icon-refresh"></i>刷新</button></div>
	                        <div class="col-lg-3 col-md-3 col-sm-6">
	                            <input type="text" class="form-control ng-pristine ng-valid ng-touched" id="text-drug"  placeholder="请输入药品名">
	                            <label for="mcmStartTime" class="input-control-label-left text-right">药品名称:</label>
	                            <label for="inputAccountExample1" class="input-control-icon-left"><i class="icon icon-file-text"></i></label>
	                        </div>
	                        <div class="col-lg-1 col-md-2 col-sm-6"><button class="btn btn-info" type="button" id="btn-search" data-toggle="tooltip" title="搜索"><i
	                                 class="icon icon-search"></i>搜索</button></div>
	                        <div class="col-lg-1 col-md-2 col-sm-6"><button class="btn btn-danger" id="btn-null" type="button" data-toggle="tooltip"
	                             title="清空"><i class="icon icon-trash"></i>清空</button></div>
	                    </div>
	               </div>
			
			
				<!-- <div class="panel-add">
					<button class="btn btn-warning" id="btn-refresh" type="button" style="width: 80px;"><i class="icon icon-spin icon-refresh"></i> 刷 新</button>
				</div>
				<div class="col-lg-5 col-md-5 col-sm-6">
					<input id="text-drug" type="text" style="padding-left:80px" class="form-control ng-pristine ng-valid ng-touched">
					<label for="mcmStartTime" style="width:100px" class="input-control-label-left text-right">药品名称：</label>
				</div>
				<div id="">
					<button class="btn btn-success" id="btn-search" type="button" style="width: 80px;"><i class="icon icon-search"></i> 搜 索</button>
					<button class="btn btn-danger" id="btn-null" type="button" style="width: 80px;"><i class="icon icon-trash"></i> 清 空</button>
				</div> -->
				<table class="table table-hover table-bordered" id="table-edit">
					<thead>
					<tr align="center">
						<th>序号</th>
						<th>药品名称</th>
						<th>操作</th>
					</tr>
					<thead>
					<tbody>
					</tbody>
				</table>
				<div id="" align="center">
						<ul class="pager pager-pills">
							<li class="previous "><a id="up-page" >« 上一页</a></li>
							<li><span id="lab_page">				
							<span id="nowpage"> 1 </span>
							<span id="midpage"> / </span>
							<span id="allpage"> 1 </span></span></li>
							<li class="next"><a id="down-page" >下一页
							»</a></li>
						</ul>
				</div>
			</div>
		</div>
		</div>
		<script src="../3rd/zui/lib/jquery/jquery.js"></script>
 		<script src="../3rd//layer/layer.js"></script>
		<script src="../3rd/zui/js/zui.min.js"></script>
		<script src="../3rd/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	</body>
</html>
<script type="text/javascript">

	//刷新界面
	document.getElementById('btn-refresh').onclick = function() {	
		location.reload();	
	}
	
	//清空
	document.getElementById('btn-null').onclick = function() {
		document.getElementById('text-drug').value = "";
	}
	//复位
	function removeincomp(){
		document.getElementById('sel-incomp').options[0].selected=true;
	}
	
	//关闭禁忌配置界面
	document.getElementById('exit-incomp').onclick = function() {
		$('#editModal').modal('hide', 'fit')
		removeincomp();
	} 

	var table = document.getElementById('table-edit');
	var stable = document.getElementById('stop-table');
	var intable = document.getElementById('incomp-table');
	
	var start = 0;
	var end = 5;
	var nowpage =1;
	var allpage =1;	
	var count=0;
	var drugname="";
	
	function init(){
		
		$.ajax({		
			url:"../dodruginfinit.action",
			type:"post",
			data:"start="+start+"&end="+end+"&drugname="+drugname,
			dataType:"json",
			success:function(datas){
		
				var data=datas.list;
				count=datas.count;	
				if(count<5) {
					allpage = 1;
				}else {
					allpage = count % 5 == 0?(count/5):(count/5+1);
				}
				document.getElementById('allpage').innerText=Math.floor(allpage);
 		 		var trs = document.getElementsByClassName('drug-tr');
	 			//清除数据
				for(var i = trs.length-1;i>=0;i--){
					trs[0].remove();
				} 			
				for (var i = 0; i < data.length; i++) {
					
					var tr = document.createElement('tr');
					
					tr.classList.add("drug-tr");
					
					var td1=document.createElement('td');
					td1.innerText=i+1;
					
					var td2=document.createElement('td');
					td2.innerText=data[i].drugName;
												
					var td3=document.createElement('td');
					td3.innerHTML='<button class="btn btn-link"  drugid='+data[i].drugId+' type="button"><i class="icon icon-edit"></i>查看禁忌药物</button> <button class="btn btn-link"  drugid='+data[i].drugId+' type="button"><i class="icon icon-edit"></i>配置禁忌</button>'
									
					tr.appendChild(td1);
					tr.appendChild(td2);
					tr.appendChild(td3);
					
					table.appendChild(tr);	 		
				 }  
			},		
				error:function(e){
				    new $.zui.Messager('初始化表请求失败！', {
	 			        icon: 'bell' // 定义消息图标
	 			    }).show();										
				}
		})
	}
			
	init(); 
	
	//下一页
	document.getElementById('down-page').onclick=function(){
		
		if(start+end>=count){
		    new $.zui.Messager('已经是最后一页了！', {
			        icon: 'bell' // 定义消息图标
			    }).show();
		}else{
			start=start+end;
			nowpage = nowpage+1;
			document.getElementById('nowpage').innerText=nowpage;
			init();
		}
	}
	//上一页
	document.getElementById('up-page').onclick=function(){
		
		if(start==0){
		    new $.zui.Messager('已经是第一页了！', {
			        icon: 'bell' // 定义消息图标
			    }).show();			
			
		}else{
			start=start-end;
			nowpage = nowpage-1;
			document.getElementById('nowpage').innerText=nowpage;
			init();	
		}
	}
	//搜索
	document.getElementById('btn-search').onclick=function(){
	
		drugname=document.getElementById('text-drug').value;	

		start = 0;
		end = 5;
		nowpage =1; 
		document.getElementById('nowpage').innerText=1;
		init();
				
	}

	var delname1="";
	
	table.onclick = function(e) {
		el = e.target;		
		var drugid=el.getAttribute('drugId');

		if (el.innerText == "查看禁忌药物") {
			$('#userModal').modal({
				backdrop: 'true',
				show: true
			})
			delname1=el.parentElement.parentElement.children[1].innerText;
			$.ajax({		
				url:"../dodrugincomp.action",
				type:"post",
				data:"drugid="+drugid,
				dataType:"json",
				success:function(datas){
			
					var data=datas.list;

	 		 		var trs = document.getElementsByClassName('drugs-tr');
		 			//清除数据
					for(var i = trs.length-1;i>=0;i--){
						trs[0].remove();
					} 			
					for (var i = 0; i < data.length; i++) {
						
						var tr = document.createElement('tr');
						
						tr.classList.add("drugs-tr");
						
						var td1=document.createElement('td');
						td1.innerText=data[i];
						
						var td2=document.createElement('td');
						td2.innerHTML='<button class="btn btn-link"  delname='+data[i]+' type="button"><i class="icon icon-trash"></i>删除</button>'
										
						tr.appendChild(td1);
						tr.appendChild(td2);
									
						stable.appendChild(tr);	 		
					 }  
				},		
					error:function(e){
					    new $.zui.Messager('初始化禁忌药物表请求失败！', {
		 			        icon: 'bell' // 定义消息图标
		 			    }).show();										
					}
			})
			
		}else if(el.innerText == "配置禁忌"){
			
			$('#editModal').modal({
				backdrop: 'static',
				show: true
			})
			var dname = el.parentElement.parentElement.children[1].innerText;
			document.getElementById('text-dname').value=dname;		
			$.ajax({		
				url:"../dooutincomp.action",
				type:"post",
				data:"dname="+dname,
				dataType:"json",
				success:function(datas){	
					
					var data=datas.list;
					console.log(datas);
	 		 		var trs = document.getElementsByClassName('incomp-tr');
		 			//清除数据
					for(var i = trs.length-1;i>=0;i--){
						trs[0].remove();
					} 			
					for (var i = 0; i < data.length; i++) {
						
						var tr = document.createElement('tr');
						
						tr.classList.add("incomp-tr");
						
						var td1=document.createElement('td');						
						td1.innerHTML='<input id="text-search" drugname='+data[i].drugName+' type="checkbox" class="check-incomp" >'
									
						var td2=document.createElement('td');
						td2.innerText=data[i].drugName;
							
						tr.appendChild(td1);
						tr.appendChild(td2);
									
						intable.appendChild(tr);	 		
					 } 
				
		
				},
				error:function(e){
					    new $.zui.Messager('未配置禁忌药物展示选择请求失败！', {
		 			        icon: 'bell' // 定义消息图标
		 			    }).show();	
				}
				
			})
			//配置禁忌药品保存
			document.getElementById('btn-incomp').onclick=function(){
				var dname1=new Array();			
				var arr=document.getElementsByClassName('check-incomp');
				var dname2=new Array();
				for(var i=0;i<arr.length;i++){
					if(arr[i].checked==true){
						dname1.push(dname);
						dname2.push(arr[i].getAttribute('drugname'));
					}
				}
				var arr1=JSON.stringify(dname1);
				var arr2=JSON.stringify(dname2);
				
				if(dname2==""){
				    new $.zui.Messager('请选择要配置的禁忌药品！', {
	 			        icon: 'bell' // 定义消息图标
	 			    }).show();
				}else{
					$.ajax({		
						url:"../doincomset.action",
						type:"post",
						data:"dname1="+arr1+"&dname2="+arr2,
						dataType:"json",
						success:function(data){
							if(data=="yes"){
							    new $.zui.Messager('药物禁忌配置成功！', {
				 			        icon: 'bell' // 定义消息图标
				 			    }).show();
							    $('#editModal').modal('hide', 'fit')
							}else if(data=="no"){
							    new $.zui.Messager('药物禁忌配置失败！', {
				 			        icon: 'bell' // 定义消息图标
				 			    }).show(); 
							}					
						},
						error:function(e){
						    new $.zui.Messager('禁忌药物配置请求失败！', {
			 			        icon: 'bell' // 定义消息图标
			 			    }).show();
						}			
					})	
				}			
			}
		}
	}

		//删除禁忌药品
		stable.onclick = function(e) {
			el = e.target;		
			var delname2=el.getAttribute('delname');
	
			if (el.innerText == "删除") {			
				layer.confirm('是否确定删除', {
					  btn: ['是','否'] //按钮
					}, function(){			  
						$.ajax({		
						url:"../dodelincomp.action",
						type:"post",
						data:"delname1="+delname1+"&delname2="+delname2,
						dataType:"json",
						success:function(data){
							if(data=="yes"){
								layer.msg('禁忌药品删除成功！', {icon: 1});
								 $('#userModal').modal('hide', 'fit')
							}else if(data=="no"){
								 layer.msg('禁忌药品删除失败', {icon: 1});
							}					
						},
						error:function(e){
							 layer.msg('禁忌药品删除请求失败', {icon: 1});
						}			
					}) 	 
									
					}, function(){
				
					});	
			}
		 
		}

	 

	
	
	
</script>
