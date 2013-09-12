package com.pedrero.eclihand.navigation;

public interface EclihandNavigator {

	public void navigateTo(EclihandView view);

	public void navigateTo(String fragment);

	public void navigateTo(String viewName, String parameters);

}