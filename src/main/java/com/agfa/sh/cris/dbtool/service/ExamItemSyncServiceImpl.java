package com.agfa.sh.cris.dbtool.service;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.agfa.sh.cris.dbtool.AppConstants;
import com.agfa.sh.cris.dbtool.domain.SimpleExamItem;

@Component
public class ExamItemSyncServiceImpl implements ExamItemSyncService{
	
	private final static Logger logger = LoggerFactory.getLogger(ExamItemSyncServiceImpl.class);
	
	@Autowired
	private SimpleExamItemRepository simpleExamItemRepository; 
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	//@Scheduled(cron="0/13 * * * * ?")
	@Override
	public void sync() {

		if (logger.isInfoEnabled()) {
			logger.info("=======================================================================================");
		}
		
		List<SimpleExamItem> examItems = simpleExamItemRepository.findAllEnabled("radiology-domain");
		int total = examItems.size();
		if (logger.isInfoEnabled()) {
			logger.info("retrieved "+total+" exam items");
		}	
		
		long idx = getExamItemCount();
		
		try {
			for(SimpleExamItem ei : examItems) {
				if (logger.isDebugEnabled()) {
					logger.debug("ei => "+ei.getCode()+", "+ei.getName()+", "+ei.getModalityType().getCode());
				}
				
				String prefix = "ei:"+ei.getCode()+":";
				if (!isKeyExists(prefix+"code")) {
					idx ++;
					setKeyValue("ei:"+idx, ei.getCode());
					setKeyValue(prefix+"idx", String.valueOf(idx));
					setKeyValue(prefix+"code", ei.getCode());
				}
				
				setKeyValue(prefix+"id", ei.getId());
				setKeyValue(prefix+"name", ei.getName());
				setKeyValue(prefix+"engname", ei.getEngname());
				setKeyValue(prefix+"modality", ei.getModalityType()!=null? ei.getModalityType().getCode(): "");
			}
		}finally {
			updateExamItemCount(idx);
		}
	}
	
	private long getExamItemCount() {
		String st = getValue(AppConstants.KEY_EXAMITEM_COUNT);
		if (st == null || st.isEmpty()) {
			return 0L;
		}
		return Long.parseLong(st);
	}
	
	private void updateExamItemCount(long cnt) {
		setKeyValue(AppConstants.KEY_EXAMITEM_COUNT, String.valueOf(cnt));
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
