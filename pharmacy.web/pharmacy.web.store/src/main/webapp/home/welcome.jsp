<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="pharmacy.common.model.UserInfo,java.util.List" %>    
<!DOCTYPE html>
<html class="x-admin-sm">
	<head>
		<meta charset="UTF-8">
		<title>欢迎页面-X-admin2.2</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8" />
		<link rel="stylesheet" href="../3rd/layui/css/layui.css" />
		<link rel="stylesheet" href="../css/font.css">
		<link rel="stylesheet" href="../css/xadmin.css">

	</head>
	<body>
	 <!-- 获取session的值 -->
    <%
    UserInfo users=(UserInfo)request.getSession().getAttribute("user");
    if(users==null){
    	response.sendRedirect("login.jsp");
    	return;
    }
    %>
		<div class="layui-fluid">
			<div class="layui-row layui-col-space15">
				<div class="layui-col-md12">
					<div class="layui-card">
						<div class="layui-card-body ">
							<blockquote class="layui-elem-quote">欢迎管理员：
								<span class="x-red"><%=users.getUname() %></span><span id="loginTime"></span>
							</blockquote>
						</div>
					</div>
				</div>
				<div class="layui-col-md12">
					<div class="layui-card">
						<div class="layui-card-header">数据统计</div>
						<div class="layui-card-body ">
							<ul class="layui-row layui-col-space10 layui-this x-admin-carousel x-admin-backlog">
								<li class="layui-col-md2 layui-col-xs6">
									<a href="javascript:;" class="x-admin-backlog-body">
										<h3>昨日访问数</h3>
										<p>
											<cite id="viewCount">66</cite></p>
									</a>
								</li>
								<li class="layui-col-md2 layui-col-xs6">
									<a href="javascript:;" class="x-admin-backlog-body">
										<h3>药品数</h3>
										<p>
											<cite id="drugCount"></cite></p>
									</a>
								</li>
								<li class="layui-col-md2 layui-col-xs6">
									<a href="javascript:;" class="x-admin-backlog-body">
										<h3>药品禁忌个数</h3>
										<p>
											<cite id="tabooCount"></cite></p>
									</a>
								</li>
								<li class="layui-col-md2 layui-col-xs6">
									<a href="javascript:;" class="x-admin-backlog-body">
										<h3>药品停用个数</h3>
										<p>
											<cite id="stopCount"></cite></p>
									</a>
								</li>
								<li class="layui-col-md2 layui-col-xs6">
									<a href="javascript:;" class="x-admin-backlog-body">
										<h3>药品低限量数</h3>
										<p>
											<cite id="useCount"></cite></p>
									</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="layui-col-sm12 layui-col-md6">
					<div class="layui-card">
						<div class="layui-card-header">低限药品统计</div>
						<div class="layui-card-body" style="min-height: 280px;">
							<div id="main1" class="layui-col-sm12" style="height: 300px;"></div>

						</div>
					</div>
				</div>
				<div class="layui-col-sm12 layui-col-md6">
					<div class="layui-card">
						<div class="layui-card-header">药品库存统计</div>
						<div class="layui-card-body" style="min-height: 280px;">
							<div id="main2" class="layui-col-sm12" style="height: 300px;"></div>

						</div>
					</div>
				</div>
				<div class="layui-col-md12">
					<div class="layui-card">
						<div class="layui-card-header">系统信息</div>
						<div class="layui-card-body ">
							<table class="layui-table">
								<tbody>
									<tr>
										<th>智慧医院药房管理系统-版本</th>
										<td>1.0.1</td>
									</tr>
									<tr>
										<th>开发环境</th>
										<td>windows7，window10</td>
									</tr>
									<tr>
										<th>IP</th>
										<td id="ip"></td>
									</tr>
									<tr>
										<th>运行环境</th>
										<td>浏览器</td>
									</tr>
									<tr>
										<th>orcale版本</th>
										<td>11.0</td>
									</tr>
									<tr>
										<th>JDK版本</th>
										<td>1.8</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="layui-col-md12">
					<div class="layui-card">
						<div class="layui-card-header">开发团队</div>
						<div class="layui-card-body ">
							<table class="layui-table">
								<tbody>
									<tr>
										<th>版权所有</th>
										<td>智慧医院管理系统开发人员</td>
									</tr>
									<tr>
										<th>开发者</th>
										<td>智慧医院管理系统开发人员</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<style id="welcome_style"></style>
			</div>
		</div>
		</div>
	</body>
	<script src="../3rd/js/echarts.min.js"></script>
	<script src="../3rd/jquery-3.4.1.min.js"></script>
	<script type="text/javascript">
	init();
	//发送ajax
	function init(){
		$.ajax({
			url : "../welcome.action",
			type : "post",
			data : "",
			dataType : "json",
			success : function(data) {
				console.log(data);
				document.getElementById("drugCount").innerText=data.drugCount;
				document.getElementById("tabooCount").innerText=data.tabooCount;
				document.getElementById("stopCount").innerText=data.stopDrug;
				document.getElementById("useCount").innerText=data.userCount;
				document.getElementById("viewCount").innerText=data.viewTable;
			},
			error : function(e) {
				new $.zui.Messager('提示消息：服务器繁忙', {
					type: 'danger' // 定义颜色主题
					}).show();
			}
		})
	}
	//药品采购统计
	purchase();
	function purchase(){
		$.ajax({
			url : "../drugInventory.action",
			type : "post",
			data : "",
			dataType : "json",
			success : function(data) {
				console.log(data);
				//创建一个数组，用来装对象传给series.data，因为series.data里面不能直接鞋for循环
                var servicedata=[];
                var servuale=[];
				for(var i=0;i<data.length;i++){
                    var obj=new Object();
                    servicedata[i]=data[i].drugName;
                    servuale[i]=data[i].inventoryCount;
                }
				var myChart = echarts.init(document.getElementById('main2'));
				var option1 = {
						tooltip: {
					        trigger: 'axis',
					        axisPointer: {
					            type: 'cross',
					            crossStyle: {
					                color: '#999'
					            }
					        }
					    },
					    toolbox: {
					        feature: {
					            dataView: {show: true, readOnly: false},
					            magicType: {show: true, type: ['line', 'bar']},
					            restore: {show: true},
					            saveAsImage: {show: true}
					        }
					    },
					    legend: {
					        data:['数量']
					    },
					    xAxis: {
					        type: 'category',
					        data: servicedata,
					    },
					    yAxis: {
					        type: 'value'
					    },
					    series: [{
					    	name:'数量',
					        data: servuale,
					        type: 'bar'
					    }]
					};
				myChart.setOption(option1);
			},
			error : function(e) {
				new $.zui.Messager('提示消息：服务器繁忙', {
					type: 'danger' // 定义颜色主题
					}).show();
			}
		});
	}
	
	viewVolume();
	//近7天网站访问量
	function viewVolume(){
		$.ajax({
			url : "../miniMum.action",
			type : "post",
			data : "",
			dataType : "json",
			success : function(data) {
				console.log(data);
				//创建一个数组，用来装对象传给series.data，因为series.data里面不能直接鞋for循环
                var servalue=[];
                var serTime=[];
				for(var i=0;i<data.length;i++){
                    var obj=new Object();
                    servalue[i]=data[i].minimumVal;
					serTime[i]=data[i].drugName;
                }
				// 基于准备好的dom，初始化echarts实例
				var myChart = echarts.init(document.getElementById('main1'));

				// 指定图表的配置项和数据
				var option = {
					grid: {
						top: '5%',
						right: '1%',
						left: '1%',
						bottom: '10%',
						containLabel: true
					},
					tooltip: {
						trigger: 'axis'
					},
					xAxis: {
						type: 'category',
						data: serTime
					},
					yAxis: {
						type: 'value'
					},
					series: [{
						name: '用户量',
						data: servalue,
						type: 'line',
						smooth: true
					}]
				};

				// 使用刚指定的配置项和数据显示图表。
				myChart.setOption(option);
			},
			error : function(e) {
				new $.zui.Messager('提示消息：服务器繁忙', {
					type: 'danger' // 定义颜色主题
					}).show();
			}
		});
	}
	//获取本地的ip
	var RTCPeerConnection = window.RTCPeerConnection || window.webkitRTCPeerConnection || window.mozRTCPeerConnection;
		if (RTCPeerConnection)(function() {
			var rtc = new RTCPeerConnection({
				iceServers: []
			});
			if (1 || window.mozRTCPeerConnection) {
				rtc.createDataChannel('', {
					reliable: false
				});
			};

			rtc.onicecandidate = function(evt) {
				if (evt.candidate) grepSDP("a=" + evt.candidate.candidate);
			};
			rtc.createOffer(function(offerDesc) {
				grepSDP(offerDesc.sdp);
				rtc.setLocalDescription(offerDesc);
			}, function(e) {
				console.warn("offer failed", e);
			});


			var addrs = Object.create(null);
			addrs["0.0.0.0"] = false;

			function updateDisplay(newAddr) {
				if (newAddr in addrs) return;
				else addrs[newAddr] = true;
				var displayAddrs = Object.keys(addrs).filter(function(k) {
					return addrs[k];
				});
				for (var i = 0; i < displayAddrs.length; i++) {
					if (displayAddrs[i].length > 16) {
						displayAddrs.splice(i, 1);
						i--;
					}
				}
				console.log(displayAddrs[0]); //打印出内网ip
				document.getElementById("ip").innerText = displayAddrs[0];
			}

			function grepSDP(sdp) {
				var hosts = [];
				sdp.split('\r\n').forEach(function(line, index, arr) {
					if (~line.indexOf("a=candidate")) {
						var parts = line.split(' '),
							addr = parts[4],
							type = parts[7];
						if (type === 'host') updateDisplay(addr);
					} else if (~line.indexOf("c=")) {
						var parts = line.split(' '),
							addr = parts[2];
						updateDisplay(addr);
					}
				});
			}
		})();
		else {
			console.log("请使用主流浏览器：chrome,firefox,opera,safari");
		}

		//获取当前时间
		window.onload=function(){ 
setInterval(function(){  
var date=new Date();  
var year=date.getFullYear(); //获取当前年份  
var mon=date.getMonth()+1; //获取当前月份  
var da=date.getDate(); //获取当前日  
var day=date.getDay(); //获取当前星期几  
var h=date.getHours(); //获取小时  
var m=date.getMinutes(); //获取分钟  
var s=date.getSeconds(); //获取秒  
var d=document.getElementById('loginTime');  
if(h<10){
	h="0"+h
}else if(m<10){
	m="0"+m
}else if(s<10){
	s="0"+s
}
if(day=1){
	day="一";
}else if(day=2){
	day="二";
}else if(day=2){
	day="三";
}else if(day=2){
	day="四";
}else if(day=2){
	day="五";
}else if(day=2){
	day="六";
}
d.innerHTML='当前时间:'+year+'年'+mon+'月'+da+'日'+'星期'+day+' '+h+':'+m+':'+s; },1000) }

</script>
</html>
