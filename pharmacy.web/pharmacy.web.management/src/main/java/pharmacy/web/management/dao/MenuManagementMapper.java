package pharmacy.web.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pharmacy.common.model.Limit;
import pharmacy.web.management.model.dto.PortAndMenuAndLimitDto;

public interface MenuManagementMapper {

	//初始化菜单
	List<PortAndMenuAndLimitDto> initMenu();
	//判断该权限是否有子权限
	List<Limit> judgeSubmenu(@Param("mid") String mid);
	//删除菜单
	int deleteMenu(@Param("mid") String mid);
	//删除权限
	int deleteLimit(@Param("lid") String lid);
	//创建菜单
	int addMenu(@Param("title") String title,@Param("icon") String icon,@Param("port") String port);
	//创建子菜单
	int addLimit(@Param("title") String title,@Param("url") String url,@Param("primaryMenuId") String primaryMenuId);
	//修改菜单
	int modifyMenu(@Param("id") String id,@Param("title") String title,@Param("icon") String icon);
	//修改权限
	int modifyLimit(@Param("title") String title,@Param("url") String url,@Param("lid") String lid);
}
