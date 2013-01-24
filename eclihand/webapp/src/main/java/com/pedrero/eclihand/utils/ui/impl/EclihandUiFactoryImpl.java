package com.pedrero.eclihand.utils.ui.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.utils.text.LocaleContainer;
import com.pedrero.eclihand.utils.ui.EclihandUiFactory;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.BaseTheme;

@Component
public class EclihandUiFactoryImpl implements EclihandUiFactory{
	
	@Resource
	private LocaleContainer localeContainer;
	
	@Override
	public Panel createMainPanel(){
		Panel panel = new Panel();
		panel.setLocale(localeContainer.getLocale());
		return panel;
	}
	
	@Override
	public Panel createSubPanel(){
		Panel panel = new Panel();
		panel.setLocale(localeContainer.getLocale());
		return panel;
	}
	
	@Override
	public Label createTitleLabel(){
		Label label = new Label();
		label.setLocale(localeContainer.getLocale());
		return label;
	}
	
	@Override
	public Label createSubTitleLabel(){
		Label label = new Label();
		label.setLocale(localeContainer.getLocale());
		return label;
	}
	
	@Override
	public Label createLabel(){
		Label label = new Label();
		label.setLocale(localeContainer.getLocale());
		return label;
	}
	
	@Override
	public Table createEntityTable(){
		Table table = new Table();
		table.setLocale(localeContainer.getLocale());
		return table;
	}
	
	@Override
	public TextField createTextField(){
		TextField textField = new TextField();
		textField.setLocale(localeContainer.getLocale());
		return textField;
	}
	
	@Override
	public DateField createDateField(){
		DateField dateField = new DateField();
		dateField.setLocale(localeContainer.getLocale());
		return dateField;
	}
	
	@Override
	public DateField createDateHourField(){
		DateField dateField = new DateField();
		dateField.setLocale(localeContainer.getLocale());
		return dateField;
	}
	
	@Override
	public ComboBox createComboBox(){
		ComboBox comboBox = new ComboBox();
		comboBox.setLocale(localeContainer.getLocale());
		return comboBox;
	}
	
	@Override
	public Button createButton(){
		Button button = new Button();
		button.setLocale(localeContainer.getLocale());
		return button;
	}

	@Override
	public Button createLinkButton() {
		Button button = new Button();
		button.setStyleName(BaseTheme.BUTTON_LINK);
		button.setLocale(localeContainer.getLocale());
		return button;
	}

	@Override
	public Link createSimpleLink() {
		Link link = new Link();
		link.setLocale(localeContainer.getLocale());
		return link;
	}

	@Override
	public ProgressIndicator createIndeterminateProgressIndicator() {
		ProgressIndicator progressIndicator = new ProgressIndicator();
		progressIndicator.setIndeterminate(true);
		progressIndicator.setEnabled(false);
		progressIndicator.setPollingInterval(1);
		return progressIndicator;
	}
	
}
