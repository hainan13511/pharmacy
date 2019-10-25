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
    // 刷新按钮
    $('#btn_refresh').click(function() {
    	location = location;
    })
    $('[data-toggle="tooltip"]').tooltip({
        placement: 'bottom'
    });
  //清空
    $('#btn_del').click(function() {
        $('#drugName').val('');
    })
    
    //表格遍历
    $(function() {
        $("#myTable").on("click", ":button", function(event) {
            var name = $(this).closest("tr").find("td").eq(1).text();
            var price = $(this).closest("tr").find("td").eq(6).text();
            var drugId = $(this).closest("tr").find("td").eq(0).attr('drugId');
            var beforePrice = $(this).closest("tr").find("td").eq(6).text();
            var value = $(this).text().trim();
            if (value == '调价') {
                $('#return_name').val(name);
                $('#return_price').val(price);
                $('#myAlertModal').modal('show');
                //提交申请
                $('#btn_confirm').off("click").on("click",function(){
                	var userId = $('#h_user').val();
                	var price = $('#return_price').val().trim();
                	if(price==''){
                		new $.zui.Messager('请输入调整价格!', {
            				icon: 'times',// 定义消息图标
            				type: 'danger' 
            			}).show();
                		return;
                	}
                	if(price=='0'){
                		new $.zui.Messager('调整价格不能为0!', {
            				icon: 'times',// 定义消息图标
            				type: 'danger' 
            			}).show();
                		return;
                	}
                	$.ajax({
                		url : "updateDrugPrice.ajax",
                		type : "post",
                		data : {
                			userId : userId,
                			price : price,
                			beforePrice : beforePrice,
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
                    			return;
                    		}else if(data=="1"){
                    			new $.zui.Messager('提交成功', {
                    				icon: 'info-sign',// 定义消息图标
                    				type: 'success' 
                    			}).show();
                    			$('#myAlertModal').modal('hide', 'fit');
                    			refresh();
                    			// 跳转操作
//                    			setTimeout(function() {
//                    				location = location;
//                    			}, 2000);
                    			return;
                    		}else if(data=="0"){
                    			new $.zui.Messager('修改失败', {
                    				icon: 'times',// 定义消息图标
                    				type: 'danger' 
                    			}).show();
                    			$('#myAlertModal').modal('hide', 'fit');
                    			return;
                    		}
                   			new $.zui.Messager('修改未成功', {
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
        drugName = null;
        $('#drugName').val('');
        document.getElementById('lab_page').innerText = page + "/" + page_all;
        initTable();
    }
    
    // 初始化表格
    function initTable() {
        // 清空除th外所有行
        $(".table tr:not(:first)").empty('');
        $
                .ajax({
                    url : "listDrugInf.ajax",
                    type : "post",
                    data : {
                        start : start,
                        end : end,
                        drugName : drugName
                    },
                    dataType : "json",
                    success : function(data) {
                        console.log("ad:" + JSON.stringify(data));
                        if (data.listDrugInf == null) {
                            new $.zui.Messager('没有查询到', {
                                icon: 'info-sign',// 定义消息图标
                                type: 'info' 
                            }).show();
                            return;
                        }
                        var arr = data.listDrugInf.list;
                        // 加载表格
                        for (var i = 0; i < arr.length; i++) {
                            var tr = document.createElement('tr');
                            var td1 = document.createElement('td');
                            td1.innerText = i+1;
                            td1.setAttribute("drugId",arr[i].drugId);
                            var td2 = document.createElement('td');
                            var drugName = arr[i].drugName;
                            td2.innerHTML = drugName;
                            var td3 = document.createElement('td');
                            td3.innerText = arr[i].drugTypeName;
                            var td4 = document.createElement('td');
                            td4.innerText = arr[i].specification;
                            var td5 = document.createElement('td');
                            td5.innerText = arr[i].dosage;
                            var td6 = document.createElement('td');
                            td6.innerText = arr[i].dcost;
                            var td7 = document.createElement('td');
                            td7.innerText = arr[i].dsale;
                            var td8 = document.createElement('td');
                            td8.innerHTML = '<button class="btn btn-link" type="button"><i class="icon icon-edit"></i> 调价</button>';
                            tr.appendChild(td1);
                            tr.appendChild(td2);
                            tr.appendChild(td3);
                            tr.appendChild(td4);
                            tr.appendChild(td5);
                            tr.appendChild(td6);
                            tr.appendChild(td7);
                            tr.appendChild(td8);

                            table.appendChild(tr);
                        }
                        // 总页数
                        count = data.listDrugInf.count;
                        page_all = Math.ceil(data.listDrugInf.count / end);
                        document.getElementById('lab_page').innerText = page
                        + "/" + page_all;
                    },
                    error : function(e) {
                        new $.zui.Messager('服务器异常!', {
                            icon: 'info-sign',// 定义消息图标
                            type: 'info' 
                        }).show();
                    }

                });
    }
    initTable();
    
    // 上一页
    function pre() {
        if (start == 0) {
            new $.zui.Messager('已经是第一页了...', {
                icon: 'info-sign', // 定义消息图标
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
                icon: 'info-sign', // 定义消息图标
                type: 'info'
            }).show();
        } else {
            start += end;
            page = page + 1;
            initTable();
        }
    }