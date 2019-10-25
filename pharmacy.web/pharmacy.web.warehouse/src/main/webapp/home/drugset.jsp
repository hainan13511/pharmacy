<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="pharmacy.common.model.UserInfo"%>
<!DOCTYPE html>
<html>
    <%
    UserInfo users=(UserInfo)request.getSession().getAttribute("login");
    %>
	<head>
		<meta charset="utf-8">
		<title>药品设置</title>
		<link rel="stylesheet" type="text/css" href="../css/drugset.css" />
		<link rel="stylesheet" href="../3rd/zui/css/zui.min.css" />
		<link href="../3rd/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
	</head>
	<body>
		<div>
		<!-- 新增药品界面 -->
		<div class="modal fade" id="myModal">
			<div class="modal-dialog" style="width: 300px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true" href="javascript:void(0);"
							 onclick="removealter()">×</span><span class="sr-only">关闭</span></button>
						<h4 class="modal-title"><i class="icon icon-edit"></i>设置药品</h4>
					</div>
					<div class="panel-body">
					<form action="" autocomplete="on">
						<div class="container-fluid">
							<div class="row row-list">
								<div class="col-md-12">
								<select name="" class="form-control" id="drugtype-frist" style="width: 100%;height: 32px;">
									<option value="" disabled selected>药品大类</option>
								</select>
								</div>
								<div class="col-md-12">
								<select name="" class="form-control" id="drugtype-second" style="width: 100%;height: 32px;">
									<option value="" disabled selected>药品小类</option>
								</select>
								</div>
								<!-- <input id="text-drugname" type="text" class="form-control" placeholder="商品名称"> -->
								<div class="col-md-12">
									<input id="text-drugname" type="text" style="padding-left:65px" class="form-control ng-pristine ng-valid ng-touched">
									<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">商品名称：</label>
								</div>
								<div class="col-md-12">
									<input id="text-chemicalname" type="text" style="padding-left:65px" class="form-control ng-pristine ng-valid ng-touched">
									<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">化学名称：</label>
								</div>
								<div class="col-md-12">
									<input id="text-commonly" type="text" style="padding-left:65px" class="form-control ng-pristine ng-valid ng-touched">
									<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">常用名称：</label>
								</div>
								<div class="col-md-12">
									<input id="text-specification" type="text" style="padding-left:55px" class="form-control ng-pristine ng-valid ng-touched">
									<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">规格：</label>
								</div>
								<div class="col-md-12">
									<input id="text-dosage" type="text" style="padding-left:55px" class="form-control ng-pristine ng-valid ng-touched">
									<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">剂型：</label>
								</div>
								<div class="col-md-12">
									<input id="text-formula" type="text" style="padding-left:65px" class="form-control ng-pristine ng-valid ng-touched">
									<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">换算公式：</label>
								</div>
								<div class="col-md-12">
									<input id="text-method" type="text" style="padding-left:65px" class="form-control ng-pristine ng-valid ng-touched">
									<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">使用方法：</label>
								</div>
								<div class="col-md-12">
									<input id="text-markup" type="text" style="padding-left:60px" class="form-control ng-pristine ng-valid ng-touched">
									<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">加成率：</label>
								</div>
								<div class="col-md-12">
									<input id="text-spell" type="text" disabled="disabled"  style="padding-left:60px" class="form-control ng-pristine ng-valid ng-touched">
									<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">拼音码：</label>
								</div>
								<div class="col-md-12">
									<input id="text-five" type="text" disabled="disabled"  style="padding-left:60px" class="form-control ng-pristine ng-valid ng-touched">
									<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">五笔码：</label>
								</div>
								<div class="col-md-12">
									<input id="text-invoice" type="text" style="padding-left:65px" class="form-control ng-pristine ng-valid ng-touched">
									<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">发票名称：</label>
								</div>
								<div class="col-md-12">
									是否抗生素: 是 <input type="radio" checked=true name="anti" id="" value="1" /> 否 <input type="radio" name="anti" id="" value="0" />
								</div>	
								<div class="col-md-12">
										<input id="text-dose" type="text" style="padding-left:60px" class="form-control ng-pristine ng-valid ng-touched">
										<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">次剂量：</label>
								</div>	
								<div class="col-md-12">
									<input id="text-numday" type="text" style="padding-left:60px" class="form-control ng-pristine ng-valid ng-touched">
									<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">日次数：</label>
								</div>
								<div class="col-md-12">
									<input id="text-dcost" type="text" style="padding-left:60px" class="form-control ng-pristine ng-valid ng-touched">
									<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">成本价：</label>
								</div>
								<div class="col-md-12">
									<input id="text-dsale" type="text" style="padding-left:60px" class="form-control ng-pristine ng-valid ng-touched">
									<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">销售价：</label>
								</div>																														
							</div>
						</div>
						<div id="" align="center">
							<button class="btn btn-primary" id="btn-drugset" type="button" style="width: 100px;height: 30px;"><i class="icon icon-save"></i>
								保 存</button>
							<button class="btn btn-primary" id="add-exit" type="button" style="width: 100px;height: 30px;"><i class="icon icon-reply"></i>
								返 回</button>
						</div>
						</form>
					</div>

				</div>
			</div>
		</div>
		<!-- 修改药品界面 -->
		<div class="modal fade" id="editModal">
			<div class="modal-dialog" style="width: 300px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true" href="javascript:void(0);"
							 onclick="removealter()">×</span><span class="sr-only">关闭</span></button>
						<h4 class="modal-title"><i class="icon icon-edit"></i>设置药品</h4>
					</div>
					<div class="panel-body">
											<div class="container-fluid">
							<div class="row row-list">
								<div class="col-md-12">
								<select name="" class="form-control" id="altertype-frist" style="width: 100%;height: 32px;">
									<option value="" disabled selected>药品大类</option>
								</select>
								</div>
								<div class="col-md-12">
								<select name="" class="form-control" id="altertype-second" style="width: 100%;height: 32px;">
									<option value="" disabled selected>药品小类</option>
								</select>
								</div>
								<!-- <input id="text-drugname" type="text" class="form-control" placeholder="商品名称"> -->
								<div class="col-md-12">
									<input id="alter-drugname" type="text" style="padding-left:65px" class="form-control ng-pristine ng-valid ng-touched">
									<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">商品名称：</label>
								</div>
								<div class="col-md-12">
									<input id="alter-chemicalname" type="text" style="padding-left:65px" class="form-control ng-pristine ng-valid ng-touched">
									<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">化学名称：</label>
								</div>
								<div class="col-md-12">
									<input id="alter-commonly" type="text" style="padding-left:65px" class="form-control ng-pristine ng-valid ng-touched">
									<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">常用名称：</label>
								</div>
								<div class="col-md-12">
									<input id="alter-specification" type="text" style="padding-left:55px" class="form-control ng-pristine ng-valid ng-touched">
									<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">规格：</label>
								</div>
								<div class="col-md-12">
									<input id="alter-dosage" type="text" style="padding-left:55px" class="form-control ng-pristine ng-valid ng-touched">
									<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">剂型：</label>
								</div>
								<div class="col-md-12">
									<input id="alter-formula" type="text" style="padding-left:65px" class="form-control ng-pristine ng-valid ng-touched">
									<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">换算公式：</label>
								</div>
								<div class="col-md-12">
									<input id="alter-method" type="text" style="padding-left:65px" class="form-control ng-pristine ng-valid ng-touched">
									<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">使用方法：</label>
								</div>
								<div class="col-md-12">
									<input id="alter-markup" type="text" style="padding-left:60px" class="form-control ng-pristine ng-valid ng-touched">
									<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">加成率：</label>
								</div>
								<div class="col-md-12">
									<input id="alter-spell" type="text" disabled="disabled" style="padding-left:60px" class="form-control ng-pristine ng-valid ng-touched">
									<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">拼音码：</label>
								</div>
								<div class="col-md-12">
									<input id="alter-five" type="text" disabled="disabled" style="padding-left:60px" class="form-control ng-pristine ng-valid ng-touched">
									<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">五笔码：</label>
								</div>
								<div class="col-md-12">
									<input id="alter-invoice" type="text" style="padding-left:65px" class="form-control ng-pristine ng-valid ng-touched">
									<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">发票名称：</label>
								</div>
								<div class="col-md-12">
										是否抗生素: 是 <input type="radio" name="alter-anti" id="alter-anti1" value="1" /> 否 <input type="radio" name="alter-anti"
									 id="alter-anti0" value="0" />
								</div>	
								<div class="col-md-12">
										<input id="alter-dose" type="text" style="padding-left:60px" class="form-control ng-pristine ng-valid ng-touched">
										<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">次剂量：</label>
								</div>	
								<div class="col-md-12">
									<input id="alter-numday" type="text" style="padding-left:60px" class="form-control ng-pristine ng-valid ng-touched">
									<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">日次数：</label>
								</div>
								<div class="col-md-12">
									<input id="alter-dcost" type="text" style="padding-left:60px" class="form-control ng-pristine ng-valid ng-touched">
									<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">成本价：</label>
								</div>
								<div class="col-md-12">
									<input id="alter-dsale" type="text" style="padding-left:60px" class="form-control ng-pristine ng-valid ng-touched">
									<label for="mcmStartTime" style="width:100px;margin: 5px auto 1px auto;" class="input-control-label-left text-left">销售价：</label>
								</div>																														
							</div>
						</div>
						<div id="" align="center">
							<button class="btn btn-primary" id="btn-alter" type="button" style="width: 100px;height: 30px;"><i class="icon icon-save"></i>
								保 存</button>
							<button class="btn btn-primary" id="alter-exit" type="button" style="width: 100px;height: 30px;"><i class="icon icon-reply"></i>
								返 回</button>
						</div>
					</div>

				</div>
			</div>
		</div>
		<!-- 头部标题 -->
		<div class="panel panel-primary panelMargin">
			<div class="panel-heading">
				药品设置
			</div>
			<div id="text-uname" style="display: none;">
				<%=users.getUname()%>
			</div>
			<!-- 主界面 -->
			<div class="panel-body">
			
				<div class="container-fluid div_seek">
                    <div class="row">
                        <div class="col-lg-1 col-md-2 col-sm-6"><button class="btn btn-primary" type="button" id="btn-add" data-toggle="tooltip" title="刷新">
                       		<i class="icon icon-plus"></i>新增</button></div>
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
    //正则判断中文
    var judge1=/[\u4e00-\u9fa5]/;
    //英文
    var judge2=/[a-zA-Z]/;
    //数字
    var judge3=/[0-9]/
	//刷新界面
	document.getElementById('btn-refresh').onclick = function() {	
		location.reload();	
	}
		
	//打开新增药品界面
	document.getElementById('btn-add').onclick = function() {
		$('#myModal').modal({
			backdrop:'static',
			show: true
		})
	}
	
	//清空新增内容
	function removenew(){
		 document.getElementById('drugtype-frist').options[0].selected=true;
		 document.getElementById('drugtype-second').options[0].selected=true;
		 document.getElementById('text-drugname').value="";
		 document.getElementById('text-chemicalname').value="";
		 document.getElementById('text-commonly').value="";
		 document.getElementById('text-specification').value="";
		 document.getElementById('text-dosage').value="";
		 document.getElementById('text-formula').value="";
		 document.getElementById('text-method').value="";
		 document.getElementById('text-markup').value="";
		 document.getElementById('text-spell').value="";
		 document.getElementById('text-five').value="";
		 document.getElementById('text-invoice').value="";
		 document.getElementById('text-dose').value="";
		 document.getElementById('text-numday').value="";
		 document.getElementById('text-dcost').value="";
		 document.getElementById('text-dsale').value="";
	}
	//清空修改内容
	function removealter(){
		 document.getElementById('altertype-frist').options[0].selected=true;
		 document.getElementById('altertype-second').options[0].selected=true;
		 document.getElementById('alter-drugname').value="";
		 document.getElementById('alter-chemicalname').value="";
		 document.getElementById('alter-commonly').value="";
		 document.getElementById('alter-specification').value="";
		 document.getElementById('alter-dosage').value="";
		 document.getElementById('alter-formula').value="";
		 document.getElementById('alter-method').value="";
		 document.getElementById('alter-markup').value="";
		 document.getElementById('alter-spell').value="";
		 document.getElementById('alter-five').value="";
		 document.getElementById('alter-invoice').value="";
		 document.getElementById('alter-dose').value="";
		 document.getElementById('alter-numday').value="";
		 document.getElementById('alter-dcost').value="";
		 document.getElementById('alter-dsale').value="";
	}
	// 关闭新增药品界面
	document.getElementById('add-exit').onclick = function() {
		$('#myModal').modal('hide', 'fit')
		removenew();
	}
	//关闭修改药品界面
	document.getElementById('alter-exit').onclick = function() {
		$('#editModal').modal('hide', 'fit')
		removealter();
	}
	//清空
	document.getElementById('btn-null').onclick = function() {
		document.getElementById('text-drug').value = "";
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
					allpage = count % 5 == 0 ? (count/5):(count/5+1);
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
					td5.innerHTML='<button class="btn btn-link"  drugid='+data[i].drugId+' type="button"><i class="icon icon-edit"></i>修改</button> <button class="btn btn-link"  drugid='+data[i].drugId+' type="button"><i class="icon icon-trash"></i>删除</button>'
									
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

	
	table.onclick = function(e) {
		el = e.target;		
		var drugid=el.getAttribute('drugId');
		if(el.innerText == "修改") {
			$('#editModal').modal({
				backdrop: 'static',
				show: true
			})	
 			$.ajax({		
			url:"../dodrugshow.action",
			type:"post",
			data:"drugid="+drugid,
			dataType:"json",
			success:function(datas){	
				var data=datas.list;		
				for (var i = 0; i < data.length; i++) {
					
					var opd=document.getElementById('altertype-frist').children;	
					for (var j = 0; j < opd.length; j++) {
						if(opd[j].innerText==data[i].fname){	
							opd[j].selected=true;
						}
			 		}
					var ops=document.getElementById('altertype-second').children;			
					for (var z = 0; z < ops.length; z++) {
						ops[z].innerText=data[i].dtname	
			 		}				 
					 document.getElementById('alter-drugname').value=data[i].drugName;
					 document.getElementById('alter-chemicalname').value=data[i].chemicalName;
					 document.getElementById('alter-commonly').value=data[i].commonlyName;
					 document.getElementById('alter-specification').value=data[i].specification;
					 document.getElementById('alter-dosage').value=data[i].dosage;
					 document.getElementById('alter-formula').value=data[i].formula;
					 document.getElementById('alter-method').value=data[i].methodUsers;
					 document.getElementById('alter-markup').value=data[i].markup;
					 document.getElementById('alter-spell').value=data[i].spell;
					 document.getElementById('alter-five').value=data[i].fiveStroke;
					 document.getElementById('alter-invoice').value=data[i].invoiceName;
					 document.getElementById('alter-dose').value=data[i].dose;
					 document.getElementById('alter-numday').value=data[i].numberDays;
					 document.getElementById('alter-dcost').value=data[i].dcost;
					 document.getElementById('alter-dsale').value=data[i].dsale;
					 if(data[i].antibiotics=="1"){
						 document.getElementById('alter-anti1').checked=true;
					 }else{
						 document.getElementById('alter-anti0').checked=true;
					 } 
		
				 }  
			},		
				error:function(e){
				    new $.zui.Messager('药品详情展示请求失败！', {
	 			        icon: 'bell' // 定义消息图标
	 			    }).show();										
				}
		}) 
			var adtname="";
			//修改保存
			document.getElementById('btn-alter').onclick=function(){
				
				 var drugname=document.getElementById('alter-drugname').value.replace(/\s+/g,"");
				 var chemicalname=document.getElementById('alter-chemicalname').value.replace(/\s+/g,"");
				 var commonly=document.getElementById('alter-commonly').value.replace(/\s+/g,"");
				 var specification=document.getElementById('alter-specification').value.replace(/\s+/g,"");
				 var dosage=document.getElementById('alter-dosage').value.replace(/\s+/g,"");
				 var formula=document.getElementById('alter-formula').value.replace(/\s+/g,"");
				 var method=document.getElementById('alter-method').value.replace(/\s+/g,"");
				 var markup=document.getElementById('alter-markup').value.replace(/\s+/g,"");
				 var spell=document.getElementById('alter-spell').value.replace(/\s+/g,"");
				 var five=document.getElementById('alter-five').value.replace(/\s+/g,"");
				 var invoice=document.getElementById('alter-invoice').value.replace(/\s+/g,"");
				 var dose=document.getElementById('alter-dose').value.replace(/\s+/g,"");
				 var numday=document.getElementById('alter-numday').value.replace(/\s+/g,"");
				 var dcost=document.getElementById('alter-dcost').value.replace(/\s+/g,"");
				 var dsale=document.getElementById('alter-dsale').value.replace(/\s+/g,"");
				 var count=dsale-dcost;
				 
				 var opd=document.getElementById('altertype-second').children;	
					for (var i = 0; i < opd.length; i++) {
						if(opd[i].selected==true){	
							adtname=opd[i].innerText;	
						}
			 		}	
			      var radios = document.getElementsByName("alter-anti");
			      var anti = 0;
			      for(var i=0;i<radios.length;i++){
			          if(radios[i].checked == true){
			              anti = radios[i].value;
			            }
			        }
			      
			      if(drugname==""||chemicalname==""||commonly==""||specification==""||formula==""||
			    		  method==""||markup==""||spell==""||five==""||invoice==""
			    		||dose==""||numday==""||dcost==""||dsale==""||dtname=="药品小类"
			    			||dosage==""||anti==""){
						new $.zui.Messager("信息请输入完整！", {
		 			        icon: 'bell' // 定义消息图标
		 			    }).show();
			      }else if(judge1.test(drugname)==false||judge1.test(chemicalname)==false||judge1.test(commonly)==false){
						new $.zui.Messager("药品名称请输入中文！", {
		 			        icon: 'bell' // 定义消息图标
		 			    }).show();
			      }else if(judge1.test(method)==false){
						new $.zui.Messager("使用方法请输入中文！", {
		 			        icon: 'bell' // 定义消息图标
		 			    }).show();
			      }else if(judge1.test(dose)==false){
						new $.zui.Messager("剂型请输入中文！", {
		 			        icon: 'bell' // 定义消息图标
		 			    }).show();
			      }else if(judge2.test(spell)==false){
						new $.zui.Messager("拼音码请输入英文！", {
		 			        icon: 'bell' // 定义消息图标
		 			    }).show();    	  
			      }else if(judge2.test(five)==false){
						new $.zui.Messager("五笔码请输入英文！", {
		 			        icon: 'bell' // 定义消息图标
		 			    }).show();    	  
			      }else if(judge3.test(numday)==false){
						new $.zui.Messager("日次数请输入数字！", {
		 			        icon: 'bell' // 定义消息图标
		 			    }).show();    	  
			      }else if(judge3.test(dcost)==false||judge3.test(dsale)==false){
						new $.zui.Messager("价格请输入数字！", {
		 			        icon: 'bell' // 定义消息图标
		 			    }).show();    	  
			      }else if(dcost<=0||dsale<=0){
						new $.zui.Messager("价格请输入正数！", {
		 			        icon: 'bell' // 定义消息图标
		 			    }).show(); 
			      }else if(count<0){
						new $.zui.Messager("销售价应大于成本价！", {
		 			        icon: 'bell' // 定义消息图标
		 			    }).show();   
			      }else{
					$.ajax({		
						url:"../doalterdrug.action",
						type:"post",
						data:"drugname="+drugname+"&chemicalname="+chemicalname+"&commonly="+commonly
						+"&specification="+specification+"&dosage="+dosage+"&formula="+formula
						+"&method="+method+"&markup="+markup+"&spell="+spell
						+"&five="+five+"&invoice="+invoice+"&dose="+dose+"&dcost="+dcost+"&dsale="+dsale
						+"&numday="+numday+"&dtname="+adtname+"&anti="+anti+"&drugid="+drugid,
						dataType:"json",
						success:function(data){
							if(data=="empty"){
								new $.zui.Messager("输入不能为空", {
				 			        icon: 'bell' // 定义消息图标
				 			    }).show();
							}else if(data=="yes"){
							    new $.zui.Messager("药品修改成功", {
				 			        icon: 'bell' // 定义消息图标
				 			    }).show();
								$('#editModal').modal('hide', 'fit')
								removealter();
								init();
							}else if(data=="no"){
							    new $.zui.Messager("药品修改失败", {
				 			        icon: 'bell' // 定义消息图标
				 			    }).show();
							}					
						},
						error:function(e){
							    new $.zui.Messager('药品修改请求失败！', {
				 			        icon: 'bell' // 定义消息图标
				 			    }).show();	
						}			
					})	 
			      }
			}		
			
		}else if(el.innerText == "删除"){		
			//询问框
			layer.confirm('是否确定删除', {
			  btn: ['是','否'] //按钮
			}, function(){			  
				$.ajax({		
				url:"../dodeldrug.action",
				type:"post",
				data:"drugid="+drugid,
				dataType:"json",
				success:function(data){
					if(data=="yes"){
						layer.msg('药品删除成功！', {icon: 1});
					    init();
					}else if(data=="no"){
						 layer.msg('药品删除失败', {icon: 1});
					}					
				},
				error:function(e){
					 layer.msg('药品删除请求失败', {icon: 1});
				}			
			}) 	 
							
			}, function(){
		
			});	
		}
	}
	//下拉框药品大类
	var dtfrist=document.getElementById('drugtype-frist');
	var atfrist=document.getElementById('altertype-frist');
	//下拉框药品小类
	var dtsecond=document.getElementById('drugtype-second');
	var atsecond=document.getElementById('altertype-second');
	//下拉框药品大类
	$.ajax({		
		url:"../dodrugtypefrist.action",
		type:"post",		
		dataType:"json",
		success:function(datas){	
			
			var data=datas.list
			var ops = document.getElementsByClassName('dtfrist-op');
			
			for(var i = ops.length-1;i>=0;i--){
				ops[0].remove();
			}  
								
			for (var i = 0; i < data.length; i++) {
														
				var op=document.createElement('option');						
				op.innerText=data[i];
				
				dtfrist.appendChild(op);
				
				op.classList.add("dtfrist-op"); 					
			}
		},
		error:function(e){
			    new $.zui.Messager('下拉框药品大类请求失败！', {
 			        icon: 'bell' // 定义消息图标
 			    }).show();	
		}
		
	})
	
	//下拉框修改药品大类
	$.ajax({		
		url:"../dodrugtypefrist.action",
		type:"post",		
		dataType:"json",
		success:function(datas){	
			
			var data=datas.list
			var ops = document.getElementsByClassName('atfrist-op');
			
			for(var i = ops.length-1;i>=0;i--){
				ops[0].remove();
			}  
								
			for (var i = 0; i < data.length; i++) {
														
				var op=document.createElement('option');						
				op.innerText=data[i];
				
				atfrist.appendChild(op); 
				
				op.classList.add("atfrist-op"); 					
			}
		},
		error:function(e){
			    new $.zui.Messager('下拉框药品大类请求失败！', {
 			        icon: 'bell' // 定义消息图标
 			    }).show();	
		}
		
	})
	//获取药品大类联动药品小类
	 var drugfrist="";
	 dtfrist.onclick = function() {		
		var ops=document.getElementById('drugtype-frist').children;		
		for (var i = 0; i < ops.length; i++) {
			if(ops[i].selected==true){				
				drugfrist=ops[i].innerText;			
				//下拉框药品小类
				$.ajax({		
					url:"../dodrugtypesecond.action",
					type:"post",
					data:"drugfrist="+drugfrist,
					dataType:"json",
					success:function(datas){	
						var data=datas.list
						var ops = document.getElementsByClassName('dtsecond-op');
						
						for(var i = ops.length-1;i>=0;i--){
							ops[0].remove();
						}  
											
						for (var i = 0; i < data.length; i++) {
																	
							var op=document.createElement('option');						
							op.innerText=data[i].dtname;						
							op.setAttribute("value",data[i].dtid);
							
							dtsecond.appendChild(op);	
							
							op.classList.add("dtsecond-op"); 					
						}
					},
					error:function(e){
						    new $.zui.Messager('下拉框药品小类请求失败！', {
			 			        icon: 'bell' // 定义消息图标
			 			    }).show();	
					}
					
				})				
			}
		}
	}
	 var alterfrist="";
	 atfrist.onclick = function() {		
			var ops=document.getElementById('altertype-frist').children;		
			for (var i = 0; i < ops.length; i++) {
				if(ops[i].selected==true){				
					alterfrist=ops[i].innerText;			
					//下拉框药品小类
					$.ajax({		
						url:"../dodrugtypesecond.action",
						type:"post",
						data:"drugfrist="+alterfrist,
						dataType:"json",
						success:function(datas){	
							var data=datas.list
							var ops = document.getElementsByClassName('atsecond-op');
							
							for(var i = ops.length-1;i>=0;i--){
								ops[0].remove();
							}  
												
							for (var i = 0; i < data.length; i++) {
																		
								var op=document.createElement('option');						
								op.innerText=data[i].dtname;
								
								atsecond.appendChild(op);	
								
								op.classList.add("atsecond-op"); 					
							}
						},
						error:function(e){
							    new $.zui.Messager('下拉框药品小类请求失败！', {
				 			        icon: 'bell' // 定义消息图标
				 			    }).show();	
						}
						
					})				
				}
			}
		}
	 //设置药品根据药名自动生成拼音码和五笔码
	 document.getElementById('text-drugname').onblur= function(){
		 var drugname=document.getElementById('text-drugname').value.replace(/\s+/g,"");
			$.ajax({		
				url:"../dospellauto.action",
				type:"post",
				data:"drugname="+drugname,
				dataType:"json",
				success:function(datas){					
					document.getElementById('text-spell').value=datas.spell;
					document.getElementById('text-five').value=datas.wubi;
				},
				error:function(e){
					    new $.zui.Messager('药名自动生成拼音码请求失败！', {
		 			        icon: 'bell' // 定义消息图标
		 			    }).show();	
				}				
			})
	 }
	 
	 //修改药品根据药名自动生成拼音码和五笔码
	 document.getElementById('alter-drugname').onblur= function(){
		 var drugname=document.getElementById('alter-drugname').value.replace(/\s+/g,"");
			$.ajax({		
				url:"../dospellauto.action",
				type:"post",
				data:"drugname="+drugname,
				dataType:"json",
				success:function(datas){					
					document.getElementById('alter-spell').value=datas.spell;
					document.getElementById('alter-five').value=datas.wubi;
				},
				error:function(e){
					    new $.zui.Messager('药名自动生成拼音码请求失败！', {
		 			        icon: 'bell' // 定义消息图标
		 			    }).show();	
				}				
			})
	 }
	 
	 
	 
	 
	 
	 var dtname="";
	 //药品设置
	 document.getElementById('btn-drugset').onclick = function() {
		 
		 var drugname=document.getElementById('text-drugname').value.replace(/\s+/g,"");
		 var chemicalname=document.getElementById('text-chemicalname').value.replace(/\s+/g,"");
		 var commonly=document.getElementById('text-commonly').value.replace(/\s+/g,"");
		 var specification=document.getElementById('text-specification').value.replace(/\s+/g,"");
		 var dosage=document.getElementById('text-dosage').value.replace(/\s+/g,"");
		 var formula=document.getElementById('text-formula').value.replace(/\s+/g,"");
		 var method=document.getElementById('text-method').value.replace(/\s+/g,"");
		 var markup=document.getElementById('text-markup').value.replace(/\s+/g,"");
		 var spell=document.getElementById('text-spell').value.replace(/\s+/g,"");
		 var five=document.getElementById('text-five').value.replace(/\s+/g,"");
		 var invoice=document.getElementById('text-invoice').value.replace(/\s+/g,"");
		 var dose=document.getElementById('text-dose').value.replace(/\s+/g,"");
		 var numday=document.getElementById('text-numday').value.replace(/\s+/g,"");
		 var dcost=document.getElementById('text-dcost').value.replace(/\s+/g,"");	
		 var dsale=document.getElementById('text-dsale').value.replace(/\s+/g,"");	
		 var count=dsale-dcost;
		 
		 var opd=document.getElementById('drugtype-second').children;	
			for (var i = 0; i < opd.length; i++) {
				if(opd[i].selected==true){	
					dtname=opd[i].value;	
				}
	 		}	
	      var radios = document.getElementsByName("anti");
	      var anti = 0;
	      for(var i=0;i<radios.length;i++){
	          if(radios[i].checked == true){
	              anti = radios[i].value;
	            }
	        } 
	      
	      if(drugname==""||chemicalname==""||commonly==""||specification==""||formula==""||
	    		  method==""||markup==""||spell==""||five==""||invoice==""
	    		||dose==""||numday==""||dcost==""||dsale==""||dtname=="药品小类"
	    			||dosage==""||anti==""){
				new $.zui.Messager("信息请输入完整！", {
 			        icon: 'bell' // 定义消息图标
 			    }).show();
	      }else if(judge1.test(drugname)==false||judge1.test(chemicalname)==false||judge1.test(commonly)==false){
				new $.zui.Messager("药品名称请输入中文！", {
 			        icon: 'bell' // 定义消息图标
 			    }).show();
	      }else if(judge1.test(dosage)==false){
				new $.zui.Messager("剂型请输入中文！", {
 			        icon: 'bell' // 定义消息图标
 			    }).show();
	      }else if(judge1.test(method)==false){
				new $.zui.Messager("使用方法请输入中文！", {
 			        icon: 'bell' // 定义消息图标
 			    }).show();
	      }else if(judge2.test(spell)==false){
				new $.zui.Messager("拼音码请输入英文！", {
 			        icon: 'bell' // 定义消息图标
 			    }).show();    	  
	      }else if(judge2.test(five)==false){
				new $.zui.Messager("五笔码请输入英文！", {
 			        icon: 'bell' // 定义消息图标
 			    }).show();    	  
	      }else if(judge3.test(numday)==false){
				new $.zui.Messager("日次数请输入数字！", {
 			        icon: 'bell' // 定义消息图标
 			    }).show();    	  
	      }else if(judge3.test(dcost)==false||judge3.test(dsale)==false){
				new $.zui.Messager("价格请输入数字！", {
 			        icon: 'bell' // 定义消息图标
 			    }).show();    	  
	      }else if(dcost<=0||dsale<=0){
				new $.zui.Messager("价格请输入正数！", {
 			        icon: 'bell' // 定义消息图标
 			    }).show(); 
	      }else if(count<0){
				new $.zui.Messager("销售价应大于成本价！", {
 			        icon: 'bell' // 定义消息图标
 			    }).show();   
	      }else{
				$.ajax({		
					url:"../dodrugset.action",
					type:"post",
					data:"drugname="+drugname+"&chemicalname="+chemicalname+"&commonly="+commonly
					+"&specification="+specification+"&dosage="+dosage+"&formula="+formula
					+"&method="+method+"&markup="+markup+"&spell="+spell
					+"&five="+five+"&invoice="+invoice+"&dose="+dose
					+"&numday="+numday+"&dtname="+dtname+"&anti="+anti+"&dcost="+dcost+"&dsale="+dsale,
					dataType:"json",
					success:function(data){
						if(data=="empty"){
							new $.zui.Messager("输入不能为空", {
			 			        icon: 'bell' // 定义消息图标
			 			    }).show();
						}else if(data=="rep"){
						    new $.zui.Messager("该药品名已存在，请重新输入", {
			 			        icon: 'bell' // 定义消息图标
			 			    }).show();				
						}else if(data=="yes"){
						    new $.zui.Messager("药品设置成功", {
			 			        icon: 'bell' // 定义消息图标
			 			    }).show();
						    init();
							$('#myModal').modal('hide', 'fit')
							removenew();
						}else if(data=="no"){
						    new $.zui.Messager("药品设置失败", {
			 			        icon: 'bell' // 定义消息图标
			 			    }).show();
						}					
					},
					error:function(e){
						    new $.zui.Messager('药品设置请求失败！', {
			 			        icon: 'bell' // 定义消息图标
			 			    }).show();	
					}			
				})		  
	      }
	 }
	 
	 
	
	 
	
	
	
</script>
