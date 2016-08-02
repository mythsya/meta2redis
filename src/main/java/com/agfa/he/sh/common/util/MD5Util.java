package com.agfa.he.sh.common.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.MessageDigest;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MD5Util {
	
	public static void main(String[] args) {
	}
	
	public final static byte[] MD5_b(String s) {
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			return md;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public final static String MD5(String s) {
       byte[] cipherData = MD5_b(s);
       if (cipherData != null) {
    	   StringBuilder sb = new StringBuilder();
    	   for (byte cipher: cipherData) {
    		   String toHexStr = Integer.toHexString(cipher & 0xff);
    		   sb.append(toHexStr.length() == 1? 0+toHexStr : toHexStr);
    	   }
    	   return sb.toString();
       }
       return null;
    }
}
