package com.wish.service;

import com.wish.dao.MenuDao;
import com.wish.dao.RoleMenuDao;
import com.wish.entity.Menu;
import com.wish.entity.RoleMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

/**
 * @author handx 908716835@qq.com
 * @date 2017年4月20日 下午1:40:19
 */

@Service
public class MenuService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MenuDao menuDao;
	@Autowired
	RoleMenuDao roleMenuDao;

	@Transactional
	public void delMenu(Long id) {
		menuDao.delete(id);
		RoleMenu roleMenu = roleMenuDao.findRoleMenuByMenuId(id);
		if (roleMenu != null) {
			roleMenuDao.deleteRoleMenu(id);
		}
	}

	public List<Menu> findMenuByRoleId(Long roleId) {
		List<Menu> list = menuDao.findMenuByRoleId(roleId);
		List<Menu> menus = getMenu(list);
		return menus;
	}

	private List<Menu> getMenu(List<Menu> list) {
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
		return menus;
	}

	public List<Menu> getMenu(Long userId) {

		List<Menu> list = menuDao.findMenuByUserId(userId);
		List<Menu> menus = new ArrayList<Menu>();
		List<Menu> childs = null;
		
		for (Menu parent :list) {
			if(0 == parent.getPid()){
				childs = new ArrayList<Menu>();
				for (Menu child : list) {
					if (child.getPid() == parent.getId()) {
						childs.add(child);
					}
				}
				parent.setChildren(childs);
				menus.add(parent);
			}
		}
		return menus;
	}

	public List<Menu> menuList() {
		List<Menu> list = menuDao.findAll();
		List<Menu> menus = getMenu(list);
		return menus;
	}

	@Transactional
	public void saveMenu(Menu menu) {
		if (menu.getId() != null) {
			Menu m = menuDao.findOne(menu.getId());
			menu.setStatus(m.getStatus());
			menu.setIcon(m.getIcon());
			menu.setPid(m.getPid());
			menu.setCreateTime(m.getCreateTime());
		} else {
			menu.setStatus(0);
			menu.setCreateTime(new Date());
		}
		menuDao.save(menu);
	}
}
