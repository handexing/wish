package com.wish.dao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName: RedisTest
 * @Description: 测试redis
 * @author handx 908716835@qq.com
 * @date 2017年5月19日 下午1:25:48
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:redis.xml")
@ActiveProfiles("test")
public class RedisTest {

	@Autowired
	RedisTemplate redisTemplate;
	// @Autowired
	// LeaveBillDao LeaveBillDao;

	@Test
	public void testRedis() {
		// 单值操作的对象，通过该对象直接可以对redis进行CRUD(单值操作)
		ValueOperations<String, String> valueOper = redisTemplate.opsForValue();
		String value = valueOper.get("han");
		System.out.println(value);

		valueOper.set("lijh", "kkkkkkk....");

		// ValueOperations<String, Object> opsForValue =
		// redisTemplate.opsForValue();
		// List<LeaveBill> list = LeaveBillDao.findLeaveBillByState(0);
//
		// opsForValue.set("leaveBillList", list);

	}

}
