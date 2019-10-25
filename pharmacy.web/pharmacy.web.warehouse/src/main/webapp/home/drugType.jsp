<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>药品类型管理</title>
		<link rel="stylesheet" href="../3rd/zui/css/zui.min.css">
		<style type="text/css">
	/* 		.myPanel {
				margin: 30px auto;
				width: 98%;
			} */
			.panel-body .panel-add> *{
				float: left;
				margin: 0 5px 20px 10px ;
			}
			.div_tree {
				padding: 15px;
			}

			.div_btn {
				text-align: center;
			}

			.div_page {
				text-align: center;
			}

			@media (min-width: 1200px) .col-lg-1 {
				width: 8.33333333%;
			}

			@media (min-width: 992px) .col-md-2 {
				width: 16.66666667%;
			}

			@media (min-width: 768px) .col-sm-6 {
				width: 50%;
			}

			@media (min-width: 1200px) .col-lg-8 {
				width: 66.66666667%;
			}

			@media (min-width: 992px) .col-md-4 {
				width: 33.33333333%;
			}

			@media (min-width: 768px) .col-sm-12 {
				width: 100%;
			}

			.col-sm-6>button {
				width: 100%;
			}

			.div_seek {
				padding-bottom: 10px;
			}

			.div_seek .col-sm-12 {
				padding-left: 3px;
			}

			.div_seek .col-sm-12>input {
				padding-left: 24px;
			}
			.drug-list1 >*{
				margin: 10px auto 10px auto;
			}
		</style>
	</head>
	<body>
	<div style="padding: 5px">
		<!-- 类型修改界面 -->
		<div class="modal fade" id="alterModal">
			<div class="modal-dialog" style="width: 300px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true" href="javascript:void(0);"onclick="removeadd()">×</span><span class="sr-only">关闭</span></button>
						<h4 class="modal-title"><i class="icon icon-edit"></i>药品类型修改</h4>
					</div>
					<div class="panel-body">
						<div class="drug-list1">
							<input id="alter-type2" type="text" class="form-control" placeholder="请输入药品类型">
						</div>
						<div id="" align="center">
							<button class="btn btn-primary" id="alter-go" type="button" style="width: 100px;height: 30px;"><i class="icon icon-save"></i>
								保 存</button>
							<button class="btn btn-primary" id="alter-exit" type="button" style="width: 100px;height: 30px;"><i class="icon icon-reply"></i>
								返 回</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 新增大类界面 -->
		<div class="modal fade" id="newbigModal">
			<div class="modal-dialog" style="width: 300px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true" href="javascript:void(0);" onclick="removeadd()">×</span><span class="sr-only">关闭</span></button>
						<h4 class="modal-title"><i class="icon icon-edit"></i>新增药品大类</h4>
					</div>
					<div class="panel-body">
						<div class="drug-list1">
							<input id="text-bigtype" type="text" class="form-control" placeholder="请输入药品大类名称">
						</div>
						<div id="" align="center">
							<button class="btn btn-primary" id="newbigtype-go" type="button" style="width: 100px;height: 30px;"><i class="icon icon-save"></i>
								保 存</button>
							<button class="btn btn-primary" id="newbig-exit" type="button" style="width: 100px;height: 30px;"><i class="icon icon-reply"></i>
								返 回</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 修改大类界面 -->
		<div class="modal fade" id="alterbigModal">
			<div class="modal-dialog" style="width: 300px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true" href="javascript:void(0);" onclick="removeadd()">×</span><span class="sr-only">关闭</span></button>
						<h4 class="modal-title"><i class="icon icon-edit"></i>修改药品大类</h4>
					</div>
					<div class="panel-body">
						<div class="drug-list1">
							<input id="alter-newbigtype" type="text" class="form-control" placeholder="请输入修改药品大类名称">
						</div>
						<div id="" align="center">
							<button class="btn btn-primary" id="alterbig-go" type="button" style="width: 100px;height: 30px;"><i class="icon icon-save"></i>
								保 存</button>
							<button class="btn btn-primary" id="alterbig-exit" type="button" style="width: 100px;height: 30px;"><i class="icon icon-reply"></i>
								返 回</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 编辑药品大类界面 -->
		<div class="modal fade" id="editbigModal">
			<div class="modal-dialog" style="width: 300px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true" href="javascript:void(0);" onclick="removeadd()">×</span><span class="sr-only">关闭</span></button>
						<h4 class="modal-title"><i class="icon icon-edit"></i>编辑药品小类</h4>
					</div>
					<div class="panel-body">
						<table class="table table-hover table-bordered" id="type-table">
							<thead>
							<tr align="center">					
								<th>药品小类</th>	
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
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4>药品类型管理</h4>
			</div>
			<div class="panel-body ">
				<div class="panel-add">
					<button class="btn btn-primary" type="button" id="btn_addbig" style="width: 90px;"><i class="icon icon-plus"></i>新增大类</button>
					<button class="btn btn-primary" type="button" id="btn_add" style="width: 90px;"><i class="icon icon-plus"></i>新增小类</button>
					<button class="btn btn-warning" id="btn-refresh" type="button" style="width: 80px;"><i class="icon icon-refresh"></i> 刷 新</button>
				</div>
				<div class="col-lg-5 col-md-5 col-sm-6">
					<input id="text-dtname" type="text" style="padding-left:80px" class="form-control ng-pristine ng-valid ng-touched">
					<label for="mcmStartTime" style="width:100px" class="input-control-label-left text-right">药品小类：</label>
				</div>
				<div id="">
					<button class="btn btn-info" id="btn-search" type="button" style="width: 80px;"><i class="icon icon-search"></i> 搜 索</button>
					<button class="btn btn-danger" id="btn_del" type="button" style="width: 80px;"><i class="icon icon-trash"></i> 清 空</button>
				</div>				
					<table class="table table-hover table-bordered" id="myTable">
						<thead>
							<tr>
								<th>序号</th>
								<th>药品大类</th>
								<th>编辑药品小类</th>
								<th>操作</th>
							</tr>
						</thead>
					</table>
				<div class="div_page">
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
		<!-- Modal -->
		<div class="modal fade bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
			<div class="modal-dialog modal-sm" role="document">
				<form action="" class="form-horizontal">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" href="javascript:void(0);"onclick="removeadd()" aria-label="Close" ><span aria-hidden="true" >&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">新增药品类型</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label for="exampleInputEmail3">药品类型：</label>
								<input type="text" id="text-type" class="form-control" placeholder="请输入药品类型">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail3" >药品大类：</label>
								<select name="" class="form-control" id="drugtype-frist">
									<option value="0" disabled selected>请选择药品大类</option>
								</select>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" href="javascript:void(0);"onclick="removeadd()" data-dismiss="modal">取消</button>
							<button type="button" id="new-add" class="btn btn-primary">保存</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		</div>
		<script src="../3rd/zui/lib/jquery/jquery.js"></script>
		<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
		<script src="../3rd/jq/jquery.min.js"></script>
		<script src="../3rd//layer/layer.js"></script>
		<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
		<script src="../3rd/zui/js/zui.min.js"></script>
	</body>
