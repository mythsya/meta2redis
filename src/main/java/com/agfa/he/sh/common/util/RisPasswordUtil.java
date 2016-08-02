package com.agfa.he.sh.common.util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class RisPasswordUtil {

	private static final Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
	
	public static final String encodePassword(String rawPass, String userId) {
		return md5PasswordEncoder.encodePassword(rawPass, userId);
	}
	
}
