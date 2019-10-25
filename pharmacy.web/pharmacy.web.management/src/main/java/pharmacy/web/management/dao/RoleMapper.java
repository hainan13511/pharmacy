package pharmacy.web.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import pharmacy.common.model.Role;
import pharmacy.web.management.model.dto.MenuDto;

public interface RoleMapper {
	
	//初始化查询角色
	List<Role> initRole(RowBounds rb, @Param("rolename") String rolename);
	//查询总数
	int count(@Param("rolename") String rolename);
	//删除角色
	int RoleDel(@Param("id") String id);
	//初始化所有权限
	List<MenuDto> RoleLimit();
	//对应角色的权限
	List<MenuDto> RolePossess(@Param("id") String id);
	//查询角色表信息
	List<Role> RoleList();
	//添加角色表信息
	int addRole(Role roleName);
	//添加角色权限表信息
	int addLimit(@Param("rid") String rid,@Param("lid") String lid);
	//删除对应角色所有的权限
	int DelLimit(@Param("rid") String rid);
}
