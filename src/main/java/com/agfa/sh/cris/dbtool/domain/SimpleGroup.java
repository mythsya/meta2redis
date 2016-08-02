package com.agfa.sh.cris.dbtool.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="auth_group")
public class SimpleGroup implements Serializable{

	private static final long serialVersionUID = 5516308145912959601L;
	
	@Id
	@Column(name = "id", length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	
	@Column(name = "domain")
	private String domain;
	
	@Column(name = "enabled")
	private Boolean enabled = true;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "type")
	private String type;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "AUTH_GROUP_ASSIGNABLEWORKITEMS", joinColumns = @JoinColumn(name = "AUTH_GROUP_ID"))
	@Column(name = "element")
	private Set<String> assignableWorkitems = new HashSet<String>();

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "AUTH_GROUP_AUTHORITY", joinColumns = @JoinColumn(name = "AUTH_GROUP_ID"))
	@Column(name = "authority")
	private Set<String> authorityNames = new HashSet<String>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public Set<String> getAssignableWorkitems() {
		return assignableWorkitems;
	}

	public void setAssignableWorkitems(Set<String> assignableWorkitems) {
		this.assignableWorkitems = assignableWorkitems;
	}


	public Set<String> getAuthorityNames() {
		return authorityNames;
	}

	public void setAuthorityNames(Set<String> authorityNames) {
		this.authorityNames = authorityNames;
	}

}
