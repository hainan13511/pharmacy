package pharmacy.web.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pharmacy.web.management.model.dto.MenuDto;

public interface MenuMapper {

	//菜单查询
	List<MenuDto> list(@Param("loginid") String loginid);
}
