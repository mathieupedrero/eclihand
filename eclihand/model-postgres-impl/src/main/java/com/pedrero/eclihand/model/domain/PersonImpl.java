package com.pedrero.eclihand.model.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PER_PERSON")
@PrimaryKeyJoinColumn(name = "ID")
public class PersonImpl extends IllustrableImpl implements Person {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4782595244056937785L;

	@Column(name = "PER_FIRST_NAME")
	private String firstName;

	@Column(name = "PER_LAST_NAME")
	private String lastName;

	@Column(name = "PER_BIRTH_DATE")
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@Column(name = "PER_GENDER")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public Date getBirthDate() {
		return birthDate;
	}

	@Override
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public Gender getGender() {
		return gender;
	}

	@Override
	public void setGender(Gender gender) {
		this.gender = gender;
	}

}
