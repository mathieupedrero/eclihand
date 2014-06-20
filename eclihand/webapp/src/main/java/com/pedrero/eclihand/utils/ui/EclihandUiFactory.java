package com.pedrero.eclihand.utils.ui;

import com.pedrero.eclihand.controller.security.ISecurityRule;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

/**
 * Factory that creates Vaadin ui objects.
 * 
 * @author mathieu.pedrero
 * 
 */
public interface EclihandUiFactory {

	Panel createMainPanel();

	Panel createSubPanel();

	Label createTitleLabel();

	Label createSubTitleLabel();

	Label createLabel();

	Table createEntityTable();

	TextField createTextField();

	/**
	 * Creates a Vaadin {@link PasswordField}
	 * 
	 * @return
	 */
	PasswordField createPasswordField();

	DateField createDateField();

	DateField createDateHourField();

	ComboBox createComboBox();

	Button createButton();

	/**
	 * Creates a button secured by the specified rule.
	 * 
	 * @param rule
	 * @return
	 */
	Button createButton(ISecurityRule rule);

	Button createLinkButton();

	Link createSimpleLink();

	ProgressIndicator createIndeterminateProgressIndicator();

	Window createModalWindow(Component bodyComponent);

}
