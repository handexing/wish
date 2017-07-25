package com.wish.util;

/**
 * @author Pinky Lam 908716835@qq.com
 * @date 2017年7月25日 下午9:42:04
 */
public class Constants {

	/**
	 * 京东
	 */
	public static final Integer JD = 1;

	/**
	 * 商品链接
	 */
	public static final String JD_GOODS_URL = "https://item.jd.com/{code}.html";

	/**
	 * 京东商品价格
	 */
	public static final String JD_GOODS_PRICE = "http://p.3.cn/prices/mgets?skuIds=J_{skuId}&pdtk=&pduid";

	/**
	 * 京东商品名称
	 */
	public static final String JD_GOODS_TITLE = "https://yx.3.cn/service/info.action?ids=";

	/**
	 * 京东商品促销
	 */
	public static final String JD_GOODS_PROMOTION = "https://cd.jd.com/promotion/v2?skuId={skuId}&area=1_72_2799_0&cat={cates}";

	/**
	 * 最大线程数
	 */
	public static final int MAX_THREAD_CNT = 10;

	/**
	 * 京东商品手机端URL
	 */
	public static final String JD_MOBILE_GOODS_URL = "https://item.m.jd.com/product/{skuId}.html";

}
