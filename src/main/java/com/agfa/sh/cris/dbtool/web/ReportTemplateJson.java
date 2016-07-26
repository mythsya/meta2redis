package com.agfa.sh.cris.dbtool.web;

import com.agfa.sh.cris.dbtool.domain.SimpleReportTemplatePlaintext;
import com.fasterxml.jackson.annotation.JsonView;

public class ReportTemplateJson {
	public static interface WithJsonView{}
	
	@JsonView(WithJsonView.class)
	public String id;
	
	@JsonView(WithJsonView.class)
	public String title;
	
	@JsonView(WithJsonView.class)
	public String impressions;
	
	@JsonView(WithJsonView.class)
	public String conclusions;
	
	public ReportTemplateJson() {}
	
	public ReportTemplateJson(SimpleReportTemplatePlaintext p) {
		this.id = p.getId();
		this.title = p.getTitle();
		this.impressions = p.getImpressions();
		this.conclusions = p.getConclusions();
		if (this.conclusions == null || this.conclusions.isEmpty()) {
			this.conclusions = "测试";
		}
	}
}
