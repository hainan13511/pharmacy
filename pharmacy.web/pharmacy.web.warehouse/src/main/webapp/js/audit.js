//刷新界面
document.getElementById('btn_refresh').onclick = function() {	
	location.reload();	
}	

//日期格式化，将毫秒转为 XXXX-XX-XX 的格式
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


//清空
document.getElementById('btn-null').onclick = function() {
	document.getElementById('text-uname').value="";
	document.getElementById('text-drug').value="";
	document.getElementById('sel_state').value = "-1"	;
}





//表
var table = document.getElementById('table-edit');

var start = 0;//从哪里开始
var end = 5;//显示几条
var nowpage =1;//页码
var allpage =1;	//页码
var uname = "";//申请人名字
var drugname="";//药品名
var state = ""; //状态
function init(){
	$.ajax({
		url:"../initAudit.action",
		type:"post",
		data:"uname="+uname+"&drugname="+drugname+"&start="+start+"&end="+end+"&state="+state,
		dataType:"json",
		success:function(data){	
			console.log(data);
			var list = data.list;
			allpage = data.pageNum;
			//设置总页码
			document.getElementById('allpage').innerText=Math.floor(allpage);
			
			//添加市删除表
			var trs = document.getElementsByClassName('drug-tr');
 			//清除数据
			for(var i = trs.length-1;i>=0;i--){
				trs[0].remove();
			} 			
			for (var i = 0; i < list.length; i++) {
				
				var tr = document.createElement('tr');
				tr.classList.add("drug-tr");
				
				var td1=document.createElement('td');
				td1.innerText=i+1;
				
				var td2=document.createElement('td');
				td2.innerText=list[i].drugName;
				
				var td3=document.createElement('td');
				td3.innerText=list[i].applyNum;
				
				var td4=document.createElement('td');
				td4.innerText=list[i].uname;
		
				
				var td5=document.createElement('td');
				td5.innerText=new Date(list[i].applyTime).Format('yyyy-MM-dd hh:mm:ss');
				
				var td6=document.createElement('td');
				if(list[i].checkState==0){
					td6.innerText="待审核";	
				}else if(list[i].checkState==1){
					td6.innerText="已审核";	
				}else if(list[i].checkState==2){
					td6.innerText="审核不通过";	
				}
				
				var td7=document.createElement('td');
				if(list[i].checkState==0){
					td7.innerHTML='<button class="btn btn-link btn-sm" drugid='+list[i].drugid+' applyid = '+list[i].applyId+' count='+list[i].applyNum+' type="button">审核通过</button> <button class="btn btn-link btn-sm"  applyid = '+list[i].applyId+' type="button">	审核不通过</button>'
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
		},error:function(e){
			console.log(data);
		}
	});
}

init();





//下一页
document.getElementById('down-page').onclick=function(){
	
	if(nowpage>=allpage){
	    new $.zui.Messager('已经是最后一页了！', {
		        icon: 'bell' // 定义消息图标
		    }).show();
	}else{
		start=start+end;
		nowpage = nowpage+1;
		document.getElementById('nowpage').innerText=nowpage;
		init();
	}
}
//上一页
document.getElementById('up-page').onclick=function(){
	
	if(nowpage<=1){
	    new $.zui.Messager('已经是第一页了！', {
		        icon: 'bell' // 定义消息图标
	    }).show();			
	}else{
		nowpage = nowpage-1;
		start=start-end;
		document.getElementById('nowpage').innerText=nowpage;
		init();	
	}
}


//搜索
document.getElementById('btn-search').onclick=function(){
	
	uname = document.getElementById('text-uname').value.trim();;
	drugname = document.getElementById('text-drug').value.trim();;
	state_id = document.getElementById('sel_state').value;
	
	if(state_id == -1){
		state = "";
	}else{
		state = state_id;
	}
		start = 0;
		end = 5;
		nowpage = 1; 
		init();
			
}


//操作
table.onclick = function(e) {
	var el = e.target;
	var click = el.innerText;
	console.log(el.innerText);
	var drugid=el.getAttribute('drugid');
	var applyid=el.getAttribute('applyid');
	var count=el.getAttribute('count');
	if(click == "审核通过"){
		layer.confirm('是否确认通过？', {
			btn: ['确认通过', '取消'] //按钮
		}, function(index) {
			//按钮一的回调
			layer.close(index);
			$.ajax({
				url:"../approve.action",
				type:"post",
				data:"drugid="+drugid+"&applyid="+applyid+"&count="+count,
				dataType:"json",
				success:function(data){	
					console.log(data);
					if(data.result == "yes"){
						new $.zui.Messager('操作成功', {
						   icon: 'icon-check-circle',
						    type: 'success' // 定义颜色主题
						}).show();
						init();
					}else if(data.result == "deficiency"){
						new $.zui.Messager('库存不足，请前往采购', {
						   icon: 'icon-exclamation-sign',
						    type: 'warning' // 定义颜色主题
						}).show();
					}
				},error:function(e){
					console.log(e);
					new $.zui.Messager('服务器繁忙。', {
					   icon: 'icon-exclamation-sign',
					    type: 'warning' // 定义颜色主题
					}).show();
				}
			});
		}, function() {
			//按钮二的回调
		});
		
	}else if(click == "审核不通过"){
		layer.confirm('是否确认不通过？', {
			btn: ['确认不通过', '取消'] //按钮
		}, function(index) {
			//按钮一的回调
			layer.close(index);
			$.ajax({
				url:"../notApproved.action",
				type:"post",
				data:"applyid="+applyid,
				dataType:"json",
				success:function(data){	
					console.log(data);
					if(data.result == "yes"){
						new $.zui.Messager('操作成功', {
						   icon: 'icon-check-circle',
						    type: 'success' // 定义颜色主题
						}).show();
						init();
					}else{
						new $.zui.Messager('操作失败', {
						   icon: 'icon-exclamation-sign',
						    type: 'warning' // 定义颜色主题
						}).show();
					}
				},error:function(e){
					console.log(e);
					new $.zui.Messager('服务器繁忙。', {
					   icon: 'icon-exclamation-sign',
					    type: 'warning' // 定义颜色主题
					}).show();
				}
			});
		}, function() {
			//按钮二的回调
		});
		
		
	}
}


//刷新
document.getElementById('btn-refresh').onclick = function(){
	start = 0;//从哪里开始
	end = 5;//显示几条
	nowpage =1;//页码
	uname = "";//申请人名字
	drugname="";//药品名
	state = ""; //状态
	init();
}
