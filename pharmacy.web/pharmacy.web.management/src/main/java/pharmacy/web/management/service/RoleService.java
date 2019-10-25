package pharmacy.web.management.service;

import java.util.ArrayList;
import java.util.List;

import pharmacy.common.model.Role;
import pharmacy.web.management.model.dto.MenuDto;

public interface RoleService {

	//初始化角色信息
	List<Role> initRole(int start ,int end,String rolename);
	//查询总数据
	int RoleCount(String rolename);
	//删除角色
	int RoleDel(String id);
	
	//初始化权限
	List<MenuDto> RoleLimit();
	//查询对应角色的权限
	List<MenuDto> RolePossess(String id);
	
	//添加角色权限信息
	int addRole(String rolename,ArrayList<String> fatherArr,ArrayList<String> chileArr);
	
	//修改权限
	int updateRole(String rid,ArrayList<String> fatherArr,ArrayList<String> chileArr);
}
