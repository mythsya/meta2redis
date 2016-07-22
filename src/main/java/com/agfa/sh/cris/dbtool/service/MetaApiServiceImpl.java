package com.agfa.sh.cris.dbtool.service;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.agfa.sh.cris.dbtool.AppConstants;
import com.agfa.sh.cris.dbtool.domain.SimpleBaseData;
import com.agfa.sh.cris.dbtool.domain.SimpleDepartment;
import com.agfa.sh.cris.dbtool.domain.SimpleExamItem;
import com.agfa.sh.cris.dbtool.domain.SimpleProfessional;
import com.agfa.sh.cris.dbtool.domain.SimpleReportTemplatePlaintext;

@Component
public class MetaApiServiceImpl implements MetaApiService {

	private final static Logger logger = LoggerFactory.getLogger(MetaApiServiceImpl.class);
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Override
	public SimpleExamItem getExamItem(String code) {
		String prefix = "ei:"+code+":";
		if (isKeyExists(prefix+"code")) {
			SimpleExamItem ei = new SimpleExamItem();
			ei.setId(getValue(prefix+"id"));
			ei.setCode(getValue(prefix+"code"));
			ei.setName(getValue(prefix+"name"));
			
			SimpleBaseData mdt = new SimpleBaseData();
			mdt.setCode(getValue(prefix+"modality"));
			ei.setModalityType(mdt);
			return ei;
		}
		
		return null;
	}

	@Override
	public SimpleExamItem randomExamItem() {
		int total = getCountFor(AppConstants.KEY_EXAMITEM_COUNT);
		if (total > 0) {
			Random rnd = new Random();
			int idx = rnd.nextInt(total)+1; // [1, total]
			String key = "ei:"+idx;
			if (logger.isDebugEnabled()) {
				logger.debug("random examitem index -> "+idx);
			}
			if (isKeyExists(key)) {
				String code =getValue(key);
				if (logger.isDebugEnabled()) {
					logger.debug("random examitem code -> "+code);
				}
				return getExamItem(code);
			}
		}
		return null;
	}

	@Override
	public SimpleDepartment getReqDepartment(String code) {
		String prefix = "rdep:"+code+":";
		if (isKeyExists(prefix+"code")) {
			SimpleDepartment dep = new SimpleDepartment();
			dep.setId(getValue(prefix+"id"));
			dep.setCode(getValue(prefix+"code"));
			dep.setName(getValue(prefix+"name"));
			return dep;
		}
		
		return null;
	}

	@Override
	public SimpleDepartment randomReqDepartment() {
		int total = getCountFor(AppConstants.KEY_REQDEPARTMENT_COUNT);
		if (total > 0) {
			Random rnd = new Random();
			int idx = rnd.nextInt(total)+1; // [1, total]
			String key = "rdep:"+idx;
			if (logger.isDebugEnabled()) {
				logger.debug("random req department index -> "+idx);
			}
			if (isKeyExists(key)) {
				String code =getValue(key);
				if (logger.isDebugEnabled()) {
					logger.debug("random req department code -> "+code);
				}
				return getReqDepartment(code);
			}
		}
		return null;
	}

	@Override
	public SimpleDepartment getNurseStation(String code) {
		String prefix = "ndep:"+code+":";
		if (isKeyExists(prefix+"code")) {
			SimpleDepartment dep = new SimpleDepartment();
			dep.setId(getValue(prefix+"id"));
			dep.setCode(getValue(prefix+"code"));
			dep.setName(getValue(prefix+"name"));
			return dep;
		}
		
		return null;
	}

	@Override
	public SimpleDepartment randomNurseStation() {
		int total = getCountFor(AppConstants.KEY_NURSESTATION_COUNT);
		if (total > 0) {
			Random rnd = new Random();
			int idx = rnd.nextInt(total)+1; // [1, total]
			String key = "ndep:"+idx;
			if (logger.isDebugEnabled()) {
				logger.debug("random nurse station index -> "+idx);
			}
			if (isKeyExists(key)) {
				String code =getValue(key);
				if (logger.isDebugEnabled()) {
					logger.debug("random nurse station code -> "+code);
				}
				return getNurseStation(code);
			}
		}
		return null;
	}

	@Override
	public SimpleProfessional getProfessional(String code) {
		String prefix = "pro:"+code+":";
		if (isKeyExists(prefix+"code")) {
			SimpleProfessional p = new SimpleProfessional();
			p.setId(getValue(prefix+"id"));
			p.setCode(getValue(prefix+"code"));
			p.setName(getValue(prefix+"name"));
			return p;
		}
		
		return null;
	}

	@Override
	public SimpleProfessional randomProfessional() {
		int total = getCountFor(AppConstants.KEY_PROFESSIONAL_COUNT);
		if (total > 0) {
			Random rnd = new Random();
			int idx = rnd.nextInt(total)+1; // [1, total]
			String key = "pro:"+idx;
			if (logger.isDebugEnabled()) {
				logger.debug("random professional index -> "+idx);
			}
			if (isKeyExists(key)) {
				String code =getValue(key);
				if (logger.isDebugEnabled()) {
					logger.debug("random professional code -> "+code);
				}
				return getProfessional(code);
			}
		}
		return null;
	}

	@Override
	public SimpleReportTemplatePlaintext getReportTemplate(String id) {
		String prefix = "rptpl:"+id+":";
		if (isKeyExists(prefix+"id")) {
			SimpleReportTemplatePlaintext p = new SimpleReportTemplatePlaintext();
			p.setId(getValue(prefix+"id"));
			p.setTitle(getValue(prefix+"title"));
			p.setImpressions(getValue(prefix+"impressions"));
			p.setConclusions(getValue(prefix+"conclusions"));
			return p;
		}
		
		return null;
	}

	@Override
	public SimpleReportTemplatePlaintext randomReportTemplate() {
		int total = getCountFor(AppConstants.KEY_REPORT_TEMPLATE_COUNT);
		if (total > 0) {
			Random rnd = new Random();
			int idx = rnd.nextInt(total)+1; // [1, total]
			String key = "rptpl:"+idx;
			if (logger.isDebugEnabled()) {
				logger.debug("random report template index -> "+idx);
			}
			if (isKeyExists(key)) {
				String id =getValue(key);
				if (logger.isDebugEnabled()) {
					logger.debug("random report template id -> "+id);
				}
				return getReportTemplate(id);
			}
		}
		return null;
	}

	private int getCountFor(String key) {
		String st = getValue(key);
		if (st == null || st.isEmpty()) {
			return 0;
		}
		return Integer.parseInt(st);
	}
	
	private boolean isKeyExists(String key) {
		return stringRedisTemplate.hasKey(key);
	}
	
	private String getValue(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}
}
