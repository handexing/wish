package com.wish.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {

	private static final String DEFAULT_FORMAT = "yyyy-MM-dd";
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	/**
	 * 
	 * @param nowTime 当前时间
	 * @param beforeTime 对比时间
	 * @return -1 超过10分钟，1没超过
	 */
	public static int compareSmsTime(Long beforeTime) {
		final long time = 60 * 1000 * 10;
		Calendar cal = Calendar.getInstance();
		Long nowTime = cal.getTimeInMillis();
		long difference = nowTime - beforeTime;
		if (difference > time) {
			return -1;
		}
		return 1;
	}

	/**
	 * 
	 * @param nowTime 当前时间
	 * @param beforeTime 对比时间
	 * @return -1 超过10分钟，1没超过
	 */
	public static int compareSmsTime(Long nowTime, Long beforeTime) {
		final long time = 60 * 1000 * 10;
		System.out.println("nowTime:" + nowTime);
		System.out.println("beforeTime:" + beforeTime);
		long difference = nowTime - beforeTime;
		System.out.println("difference:" + difference);
		System.out.println("time:" + time);
		if (difference > time) {
			return -1;
		}
		return 1;
	}

	public static String getDateFormatStr(Date date) {
		if (date == null) {
			return new SimpleDateFormat(DEFAULT_FORMAT).format(new Date());
		}
		return new SimpleDateFormat(DEFAULT_FORMAT).format(date);
	}
	
	/**
	 * 日期转字符串
	 * 
	 * @param date 日期
	 * @param format 格式
	 * @return 格式化后的字符串
	 */
	public static String getDateFormatStr(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}
	
	/**
	 * 字符串转格式化日期
	 * 
	 * @param dateStr 日期字符串
	 * @param format 格式
	 * @return 日期
	 */
	public static Date parseDate(String dateStr, String format) {
		try {
			return new SimpleDateFormat(format).parse(dateStr);
		} catch (final ParseException e) {
			logger.error("", e);
			return null;
		}
	}
}
