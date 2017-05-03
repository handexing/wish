package com.wish.service;

import com.wish.dao.MenuDao;
import com.wish.dao.RoleDao;
import com.wish.dao.RoleMenuDao;
import com.wish.entity.Menu;
import com.wish.entity.Role;
import com.wish.entity.RoleMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

/**
 * @author handx 908716835@qq.com
 * @date 2017年4月24日 上午10:32:11
 */

@Service
public class RoleService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	RoleDao cmsGroupDao;
	@Autowired
	MenuDao menuDao;
	@Autowired
	MenuService menuService;
	@Autowired
	RoleMenuDao cmsGroupMenuDao;

	private List<Menu> getMenu(List<Menu> list, Long roleId) {
		List<Menu> menus = new ArrayList<Menu>();
		List<Menu> childs = null;
		for (Menu parent : list) {
			if (0 == parent.getPid()) {
				parent.setText(parent.getTitle());
				parent.setIcon("<i class=\"layui-icon\">" + parent.getIcon() + "</i>");
				childs = new ArrayList<Menu>();
				for (Menu child : list) {
					if (child.getPid() == parent.getId()) {
						child.setText(child.getTitle());
						child.setIcon("<i class=\"layui-icon\">" + child.getIcon() + "</i>");
						childs.add(child);
					}
				}
				parent.setChildren(childs);
				menus.add(parent);
			}
		}

		List<Menu> userMenus = menuDao.findMenuByRoleId(roleId);// 已经有的菜单
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();

		for (Menu menu : menus) {
			List<Menu> children = menu.getChildren();
			if (children != null && children.size() > 0) {
				for (Menu child : children) {
					for (Menu m : userMenus) {
						if (child.getId().equals(m.getId())) {
							map.put("selected", true);
							child.setState(map);
						}
					}
				}
			} else {
				for (Menu m : userMenus) {
					if (menu.getId().equals(m.getId())) {
						map.put("selected", true);
						menu.setState(map);
					}
				}
			}
		}
		return menus;
	}

	public List<Menu> menuList(Long roleId) {
		List<Menu> list = menuDao.findAll();
		List<Menu> menus = getMenu(list, roleId);
		return menus;
	}

	@Transactional
	public void saveRole(Role role) {
		if (role.getId() != null) {

		} else {
			role.setStatus(0);
			role.setCreateTime(new Date());
		}
		cmsGroupDao.save(role);
	}

	@Transactional
	public void saveRoleMenu(List<String> ids, Long roleId) {

		cmsGroupMenuDao.deleteRoleMenuByRoleId(roleId);
		List<Long> menuId = new ArrayList<Long>();

		for (String id : ids) {
			Menu menu = menuDao.findOne(Long.parseLong(id));
			if (0 != menu.getPid()) {
				menuId.add(menu.getPid());
			}
			RoleMenu groupMenu = new RoleMenu();
			groupMenu.setCreateTime(new Date());
			groupMenu.setRoleId(roleId);
			groupMenu.setMenuId(Long.parseLong(id));
			cmsGroupMenuDao.save(groupMenu);
		}

		for (Long id : menuId) {
			RoleMenu menu = cmsGroupMenuDao.findByRoleIdAndMenuId(roleId, id);
			if (menu == null) {
				RoleMenu groupMenu = new RoleMenu();
				groupMenu.setCreateTime(new Date());
				groupMenu.setRoleId(roleId);
				groupMenu.setMenuId(id);
				cmsGroupMenuDao.save(groupMenu);
			}

		}
	}
}
