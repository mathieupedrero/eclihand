package com.pedrero.eclihand.navigation;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.pedrero.eclihand.ui.panel.EclihandContentPanel;
import com.vaadin.navigator.Navigator;

public class EclihandNavigatorImpl implements EclihandNavigator {

	private static final String FRAGMENT_SEPARATOR = "/";

	@Resource
	private EclihandContentPanel contentPanel;

	private Navigator navigator;

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

	@PostConstruct
	public void afterPropertiesSet() throws Exception {
		navigator = new Navigator(contentPanel.getUI(), contentPanel);
		for (Entry<String, EclihandView> viewAssociation : views.entrySet()) {
			navigator.addView(viewAssociation.getKey(),
					viewAssociation.getValue());
		}
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
