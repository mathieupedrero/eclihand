package com.pedrero.eclihand.ui.panel;

import javax.annotation.Resource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.navigation.AbstractEclihandView;
import com.pedrero.eclihand.navigation.EclihandPlace;
import com.pedrero.eclihand.navigation.places.WelcomePlace;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.ui.Label;

@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class HomeScreen extends AbstractEclihandView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -499208820273916658L;

	@Resource
	private MessageResolver messageResolver;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	@Resource
	private EclihandUiFactory eclihandUiFactory;

	@Resource
	private WelcomePlace welcomePlace;

	@Resource
	private BeanFactory beanFactory;

	@Override
	public void postConstruct() {
		super.postConstruct();
		Label label = eclihandUiFactory.createLabel();
		label.setValue("Accueil");
		this.getMainLayout().addComponent(label);
		this.getComponent().setCaption(messageResolver.getMessage("home.caption"));
	}

	@Override
	public EclihandPlace retrieveAssociatedPlace() {
		return welcomePlace;
	}

}
