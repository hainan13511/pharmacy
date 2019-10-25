package pharmacy.web.warehouse.dao;

import org.apache.ibatis.annotations.Param;

import pharmacy.common.model.UserInfo;

public interface LoginMapper {

	//登录
	UserInfo login (@Param("username")String username,@Param("password")String password);
}
