package com.agfa.sh.cris.dbtool.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.agfa.sh.cris.dbtool.AppConstants;
import com.agfa.sh.cris.dbtool.domain.SimpleDepartment;

@Component
public class DepartmentSyncServiceImpl implements DepartmentSyncService{

	private final static Logger logger = LoggerFactory.getLogger(DepartmentSyncServiceImpl.class);
	
	@Autowired
	private SimpleDepartmentRepository simpleDepartmentRepository; 
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	//@Scheduled(cron="0/7 * * * * ?")
	@Override
	public void sync() {
		if (logger.isInfoEnabled()) {
			logger.info("=======================================================================================");
		}
		
		List<SimpleDepartment> deps = simpleDepartmentRepository.findAllActiveDepartments();
		int total = deps.size();
		if (logger.isInfoEnabled()) {
			logger.info("retrieved "+total+" departments");
		}
		
		syncReqDepartments(deps);
		
		syncNurseStations(deps);
	}
	
	private void syncReqDepartments(List<SimpleDepartment> deps) {
		long idx = getReqDepartmentCount();
		try {
			for (SimpleDepartment dep : deps) {
				if (dep.asRequestDepartment()) {
					if (logger.isDebugEnabled()) {
						logger.debug("req dep => "+dep.getCode()+", "+dep.getName());
					}
					
					String prefix = "rdep:"+dep.getCode()+":";
					if (!isKeyExists(prefix+"code")) {
						idx ++;
						setKeyValue("rdep:"+idx, dep.getCode());
						setKeyValue(prefix+"idx", String.valueOf(idx));
						setKeyValue(prefix+"code", dep.getCode());
					}
					
					setKeyValue(prefix+"id", dep.getId());
					setKeyValue(prefix+"name", dep.getName());
				}
			}
		} finally {
			updateReqDepartmentCount(idx);
		}
	}
	
	private void syncNurseStations(List<SimpleDepartment> deps) {
		long idx = getNurseStationCount();
		try {
			for (SimpleDepartment dep : deps) {
				if (dep.asNurseStation()) {
					if (logger.isDebugEnabled()) {
						logger.debug("nurse station => "+dep.getCode()+", "+dep.getName());
					}
					
					String prefix = "ndep:"+dep.getCode()+":";
					if (!isKeyExists(prefix+"code")) {
						idx ++;
						setKeyValue("ndep:"+idx, dep.getCode());
						setKeyValue(prefix+"idx", String.valueOf(idx));
						setKeyValue(prefix+"code", dep.getCode());
					}
					
					setKeyValue(prefix+"id", dep.getId());
					setKeyValue(prefix+"name", dep.getName());
				}
			}
		} finally {
			updateNurseStationCount(idx);
		}
	}
	
	private long getReqDepartmentCount() {
		String st = getValue(AppConstants.KEY_REQDEPARTMENT_COUNT);
		if (st == null || st.isEmpty()) {
			return 0L;
		}
		return Long.parseLong(st);
	}
	
	private void updateReqDepartmentCount(long cnt) {
		setKeyValue(AppConstants.KEY_REQDEPARTMENT_COUNT, String.valueOf(cnt));
	}
	
	private long getNurseStationCount() {
		String st = getValue(AppConstants.KEY_NURSESTATION_COUNT);
		if (st == null || st.isEmpty()) {
			return 0L;
		}
		return Long.parseLong(st);
	}
	
	private void updateNurseStationCount(long cnt) {
		setKeyValue(AppConstants.KEY_NURSESTATION_COUNT, String.valueOf(cnt));
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
