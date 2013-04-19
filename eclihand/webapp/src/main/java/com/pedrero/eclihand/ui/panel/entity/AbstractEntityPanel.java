package com.pedrero.eclihand.ui.panel.entity;

import java.util.List;

import javax.annotation.Resource;

import com.pedrero.eclihand.ui.panel.EclihandMainPanel;
import com.pedrero.eclihand.utils.UpdatableContentController;
import com.pedrero.eclihand.utils.UpdatableContentDisplayer;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Layout;

public abstract class AbstractEntityPanel extends EclihandMainPanel implements UpdatableContentDisplayer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7219745545618911623L;

	@Resource
	private EclihandUiFactory eclihandUiFactory;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	private Layout globalLayout;

	private Layout buttonsLayout;
	
	private Boolean updatable;
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

	private Boolean showButtons = true;

	private Boolean showDeleteButton = true;

	public AbstractEntityPanel() {
		super();
	}

	public AbstractEntityPanel(ComponentContainer content) {
		super(content);
	}

	public AbstractEntityPanel(String caption, ComponentContainer content) {
		super(caption, content);
	}

	public AbstractEntityPanel(String caption) {
		super(caption);
	}

	/**
	 * @return the updatable
	 */
	public Boolean getUpdatable() {
		return updatable;
	}

	/**
	 * @param updatable
	 *            the updatable to set
	 */
	public void setUpdatable(Boolean updatable) {
		this.updatable = updatable;
	}

	public void init() {
		updatable = false;

		globalLayout = eclihandLayoutFactory.createCommonVerticalLayout();
		buttonsLayout = eclihandLayoutFactory.createCommonHorizontalLayout();

		switchUpdateModeButton = eclihandUiFactory.createButton();
		validateChanges = eclihandUiFactory.createButton();
		delete = eclihandUiFactory.createButton();

		// Layouts
		this.setContent(globalLayout);
		globalLayout.addComponent(getMainLayout());
		globalLayout.addComponent(buttonsLayout);

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
					getController().makeReadOnly();
				} else {
					getController().makeUpdatable();
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
					getController().validateChanges();
					getController().makeReadOnly();
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
					getController().delete();
				}
			}
		});
	}

	public void makeUpdatable() {
		updatable = true;
		delete.setVisible(getShowButtons() && getShowDeleteButton());
		validateChanges.setVisible(getShowButtons());
		switchUpdateModeButton.setVisible(getShowButtons());

		if (getContentDisplayers() != null) {
			for (UpdatableContentDisplayer contentDisplayer : getContentDisplayers()) {
				contentDisplayer.makeUpdatable();
			}
		}
	}

	public void makeCreateMode() {
		updatable = true;
		delete.setVisible(false);
		validateChanges.setVisible(getShowButtons());
		switchUpdateModeButton.setVisible(false);

		if (getContentDisplayers() != null) {
			for (UpdatableContentDisplayer contentDisplayer : getContentDisplayers()) {
				contentDisplayer.makeCreateMode();
			}
		}
	}

	public void makeReadOnly() {
		updatable = false;
		delete.setVisible(false);
		validateChanges.setVisible(false);
		switchUpdateModeButton.setVisible(getShowButtons());

		if (getContentDisplayers() != null) {
			for (UpdatableContentDisplayer contentDisplayer : getContentDisplayers()) {
				contentDisplayer.makeReadOnly();
			}
		}
	}

	/**
	 * @return the buttonsLayout
	 */
	protected Layout getButtonsLayout() {
		return buttonsLayout;
	}

	/**
	 * @return the validateChanges
	 */
	protected Button getValidateChanges() {
		return validateChanges;
	}

	/**
	 * @return the delete
	 */
	protected Button getDelete() {
		return delete;
	}
	
	/**
	 * @return the switchUpdateModeButton
	 */
	protected Button getSwitchUpdateModeButton() {
		return switchUpdateModeButton;
	}

	public abstract List<UpdatableContentDisplayer> getContentDisplayers();

	public abstract UpdatableContentController getController();

	public abstract Layout getMainLayout();

	/**
	 * @return the showButtons
	 */
	protected Boolean getShowButtons() {
		return showButtons;
	}

	/**
	 * @param showButtons
	 *            the showButtons to set
	 */
	protected void setShowButtons(Boolean showButtons) {
		this.showButtons = showButtons;
	}

	/**
	 * @return the showDeleteButton
	 */
	protected Boolean getShowDeleteButton() {
		return showDeleteButton;
	}

	/**
	 * @param showDeleteButton
	 *            the showDeleteButton to set
	 */
	protected void setShowDeleteButton(Boolean showDeleteButton) {
		this.showDeleteButton = showDeleteButton;
	}

}