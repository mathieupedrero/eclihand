package com.pedrero.eclihand.ui.window;

import javax.annotation.Resource;

import com.pedrero.eclihand.controller.window.GenericSearchModalWindowController;
import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.ui.table.GenericTable;
import com.pedrero.eclihand.utils.Identifiable;
import com.pedrero.eclihand.utils.Initiable;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class GenericSearchModalWindow<T extends DataObjectDto> extends Window
		implements Initiable, Identifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4962193726818757682L;

	private String id;

	@Resource
	private EclihandUiFactory eclihandUiFactory;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	@Resource
	private MessageResolver messageResolver;

	private Label titleLabel;

	private TextField searchTextField;

	private Button searchButton;

	private Button validateButton;

	private Button cancelButton;

	private ProgressIndicator progressIndicator;

	private GenericTable<T> displayGenericTable;

	// Fields fed by spring configuration

	private GenericSearchModalWindowController<T> searchModalWindowController;

	private String captionKey;

	private String titleKey;

	private String cancelButtonKey;

	private String validateButtonKey;

	private String searchButtonKey;

	private Layout layout;

	@Override
	public void init() {
		layout = eclihandLayoutFactory.createCommonVerticalLayout();
		this.setContent(layout);
		this.setModal(true);

		getDisplayGenericTable().init();
		searchTextField = eclihandUiFactory.createTextField();

		initValidateButton();
		initSearchButton();
		initCancelButton();

		titleLabel = eclihandUiFactory.createTitleLabel();

		progressIndicator = eclihandUiFactory
				.createIndeterminateProgressIndicator();

		GridLayout searchFormLayout = eclihandLayoutFactory
				.createCommonGridLayout(2, 1);
		searchFormLayout.addComponent(searchTextField);
		searchFormLayout.addComponent(searchButton);

		searchFormLayout.setSizeUndefined();

		HorizontalLayout buttonsLayout = eclihandLayoutFactory
				.createCommonHorizontalLayout();
		buttonsLayout.addComponent(validateButton);
		buttonsLayout.addComponent(cancelButton);

		layout.addComponent(titleLabel);
		layout.addComponent(searchFormLayout);
		layout.addComponent(getDisplayGenericTable());
		layout.addComponent(buttonsLayout);
		layout.addComponent(progressIndicator);

		initLabels();
	}

	private void initCancelButton() {
		cancelButton = eclihandUiFactory.createButton();
		cancelButton.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 4314433951375040900L;

			@Override
			public void buttonClick(ClickEvent event) {
				GenericSearchModalWindow.this.close();
			}
		});
	}

	private void initSearchButton() {
		searchButton = eclihandUiFactory.createButton();
		searchButton.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -4346027959455235032L;

			@Override
			public void buttonClick(ClickEvent event) {
				getSearchModalWindowController().searchAndDisplay(
						searchTextField.getValue().toString());
			}
		});
		searchButton.setClickShortcut(KeyCode.ENTER);
	}

	private void initValidateButton() {
		validateButton = eclihandUiFactory.createButton();
		validateButton.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -1488315635459822992L;

			@Override
			public void buttonClick(ClickEvent event) {
				getSearchModalWindowController().validateChoice(
						getDisplayGenericTable().retrieveSelection());
				close();

			}
		});
	}

	private void initLabels() {
		this.setCaption(messageResolver.getMessage(captionKey));
		this.getTitleLabel().setCaption(messageResolver.getMessage(titleKey));
		this.getCancelButton().setCaption(
				messageResolver.getMessage(cancelButtonKey));
		this.getValidateButton().setCaption(
				messageResolver.getMessage(validateButtonKey));
		this.getSearchButton().setCaption(
				messageResolver.getMessage(searchButtonKey));
	}

	public GenericTable<T> getDisplayGenericTable() {
		return displayGenericTable;
	}

	public void setDisplayGenericTable(GenericTable<T> displayGenericTable) {
		this.displayGenericTable = displayGenericTable;
	}

	public GenericSearchModalWindowController<T> getSearchModalWindowController() {
		return searchModalWindowController;
	}

	public void setSearchModalWindowController(
			GenericSearchModalWindowController<T> searchModalWindowController) {
		this.searchModalWindowController = searchModalWindowController;
	}

	public void feedTableWith(Iterable<T> objects) {
		getDisplayGenericTable().removeAllDataObjects();
		getDisplayGenericTable().add(objects);
	}

	public Label getTitleLabel() {
		return titleLabel;
	}

	public Button getSearchButton() {
		return searchButton;
	}

	public Button getValidateButton() {
		return validateButton;
	}

	public Button getCancelButton() {
		return cancelButton;
	}

	public ProgressIndicator getProgressIndicator() {
		return progressIndicator;
	}

	public void setProgressIndicator(ProgressIndicator progressIndicator) {
		this.progressIndicator = progressIndicator;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getCaptionKey() {
		return captionKey;
	}

	public void setCaptionKey(String captionKey) {
		this.captionKey = captionKey;
	}

	public String getTitleKey() {
		return titleKey;
	}

	public void setTitleKey(String titleKey) {
		this.titleKey = titleKey;
	}

	public String getCancelButtonKey() {
		return cancelButtonKey;
	}

	public void setCancelButtonKey(String cancelButtonKey) {
		this.cancelButtonKey = cancelButtonKey;
	}

	public String getValidateButtonKey() {
		return validateButtonKey;
	}

	public void setValidateButtonKey(String validateButtonKey) {
		this.validateButtonKey = validateButtonKey;
	}

	public String getSearchButtonKey() {
		return searchButtonKey;
	}

	public void setSearchButtonKey(String searchButtonKey) {
		this.searchButtonKey = searchButtonKey;
	}

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Identifiable))
			return false;
		Identifiable other = (Identifiable) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

}
