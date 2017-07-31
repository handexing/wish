package com.wish.quartz;

import com.wish.dao.SkuInfoDao;
import com.wish.dao.SkuSrcDao;
import com.wish.entity.SkuInfo;
import com.wish.entity.SkuSrc;
import com.wish.util.Constants;
import com.wish.util.DateUtil;
import com.wish.util.JsoupUtil;
import com.wish.util.PropertiesUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName: JdMain
 * @Description:定时将数据存到数据库中
 * @author handx 908716835@qq.com
 * @date 2017年7月25日 上午10:18:37
 */
@Service
public class JdMain {

	/**
	 * @Title: getWebDriver
	 * @Description: 获取WebDriver实例
	 * @return WebDriver
	 */
	public static WebDriver getWebDriver() {

		DesiredCapabilities dCaps = new DesiredCapabilities();
		dCaps.setCapability("phantomjs.page.settings.userAgent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
		dCaps.setCapability("phantomjs.page.customHeaders.User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");

		dCaps.setCapability("phantomjs.page.customHeaders.Referer", "https://m.jd.com/");

		dCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
				new String[] { "--load-images=false", "--disk-cache=true", "--ignore-ssl-errors=true" });
		try {
			System.setProperty(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
					PropertiesUtils.getProperty(PropertiesUtils.PHANTOMJS_DRIVER_PATH));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		WebDriver driver = new PhantomJSDriver(dCaps);
		return driver;
	}

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	SkuSrcDao skuSrcDao;

	@Autowired
	SkuInfoDao skuInfoDao;

	public void jdMain() {

		logger.info("=======================开始爬取数据=======================");

		List<SkuSrc> list = skuSrcDao.findSkuSrcByPlatfmCode(Constants.JD);
		long start = System.currentTimeMillis();
		WebDriver driver = getWebDriver();

		try {
			int i = 0;
			for (final SkuSrc sku : list) {
				++i;
				if (i % 100 == 0) {
					driver.quit();
					logger.info("退出程序....停止2S...");
					Thread.sleep(2000);
					logger.info("开启程序.......");
					driver = getWebDriver();
				}
				Thread.sleep(1000);
				SkuInfo skuInfo = new JsoupUtil().getGoods(driver, sku);
				if (skuInfo != null) {
					skuInfoDao.save(skuInfo);
				}
			}
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		logger.info("爬取耗时：" + (end - start) / 1000 / 60 + "分");
		logger.info("==============日期：" + DateUtil.getDateFormatStr(null) + "-抓取完毕=============");

	}
}
