
//初始化菜单
function init() {
	var htmlData='';
	$.ajax({
		url: "../initMenu.action",
		type: "post",
		data: "",
		dataType: "json",
		success: function(data) {
			console.log(data);
			var menus = treeData(data);
			// 获取 tree 实例
			var myTree = $('#myTree').data('zui.tree');
			// 更新数据
			console.log("生成的树"+menus);
			console.log("1231231231---"+myTree);
			if(myTree == undefined){
				$('#myTree').tree({data: menus});
			}else{
				myTree.reload(menus);
			}
			
			
			
			
			
//			 for (var i = 0; i < data.length; i++) {
//			 	htmlData += '<p>'+data[i].pname+'</p>';
//			 
//			 	for (var j = 0; j < data[i].listDtos.length; j++) {
//			 		htmlData += ' <ul id="treeMenu" class="tree tree-menu tree-animate" data-ride="tree" > ';
//			 		htmlData += '<li>'
//			 		+' <a _ngcontent-xja-c6="" class="btn-content">'+ data[i].listDtos[j].menuname+''
//			 		+' <button class="btn btn-link btn-mini button_but" type="button" data-toggle="modal" data-target="#submenu" onclick="getPrimaryMenuId('+data[i].listDtos[j].menuid+')"> <i class="icon icon-plus"></i> 添加子菜单 </button> '
//			 		+' <button class="btn btn-link btn-mini button_but" type="button" data-toggle="modal" data-target="#modifyprimarymenu"> <i class="icon icon-edit"></i> 编辑 </button> '
//			 		+'<button class="btn btn-link btn-mini button_but" type="button" onclick="deletePrimaryMenu('+data[i].listDtos[j].menuid+')"> <i class="icon icon-times"></i> 删除 </button>'
//			 		+' </a> ';
//			 		htmlData += ' <ul> ';
//			 		for (var l = 0; l < data[i].listDtos[j].secondMenu.length; l++) {
//			 				htmlData += ' <li> '
//			 				+' <a href="#">'+data[i].listDtos[j].secondMenu[l].limitname+''
//			 				+' <button class="btn btn-link btn-mini button_but" type="button" onclick="getSubMenuId('+data[i].listDtos[j].secondMenu[l].limitid+',\''+data[i].listDtos[j].secondMenu[l].limitname+'\',\''+data[i].listDtos[j].secondMenu[l].url+'\')"> <i class="icon icon-edit"></i> 编辑 </button> '
//			 				+' <button class="btn btn-link btn-mini button_but" type="button" onclick="deleteSecondaryMenu('+data[i].listDtos[j].secondMenu[l].limitid+')"> <i class="icon icon-times"></i> 删除 </button> '
//			 				+' </a></li> ';
//			 		}
//			 		htmlData += ' </ul> </ul> ';
//			 	}
//			 	htmlData += ' </li> ';
//			 }
//			 console.log(htmlData);
//			$('#nav_menu').html(htmlData);
//			var script = document.createElement("script");
//			script.src = "../3rd/zui/js/zui.min.js";
//			document.body.appendChild(script);
		},error: function(e) {
			console.log(e)
		}
	});
}

init();

