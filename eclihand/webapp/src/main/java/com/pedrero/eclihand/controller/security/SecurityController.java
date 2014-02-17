package com.pedrero.eclihand.controller.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;

import com.pedrero.eclihand.model.domain.Credential;
import com.pedrero.eclihand.model.dto.UserDto;
import com.pedrero.eclihand.service.UserService;
import com.pedrero.eclihand.ui.Authentication;
import com.pedrero.eclihand.ui.UIManager;
import com.vaadin.ui.Component;

@Controller
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SecurityController {

	@Resource
	private UIManager uiManager;

	@Resource
	private UserService userService;

	@Resource
	private Authentication currentAuthentication;

	private Boolean hasCredential(UserDto user, Credential credential) {
		if (credential == null) {
			return true;
		}
		return userService.retrieveCredentialsFor(user).contains(credential);
	}

	private Boolean hasCredentialIn(UserDto user,
			Collection<Credential> credentials) {
		if (credentials.isEmpty()) {
			return true;
		}
		return !Collections.disjoint(userService.retrieveCredentialsFor(user),
				credentials);
	}

	public Boolean userHasCredential(Credential credential) {
		return this.hasCredential(currentAuthentication.getAuthenticatedUser(),
				credential);
	}

	public Boolean userHasCredentialIn(Collection<Credential> credentials) {
		return this.hasCredentialIn(
				currentAuthentication.getAuthenticatedUser(), credentials);
	}

	public Boolean userHasCredentialIn(Credential... credentials) {
		return this.userHasCredentialIn(Arrays.asList(credentials));
	}

	public Boolean canBeShown(Component component) {
		if (component instanceof ISecuredObject) {
			ISecuredObject eclihandComponent = (ISecuredObject) component;
			return userHasCredentialIn(eclihandComponent
					.getRequiredCredentials());
		}
		return true;
	}

}
