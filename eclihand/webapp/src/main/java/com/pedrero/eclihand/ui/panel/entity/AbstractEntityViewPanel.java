package com.pedrero.eclihand.ui.panel.entity;

import static com.pedrero.eclihand.controller.security.SecurityRuleUtils.userHas;

import javax.annotation.Resource;

import com.pedrero.eclihand.controller.security.ISecurityRule;
import com.pedrero.eclihand.model.domain.Credential;
import com.pedrero.eclihand.model.exception.EclihandRuntimeException;
import com.pedrero.eclihand.navigation.AbstractEclihandView;
import com.pedrero.eclihand.navigation.EclihandPlace;
import com.pedrero.eclihand.navigation.EclihandView;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;

public abstract class AbstractEntityViewPanel extends AbstractEclihandView implements EclihandView {

	private static final String MODIFY_KEY = "common.modify";

	@Resource
	private EclihandUiFactory eclihandUiFactory;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	@Resource
	private MessageResolver messageResolver;

	protected ISecurityRule ruleToEdit() {
		return userHas(Credential.FORBIDDEN);
	}

	protected Component editComponent() {
		throw new EclihandRuntimeException();
	};

	/**
	 * 
	 */
	private static final long serialVersionUID = 9093227224467715454L;

	@Override
	protected void postConstruct() {
		super.postConstruct();
		Button editButton = eclihandUiFactory.createButton(ruleToEdit());
		editButton.setCaption(messageResolver.getMessage(MODIFY_KEY));
		editButton.addClickListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 7681596058867795285L;

			@Override
			public void buttonClick(ClickEvent event) {
				AbstractEntityViewPanel.this.getComponent().getUI()
						.addWindow(eclihandUiFactory.createModalWindow(editComponent()));
			}
		});
		this.addRightDownComponent(editButton);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		retrieveAssociatedPlace().feedFromFragment(event.getParameters());
	}

	@Override
	public EclihandPlace retrieveAssociatedPlace() {
		return null;
	}

}
