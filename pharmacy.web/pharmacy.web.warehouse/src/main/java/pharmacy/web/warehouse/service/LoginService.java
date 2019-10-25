package pharmacy.web.warehouse.service;

import pharmacy.web.warehouse.model.dto.ResultUserInfoAndMenu;

public interface LoginService {

	//登录
	ResultUserInfoAndMenu login (String username,String pwd);
}
