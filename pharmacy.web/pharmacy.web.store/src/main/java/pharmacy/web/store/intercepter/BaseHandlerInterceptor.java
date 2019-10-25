package pharmacy.web.store.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import pharmacy.common.model.UserInfo;
import pharmacy.web.store.service.AddDrugLogSeverice;

/**
 * <p>Title			: BaseHandlerInterceptor</p>
 * <p>Description	: 系统最高权限拦截器</p>
 * <p>DevelopTools	: Eclipse_x64_v4.6.2</p>
 * <p>DevelopSystem	: Windows10</p>
 * <p>Company		: org.yhn</p>
 * @author			: yhn
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
			return true;
		} else {
			// 判断session
			Object username = request.getSession().getAttribute("user");
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
					response.sendRedirect("/home/login.jsp");
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
        UserInfo u = (UserInfo) session.getAttribute("user");
        String requestUri = request.getRequestURI();
        if(u!=null) {
        	String[] string = requestUri.split("\\/");
			String str = string[string.length-1];
			if(str.equals("changpwd.action")) {
				addDrugLogSeverice.AddDrugLog("修改密码", u.getUid()+"");
			}else if(str.equals("alterApply.action")) {
				addDrugLogSeverice.AddDrugLog("修改请领单", u.getUid()+"");
			}else if(str.equals("DrugApplyConfirm.ajax")) {
				addDrugLogSeverice.AddDrugLog("新增请领单", u.getUid()+"");
			}else if(str.equals("updateDrugPrice.ajax")) {
				addDrugLogSeverice.AddDrugLog("调整药品零售价", u.getUid()+"");
			}else if(str.equals("drugBreakage.ajax")) {
				addDrugLogSeverice.AddDrugLog("药品报损", u.getUid()+"");
			}else if(str.equals("drugSend.ajax")) {
				addDrugLogSeverice.AddDrugLog("药品发药", u.getUid()+"");
			}else if(str.equals("drugStopUseBtn.action")) {
				addDrugLogSeverice.AddDrugLog("药品停用", u.getUid()+"");
			}else if(str.equals("drugStartUseBtn.action")) {
				addDrugLogSeverice.AddDrugLog("药品恢复", u.getUid()+"");
			}else if(str.equals("drugBreakage1.action")) {
				addDrugLogSeverice.AddDrugLog("药品报损", u.getUid()+"");
			}else if(str.equals("drugapplty1.action")) {
				addDrugLogSeverice.AddDrugLog("药品请领", u.getUid()+"");
			}else if(str.equals("modifyInventory1.action")) {
				addDrugLogSeverice.AddDrugLog("药品退库", u.getUid()+"");
			}else if(str.equals("modifyInventory.action")) {
				addDrugLogSeverice.AddDrugLog("药品退库", u.getUid()+"");
			}else if(str.equals("login.action")) {
				addDrugLogSeverice.AddDrugLog("用户登录药房", u.getUid()+"");
			}
        }
	}

}
