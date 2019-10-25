package pharmacy.web.store.controller;

import java.awt.image.BufferedImage;
import java.security.interfaces.RSAPrivateKey;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pharmacy.common.model.UserInfo;
import pharmacy.common.utils.Md5Util;
import pharmacy.common.utils.RSAUtils;
import pharmacy.common.utils.SecurityCode;
import pharmacy.common.utils.SecurityImage;
import pharmacy.common.utils.StringUtil;
import pharmacy.web.store.model.dto.ResultUserInfoAndMenu;
import pharmacy.web.store.service.DrugWarningService;
import pharmacy.web.store.service.LoginService;
import pharmacy.web.store.utils.MyThread;

/**
 * 
 * <p>Title : LoginController</p>
 * <p>Description : </p>
 * <p>DevelopTools : Eclipse_x64_v4.10.0</p>
 * <p>DevelopSystem : windows 10</p>
 * <p>Company : org.yhn</p>
 * @author : Yhn
 * @date : 2019年8月27日 下午11:02:56
 * @version : 1.0.0
 */

@Controller
public class LoginController {

	private MyThread rn;
    /**
     * 图片验证码
     * 
     * @param response HttpServletResponse
     * @param time     随机时间
     * @throws Exception 图片异常
     */

    @Autowired
    private DrugWarningService drugWarningService;
    
    @RequestMapping("/SecurityCodeImageAction.action")
    public void imageCode(HttpServletRequest request, HttpServletResponse response, String time) throws Exception {
    	String securityCode = SecurityCode.getSecurityCode();
		System.out.println("随机生成的验证码："+securityCode);
		request.getSession().setAttribute("storeCode", securityCode);
        BufferedImage createImage = SecurityImage.createImage(securityCode);
        ImageIO.write(createImage, "jpg", response.getOutputStream()); // 将图片验证码输出
    }

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     * 
     * @param username
     * @param password
     * @param request
     * @return
     * @throws Exception 
     */
    @RequestMapping("/login.action")
    @ResponseBody
    public int login(String username, String password, String code, HttpServletRequest request) throws Exception {
        // 校验
        if (StringUtil.isAllEmpty(username, password, code)) {
            return 3;
        }
        String securityCode = (String)request.getSession().getAttribute("storeCode");
        System.out.println("登录接收到信息：账号" + username + ",密码：" + password + ",验证码：" + code.toLowerCase());
        if (code.toLowerCase()!=null&&!securityCode.toLowerCase().equals(code.toLowerCase())) {
            return 2;
        }
        
        
		//RSA解密
		RSAPrivateKey privateKey = (RSAPrivateKey) request.getSession().getAttribute("privateKeyStoreLogin");
		System.out.println("账号：" + username);
		System.out.println("密码：" + password);
		//System.out.println("公钥：" + privateKey);
		username = RSAUtils.decryptByPrivateKey(username, privateKey);
		System.out.println("解密后账号1:" + username);
		password= RSAUtils.decryptByPrivateKey(password, privateKey);
		System.out.println("解密后密码2:" + password);
		password = Md5Util.MD5(password);
		System.out.println("MD5加密后"+password);
        // 数据库操作
        ResultUserInfoAndMenu totality = loginService.login(username, password);
        if (totality.getUserInfo() != null) {
            System.out.println("44:"+totality.getUserInfo().toString());
            System.out.println("权限:"+totality.getMenuDto().toString());
            // 判断没有权限
            if (totality.getMenuDto().isEmpty()) {
                return 4;
            }
            // 保存登录人的session
            UserInfo users=totality.getUserInfo();
            request.getSession().setAttribute("user",users);
            System.out.println("保存了");
            // 保存权限的session
            request.getSession().setAttribute("Limit", totality.getMenuDto());
            System.out.println("asdsad:"+users.toString());
            if(rn==null) {
            	rn = new MyThread(drugWarningService);
            	Thread thread = new Thread(rn);
            	thread.start();
            }
//            MyThread rn = new MyThread(drugWarningService);
//            Thread thread = new Thread(rn);
//            thread.start();
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
        request.getSession().removeAttribute("user");
        //重定向跳转页面
        response.sendRedirect("home/login.jsp");
    }
}
