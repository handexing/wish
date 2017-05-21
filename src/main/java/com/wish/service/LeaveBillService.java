package com.wish.service;

import com.wish.dao.LeaveBillDao;
import com.wish.entity.LeaveBill;
import com.wish.entity.User;

import org.activiti.engine.RuntimeService;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	LeaveBillDao leaveBillDao;
	@Autowired
	RuntimeService runtimeService;

	@Autowired
	ScheduleJobService scheduleJobService;
	// @Autowired
	// RedisTemplate redisTemplate;

	@Transactional
	public void saveLeaveBill(LeaveBill leaveBill, User user) throws SchedulerException {
		leaveBill.setState(0);
		leaveBill.setUser(user);
		leaveBill.setCreateDate(new Date());
		leaveBillDao.save(leaveBill);
	}

	@Transactional
	public void saveStartProcess(LeaveBill leaveBill) {
		
		// 1：修改状态为审批中
		leaveBill.setState(1);
		leaveBillDao.save(leaveBill);
		// 2：使用当前对象获取到流程定义的key（对象的名称就是流程定义的key）
		String key = leaveBill.getClass().getSimpleName();

		/**
		 * 3：获取当前任务的办理人，使用流程变量设置下一个任务的办理人 inputUser是流程变量的名称， 获取的办理人是流程变量的值
		 */
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("inputUser", leaveBill.getUser().getAccount());// 表示惟一用户

		/**
		 * 4： (1)使用流程变量设置字符串（格式：Leavebill.id的形式），通过设置，让启动的流程（流程实例）关联业务
		 * (2)使用正在执行对象表中的一个字段BUSINESS_KEY（Activiti提供的一个字段），让启动的流程（流程实例）关联业务
		 */
		// 格式：Leavebill.id的形式（使用流程变量）
		String objId = key + "." + leaveBill.getId();
		variables.put("objId", objId);

		// 6：使用流程定义的key，启动流程实例，同时设置流程变量，同时向正在执行的执行对象表中的字段BUSINESS_KEY添加业务数据，同时让流程关联业务
		runtimeService.startProcessInstanceByKey(key, objId, variables);

	}

	/**
	 * @Title: startProcess
	 * @Description: 定时启动请假任务
	 */
	public void startProcess() {

		logger.debug("检查请假任务是否可以开启...");

		List<LeaveBill> list = leaveBillDao.findLeaveBillByState(0);
		if (list != null && list.size() > 0) {
			for (LeaveBill leaveBill : list) {
				logger.debug("开启流程：" + leaveBill.getUser().getName());
				saveStartProcess(leaveBill);
			}
		}

	}

}
