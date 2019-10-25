package pharmacy.web.management.service.impl;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.common.model.Role;
import pharmacy.common.model.UserInfo;
import pharmacy.web.management.dao.UserMgrMapper;
import pharmacy.web.management.service.UserMgrService;

@Service
public class UserMgrServiceImpl implements UserMgrService{

	@Autowired
	private UserMgrMapper userMgrMapper;

	@Transactional(rollbackFor = Exception.class)
	public ArrayList<UserInfo> userTable(int uid,String stime, String etime, String uname, RowBounds rn) {
		ArrayList<UserInfo> list = userMgrMapper.userMgrTable(uid,stime, etime, uname, rn);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
		return list;
	}

	@Transactional(rollbackFor = Exception.class)
	public int count(int uid,String stime, String etime, String uname) {
		int a = 0;
		a = userMgrMapper.userMgrTableCount(uid,stime, etime, uname);
		return a;
	}

	@Transactional(rollbackFor = Exception.class)
	public int prohibit(int uid, int aid) {
		int a  = -1;
		a  = userMgrMapper.prohibit(uid);
		return a;
	}

	@Transactional(rollbackFor = Exception.class)
	public int enable(int uid, int aid) {
		int a  = -1;
		a  = userMgrMapper.enable(uid);
		return a;
	}

	@Transactional(rollbackFor = Exception.class)
	public int resetPassword(int uid, int aid,String pwd) {
		int a  = -1;
		a  = userMgrMapper.resetPassword(uid, pwd);
		return a;
	}

	@Transactional(rollbackFor = Exception.class)
	public String userMgrAdd(String uname, String uacc, String upwd, int aid,ArrayList<String> arr) {
		String str = "yes";
		int a = 0;
		a = userMgrMapper.userAccChecking(uacc);
		if(a>0) {
			System.out.println("账号存在");
			return "cz";
		}
		a = userMgrMapper.userMgrAdd(uname, uacc, upwd);
		if(a<0) {
			System.out.println("插入失败a="+a);
			str = "no";
			return str;
		}
		String uid = userMgrMapper.userMgrUid(uacc);
		for (int i = 0; i < arr.size(); i++) {
			a = userMgrMapper.userMgrAddRole(arr.get(i), uid);
			if(a<0) {
				System.out.println("插入角色权限关系失败a="+a);
				str = "no";
				return str;
			}
		}
		return str;
	}
 
	@Transactional(rollbackFor = Exception.class)
	public ArrayList<Role> userMgrRole() {
		ArrayList<Role> list = new ArrayList<Role>();
		list = userMgrMapper.userMgrRole();
		return list;
	}

	@Transactional(rollbackFor = Exception.class)
	public ArrayList<String> userMgrRoleGet(int uid) {
		System.out.println("uid="+uid);
		ArrayList<String> list = new ArrayList<String>();
		list = userMgrMapper.userMgrRoleGet(uid);
		return list;
	}

	@Override
	public int userMgrRoleSet(int aid,int uid, ArrayList<String> list) {
		int a = 0;
		//清空角色原有权限
		userMgrMapper.userMgrDelRole(uid);
		//添加新角色
		for (int i = 0; i < list.size(); i++) {
			a = userMgrMapper.userMgrAddRole(list.get(i), uid+"");
			if(a<1) {
				System.out.println("插入角色权限关系失败a="+a);
				return a;
			}
		}
		return a;
	}
	
	
	
}
