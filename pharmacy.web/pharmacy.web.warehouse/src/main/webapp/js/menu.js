// 
// // 手动通过点击模拟高亮菜单项
// $('#treeMenu').on('click', 'a', function() {
//     $('#treeMenu li.active').removeClass('active');
//     $(this).closest('li').addClass('active');
// });
// 

// $('#tree').tree(options);
//初始化菜单
function init() {
	$.ajax({
		url: "../initMenu.action",
		type: "post",
		data: "",
		dataType: "json",
		success: function(data) {
			console.log(data);
			var htmlData='';
			for (var i = 0; i < data.length; i++) {
				htmlData += '<p>'+data[i].pname+'</p>';
			
				for (var j = 0; j < data[i].listDtos.length; j++) {
					htmlData += ' <ul id="treeMenu" class="tree tree-menu tree-animate" data-ride="tree" > ';
					htmlData += '<li>'
					+' <a _ngcontent-xja-c6="" class="btn-content">'+ data[i].listDtos[j].menuname+''
					+' <button class="btn btn-link btn-mini button_but" type="button" data-toggle="modal" data-target="#submenu" onclick="getPrimaryMenuId('+data[i].listDtos[j].menuid+')"> <i class="icon icon-plus"></i> 添加子菜单 </button> '
					+' <button class="btn btn-link btn-mini button_but" type="button" data-toggle="modal" data-target="#modifyprimarymenu"> <i class="icon icon-edit"></i> 编辑 </button> '
					+' </a> ';
					htmlData += ' <ul> ';
					for (var l = 0; l < data[i].listDtos[j].secondMenu.length; l++) {
							htmlData += ' <li> '
							+' <a href="#">'+data[i].listDtos[j].secondMenu[l].limitname+''
							+' <button class="btn btn-link btn-mini button_but" type="button" data-toggle="modal" data-target="#submenu"  onclick="getSubMenuId('+data[i].listDtos[j].secondMenu[l].limitid+')"> <i class="icon icon-edit"></i> 编辑 </button> '
							+' <button class="btn btn-link btn-mini button_but" type="button" onclick="deleteSecondaryMenu('+data[i].listDtos[j].secondMenu[l].limitid+')"> <i class="icon icon-times"></i> 删除 </button> '
							+' </a></li> ';
					}
					htmlData += ' </ul> </ul> ';
				}
				htmlData += ' </li> ';
			}
			document.getElementById('nav_menu').innerHTML = htmlData;
			console.log(htmlData);
		},error: function(e) {

		}
	})
}

init();












//删除一级菜单
function deletePrimaryMenu(e) {
	layer.confirm('是否确认删除？', {
		btn: ['确认删除', '取消'] //按钮
	}, function(index) {
		//按钮一的回调
		layer.close(index);
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
	}, function() {
		//按钮二的回调
	});
}


//添加一级菜单
document.getElementById('addMenu').onclick = function() {

}

//获取一级菜单ID
var primaryMenuId = undefined;
//获取是谁点击添加子菜单或修改菜单的ID
function getPrimaryMenuId(e) {
	subMenuId = undefined;
	primaryMenuId = e;
	console.log(primaryMenuId);
}



//修改一级菜单
document.getElementById('modifyMenu').onclick = function() {

	primaryMenuId = undefined;
}


//获取二级级菜单ID
var subMenuId = undefined;
//获取是谁点击添加子菜单或修改菜单的ID
function getSubMenuId(e) {
	primaryMenuId = undefined;
	subMenuId = e;
	console.log(subMenuId);
}

//添加子菜单或修改子菜单
document.getElementById('modifySubmenuAndAddSubmenu').onclick = function() {
	if (primaryMenuId != undefined) {
		console.log("添加子级菜单")
	} else if (subMenuId != undefined) {
		console.log("修改二级菜单");
	}
	primaryMenuId = undefined;
	subMenuId = undefined;
}
