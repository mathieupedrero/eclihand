package com.pedrero.eclihand.ui.panel.entity;

import javax.annotation.Resource;

import com.pedrero.eclihand.navigation.EclihandPlace;
import com.pedrero.eclihand.navigation.EclihandView;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Layout;

public abstract class AbstractEntityViewPanel extends AbstractEntityComponent implements EclihandView {

	public AbstractEntityViewPanel() {
		super(EditMode.VIEW);
	}

	private static final String MAKE_UPDATABLE_KEY = "common.make.updatable";

	private static final String DISCARD_CHANGES_KEY = "common.discard.changes";

	private static final String VALIDATE_CHANGES_KEY = "common.validate.changes";

	private static final String DELETE_KEY = "common.delete";

	@Resource
	private EclihandUiFactory eclihandUiFactory;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	@Resource
	private MessageResolver messageResolver;

	/**
	 * Button to switch to update mode
	 */
	private Button switchUpdateModeButton;

	/**
	 * Button to validate changes made in update mode
	 */
	private Button validateChanges;
	/**
	 * Button to validate changes made in update mode
	 */
	private Button delete;

	private Layout buttonsLayout;

	private final Boolean showButtons = true;

	private final Boolean updatable = false;

	private final Boolean showDeleteButton = true;

	/**
	 * 
	 */
	private static final long serialVersionUID = 9093227224467715454L;

	@Override
	public EclihandPlace retrieveAssociatedPlace() {
		return null;
	}

	public void makeUpdatable() {

		delete.setVisible(showButtons /* && getShowDeleteButton() */);
		validateChanges.setVisible(showButtons);
		switchUpdateModeButton.setVisible(showButtons);
		switchUpdateModeButton.setCaption(messageResolver.getMessage(DISCARD_CHANGES_KEY));
	}

	public void makeCreateMode() {
		delete.setVisible(false);
		validateChanges.setVisible(showButtons);
		switchUpdateModeButton.setVisible(false);
	}

	public void makeReadOnly() {
		delete.setVisible(false);
		validateChanges.setVisible(false);
		switchUpdateModeButton.setVisible(showButtons);
		switchUpdateModeButton.setCaption(messageResolver.getMessage(MAKE_UPDATABLE_KEY));
	}

	@Override
	protected void postConstruct() {
		super.postConstruct();

		switchUpdateModeButton = eclihandUiFactory.createButton();
		switchUpdateModeButton.setCaption(messageResolver.getMessage(updatable ? MAKE_UPDATABLE_KEY
				: DISCARD_CHANGES_KEY));
		validateChanges = eclihandUiFactory.createButton();
		validateChanges.setCaption(messageResolver.getMessage(VALIDATE_CHANGES_KEY));
		delete = eclihandUiFactory.createButton();
		delete.setCaption(messageResolver.getMessage(DELETE_KEY));

		buttonsLayout.addComponent(switchUpdateModeButton);
		buttonsLayout.addComponent(validateChanges);
		buttonsLayout.addComponent(delete);

		// switchUpdateModeButton.setCaption(messageResolver.getMessage(UpdatableContentManager.MAKE_UPDATABLE_KEY));
		switchUpdateModeButton.addClickListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -6563780592033942016L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (updatable) {
					makeReadOnly();
				} else {
					makeUpdatable();
				}
			}
		});

		// validateChanges.setCaption(messageResolver.getMessage(UpdatableContentManager.VALIDATE_CHANGES_KEY));
		validateChanges.addClickListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -6563780592033942016L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (updatable) {
					// validateChanges();
					makeReadOnly();
				}
			}
		});

		// delete.setCaption(messageResolver.getMessage(UpdatableContentManager.DELETE_KEY));
		delete.addClickListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -6563780592033942016L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (updatable) {
					// delete();
				}
			}
		});
	}

	@Override
	public void enter(ViewChangeEvent event) {
		retrieveAssociatedPlace().feedFromFragment(event.getParameters());
	}

}
