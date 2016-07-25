package com.agfa.sh.cris.dbtool.service;

import com.agfa.sh.cris.dbtool.domain.SimpleDepartment;
import com.agfa.sh.cris.dbtool.domain.SimpleDevice;
import com.agfa.sh.cris.dbtool.domain.SimpleExamItem;
import com.agfa.sh.cris.dbtool.domain.SimpleProfessional;
import com.agfa.sh.cris.dbtool.domain.SimpleReportTemplatePlaintext;

public interface MetaApiService {

	SimpleExamItem getExamItem(String code);
	
	SimpleExamItem randomExamItem();
	
	SimpleDepartment getReqDepartment(String code);
	
	SimpleDepartment randomReqDepartment();
	
	SimpleDepartment getNurseStation(String code);
	
	SimpleDepartment randomNurseStation();
	
	SimpleProfessional getProfessional(String code);
	
	SimpleProfessional randomProfessional();
	
	SimpleReportTemplatePlaintext getReportTemplate(String id);
	
	SimpleReportTemplatePlaintext randomReportTemplate();
	
	SimpleDevice getDevice(String code);
	
	SimpleDevice randomDevice(String type);
}
