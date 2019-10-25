/**
 * 
 */
  // 选择时间和日期
    $(".form-datetime").datetimepicker(
    {
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1,
        format: "yyyy-mm-dd hh:ii"
    });
 // 二级分类
    $('#addParentId').click(
    		function() {
    			var fid = $('#addParentId').val();
    			// 清空子元素
    			$('#addTypeId').empty();
    			$('#addTypeId').append('<option value="-1">请选择</option>')
    			if (fid == '-1') {
    				return;
    			}
    			$.ajax({
    				url : "listDrugType.ajax",
    				type : "post",
    				data : {
    					fid : fid
    				},
    				dataType : "json",
    				success : function(data) {
    					// 添加数据库选项
    					for (var i = 0; i < data.length; i++) {
    						$(
    								("<option value=" + data[i].dtId + ">"
    										+ data[i].dtName + "</option>"))
    								.appendTo($("#addTypeId"));
    					}
    				},
    				error : function(e) {
    					new $.zui.Messager('初始化药品类型下拉框失败!', {
    						icon : 'times',// 定义消息图标
    						type : 'danger'
    					}).show();
    				}

    			});
    		})
    // 药品选择
    $('#addTypeId').click(
    		function() {
    			var dtId = $('#addTypeId').val();
    			// 清空子元素
    			$('#addDrugId').empty();
    			$('#addDrugId').append('<option value="-1">请选择</option>')
    			if (dtId == '-1') {
    				return;
    			}
    			$.ajax({
    				url : "sendListDrugInf.ajax",
    				type : "post",
    				data : {
    					dtId : dtId
    				},
    				dataType : "json",
    				success : function(data) {
    					drugArr = data;
    					//console.log("ad:" + JSON.stringify(drugArr));
    					// 添加数据库选项
    					for (var i = 0; i < data.length; i++) {
    						$(
    								("<option value=" + data[i].drugId + ">"
    										+ data[i].drugName + "</option>"))
    								.appendTo($("#addDrugId"));
    					}
    				},
    				error : function(e) {
    					new $.zui.Messager('初始化药品下拉框失败!', {
    						icon : 'times',// 定义消息图标
    						type : 'danger'
    					}).show();
    				}

    			});
    		});
    // 新增按钮
    $('#btn_add').click(function() {
        $('#myModal').modal('show');
    })
    $('[data-toggle="tooltip"]').tooltip({
        placement: 'bottom'
    });
    //清空
    $('#btn_del').click(function() {
        $('#drugName').val('');
        $('#dateStart').val('');
        $('#dateEnd').val('');
        $('#checkState').val('-1');
    })
    
    //提交申请
    $('#btn_confirm').click(function(){
    	var drugName = $('#addDrugId').find("option:selected").text().trim();
    	var drugNum = $('#addNum').val().trim();
    	if(drugName==''){
    		new $.zui.Messager('请选择药品!', {
				icon: 'times',// 定义消息图标
				type: 'danger' 
			}).show();
    		return;
    	}
    	if(drugNum==''){
    		new $.zui.Messager('请输入药品数量!', {
				icon: 'times',// 定义消息图标
				type: 'danger' 
			}).show();
    		return;
    	}
    	$.ajax({
    		url : "DrugApplyConfirm.ajax",
    		type : "post",
    		data : {
    			drugName : drugName,
    			drugNum : drugNum
    		},
    		dataType : "json",
    		success : function(data) {
    			//console.log('状态:' + data);
    			if(data==null){
        			new $.zui.Messager('用户未登录', {
        				icon: 'info-sign',// 定义消息图标
        				type: 'success' 
        			}).show();
        			// 跳转操作
        			setTimeout(function() {
        				parent.location.href="home/login.jsp"; 
        			}, 1000);
        		}else if(data=="1"){
        			$('#myModal').modal('hide', 'fit');
        			new $.zui.Messager('提交成功', {
        				icon: 'info-sign',// 定义消息图标
        				type: 'success' 
        			}).show();
        			// 跳转操作
        			setTimeout(function() {
        				location = location;
        			}, 1000);
        			return;
        		}else if(data=="-1"){
        			new $.zui.Messager('没有这种药品,请重新选择药品', {
        				icon: 'times',// 定义消息图标
        				type: 'danger' 
        			}).show();
        			return;
        		}else if(data=="0"){
        			new $.zui.Messager('插入失败', {
        				icon: 'times',// 定义消息图标
        				type: 'danger' 
        			}).show();
        			$('#myModal').modal('hide', 'fit');
        			return;
        		}
    			console.log('修改未成功!' + data.msg)
    		},
    		error : function(e) {
    			new $.zui.Messager('提交失败', {
        			icon: 'times',// 定义消息图标
        			type: 'danger' 
        		}).show();
    		}

    	})
    })
    // 刷新按钮
    $('#btn_refresh').click(function() {
    	location = location;
    })
    
    var start = 0;// 起始查询数据序列
    var end = 5;// 最大查询数据序列
    var page = 1;// 当前页
    var page_all = 1;// 总页数
    var count = 0;// 数据总数
    var table = $('#myTable')[0];
    var drugName = null;
    var checkState = null;
    var dateStart = null;
    var dateEnd = null;
    // 查询
    $('#btn_query').click(function() {
        start = 0;
        end = 5;
        page = 1;
        page_all = 1;
        count = 0;
        document.getElementById('lab_page').innerText = page + "/" + page_all;
    	drugName = $('#drugName').val();
    	checkState = $('#checkState').val();
    	dateStart = $('#dateStart').val();
    	dateEnd = $('#dateEnd').val();
    	if(dateStart>dateEnd){
    		new $.zui.Messager('开始时间不能大于截止时间!', {
                icon: 'info-sign',// 定义消息图标
                type: 'info' 
            }).show();
    		return;
    	}
        initTable();
    })
    // 刷新
    function refresh() {
        start = 0;
        end = 5;
        page = 1;
        page_all = 1;
        count = 0;
        $('#drugName').val('');
        $('#dateStart').val('');
        $('#dateEnd').val('');
        $('#checkState').val('-1');
        document.getElementById('lab_page').innerText = page + "/" + page_all;
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
    // 初始化表格
    function initTable() {
        // 清空除th外所有行
        $(".table tr:not(:first)").empty();
        $
                .ajax({
                    url : "listDrugApply.ajax",
                    type : "post",
                    data : {
                        start : start,
                        end : end,
                        drugName : drugName,
                        checkState : checkState,
                        dateStart : dateStart,
                        dateEnd : dateEnd
                    },
                    dataType : "json",
                    success : function(data) {
                       // console.log("ad:" + JSON.stringify(data));
                        if (data.DrugApplyDto == null) {
                            new $.zui.Messager('未查询到数据', {
                                icon: 'info-sign',// 定义消息图标
                                type: 'info' 
                            }).show();
                            refresh();
                            return;
                        }
                        var arr = data.DrugApplyDto.list;
                        // 加载表格
                        for (var i = 0; i < arr.length; i++) {

                            var tr = document.createElement('tr');
                            var td1 = document.createElement('td');
                            td1.innerText = i+1;
                            td1.setAttribute("applyId",arr[i].applyId);
                            var td2 = document.createElement('td');
                            td2.innerText = arr[i].drugName;
                            var td3 = document.createElement('td');
                            td1.setAttribute("userId",arr[i].userId);
                            td3.innerText = arr[i].userName;
                            var td4 = document.createElement('td');
                            var time = new Date(arr[i].applyTime).Format('yyyy-MM-dd hh:mm:ss')
                            td4.innerText = time;
                            var td5 = document.createElement('td');
                            td5.innerText = arr[i].applyNum;
                            var td6 = document.createElement('td');
                            var state = arr[i].checkState=="0"?"待审核":"审核通过";
                            td6.innerText = state;
                            var td7 = document.createElement('td');
                            if(state=="待审核"){
                            td7.innerHTML = '<button class="btn btn-link" type="button"><i class="icon icon-edit"></i> 修改</button>';
                            }else{
                            td7.innerHTML = '<button class="btn btn-link" type="button"disabled><i class="icon icon-edit" ></i> 修改</button>';
                            }
                            tr.appendChild(td1);
                            tr.appendChild(td2);
                            tr.appendChild(td3);
                            tr.appendChild(td4);
                            tr.appendChild(td5);
                            tr.appendChild(td6);
                            tr.appendChild(td7);

                            table.appendChild(tr);
                        }
                        // 总页数
                        count = data.DrugApplyDto.count;
                        page_all = Math.ceil(data.DrugApplyDto.count / end);
                        document.getElementById('lab_page').innerText = page
                        + "/" + page_all;
                    },
                    error : function(e) {
                        new $.zui.Messager('表格初始化失败!', {
                            icon: 'info-sign',// 定义消息图标
                            type: 'info' 
                        }).show();
                        refresh();
                    }

                });
    }
    initTable();
// form表单接收返回值
    $(function(){
    	/** 验证修改请领单是否成功 */
    	$("#form2").ajaxForm(function(data){  
    		if(data=="1"){
    			 new $.zui.Messager('提交成功', {
    	                icon: 'info-sign',// 定义消息图标
    	                type: 'success' 
    	            }).show();
    			 $('#myAlertModal').modal('hide')
    			// 跳转操作
					setTimeout(function() {
						location = location;
					}, 2000);
    			return;
    		}else if(data=="0"){
    			new $.zui.Messager('已审核,修改失败', {
    				icon: 'times',// 定义消息图标
    				type: 'danger' 
    			}).show();
    			$('#myAlertModal').modal('hide');
    			location = location;
    			return;
    		}
    		new $.zui.Messager('提交失败', {
                icon: 'times',// 定义消息图标
                type: 'danger' 
            }).show();
    	});     
    });
    // 表格按钮事件
    $(function() {
        $("#myTable").on("click", ":button", function(event) {
            var id = $(this).closest("tr").find("td").eq(0).attr('applyId');
            var name= $(this).closest("tr").find("td").eq(1).text();
            var userName = $(this).closest("tr").find("td").eq(2).text();
            var time = $(this).closest("tr").find("td").eq(3).text();
            var num = $(this).closest("tr").find("td").eq(4).text();
            var value = $(this).text().trim();
            if (value == '修改') {
            	$('#applyId').val(id);
                $('#altUser').val(userName);
                $('#altTime').val(time);
                $('#altDrug').val(name);
                $('#altNum').val(num);
                $('#myAlertModal').modal('show');
            }
        });
    });
    
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