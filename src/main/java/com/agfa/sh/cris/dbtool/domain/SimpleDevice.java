package com.agfa.sh.cris.dbtool.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="role_device")
public class SimpleDevice implements Serializable{

	private static final long serialVersionUID = -4010877921349912835L;

	@Id
	@Column(name = "id", length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	
	@Column(name = "id_extension")
	private String code;
	
	@Column(name = "id_root")
	private String root;
	
	@Column(name = "name")
	private String name; 
	
	@Column(name = "status")
	private String status;
	
	@ManyToOne
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "modalitytype_id")
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

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public SimpleBaseData getModalityType() {
		return modalityType;
	}

	public void setModalityType(SimpleBaseData modalityType) {
		this.modalityType = modalityType;
	}
}
