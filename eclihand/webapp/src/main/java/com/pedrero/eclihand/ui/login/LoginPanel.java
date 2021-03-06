package com.pedrero.eclihand.ui.login;

import static com.pedrero.eclihand.controller.security.SecurityRuleUtils.userHasOneOf;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.security.ISecurityRule;
import com.pedrero.eclihand.model.domain.Credential;
import com.pedrero.eclihand.navigation.AbstractEclihandComponent;
import com.pedrero.eclihand.service.biz.UserService;
import com.pedrero.eclihand.service.runtime.exception.BadCredentialsException;
import com.pedrero.eclihand.ui.Authentication;
import com.pedrero.eclihand.ui.UIManager;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoginPanel extends AbstractEclihandComponent {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginPanel.class);

	@Override
	public ISecurityRule buildSecurityRule() {
		return userHasOneOf(Credential.CONNECT);
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
	public void postConstruct() {
		super.postConstruct();
		Layout layout = eclihandLayoutFactory.createCommonFormLayout();
		final TextField loginField = eclihandUiFactory.createTextField();
		loginField.setCaption(messageResolver.getMessage("login.login"));
		layout.addComponent(loginField);
		final PasswordField passwordField = eclihandUiFactory.createPasswordField();
		passwordField.setCaption(messageResolver.getMessage("login.password"));
		layout.addComponent(passwordField);
		Button logInButton = eclihandUiFactory.createButton();
		logInButton.setCaption(messageResolver.getMessage("login.log_in"));
		logInButton.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 6580065112238446374L;

			@Override
			public void buttonClick(ClickEvent event) {
				String md5Password = null;
				MessageDigest messageDigest = null;
				try {
					messageDigest = MessageDigest.getInstance("SHA-1");
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				byte[] result = new byte[messageDigest.getDigestLength()];
				messageDigest.reset();
				messageDigest.update(passwordField.getValue().getBytes());
				result = messageDigest.digest();

				StringBuffer buf = new StringBuffer(result.length * 2);

				for (int i = 0; i < result.length; i++) {
					int intVal = result[i] & 0xff;
					if (intVal < 0x10) {
						buf.append("0");
					}
					buf.append(Integer.toHexString(intVal));
				}

				md5Password = buf.toString();

				try {
					userService.checkCredentials(loginField.getValue(), md5Password);
					LOGGER.info("Logged in pour {} - {}", loginField.getValue(), md5Password);
					// authentication.setAuthenticatedUser(loggedIn);
					// uiManager.refreshFrameElements();
				} catch (BadCredentialsException e) {
					LOGGER.info("Erreur de login pour {} - {}", loginField.getValue(), md5Password);
				}
			}
		});
		layout.addComponent(logInButton);
		this.getMainLayout().addComponent(layout);
	}
}
