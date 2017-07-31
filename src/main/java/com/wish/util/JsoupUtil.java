package com.wish.util;

import com.wish.entity.SkuInfo;
import com.wish.entity.SkuSrc;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;

import java.util.Date;

/**
 * @author Pinky Lam 908716835@qq.com
 * @date 2017年7月25日 下午9:37:22
 */
public class JsoupUtil {

	public final static Logger log = Logger.getLogger(JsoupUtil.class);

	public SkuInfo getGoods(WebDriver driver, SkuSrc skuSrc) {

		SkuInfo pisSkuInfo = new SkuInfo();

		String url = Constants.JD_MOBILE_GOODS_URL.replace("{skuId}", skuSrc.getSkuCode().trim());
		log.info("正在爬取：" + url);
		try {
			Thread.sleep(500);
			driver.get(url);
			log.info(driver.getTitle());
			Document doc = Jsoup.parse(driver.getPageSource());
			pisSkuInfo.setSkuSrcId(skuSrc.getSkuCode());
			pisSkuInfo.setDateId(new Date());

			String goodName = doc.select("#goodName").attr("value");
			if (StringUtils.isEmpty(goodName)) {
				log.warn("请求url有误！" + url);
				return null;
			}
			pisSkuInfo.setTitle(goodName);
			String price = doc.select("#jdPrice").attr("value");
			if ("暂无报价".equals(price)) {
				pisSkuInfo.setPrice(0.0);
			} else {
				pisSkuInfo.setPrice(Double.parseDouble(price));
			}
			String prodSubName = doc.select(".prod-act").text();
			pisSkuInfo.setSubtitle(prodSubName);
			return pisSkuInfo;
		} catch (Exception e) {
			log.warn("请求url有误！" + skuSrc.getUrl());
			e.printStackTrace();
		}
		return null;
	}

}
