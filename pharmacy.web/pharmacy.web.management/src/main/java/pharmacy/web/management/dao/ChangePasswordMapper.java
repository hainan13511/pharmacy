package pharmacy.web.management.dao;

import org.apache.ibatis.annotations.Param;

import pharmacy.common.model.UserInfo;

public interface ChangePasswordMapper {

	//查询源密码
	UserInfo user(@Param("id") String id);
	//修改密码
	int changepwd(@Param("newpwd") String newpwd,@Param("id") String id);
}
