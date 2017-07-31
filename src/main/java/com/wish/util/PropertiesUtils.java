package com.wish.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author handx 908716835@qq.com
 * @date 2017年6月1日 下午10:34:15
 */
public class PropertiesUtils {

	public static final String PHANTOMJS_DRIVER_PATH = "phantomjs.driver.path";

	public static String getProperty(String key) throws FileNotFoundException, IOException {
		Properties pps = new Properties();
		pps.load(PropertiesUtils.class.getClassLoader().getResourceAsStream("config.properties"));
		String value = pps.getProperty(key);
		return value;
	}




}
