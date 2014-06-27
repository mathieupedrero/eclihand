package com.pedrero.eclihand.navigation;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.pedrero.eclihand.ui.panel.EclihandContentPanel;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewProvider;

public class EclihandNavigatorImpl implements EclihandNavigator {

	private static final String FRAGMENT_SEPARATOR = "/";

	private Navigator navigator;

	@Resource
	private EclihandContentPanel eclihandContentPanel;

	private List<ViewProvider> viewProviders;

	public EclihandNavigatorImpl() {
		super();
	}

	@Override
	public void navigateTo(EclihandPlace place) {
		navigator.navigateTo(place.generateViewState());

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
		navigator = new Navigator(eclihandContentPanel.getComponent().getUI(), eclihandContentPanel);
		for (ViewProvider viewProvider : viewProviders) {
			navigator.addProvider(viewProvider);
		}
	}

	public List<ViewProvider> getViewProviders() {
		return viewProviders;
	}

	public void setViews(List<ViewProvider> viewProviders) {
		this.viewProviders = viewProviders;
	}

}
