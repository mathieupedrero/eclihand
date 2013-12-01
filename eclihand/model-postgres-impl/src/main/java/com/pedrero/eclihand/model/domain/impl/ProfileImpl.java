package com.pedrero.eclihand.model.domain.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.pedrero.eclihand.model.domain.Authorization;
import com.pedrero.eclihand.model.domain.Profile;

@Entity
@Table(name = "PRF_PROFILE")
@PrimaryKeyJoinColumn(name = "ID")
public class ProfileImpl extends IllustrableImpl implements Profile {

	@Column(name = "PRF_NAME")
	private String name;

	@ManyToMany
	@JoinTable(name = "PRF_PROFILE_AUT_AUTHORIZATION", joinColumns = @JoinColumn(name = "PRF_ID"), inverseJoinColumns = @JoinColumn(name = "AUT_ID"))
	private Set<AuthorizationImpl> authorizations = new HashSet<AuthorizationImpl>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Set<Authorization> getAuthorizations() {
		return (Set) authorizations;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setAuthorizations(Set<Authorization> authorizations) {
		this.authorizations = (Set) authorizations;
	}

}
