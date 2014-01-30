package com.pedrero.eclihand.utils.ui.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.security.SecurityComponentAttachListener;
import com.pedrero.eclihand.controller.security.SecurityController;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@Component
public class EclihandLayoutFactoryImpl implements EclihandLayoutFactory {

	@Resource
	private SecurityController securityController;

	@Override
	public VerticalLayout createCommonVerticalLayout() {
		VerticalLayout layout = new VerticalLayout();
		layout.addComponentAttachListener(new SecurityComponentAttachListener(
				securityController, layout));
		layout.setSizeUndefined();
		layout.setMargin(true);
		return layout;
	}

	@Override
	public HorizontalLayout createCommonHorizontalLayout() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.addComponentAttachListener(new SecurityComponentAttachListener(
				securityController, layout));
		layout.setSizeUndefined();
		layout.setMargin(true);
		return layout;
	}

	@Override
	public GridLayout createCommonGridLayout(int columns, int rows) {
		GridLayout layout = new GridLayout(columns, rows);
		layout.addComponentAttachListener(new SecurityComponentAttachListener(
				securityController, layout));
		layout.setSizeUndefined();
		layout.setMargin(true);
		return layout;
	}

	@Override
	public FormLayout createCommonFormLayout() {
		FormLayout formLayout = new FormLayout();
		formLayout
				.addComponentAttachListener(new SecurityComponentAttachListener(
						securityController, formLayout));
		return formLayout;
	}
}
