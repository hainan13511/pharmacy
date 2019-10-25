
	//刷新界面
	document.getElementById('btn-refresh').onclick = function() {	
		location.reload();	
	}	
	//清空
	document.getElementById('btn-null').onclick = function() {
		document.getElementById('text-drug').value = "";
		document.getElementById('text-supply').value="";
		document.getElementById('text-uaccount').value="";
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
	
	
	
	
	
	
	
	var table = document.getElementById('table-edit');
	
	var start = 0;
	var end = 5;
	var nowpage =1;
	var allpage =1;	
	var drugname="";
	var supply="";
	var uname="";
	
	
	function init(){
		
		$.ajax({		
			url:"../init.action",
			type:"post",
			data:"start="+start+"&end="+end+"&drugname="+drugname+"&supply="+supply
			+"&uname="+uname,
			dataType:"json",
			success:function(datas){
				console.log(datas);
				var data=datas.list;
				allpage=datas.pageNum;	

				document.getElementById('allpage').innerText=Math.floor(allpage);
 		 		var trs = document.getElementsByClassName('drug-tr');
	 			//清除数据
				for(var i = trs.length-1;i>=0;i--){
					trs[0].remove();
				} 			
				for (var i = 0; i < data.length; i++) {
					
					var tr = document.createElement('tr');
					
					tr.classList.add("drug-tr");
					
					var td1=document.createElement('td');
					td1.innerText= i+1;
					
					var td2=document.createElement('td');
					td2.innerText=data[i].drugName;
					
					var td3=document.createElement('td');
					td3.innerText=data[i].uname;
					
					var td4=document.createElement('td');
					td4.innerText=new Date(data[i].purchasingTime).Format('yyyy-MM-dd hh:mm:ss');
							
					var td5=document.createElement('td');
					td5.innerText=data[i].provider;
					
					var td6=document.createElement('td');
					td6.innerText=data[i].inventoryCount;
					
					var td7=document.createElement('td');
					td7.innerHTML='<button class="btn btn-link btn-sm" inventoryid='+data[i].inventoryid+' type="button">退还厂家</button>'
					
					tr.appendChild(td1);
					tr.appendChild(td2);
					tr.appendChild(td3);
					tr.appendChild(td4);
					tr.appendChild(td5);
					tr.appendChild(td6);
					tr.appendChild(td7);
					
					table.appendChild(tr);	 		
				 }  
			},		
				error:function(e){
				    new $.zui.Messager('初始化表请求失败！', {
	 			        icon: 'bell' // 定义消息图标
	 			    }).show();										
				}
		})
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
		if(nowpage<=0){
		    new $.zui.Messager('已经是第一页了！', {
			        icon: 'bell' // 定义消息图标
			    }).show();			
			
		}else{
			start=start-end;
			nowpage = nowpage-1;
			document.getElementById('nowpage').innerText=nowpage;
			init();	
		}
	}
	
	
	//搜索
	document.getElementById('btn-search').onclick=function(){
		drugname=document.getElementById('text-drug').value;
		supply=document.getElementById('text-supply').value;
		uname=document.getElementById('text-uaccount').value;
		
		start = 0;
		end = 5;
		nowpage =1; 
		document.getElementById('nowpage').innerText=1;
		 init();			
	}

var inventoryid ;
table.onclick = function(e){
	var tar = e.target;
	console.log(tar);
	var text = tar.innerText; 
	if(text == "退还厂家"){
		$('#userModal').modal('show', 'fit')
		inventoryid = tar.getAttribute('inventoryid');
	}
}


document.getElementById('out-go').onclick = function(){
	var drug_count = document.getElementById('drug-count').value.trim();
	if(drug_count == ""){
		 new $.zui.Messager('请输入退还数量！', {
		   icon: 'icon-exclamation-sign',
			type: 'warning' // 定义颜色主题
		}).show();
	}else{
		var flag= /^\+?[1-9][0-9]*$/.test(drug_count);
		if(flag){
			$.ajax({
				url:"../reduceInventories.action",
				type:"post",
				data:"inventoryid="+inventoryid+"&drug_count="+drug_count,
				dataType:"json",
				success:function(data){	
					console.log(data);
					if(data.result == "yes"){
						new $.zui.Messager('退还厂家成功', {
						   icon: 'icon-check-circle',
						    type: 'success' // 定义颜色主题
						}).show();
						$('#userModal').modal('hide', 'fit')
						init();
					}else if(data.result == "insufficient"){
						new $.zui.Messager('库存大于现有库存请核对库存！', {
							   icon: 'icon-exclamation-sign',
								type: 'warning' // 定义颜色主题
						}).show();
					}else{
						new $.zui.Messager('退还厂家失败！', {
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
			
		}else{
			new $.zui.Messager('请输入大于0数字！', {
			   icon: 'icon-exclamation-sign',
				type: 'warning' // 定义颜色主题
			}).show();
		}
	}
}
//点击返回是关闭弹窗
document.getElementById('shop-exit').onclick = function(){
	$('#userModal').modal('hide', 'fit')
}
//关闭弹窗清空输入框
$('#userModal').on('hide.zui.modal', function() {
  document.getElementById('drug-count') .value= '' ;
})
