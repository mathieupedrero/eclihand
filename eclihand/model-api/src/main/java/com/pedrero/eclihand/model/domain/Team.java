package com.pedrero.eclihand.model.domain;

import java.util.Set;

@SuppressWarnings("rawtypes")
public interface Team<P extends Player> extends Illustrable {

	public abstract Integer getYear();

	public abstract void setYear(Integer year);

	public Set<P> getPlayers();

	public void setPlayers(Set<P> players);

	public Gender getGender();

	public void setGender(Gender gender);

}