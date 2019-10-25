package pharmacy.web.management.model.dto;

import java.io.Serializable;
import java.util.List;

import pharmacy.common.model.UserInfo;

public class ResultUserInfoAndMenu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<MenuDto> menuDto;
	private UserInfo userInfo;
	public ResultUserInfoAndMenu(List<MenuDto> menuDto, UserInfo userInfo) {
		super();
		this.menuDto = menuDto;
		this.userInfo = userInfo;
	}
	public ResultUserInfoAndMenu() {
		super();
	}
	public List<MenuDto> getMenuDto() {
		return menuDto;
	}
	public void setMenuDto(List<MenuDto> menuDto) {
		this.menuDto = menuDto;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
}
