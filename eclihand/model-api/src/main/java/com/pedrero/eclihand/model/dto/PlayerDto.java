package com.pedrero.eclihand.model.dto;

import java.util.Set;

public class PlayerDto extends IllustrableDto {
	private PersonDto playerPerson;

	private Set<TeamDto> teams;

	public PersonDto getPlayerPerson() {
		return playerPerson;
	}

	public void setPlayerPerson(PersonDto playerPerson) {
		this.playerPerson = playerPerson;
	}

	public Set<TeamDto> getTeams() {
		return teams;
	}

	public void setTeams(Set<TeamDto> teams) {
		this.teams = teams;
	}

}
