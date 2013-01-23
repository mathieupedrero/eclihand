package com.pedrero.eclihand.utils.ui.impl;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@Component
public class EclihandLayoutFactoryImpl implements EclihandLayoutFactory {

	@Override
	public VerticalLayout createCommonVerticalLayout() {
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeUndefined();
		layout.setMargin(true);
		return layout;
	}

	@Override
	public HorizontalLayout createCommonHorizontalLayout() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSizeUndefined();
		layout.setMargin(true);
		return layout;
	}

	@Override
	public GridLayout createCommonGridLayout(int columns, int rows) {
		GridLayout layout = new GridLayout(columns, rows);
		layout.setSizeUndefined();
		layout.setMargin(true);
		return layout;
	}

}
