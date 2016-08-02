package com.agfa.sh.cris.dbtool.web;

import com.agfa.sh.cris.dbtool.domain.SimpleBaseData;
import com.agfa.sh.cris.dbtool.domain.SimpleDepartment;
import com.agfa.sh.cris.dbtool.domain.SimpleDevice;
import com.agfa.sh.cris.dbtool.domain.SimpleExamItem;
import com.agfa.sh.cris.dbtool.domain.SimpleProfessional;
import com.agfa.sh.cris.dbtool.domain.SimpleUser;
import com.fasterxml.jackson.annotation.JsonView;

public class MetaCodedBeanJson {
	public static interface WithJsonView{}
	
	@JsonView(WithJsonView.class)
	public String id;
	
	@JsonView(WithJsonView.class)
	public String code;
	
	@JsonView(WithJsonView.class)
	public String name;
	
	@JsonView(WithJsonView.class)
	public String extra;
	
	public MetaCodedBeanJson() {}
	
	public MetaCodedBeanJson(SimpleBaseData v) {
		this.id = v.getId();
		this.code = v.getCode();
		this.name = v.getName();
		this.extra = "";
	}
	
	public MetaCodedBeanJson(SimpleExamItem v) {
		this.id = v.getId();
		this.code = v.getCode();
		this.name = v.getName();
		this.extra = v.getModalityType() != null? v.getModalityType().getCode() : "";
	}
	
	public MetaCodedBeanJson(SimpleDepartment v) {
		this.id = v.getId();
		this.code = v.getCode();
		this.name = v.getName();
		this.extra = "";
	}
	
	public MetaCodedBeanJson(SimpleProfessional v) {
		this.id = v.getId();
		this.code = v.getCode();
		this.name = v.getName();
		this.extra = "";
	}

	public MetaCodedBeanJson(SimpleDevice v) {
		this.id = v.getId();
		this.code = v.getCode();
		this.name = v.getName();
		this.extra = v.getModalityType().getCode();
	}
	
	public MetaCodedBeanJson(SimpleUser v) {
		this.id = v.getId();
		this.code = v.getUsername();
		this.name = v.getRoleName();
		this.extra = v.getPassword();
	}
}
