package com.pedrero.eclihand.model.domain;

import java.util.Date;


public interface Person extends Illustrable {

	public abstract String getFirstName();

	public abstract void setFirstName(String firstName);

	public abstract String getLastName();

	public abstract void setLastName(String lastName);

	public abstract Date getBirthDate();

	public abstract void setBirthDate(Date birthDate);

	public abstract Gender getGender();

	public abstract void setGender(Gender gender);

}