
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


