package com.pedrero.eclihand.ui.panel;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.navigation.EclihandPlace;
import com.pedrero.eclihand.navigation.EclihandViewImpl;
import com.pedrero.eclihand.navigation.places.WelcomePlace;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;

@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class HomeScreen extends EclihandViewImpl {

	@Resource
	private MessageResolver messageResolver;

	@Value(value = "${main.panel.width}")
	private String panelWidth;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	@Resource
	private EclihandUiFactory eclihandUiFactory;

	@Resource
	private WelcomePlace welcomePlace;

	@Resource
	private BeanFactory beanFactory;

	private Layout layout;

	/**
	 * 
	 */
	private static final long serialVersionUID = -499208820273916658L;

	private void init() {
		layout = eclihandLayoutFactory.createCommonVerticalLayout();
		layout.setWidth(panelWidth);
		Label label = eclihandUiFactory.createLabel();
		label.setValue("Accueil");
		layout.addComponent(label);
		this.setContent(layout);
		this.setCaption(messageResolver.getMessage("home.caption"));

	}

	@PostConstruct
	public void postConstruct() {
		init();
	}

	@Override
	public EclihandPlace retrieveAssociatedPlace() {
		return welcomePlace;
	}

}
