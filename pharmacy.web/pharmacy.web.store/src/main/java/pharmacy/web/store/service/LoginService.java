package pharmacy.web.store.service;


import pharmacy.web.store.model.dto.ResultUserInfoAndMenu;

public interface LoginService {

	//登录
	ResultUserInfoAndMenu login (String username,String pwd);
}
