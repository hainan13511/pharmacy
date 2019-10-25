<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>药品退库</title>
        <link rel="stylesheet" href="../3rd/zui/css/zui.min.css">
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
        <!--隐藏域，保存用户名和id -->
        <input type="hidden" name="张三" value="10001" id="h_user">
        <div class="panel panel-primary myPanel">
            <div class="panel-heading">
                <h4>药品退库</h4>
            </div>
            <div class="panel-body ">
                <div class="container-fluid div_seek">
                    <div class="row">
                        <div class="col-lg-1 col-md-2 col-sm-6"><button class="btn btn-warning" type="button" data-toggle="tooltip" title="刷新"><i
                                 class="icon icon-refresh"></i>刷新</button></div>
                        <div class="col-lg-3 col-md-4 col-sm-6">
                            <input type="text" class="form-control form-datetime" placeholder="请输入开始时间">
                            <label for="mcmStartTime" class="input-control-label-left text-right">开始时间:</label>
                            <label for="inputAccountExample1" class="input-control-icon-left"><i class="icon icon-calendar "></i></label>
                        </div>
                        <div class="col-lg-3 col-md-4 col-sm-6">
                            <input type="text" class="form-control form-datetime" placeholder="请输入结束时间">
                            <label for="mcmStartTime" class="input-control-label-left text-right">结束时间:</label>
                            <label for="inputAccountExample1" class="input-control-icon-left"><i class="icon icon-calendar "></i></label>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-6">
                            <input type="text" class="form-control ng-pristine ng-valid ng-touched" placeholder="请输入药品名">
                            <label for="mcmStartTime" class="input-control-label-left text-right">药品名称:</label>
                            <label for="inputAccountExample1" class="input-control-icon-left"><i class="icon icon-file-text"></i></label>
                        </div>
                        <div class="col-lg-1 col-md-2 col-sm-6"><button class="btn btn-info" type="button" data-toggle="tooltip" title="搜索"><i
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
                                <th>入库时间</th>
                                <th>药房库存</th>
                                <th>药库库存</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tr>
                            <td>1</td>
                            <td>板蓝根</td>
                            <td>2019-07-08 09:04:07</td>
                            <td>100</td>
                            <td>48</td>
                            <td><button class="btn btn-link" type="button"><i class="icon icon-edit"></i> 退库</button>
                            </td>
                        </tr>

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
                <form action="" class="form-horizontal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel"><i _ngcontent-see-c7="" class="icon icon-edit"></i>申请退库</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="exampleInputEmail3">申请人：</label>
                                <input type="text" class="form-control" id="return_user" disabled="disabled">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputEmail3">申请时间：</label>
                                <input type="text" class="form-control" id="return_date" disabled="disabled">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputEmail3">药品名：</label>
                                <input type="text" class="form-control" id="return_name" disabled="disabled">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputEmail3">剩余库存：</label>
                                <input type="text" class="form-control" id="return_num" disabled="disabled">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputEmail3">退库原因：</label>
                                <input type="text" class="form-control" placeholder="">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="submit" class="btn btn-primary">保存</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
        <script src="../3rd/jquery-3.4.1.min.js"></script>
        <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
        <script src="../3rd/zui/js/zui.min.js"></script>
        <script src="../3rd/zui/lib/datetimepicker/datetimepicker.min.js"></script>
    </body>
</html>
<script type="text/javascript">
    // 选择时间和日期
    $(".form-datetime").datetimepicker({
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1,
        format: "yyyy-mm-dd hh:ii"
    });
    $('#btn_add').click(function() {
        $('#myModal').modal('show')
    })
    $('[data-toggle="tooltip"]').tooltip({
        placement: 'bottom'
    });
    $('#btn_del').click(function() {
        new $.zui.Messager('已清空。', {
            icon: 'bell' // 定义消息图标
        }).show();
        $('#text_type').val('');
    })
    Date.prototype.Format = function(fmt) {
        var o = {
            "M+": this.getMonth() + 1, //月份 
            "d+": this.getDate(), //日 
            "H+": this.getHours(), //小时 
            "m+": this.getMinutes(), //分 
            "s+": this.getSeconds(), //秒 
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
            "S": this.getMilliseconds() //毫秒 
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[
                k]).substr(("" + o[k]).length)));
        return fmt;
    }
    $(function() {
        $("#myTable").on("click", ":button", function(event) {
            var name = $(this).closest("tr").find("td").eq(1).text();
            var num = $(this).closest("tr").find("td").eq(3).text();
            var userName = $('#h_user').attr('name');
            var time = new Date().Format("yyyy-MM-dd HH:mm:ss"); 
            var value = $(this).text().trim();
            if (value == '退库') {
                $('#return_user').val(userName);
                $('#return_date').val(time);
                $('#return_name').val(name);
                $('#return_num').val(num);
                $('#myAlertModal').modal('show');
            }
        });
    });
    var start = 0; // 起始查询数据序列
    var end = 5; // 最大查询数据序列
    var page = 1; // 当前页
    var page_all = 1; // 总页数
    var count = 0; // 数据总数
    var table = $('.table')[0];
    // 上一页
    function pre() {
        if (start == 0) {
            new $.zui.Messager('已经是第一页了...', {
            	 icon: 'info-sign',// 定义消息图标
                 type: 'info' 
            }).show();
        } else {
            start -= end;
            page = page - 1;
            initTable();
        }
    }

    // 下一页
    function next() {
        if ((start + end) >= count) {
            new $.zui.Messager('已经是最后一页了...', {
            	 icon: 'info-sign',// 定义消息图标
                 type: 'info' 
            }).show();
        } else {
            start += end;
            page = page + 1;
            initTable();
        }
    }
</script>
