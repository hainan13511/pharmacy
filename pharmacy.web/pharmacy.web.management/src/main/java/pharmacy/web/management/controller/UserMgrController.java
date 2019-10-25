package pharmacy.web.management.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import pharmacy.common.model.Role;
import pharmacy.common.model.UserInfo;
import pharmacy.common.utils.Md5Util;
import pharmacy.web.management.service.UserMgrService;

@Controller
public class UserMgrController {

	@Autowired
	private UserMgrService userMgrService;
	//
	@RequestMapping("/userMgrTable.action")
	@ResponseBody
	public ArrayList<UserInfo> userMgrTable(int ks,int jt,String stime,String etime,String uname,HttpServletRequest request, HttpServletResponse response) {
		UserInfo user = (UserInfo) request.getSession().getAttribute("login");
		RowBounds rn = new RowBounds((ks-1)*jt, jt);
		ArrayList<UserInfo> list = userMgrService.userTable(user.getUid(),stime, etime, uname, rn);
		return list;
	}
	@RequestMapping("/userMgrTableCount.action")
	@ResponseBody
	public int userMgrCount(String stime,String etime,String uname,HttpServletRequest request, HttpServletResponse response) {
		int a = 0;
		UserInfo user = (UserInfo) request.getSession().getAttribute("login");
		a = userMgrService.count(user.getUid(),stime, etime, uname);		
		return a;
	}
	//System.out.println("用户禁用");
	@RequestMapping("/userMgrProhibit.action")
	@ResponseBody
	public String userMgrProhibit(HttpServletRequest request, HttpServletResponse response,int uid){
		
		String str = "no";
		UserInfo user = (UserInfo) request.getSession().getAttribute("login");
		int a = userMgrService.prohibit(uid, user.getUid());
		if(a>-1) {
			str = "yes";
		}
		return str;
	}
	//System.out.println("用户启用");
	@RequestMapping("/userMgrEnable.action")
	@ResponseBody
	public String userMgrEnable(HttpServletRequest request, HttpServletResponse response,int uid){
		
		String str = "no";
		UserInfo user = (UserInfo) request.getSession().getAttribute("login");
		int a = userMgrService.enable(uid, user.getUid());
		if(a>-1) {
			str = "yes";
		}
		return str;
	}
//	System.out.println("重置用户密码");
	@RequestMapping("/resetPassword.action")
	@ResponseBody
	public String resetPassword(HttpServletRequest request, HttpServletResponse response,int uid){
		
		String str = "no";
		String pwd = "123456";
		pwd = Md5Util.MD5(pwd);
		UserInfo user = (UserInfo) request.getSession().getAttribute("login");
		int a = userMgrService.resetPassword(uid, user.getUid(),pwd);
		if(a>-1) {
			str = "yes";
		}
		return str;
	}
	//System.out.println("用户添加");
	@RequestMapping("/userMgrAdd.action")
	@ResponseBody
	public String userMgrAdd(HttpServletRequest request, HttpServletResponse response,String uname,String uacc,String upwd,String list){
		
		String str = "no";
		System.out.println("list="+list);
		ArrayList<String> arr = new ArrayList<String>();
		if(!list.equals("")) {
			arr = (ArrayList<String>) JSON.parseArray(list,String.class);
		}
		UserInfo user = (UserInfo) request.getSession().getAttribute("login");
		System.out.println("uname="+uname);
		System.out.println("uacc="+uacc);
		upwd = Md5Util.MD5(upwd);
		System.out.println("upwd="+upwd);
		str = userMgrService.userMgrAdd(uname, uacc, upwd, user.getUid(),arr);
		return str;
	}
	@RequestMapping("/userMgrRole.action")
	@ResponseBody
	public ArrayList<Role> userMgrRole(){
		ArrayList<Role> list = new ArrayList<Role>();	
		list = userMgrService.userMgrRole();
		return list;
	}
	@RequestMapping("/userMgrRoleGet.action")
	@ResponseBody
	public ArrayList<String> userMgrRoleGet(int uid){
		ArrayList<String> list = new ArrayList<String>();	
		list = userMgrService.userMgrRoleGet(uid);
		System.out.println("list="+list.size());
		return list;
	}
	@RequestMapping("/userMgrRoleSet.action")
	@ResponseBody
	public String userMgrRoleSet(HttpServletRequest request, HttpServletResponse response,int uid,String arr){
		String str = "no";
		ArrayList<String> list = new ArrayList<String>();	
		if(!arr.equals("")) {
			list = (ArrayList<String>) JSON.parseArray(arr,String.class);
		}
		UserInfo user = (UserInfo) request.getSession().getAttribute("login");
		int a = userMgrService.userMgrRoleSet(user.getUid(),uid, list);
		if(a>0) {
			str = "yes";
		}
		return str;
	}	
}
