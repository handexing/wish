package com.wish.dao.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName: QuartzTest
 * @Description: 测试定时任务
 * @author handx 908716835@qq.com
 * @date 2017年5月4日 下午4:40:44
 *
 */

public class QuartzTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		context.getBean("scheduler");
	}
}
