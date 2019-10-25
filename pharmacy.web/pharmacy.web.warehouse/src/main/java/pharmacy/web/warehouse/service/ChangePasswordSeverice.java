package pharmacy.web.warehouse.service;

public interface ChangePasswordSeverice {

	//修改密码
	String changePwd(String oldpwd,String newpwd,String id);
}
