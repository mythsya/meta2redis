package com.agfa.sh.cris.dbtool.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agfa.sh.cris.dbtool.domain.SimpleDepartment;
import com.agfa.sh.cris.dbtool.domain.SimpleExamItem;
import com.agfa.sh.cris.dbtool.domain.SimpleProfessional;
import com.agfa.sh.cris.dbtool.domain.SimpleReportTemplatePlaintext;
import com.agfa.sh.cris.dbtool.service.MetaApiService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class MetaApiController {

	@Autowired
	private MetaApiService metaApiService;
	
	@RequestMapping("/examitem")
	@JsonView(MetaCodedBeanJson.WithJsonView.class)
	public MetaCodedBeanJson getExamItem(@RequestParam(name="code", required=true) String code) {
		SimpleExamItem ei = metaApiService.getExamItem(code);
		MetaCodedBeanJson json = new MetaCodedBeanJson();
		if (ei != null) {
			json = new MetaCodedBeanJson(ei);
		}
		return json;
	}
	
	@RequestMapping("/examitem/random")
	@JsonView(MetaCodedBeanJson.WithJsonView.class)
	public MetaCodedBeanJson randomExamItem() {
		SimpleExamItem ei = metaApiService.randomExamItem();
		if (ei == null) {
			ei = metaApiService.randomExamItem();
		}
		MetaCodedBeanJson json = new MetaCodedBeanJson();
		if (ei != null) {
			json = new MetaCodedBeanJson(ei);
		}
		return json;
	}
	
	@RequestMapping("/reqdep")
	@JsonView(MetaCodedBeanJson.WithJsonView.class)
	public MetaCodedBeanJson getReqDepartment(@RequestParam(name="code", required=true) String code) {
		SimpleDepartment d = metaApiService.getReqDepartment(code);
		MetaCodedBeanJson json = new MetaCodedBeanJson();
		if (d != null) {
			json = new MetaCodedBeanJson(d);
		}
		return json;
	}
	
	@RequestMapping("/reqdep/random")
	@JsonView(MetaCodedBeanJson.WithJsonView.class)
	public MetaCodedBeanJson randomReqDepartment() {
		SimpleDepartment d = metaApiService.randomReqDepartment();
		if (d == null) {
			d = metaApiService.randomReqDepartment();
		}
		MetaCodedBeanJson json = new MetaCodedBeanJson();
		if (d != null) {
			json = new MetaCodedBeanJson(d);
		}
		return json;
	}
	
	@RequestMapping("/nursestation")
	@JsonView(MetaCodedBeanJson.WithJsonView.class)
	public MetaCodedBeanJson getNurseStation(@RequestParam(name="code", required=true) String code) {
		SimpleDepartment d = metaApiService.getNurseStation(code);
		MetaCodedBeanJson json = new MetaCodedBeanJson();
		if (d != null) {
			json = new MetaCodedBeanJson(d);
		}
		return json;
	}
	
	@RequestMapping("/nursestation/random")
	@JsonView(MetaCodedBeanJson.WithJsonView.class)
	public MetaCodedBeanJson randomNurseStation() {
		SimpleDepartment d = metaApiService.randomNurseStation();
		if (d == null) {
			d = metaApiService.randomNurseStation();
		}
		MetaCodedBeanJson json = new MetaCodedBeanJson();
		if (d != null) {
			json = new MetaCodedBeanJson(d);
		}
		return json;
	}
	
	@RequestMapping("/professional")
	@JsonView(MetaCodedBeanJson.WithJsonView.class)
	public MetaCodedBeanJson getProfessional(@RequestParam(name="code", required=true) String code) {
		SimpleProfessional d = metaApiService.getProfessional(code);
		MetaCodedBeanJson json = new MetaCodedBeanJson();
		if (d != null) {
			json = new MetaCodedBeanJson(d);
		}
		return json;
	}
	
	@RequestMapping("/professional/random")
	@JsonView(MetaCodedBeanJson.WithJsonView.class)
	public MetaCodedBeanJson randomProfessional() {
		SimpleProfessional d = metaApiService.randomProfessional();
		if (d == null) {
			d = metaApiService.randomProfessional();
		}
		MetaCodedBeanJson json = new MetaCodedBeanJson();
		if (d != null) {
			json = new MetaCodedBeanJson(d);
		}
		return json;
	}
	
	@RequestMapping("/reporttemplate")
	@JsonView(ReportTemplateJson.WithJsonView.class)
	public ReportTemplateJson getReportTemplate(@RequestParam(name="id", required=true) String id) {
		SimpleReportTemplatePlaintext d = metaApiService.getReportTemplate(id);
		ReportTemplateJson json = new ReportTemplateJson();
		if (d != null) {
			json = new ReportTemplateJson(d);
		}
		return json;
	}
	
	@RequestMapping("/reporttemplate/random")
	@JsonView(ReportTemplateJson.WithJsonView.class)
	public ReportTemplateJson randomReportTemplate() {
		SimpleReportTemplatePlaintext d = metaApiService.randomReportTemplate();
		if (d == null) {
			d = metaApiService.randomReportTemplate();
		}
		ReportTemplateJson json = new ReportTemplateJson();
		if (d != null) {
			json = new ReportTemplateJson(d);
		}
		return json;
	}
}
