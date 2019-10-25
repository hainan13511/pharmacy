<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>药品报损明细</title>
		<link rel="stylesheet" href="../3rd/zui/css/zui.min.css">
		<link href="../3rd/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
		<style type="text/css">
			.myPanel {
				width: 99.5%;
				margin: 5px auto;
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

			.div_seek .col-lg-2>input {
				padding-left: 85px;
			}

			.div_seek .col-lg-2>label {
				padding-left: 15px;
				padding-top: 6px;	
			}

			.col-lg-3>.input-control-label-left {
				width: 95px;
			}

			.col-lg-2>.input-control-label-left {
				width: 82px;
			}

			.div_seek>.row>div {
				padding: 5px 3px;
			}
			.seek-text >.input-control-label-left{
				width: 82px;
			}
		</style>
	</head>
	<body>
		<div class="panel panel-primary myPanel">
			<div class="panel-heading">
				<h4>药品报损明细</h4>
			</div>
			<div class="panel-body ">
				<div class="container-fluid div_seek">
					<div class="row">
					<div class="col-lg-1 col-md-2 col-sm-6"><button class="btn btn-warning" type="button" data-toggle="tooltip" id="refer" title="刷新"><i
								 class="icon icon-refresh"></i>刷新</button></div>
						<div class="col-lg-3 col-md-4 col-sm-6">
							<input type="text" class="form-control form-date" id="beginning" placeholder="请输入开始时间">
							<label for="mcmStartTime" class="input-control-label-left text-right">开始时间:</label>
							<label for="inputAccountExample1" class="input-control-icon-left"><i class="icon icon-calendar "></i></label>
						</div>
						<div class="col-lg-3 col-md-4 col-sm-6">
							<input type="text" class="form-control form-date" id="endding" placeholder="请输入结束时间">
							<label for="mcmStartTime" class="input-control-label-left text-right">结束时间:</label>
							<label for="inputAccountExample1" class="input-control-icon-left"><i class="icon icon-calendar "></i></label>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-6 seek-text">
							<input type="text" class="form-control ng-pristine ng-valid ng-touched" id="operator" placeholder="请输入操作人">
							<label for="mcmStartTime" class="input-control-label-left text-right">操作人:</label>
							<label for="inputAccountExample1" class="input-control-icon-left"><i class="icon icon-user"></i></label>
						</div>
						<div class="col-lg-1 col-md-2 col-sm-6"><button class="btn btn-info" type="button" data-toggle="tooltip" onclick="search()" title="搜索"><i
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
								<th>报损原因</th>
								<th>报损数</th>
								<th>操作时间</th>
								<th>操作人</th>
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
		<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
		<script src="../3rd/jquery-3.4.1.min.js"></script>
		<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
		<script src="../3rd/zui/lib/datetimepicker/datetimepicker.min.js"></script>
		<script src="../3rd/zui/js/zui.min.js"></script>
	</body>
</html>
<script type="text/javascript">
//选择时间和日期
$(".form-date").datetimepicker(
{
language:  "zh-CN",
weekStart: 1,
todayBtn:  1,
autoclose: 1,
todayHighlight: 1,
startView: 2,
minView: 2,
forceParse: 0,
format: "yyyy-mm-dd"
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
		$('#beginning').val('');//清空文本框
		$('#endding').val('');
		$('#drugName').val('');
		$('#operator').val('');
	})
	$("#refer").click(function(){//刷新页面
		window.location.reload();
	})
	var start = 0; // 起始查询数据序列
	var end = 5; // 最大查询数据序列
	var present = 1; // 当前页
	var allpage = 1; // 总页数
	var count = 0; // 数据总数
	var table = $('.table')[0];
	var startTime="";//开始时间
	var endTime="";//结束时间
	var operate="";//操作人
	init();//初始化
	function init(){
		//发送请求
		$.ajax({
					url : "../drugBreakage.action", //跳转的页面
					data : "start=" + start + "&end=" + end + "&startTime="
							+ startTime+"&endTime="+endTime+"&operate="+operate, //发送请求的数据
					dataType : 'json', //服务器返回json格式数据
					type : 'post', //HTTP请求类型
					timeout : 10000, //超时时间设置为10秒；
					success : function(data) {
						console.log("data:" + data);
						if (data.login == "err") { //如果没有登录（第一种）
							window.location.href = "home/login.jsp";
						}
						//绘制表
						var $table = $("#myTable");
						// 清空除th外所有行
						$("#myTable tr:not(:first)").empty();
						for (var i = 0; i < data.length; i++) {
							var $tr = $("<tr></tr>");
							var $td1 = $("<td>" + (i + 1) + "</td>");
							var $td2 = $("<td>" + data[i].breakageContent + "</td>")
							var $td3 = $("<td>" + data[i].breakageCount + "</td>");
							var $td4 = $("<td>" +new Date(data[i].breakageDate).Format('yyyy-MM-dd hh:mm:ss') + "</td>");
							var $td5 = $("<td>" + data[i].uname + "</td>");
							$tr.append($td1, $td2, $td3, $td4, $td5);
							$table.append($tr);
						}
						page();//页码
					},
					error : function(xhr, type, errorThrown) {
						new $.zui.Messager('提示消息：服务器繁忙', {
							type : 'danger' // 定义颜色主题
						}).show();
					}
				});
	}
	
	//页面
	function page(){
		$.ajax({
			url : "../drugBreakageCount.action",//跳转的页面
			data : "startTime="+ startTime+"&endTime="+endTime+"&operate="+operate, //发送请求的数据
			dataType : 'text',//服务器返回json式数据
			type : 'post',//HTTP请求类型
			success : function(data) {
				//如果没有值
				if(data=='0'){
					data=1;
				}
				//计算总页数
				allpage = data;
				console.log("allpage:" + allpage);
				document.getElementById('lab_page').innerText=present+"/"+allpage
			},
			error : function(xhr, type, errorThrown) {
				new $.zui.Messager('提示消息：服务器繁忙', {
					type : 'danger' // 定义颜色主题
				}).show();
			}
		});
	
	}
	// 上一页
	function pre() {
		if (present == 1) {
			new $.zui.Messager('已经是第一页了...', {
				icon: 'bell' // 定义消息图标
			}).show();
		} else {
			start = start - 5;
			present = present - 1;
			init();
		}
	}

	// 下一页
	function next() {
		if (present == allpage) {
			new $.zui.Messager('已经是最后一页了...', {
				icon: 'bell' // 定义消息图标
			}).show();
		} else {
			start = start + 5;
			present = present + 1;
			init();
		}
	}
	
	//搜索
	function search(){
		var begin= $("#beginning").val();//获取值
		var end=$("#endding").val();
		var operator=$("#operator").val();
		if(end < begin){
		      new $.zui.Messager('提示消息：结束时间不可以大于开始时间', {
					type : 'warning' // 定义颜色主题
				}).show();
		      return;
		  }
		console.log("时间开始："+begin+",结束："+end+",operator="+operator);
		startTime=begin;//开始时间[赋值]
		endTime=end;//结束时间
		operate=operator;//操作人
		//搜索时从第一页和第一个开始搜索
		present = 1;
		start = 0;
		init();//刷新界面
	}
	

	 // 日期格式化，将毫秒转为 XXXX-XX-XX 的格式
   Date.prototype.Format = function(fmt) {
       var o = {
           "M+" : this.getMonth() + 1, // 月份
           "d+" : this.getDate(), // 日
           "h+" : this.getHours(), // 小时
           "m+" : this.getMinutes(), // 分
           "s+" : this.getSeconds(), // 秒
           "q+" : Math.floor((this.getMonth() + 3 ) / 3), // 季度
           "S" : this.getMilliseconds()// 毫秒
       };
       if (/(y+)/.test(fmt))
           fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
                   .substr(4 - RegExp.$1.length));
       for ( var k in o)
           if (new RegExp("(" + k + ")").test(fmt))
               fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
                       : (("00" + o[k]).substr(("" + o[k]).length)));
       return fmt;

   };
</script>
