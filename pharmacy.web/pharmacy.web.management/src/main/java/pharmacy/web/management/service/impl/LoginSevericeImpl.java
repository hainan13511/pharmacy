package pharmacy.web.management.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.common.model.UserInfo;
import pharmacy.web.management.dao.LoginMapper;
import pharmacy.web.management.dao.MenuMapper;
import pharmacy.web.management.model.dto.MenuDto;
import pharmacy.web.management.model.dto.ResultUserInfoAndMenu;
import pharmacy.web.management.service.LoginService;

@Service
public class LoginSevericeImpl implements LoginService {

	@Autowired
	private LoginMapper loginMapper;
	@Autowired
	private MenuMapper menuMapper;
	//登录
	@Transactional(rollbackFor = Exception.class)
	public ResultUserInfoAndMenu  login(String username, String pwd) {
		System.out.println(username+","+pwd);
		UserInfo login = loginMapper.login(username, pwd);
		//查询对应的菜单
		List<MenuDto> list=null;
		if(login!=null) {
		list = menuMapper.list(login.getUid()+"");
		}
		
		return new ResultUserInfoAndMenu(list, login);
	}
}
