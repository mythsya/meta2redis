package com.agfa.sh.cris.dbtool.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.agfa.sh.cris.dbtool.AppConstants;
import com.agfa.sh.cris.dbtool.domain.SimpleBaseData;
import com.agfa.sh.cris.dbtool.domain.SimpleReportTemplatePlaintext;

@Component
public class ReportTemplateSyncServiceImpl implements ReportTemplateSyncService {

	private final static Logger logger = LoggerFactory.getLogger(ReportTemplateSyncServiceImpl.class);
	
	@Autowired
	private SimpleReportTemplateRepository simpleReportTemplateRepository; 
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Override
	public void sync() {
		if (logger.isInfoEnabled()) {
			logger.info("=======================================================================================");
		}
		
		List<SimpleReportTemplatePlaintext> reportTemplates =  simpleReportTemplateRepository.findAllReportTemplates("radiology-domain");
		int total = reportTemplates.size();
		if (logger.isInfoEnabled()) {
			logger.info("retrieved "+total+" report templates");
		}
		
		long idx = this.getReportTemplateCount();
		try {
			for(SimpleReportTemplatePlaintext rpt : reportTemplates) {
				if (logger.isDebugEnabled()) {
					logger.debug("report template => "+rpt.getId()+", "+rpt.getTitle());
				}
				
				String prefix = "rptpl:"+rpt.getId()+":";
				if (!isKeyExists(prefix+"id")) {
					idx ++;
					setKeyValue("rptpl:"+idx, rpt.getId());
					setKeyValue(prefix+"idx", String.valueOf(idx));
					setKeyValue(prefix+"id", rpt.getId());
				}
								
				setKeyValue(prefix+"title", rpt.getTitle());
				setKeyValue(prefix+"impressions", rpt.getImpressions());
				setKeyValue(prefix+"conclusions", rpt.getConclusions());
				
				//setKeyTags(prefix+"categories", rpt.getCategories());
				setKeyTags(prefix+"modalities", rpt.getModalities());
				setKeyTags(prefix+"tsgroups", rpt.getTargetSiteGroups());
				setKeyTags(prefix+"targetsites", rpt.getTargetSites());

			}
		}finally {
			updateReportTemplateCount(idx);
		}
	}
	
	private void setKeyTags(String key, List<SimpleBaseData> tags) {
		if(tags != null && !tags.isEmpty()) {
			List<String> codes = new ArrayList<String>();
			for(SimpleBaseData tag : tags) {
				codes.add(tag.getCode());
			}
			String value = StringUtils.collectionToCommaDelimitedString(codes);
			setKeyValue(key, value);
		}
	}

	private long getReportTemplateCount() {
		String st = getValue(AppConstants.KEY_REPORT_TEMPLATE_COUNT);
		if (st == null || st.isEmpty()) {
			return 0L;
		}
		return Long.parseLong(st);
	}
	
	private void updateReportTemplateCount(long cnt) {
		setKeyValue(AppConstants.KEY_REPORT_TEMPLATE_COUNT, String.valueOf(cnt));
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
