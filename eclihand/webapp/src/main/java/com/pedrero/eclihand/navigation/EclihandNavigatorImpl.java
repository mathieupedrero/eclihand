package com.pedrero.eclihand.navigation;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.InitializingBean;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Panel;

public class EclihandNavigatorImpl implements EclihandNavigator,
		InitializingBean {

	private static final String FRAGMENT_SEPARATOR = "/";

	private Navigator navigator;

	private Panel viewDisplayPanel;

	private Map<String, EclihandView> views;

	public EclihandNavigatorImpl() {
		super();
	}

	@Override
	public void navigateTo(EclihandView view) {
		navigator
				.navigateTo(view.retrieveAssociatedPlace().generateViewState());

	}

	@Override
	public void navigateTo(String navigationState) {
		navigator.navigateTo(navigationState);
	}

	@Override
	public void navigateTo(String viewName, String parameters) {
		navigator.navigateTo(viewName + FRAGMENT_SEPARATOR + parameters);

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		navigator = new Navigator(viewDisplayPanel.getUI(), viewDisplayPanel);
		for (Entry<String, EclihandView> viewAssociation : views.entrySet()) {
			navigator.addView(viewAssociation.getKey(),
					viewAssociation.getValue());
		}
	}

	/**
	 * @return the viewDisplayPanel
	 */
	public Panel getViewDisplayPanel() {
		return viewDisplayPanel;
	}

	/**
	 * @param viewDisplayPanel
	 *            the viewDisplayPanel to set
	 */
	public void setViewDisplayPanel(Panel viewDisplayPanel) {
		this.viewDisplayPanel = viewDisplayPanel;
	}

	/**
	 * @return the views
	 */
	public Map<String, EclihandView> getViews() {
		return views;
	}

	/**
	 * @param views
	 *            the views to set
	 */
	public void setViews(Map<String, EclihandView> views) {
		this.views = views;
	}

}
