package com.agfa.sh.cris.dbtool.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.agfa.sh.cris.dbtool.AppConstants;
import com.agfa.sh.cris.dbtool.domain.SimpleProfessional;

@Component
public class ProfessionalSyncServiceImpl implements ProfessionalSyncService{

	private final static Logger logger = LoggerFactory.getLogger(ProfessionalSyncServiceImpl.class);
	
	@Autowired
	private SimpleProfessionalRepository simpleProfessionalRepository; 
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Override
	public void sync() {
		if (logger.isInfoEnabled()) {
			logger.info("=======================================================================================");
		}
		
		List<SimpleProfessional> pros = simpleProfessionalRepository.findAllActiveProfessional();
		int total = pros.size();
		if (logger.isInfoEnabled()) {
			logger.info("retrieved "+total+" professionals");
		}
		
		
		long idx = getProfessionalCount();
		
		try {
			for(SimpleProfessional pro : pros) {
				if (logger.isDebugEnabled()) {
					logger.debug("professional => "+pro.getCode()+", "+pro.getName());
				}
				
				String prefix = "pro:"+pro.getCode()+":";
				if (!isKeyExists(prefix+"code")) {
					idx ++;
					setKeyValue("pro:"+idx, pro.getCode());
					setKeyValue(prefix+"idx", String.valueOf(idx));
					setKeyValue(prefix+"code", pro.getCode());
				}
				
				setKeyValue(prefix+"id", pro.getId());
				setKeyValue(prefix+"name", pro.getName());
			}
		} finally {
			updateProfessionalCount(idx);
		}
	}
	
	private long getProfessionalCount() {
		String st = getValue(AppConstants.KEY_PROFESSIONAL_COUNT);
		if (st == null || st.isEmpty()) {
			return 0L;
		}
		return Long.parseLong(st);
	}
	
	private void updateProfessionalCount(long cnt) {
		setKeyValue(AppConstants.KEY_PROFESSIONAL_COUNT, String.valueOf(cnt));
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
