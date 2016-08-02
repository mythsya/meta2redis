package com.agfa.sh.cris.dbtool.service;

public interface UserPasswordService {

	void changePwd(String userId, String newPwd);
	
	void changeAllPwd(String domain, String newPwd);
	
}
