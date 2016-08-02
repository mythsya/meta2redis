package com.agfa.sh.cris.dbtool.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
@Table(name="auth_user")
public class SimpleUser implements Serializable{

	private static final long serialVersionUID = 2843223596889190861L;

	@Id
	@Column(name = "id", length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "enabled")
	private Boolean enabled = true;
	
	@Column(name = "roleclassname")
	private String roleClassName;

	@Column(name = "rolecode")
	private String roleCode;

	@Column(name = "roleid")
	private String roleId;

	@Column(name = "rolename")
	private String roleName;
	
	@ManyToOne(targetEntity = SimpleGroup.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name="currentgroup_id")
	@Fetch(FetchMode.SELECT)
	private SimpleGroup currentGroup;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getRoleClassName() {
		return roleClassName;
	}

	public void setRoleClassName(String roleClassName) {
		this.roleClassName = roleClassName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public SimpleGroup getCurrentGroup() {
		return currentGroup;
	}

	public void setCurrentGroup(SimpleGroup currentGroup) {
		this.currentGroup = currentGroup;
	}
}
