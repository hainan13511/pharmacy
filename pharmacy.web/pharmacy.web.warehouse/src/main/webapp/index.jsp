<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="pharmacy.common.model.UserInfo,pharmacy.web.warehouse.model.dto.MenuDto,java.util.List" %>    
<!doctype html>
<html class="x-admin-sm">
    <head>
        <meta charset="UTF-8">
        <title>药库管理</title>

        <meta name="renderer" content="webkit|ie-comp|ie-stand">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
       <!--  <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8" /> -->
        <meta http-equiv="Cache-Control" content="no-siteapp" />
        <link rel="stylesheet" href="./css/font.css">
        <link rel="stylesheet" href="./css/xadmin.css">
        <!-- <link rel="stylesheet" href="./css/theme5.css"> -->
        <script src="3rd/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="./js/xadmin.js"></script>
		<link rel="stylesheet" href="3rd/layui/css/layui.css">
		<script type="text/javascript"src="3rd/jq/jquery.min.js"></script>
        <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
        <!--[if lt IE 9]>
          <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
          <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <script>
            // 是否开启刷新记忆tab功能
            // var is_remember = false;
        </script>
    </head>
    <!-- 获取session的值 -->
    <%
    UserInfo users=(UserInfo)request.getSession().getAttribute("login");
    if(users==null){
    	response.sendRedirect("html/login.html");
    	return;
    }
    %>
    <body class="index">
        <!-- 顶部开始 -->
        <div class="container">
            <div class="logo">
                <a href="./index.jsp">智能医院药库管理端 </a></div>
            <div class="left_open">
                <a><i title="展开左侧栏" class="iconfont">&#xe699;</i></a>
            </div>
            
            <ul class="layui-nav right" lay-filter="">
                <li class="layui-nav-item">
                    <a href="javascript:;"><%=users.getUname() %></a>
                    <dl class="layui-nav-child">
                        <!-- 二级菜单 -->
                        <dd>
                            <a onclick="xadmin.open('修改密码','home/changePassword.jsp')">修改密码</a></dd>
                        <dd>
                            <a href="exit.action">退出</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
        <!-- 顶部结束 -->
        <!-- 中部开始 -->
        <!-- 左侧菜单开始 -->
       
        <div class="left-nav">
            <div id="side-nav">
                <ul id="nav">
                 <%
			       List<MenuDto> list=(List<MenuDto>)request.getSession().getAttribute("Limit");
					for (int i = 0; i < list.size(); i++) {
						System.out.println(list.toString());
					
			        %>
                    <li>
                        <a href="javascript:;">
                           <i class="layui-icon <%=list.get(i).getIcon()%>"></i>   
							<cite><%=list.get(i).getMenuname() %></cite>
                            <i class="iconfont nav_right">&#xe697;</i></a>
                            <%for(int j = 0; j < list.get(i).getSecondMenu().size(); j++){ %>
                            	<ul class="sub-menu">
                            <li>
                                <a onclick="xadmin.add_tab('<%=list.get(i).getSecondMenu().get(j).getLimitname() %>','home/<%=list.get(i).getSecondMenu().get(j).getUrl() %>')">
                                    <i class="iconfont">&#xe6a7;</i>
                                    <cite><%=list.get(i).getSecondMenu().get(j).getLimitname() %></cite></a>
                            </li>
                        </ul>
                            <% } %>
                    </li>
					<%} %>
                </ul>
            </div>
            
        </div>
        <!-- <div class="x-slide_left"></div> -->
        <!-- 左侧菜单结束 -->
        <!-- 右侧主体开始 -->
       <div class="page-content">
                   <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
                       <ul class="layui-tab-title">                      
                           <li class="home">                          
                               <i class="layui-icon">&#xe68e;</i>我的桌面</li></ul>
                       <div class="layui-unselect layui-form-select layui-form-selected" id="tab_right">                           
                           <dl>
                               <dd data-type="this">关闭当前</dd>
                               <dd data-type="other">关闭其它</dd>
                               <dd data-type="all">关闭全部</dd></dl>
                       </div>
                       <div class="layui-tab-content">
                           <div class="layui-tab-item layui-show">
                               <iframe src='home/welcome.jsp' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
                           </div>
                       </div>
                       <div id="tab_show"></div>
                   </div>
               </div>
        <div class="page-content-bg"></div>
        <style id="theme_style"></style>
        <!-- 右侧主体结束 -->
        <!-- 中部结束 -->
        <script>//百度统计可去掉
        var is_remember = false;
            var _hmt = _hmt || []; (function() {
                var hm = document.createElement("script");
                hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
                var s = document.getElementsByTagName("script")[0];
                s.parentNode.insertBefore(hm, s);
            })();
            </script>
    </body>
<script type="text/javascript">

var websocket = null;
/* 判断浏览器是否支持websocket */
if ('WebSocket' in window) {
	/* alert("开始连接服务器"); */
	websocket = new WebSocket("ws://127.0.0.1:8080/pharmacy.web.warehouse/websocket");
	websocket.onopen = function() {
		
		websocket.send("客户端连接成功");
	}
	websocket.οnerrοr = function() {
		
		websocket.send("客户端连接失败");
	}
	websocket.onclose = function() {
		
		websocket.send("客户端连接关闭");
	}
	websocket.onmessage = function(e) {
		send(e.data);
	}
	/*监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。	 */
	window.onbeforeunload = function() {
		closeWebSocket();
	}
} else {
	alert('当前浏览器 Not support websocket')
}

var message="";
var str="";
var obj="";
/* 将消息显示在网页上 */
function send(e) {
	/*  alert(e);  */ 
	message = eval("(" + e + ")"); 
	str="";
	if(""+e+""!=""+obj+""){
		for(var i=0;i<message.length;i++){	
			str+='<div style="padding: 5px 30px;">'+message[i]+'</div>';
		}
		  layer.open({		  
			  type: 1
			  ,shade: 0 
			  ,title:'警报'
			  ,content:str
			  ,scrollbar:false
			  ,offset: 'r' 
			  ,skin: 'demo-class'
			  ,btn: '关闭全部'
			  ,btnAlign: 'c' 
	          ,yes: function(){
		          layer.closeAll();
		        }
			}); 
	}
	obj=e;

}


/* 关闭WebSocket连接 */
function closeWebSocket() {
	websocket.close();
}
</script>

</html>