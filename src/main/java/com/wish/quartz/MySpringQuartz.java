package com.wish.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Description: 测试
 * @author handx 908716835@qq.com
 * @date 2017年5月8日 下午2:04:22
 *
 */
@Service
public class MySpringQuartz {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public void run() {
		logger.debug("run=========================MySpringQuartz");
	}
}
