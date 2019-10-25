<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="pharmacy.common.model.*,pharmacy.web.store.model.dto.MenuDto,java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>药品报损</title>
<link rel="stylesheet" href="../3rd/zui/css/zui.min.css">
<link href="../3rd/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="../css/drugBreakage.css">
</head>
<body>
    <%
    UserInfo users = (UserInfo) session.getAttribute("user");
    if(users==null){
    %>
     <script type="text/javascript">
        window.top.location.href = "login.jsp";
        </script>
    <%
            return;
        }
    %>
	<div class="panel panel-primary myPanel">
		<div class="panel-heading">
			<h4>药品报损</h4>
		</div>
		<div class="panel-body ">
			<div class="container-fluid div_seek">
				<div class="row">
					<div class="col-lg-1 col-md-2 col-sm-6">
						<button class="btn btn-warning" id="btn_refresh" type="button"
							data-toggle="tooltip" title="刷新">
							<i class="icon icon-refresh"></i>刷新
						</button>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-6">
						<input type="text"
							class="form-control ng-pristine ng-valid ng-touched"
							id="drugName" placeholder="请输入药品名"> <label
							for="mcmStartTime" class="input-control-label-left text-right">药品名称:</label>
						<label for="inputAccountExample1" class="input-control-icon-left"><i
							class="icon icon-file-text"></i></label>
					</div>
					<div class="col-lg-1 col-md-2 col-sm-6">
						<button class="btn btn-info" type="button" data-toggle="tooltip"
							id="btn_query" title="搜索">
							<i class="icon icon-search"></i>搜索
						</button>
					</div>
					<div class="col-lg-1 col-md-2 col-sm-6">
						<button class="btn btn-danger" id="btn_del" type="button"
							data-toggle="tooltip" title="清空">
							<i class="icon icon-trash"></i>清空
						</button>
					</div>
				</div>
			</div>

			<div class="table-responsive">
				<table class="table table-hover table-bordered" id="myTable">
					<thead>
						<tr>
							<th>序号</th>
							<th>药品名</th>
							<th>规格</th>
							<th>剂型</th>
							<th>拼音码</th>
							<th>成本价</th>
							<th>零售价</th>
							<th>药房库存</th>
							<th>药库库存</th>
							<th>批次</th>
							<th>操作</th>
						</tr>
					</thead>
				</table>
			</div>
			<div class="div_page">
				<ul class="pager pager-pills">
					<li class="previous "><a href="javascript:void(0);"
						onclick="pre()">上一页</a></li>
					<li><span id="lab_page">1/1</span></li>
					<li class="next"><a href="javascript:void(0);"
						onclick="next()">下一页</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- 修改Modal -->
	<div class="modal fade bs-example-modal-sm" id="myAlertModal"
		tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog modal-sm" role="document">
			<form action="" class="form-horizontal">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">
							<i _ngcontent-see-c7="" class="icon icon-edit"></i>申请报损
						</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="exampleInputEmail3">操作员：</label> <input type="text"
								value="<%=users.getUname()%>" class="form-control"
								id="return_user" disabled="disabled">
						</div>
						<div class="form-group">
							<label for="exampleInputEmail3">药品名：</label> <input type="text"
								class="form-control" id="return_name" disabled="disabled">
						</div>
						<div class="form-group">
							<label for="exampleInputEmail3">批次：</label> <input type="text"
								class="form-control" id="return_batch" disabled="disabled">
						</div>
						<div class="form-group">
							<label for="exampleInputEmail3">报损数量：</label> <input type="text"
								class="form-control" id="return_num">
						</div>
						<div class="form-group">
							<label for="exampleInputEmail3">报损原因：</label> <input type="text"
								class="form-control" id="return_cause">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary" id="btn_confirm">保存</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!--隐藏域，保存用户名和id -->
	<input type="hidden" name="<%=users.getUname()%>"
		value="<%=users.getUid()%>" id="h_user">
	<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
	<script src="../3rd/jquery-3.4.1.min.js"></script>
	<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
	<script src="../3rd/zui/js/zui.min.js"></script>
	<script src="../3rd/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="../js/drugBreakage.js"></script>
</body>
</html>