package com.pedrero.eclihand.ui.panel;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.pedrero.eclihand.controller.security.ISecuredObject;
import com.pedrero.eclihand.controller.security.ISecurityRule;
import com.pedrero.eclihand.controller.security.SecuredComponent;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;

public abstract class EclihandAbstractComponent implements ISecuredObject {

	private static final String LIGHT_STYLE = "light";

	@Resource
	private EclihandLayoutFactory layoutFactory;

	private Layout mainLayout;

	private Layout wrapperLayout;

	private Layout rightDownLayout;

	private Layout leftDownLayout;

	private Panel mainComponent;

	@PostConstruct
	protected void postConstruct() {
		wrapperLayout = layoutFactory.createCommonVerticalLayout();
		mainLayout = layoutFactory.createCommonVerticalLayout();

		wrapperLayout.addComponent(mainLayout);

		HorizontalLayout buttonsLayout = layoutFactory.createCommonHorizontalLayout();
		rightDownLayout = layoutFactory.createCommonHorizontalLayout();
		leftDownLayout = layoutFactory.createCommonHorizontalLayout();

		wrapperLayout.addComponent(buttonsLayout);
		buttonsLayout.addComponent(leftDownLayout);
		buttonsLayout.setComponentAlignment(leftDownLayout, Alignment.MIDDLE_LEFT);
		buttonsLayout.addComponent(rightDownLayout);
		buttonsLayout.setComponentAlignment(rightDownLayout, Alignment.MIDDLE_RIGHT);

		mainComponent = new SecuredComponent() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -8217679170884277390L;

			@Override
			public ISecurityRule getSecurityRule() {
				return EclihandAbstractComponent.this.getSecurityRule();
			}

		};
		mainComponent.setStyleName(LIGHT_STYLE);
		mainComponent.setContent(wrapperLayout);
	}

	private ISecurityRule securityRule;

	@Override
	public ISecurityRule getSecurityRule() {
		return securityRule;
	}

	public void setSecurityRule(ISecurityRule securityRule) {
		this.securityRule = securityRule;
	}

	protected Layout getMainLayout() {
		return mainLayout;
	}

	public Panel getComponent() {
		return mainComponent;
	}

	public void addLeftDownComponent(Component component) {
		leftDownLayout.addComponent(component);
	}

	public void addRightDownComponent(Component component) {
		rightDownLayout.addComponent(component);
	}

}
