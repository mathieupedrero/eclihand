package com.pedrero.eclihand.model.dto;


public class LightPlayerDto extends IllustrableDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4040884833947479489L;

	private PersonDto playerPerson;

	public PersonDto getPlayerPerson() {
		return playerPerson;
	}

	public void setPlayerPerson(PersonDto playerPerson) {
		this.playerPerson = playerPerson;
	}

}
