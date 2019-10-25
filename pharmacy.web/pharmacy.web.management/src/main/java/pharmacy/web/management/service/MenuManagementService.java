package pharmacy.web.management.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pharmacy.web.management.model.dto.PortAndMenuAndLimitDto;

public interface MenuManagementService {
	//初始化菜单
	List<PortAndMenuAndLimitDto> initMenuService();
	
	//删除菜单
	boolean deleteMenu(String mid);
	
	//添加菜单
	boolean addMenu(String title,String icon,String port);
	
	//删除子菜单
	boolean deleteLimit(String lid);
	
	//添加子菜单
	boolean addLimit(String title, String url,String primaryMenuId);
	
	//修改菜单
	boolean modifyMenu(String title,String id,String icon);
	
	//修改权限
	boolean modifyLimit( String title, String url,String lid);
}
