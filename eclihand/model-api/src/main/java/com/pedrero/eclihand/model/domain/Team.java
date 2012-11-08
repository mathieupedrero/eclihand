package com.pedrero.eclihand.model.domain;

import java.util.List;


@SuppressWarnings("rawtypes")
public interface Team<P extends Player> extends Illustrable {

	public abstract Integer getYear();

	public abstract void setYear(Integer year);

	public List<P> getPlayers();

	public void setPlayers(List<P> players);

	public Gender getGender();

	public void setGender(Gender gender);

}