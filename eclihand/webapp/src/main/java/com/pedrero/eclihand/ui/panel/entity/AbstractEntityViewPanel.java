package com.pedrero.eclihand.ui.panel.entity;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;

import com.pedrero.eclihand.model.domain.Credential;
import com.pedrero.eclihand.navigation.EclihandView;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;

public abstract class AbstractEntityViewPanel extends AbstractEntityComponent implements EclihandView {

	public AbstractEntityViewPanel() {
		super(EditMode.VIEW);
	}

	private static final String MODIFY_KEY = "common.modify";

	@Resource
	private EclihandUiFactory eclihandUiFactory;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	@Resource
	private MessageResolver messageResolver;

	protected Set<Credential> credetialsToEdit() {
		HashSet<Credential> set = new HashSet<Credential>();
		set.add(Credential.FORBIDDEN);
		return set;
	}

	protected Component editComponent() {
		throw new NotImplementedException();
	};

	/**
	 * 
	 */
	private static final long serialVersionUID = 9093227224467715454L;

	@Override
	protected void postConstruct() {
		super.postConstruct();
		Button editButton = eclihandUiFactory.createButton(credetialsToEdit());
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

}
