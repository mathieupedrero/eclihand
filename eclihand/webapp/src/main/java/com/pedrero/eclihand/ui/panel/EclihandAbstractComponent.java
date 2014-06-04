package com.pedrero.eclihand.ui.panel;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.pedrero.eclihand.controller.security.ISecuredObject;
import com.pedrero.eclihand.controller.security.ISecurityRule;
import com.pedrero.eclihand.model.domain.Credential;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;

public abstract class EclihandAbstractComponent implements ISecuredObject {

	@Resource
	private EclihandLayoutFactory layoutFactory;

	private Layout mainLayout;

	private Layout wrapperLayout;

	private Layout rightDownLayout;

	private Layout leftDownLayout;

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
	}

	private ISecurityRule securityRule;

	@Override
	public abstract Set<Credential> getRequiredCredentials();

	@Override
	public ISecurityRule getSecurityRule() {
		return securityRule;
	}

	public void setSecurityRule(ISecurityRule securityRule) {
		this.securityRule = securityRule;
	}

	public Layout getMainLayout() {
		return mainLayout;
	}

	public Layout getWrapperLayout() {
		return wrapperLayout;
	}

	public void addLeftDownComponent(Component component) {
		leftDownLayout.addComponent(component);
	}

	public void addRightDownComponent(Component component) {
		rightDownLayout.addComponent(component);
	}

}
