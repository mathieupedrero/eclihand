package com.pedrero.eclihand.navigation;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.utils.text.UriFragmentManager;

@Component
@Scope("prototype")
public abstract class EclihandPlaceImpl implements EclihandPlace {

	private final String placeName;

	public EclihandPlaceImpl(String placeName) {
		super();
		this.placeName = placeName;
	}

	@Autowired
	private UriFragmentManager uriFragmentManager;

	/**
	 * @return the uriFragmentManager
	 */
	public UriFragmentManager getUriFragmentManager() {
		return uriFragmentManager;
	}

	/**
	 * @param uriFragmentManager
	 *            the uriFragmentManager to set
	 */
	public void setUriFragmentManager(UriFragmentManager uriFragmentManager) {
		this.uriFragmentManager = uriFragmentManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pedrero.eclihand.navigation.EclihandPlace#feedFromFragment(java.lang
	 * .String)
	 */
	@Override
	public void feedFromFragment(String fragment) {
		feedFromProperties(getUriFragmentManager().parse(fragment));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pedrero.eclihand.navigation.EclihandPlace#generateFragment()
	 */
	@Override
	public String generateFragment() {
		return getUriFragmentManager().computePropertyFragment(
				generateProperties());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pedrero.eclihand.navigation.EclihandPlace#generateViewState()
	 */
	@Override
	public String generateViewState() {
		return getPlaceName() + '/' + generateFragment();
	}

	protected abstract void feedFromProperties(Map<String, String> properties);

	protected abstract Map<String, String> generateProperties();

	public String getPlaceName() {
		return placeName;
	}
}
