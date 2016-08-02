package com.agfa.he.sh.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Base64Util {
	
	public static String encode(String str) {
		byte[] b = null;
		try {
			b = str.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {			
			e.printStackTrace();
		}
		if (b != null) {
			return encode(b);
		}
		return null;
	}
	 /** 
     * 编码 
     * @param bstr 
     * @return String 
     */  
    public static String encode(byte[] bstr){  
    	return new sun.misc.BASE64Encoder().encode(bstr);  
    } 
  
    /** 
     * 解码 
     * @param str 
     * @return string 
     */  
    public static byte[] decode(String str){  
	    byte[] bt = null;  
	    try {  
	        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();  
	        bt = decoder.decodeBuffer( str );  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
  
        return bt;  
    }  
}
