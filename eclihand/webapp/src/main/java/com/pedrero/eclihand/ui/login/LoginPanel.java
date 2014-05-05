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
import com.pedrero.eclihand.ui.panel.EclihandAbstractComponent;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.ui.Button;
import com.vaadin.ui.Layout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoginPanel extends EclihandAbstractComponent {

	@Override
	public final Set<Credential> getRequiredCredentials() {
		return new HashSet<Credential>(Arrays.asList(Credential.CONNECT));
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6928146680402643741L;

	@Resource
	private MessageResolver messageResolver;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	@Resource
	private EclihandUiFactory eclihandUiFactory;

	@Override
	@PostConstruct
	public void postConstruct() {
		Layout layout = eclihandLayoutFactory.createCommonFormLayout();
		TextField loginField = eclihandUiFactory.createTextField();
		loginField.setCaption(messageResolver.getMessage("login.login"));
		layout.addComponent(loginField);
		PasswordField passwordField = eclihandUiFactory.createPasswordField();
		passwordField.setCaption(messageResolver.getMessage("login.password"));
		layout.addComponent(passwordField);
		Button logInButton = eclihandUiFactory.createButton();
		logInButton.setCaption(messageResolver.getMessage("login.log_in"));
		layout.addComponent(logInButton);
		this.addComponent(layout);
	}
}
