package com.agfa.sh.cris.dbtool.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "report_template")
@Inheritance(strategy = InheritanceType.JOINED)
public class SimpleReportTemplate extends SimpleReportTemplateNode{

	private static final long serialVersionUID = -5755648925969265369L;

	@Column(name = "reporttype")
	protected String reportType;
	
	@ManyToMany(targetEntity = SimpleBaseData.class, fetch = FetchType.EAGER)
	@JoinTable(name = "report_template_modality", joinColumns = { @JoinColumn(name = "node_id") }, inverseJoinColumns = { @JoinColumn(name = "basedata_id") })
	protected List<SimpleBaseData> modalities = new ArrayList<SimpleBaseData>();
	
	@ManyToMany(targetEntity = SimpleBaseData.class, fetch = FetchType.EAGER)
	@JoinTable(name = "report_template_tsgroup", joinColumns = { @JoinColumn(name = "node_id") }, inverseJoinColumns = { @JoinColumn(name = "basedata_id") })
	protected List<SimpleBaseData> targetSiteGroups = new ArrayList<SimpleBaseData>();

	@ManyToMany(targetEntity = SimpleBaseData.class, fetch = FetchType.EAGER)
	@JoinTable(name = "report_template_targetsite", joinColumns = { @JoinColumn(name = "node_id") }, inverseJoinColumns = { @JoinColumn(name = "basedata_id") })
	protected List<SimpleBaseData> targetSites = new ArrayList<SimpleBaseData>();

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public List<SimpleBaseData> getModalities() {
		return modalities;
	}

	public void setModalities(List<SimpleBaseData> modalities) {
		this.modalities = modalities;
	}

	public List<SimpleBaseData> getTargetSiteGroups() {
		return targetSiteGroups;
	}

	public void setTargetSiteGroups(List<SimpleBaseData> targetSiteGroups) {
		this.targetSiteGroups = targetSiteGroups;
	}

	public List<SimpleBaseData> getTargetSites() {
		return targetSites;
	}

	public void setTargetSites(List<SimpleBaseData> targetSites) {
		this.targetSites = targetSites;
	}
}
