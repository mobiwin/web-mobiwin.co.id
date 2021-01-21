package com.mobiwin.websites.common;

import org.springframework.util.DigestUtils;
 
import java.security.MessageDigest;
 
/**
 * @author Kodak
 * @version 1.0
 * @create 2019/08/04/10:30
 */
public class MD5Util {
	/***
	 32 * MD5 md5 code generating overweight
	 */
	public static String string2MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];
 
		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16){
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
 
	}
 
	/**
	  * Perform encryption and decryption algorithm encryption once, twice decryption
	 */
	public static String convertMD5(String inStr) {
 
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;
 
	}
 
	 // main function test
	public static void main(String args[]) {
		String s = "Kodak";
		 System.out.println ( "Original:" + s);
		 System.out.println ( "after MD5:" + string2MD5 (s));
		 System.out.println ( "encryption:" + convertMD5 (s));
		 System.out.println ( "decrypted:" + convertMD5 (convertMD5 (s)));
	}
 
 
 
}
