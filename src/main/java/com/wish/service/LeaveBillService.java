package com.wish.service;

import com.wish.dao.LeaveBillDao;
import com.wish.entity.LeaveBill;
import com.wish.entity.User;

import org.activiti.engine.RuntimeService;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

/**
 * @author handx 908716835@qq.com
 * @date 2017年5月17日 下午10:36:46
 *
 */

@Service
public class LeaveBillService {

	public final static DateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	LeaveBillDao LeaveBillDao;
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	ScheduleJobService scheduleJobService;
	@Autowired
	RedisTemplate redisTemplate;

	@Transactional
	public void saveLeaveBill(LeaveBill leaveBill, User user) throws SchedulerException {
		leaveBill.setState(0);
		leaveBill.setUser(user);
		LeaveBillDao.save(leaveBill);
	}

	/**
	 * @Title: startProcess 
	 * @Description: 定时启动请假任务
	 */
	public void startProcess() {

		logger.debug("检查请假任务是否可以开启...");

		@SuppressWarnings("unchecked")
		ValueOperations<String, LeaveBill> valueOper = redisTemplate.opsForValue();
		List<LeaveBill> list = LeaveBillDao.findLeaveBillByState(0);

		String toDay = dfDate.format(new Date());
		for (LeaveBill leaveBill : list) {
			String leaveDate = dfDate.format(leaveBill.getLeaveDate());
			if (leaveDate.equals(toDay)) {
				logger.debug("可以开启。。。" + leaveBill.getUser().getName());
			}
		}
	}

}
