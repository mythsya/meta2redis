package com.agfa.sh.cris.dbtool.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.agfa.sh.cris.dbtool.domain.SimpleReportTemplatePlaintext;

public interface SimpleReportTemplateRepository extends Repository<SimpleReportTemplatePlaintext,String>{

	@Query(value="select r from SimpleReportTemplatePlaintext r where r.domain = ?1 ")
	List<SimpleReportTemplatePlaintext> findAllReportTemplates(String domain);
}
