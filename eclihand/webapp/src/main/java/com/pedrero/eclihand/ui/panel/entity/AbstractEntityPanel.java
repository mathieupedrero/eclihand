package com.pedrero.eclihand.ui.panel.entity;

import java.util.List;

import javax.annotation.Resource;

import com.pedrero.eclihand.ui.panel.EclihandAbstractComponent;
import com.pedrero.eclihand.utils.UpdatableContentController;
import com.pedrero.eclihand.utils.UpdatableContentDisplayer;
import com.pedrero.eclihand.utils.UpdatableContentManager;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Layout;

public abstract class AbstractEntityPanel extends EclihandAbstractComponent implements
		UpdatableContentDisplayer {

	private String makeUpdatableCaptionKey = UpdatableContentManager.MAKE_UPDATABLE_KEY;

	private String discardChangesCaptionKey = UpdatableContentManager.DISCARD_CHANGES_KEY;

	private String validateChangesCaptionKey = UpdatableContentManager.VALIDATE_CHANGES_KEY;

	private String deleteCaptionKey = UpdatableContentManager.DELETE_KEY;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7219745545618911623L;

	@Resource
	private EclihandUiFactory eclihandUiFactory;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	@Resource
	private MessageResolver messageResolver;

	private Layout globalLayout;

	private Layout buttonsLayout;

	private Boolean updatable = false;
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
		globalLayout = eclihandLayoutFactory.createCommonVerticalLayout();
		buttonsLayout = eclihandLayoutFactory.createCommonHorizontalLayout();

		switchUpdateModeButton = eclihandUiFactory.createButton();
		switchUpdateModeButton.setCaption(messageResolver
				.getMessage(updatable ? makeUpdatableCaptionKey
						: discardChangesCaptionKey));
		validateChanges = eclihandUiFactory.createButton();
		validateChanges.setCaption(messageResolver
				.getMessage(validateChangesCaptionKey));
		delete = eclihandUiFactory.createButton();
		delete.setCaption(messageResolver.getMessage(deleteCaptionKey));

		// Layouts
		this.addComponent(globalLayout);
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

		if (updatable) {
			makeUpdatable();
		} else {
			makeReadOnly();
		}
	}

	public void makeUpdatable() {
		updatable = true;
		delete.setVisible(getShowButtons() && getShowDeleteButton());
		validateChanges.setVisible(getShowButtons());
		switchUpdateModeButton.setVisible(getShowButtons());
		switchUpdateModeButton.setCaption(messageResolver
				.getMessage(discardChangesCaptionKey));
	}

	public void makeCreateMode() {
		updatable = true;
		delete.setVisible(false);
		validateChanges.setVisible(getShowButtons());
		switchUpdateModeButton.setVisible(false);
	}

	public void makeReadOnly() {
		updatable = false;
		delete.setVisible(false);
		validateChanges.setVisible(false);
		switchUpdateModeButton.setVisible(getShowButtons());
		switchUpdateModeButton.setCaption(messageResolver
				.getMessage(makeUpdatableCaptionKey));
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
	public void setShowButtons(Boolean showButtons) {
		this.showButtons = showButtons;
	}

	/**
	 * @return the showDeleteButton
	 */
	public Boolean getShowDeleteButton() {
		return showDeleteButton;
	}

	/**
	 * @param showDeleteButton
	 *            the showDeleteButton to set
	 */
	public void setShowDeleteButton(Boolean showDeleteButton) {
		this.showDeleteButton = showDeleteButton;
	}

	/**
	 * @param makeUpdatableCaptionKey
	 *            the makeUpdatableCaptionKey to set
	 */
	public void setMakeUpdatableCaptionKey(String makeUpdatableCaptionKey) {
		this.makeUpdatableCaptionKey = makeUpdatableCaptionKey;
	}

	/**
	 * @param discardChangesCaptionKey
	 *            the discardChangesCaptionKey to set
	 */
	public void setDiscardChangesCaptionKey(String discardChangesCaptionKey) {
		this.discardChangesCaptionKey = discardChangesCaptionKey;
	}

	/**
	 * @param validateChangesCaptionKey
	 *            the validateChangesCaptionKey to set
	 */
	public void setValidateChangesCaptionKey(String validateChangesCaptionKey) {
		this.validateChangesCaptionKey = validateChangesCaptionKey;
	}

	/**
	 * @param deleteCaptionKey
	 *            the deleteCaptionKey to set
	 */
	public void setDeleteCaptionKey(String deleteCaptionKey) {
		this.deleteCaptionKey = deleteCaptionKey;
	}

}