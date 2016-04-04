package com.ling.utils;

import java.util.Arrays;

/**
 * ��֤��
 * @author XiaZhiling
 *
 */
public class CheckUtil {
	
	private static final String token = "wechat";//token
	
	/**
	 * 1. ��token��timestamp��nonce�������������ֵ�������
     * 2. �����������ַ���ƴ�ӳ�һ���ַ�������sha1����
     * 3. �����߻�ü��ܺ���ַ�������signature�Աȣ���ʶ��������Դ��΢��
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
		
		String temp = EncryptUtil.getSha1(sb.toString());//sha1����
		return temp.equals(signature);
	}

}
