package com.pedrero.eclihand.ui.login;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.model.domain.Credential;
import com.pedrero.eclihand.service.UserService;
import com.pedrero.eclihand.ui.Authentication;
import com.pedrero.eclihand.ui.UIManager;
import com.pedrero.eclihand.ui.panel.EclihandAbstractComponent;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Layout;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogoutPanel extends EclihandAbstractComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6928146680402643741L;

	@Override
	public final Set<Credential> getRequiredCredentials() {
		return new HashSet<Credential>(Arrays.asList(Credential.OWN_TEAM_EDIT));
	}

	@Resource
	private MessageResolver messageResolver;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	@Resource
	private EclihandUiFactory eclihandUiFactory;

	@Resource
	private UserService userService;

	@Resource
	private Authentication authentication;

	@Resource
	private UIManager uiManager;

	@Override
	@PostConstruct
	public void postConstruct() {
		Layout layout = eclihandLayoutFactory.createCommonFormLayout();
		Button logOutButton = eclihandUiFactory.createButton();
		logOutButton.setCaption(messageResolver.getMessage("login.log_out"));
		logOutButton.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				authentication.setAuthenticatedUser(userService
						.retrieveGuestUser());
				uiManager.refreshFrameElements();
			}
		});
		layout.addComponent(logOutButton);
		this.addComponent(layout);
	}
}
