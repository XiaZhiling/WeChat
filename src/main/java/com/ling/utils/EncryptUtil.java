package com.ling.utils;

import java.security.MessageDigest;

/**
 * 加密工具
 * @author XiaZhiling
 *
 */
public class EncryptUtil {
	
	/**
	 * SHA1 加密
	 * @param str
	 * @return 加密后的字符串
	 */
	public static String getSha1(String str) {
		if(str == null || str.length() == 0){
			return null;
		}
		char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
			messageDigest.update(str.getBytes("UTF-8"));
			byte[] md = messageDigest.digest();
			int j = md.length;
			char buf[] = new char[j*2];
			int k =0;
			for(int i = 0;i<j;i++){
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf]; 
			}
			return new String(buf);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
