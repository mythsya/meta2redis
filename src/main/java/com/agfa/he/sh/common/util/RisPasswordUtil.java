package com.agfa.he.sh.common.util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class RisPasswordUtil {

	private static final Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
	
	public static final String encodePassword(String rawPass, String userId) {
		return md5PasswordEncoder.encodePassword(rawPass, userId);
	}
	
	public static void main(String[] args) {
		String encodedPwd = Md5JsUtil.b64_md5("123"); 
		encodedPwd = encodePassword(encodedPwd, "2c9a495534452928013445d902cf28d3");
		
		System.out.println(encodedPwd);
	}
}
