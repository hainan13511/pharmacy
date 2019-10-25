<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="pharmacy.common.model.*,pharmacy.web.store.model.dto.MenuDto,java.util.List" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>药品调价</title>
        <link rel="stylesheet" href="../3rd/zui/css/zui.min.css">
        <link href="../3rd/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
        <style type="text/css">
            .myPanel {
                margin: 5px auto;
                width: 99.5%;
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
    <body>
        <div class="panel panel-primary myPanel">
            <div class="panel-heading">
                <h4>药品调价</h4>
            </div>
            <div class="panel-body ">
                <div class="container-fluid div_seek">
                    <div class="row">
                        <div class="col-lg-1 col-md-2 col-sm-6"><button class="btn btn-warning" type="button" id="btn_refresh" data-toggle="tooltip" title="刷新"><i
                                 class="icon icon-refresh"></i>刷新</button></div>
                        <div class="col-lg-3 col-md-3 col-sm-6">
                            <input type="text" class="form-control ng-pristine ng-valid ng-touched" id="drugName"  placeholder="请输入药品名">
                            <label for="mcmStartTime" class="input-control-label-left text-right">药品名称:</label>
                            <label for="inputAccountExample1" class="input-control-icon-left"><i class="icon icon-file-text"></i></label>
                        </div>
                        <div class="col-lg-1 col-md-2 col-sm-6"><button class="btn btn-info" type="button"  id="btn_query" data-toggle="tooltip" title="搜索"><i
                                 class="icon icon-search"></i>搜索</button></div>
                        <div class="col-lg-1 col-md-2 col-sm-6"><button class="btn btn-danger" id="btn_del" type="button" data-toggle="tooltip"
                             title="清空"><i class="icon icon-trash"></i>清空</button></div>
                    </div>
                </div>

                <div class="table-responsive">
                    <table class="table table-hover table-bordered" id="myTable">
                        <thead>
                            <tr>
                                <th>序号</th>
                                <th>药品名</th>
                                <th>分类</th>
                                <th>规格</th>
                                <th>剂型</th>
                                <th>成本价</th>
                                <th>零售价</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                    </table>
                </div>
                <div class="div_page">
                    <ul class="pager pager-pills">
                        <li class="previous "><a href="javascript:void(0);" onclick="pre()">上一页</a></li>
                        <li><span id="lab_page">1/1</span></li>
                        <li class="next"><a href="javascript:void(0);" onclick="next()">下一页</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- 修改Modal -->
        <div class="modal fade bs-example-modal-sm" id="myAlertModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
            <div class="modal-dialog modal-sm" role="document">
                <form  class="form-horizontal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">申请调价</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="exampleInputEmail3">申请人：</label>
                                <input type="text" class="form-control" id="return_user" value="<%=users.getUname() %>"  disabled="disabled">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputEmail3">药品名：</label>
                                <input type="text" class="form-control" id="return_name" disabled="disabled">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputEmail3">调整价格：<small>(调整的是零售价)</small></label>
                                <input type="text" class="form-control" id="return_price"  maxlength="9" oninput = "value=value.replace(/[^\d]/g,'')"  >
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
        <input type="hidden" name="<%=users.getUname() %>" value="<%=users.getUid() %>" id="h_user">
        <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
        <script src="../3rd/jquery-3.4.1.min.js"></script>
        <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
        <script src="../3rd/zui/js/zui.min.js"></script>
        <script src="../3rd/zui/lib/datetimepicker/datetimepicker.min.js"></script>
        <script src="../js/drugAdjustPrice.js"></script>
    </body>
</html>