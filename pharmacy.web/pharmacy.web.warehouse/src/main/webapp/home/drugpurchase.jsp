<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="pharmacy.common.model.UserInfo"%>
<!DOCTYPE html>
<html>
    <%
    UserInfo users=(UserInfo)request.getSession().getAttribute("login");
    %>
	<head>
		<meta charset="utf-8">
		<title>药品采购</title>
		<link rel="stylesheet" type="text/css" href="../css/drugset.css" />
		<link rel="stylesheet" href="../3rd/zui/css/zui.min.css" />
		<link href="../3rd/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
	</head>
	<body>
	<div style="padding: 5px">
		<!-- 编辑采购界面 -->
		<div class="modal fade" id="userModal">
			<div class="modal-dialog" style="width: 300px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true" href="javascript:void(0);"onclick="removeshop()">×</span><span class="sr-only">关闭</span></button>
						<h4 class="modal-title"><i class="icon icon-edit"></i>采购入库</h4>
					</div>
					<div class="panel-body">
						<div class="drug-list1">
						    <input id="drug-supply" type="text" class="form-control" placeholder="供应商">
							<input id="drug-shop" type="text" class="form-control" disabled="disabled">
							<input id="drug-amount" type="text" class="form-control" placeholder="采收数量">
							<select name="" class="form-control" id="sel-unit" style="width: 100%;height: 32px;">
								<option value="" disabled selected>药品单位</option>
							</select>
							<input type="text" class="form-control form-date" id="drug-time" autocomplete="off" placeholder="请选择药品有效日期">
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
		
		<div class="panel panel-primary panelMargin">
			<div class="panel-heading">
				药品采购
			</div>
			<div id="text-uname" style="display: none;"><%=users.getUname()%></div>
			<!-- 主界面 -->
			<div class="panel-body">
			
			
			  <div class="container-fluid div_seek">
                    <div class="row">
                        <div class="col-lg-1 col-md-2 col-sm-6"><button class="btn btn-warning" type="button"  id="btn-refresh" data-toggle="tooltip" title="刷新"><i
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
						<th>药品类型</th>
						<th>是否抗生素</th>
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
	// 仅选择日期
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
	//刷新界面
	document.getElementById('btn-refresh').onclick = function() {	
		location.reload();	
	}
		
	//清空采购界面内容
	function removeshop(){
		document.getElementById('drug-supply').value="";
		document.getElementById('drug-amount').value="";
		document.getElementById('drug-time').value="";
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
	//药品单位下拉框
	var selunit=document.getElementById('sel-unit');
	function unitinit(){
		$.ajax({		
			url:"../dounitinf.action",
			type:"post",		
			dataType:"json",
			success:function(datas){	
				
				var data=datas.list
				var ops = document.getElementsByClassName('unit-op');
				
				for(var i = ops.length-1;i>=0;i--){
					ops[0].remove();
				}  
				for (var i = 0; i < data.length; i++) {
															
					var op=document.createElement('option');						
					op.innerText=data[i];
					
					selunit.appendChild(op);
					
					op.classList.add("unit-op"); 					
				}
			},
			error:function(e){
				    new $.zui.Messager('下拉框药品单位请求失败！', {
	 			        icon: 'bell' // 定义消息图标
	 			    }).show();	
			}		
		})		
	}
	unitinit();

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
					if(data[i].antibiotics==1){
						td4.innerText="是";	
					}else{
						td4.innerText="否";	
					}								
					var td5=document.createElement('td');
					td5.innerHTML='<button class="btn btn-link" drugid='+data[i].drugId+' type="button"><i class="icon icon-shopping-cart"></i>采购入库</button> '
									
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
    //正则判断中文
    var judge1=/[\u4e00-\u9fa5]/;
    //英文
    var judge2=/[a-zA-Z]/;
    //数字
    var judge3=/[0-9]/
	//搜索
	document.getElementById('btn-search').onclick=function(){
	
		drugname=document.getElementById('text-drug').value.replace(/\s+/g,"");	

		start = 0;
		end = 5;
		nowpage =1; 
		document.getElementById('nowpage').innerText=1;
		init();			
	}

	table.onclick = function(e) {
		el = e.target;		
		var drugid=el.getAttribute('drugId');
		//打开采购入库界面
		if (el.innerText == "采购入库") {
			$('#userModal').modal({
				backdrop: 'static',
				show: true
			})
			var drugshop=el.parentElement.parentElement.children[1].innerText;
			document.getElementById('drug-shop').value="药品名称："+drugshop;
			//采购保存
			document.getElementById('shop-go').onclick = function() {
				var uname=document.getElementById('text-uname').innerText;
				var supply=document.getElementById('drug-supply').value.replace(/\s+/g,"");
				var amount=document.getElementById('drug-amount').value.replace(/\s+/g,"");
				var drugtime=document.getElementById('drug-time').value;
				
				var opd=document.getElementById('sel-unit').children;	
					for (var i = 0; i < opd.length; i++) {
						if(opd[i].selected==true){	
							var unit=opd[i].innerText;	
						}
			 		}
				if(supply==""||amount==""||drugtime==""){
					new $.zui.Messager("采购信息请输入完整！", {
	 			        icon: 'bell' // 定义消息图标
	 			    }).show();
				}else if(unit=="药品单位"){
					new $.zui.Messager("请选择药品单位！", {
	 			        icon: 'bell' // 定义消息图标
	 			    }).show();
				}else if(judge3.test(amount)==false){
					new $.zui.Messager("数量请输入数字！", {
	 			        icon: 'bell' // 定义消息图标
	 			    }).show(); 
				}else if(amount<=0){
					new $.zui.Messager("数量请输入正数！", {
	 			        icon: 'bell' // 定义消息图标
	 			    }).show(); 
		      	}else{
	 	 			$.ajax({		
						url:"../dodrugshop.action",
						type:"post",
						data:"drugid="+drugid+"&supply="+supply+"&amount="+amount
						+"&drugtime="+drugtime+"&uname="+uname+"&unit="+unit,
						dataType:"json",
						success:function(data){
							if(data=="empty"){
								new $.zui.Messager("输入不能为空", {
				 			        icon: 'bell' // 定义消息图标
				 			    }).show();
							}else if(data=="yes"){
							    new $.zui.Messager("药品采购成功", {
				 			        icon: 'bell' // 定义消息图标
				 			    }).show();
								$('#userModal').modal('hide', 'fit')
								removeshop();
							}else if(data=="no"){
							    new $.zui.Messager("药品采购失败", {
				 			        icon: 'bell' // 定义消息图标
				 			    }).show();
							}					
						},
						error:function(e){
							    new $.zui.Messager('药品采购请求失败！', {
				 			        icon: 'bell' // 定义消息图标
				 			    }).show();	
						}			
					}) 									
				}		 	
			}	
		}
	}

	 
	 
	
	 
	
	
	
</script>
