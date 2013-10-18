package com.pedrero.eclihand.model.domain;

import java.util.Set;

public interface Team extends Illustrable {

	public abstract Integer getYear();

	public abstract void setYear(Integer year);

	public Set<Player> getPlayers();

	public Gender getGender();

	public void setGender(Gender gender);

}