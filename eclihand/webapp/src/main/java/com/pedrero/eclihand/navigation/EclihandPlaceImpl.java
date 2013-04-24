package com.pedrero.eclihand.navigation;

import java.util.Map;

import javax.annotation.Resource;

import com.pedrero.eclihand.utils.text.UriFragmentManager;

public abstract class EclihandPlaceImpl implements EclihandPlace {
	@Resource
	private UriFragmentManager uriFragmentManager;

	@Override
	public final void feedFromFragment(String fragment) {
		feedFromProperties(uriFragmentManager.parse(fragment));
	}

	@Override
	public final String generateFragment() {
		return uriFragmentManager.computeFragment(generateProperties());
	}

	protected abstract void feedFromProperties(Map<String, String> properties);

	protected abstract Map<String, String> generateProperties();
}
