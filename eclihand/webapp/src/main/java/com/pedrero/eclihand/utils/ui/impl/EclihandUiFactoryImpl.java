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
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;

@Component
public class EclihandUiFactoryImpl implements EclihandUiFactory{
	
	@Resource
	private LocaleContainer localeContainer;
	
	public Panel createMainPanel(){
		Panel panel = new Panel();
		panel.setLocale(localeContainer.getLocale());
		return panel;
	}
	
	public Panel createSubPanel(){
		Panel panel = new Panel();
		panel.setLocale(localeContainer.getLocale());
		return panel;
	}
	
	public Label createTitleLabel(){
		Label label = new Label();
		label.setLocale(localeContainer.getLocale());
		return label;
	}
	
	public Label createSubTitleLabel(){
		Label label = new Label();
		label.setLocale(localeContainer.getLocale());
		return label;
	}
	
	public Label createLabel(){
		Label label = new Label();
		label.setLocale(localeContainer.getLocale());
		return label;
	}
	
	public Table createEntityTable(){
		Table table = new Table();
		table.setLocale(localeContainer.getLocale());
		return table;
	}
	
	public TextField createTextField(){
		TextField textField = new TextField();
		textField.setLocale(localeContainer.getLocale());
		return textField;
	}
	
	public DateField createDateField(){
		DateField dateField = new DateField();
		dateField.setLocale(localeContainer.getLocale());
		return dateField;
	}
	
	public DateField createDateHourField(){
		DateField dateField = new DateField();
		dateField.setLocale(localeContainer.getLocale());
		return dateField;
	}
	
	public ComboBox createComboBox(){
		ComboBox comboBox = new ComboBox();
		comboBox.setLocale(localeContainer.getLocale());
		return comboBox;
	}
	
	public Button createButton(){
		Button button = new Button();
		button.setLocale(localeContainer.getLocale());
		return button;
	}

	@Override
	public Link createSimpleLink() {
		Link link = new Link();
		link.setLocale(localeContainer.getLocale());
		return link;
	}
	
}
