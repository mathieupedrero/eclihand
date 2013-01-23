package com.pedrero.eclihand.ui.window;

import javax.annotation.Resource;

import com.pedrero.eclihand.controller.window.GenericSearchModalWindowController;
import com.pedrero.eclihand.model.dto.DataObjectDto;
import com.pedrero.eclihand.ui.table.GenericTable;
import com.pedrero.eclihand.utils.Identifiable;
import com.pedrero.eclihand.utils.Initiable;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public abstract class GenericSearchModalWindow<T extends DataObjectDto> extends
		Window
		implements Initiable, Identifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4962193726818757682L;

	@Resource
	private EclihandUiFactory eclihandUiFactory;
	
	private Label titleLabel;

	private TextField searchTextField;

	private Button searchButton;

	private Button validateButton;

	private Button cancelButton;
	
	private ProgressIndicator progressIndicator;

	private String id;

	@Override
	public void init() {
		this.setModal(true);

		getDisplayGenericTable().init();
		searchTextField = eclihandUiFactory.createTextField();

		validateButton = eclihandUiFactory.createButton();
		validateButton.addListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -1488315635459822992L;

			@Override
			public void buttonClick(ClickEvent event) {
				getController().validateChoice(
						getDisplayGenericTable().retrieveSelection());
				close();

			}
		});
		searchButton = eclihandUiFactory.createButton();
		searchButton.addListener(new ClickListener() {
			

			/**
			 * 
			 */
			private static final long serialVersionUID = -4346027959455235032L;

			@Override
			public void buttonClick(ClickEvent event) {
				getController().searchAndDisplay(
						searchTextField.getValue().toString());
			}
		});
		cancelButton = eclihandUiFactory.createButton();
		cancelButton.addListener(new ClickListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 4314433951375040900L;

			@Override
			public void buttonClick(ClickEvent event) {
				GenericSearchModalWindow.this.close();
			}
		});

		titleLabel = eclihandUiFactory.createTitleLabel();

		progressIndicator = eclihandUiFactory
				.createIndeterminateProgressIndicator();

		GridLayout searchFormLayout = new GridLayout(2, 1);
		searchFormLayout.addComponent(searchTextField);
		searchFormLayout.addComponent(searchButton);

		HorizontalLayout buttonsLayout = new HorizontalLayout();
		buttonsLayout.addComponent(validateButton);
		buttonsLayout.addComponent(cancelButton);

		this.setContent(new VerticalLayout());
		this.addComponent(titleLabel);
		this.addComponent(searchFormLayout);
		this.addComponent(getDisplayGenericTable());
		this.addComponent(buttonsLayout);
		this.addComponent(progressIndicator);
		
	}

	public abstract GenericTable<T> getDisplayGenericTable();

	public abstract GenericSearchModalWindowController<T> getController();

	public void feedTableWith(Iterable<T> objects) {
		getDisplayGenericTable().removeAllItems();
		getDisplayGenericTable().add(objects);
	}
	
	public GenericSearchModalWindow<T> getThis(){
		return this;
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
		if (! (obj instanceof Identifiable) )
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
