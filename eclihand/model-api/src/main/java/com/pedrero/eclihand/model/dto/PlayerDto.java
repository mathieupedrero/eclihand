package com.pedrero.eclihand.model.dto;

import java.util.List;

public class PlayerDto extends DataObjectDto {
	private PersonDto playerPerson;

	private List<TeamDto> teams;

	public PersonDto getPlayerPerson() {
		return playerPerson;
	}

	public void setPlayerPerson(PersonDto playerPerson) {
		this.playerPerson = playerPerson;
	}

	public List<TeamDto> getTeams() {
		return teams;
	}

	public void setTeams(List<TeamDto> teams) {
		this.teams = teams;
	}

}
