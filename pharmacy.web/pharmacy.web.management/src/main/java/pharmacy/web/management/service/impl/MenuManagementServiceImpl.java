package pharmacy.web.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.common.model.Limit;
import pharmacy.web.management.dao.MenuManagementMapper;
import pharmacy.web.management.model.dto.PortAndMenuAndLimitDto;
import pharmacy.web.management.service.MenuManagementService;
@Service
public class MenuManagementServiceImpl implements MenuManagementService {

	@Autowired
	private MenuManagementMapper menu;
	
	
	//初始化菜单
	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<PortAndMenuAndLimitDto> initMenuService() {
		List<PortAndMenuAndLimitDto> portList = null;
		List<PortAndMenuAndLimitDto> initMenu = menu.initMenu();
		portList = initMenu;
		return portList;
	}


	//删除菜单
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean deleteMenu(String mid) {
		//判断菜单是否有子菜单
		List<Limit> judgeSubmenu = menu.judgeSubmenu(mid);
		if(judgeSubmenu.isEmpty()) {
			int flag = menu.deleteMenu(mid);
			if(flag <= 0) {
				return false;
			}
		}else {
			for (int i = 0; i < judgeSubmenu.size(); i++) {
				String limitid = judgeSubmenu.get(i).getLimitid();
				int flag = menu.deleteLimit(limitid);
				if(flag<=0) {
					new Exception();
					return false;
				}
				
			}
			int flag = menu.deleteMenu(mid);
			if(flag <= 0) {
				new Exception();
				return false;
			}
		}
		return true;
	}

	//添加菜单
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean addMenu(String title, String icon, String port) {
		int flag = menu.addMenu(title, icon, port);
		if(flag>0) {
			return true;
		}
		return false;
	}


	//删除子菜单
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean deleteLimit(String lid) {
		int flag = menu.deleteLimit(lid);
		if(flag>0) {
			return true;
		}
		return false;
	}


	//添加子菜单
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean addLimit(String title, String url, String primaryMenuId) {
		int flag = menu.addLimit(title, url, primaryMenuId);
		if(flag>0) {
			return true;
		}
		return false;
	}
	
	//修改菜单
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean modifyMenu(String title, String id, String icon) {
		int flag = menu.modifyMenu(id, title, icon);
		if(flag>0) {
			return true;
		}
		return false;
	}

	//修改权限
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean modifyLimit(String title, String url, String lid) {
		int flag = menu.modifyLimit(title, url, lid);
		if(flag>0) {
			return true;
		}
		return false;
	}

}
