package com.wish.service;

import com.wish.dao.RoleUserDao;
import com.wish.dao.UserDao;
import com.wish.entity.RoleUser;
import com.wish.entity.User;
import com.wish.util.MD5Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import javax.transaction.Transactional;

/**
 * @author handx 908716835@qq.com
 * @date 2017年4月25日 下午1:55:54
 */

@Service
public class UserService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserDao userDao;
	@Autowired
	RoleUserDao roleUserDao;

	@Transactional
	public void addUser(String name, String account, Long roleId, String phone) {
		User user = new User();
		user.setAccount(account);
		user.setCreateTime(new Date());
		user.setName(name);
		user.setPassword(MD5Util.md5("123456"));
		user.setStatus(0);
		user.setPhone(phone);
		userDao.save(user);
		
		RoleUser roleUser = new RoleUser();
		roleUser.setCreateTime(new Date());
		roleUser.setRoleId(roleId);
		roleUser.setUserId(user.getId());
		roleUserDao.save(roleUser);
	}

	@Transactional
	public void updateUser(Long id, String name, Long roleId) {
		userDao.updateUser(id, name);
		roleUserDao.updateGroupUser(roleId, id);
	}

}
