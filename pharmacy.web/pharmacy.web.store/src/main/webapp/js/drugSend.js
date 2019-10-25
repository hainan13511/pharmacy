/**
 * 
 */
// 选择时间和日期
$(".form-datetime").datetimepicker({
	weekStart : 1,
	todayBtn : 1,
	autoclose : 1,
	todayHighlight : 1,
	startView : 2,
	forceParse : 0,
	showMeridian : 1,
	format : "yyyy-mm-dd hh:ii"
});
$('#btn_add').click(function() {
	$('#myModal').modal('show')
})
$('[data-toggle="tooltip"]').tooltip({
	placement : 'bottom'
});
// 新增按钮
$('#btn_add').click(function() {
	$('#addParentId').val('-1');
	$('#addTypeId').val('-1');
	$('#addDrugId').val('-1');
	$('#addNum').val('');
	$('#myModal').modal('show');
})
// 取消
$('#btn_close').click(function() {
	layer.confirm('确定取消所有已添加药品？', {
		btn : [ '是', '否' ]
	// 可以无限个按钮
	}, function(index, layero) {
		$(".table tr:not(:first)").empty();
		layer.close(index);
	}, function(index) {
		console.log('2')
	});
})
// 二级分类
//$('#addParentId').click(
//		function() {
//			var fid = $('#addParentId').val();
//			// 清空子元素
//			$('#addTypeId').empty();
//			$('#addTypeId').append('<option value="-1">请选择</option>')
//			if (fid == '-1') {
//				return;
//			}
//			$.ajax({
//				url : "listDrugType.ajax",
//				type : "post",
//				data : {
//					fid : fid
//				},
//				dataType : "json",
//				success : function(data) {
//					// 添加数据库选项
//					for (var i = 0; i < data.length; i++) {
//						$(
//								("<option value=" + data[i].dtId + ">"
//										+ data[i].dtName + "</option>"))
//								.appendTo($("#addTypeId"));
//					}
//				},
//				error : function(e) {
//					new $.zui.Messager('初始化药品类型下拉框失败!', {
//						icon : 'times',// 定义消息图标
//						type : 'danger'
//					}).show();
//				}
//
//			});
//		})
function emptySearch() {
	$('#searchDrugName').val('');
	// 清空除th外所有行
	$("#searchTable tr:not(:first)").empty();
	$("#searchTable").fadeOut();
}
function emptyNum() {
	$('#addNum').val('');
}
// 药品搜索
$('#btnSearch').click(function() {
					// 清空除th外所有行
					$("#searchTable tr:not(:first)").empty();
					var drugName = $('#searchDrugName').val();
					if (drugName == '') {
						return;
					}
					// 清空子元素
					$.ajax({
								url : "selectDrugInfo.ajax",
								type : "post",
								data : {
									drugName : drugName
								},
								dataType : "json",
								success : function(data) {
//									console.log(data)
									// console.log("ad:" +
									// JSON.stringify(drugArr));
									if (data == null) {
										new $.zui.Messager('未能搜索到', {
											icon : 'info-sign',// 定义消息图标
											type : 'info'
										}).show();
										return;
									}
									// 添加数据库选项
									for (var i = 0; i < data.length; i++) {
										var $tr = $('<tr> </tr>');
										var $td1 = $('<td>' + (i + 1) + '</td>');
										$td1.attr('drugId', data[i].drugId);
										var $td2 = $('<td>' + data[i].drugName
												+ '</td>');
										var $td3 = $('<td><button class="btn btn-link" type="button"><i class="icon icon-plus"></i> 添加</button></td>');
										$tr.append($td1, $td2, $td3);
										$('#searchTable').append($tr);
									}
									$("#searchTable").fadeIn("slow");
								},
								error : function(e) {
									new $.zui.Messager('服务器异常', {
										icon : 'times',// 定义消息图标
										type : 'danger'
									}).show();
								}

							});
				});

// 表格按钮事件
$(function() {
	$("#myTable").on("click", ":button", function(event) {
		var value = $(this).text().trim();
		if (value == '删除') {
			$(this).closest("tr").remove();
			countPrices();
		}
	});
});