function treeData(data) {
	var menus = [];
	for (var i = 0; i < data.length; i++) {
		var items = data[i].listDtos;
		var itemarr = [];
		// 初始化子选项
		if (items != null && items.length > 0) {
			for ( var j in items) {
				var submenus = items[j].secondMenu;
				var submenuarr = [];

				if(submenus != null && submenus.length > 0){
					for ( var k in submenus) {
						var submenu={
								html : '<a href="#">'+submenus[k].limitname+''
				 				+' <button class="btn btn-link btn-mini button_but" type="button" onclick="getSubMenuId('+submenus[k].limitid+',\''+submenus[k].limitname+'\',\''+submenus[k].url+'\')"> <i class="icon icon-edit"></i> 编辑 </button> '
				 				+' <button class="btn btn-link btn-mini button_but" type="button" onclick="deleteSecondaryMenu('+submenus[k].limitid+')"> <i class="icon icon-times"></i> 删除 </button> '
				 				+' </a>',	
				 				open: true,
						}
						submenuarr.push(submenu);
					}
				}
				
				
				var item = {
					html : '<a _ngcontent-xja-c6="" class="btn-content">'+items[j].menuname+''
			 		+' <button class="btn btn-link btn-mini button_but" type="button" data-toggle="modal" data-target="#submenu" onclick="getPrimaryMenuId('+items[j].menuid+')"> <i class="icon icon-plus"></i> 添加子菜单 </button> '
			 		+' <button class="btn btn-link btn-mini button_but" type="button" data-toggle="modal" data-target="#modifyprimarymenu"> <i class="icon icon-edit"></i> 编辑 </button> '
			 		+'<button class="btn btn-link btn-mini button_but" type="button" onclick="deletePrimaryMenu('+items[j].menuid+')"> <i class="icon icon-times"></i> 删除 </button>'
			 		+' </a> ',
			 		open: true,
			 		children : submenuarr
				};
				itemarr.push(item);
			}
		}
		// 父级选项
		var menu = {
			html : '<a _ngcontent-xja-c6="" class="btn-content">'+ data[i].pname+'</a> ',
			open: true,
			children : itemarr
		};
		menus.push(menu);
		
	}
	return menus;
}







//删除一级菜单
function deletePrimaryMenu(e) {
	layer.confirm('是否确认删除？', {
		btn: ['确认删除', '取消'] //按钮
	}, function(index) {
		layer.close(index);
		$.ajax({
			url:"../deleteMenu.action",
			type:"post",
			data:"mid="+e,
			dataType:"json",
			success:function(data){
				console.log(data);
				if(data.result == "yes"){
					layer.ready(function(){
						  layer.msg('删除成功。');
						  init();
					}); 
				}else{
					layer.ready(function(){
						  layer.msg('删除失败。');
					}); 
				}
			},error:function(e){
				console.log(e);
				layer.ready(function(){
					  layer.msg('请求失败。');
				}); 
			}
		});
	}, function() {
		//按钮二的回调
	});
}

//删除二级菜单
function deleteSecondaryMenu(e) {
	layer.confirm('是否确认删除？', {
		btn: ['确认删除', '取消'] //按钮
	}, function(index) {
		//按钮一的回调
		layer.close(index);
		$.ajax({
			url:"../deleteLimit.action",
			type:"post",
			data:"lid="+e,
			dataType:"json",
			success:function(data){
				console.log(data);
				if(data.result == "yes"){
					layer.ready(function(){
						  layer.msg('删除成功。');
						  init();
					}); 
				}else{
					layer.ready(function(){
						  layer.msg('删除失败。');
					}); 
				}
			},error:function(e){
				console.log(e);
				layer.ready(function(){
					  layer.msg('请求失败。');
				}); 
			}
		});
	}, function() {
		//按钮二的回调
	});
}


//添加一级菜单
document.getElementById('addMenu').onclick = function() {
	var title = document.getElementById('primarymenu_input_title').value.trim();
	var icon = document.getElementById('primarymenu_sel_icon').value.trim();
	var port = document.getElementById('primarymenu_sel_port').value.trim();
	console.log(title);
	console.log(icon);
	console.log(port);
	if(title == "" || icon == -1 || port == -1){
		layer.ready(function(){
		  layer.msg('请完善数据');
		});   
	}else{
		$.ajax({
			url:"../addMenu.action",
			type:"post",
			data:"title="+title+"&icon="+icon+"&port="+port,
			dataType:"json",
			success:function(data){	
				console.log(data);
				if(data.result == "yes"){
					layer.ready(function(){
						  layer.msg('添加成功');
					}); 
					$('#primarymenu').modal('hide', 'fit');	
					init();
				}else{
					layer.ready(function(){
						layer.msg('添加失败');
					}); 
				}
			},error:function(e){
				console.log(e);
				layer.ready(function(){
					  layer.msg('请求失败。');
				});  
			}
		});
	}
}

//获取一级菜单ID
var primaryMenuId = undefined;
//获取是谁点击添加子菜单或修改菜单的ID
function getPrimaryMenuId(e) {
	subMenuId = undefined;
	primaryMenuId = e;
	console.log(primaryMenuId);
}

