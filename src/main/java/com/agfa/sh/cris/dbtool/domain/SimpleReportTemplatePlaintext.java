package com.agfa.sh.cris.dbtool.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "report_template_plaintext")
@Inheritance(strategy = InheritanceType.JOINED)
public class SimpleReportTemplatePlaintext extends SimpleReportTemplate{

	private static final long serialVersionUID = 6905534126904543849L;
	
	@Lob
	@Type(type = "org.hibernate.type.StringClobType")
	@Column(name = "conclusions", columnDefinition = "CLOB")
	protected String conclusions = null;

	@Lob
	@Type(type = "org.hibernate.type.StringClobType")
	@Column(name = "findings", columnDefinition = "CLOB")	
	protected String findings = null;

	@Lob
	@Type(type = "org.hibernate.type.StringClobType")
	@Column(name = "impressions", columnDefinition = "CLOB")
	protected String impressions = null;

	@Lob
	@Type(type = "org.hibernate.type.StringClobType")
	@Column(name = "proceduredescriptions", columnDefinition = "CLOB")
	protected String procedureDescriptions = null;

	public String getConclusions() {
		return conclusions;
	}

	public void setConclusions(String conclusions) {
		this.conclusions = conclusions;
	}

	public String getFindings() {
		return findings;
	}

	public void setFindings(String findings) {
		this.findings = findings;
	}

	public String getImpressions() {
		return impressions;
	}

	public void setImpressions(String impressions) {
		this.impressions = impressions;
	}

	public String getProcedureDescriptions() {
		return procedureDescriptions;
	}

	public void setProcedureDescriptions(String procedureDescriptions) {
		this.procedureDescriptions = procedureDescriptions;
	}

}
