package com.pedrero.eclihand.navigation.places;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TeamPlace extends EntityPlace {

	public TeamPlace() {
		super("team");
	}

}