//点击一级菜单编辑按钮
function getPrimaryMenuInf(id,title,icon) {
	subMenuId = undefined;
	primaryMenuId = id;
	$('#modifyprimarymenu_input_title').val(title);
	$('#modifyprimarymenu_sel_icon').val(icon);
	$('#modifyprimarymenu').modal('show', 'fit')
	console.log(primaryMenuId);
}

//修改一级菜单
document.getElementById('modifyMenu').onclick = function() {
	
	var title = document.getElementById('modifyprimarymenu_input_title').value.trim();
	var icon = document.getElementById('modifyprimarymenu_sel_icon').value.trim();
	if(title == "" || icon == -1){
		layer.ready(function(){
			  layer.msg('请完善数据');
		}); 
	}else{
		$.ajax({
			url:"../modifyMenu.action",
			type:"post",
			data:"title="+title+"&id="+primaryMenuId+"&icon="+icon,
			dataType:"json",
			success:function(data){	
				console.log(data);
				if(data.result == "yes"){
					layer.ready(function(){
						  layer.msg('修改成功。');
						  primaryMenuId = undefined;
						  $('#modifyprimarymenu').modal('hide', 'fit');	
						  init();
					}); 
				}else{
					layer.ready(function(){
						  layer.msg('修改失败。');
					}); 
				}
			},error:function(e){
				console.log(data);
				layer.ready(function(){
					  layer.msg('请求失败。');
				});
			}
		});
	}
	console.log("修改一级菜单")
	
}






//获取二级级菜单ID
var subMenuId = undefined;
//获取是谁点击添加子菜单或修改菜单的ID
function getSubMenuId(lid,title,url) {
	primaryMenuId = undefined;
	subMenuId = lid;
	console.log(subMenuId);
	$('#submenu_title_input').val(title);
	$('#submenu_url_input').val(url);
	$('#submenu').modal('show', 'fit')
	
}

//添加子菜单或修改子菜单
document.getElementById('modifySubmenuAndAddSubmenu').onclick = function() {
	if (primaryMenuId != undefined) {
		console.log("添加子级菜单")
		var title = document.getElementById('submenu_title_input').value.trim();
		var url = document.getElementById('submenu_url_input').value.trim();
		console.log(title);
		console.log(url);
		if(title == "" || url == "" ){
			layer.ready(function(){
				  layer.msg('请完善数据');
			});   
		}else{
			$.ajax({
				url:"../addLimit.action",
				type:"post",
				data:"title="+title+"&url="+url+"&primaryMenuId="+primaryMenuId,
				dataType:"json",
				success:function(data){	
					console.log(data);
					if(data.result == "yes"){
						layer.ready(function(){
							  layer.msg('添加成功');
//							  location.reload();
						}); 
						$('#submenu').modal('hide', 'fit');	
						init();
					}else{
						layer.ready(function(){
							layer.msg('添加失败');
						}); 
					}
				},error:function(e){
					console.log(e);
					layer.ready(function(){
						  layer.msg('请求失败。');
					});  
				}
			});
		}
	} else if (subMenuId != undefined) {
		console.log("修改二级菜单");
		var title = document.getElementById('submenu_title_input').value.trim();
		var url = document.getElementById('submenu_url_input').value.trim();
		console.log(title);
		console.log(url);
		if(title == "" || url == "" ){
			layer.ready(function(){
				  layer.msg('请完善数据');
			});   
		}else{
			$.ajax({
				url:"../modifyLimit.action",
				type:"post",
				data:"title="+title+"&url="+url+"&lid="+subMenuId,
				dataType:"json",
				success:function(data){	
					console.log(data);
					if(data.result == "yes"){
						layer.ready(function(){
							  layer.msg('修改成功');
//							  location.reload();
						}); 
						$('#submenu').modal('hide', 'fit');	
						init();
					}else{
						layer.ready(function(){
							layer.msg('修改失败');
						}); 
					}
				},error:function(e){
					console.log(e);
					layer.ready(function(){
						  layer.msg('请求失败。');
					});  
				}
			});
		}
	}
}



//document.getElementById('nav_menu').onclick = function(e){
//	var tar = e.target;
//	console.log(tar);
//}
