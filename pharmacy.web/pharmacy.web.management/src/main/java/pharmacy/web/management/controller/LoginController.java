package pharmacy.web.management.controller;

import java.awt.image.BufferedImage;
import java.security.interfaces.RSAPrivateKey;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pharmacy.common.utils.Md5Util;
import pharmacy.common.utils.RSAUtils;
import pharmacy.common.utils.SecurityCode;
import pharmacy.common.utils.SecurityImage;
import pharmacy.common.utils.StringUtil;
import pharmacy.web.management.model.dto.ResultUserInfoAndMenu;
import pharmacy.web.management.service.LoginService;

/**
 * 登录的controller
 * <p>Title : LoginController</p>
 * <p>Description : </p>
 * <p>DevelopTools : Eclipse_x64_v4.10.0</p>
 * <p>DevelopSystem : Windows7</p>
 * <p>Company : org.kjb
</p>
 * @author : kejianbin
 * @date : 2019年8月23日 上午11:29:12
 * @version : 1.0.0
 */


@Controller
public class LoginController {
	
	
	
	/**
	 * 图片验证码
	 * @param response 		HttpServletResponse
	 * @param time 			随机时间
	 * @throws Exception 	图片异常
	 */
	@RequestMapping("/SecurityCodeImageAction.action")
	public void imageCode(HttpServletRequest request, HttpServletResponse response, String time) throws Exception {
		String securityCode = SecurityCode.getSecurityCode();
		System.out.println("随机生成的验证码："+securityCode);
		request.getSession().setAttribute("mCode", securityCode);
		BufferedImage createImage = SecurityImage.createImage(securityCode);
		String securityCode1 = (String)request.getSession().getAttribute("mCode");
		System.out.println("判断是否存入到了session中"+securityCode1);
		ImageIO.write(createImage, "jpg", response.getOutputStream()); // 将图片验证码输出
	}
	
	@Autowired
	private LoginService loginService;
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/login.action")
	@ResponseBody
	public int  login(String username,String password,String code,HttpServletRequest request) throws Exception {
		//校验
		if(StringUtil.isAllEmpty(username,password,code)) {
			return 3;
		}
		
		System.out.println("登录接收到信息：账号"+username+",密码："+password+",验证码："+code.toLowerCase());
		String securityCode = (String)request.getSession().getAttribute("mCode");
		if(code.toLowerCase()!=null&&!securityCode.toLowerCase().equals(code.toLowerCase())) {
			return 2;
		}
		
		//RSA解密
		RSAPrivateKey privateKey = (RSAPrivateKey) request.getSession().getAttribute("privateKeyMagLogin");
		System.out.println("账号：" + username);
		System.out.println("密码：" + password);
		//System.out.println("公钥：" + privateKey);
		username = RSAUtils.decryptByPrivateKey(username, privateKey);
		System.out.println("解密后账号1:" + username);
		password= RSAUtils.decryptByPrivateKey(password, privateKey);
		System.out.println("解密后密码2:" + password);
		password = Md5Util.MD5(password);
		System.out.println("MD5加密后"+password);
		//数据库操作
		ResultUserInfoAndMenu totality = loginService.login(username, password);
		if(totality.getUserInfo()!=null) {
			System.out.println("权限："+totality.getMenuDto());
			//判断没有权限
			if(totality.getMenuDto().isEmpty()) {
				return 4;
			}
			//保存登录人的session
			request.getSession().setAttribute("login", totality.getUserInfo());
			//保存权限的session
			request.getSession().setAttribute("Limit",totality.getMenuDto());
			return 1;
		}
		return 0;
	}
	
	/**
	 * 退出
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/exit.action")
	public void exitLogin(HttpServletRequest request,HttpServletResponse response)throws Exception {
		//清空session
		request.getSession().removeAttribute("login");
		//重定向跳转页面
		response.sendRedirect("html/login.html");
	}
}
