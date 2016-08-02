package com.agfa.sh.cris.dbtool.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.agfa.he.sh.common.util.Md5JsUtil;
import com.agfa.he.sh.common.util.RisPasswordUtil;
import com.agfa.sh.cris.dbtool.AppConstants;
import com.agfa.sh.cris.dbtool.domain.SimpleUser;


@Component
@Transactional(readOnly=true)
public class UserSyncServiceImpl implements UserSyncService{

	private final static Logger logger = LoggerFactory.getLogger(UserSyncServiceImpl.class);
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private SimpleUserRepository simpleUserRepository;
	
	private final String defaultPassword = Md5JsUtil.b64_md5("123");
	
	@Override
	public void sync() {
		if (logger.isInfoEnabled()) {
			logger.info("=======================================================================================");
		}
		
		List<SimpleUser> users = simpleUserRepository.findAllEnabledUsers();
		int total = users.size();
		if (logger.isInfoEnabled()) {
			logger.info("retrieved "+total+" users");
		}
		
		Map<String, List<String>> workitemMap = new HashMap<String,List<String>>();
		
		long idx = getUserCount();
		SimpleUser defaultUser = null;
		try {			
			for(SimpleUser user : users) {
				String[] workitems = getAssignableWorkitems(user);				
				String workitemStr = StringUtils.arrayToDelimitedString(workitems, ",");
				if (logger.isDebugEnabled()) {
					logger.debug("user => "+user.getId()+", "+user.getUsername()+" > "+workitemStr);
				}
				updateWorkitemMap(workitemMap, user.getId(), workitems);
				String prefix = "user:"+user.getId()+":";
				if (!isKeyExists(prefix+"id")) {
					idx ++;
					setKeyValue("user:"+idx, user.getId());
					setKeyValue(prefix+"idx", String.valueOf(idx));	
					setKeyValue(prefix+"id", user.getId());
				}
				setKeyValue(prefix+"username", user.getUsername());
				setKeyValue(prefix+"rawpwd", defaultPassword);
				setKeyValue(prefix+"pwd", user.getPassword());
				setKeyValue(prefix+"name", user.getRoleName());
				setKeyValue(prefix+"group", user.getCurrentGroup().getId());
				setKeyValue(prefix+"workitems", workitemStr);
				
				if ("admin".equalsIgnoreCase(user.getUsername()) || "service".equalsIgnoreCase(user.getUsername())) {
					defaultUser = user;
				}
			}
			
			if (defaultUser != null) {
				setKeyValue("user:default", defaultUser.getId());
			}
			
			for(Entry<String, List<String>> entry : workitemMap.entrySet()) {
				String key = "user:wi:"+entry.getKey();
				List<String> values = entry.getValue();
				String allwi = StringUtils.collectionToDelimitedString(values, ",");
				setKeyValue(key, allwi);
			}
		} finally {
			updateUserCount(idx);
		}
		
	}
	
	private void updateWorkitemMap(Map<String, List<String>> workitemMap, String uid, String[] workitems) {
		for(String wi : workitems) {
			if (!workitemMap.containsKey(wi)) {
				workitemMap.put(wi, new ArrayList<String>());
			}
			List<String> wl = workitemMap.get(wi);
			if (!wl.contains(uid)) {
				wl.add(uid);
			}
		}
	}
	
	private String[] getAssignableWorkitems(SimpleUser user) {
		int workitemCnt = user.getCurrentGroup().getAssignableWorkitems().size();
		String[] workitems = new String[workitemCnt];
		int i=0;
		for(String w: user.getCurrentGroup().getAssignableWorkitems()) {
			workitems[i++] = w;
		}
		return workitems;
	}
	
	private long getUserCount() {
		String st = getValue(AppConstants.KEY_USER_COUNT);
		if (st == null || st.isEmpty()) {
			return 0L;
		}
		return Long.parseLong(st);
	}
	
	private void updateUserCount(long cnt) {
		setKeyValue(AppConstants.KEY_USER_COUNT, String.valueOf(cnt));
	}
	
	private boolean isKeyExists(String key) {
		return stringRedisTemplate.hasKey(key);
	}
	
	private void setKeyValue(String key, String val) {
		if (key!=null && !key.isEmpty() && val != null && !val.isEmpty()) {
			stringRedisTemplate.opsForValue().set(key, val);
		}
	}
	
	private String getValue(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	} 

}
