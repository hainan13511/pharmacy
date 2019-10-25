package pharmacy.web.management.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;

import pharmacy.common.model.Role;
import pharmacy.common.model.UserInfo;

public interface UserMgrService {
	
	//用户表
	ArrayList<UserInfo> userTable(int uid,String stime,String etime,String uname,RowBounds rn);
	//计数
	int count(int uid,String stime,String etime,String uname);
	//用户禁用
	int prohibit(int uid,int aid);
	//用户启用
	int enable(int uid,int aid);
	//用户重置密码
	int resetPassword(int uid,int aid,String pwd);
	//添加用户
	String userMgrAdd(String uname,String uacc,String upwd,int aid,ArrayList<String> arr);
	//获取角色
	ArrayList<Role> userMgrRole();
	//获取用户角色
	ArrayList<String> userMgrRoleGet(int uid);
	//修改用户角色
	int userMgrRoleSet(int aid,int uid,ArrayList<String> list);
	
}
