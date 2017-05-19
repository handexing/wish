package com.wish.quartz;

import com.wish.dao.LeaveBillDao;
import com.wish.entity.LeaveBill;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: RedisScheduler
 * @Description:定时将数据库数据同步到redis中，繁殖定时操作频繁操作数据库
 * @author handx 908716835@qq.com
 * @date 2017年5月19日 上午10:44:19
 */

@Service
public class RedisScheduler {

	@Autowired
	RedisTemplate redisTemplate;
	@Autowired
	LeaveBillDao LeaveBillDao;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public void initRedis() {

		logger.debug("run=========================RedisScheduler");

		ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
		List<LeaveBill> list = LeaveBillDao.findLeaveBillByState(0);

		opsForValue.set("leaveBillList", list);

	}

}
