package com.pedrero.eclihand.model.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "USR_USER")
@PrimaryKeyJoinColumn(name = "ID")
public class UserImpl extends IllustrableImpl implements User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2426191255541763126L;

	@Column(name = "USR_LOGIN")
	private String login;

	@Column(name = "USR_MAIL_ADDRESS")
	private String mailAddress;

	@Column(name = "USR_PASSWORD")
	private String password;

	@Column(name = "USR_USER_TYPE")
	@Enumerated(EnumType.STRING)
	private UserType userType;

	@ManyToMany
	@JoinTable(name = "USR_USER_PRF_PROFILE", joinColumns = @JoinColumn(name = "USR_ID"), inverseJoinColumns = @JoinColumn(name = "PRF_ID"))
	private Set<ProfileImpl> profiles = new HashSet<ProfileImpl>();

	@Override
	public String getLogin() {
		return login;
	}

	@Override
	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public String getMailAddress() {
		return mailAddress;
	}

	@Override
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public UserType getUserType() {
		return userType;
	}

	@Override
	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Set<Profile> getProfiles() {
		return (Set) profiles;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setProfiles(Set<Profile> profiles) {
		this.profiles = (Set) profiles;
	}

}
