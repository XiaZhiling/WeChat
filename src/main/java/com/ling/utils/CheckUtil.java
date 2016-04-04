package com.ling.utils;

import java.util.Arrays;

public class CheckUtil {
	
	private static final String token = "wechat";//token
	
	public static boolean checkSignature(String signature,String timestamp,String nonce) {
		String[] arr = new String[]{token,timestamp,nonce}; 
		Arrays.sort(arr);
		
		StringBuffer sb = new StringBuffer();
		for(int i =0;i<arr.length;i++){
			sb.append(arr[i]);
		}
		
		String temp = EncryptUtil.getSha1(sb.toString());//sha1¼ÓÃÜ
		return temp.equals(signature);
	}

}
