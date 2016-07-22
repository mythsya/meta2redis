package com.agfa.sh.cris.dbtool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerServiceImpl implements SchedulerService {

	private final static Logger logger = LoggerFactory.getLogger(SchedulerServiceImpl.class);
	
	@Autowired
	private ExamItemSyncService examItemSyncService;
	
	@Autowired
	private DepartmentSyncService departmentSyncService;
	
	@Autowired
	private ProfessionalSyncService professionalSyncService;
	
	@Autowired
	private ReportTemplateSyncService reportTemplateSyncService;
	
	//@Scheduled(cron="0/20 * * * * ?")
	@Scheduled(cron="0 0 22 * * ?")
	@Override
	public void run() {
		if (logger.isInfoEnabled()) {
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			logger.info("scheduler is running...");
		}
		
		examItemSyncService.sync();
		
		departmentSyncService.sync();
		
		professionalSyncService.sync();
		
		reportTemplateSyncService.sync();
		
		if (logger.isInfoEnabled()) {
			logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			logger.info("\n");
		}
	}

}
