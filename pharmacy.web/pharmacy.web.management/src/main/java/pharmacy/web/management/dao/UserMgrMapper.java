package pharmacy.web.management.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import pharmacy.common.model.Role;
import pharmacy.common.model.UserInfo;

public interface UserMgrMapper {
	//用户表
	ArrayList<UserInfo> userMgrTable(@Param("uid")int uid,@Param("stime")String stime,@Param("etime")String etime,@Param("uname")String uname,RowBounds rn);
	//用户表计数
	int userMgrTableCount(@Param("uid")int uid,@Param("stime")String stime,@Param("etime")String etime,@Param("uname")String uname);
	//用户禁用
	int prohibit(@Param("uid")int uid);
	//用户启用
	int enable(@Param("uid")int uid);
	//重置密码
	int resetPassword(@Param("uid")int uid,@Param("pwd") String pwd);
	//添加用户
	int userMgrAdd(@Param("uname")String uname,@Param("uacc")String uacc,@Param("upwd")String upwd);
	//账号查重
	int userAccChecking(@Param("uacc")String uacc);
	//获取角色
	ArrayList<Role> userMgrRole();
	//添加账号角色关系
	int userMgrAddRole(@Param("rid")String rid,@Param("uid")String uid);
	//查询账号
	String userMgrUid(@Param("acc")String acc);
	//获取用户角色
	ArrayList<String> userMgrRoleGet(@Param("uid")int uid);
	//清空角色原有权限
	int userMgrDelRole(@Param("uid")int uid);
	//插入日志
	int initLog(@Param("aid")int aid,@Param("str")String str);
}
