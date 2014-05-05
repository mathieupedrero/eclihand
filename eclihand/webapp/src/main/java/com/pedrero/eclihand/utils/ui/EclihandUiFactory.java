package com.pedrero.eclihand.utils.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;

/**
 * Factory that creates Vaadin ui objects.
 * 
 * @author mathieu.pedrero
 * 
 */
public interface EclihandUiFactory {

	public Panel createMainPanel();

	public Panel createSubPanel();

	public Label createTitleLabel();

	public Label createSubTitleLabel();

	public Label createLabel();

	public Table createEntityTable();

	public TextField createTextField();

	/**
	 * Creates a Vaadin {@link PasswordField}
	 * 
	 * @return
	 */
	public PasswordField createPasswordField();

	public DateField createDateField();

	public DateField createDateHourField();

	public ComboBox createComboBox();

	public Button createButton();

	public Button createLinkButton();

	public Link createSimpleLink();

	public ProgressIndicator createIndeterminateProgressIndicator();

}
