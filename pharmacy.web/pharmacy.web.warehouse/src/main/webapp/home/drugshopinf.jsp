<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>药品采购统计表</title>
<link rel="stylesheet" type="text/css" href="../css/drugshopinf.css" />
<link rel="stylesheet" href="../3rd/zui/css/zui.min.css" />
<link href="../3rd/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
</head>
<body>
	<div style="padding: 5px">
		<!-- 头部标题 -->
		<div class="panel panel-primary">
			<div class="panel-heading">药品采购统计表</div>
			<!-- 主界面 -->
			<div class="panel-body">
				<div class="container-fluid div_seek">
					<div class="row">
						<div class="col-lg-1 col-md-2 col-sm-6">
							<button class="btn btn-warning" type="button" id="btn-refresh"
								data-toggle="tooltip" title="刷新">
								<i class="icon icon-refresh"></i>刷新
							</button>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-6">
							<input type="text"
								class="form-control ng-pristine ng-valid ng-touched"
								id="text-drug" placeholder="请输入药品名"> <label
								for="mcmStartTime" class="input-control-label-left text-right">药品名称:</label>
							<label for="inputAccountExample1" class="input-control-icon-left"><i
								class="icon icon-file-text"></i></label>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-6">
							<input type="text"
								class="form-control ng-pristine ng-valid ng-touched"
								id="text-supply" placeholder="请输入供应商"> <label
								for="mcmStartTime" class="input-control-label-left text-right">供应商:</label>
							<label for="inputAccountExample1" class="input-control-icon-left"><i
								class="icon icon-file-text"></i></label>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-6">
							<input type="text"
								class="form-control ng-pristine ng-valid ng-touched"
								id="text-uaccount" placeholder="请输入经办人"> <label
								for="mcmStartTime" class="input-control-label-left text-right">经办人:</label>
							<label for="inputAccountExample1" class="input-control-icon-left"><i
								class="icon icon-file-text"></i></label>
						</div>
						<div class="col-lg-1 col-md-2 col-sm-6">
							<button class="btn btn-info" type="button" id="btn-search"
								data-toggle="tooltip" title="搜索">
								<i class="icon icon-search"></i>搜索
							</button>
						</div>
						<div class="col-lg-1 col-md-2 col-sm-6">
							<button class="btn btn-danger" id="btn-null" type="button"
								data-toggle="tooltip" title="清空">
								<i class="icon icon-trash"></i>清空
							</button>
						</div>
					</div>


					<table class="table table-bordered" id="table-edit">
						<thead>
							<tr align="center">
								<th>序号</th>
								<th>药品名称</th>
								<th>经办人</th>
								<th>采购时间</th>
								<th>供应商</th>
								<th>采购数量</th>
								<th>采购金额</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<div id="" align="center">
						<ul class="pager pager-pills">
							<li class="previous "><a href="javascript:void(0);"
								id="up-page" onclick="pre()">« 上一页</a></li>
							<li><span id="lab_page"> <span id="nowpage"> 1
								</span> <span id="midpage"> / </span> <span id="allpage"> 1 </span></span></li>
							<li class="next"><a href="javascript:void(0);"
								id="down-page" onclick="next()">下一页 »</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="../3rd/zui/lib/jquery/jquery.js"></script>
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
		document.getElementById('text-supply').value="";
		document.getElementById('text-uaccount').value="";
	}
	var table = document.getElementById('table-edit');
	
	var start = 0;
	var end = 5;
	var nowpage =1;
	var allpage =1;	
	var count=0;
	var drugname="";
	var supply="";
	var uname="";
	
	function init(){
		
		$.ajax({		
			url:"../dodrugshopinfinit.action",
			type:"post",
			data:"start="+start+"&end="+end+"&drugname="+drugname+"&supply="+supply
			+"&uname="+uname,
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
					td3.innerText=data[i].uname;
					
					var td4=document.createElement('td');
					td4.innerText=data[i].purchasingTime;
							
					var td5=document.createElement('td');
					td5.innerText=data[i].provider;
					
					var td6=document.createElement('td');
					td6.innerText=data[i].inventoryCount;
					
					var td7=document.createElement('td');
					td7.innerText=(data[i].dcost)*(data[i].inventoryCount)+"元";
					
					tr.appendChild(td1);
					tr.appendChild(td2);
					tr.appendChild(td3);
					tr.appendChild(td4);
					tr.appendChild(td5);
					tr.appendChild(td6);
					tr.appendChild(td7);
					
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
		supply=document.getElementById('text-supply').value;
		uname=document.getElementById('text-uaccount').value;
		
		start = 0;
		end = 5;
		nowpage =1; 
		document.getElementById('nowpage').innerText=1;
		init();			
	}

</script>