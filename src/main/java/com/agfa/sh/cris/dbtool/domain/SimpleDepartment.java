package com.agfa.sh.cris.dbtool.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="role_department")
public class SimpleDepartment implements Serializable{

	private static final long serialVersionUID = -6005170104898016165L;

	@Id
	@Column(name = "id", length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	
	@Column(name = "id_extension")
	private String code;
	
	@Column(name = "name")
	private String name; 
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "depttype")
	private String type;
	
	public boolean asRequestDepartment() {
		return type != null && type.indexOf("-OB-")>=0;
	}
	
	public boolean asNurseStation() {
		return type != null && type.indexOf("-N-") >= 0;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
