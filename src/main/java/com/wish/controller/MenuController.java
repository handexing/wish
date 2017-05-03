package com.wish.controller;

import com.wish.entity.Menu;
import com.wish.entity.User;
import com.wish.model.ExecuteResult;
import com.wish.service.MenuService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * @author handx 908716835@qq.com
 * @date 2017年4月20日 下午2:14:26
 */

@RestController
@RequestMapping("menu")
public class MenuController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MenuService menuService;

	@RequestMapping("delMenu")
	public ExecuteResult<Boolean> delMenu(Long id) {
		ExecuteResult<Boolean> result = new ExecuteResult<Boolean>();
		try {
			menuService.delMenu(id);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("", e);
		}
		return result;
	}

	@RequestMapping(value = "getMenu")
	public List<Menu> getMenu(HttpSession session) {
		User user = (User) session.getAttribute("user");
		return menuService.getMenu(user.getId());
	}

	@RequestMapping(value = "menuList")
	public List<Menu> menuList() {
		return menuService.menuList();
	}

	@RequestMapping("saveMenu")
	public ExecuteResult<Boolean> saveMenu(@RequestBody Menu menu) {
		ExecuteResult<Boolean> result = new ExecuteResult<Boolean>();
		try {
			menuService.saveMenu(menu);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("", e);
		}
		return result;
	}

	@RequestMapping("menuPage")
	public ModelAndView showMenuPage() {
		return new ModelAndView("/system/menuList");
	}

}
