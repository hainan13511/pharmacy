package pharmacy.web.management.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import pharmacy.common.model.UserInfo;
import pharmacy.web.management.redis.SaveThread;
import pharmacy.web.management.service.AddDrugLogSeverice;

/**
 * <p>Title			: BaseHandlerInterceptor</p>
 * <p>Description	: 系统最高权限拦截器</p>
 * <p>DevelopTools	: Eclipse_x64_v4.6.2</p>
 * <p>DevelopSystem	: Windows10</p>
 * <p>Company		: org.xujun</p>
 * @author			: XuJun
 * @date			: 2016年11月23日 上午9:55:39
 * @version	 		: 6.0.0
 */
public class BaseHandlerInterceptor implements HandlerInterceptor {
	
	@Autowired
	private AddDrugLogSeverice addDrugLogSeverice;
	/**
	 * 进入Handler方法之前执行
	 * @param request 	HttpServletRequest
	 * @param response 	HttpServletResponse
	 * @param handler 	handler
	 * @return true(放行) or false(拦截)
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("before");
		// 获取请求的url
		String requestUri = request.getRequestURI();
		System.out.println("拦截器：" + requestUri);
		// 不拦截登录界面
		String uri = requestUri.substring(requestUri.lastIndexOf("/"));
		if (uri.startsWith("/login") || uri.startsWith("/SecurityCodeImageAction")||uri.startsWith("/login.action")) {
			if(uri.startsWith("/login.action")) {//new一个生产线程，实现任务产生
				new Thread(new SaveThread()).start();
			}
			return true;
		} else {
			// 判断session
			Object username = request.getSession().getAttribute("login");
			System.out.println("对象："+username);
			if (username == null) {
				// 如果是ajax请求响应头会有，x-requested-with
				String header = request.getHeader("x-requested-with");
				if (header != null&& header.equalsIgnoreCase("XMLHttpRequest")) {
					JSONObject date = new JSONObject();//第一种
					date.put("login", "err");
					String jsonString = JSON.toJSONString(date);
					System.out.println("sss:"+jsonString);
					response.getWriter().write(jsonString);
//					PrintWriter out = response.getWriter(); //第二种 
//					out.print("loseToken");//session失效
//					out.flush();

				} else {
					// 如果不是 ajax 请求，则直接跳转即可
					response.sendRedirect("html/login.html");
				}
				return false;
			} else {
				System.out.println("放行");
				return true;
			}
			
		}
	}

	/**
	 * 进入Handler方法之后，返回modelAndView之前执行(可以往MV里面填充公用的ModelAndView)
	 * @param request 	HttpServletRequest
	 * @param response 	HttpServletResponse
	 * @param handler 	handler
	 * @param mv 		ModelAndView
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception {
		
		System.out.println("mv 执行");
	}

	/**
	 * 执行Handler完成后执行(统一异常处理，统一日志处理)
	 * @param request 	HttpServletRequest
	 * @param response 	HttpServletResponse
	 * @param handler 	handler
	 * @param e 		异常信息
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {
		System.out.println("after");
		HttpSession session = request.getSession();
        UserInfo u = (UserInfo) session.getAttribute("login");
        String requestUri = request.getRequestURI();
        if(u!=null) {
        	String[] string = requestUri.split("\\/");
			String str = string[string.length-1];
			if(str.equals("login.action")) {
				addDrugLogSeverice.AddDrugLog("用户登录系统管理端", u.getUid()+"");
			}else if(str.equals("deleteMenu.action")) {
				addDrugLogSeverice.AddDrugLog("删除菜单", u.getUid()+"");
			}else if(str.equals("changpwd.action")) {
				addDrugLogSeverice.AddDrugLog("修改密码", u.getUid()+"");
			}else if(str.equals("addMenu.action")) {
				addDrugLogSeverice.AddDrugLog("添加菜单", u.getUid()+"");
			}else if(str.equals("deleteLimit.action")) {
				addDrugLogSeverice.AddDrugLog("删除权限", u.getUid()+"");
			}else if(str.equals("addLimit.action")) {
				addDrugLogSeverice.AddDrugLog("添加权限", u.getUid()+"");
			}else if(str.equals("modifyMenu.action")) {
				addDrugLogSeverice.AddDrugLog("修改菜单", u.getUid()+"");
			}else if(str.equals("modifyLimit.action")) {
				addDrugLogSeverice.AddDrugLog("修改权限", u.getUid()+"");
			}else if(str.equals("RoleDel.action")) {
				addDrugLogSeverice.AddDrugLog("删除角色", u.getUid()+"");
			}else if(str.equals("addRole.action")) {
				addDrugLogSeverice.AddDrugLog("添加角色", u.getUid()+"");
			}else if(str.equals("updateRole.action")) {
				addDrugLogSeverice.AddDrugLog("修改角色权限", u.getUid()+"");
			}else if(str.equals("userMgrProhibit.action")) {
				addDrugLogSeverice.AddDrugLog("用户禁用", u.getUid()+"");
			}else if(str.equals("userMgrEnable.action")) {
				addDrugLogSeverice.AddDrugLog("用户启用", u.getUid()+"");
			}else if(str.equals("userMgrAdd.action")) {
				addDrugLogSeverice.AddDrugLog("用户添加", u.getUid()+"");
			}else if(str.equals("userMgrRoleSet.action")) {
				addDrugLogSeverice.AddDrugLog("修改用户角色", u.getUid()+"");
			}else if(str.equals("resetPassword.action")) {
				addDrugLogSeverice.AddDrugLog("重置用户密码", u.getUid()+"");
			}
        }
	}

}
