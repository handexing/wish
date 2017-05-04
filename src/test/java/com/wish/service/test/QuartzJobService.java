package com.wish.service.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 任务调度
 * @author handx 908716835@qq.com
 * @date 2017年5月4日 下午3:55:35
 */

public class QuartzJobService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public void run() {
		logger.info("==========run===========");
	}
}
