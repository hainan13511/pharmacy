package pharmacy.web.management.service;


import pharmacy.web.management.model.dto.ResultUserInfoAndMenu;

public interface LoginService {

	//登录
	ResultUserInfoAndMenu login (String username,String pwd);
}
