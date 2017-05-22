package com.wish.service;

import org.activiti.engine.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author handx 908716835@qq.com
 * @date 2017年5月22日 下午4:02:03
 */

@Service
public class ProcessService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	TaskService taskService;

}
