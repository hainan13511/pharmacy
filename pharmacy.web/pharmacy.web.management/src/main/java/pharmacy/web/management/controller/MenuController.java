package pharmacy.web.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import pharmacy.common.utils.StringUtil;
import pharmacy.web.management.model.dto.PortAndMenuAndLimitDto;
import pharmacy.web.management.service.MenuManagementService;

@Controller
public class MenuController {

	@Autowired
	private MenuManagementService menu;
	
	/**
	 * 	初始化菜单
	 * @return 返回查询到的菜单数据
	 */
	
	@RequestMapping("/initMenu.action")
	@ResponseBody
	public List<PortAndMenuAndLimitDto> initMenu() {
		List<PortAndMenuAndLimitDto> initMenuService = menu.initMenuService();
		for (PortAndMenuAndLimitDto portAndMenuAndLimitDto : initMenuService) {
			System.out.println("菜单：--------------"+portAndMenuAndLimitDto.getPname());
//			portAndMenuAndLimitDto.getListDtos()
		}
		return initMenuService;
	}
	
	/**
	 *	删除菜单
	 * @param mid 菜单ID
	 * @return 返回JOSNobject
	 */
	@RequestMapping("/deleteMenu.action")
	@ResponseBody
	public JSONObject deleteMenu(String mid) {
		JSONObject data = new JSONObject();
		if(StringUtil.isNotEmpty(mid)){
			boolean flag = menu.deleteMenu(mid);
			if(flag) {
				data.put("result", "yes");
			}else {
				data.put("result", "no");
			}
		}
		return data;
	}
	
	/**
	 *	添加菜单
	 * @param title 菜单名
	 * @param icon 图标
	 * @param port 端
	 * @return 返回JOSNobject
	 */
	
	@RequestMapping("/addMenu.action")
	@ResponseBody
	public JSONObject addMenu(String title,String icon,String port) {
		JSONObject data = new JSONObject();
		if(StringUtil.isAllNotEmpty(title,icon,port)){
			boolean flag = menu.addMenu(title, icon, port);
			if(flag) {
				data.put("result", "yes");
			}else {
				data.put("result", "no");
			}
		}
		return data;
	}
	
	/**
	 * 	删除权限
	 * @param lid 权限ID
	 * @return 返回JSON
	 */
	@RequestMapping("/deleteLimit.action")
	@ResponseBody
	public JSONObject deleteLimit(String lid) {
		JSONObject data = new JSONObject();
		if(StringUtil.isNotEmpty(lid)){
			System.out.println(lid);
			boolean flag = menu.deleteLimit(lid);
			if(flag) {
				data.put("result", "yes");
			}else {
				data.put("result", "no");
			}
		}
		return data;
	}
	
	/**
	 * 	添加权限
	 * @param title 权限名
	 * @param url	路径
	 * @param primaryMenuId 菜单ID
	 * @return 返回JOSN
	 */
	@RequestMapping("/addLimit.action")
	@ResponseBody
	public JSONObject addLimit(String title, String url, String primaryMenuId) {
		JSONObject data = new JSONObject();
		if(StringUtil.isAllNotEmpty(title,url,primaryMenuId)){
			boolean flag = menu.addLimit(title, url, primaryMenuId);
			if(flag) {
				data.put("result", "yes");
			}else {
				data.put("result", "no");
			}
		}
		return data;
	}
	
	/**
	 * 	修改菜单
	 * @param title	菜单名
	 * @param id	菜单ID
	 * @param icon	菜单图标
	 * @return
	 */
	@RequestMapping("/modifyMenu.action")
	@ResponseBody
	public JSONObject modifyMenu(String title,String id,String icon) {
		JSONObject data = new JSONObject();
		if(StringUtil.isAllNotEmpty(title,id,icon)){
			boolean flag = menu.modifyMenu(title, id, icon);
			if(flag) {
				data.put("result", "yes");
			}else {
				data.put("result", "no");
			}
		}
		return data;
	}
	
	/**
	 * 	修改权限
	 * @param title 权限名
	 * @param url url
	 * @param lid 权限ID
	 * @return 返回Json
	 */
	@RequestMapping("/modifyLimit.action")
	@ResponseBody
	public JSONObject modifyLimit( String title, String url,String lid) {
		JSONObject data = new JSONObject();
		if(StringUtil.isAllNotEmpty(title,url,lid)){
			boolean flag = menu.modifyLimit(title, url, lid);
			if(flag) {
				data.put("result", "yes");
			}else {
				data.put("result", "no");
			}
		}
		return data;
	}
	
	
	
}
