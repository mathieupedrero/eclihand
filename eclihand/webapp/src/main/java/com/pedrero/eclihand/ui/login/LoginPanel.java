package com.pedrero.eclihand.ui.login;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

@Component(value = "teamPanel")
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoginPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6928146680402643741L;

	private final TextField loginTextField = new TextField("login");

	private final PasswordField passwordField = new PasswordField("password");

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	@PostConstruct
	public void postConstruct() {
		Layout layout = eclihandLayoutFactory.createCommonFormLayout();
		layout.addComponent(loginTextField);
		layout.addComponent(passwordField);
		this.setContent(layout);
	}
}
