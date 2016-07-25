package com.agfa.sh.cris.dbtool.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.agfa.sh.cris.dbtool.AppConstants;
import com.agfa.sh.cris.dbtool.domain.SimpleDevice;
import com.agfa.sh.cris.dbtool.domain.SimpleProfessional;

@Component
public class DeviceSyncServiceImpl implements DeviceSyncService {
	
	private final static Logger logger = LoggerFactory.getLogger(DeviceSyncServiceImpl.class);

	@Autowired
	private SimpleDeviceRepository simpleDeviceRepository; 
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Override
	public void sync() {
		if (logger.isInfoEnabled()) {
			logger.info("=======================================================================================");
		}
		
		List<SimpleDevice> devs = simpleDeviceRepository.findAllActiveDevices();
		int total = devs.size();
		if (logger.isInfoEnabled()) {
			logger.info("retrieved "+total+" devices");
		}
		
		
		long idx = getDeviceCount();
		
		try {
			for(SimpleDevice dev : devs) {
				if (logger.isDebugEnabled()) {
					logger.debug("device => "+dev.getCode()+", "+dev.getName());
				}
				
				String prefix = "dev:"+dev.getCode()+":";
				if (!isKeyExists(prefix+"code")) {
					idx ++;
					setKeyValue("dev:"+idx, dev.getCode());
					setKeyValue(prefix+"idx", String.valueOf(idx));
					setKeyValue(prefix+"code", dev.getCode());
				}
				
				setKeyValue(prefix+"id", dev.getId());
				setKeyValue(prefix+"name", dev.getName());
				setKeyValue(prefix+"type", dev.getModalityType().getCode());
			}
		} finally {
			updateDeviceCount(idx);
		}
	}

	private long getDeviceCount() {
		String st = getValue(AppConstants.KEY_DEVICE_COUNT);
		if (st == null || st.isEmpty()) {
			return 0L;
		}
		return Long.parseLong(st);
	}
	
	private void updateDeviceCount(long cnt) {
		setKeyValue(AppConstants.KEY_DEVICE_COUNT, String.valueOf(cnt));
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
