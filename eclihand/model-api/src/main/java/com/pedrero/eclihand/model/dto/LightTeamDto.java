package com.pedrero.eclihand.model.dto;

import com.pedrero.eclihand.model.domain.Gender;

public class LightTeamDto extends IllustrableDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7393948532688578828L;

	private Integer year;

	private Gender gender;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

}
