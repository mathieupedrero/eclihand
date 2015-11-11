package com.pedrero.eclihand.model.dto;

import java.util.Set;

public class PlayerDto extends IllustrableDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4040884833947479489L;

	private PersonDto playerPerson;

	private Set<LightTeamDto> teams;

	public PersonDto getPlayerPerson() {
		return playerPerson;
	}

	public void setPlayerPerson(PersonDto playerPerson) {
		this.playerPerson = playerPerson;
	}

	public Set<LightTeamDto> getTeams() {
		return teams;
	}

	public void setTeams(Set<LightTeamDto> teams) {
		this.teams = teams;
	}

}
