package com.pedrero.eclihand.model.domain.factory;

import com.pedrero.eclihand.model.domain.Authorization;
import com.pedrero.eclihand.model.domain.Person;
import com.pedrero.eclihand.model.domain.Player;
import com.pedrero.eclihand.model.domain.Profile;
import com.pedrero.eclihand.model.domain.Team;
import com.pedrero.eclihand.model.domain.User;

public interface DomainObjectsFactory {
	Authorization createAuthorization();

	Person createPerson();

	Player createPlayer();

	User createUser();

	Profile createProfile();

	Team createTeam();
}