</html>
<script type="text/javascript">
	$('#btn_add').click(function() {
		$('#myModal').modal('show')
	})
	$('#btn_addbig').click(function() {
		$('#newbigModal').modal('show')
	})
	$('[data-toggle="tooltip"]').tooltip({
		placement: 'bottom'
	});
	$('#btn_del').click(function(){
		document.getElementById('text-dtname').value="";
	})
	//退出修改类型界面
	document.getElementById('alter-exit').onclick = function() {	
		$('#alterModal').modal('hide', 'fit');
		removeadd();
	}
	//退出新增大类界面
	document.getElementById('newbig-exit').onclick = function() {	
		$('#newbigModal').modal('hide', 'fit');
		removeadd();
	}
	//退出修改大类界面
	document.getElementById('alterbig-exit').onclick = function() {	
		$('#alterbigModal').modal('hide', 'fit');
		removeadd();
	}
	
	function removeadd(){
		document.getElementById('text-type').value="";
		document.getElementById('text-bigtype').value="";
		document.getElementById('alter-newbigtype').value="";
		document.getElementById('drugtype-frist').options[0].selected=true;
	}
	
	//刷新界面
	document.getElementById('btn-refresh').onclick = function() {	
		location.reload();	
	}	
    //正则判断中文
    var judge1=/[\u4e00-\u9fa5]/;
	//新增药品大类
	document.getElementById('newbigtype-go').onclick = function() {	
		var newbig=document.getElementById('text-bigtype').value.replace(/\s+/g,"");
		if(newbig==""){
			new $.zui.Messager("请输入药品大类！", {
			        icon: 'bell' // 定义消息图标
			    }).show();
		}else if(judge1.test(newbig)==false){
			new $.zui.Messager("请输入中文！", {
		        icon: 'bell' // 定义消息图标
		    }).show();			
		}else{
			$.ajax({		
				url:"../doaddbig.action",
				type:"post",
				data:"newbig="+newbig,
				dataType:"json",
				success:function(data){
					if(data=="empty"){
						new $.zui.Messager("输入不能为空", {
		 			        icon: 'bell' // 定义消息图标
		 			    }).show();
					}else if(data=="rep"){
						new $.zui.Messager("该药品类型已存在！", {
		 			        icon: 'bell' // 定义消息图标
		 			    }).show();		
					}else if(data=="yes"){
					    new $.zui.Messager("药品大类新增成功", {
		 			        icon: 'bell' // 定义消息图标
		 			    }).show();
					    $('#newbigModal').modal('hide', 'fit')				    
					    initbigtype();
					    removeadd();
					    init();
					}else if(data=="no"){
					    new $.zui.Messager("药品大类新增失败", {
		 			        icon: 'bell' // 定义消息图标
		 			    }).show();
					}					
				},
				error:function(e){
					    new $.zui.Messager('药品大类新增请求失败！', {
		 			        icon: 'bell' // 定义消息图标
		 			    }).show();	
				}			
			})				
		}
	}
	
	//下拉框药品大类
	var dtfrist=document.getElementById('drugtype-frist');
	
	//下拉框药品大类
	function initbigtype(){
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
	}

	initbigtype();
	var bigtable=document.getElementById('type-table');
	
	var start = 0; // 起始查询数据序列
	var end = 5; // 最大查询数据序列
	var nowpage = 1; // 当前页
	var allpage = 1; // 总页数
	var count = 0; // 数据总数
	var dtname="";
	var table = document.getElementById('myTable');

	function init(){
		
		$.ajax({		
			url:"../dodrugtypeinit.action",
			type:"post",
			data:"start="+start+"&end="+end+"&dtname="+dtname,
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
 		 		var trs = document.getElementsByClassName('drugtype-tr');
	 			//清除数据
				for(var i = trs.length-1;i>=0;i--){
					trs[0].remove();
				} 			
				for (var i = 0; i < data.length; i++) {
					
					var tr = document.createElement('tr');
					
					tr.classList.add("drugtype-tr");
					
					var td1=document.createElement('td');
					td1.innerText=i+1;
					
					var td2=document.createElement('td');
					td2.innerText=data[i].dtname;
					
					var td3=document.createElement('td');
					td3.innerHTML='<button class="btn btn-link"  dtid='+data[i].dtid+' type="button"><i class="icon icon-edit"></i>查看编辑药品小类</button>'
												
					var td4=document.createElement('td');
					td4.innerHTML='<button class="btn btn-link"  dtid='+data[i].dtid+' type="button"><i class="icon icon-edit"></i>修改</button> <button class="btn btn-link"  dtid='+data[i].dtid+' type="button"><i class="icon icon-trash"></i>删除</button>'
									
					tr.appendChild(td1);
					tr.appendChild(td2);
					tr.appendChild(td3);
					tr.appendChild(td4);
					
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
	
		dtname=document.getElementById('text-dtname').value.replace(/\s+/g,"");	

		start = 0;
		end = 5;
		nowpage =1; 
		document.getElementById('nowpage').innerText=1;
		init(); 
				
	}
	var fname="";
	//新增类型
	document.getElementById('new-add').onclick=function(){
		
		var tname=document.getElementById('text-type').value.replace(/\s+/g,"");
		var opd=document.getElementById('drugtype-frist').children;	
			for (var i = 0; i < opd.length; i++) {
				if(opd[i].selected==true){	
					fname=opd[i].innerText;	
				}
	 		}	
			if(fname=="请选择药品大类"){
				new $.zui.Messager("请选择药品大类！", {
			        icon: 'bell' // 定义消息图标
			    }).show();
			}else if(tname==""){
				new $.zui.Messager("请输入药品类型！", {
				        icon: 'bell' // 定义消息图标
				    }).show();
			}else if(judge1.test(tname)==false){
				new $.zui.Messager("请输入中文！", {
			        icon: 'bell' // 定义消息图标
			    }).show();			
			}else{
	 			$.ajax({		
					url:"../doaddtype.action",
					type:"post",
					data:"tname="+tname+"&fname="+fname,
					dataType:"json",
					success:function(data){
						if(data=="empty"){
							new $.zui.Messager("输入不能为空", {
			 			        icon: 'bell' // 定义消息图标
			 			    }).show();
						}else if(data=="rep"){
							new $.zui.Messager("该药品类型已存在！", {
			 			        icon: 'bell' // 定义消息图标
			 			    }).show();		
						}else if(data=="yes"){
						    new $.zui.Messager("药品类型新增成功", {
			 			        icon: 'bell' // 定义消息图标
			 			    }).show();
						    $('#myModal').modal('hide', 'fit')
						    removeadd();
						    init();
						}else if(data=="no"){
						    new $.zui.Messager("药品类型新增失败", {
			 			        icon: 'bell' // 定义消息图标
			 			    }).show();
						}					
					},
					error:function(e){
						    new $.zui.Messager('药品类型新增请求失败！', {
			 			        icon: 'bell' // 定义消息图标
			 			    }).show();	
					}			
				})				
			}
	}
	//编辑小类
	bigtable.onclick = function(e) {	
		el = e.target;
		if (el.innerText == "删除") {		
			var dtid=el.getAttribute('dtid');	
			layer.confirm('是否确定删除', {
				  btn: ['是','否'] //按钮
				}, function(){			  
					$.ajax({		
					url:"../dodeltype.action",
					type:"post",
					data:"dtid="+dtid,
					dataType:"json",
					success:function(data){
						if(data=="yes"){
							layer.msg('药品类型删除成功！', {icon: 1});
							$('#editbigModal').modal('hide', 'fit')				    
						}else if(data=="no"){
							 layer.msg('药品类型删除失败', {icon: 1});
						}					
					},
					error:function(e){
						 layer.msg('药品类型删除请求失败', {icon: 1});
					}			
				}) 	 
								
				}, function(){
			
				});	
		}else if(el.innerText == "修改") {
			$('#editbigModal').modal('hide', 'fit')
			$('#alterModal').modal({
				backdrop: 'static',
				show: true
			})	
			document.getElementById('alter-type2').value=el.parentElement.parentElement.children[0].innerText;
			 //修改保存
			document.getElementById('alter-go').onclick = function() {
				var dtid=el.getAttribute('dtid');	
				var newtype=document.getElementById('alter-type2').value.replace(/\s+/g,"");
				if(newtype==""){
					new $.zui.Messager("请输入新的类型", {
	 			        icon: 'bell' // 定义消息图标
	 			    }).show();
				}else if(judge1.test(newtype)==false){
					new $.zui.Messager("请输入中文", {
	 			        icon: 'bell' // 定义消息图标
	 			    }).show();
				}else{
					$.ajax({		
						url:"../doaltertype.action",
						type:"post",
						data:"dtid="+dtid+"&newtype="+newtype,
						dataType:"json",
						success:function(data){
							if(data=="empty"){
								new $.zui.Messager("输入不能为空", {
				 			        icon: 'bell' // 定义消息图标
				 			    }).show();
							}else if(data=="rep"){
								new $.zui.Messager("该药品类型已存在！", {
				 			        icon: 'bell' // 定义消息图标
				 			    }).show();		
							}else if(data=="yes"){
							    new $.zui.Messager("药品类型修改成功", {
				 			        icon: 'bell' // 定义消息图标
				 			    }).show();
							    $('#alterModal').modal('hide', 'fit')
							    init();
							    removeadd();
							}else if(data=="no"){
							    new $.zui.Messager("药品类型修改失败", {
				 			        icon: 'bell' // 定义消息图标
				 			    }).show();
							}					
						},
						error:function(e){
							    new $.zui.Messager('药品类型修改请求失败！', {
				 			        icon: 'bell' // 定义消息图标
				 			    }).show();	
						}			
					})						
				}
			} 
		}	
	}
	
	table.onclick = function(e) {

		el = e.target;
		if (el.innerText == "删除") {
			var bigname=el.parentElement.parentElement.children[1].innerText;
 			layer.confirm('删除大类后该大类所属的小类将全部删除，是否确定删除？', {
				  btn: ['是','否'] //按钮
				}, function(){			  
					$.ajax({		
					url:"../dodelbig.action",
					type:"post",
					data:"bigname="+bigname,
					dataType:"json",
					success:function(data){
						if(data=="yes"){
							layer.msg('药品大类删除成功！', {icon: 1});
							$('#editbigModal').modal('hide', 'fit')
							initbigtype();
							start = 0;
							end = 5;
							nowpage =1; 
							document.getElementById('nowpage').innerText=1;
						    init();
						}else if(data=="no"){
							layer.msg('药品大类删除成功！', {icon: 1});
							$('#editbigModal').modal('hide', 'fit')
							initbigtype();
						    init();
						}					
					},
					error:function(e){
						 layer.msg('药品大类删除请求失败', {icon: 1});
					}			
				}) 	 						
				}, function(){
			
				});		
		}else if(el.innerText=="查看编辑药品小类"){
			$('#editbigModal').modal('show');
			var drugfrist=el.parentElement.parentElement.children[1].innerText;
			//药品小类展示
			$.ajax({		
				url:"../dodrugtypesecond.action",
				type:"post",
				data:"drugfrist="+drugfrist,
				dataType:"json",
				success:function(datas){	
					var data=datas.list;
	 		 		var trs = document.getElementsByClassName('bigtype-tr');
		 			//清除数据
					for(var i = trs.length-1;i>=0;i--){
						trs[0].remove();
					} 			
					for (var i = 0; i < data.length; i++) {
						
						var tr = document.createElement('tr');
						
						tr.classList.add("bigtype-tr");
						
						var td1=document.createElement('td');
						td1.innerText=data[i].dtname;
													
						var td2=document.createElement('td');
						td2.innerHTML='<button class="btn btn-link"  dtid='+data[i].dtid+'  type="button"><i class="icon icon-edit"></i>修改</button> <button class="btn btn-link"  dtid='+data[i].dtid+'  type="button"><i class="icon icon-trash"></i>删除</button>'
										
						tr.appendChild(td1);
						tr.appendChild(td2);
						
						bigtable.appendChild(tr);	 		
					 }
				},
				error:function(e){
					    new $.zui.Messager('下拉框药品小类请求失败！', {
		 			        icon: 'bell' // 定义消息图标
		 			    }).show();	
				}
				
			})	
			
		}else if(el.innerText == "修改"){
			
			 $('#alterbigModal').modal('show') 
			 
			 document.getElementById('alterbig-go').onclick=function(){
				var oldbigtype=el.parentElement.parentElement.children[1].innerText;
				var newbigtype=document.getElementById('alter-newbigtype').value.replace(/\s+/g,"");
				if(newbigtype==""){
					new $.zui.Messager("请输入新的药品大类！", {
	 			        icon: 'bell' // 定义消息图标
	 			    }).show();	
				}else if(judge1.test(newbigtype)==false){
					new $.zui.Messager("请输入中文！", {
				        icon: 'bell' // 定义消息图标
				    }).show();	
				}else{
					$.ajax({		
						url:"../doalterbig.action",
						type:"post",
						data:"oldbigtype="+oldbigtype+"&newbigtype="+newbigtype,
						dataType:"json",
						success:function(data){
							if(data=="empty"){
								new $.zui.Messager("输入不能为空", {
				 			        icon: 'bell' // 定义消息图标
				 			    }).show();
							}else if(data=="rep"){
								new $.zui.Messager("该药品类型已存在！", {
				 			        icon: 'bell' // 定义消息图标
				 			    }).show();		
							}else if(data=="yes"){
							    new $.zui.Messager("药品大类修改成功", {
				 			        icon: 'bell' // 定义消息图标
				 			    }).show();
							    $('#alterbigModal').modal('hide', 'fit')
								initbigtype();
								removeadd();
							    init();
							}else if(data=="no"){
							    new $.zui.Messager("药品大类修改失败", {
				 			        icon: 'bell' // 定义消息图标
				 			    }).show();
							}					
						},
						error:function(e){
							    new $.zui.Messager('药品类型修改请求失败！', {
				 			        icon: 'bell' // 定义消息图标
				 			    }).show();	
						}			
					})			
				}	 
			}	
		}
	}
	
	
	
	
</script>
