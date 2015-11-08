package com.pedrero.eclihand.model.domain.factory;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.model.domain.Authorization;
import com.pedrero.eclihand.model.domain.AuthorizationImpl;
import com.pedrero.eclihand.model.domain.Person;
import com.pedrero.eclihand.model.domain.PersonImpl;
import com.pedrero.eclihand.model.domain.Player;
import com.pedrero.eclihand.model.domain.PlayerImpl;
import com.pedrero.eclihand.model.domain.Profile;
import com.pedrero.eclihand.model.domain.ProfileImpl;
import com.pedrero.eclihand.model.domain.Team;
import com.pedrero.eclihand.model.domain.TeamImpl;
import com.pedrero.eclihand.model.domain.User;
import com.pedrero.eclihand.model.domain.UserImpl;

@Component
public class DomainObjectsFactoryImpl implements DomainObjectsFactory {

	@Override
	public Authorization createAuthorization() {
		return new AuthorizationImpl();
	}

	@Override
	public Person createPerson() {
		return new PersonImpl();
	}

	@Override
	public Player createPlayer() {
		return new PlayerImpl();
	}

	@Override
	public User createUser() {
		return new UserImpl();
	}

	@Override
	public Profile createProfile() {
		return new ProfileImpl();
	}

	@Override
	public Team createTeam() {
		return new TeamImpl();
	}

}
