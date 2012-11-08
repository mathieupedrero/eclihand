package com.pedrero.eclihand.model.domain;

import java.util.List;

@SuppressWarnings("rawtypes")
public interface Player<P extends Person, T extends Team> extends Illustrable {

	public abstract P getPlayerPerson();

	public abstract void setPlayerPerson(P playerPerson);

	public abstract List<T> getTeams();

	public abstract void setTeams(List<T> teams);

}