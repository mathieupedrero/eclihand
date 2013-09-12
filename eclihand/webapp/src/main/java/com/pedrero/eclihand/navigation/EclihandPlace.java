package com.pedrero.eclihand.navigation;

public interface EclihandPlace {

	/**
	 * Feeds the place from fragment string, to handle view parameters
	 * 
	 * @param fragment
	 */
	public abstract void feedFromFragment(String fragment);

	/**
	 * Generates fragment from view parameters
	 * 
	 * @return the view parameters fragment
	 */
	public abstract String generateFragment();

	/**
	 * Generates view state from view parameters. this method should invoke the
	 * generateFragment method to handle parameters.
	 * 
	 * @return the fragment
	 */
	public abstract String generateViewState();

}
