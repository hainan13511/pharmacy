package pharmacy.web.management.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import pharmacy.common.model.Role;
import pharmacy.common.utils.StringUtil;
import pharmacy.web.management.model.dto.MenuDto;
import pharmacy.web.management.service.RoleService;

@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;

	// 初始化
	@RequestMapping("/initRole.action")
	@ResponseBody
	public List<Role> roleList(int start, int end, String rolename) {
		// 校验
		if (StringUtil.isAllEmpty(start + "", end + "")) {
			return null;
		}
		// 数据库操作
		System.out.println("搜索：" + rolename);
		List<Role> initRole = roleService.initRole(start, end, rolename);
		return initRole;
	}

	/**
	 * 查询总数据
	 * 
	 * @param rolename
	 * @return
	 */
	@RequestMapping("/Rolecount.action")
	@ResponseBody
	public int Rolecount(String rolename) {
		// 计算页码
		int roleCount = roleService.RoleCount(rolename);
		System.out.println("总数据：" + roleCount);
		return roleCount;
	}

	/**
	 * 删除角色
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/RoleDel.action")
	@ResponseBody
	public int RoleDel(String id) {
		if (StringUtil.isAllEmpty(id)) {
			return 0;
		}
		System.out.println("删除：" + id);
		int roleDel = roleService.RoleDel(id);
		return roleDel;
	}

	/**
	 * 初始化所有权限
	 * 
	 * @param id
	 */
	@RequestMapping("/roleRedact.action")
	@ResponseBody
	public List<MenuDto> RoleRedact() {
		// 数据库操作
		List<MenuDto> roleLimit = roleService.RoleLimit();
		return roleLimit;
	}

	/**
	 * 对应角色的权限
	 * 
	 * @param id
	 */
	@RequestMapping("/initRolePower.ajax")
	@ResponseBody
	public List<MenuDto> initRolePower(String id) {
		List<MenuDto> rolePossess = roleService.RolePossess(id);
		return rolePossess;
	}

	/**
	 * 添加角色并添加权限
	 * 
	 * @param rolename
	 * @param sonArr
	 * @param parentArr
	 * @return
	 */
	@RequestMapping("/addRole.action")
	@ResponseBody
	public int addRole(String rolename, String sonArr, String parentArr) {
		// 校验
		if (StringUtil.isAllEmpty(rolename, sonArr, parentArr)) {
			return 0;
		}
		System.out.println("son:" + sonArr + ",parent:" + parentArr);
		ArrayList<String> fatherArr = (ArrayList<String>) JSON.parseArray(parentArr, String.class);
		ArrayList<String> childArr = (ArrayList<String>) JSON.parseArray(sonArr, String.class);
		System.out.println("角色名：" + rolename + ",父级：" + fatherArr.toString() + ",子级：" + childArr.toString());
		// 数据库操作
		int addRole = roleService.addRole(rolename, fatherArr, childArr);
		System.out.println("controller：" + addRole);
		return addRole;
	}

	/**
	 * 修改权限
	 * 
	 * @param rid
	 * @param sonArr
	 * @param parentArr
	 * @return
	 */
	@RequestMapping("/updateRole.action")
	@ResponseBody
	public int updateRole(String rid, String sonArr, String parentArr) {
		// 校验
		if (StringUtil.isAllEmpty(rid, sonArr, parentArr)) {
			return 0;
		}
		System.out.println("son:" + sonArr + ",parent:" + parentArr);
		ArrayList<String> fatherArr = (ArrayList<String>) JSON.parseArray(parentArr, String.class);
		ArrayList<String> childArr = (ArrayList<String>) JSON.parseArray(sonArr, String.class);
		System.out.print("角色名：" + rid + ",父级：" + fatherArr.toString() + ",子级：" + childArr.toString());

		int updateRole = roleService.updateRole(rid, fatherArr, childArr);
		return updateRole;
	}
}
