<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<!-- ZUI 标准版压缩后的 CSS 文件 -->
		<link rel="stylesheet" href="../3rd/zui/css/zui.min.css">
		<link rel="stylesheet" type="text/css" href="../css/menu.css" />
		<!-- ZUI Javascript 依赖 jQuery -->
		<script src="../3rd/jq/jquery.min.js"></script>
		<!-- <script src="../js/initMenuData.js" type="text/javascript" charset="utf-8"></script> -->
		<title></title>
	</head>
	<body>

		<!-- 添加菜单对话框 start -->
		<div class="modal fade" id="primarymenu">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span></button>
						<h4 class="modal-title">
							<i class="icon icon-edit"></i>&nbsp;菜单编辑
						</h4>
					</div>
					<div class="modal-body">
						<!--内容 start -->
						<!-- 标题 -->
						<div class="input-control has-label-left div_interval">
							<input id="primarymenu_input_title" type="text" class="form-control" placeholder="">
							<label for="primarymenu_input_title" class="input-control-label-left"><i class="icon icon-file-text"></i>标题:</label>
						</div>
						<!-- 选择图标 -->
						<select id="primarymenu_sel_icon" class="form-control div_interval">
							<option value="-1">选择图标</option>
							<option value="layui-icon-username">layui-icon-username</option>
							<option value="layui-icon-auz">layui-icon-auz</option>
							<option value="layui-icon-website">layui-icon-website</option>
							<option value="layui-icon-console">layui-icon-console</option>
							<option value="layui-icon-chart">layui-icon-chart</option>
						</select>
						<!-- 选择所属端 -->
						<select id="primarymenu_sel_port" class="form-control div_interval">
							<option value="-1">选择端口</option>
							<option value="1">药房端</option>
							<option value="2">药库端</option>
							<option value="3">管理端</option>
						</select>
						<!--内容 end -->
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="addMenu">
							<i class="icon icon-save"></i> 保存
						</button>
						<button type="button" class="btn btn-danger" data-dismiss="modal">
							<i class="icon icon-times"></i> 取消
						</button>
					</div>
				</div>
			</div>
		</div>
		<!-- 添加菜单对话框 end -->

		<!-- 修改菜单对话框 start -->
		<div class="modal fade" id="modifyprimarymenu">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span></button>
						<h4 class="modal-title">
							<i class="icon icon-edit"></i>&nbsp;菜单编辑
						</h4>
					</div>
					<div class="modal-body">
						<!--内容 start -->
						<!-- 标题 -->
						<div class="input-control has-label-left div_interval">
							<input id="modifyprimarymenu_input_title" type="text" class="form-control" placeholder="">
							<label for="modifyprimarymenu_input_title" class="input-control-label-left"><i class="icon icon-file-text"></i>标题:</label>
						</div>
						<!-- 选择图标 -->
						<select id="modifyprimarymenu_sel_icon" class="form-control div_interval">
							<option value="-1">选择图标</option>
							<option value="layui-icon-username">layui-icon-username</option>
							<option value="layui-icon-auz">layui-icon-auz</option>
							<option value="layui-icon-website">layui-icon-website</option>
							<option value="layui-icon-console">layui-icon-console</option>
							<option value="layui-icon-chart">layui-icon-chart</option>
						</select>
						<!--内容 end -->
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="modifyMenu">
							<i class="icon icon-save"></i> 保存
						</button>
						<button type="button" class="btn btn-danger" data-dismiss="modal">
							<i class="icon icon-times"></i> 取消
						</button>
					</div>
				</div>
			</div>
		</div>
		<!-- 修改菜单对话框 end -->


		<!-- 添加子菜单对话框 start -->
		<div class="modal fade" id="submenu">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span></button>
						<h4 class="modal-title">
							<i class="icon icon-edit"></i>&nbsp;菜单编辑
						</h4>
					</div>
					<div class="modal-body">
						<!--内容 start -->
						<!-- 标题 -->
						<div class="input-control has-label-left div_interval">
							<input id="submenu_title_input" type="text" class="form-control" placeholder="">
							<label for="submenu_title_input" class="input-control-label-left"><i class="icon icon-file-text"></i>标题:</label>
						</div>
						<!-- url -->
						<div class="input-control has-label-left div_interval">
							<input id="submenu_url_input" type="text" class="form-control" placeholder="">
							<label for="submenu_url_input" class="input-control-label-left"><i class="icon icon-link"></i>URL:</label>
						</div>
						<!--内容 end -->
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="modifySubmenuAndAddSubmenu">
							<i class="icon icon-save"></i> 保存
						</button>
						<button type="button" class="btn btn-danger" data-dismiss="modal">
							<i class="icon icon-times"></i> 取消
						</button>
					</div>
				</div>
			</div>
		</div>
		<!-- 添加子菜单对话框 end -->

		<div class="div_external">
			<div class="panel panel-primary ">
				<div class="panel-heading">
					<span>
						菜单管理
					</span>
				</div>
				<div class="panel-body">
					<div class="add_div">
						<button class="btn btn-sm btn-info" data-toggle="modal" data-target="#primarymenu">
							<i class="icon icon-plus"></i> 添加一级菜单
						</button>
					</div>

					<nav class="menu div_menu" id="nav_menu"  style="width: 60%">
						<!-- 创建一个空的树，需要包含 .tree CLASS -->
						<ul  class="tree tree-menu tree-animate"  id="myTree"></ul>
					
					</nav>

				</div>
			</div>
		</div>
		<!-- layer弹框 -->
		<script src="../3rd/layer/layer.js"></script>
		<!-- ZUI 标准版压缩后的 JavaScript 文件 -->
		<script src="../3rd/zui/js/zui.min.js" id="zui_js" ></script>
		<script src="../js/menu.js" type="text/javascript" charset="utf-8"></script>
		<!-- <script src="../js/initMenuData.js" type="text/javascript" charset="utf-8"></script> -->
	</body>
</html>
