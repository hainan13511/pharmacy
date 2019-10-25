<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="pharmacy.common.model.UserInfo"%>
<!DOCTYPE html>
<html>
    <%
    UserInfo users=(UserInfo)request.getSession().getAttribute("login");
    %>
	<head>
		<meta charset="utf-8">
		<title>医保药品核对</title>
		<link rel="stylesheet" type="text/css" href="../css/drugset.css" />
		<link rel="stylesheet" href="../3rd/zui/css/zui.min.css" />
		<link href="../3rd/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
	</head>
	<body>
	<div style="padding: 5px">
		<!-- 编辑医保药品界面 -->
		<div class="modal fade" id="userModal">
			<div class="modal-dialog" style="width: 300px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true" href="javascript:void(0);"onclick="removeshop()">×</span><span class="sr-only">关闭</span></button>
						<h4 class="modal-title"><i class="icon icon-edit"></i>医保编码配置</h4>
					</div>
					<div class="panel-body">
						<div class="drug-list1">
							<input id="drug-shop" type="text" class="form-control" disabled="disabled">
							<input id="drug-insurance" type="text" class="form-control" placeholder="医保编码">						
						</div>
						<div id="" align="center">
							<button class="btn btn-primary" id="shop-go" type="button" style="width: 100px;height: 30px;"><i class="icon icon-save"></i>
								保 存</button>
							<button class="btn btn-primary" id="shop-exit" type="button" style="width: 100px;height: 30px;"><i class="icon icon-reply"></i>
								返 回</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 头部标题 -->
		<div class="panel panel-primary">
			<div class="panel-heading">
				医保药品核对
			</div>
			<div id="text-uname" style="display: none;"><%=users.getUname()%></div>
		<!-- 主界面 -->
		<div class="panel-body">
			<div class="panel-add">
				<button class="btn btn-warning" id="btn-refresh" type="button" style="width: 80px;"><i class="icon icon-refresh"></i> 刷 新</button>
			</div>
			<div class="col-lg-5 col-md-5 col-sm-6">
				<input id="text-drug" type="text" style="padding-left:80px" class="form-control ng-pristine ng-valid ng-touched">
				<label for="mcmStartTime" style="width:100px" class="input-control-label-left text-right">药品名称：</label>
			</div>
			<div id="">
				<button class="btn btn-info" id="btn-search" type="button" style="width: 80px;"><i class="icon icon-search"></i> 搜 索</button>
				<button class="btn btn-danger" id="btn-null" type="button" style="width: 80px;"><i class="icon icon-trash"></i> 清 空</button>
			</div>
			<table class="table table-hover table-bordered" id="table-edit">
				<thead>
				<tr align="center">
					<th>序号</th>
					<th>药品名称</th>
					<th>药品类型</th>
					<th>医保编码</th>
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
		
	//清空医保配置界面内容
	function removeshop(){
		document.getElementById('drug-insurance').value="";
	}
	
	//清空
	document.getElementById('btn-null').onclick = function() {
		document.getElementById('text-drug').value = "";
	}
	//关闭采购界面
	document.getElementById('shop-exit').onclick = function() {
		$('#userModal').modal('hide', 'fit')
		removeshop();
	}

	var table = document.getElementById('table-edit');
	
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
					td3.innerText=data[i].dtname;
					
					var td4=document.createElement('td');
					var td5=document.createElement('td');
					if(data[i].insurValue==undefined){
						td4.innerText='不支持医保';
						td5.innerHTML='<button class="btn btn-link" drugid='+data[i].drugId+' type="button"><i class="icon icon-shopping-cart"></i>医保编码配置</button> '
					}else{
						td4.innerText=data[i].insurValue;
						td5.innerHTML='<button class="btn btn-link" drugid='+data[i].drugId+' type="button"><i class="icon icon-shopping-cart"></i>医保编码配置</button> <button class="btn btn-link" drugid='+data[i].drugId+' type="button"><i class="icon icon-trash"></i>取消医保</button>'
					}
														
					tr.appendChild(td1);
					tr.appendChild(td2);
					tr.appendChild(td3);
					tr.appendChild(td4);
					tr.appendChild(td5);
					
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
    //正则判断中文
    var judge1=/[\u4e00-\u9fa5]/;
	
	table.onclick = function(e) {
		el = e.target;		
		var drugid=el.getAttribute('drugId');
		//打开医保配置设置界面
		if (el.innerText == "医保编码配置") {
			$('#userModal').modal({
				backdrop: 'static',
				show: true
			})
			var drugshop=el.parentElement.parentElement.children[1].innerText;
			document.getElementById('drug-shop').value="药品名称："+drugshop;
			//医保配置
			document.getElementById('shop-go').onclick = function() {
				var uname=document.getElementById('text-uname').innerText;
				var insurance=document.getElementById('drug-insurance').value.replace(/\s+/g,"");
				if(insurance==""){
				    new $.zui.Messager("输入不能为空", {
	 			        icon: 'bell' // 定义消息图标
	 			    }).show();
				}else if(judge1.test(insurance)==true){
				    new $.zui.Messager("医保编码格式输入错误", {
	 			        icon: 'bell' // 定义消息图标
	 			    }).show();
				}else{
	 	 			$.ajax({		
						url:"../doinsuranceset.action",
						type:"post",
						data:"drugid="+drugid+"&insurance="+insurance,
						dataType:"json",
						success:function(data){
							if(data=="yes"){
							    new $.zui.Messager("药品医保编码配置成功", {
				 			        icon: 'bell' // 定义消息图标
				 			    }).show();
							    init();
								$('#userModal').modal('hide', 'fit')
								removeshop();					
							}else if(data=="no"){
							    new $.zui.Messager("药品医保编码配置失败", {
				 			        icon: 'bell' // 定义消息图标
				 			    }).show();
							}					
						},
						error:function(e){
							    new $.zui.Messager('药品医保编码配置请求失败！', {
				 			        icon: 'bell' // 定义消息图标
				 			    }).show();	
						}			
					})			
				}				
			}	
		}else if(el.innerText == "取消医保"){
			var insur="";
			//询问框
			layer.confirm('是否确定取消医保', {
			  btn: ['是','否'] //按钮
			}, function(){			  
				$.ajax({		
				url:"../doinsuranceset.action",
				type:"post",
				data:"drugid="+drugid+"&insurance="+insur,
				dataType:"json",
				success:function(data){
					if(data=="yes"){
						layer.msg('药品医保取消成功！', {icon: 1});
					    init();
					}else if(data=="no"){
						 layer.msg('药品医保取消失败！', {icon: 1});
					}					
				},
				error:function(e){
					 layer.msg('药品医保取消请求失败！', {icon: 1});
				}			
			}) 	 
							
			}, function(){
		
			});	
		}
	}

	 
	 
	
	 
	
	
	
</script>
