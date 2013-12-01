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

import com.pedrero.eclihand.model.domain.Profile;
import com.pedrero.eclihand.model.domain.User;

@Entity
@Table(name = "USR_USER")
@PrimaryKeyJoinColumn(name = "ID")
public class UserImpl extends IllustrableImpl implements User {

	@Column(name = "USR_LOGIN")
	private String login;

	@Column(name = "USR_PASSWORD")
	private String password;

	@ManyToMany
	@JoinTable(name = "USR_USER_PRF_PROFILE", joinColumns = @JoinColumn(name = "USR_ID"), inverseJoinColumns = @JoinColumn(name = "PRF_ID"))
	private Set<ProfileImpl> profiles = new HashSet<ProfileImpl>();

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Set<Profile> getProfiles() {
		return (Set) profiles;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setProfiles(Set<Profile> profiles) {
		this.profiles = (Set) profiles;
	}

}
