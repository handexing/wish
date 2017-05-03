package com.wish.controller;

import com.wish.dao.RoleDao;
import com.wish.entity.Menu;
import com.wish.entity.Role;
import com.wish.model.ExecuteResult;
import com.wish.model.RetJson;
import com.wish.service.MenuService;
import com.wish.service.RoleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author handx 908716835@qq.com
 * @date 2017年4月24日 上午9:58:22
 */

@RestController
@RequestMapping("role")
public class RoleController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	RoleDao roleDao;
	@Autowired
	RoleService roleService;

	@Autowired
	MenuService menuService;

	@RequestMapping(value = "getRoleList")
	public RetJson getRoleList() {
		RetJson retJson = new RetJson();
		retJson.setData(roleDao.findAll());
		return retJson;
	}

	@RequestMapping(value = "getAllMenu")
	public List<Menu> menuList(Long roleId) {
		return roleService.menuList(roleId);
	}

	@RequestMapping(value = "roleMenuList")
	public List<Menu> roleMenuList(Long roleId) {
		return menuService.findMenuByRoleId(roleId);
	}

	@RequestMapping("saveRole")
	public ExecuteResult<Boolean> saveRole(@RequestBody Role role) {
		ExecuteResult<Boolean> result = new ExecuteResult<Boolean>();
		try {
			roleService.saveRole(role);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("", e);
		}
		return result;
	}

	@RequestMapping("saveRoleMenu")
	public ExecuteResult<Boolean> saveRoleMenu(@RequestParam("ids[]") List<String> ids, Long groupId) {
		ExecuteResult<Boolean> result = new ExecuteResult<Boolean>();
		try {
			roleService.saveRoleMenu(ids, groupId);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("", e);
		}
		return result;
	}


	@RequestMapping("rolePage")
	public ModelAndView showGroupPage() {
		return new ModelAndView("/system/roleList");
	}

}
