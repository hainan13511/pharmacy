<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>角色管理</title>
<link rel="stylesheet" type="text/css" href="../css/role.css" />
<link rel="stylesheet" href="../3rd/zui/css/zui.min.css" />
<link href="../3rd/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
</head>
<body>
	<!-- 新增角色界面 -->
	<div class="modal fade" id="myModal">
		<div class="modal-dialog" style="width: 300px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">
						<i class="icon icon-edit"></i>新增角色
					</h4>
				</div>
				<div class="panel-body">
					<div class="">
						<input id="inputAccountExample1" type="text" class="form-control"
							placeholder="角色名称">
					</div>
					<div class="panel panel-primary limit-list1">
						<div class="panel-heading">选择菜单</div>
						<ul class="tree tree-lines" id="myTree">
						</ul>
					</div>
					<div id="" align="center">
						<button class="btn btn-primary" type="button" onclick="addRole()"
							style="width: 100px; height: 30px;">
							<i class="icon icon-save"></i> 保 存
						</button>
						<button class="btn btn-primary" id="add-exit" type="button"
							style="width: 100px; height: 30px;">
							<i class="icon icon-reply"></i> 返 回
						</button>
					</div>
				</div>

			</div>
		</div>
	</div>
	<!-- 编辑角色界面 -->
	<div class="modal fade" id="userModal">
		<div class="modal-dialog" style="width: 300px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">
						<i class="icon icon-edit"></i>角色编辑
					</h4>
				</div>
				<div class="panel-body">
					<div class="">
						<input id="updateRoleName" type="text" class="form-control"
							readonly="readonly">
					</div>
					<div class="panel panel-primary limit-list1">
						<div class="panel-heading">选择菜单</div>
						<ul class="tree tree-lines" id="userTree">

						</ul>
					</div>
					<div id="" align="center">
						<button class="btn btn-primary" type="button"
							onclick="updateRole()" style="width: 100px; height: 30px;">
							<i class="icon icon-save"></i> 保 存
						</button>
						<button class="btn btn-primary" id="alter-exit" type="button"
							style="width: 100px; height: 30px;">
							<i class="icon icon-reply"></i> 返 回
						</button>
					</div>
				</div>

			</div>
		</div>
	</div>

	<div style="padding: 5px">
		<!-- 头部标题 -->
		<div class="panel panel-primary">
			<div class="panel-heading">角色管理</div>

			<!-- 主界面 -->
			<div class="panel-body ">
				<div class="container-fluid div_seek">
					<div class="row">
						<div class="col-lg-1 col-md-2 col-sm-6">
							<button class="btn btn-primary top-btn" type="button" id="btn-add"
								data-toggle="tooltip" title="刷新">
								<i class="icon icon-plus"></i>新增
							</button>
						</div>
						<div class="col-lg-1 col-md-2 col-sm-6">
							<button class="btn btn-warning" type="button" id="btn_refresh"
								data-toggle="tooltip" title="刷新">
								<i class="icon icon-refresh"></i>刷新
							</button>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-6">
							<input type="text"
								class="form-control ng-pristine ng-valid ng-touched"
								id="text-search" value=" " placeholder="请输入用户名"> <label
								for="mcmStartTime" class="input-control-label-left text-right">用户名:</label>
							<label for="inputAccountExample1" class="input-control-icon-left"><i
								class="icon icon-file-text"></i></label>
						</div>
						<div class="col-lg-1 col-md-2 col-sm-6">
							<button class="btn btn-info" onclick="search()" type="button"
								id="btn_query" data-toggle="tooltip" title="搜索">
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
								<th>角色名称</th>
								<th>操作</th>
							</tr>
						</thead>
					</table>
					<div id="" align="center">
						<ul class="pager pager-pills">
							<li class="previous "><a href="javascript:void(0);"
								id="up-page" onclick="pre()">« 上一页</a></li>
							<li><span id="lab_page"> <span id="nowpage"> </span>
									<span id="midpage"> / </span> <span id="allpage"> </span></span></li>
							<li class="next"><a href="javascript:void(0);"
								id="down-page" onclick="next()">下一页 »</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<script src="../3rd/zui/lib/jquery/jquery.js"></script>
		<script src="../3rd/zui/js/zui.min.js"></script>
		<script src="../3rd/zui/lib/datetimepicker/datetimepicker.min.js"></script>
		<script src="../js/role.js"></script>
</body>
</html>
