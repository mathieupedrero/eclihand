package com.pedrero.eclihand.navigation;

import java.util.List;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.ui.Panel;

public class EclihandNavigatorImpl implements EclihandNavigator {

	private static final String FRAGMENT_SEPARATOR = "/";

	private Navigator navigator;

	private Panel viewDisplayPanel;

	private List<ViewProvider> viewProviders;

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
	public void postConstruct() throws Exception {
		navigator = new Navigator(viewDisplayPanel.getUI(), viewDisplayPanel);
		for (ViewProvider viewProvider : viewProviders) {
			navigator.addProvider(viewProvider);
		}
	}

	public Panel getViewDisplayPanel() {
		return viewDisplayPanel;
	}

	public void setViewDisplayPanel(Panel viewDisplayPanel) {
		this.viewDisplayPanel = viewDisplayPanel;
	}

	public List<ViewProvider> getViewProviders() {
		return viewProviders;
	}

	public void setViews(List<ViewProvider> viewProviders) {
		this.viewProviders = viewProviders;
	}

}
