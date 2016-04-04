package com.ling.utils;

import java.util.Arrays;

/**
 * 验证类
 * @author XiaZhiling
 *
 */
public class CheckUtil {
	
	private static final String token = "wechat";//token
	
	/**
	 * 1. 将token、timestamp、nonce三个参数进行字典序排序
     * 2. 将三个参数字符串拼接成一个字符串进行sha1加密
     * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean checkSignature(String signature,String timestamp,String nonce) {
		String[] arr = new String[]{token,timestamp,nonce}; 
		Arrays.sort(arr);
		
		StringBuffer sb = new StringBuffer();
		for(int i =0;i<arr.length;i++){
			sb.append(arr[i]);
		}
		
		String temp = EncryptUtil.getSha1(sb.toString());//sha1加密
		return temp.equals(signature);
	}

}
