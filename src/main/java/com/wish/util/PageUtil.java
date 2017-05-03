package com.wish.util;

public class PageUtil {
	/**
	 * 跟客户端约定页码从1开始为第一页
	 * 
	 * @param page 页码
	 * @return 返回页码
	 */
	public static int calcPage(int page) {
		return page <= 1 ? 0 : page - 1;
	}
}
