package com.wish.quartz;

import com.wish.dao.SkuInfoDao;
import com.wish.dao.SkuSrcDao;
import com.wish.entity.SkuInfo;
import com.wish.entity.SkuSrc;
import com.wish.util.Constants;
import com.wish.util.DateUtil;
import com.wish.util.JsoupUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: JdMain
 * @Description:定时将数据存到数据库中
 * @author handx 908716835@qq.com
 * @date 2017年7月25日 上午10:18:37
 */
@Service
public class JdMain {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SkuSrcDao skuSrcDao;
	@Autowired
	SkuInfoDao skuInfoDao;

	public void jdMain() {

		logger.info("=======================开始爬取数据=======================");

		List<SkuSrc> list = skuSrcDao.findSkuSrcByPlatfmCode(Constants.JD);

		long start = System.currentTimeMillis();
		ExecutorService p = Executors.newFixedThreadPool(Constants.MAX_THREAD_CNT);
		final List<Callable<Integer>> partitions = new ArrayList<Callable<Integer>>();

		try {
			for (final SkuSrc skuSrc : list) {
				partitions.add(new Callable<Integer>() {
					public Integer call() throws Exception {
						SkuInfo skuInfo = JsoupUtil.getGoods1(skuSrc);
						skuInfoDao.save(skuInfo);
						return 0;
					}
				});
			}
			p.invokeAll(partitions);
			p.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		logger.info("爬取耗时：" + (end - start) / 1000 / 60 + "分");
		logger.info("==============日期：" + DateUtil.getStartDate(new Date()) + "-抓取完毕=============");

	}
}
