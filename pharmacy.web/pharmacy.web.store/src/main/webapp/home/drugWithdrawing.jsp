<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>药品返库</title>
		<link rel="stylesheet" href="../3rd/zui/css/zui.min.css" />
		<link href="../3rd/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="../css/drugWithdrawing.css" />
	</head>
	<body>
		<!-- 编辑退还数量 -->
		<div class="modal fade" id="userModal">
			<div class="modal-dialog" style="width: 300px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true" href="javascript:void(0);">×</span><span class="sr-only">关闭</span></button>
						<h4 class="modal-title"><i class="icon icon-edit"></i>返库数量</h4>
					</div>
					<div class="panel-body">
						<div class="drug-list1">
							<input id="drug-count" type="text" class="form-control"  placeholder="退还数量" AUTOCOMPLETE="off" >
						</div>
						<div style="height: 10px;"></div>
						<div id="" align="center">
							<button class="btn btn-primary" id="out-go" type="button" style="width: 100px;height: 30px;"><i class="icon icon-save"></i>
								保 存</button>
							<button class="btn btn-primary" id="shop-exit" type="button" style="width: 100px;height: 30px;"><i class="icon icon-reply"></i>
								返 回</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="div_external">
			<div class="panel panel-primary">
			<!-- 头部标题 -->
				<div class="panel-heading">
					药品返库
				</div>
				<!-- 主界面 -->
				<div class="panel-body">
				
					<div class="container-fluid div_seek">
					    <div class="row">
					        <div class="col-lg-1 col-md-2 col-sm-6"><button class="btn btn-warning" type="button" id="btn-refresh" data-toggle="tooltip" title="刷新"><i
					                 class="icon icon-refresh"></i>刷新</button></div>
					        <div class="col-lg-3 col-md-3 col-sm-6">
					            <input type="text" class="form-control ng-pristine ng-valid ng-touched"  id="text-drug"  placeholder="请输入药品名">
					            <label for="mcmStartTime" class="input-control-label-left text-right">药品名称:</label>
					            <label for="inputAccountExample1" class="input-control-icon-left"><i class="icon icon-file-text"></i></label>
					        </div>
					        <div class="col-lg-3 col-md-3 col-sm-6">
					            <input type="text" class="form-control ng-pristine ng-valid ng-touched" id="text-batch"  placeholder="请输入药品批号">
					            <label for="mcmStartTime" class="input-control-label-left text-right">药品批号:</label>
					            <label for="inputAccountExample1" class="input-control-icon-left"><i class="icon icon-file-text"></i></label>
					        </div>
					        <div class="col-lg-1 col-md-2 col-sm-6"><button class="btn btn-info" type="button" id="btn-search" data-toggle="tooltip" title="搜索"><i
					                 class="icon icon-search"></i>搜索</button></div>
					        <div class="col-lg-1 col-md-2 col-sm-6"><button class="btn btn-danger" id="btn-null" type="button" data-toggle="tooltip"
					             title="清空"><i class="icon icon-trash"></i>清空</button></div>
					    </div>
					</div>
				
				
					<!-- <div class="panel-add">
						<button class="btn btn-warning" id="btn-refresh" type="button" style="width: 80px;"><i class="icon icon-spin icon-refresh"></i> 刷 新</button>
					</div>
					<div class="panel-search">
						<input id="text-drug" type="text" class="form-control" placeholder="药品名称">
						<input id="text-batch" type="text" class="form-control" placeholder="批号">
					</div>
					<div id="">
						<button class="btn btn-success" id="btn-search" type="button" style="width: 80px;"><i class="icon icon-search"></i> 搜 索</button>
						<button class="btn btn-danger" id="btn-null" type="button" style="width: 80px;"><i class="icon icon-trash"></i> 清 空</button>
					</div> -->
					<table class="table table-bordered" id="table-edit">
						<thead>
						<tr align="center">
							<th>序号</th>
							<th>药品名称</th>
							<th>单位</th>
							<th>库存数量</th>
							<th>批号</th>
							<th>操作</th>
						</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<div id="" align="center">
							<ul class="pager pager-pills">
								<li class="previous "><a href="javascript:void(0);" id="up-page" onclick="pre()">上一页</a></li>
								<li><span id="lab_page">				
								<span id="nowpage"> 1 </span>
								<span id="midpage"> / </span>
								<span id="allpage"> 1 </span></span></li>
								<li class="next"><a href="javascript:void(0);" id="down-page" onclick="next()">下一页
								</a></li>
							</ul>
					</div>
				</div>
			</div>
		</div>
		<script src="../3rd/zui/lib/jquery/jquery.js"></script>
		<script src="../3rd/zui/js/zui.min.js"></script>
		<script src="../3rd/zui/lib/datetimepicker/datetimepicker.min.js"></script>
		<script src="../js/drugWithdrawing.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>