var count = 0;
// 添加药品
$(function() {
	$("#searchTable").on("click", ":button", function(event) {
		var value = $(this).text().trim();
		var drugId = $(this).closest("tr").find("td").eq(0).attr('drugId');;
		if (value == '添加') {
			console.log('添加了')
			var drugNum;
			$('#mySmModal').modal('show');
			//数量确定
			$('#btn_num_confirm').off("click").on("click",function(){
				drugNum = $('#addNum').val();
				if (drugNum == '') {
					new $.zui.Messager('数量不能为空!', {
						icon : 'times',// 定义消息图标
						type : 'danger'
					}).show();
					return;
				}
				var result = true;
				$('#myTable tr').each(function() {
					var row = $(this).index();//行号
					if($(this).index()>0){
//						console.log('遍历'+row);
					var txt = $(this).find('td:eq(0)').attr('drugid'); // 选中每个tr里面的第1个td
					var cost = $(this).find('td:eq(4)').text();
					var sale = $(this).find('td:eq(5)').text();
					var num = $(this).find('td:eq(6)').text();
					// console.log('958' + txt);
					if (drugId == txt) {
						num = Number(num) + Number(drugNum);// 获取原本数量,转换为数字相加
						$(this).find('td:eq(6)').text(num);
						$(this).find('td:eq(7)').text(num * cost);// 修改该行成本总价
						$(this).find('td:eq(8)').text(num * sale);// 修改该行零售总价
						countPrices();
						$('#addNum').val('');
						$('#mySmModal').modal('hide', 'fit');
//						$('#myModal').modal('hide', 'fit');
						new $.zui.Messager('药名重复,已合并到表格!', {
							icon : 'info-sign',// 定义消息图标
							type : 'info'
						}).show();
						result = false;
						return false;
					}
					}
				});
				if (!result) return;
				//查询选中药品信息
				$.ajax({
					url : "selectDrugInfo.ajax",
					type : "post",
					data : {
						drugId : drugId
					},
					dataType : "json",
					success : function(data) {
//						console.log("bb:" + JSON.stringify(data));
						if (data == null) {
							new $.zui.Messager('未能搜索到', {
								icon : 'info-sign',// 定义消息图标
								type : 'info'
							}).show();
							return;
						}
						// 新增购买药品信息
							for (var i = 0; i < data.length; i++) {
								var $tr = $('<tr> </tr>');
								var $td2 = $('<td>' + data[i].drugName + '</td>');
								$td2.attr('drugId', data[i].drugId);
								var $td3 = $('<td>' + data[i].specification
										+ '</td>');
								var $td4 = $('<td>' + data[i].dosage + '</td>');
								var $td5 = $('<td>' + data[i].spell + '</td>');
								var $td6 = $('<td>' + data[i].dcost + '</td>');
								var $td7 = $('<td>' + data[i].dsale + '</td>');
								var $td8 = $('<td>' + drugNum + '</td>');
								var $td9 = $('<td>' + data[i].dcost * drugNum
										+ '</td>');
								var $td10 = $('<td>' + data[i].dsale * drugNum
										+ '</td>');
								var $td11 = $('<td><button class="btn btn-link" type="button"><i class="icon icon-times"></i> 删除</button></td>');
								$tr.append($td2, $td3, $td4, $td5, $td6,
										$td7, $td8, $td9, $td10, $td11);
								$('#myTable').append($tr);
							}
							countPrices();
					},
					error : function(e) {
						new $.zui.Messager('服务器异常', {
							icon : 'times',// 定义消息图标
							type : 'danger'
						}).show();
					}
					});
				$('#addNum').val('');
				new $.zui.Messager('添加成功', {
					icon : 'info-sign',// 定义消息图标
					type : 'success'
				}).show();
				$('#mySmModal').modal('hide', 'fit');
			})
		}
	});
});

$('#btn_confirm').click(function() {
			emptySearch();
			$('#myModal').modal('hide', 'fit');
		})
// 计算总价,刷新添加窗口
function countPrices() {
	var prices = 0;
	$('#myTable tr').each(function() {
		var price = $(this).find('td:eq(8)').text();
		prices = Number(prices) + Number(price);
	})
	$('#countPrices').val(prices);
}

// 发药
$('#btn_send').click(function() {
	var idArr = [];
	$('#myTable tr').each(function() {
		if ($(this).index() > 0) {
			var id = $(this).find('td:eq(0)').attr('drugid'); // 药品编号
			var num = $(this).find('td:eq(6)').text(); // 药品数量
			var cost = $(this).find('td:eq(7)').text(); // 药品成本
			var sale = $(this).find('td:eq(8)').text(); // 药品零售价
			var drug = {
				drugId : id,
				salesCount : num,
				salesCostPrice : cost,
				salesSellPrice : sale
			}
			if (id != '' && num != '' && cost != '' && sale != '')
				idArr.push(drug);
		}
	})

	console.log('idArr' + idArr.length);
	if (idArr.length == 0) {
		return;
	}
	idArr = JSON.stringify(idArr);
	console.log('idArr');
	$.ajax({
		url : "drugSend.ajax",
		type : "post",
		data : {
			idArr : idArr
		},
		dataType : "json",
		success : function(data) {
//			console.log('状态:' + data);
			if (data == null) {
				new $.zui.Messager('未登录!', {
					icon : 'times',// 定义消息图标
					type : 'danger'
				}).show();
				return;
			} else if (data > 0 ) {
				new $.zui.Messager('提交成功', {
					icon : 'info-sign',// 定义消息图标
					type : 'success'
				}).show();
				// 跳转操作
				setTimeout(function() {
					location = location;
				}, 2000);
				return;
			} else if (data == "0") {
				new $.zui.Messager('发药失败', {
					icon : 'times',// 定义消息图标
					type : 'danger'
				}).show();
				return;
			} else if (data == "-2") {
				new $.zui.Messager('药品数量大于药房库存,无法购买', {
					icon : 'times',// 定义消息图标
					type : 'danger'
				}).show();
				return;
			} else if (data == "-3") {
				new $.zui.Messager('存在药品配伍禁忌!', {
					icon : 'times',// 定义消息图标
					type : 'danger'
				}).show();
				return;
			}
			new $.zui.Messager('提交未成功', {
				icon : 'info-sign',// 定义消息图标
				type : 'success'
			}).show();
		},
		error : function(e) {
			new $.zui.Messager('服务器异常', {
				icon : 'times',// 定义消息图标
				type : 'danger'
			}).show();
		}
	});

})
