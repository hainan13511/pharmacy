
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
	var table = document.getElementById('table-edit');
	
	var start = 0;
	var end = 5;
	var nowpage =1;
	var allpage =1;	
	var drugname="";
	var batch="";
	
	
	function init(){
		
		$.ajax({		
			url:"../init.action",
			type:"post",
			data:"start="+start+"&end="+end+"&drugname="+drugname+"&batch="+batch,
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
					td1.innerText=data[i].rn;
					
					var td2=document.createElement('td');
					td2.innerText=data[i].drugName;
					
					var td3=document.createElement('td');
					td3.innerText=data[i].unit;
					
					var td4=document.createElement('td');
					td4.innerText=data[i].houseCount;
							
					var td5=document.createElement('td');
					td5.innerText=data[i].dwdid;
					
					
					var td6=document.createElement('td');
					
					td6.innerHTML='<button class="btn btn-link btn-sm" houseid='+data[i].houseid+' drugId='+data[i].drugId+' dwdid='+data[i].dwdid+' type="button">退库</button>'
					
					tr.appendChild(td1);
					tr.appendChild(td2);
					tr.appendChild(td3);
					tr.appendChild(td4);
					tr.appendChild(td5);
					tr.appendChild(td6);
					
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
		console.log(nowpage)
		console.log(allpage)
		if(nowpage >= allpage){
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
		batch=document.getElementById('text-batch').value;
		
		start = 0;
		end = 5;
		nowpage =1; 
		document.getElementById('nowpage').innerText=1;
		init();			
	}

var houseid ;
var drugid ;
var dwdid ;
table.onclick = function(e){
	var tar = e.target;
	console.log(tar);
	var text = tar.innerText; 
	if(text == "退库"){
		$('#userModal').modal('show', 'fit')
		houseid = tar.getAttribute('houseid');
		drugid = tar.getAttribute('drugId');
		dwdid = tar.getAttribute('dwdid');
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
				url:"../modifyInventory.action",
				type:"post",
				data:"houseid="+houseid+"&count="+drug_count+"&dwdid="+dwdid+"&drugid="+drugid,
				dataType:"json",
				success:function(data){	
					console.log(data);
					if(data.result == "yes"){
						new $.zui.Messager('退库成功', {
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
						new $.zui.Messager('退库失败！', {
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
			new $.zui.Messager('数量只能输入数字，或数量不能小于0！', {
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
