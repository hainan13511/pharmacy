<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="pharmacy.common.model.*,pharmacy.web.store.model.dto.MenuDto,java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>药品发药</title>
<link rel="stylesheet" href="../3rd/zui/css/zui.min.css">
<link href="../3rd/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="../css/drugSend.css">
</head>
<body>
	<%
	    UserInfo users = (UserInfo) session.getAttribute("user");
				if (users == null) {
	%>
	<script type="text/javascript">
		window.top.location.href = "login.jsp";
	</script>
	<%
	    return;
	    }
	%>
	<!--隐藏域，保存用户名和id -->
	<input type="hidden" name="<%=users.getUname()%>"
		value="<%=users.getUid()%>" id="h_user">
	<div class="panel panel-primary myPanel">
		<div class="panel-heading">
			<h4>药品发药</h4>
		</div>
		<div class="panel-body ">
			<div class="container-fluid div_seek">
				<div class="row">
					<div class="col-lg-1 col-md-2 col-sm-6">
						<button class="btn btn-primary" id="btn_add" type="button"
							data-toggle="tooltip" title="添加药品">
							<i class="icon icon-plus"></i>添加药品
						</button>
					</div>
					<div class="col-lg-1 col-md-2 col-sm-6">
						<button class="btn btn-danger" id="btn_close" type="button"
							data-toggle="tooltip" title="取消">
							<i class="icon icon-times"></i>取消
						</button>
					</div>
				</div>
			</div>

			<div class="table-responsive">
				<table class="table table-hover table-bordered" id="myTable">
					<thead>
						<tr>
							<th>药品名</th>
							<th>规格</th>
							<th>剂型</th>
							<th>拼音码</th>
							<th>成本价</th>
							<th>零售价</th>
							<th>数量</th>
							<th>成本金额</th>
							<th>零售金额</th>
							<th>操作</th>
						</tr>
					</thead>
				</table>
			</div>
			<div class="div_page">
				<div class="container-fluid ">
					<div class="row">
						<div class="col-lg-1 col-md-2 col-sm-6 col-left">
							<input type="text"
								class="form-control ng-pristine ng-valid ng-touched"
								id="countPrices" placeholder="0" disabled="disabled"> <label
								for="mcmStartTime" class="input-control-label-left text-right">总计:</label>
							<label for="inputAccountExample1" class="input-control-icon-left"><i
								class="icon icon-cubes"></i></label>
						</div>
						<div class="col-lg-1 col-md-2 col-sm-6 col-right">
							<button class="btn btn-success" id="btn_send" type="button"
								data-toggle="tooltip" title="发药">
								<i class="icon icon-location-arrow"></i>发药
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 新增Modal -->
	<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true" onclick="emptySearch()">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<i class="icon icon-plus"></i>添加药品
					</h4>
				</div>
				<div class="modal-body">
					<div class="div_search">
						<div class="input-group">
							<div
								class="input-control search-box search-box-circle has-icon-left has-icon-right search-example"
								id="searchboxExample">
								<input id="searchDrugName" type="search"
									class="form-control search-input" placeholder="请输入药品名进行搜索">
								<label for="inputSearchExample3"
									class="input-control-icon-left search-icon"><i
									class="icon icon-search"></i></label>
							</div>
							<span class="input-group-btn">
								<button class="btn btn-primary" id="btnSearch" type="button">搜索</button>
							</span>
						</div>
					</div>
					<div class="table-responsive table-top">
						<table class="table table-hover table-bordered" id="searchTable">
							<thead>
								<tr>
									<th>序号</th>
									<th>药品名</th>
									<th>操作</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="btn_confirm">确定</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="mySmModal" data-backdrop="false">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="input-control has-label-left">
					<input type="text" class="form-control"
						maxlength="9" onkeyup="this.value=this.value.replace(/\D|^0/g,'')"
						onafterpaste="this.value=this.value.replace(/\D|^0/g,'')"
						id="addNum" placeholder="请输入数量"> 
						<label for="inputAccountExample2"
						class="input-control-label-left">数量:</label>
				</div>
				<div class="modal-footer">
				    <button type="button" class="btn btn-default" data-dismiss="modal" onclick="emptyNum()">取消</button>
                    <button type="button" class="btn btn-primary" id="btn_num_confirm">确定</button>
                </div>
			</div>
			
		</div>
	</div>
	<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
	<script src="../3rd/jquery-3.4.1.min.js"></script>
	<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
	<script src="../3rd/zui/js/zui.min.js"></script>
	<script src="../3rd/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="../3rd/layer/layer.js"></script>
	<script src="../js/drugSend.js"></script>
</body>
</html>