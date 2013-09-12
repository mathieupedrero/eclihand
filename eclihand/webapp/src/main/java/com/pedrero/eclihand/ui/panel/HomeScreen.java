package com.pedrero.eclihand.ui.panel;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.panel.HomePanelController;
import com.pedrero.eclihand.navigation.EclihandPlace;
import com.pedrero.eclihand.navigation.EclihandViewImpl;
import com.pedrero.eclihand.navigation.places.WelcomePlace;
import com.pedrero.eclihand.utils.Initiable;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.vaadin.ui.Layout;

@Component(value = "homeScreen")
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class HomeScreen extends EclihandViewImpl implements Initiable,
		InitializingBean {

	@Resource
	private HomePanelController homePanelController;

	@Resource
	private MessageResolver messageResolver;

	@Value(value = "${main.panel.width}")
	private String panelWidth;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	@Resource
	private WelcomePlace welcomePlace;

	private Layout layout;

	/**
	 * 
	 */
	private static final long serialVersionUID = -499208820273916658L;

	private void init() {
		layout = eclihandLayoutFactory.createCommonVerticalLayout();
		layout.setWidth(panelWidth);
		// this.setUiComponent(layout);
		this.setContent(layout);
		this.setCaption(messageResolver.getMessage("home.caption"));

	}

	@Override
	public void display() {

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}

	@Override
	public EclihandPlace retrieveAssociatedPlace() {
		return welcomePlace;
	}

}
