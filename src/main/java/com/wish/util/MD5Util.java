package com.wish.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	private static final Logger logger = LoggerFactory.getLogger(MD5Util.class);

	public static final String md5(final String s) {
		final char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			final byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			final MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			final byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			final int j = md.length;
			final char[] str = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				final byte byte0 = md[i];
				final int l = 0xf;
				final int l2 = 4;
				str[k++] = hexDigits[byte0 >>> l2 & l];
				str[k++] = hexDigits[byte0 & l];
			}
			return new String(str);
		} catch (final NoSuchAlgorithmException e) {
			logger.error("", e);
			return null;
		}
	}

	public static void main(String[] ars) {
		System.out.println(MD5Util.md5("Ideal@2015"));
	}
}