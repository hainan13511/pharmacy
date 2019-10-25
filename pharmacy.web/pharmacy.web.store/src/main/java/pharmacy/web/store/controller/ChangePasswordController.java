package pharmacy.web.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pharmacy.common.utils.Md5Util;
import pharmacy.web.store.service.ChangePasswordSeverice;

@Controller
public class ChangePasswordController {

	@Autowired
	private ChangePasswordSeverice changePasswordSeverice;

	@RequestMapping("/changpwd.action")
	@ResponseBody
	public String changePwd(String account, String oldpassword, String newpassword) {
		System.out.println("接收到信息：" + account + "," + oldpassword + "," + newpassword);
		// md5加密
		String md5Newpassword = Md5Util.MD5(newpassword);
		String md5Oldpassword = Md5Util.MD5(oldpassword);
		System.out.println("md5加密：新--》" + md5Newpassword + ",旧密码--》" + md5Oldpassword);
		String changePwd = changePasswordSeverice.changePwd(md5Oldpassword, md5Newpassword, account);
		System.out.println("修改密码的结果：" + changePwd);
		return changePwd;
	}
}
