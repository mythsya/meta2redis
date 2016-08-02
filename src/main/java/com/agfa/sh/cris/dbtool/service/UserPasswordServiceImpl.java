package com.agfa.sh.cris.dbtool.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.agfa.he.sh.common.util.Md5JsUtil;
import com.agfa.he.sh.common.util.RisPasswordUtil;
import com.agfa.sh.cris.dbtool.domain.SimpleUser;

@Component
@Transactional(readOnly=true)
public class UserPasswordServiceImpl implements UserPasswordService{

	private final static Logger logger = LoggerFactory.getLogger(UserPasswordServiceImpl.class);
	
	@Autowired
	private SimpleUserRepository simpleUserRepository;
	
	private String encodePassword(String rawPwd, String userId) {
		String encodedPwd = Md5JsUtil.b64_md5(rawPwd); 
		encodedPwd = RisPasswordUtil.encodePassword(encodedPwd, userId);
		return encodedPwd;
	}
	
	@Override
	@Transactional
	public void changePwd(String userId, String newPwd) {
		if (logger.isDebugEnabled()) {
			logger.debug("changing password to "+newPwd+" for user(id="+userId+")");
		}
		String encodedPwd = encodePassword(newPwd, userId);
		simpleUserRepository.changePassword(userId, encodedPwd);
	}

	@Override
	@Transactional
	public void changeAllPwd(String domain, String newPwd) {
		List<SimpleUser> users = simpleUserRepository.findAllEnabledUsers();

		if (users != null) {
			if (logger.isInfoEnabled()) {
				logger.debug("changing password to "+newPwd+" for　"+users.size()+" users within "+domain);
			}
			for (SimpleUser user : users) {
				String encodedPwd = encodePassword(newPwd, user.getId());
				simpleUserRepository.changePassword(user.getId(), encodedPwd);
			}
		}
	}

}
