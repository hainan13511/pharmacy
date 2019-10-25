package pharmacy.web.management.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.common.model.Role;
import pharmacy.common.utils.StringUtil;
import pharmacy.web.management.dao.RoleMapper;
import pharmacy.web.management.model.dto.MenuDto;
import pharmacy.web.management.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	//初始化角色
	@Autowired
	private RoleMapper roleMapper;
	
	@Transactional(rollbackFor  = Exception.class)
	public List<Role> initRole(int start ,int end,String rolename) {
		RowBounds rb=new RowBounds(start, end);
		List<Role> initRole = roleMapper.initRole(rb,rolename);
		return initRole;
	}
	
	
	//删除角色
	@Transactional(rollbackFor  = Exception.class)
	public int RoleDel(String id) {
		//校验
		if(StringUtil.isAllEmpty(id)) {
			return 0;
		}
		System.out.println("角色删除的id："+id);
		int roleDel = roleMapper.RoleDel(id);
		//删除对应对应角色的权限
		int delLimit = roleMapper.DelLimit(id);
		if(roleDel!=0&&delLimit!=0) {
			return roleDel;
		}
		return 0;
	}


	//查询总数据
	@Transactional(rollbackFor = Exception.class)
	public int RoleCount(String rolename) {
		int count = roleMapper.count(rolename);
		System.out.println("日志的总数据："+count);
		int allpage = count % 5 == 0 ? (count / 5) : (count / 5 + 1);
		return allpage;
	}


	//所有的权限
	@Transactional(rollbackFor = Exception.class)
	public List<MenuDto> RoleLimit() {
		List<MenuDto> roleLimit = roleMapper.RoleLimit();
		return roleLimit;
	}


	//查询对应角色的权限
	@Transactional(rollbackFor = Exception.class)
	public List<MenuDto> RolePossess(String id) {
		List<MenuDto> rolePossess = roleMapper.RolePossess(id);
		return rolePossess;
	}

	//添加角色权限信息
	@Transactional(rollbackFor = Exception.class)
	public int addRole(String rolename, ArrayList<String> fatherArr, ArrayList<String> chileArr) {
		//查询角色
		List<Role> roleList = roleMapper.RoleList();
		for (int i = 0; i < roleList.size(); i++) {
			if(roleList.get(i).getRolename().equals(rolename)) {
				System.out.println("角色名重复");
				return 2;
			}
		}
		//添加角色表信息
		Role role=new Role(null, rolename);
		int addRole = roleMapper.addRole(role);
		System.out.println("返回的结果："+addRole+",id:"+role.getRoleid());
		int addLimit=0;
		for (int i = 0; i < fatherArr.size(); i++) {//添加父级的权限
			System.out.println("子级的权限:"+fatherArr.get(i));
			addLimit = roleMapper.addLimit(role.getRoleid(), fatherArr.get(i));
		}
		for (int i = 0; i < chileArr.size(); i++) {
			System.out.println("子级的权限:"+chileArr.get(i));
			addLimit = roleMapper.addLimit(role.getRoleid(), chileArr.get(i));
		}
		if(addRole!=0&&addLimit!=0) {
			return 1;
		}
		return 0;
	}
	//修改权限
	@Transactional(rollbackFor = Exception.class)
	public int updateRole(String rid, ArrayList<String> fatherArr, ArrayList<String> chileArr) {
		int delLimit = roleMapper.DelLimit(rid);
		if(delLimit==0) {
			return 0;
		}
		System.out.println("删除的结果："+delLimit);
		int addLimit=0;
//		for (int i = 0; i < fatherArr.size(); i++) {//添加父级的权限
//			System.out.println("子级的权限:"+fatherArr.get(i));
//			addLimit = roleMapper.addLimit(rid, fatherArr.get(i));
//		}
		for (int i = 0; i < chileArr.size(); i++) {//添加子级的权限
			System.out.println("子级的权限:"+chileArr.get(i));
			addLimit = roleMapper.addLimit(rid ,chileArr.get(i));
		}
		if(delLimit!=0&&addLimit!=0) {
			return 1;
		}
		return 0;
	}

}
