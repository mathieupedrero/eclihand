package com.pedrero.eclihand.navigation;

public interface EclihandNavigator {

	void navigateTo(String fragment);

	void navigateTo(String viewName, String parameters);

	void navigateTo(EclihandPlace place);

}
