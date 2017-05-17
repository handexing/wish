package com.wish.service;

import com.wish.dao.LeaveBillDao;
import com.wish.entity.LeaveBill;
import com.wish.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author handx 908716835@qq.com
 * @date 2017年5月17日 下午10:36:46
 *
 */

@Service
public class LeaveBillService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	LeaveBillDao LeaveBillDao;

	@Transactional
	public void saveLeaveBill(LeaveBill leaveBill, User user) {
		leaveBill.setState(0);
		leaveBill.setUser(user);
		LeaveBillDao.save(leaveBill);
	}


}
