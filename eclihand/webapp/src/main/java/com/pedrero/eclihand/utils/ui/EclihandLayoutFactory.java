package com.pedrero.eclihand.utils.ui;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public interface EclihandLayoutFactory {

	public VerticalLayout createCommonVerticalLayout();

	public HorizontalLayout createCommonHorizontalLayout();

	public GridLayout createCommonGridLayout(int columns, int rows);

	public FormLayout createFormLayout();

}
