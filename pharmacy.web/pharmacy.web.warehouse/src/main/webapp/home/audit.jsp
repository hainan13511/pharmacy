<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>出库审核</title>
		<link rel="stylesheet" type="text/css" href="../css/audit.css" />
		<link rel="stylesheet" href="../3rd/zui/css/zui.min.css" />
		<link href="../3rd/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
		        <style type="text/css">
            .myPanel {
                margin: 30px auto;
                width: 98%;
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

            .div_seek .col-lg-3>input {
                padding-left: 95px;
            }

            .div_seek .col-lg-3>label {
                padding-left: 12px;
                padding-top: 5px;
            }

            .div_seek .col-lg-2>select {
                padding-left: 110px;
            }

            .div_seek .col-lg-2>label {
                padding-left: 12px;
                padding-top: 5px;
            }

            .col-lg-3>.input-control-label-left {
                width: 95px;
            }

            .col-lg-2>.input-control-label-left {
                width: 95px;
            }

            .div_seek>.row>div {
                padding: 5px 3px;
            }
        </style>
	</head>
	<body>
	<div class="div_external"	>
		<!-- 头部标题 -->
			<div class="panel panel-primary">
				<div class="panel-heading">
					出库审核
				</div>
				
				<!-- 主界面 -->
				<div class="panel-body">
				
			 		 <div class="container-fluid div_seek">
	                    <div class="row">
	                        <div class="col-lg-1 col-md-2 col-sm-6"><button class="btn btn-warning" type="button" id="btn_refresh" data-toggle="tooltip" title="刷新"><i
	                                 class="icon icon-refresh"></i>刷新</button></div>
	                        <div class="col-lg-3 col-md-3 col-sm-6">
	                            <input type="text" class="form-control ng-pristine ng-valid ng-touched" id="text-drug"  placeholder="请输入药品名">
	                            <label for="mcmStartTime" class="input-control-label-left text-right">药品名称:</label>
	                            <label for="inputAccountExample1" class="input-control-icon-left"><i class="icon icon-file-text"></i></label>
	                        </div>
	                        <div class="col-lg-3 col-md-3 col-sm-6">
	                            <input type="text" class="form-control ng-pristine ng-valid ng-touched" id="text-uname"  placeholder="请输入申请人名称">
	                            <label for="mcmStartTime" class="input-control-label-left text-right">申请人:</label>
	                            <label for="inputAccountExample1" class="input-control-icon-left"><i class="icon icon-file-text"></i></label>
	                        </div>
	                        <div class="col-lg-1 col-md-5 col-sm-6">
								<select id="sel_state"  class="form-control">
								  <option value="-1">全部状态</option>
								  <option value="0">待审核</option>
								  <option value="1">已通过</option>
								  <option value="2">未通过</option>
								</select>
							</div> 
	                        <div class="col-lg-1 col-md-2 col-sm-6"><button class="btn btn-info" type="button"  id="btn-search" data-toggle="tooltip" title="搜索"><i
	                                 class="icon icon-search"></i>搜索</button></div>
	                        <div class="col-lg-1 col-md-2 col-sm-6"><button class="btn btn-danger" id="btn-null"type="button" data-toggle="tooltip"
	                             title="清空"><i class="icon icon-trash"></i>清空</button></div>
	                    </div>
	                </div> 
	<!-- 			
					<div class="panel-add">
						<button class="btn btn-warning" id="btn-refresh" type="button" style="width: 80px;"><i class="icon icon-spin icon-refresh"></i> 刷 新</button>
					</div>
					<div class="col-lg-3 col-md-5 col-sm-6">
						<input id="text-uname" type="text" style="padding-left:80px;" class="form-control ng-pristine ng-valid ng-touched">
						<label for="mcmStartTime" style="width:100px" class="input-control-label-left text-right">申请人：</label>
					</div>
					<div class="col-lg-3 col-md-5 col-sm-6">
						<input id="text-drug" type="text" style="padding-left:80px" class="form-control ng-pristine ng-valid ng-touched">
						<label for="mcmStartTime" style="width:100px" class="input-control-label-left text-right">药品名称：</label>
					</div>
					<div class="col-lg-1 col-md-5 col-sm-6">
						<select id="sel_state"  class="form-control">
						  <option value="-1">全部状态</option>
						  <option value="0">待审核</option>
						  <option value="1">已通过</option>
						  <option value="2">未通过</option>
						</select>
					</div>
					<div id="">
						<button class="btn btn-success" id="btn-search" type="button" style="width: 80px;"><i class="icon icon-search"></i> 搜 索</button>
						<button class="btn btn-danger" id="btn-null" type="button" style="width: 80px;"><i class="icon icon-trash"></i> 清 空</button>
					</div>  -->
					<table class="table table-hover table-bordered" id="table-edit">
						<thead>
						<tr align="center">
							<th>序号</th>
							<th>药品名称</th>
							<th>数量</th>
							<th>申请人</th>
							<th>申请时间</th>
							<th>申请状态</th>
							<th>操作</th>
						</tr>
						<thead>
						<tbody>
						</tbody>
					</table>
					<div id="" align="center">
							<ul class="pager pager-pills">
								<li class="previous "><a id="up-page" >« 上一页</a></li>
								<li><span id="lab_page">				
								<span id="nowpage"> 1 </span>
								<span id="midpage"> / </span>
								<span id="allpage"> 1 </span>
								</span></li>
								<li class="next"><a id="down-page" >下一页
								»</a></li>
							</ul>
					</div>
				</div>
			</div>
		</div>
		<script src="../3rd/zui/lib/jquery/jquery.js"></script>
		<!-- layer弹框 -->
		<script src="../3rd/layer/layer.js"></script>
		<script src="../3rd/zui/js/zui.min.js"></script>
		<script src="../3rd/zui/lib/datetimepicker/datetimepicker.min.js"></script>
		<script src="../js/audit.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>