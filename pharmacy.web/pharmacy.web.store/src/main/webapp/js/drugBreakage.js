/**
 * 
 */
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
    // 刷新按钮
    $('#btn_refresh').click(function() {
    	location = location;
    })
    //清空
    $('#btn_del').click(function() {
        $('#drugName').val('');
    })
    //日期格式化
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
    //表格遍历,获取点击的按钮
    $(function() {
        $("#myTable").on("click", ":button", function(event) {
        	var drugId = $(this).closest("tr").find("td").eq(0).attr('drugId');
            var drugName = $(this).closest("tr").find("td").eq(1).text();
            var inventory = $(this).closest("tr").find("td").eq(7).text();
            var batch = $(this).closest("tr").find("td").eq(9).text();
            var value = $(this).text().trim();
            if (value == '报损') {
            	$('#return_name').val(drugName);
            	$('#return_batch').val(batch);
                $('#myAlertModal').modal('show');
                //提交申请
                $('#btn_confirm').off("click").on("click",function(){
                	var num = $('#return_num').val().trim();
                	var cause = $('#return_cause').val().trim();
                	if(num==''){
                		new $.zui.Messager('请输入报损数量!', {
            				icon: 'times',// 定义消息图标
            				type: 'danger' 
            			}).show();
                		return;
                	}
                	if(cause==''){
                		new $.zui.Messager('请输入报损原因!', {
            				icon: 'times',// 定义消息图标
            				type: 'danger' 
            			}).show();
                		return;
                	}
                	if(num>inventory){
                		new $.zui.Messager('报损数量大于药房库存数量,请重新填写!', {
                			icon: 'times',// 定义消息图标
            				type: 'danger' 
            			}).show();
                		return;
                		}
                	$.ajax({
                		url : "drugBreakage.ajax",
                		type : "post",
                		data : {
                			num : num,
                			cause : cause,
                			batch : batch,
                			drugId : drugId
                		},
                		dataType : "json",
                		success : function(data) {
                			console.log('状态:' + data);
                			if(data==null){
                    			new $.zui.Messager('用户未登录', {
                    				icon: 'info-sign',// 定义消息图标
                    				type: 'success' 
                    			}).show();
                    			// 跳转操作
                    			setTimeout(function() {
                    				parent.location.href="home/login.jsp"; 
                    			}, 2000);
                    		}else if(data=="1"){
                    			new $.zui.Messager('提交成功', {
                    				icon: 'info-sign',// 定义消息图标
                    				type: 'success' 
                    			}).show();
                    			$('#myAlertModal').modal('hide', 'fit');
                    			refresh();
                    			// 跳转操作
                    			/*setTimeout(function() {
                    				location = location;
                    			}, 2000);*/
                    			return;
                    		}else if(data=="0"){
                    			new $.zui.Messager('报损失败,请检查输入是否正确', {
                    				icon: 'times',// 定义消息图标
                    				type: 'danger' 
                    			}).show();
                    			$('#myAlertModal').modal('hide', 'fit');
                    			return;
                    		}
                   			new $.zui.Messager('报损未成功', {
                				icon: 'times',// 定义消息图标
                				type: 'danger' 
                			}).show();
                			$('#myAlertModal').modal('hide', 'fit');
                		},
                		error : function(e) {
                			new $.zui.Messager('提交失败', {
                    			icon: 'times',// 定义消息图标
                    			type: 'danger' 
                    		}).show();
                		}

                	})
                })
            }
        });
    });
    var start = 0; // 起始查询数据序列
    var end = 5; // 最大查询数据序列
    var page = 1; // 当前页
    var page_all = 1; // 总页数
    var count = 0; // 数据总数
    var table = $('.table')[0];
    var drugName = null;
    // 查询
    $('#btn_query').click(function() {
        start = 0;
        end = 5;
        page = 1;
        page_all = 1;
        count = 0;
        document.getElementById('lab_page').innerText = page + "/" + page_all;
    	drugName = $('#drugName').val();
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
        document.getElementById('lab_page').innerText = page + "/" + page_all;
        initTable();
    }
    
    // 初始化表格
    function initTable() {
        // 清空除th外所有行
        $(".table tr:not(:first)").empty();
        $
                .ajax({
                    url : "listDrugAndInventory.ajax",
                    type : "post",
                    data : {
                        start : start,
                        end : end,
                        drugName : drugName,
                    },
                    dataType : "json",
                    success : function(data) {
                        console.log("ad:" + JSON.stringify(data));
                        if (data.drugAndInventory == null) {
                            new $.zui.Messager('没有查询到', {
                                icon: 'info-sign',// 定义消息图标
                                type: 'info' 
                            }).show();
                            return;
                        }
                        var arr = data.drugAndInventory.list;
                        // 加载表格
                        for (var i = 0; i < arr.length; i++) {
                        	console.log(arr[i].drugName)
                            var tr = document.createElement('tr');
                            var td1 = document.createElement('td');
                            td1.innerText = i+1;
                            td1.setAttribute("drugId",arr[i].drugId);
                            var td2 = document.createElement('td');
                            td2.innerText = arr[i].drugName;
                            var td3 = document.createElement('td');
                            td3.innerText = arr[i].specification;
                            var td4 = document.createElement('td');
                            td4.innerText = arr[i].dosage;
                            var td5 = document.createElement('td');
                            td5.innerText = arr[i].spell;
                            var td6 = document.createElement('td');
                            td6.innerText = arr[i].dcost;
                            var td7 = document.createElement('td');
                            td7.innerText = arr[i].dsale;
                            var td8 = document.createElement('td');
                            td8.innerText = arr[i].hSum;
                            var td9 = document.createElement('td');
                            td9.innerText = arr[i].pSum;
                            var td10 = document.createElement('td');
                            td10.innerText = arr[i].dwdId;
                            var td11 = document.createElement('td');
                            if(td8.innerText =='0'){
                            	td11.innerHTML = '<button class="btn btn-link" type="button" disabled><i class="icon icon-edit"></i> 报损</button>';
                            }else{
                            	td11.innerHTML = '<button class="btn btn-link" type="button"><i class="icon icon-edit"></i> 报损</button>';
                            }
                            tr.appendChild(td1);
                            tr.appendChild(td2);
                            tr.appendChild(td3);
                            tr.appendChild(td4);
                            tr.appendChild(td5);
                            tr.appendChild(td6);
                            tr.appendChild(td7);
                            tr.appendChild(td8);
                            tr.appendChild(td9);
                            tr.appendChild(td10);
                            tr.appendChild(td11);

                            table.appendChild(tr);
                        }
                        // 总页数
                        count = data.drugAndInventory.count;
                        page_all = Math.ceil(data.drugAndInventory.count / end);
                        console.log("页码:"+page_all+"count:"+count)
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