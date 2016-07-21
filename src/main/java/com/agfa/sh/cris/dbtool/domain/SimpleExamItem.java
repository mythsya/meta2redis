package com.agfa.sh.cris.dbtool.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="exam_item")
public class SimpleExamItem implements Serializable{

	private static final long serialVersionUID = -1779882124410348947L;

	@Id
	@Column(name = "id", length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	
	@Column(name = "domain")
	private String domain;
	
	@Column(name = "bizid")
	private String code;
	
	@Column(name = "name")
	private String name; 
	
	@Column(name = "englishname")
	private String engname;
	
	@Column(name = "enabled")
	private Boolean enabled = false;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="modalitytype_id", nullable=false, updatable=false)
	private SimpleBaseData modalityType;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEngname() {
		return engname;
	}

	public void setEngname(String engname) {
		this.engname = engname;
	}

	public SimpleBaseData getModalityType() {
		return modalityType;
	}

	public void setModalityType(SimpleBaseData modalityType) {
		this.modalityType = modalityType;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	} 
	
}
