package com.agfa.sh.cris.dbtool.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agfa.sh.cris.dbtool.service.UserPasswordService;

@RestController
public class UserController {

	@Autowired
	private UserPasswordService userPasswordService;
	
	@RequestMapping("/user/password/chgall")
	@Transactional
	public String changeAllPasswords(@RequestParam(name="pwd", required=false) String pwd) {
		if (pwd == null || pwd.isEmpty()) {
			pwd = "123";
		}
		userPasswordService.changeAllPwd("radiology-domain", pwd);
		
		return "1";
	}
	
	@RequestMapping("/user/password/chg")
	@Transactional
	public String changePassword(@RequestParam(name="uid", required=true) String uid, 
			@RequestParam(name="pwd", required=false) String pwd) {
		if (pwd == null || pwd.isEmpty()) {
			pwd = "123";
		}
		userPasswordService.changePwd(uid, pwd);
		
		return "1";
	}
}
