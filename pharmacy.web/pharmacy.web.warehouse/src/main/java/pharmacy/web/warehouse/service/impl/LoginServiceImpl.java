package pharmacy.web.warehouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.common.model.UserInfo;

import pharmacy.web.warehouse.dao.LoginMapper;
import pharmacy.web.warehouse.dao.MenuMapper;
import pharmacy.web.warehouse.model.dto.MenuDto;
import pharmacy.web.warehouse.model.dto.ResultUserInfoAndMenu;
import pharmacy.web.warehouse.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

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
