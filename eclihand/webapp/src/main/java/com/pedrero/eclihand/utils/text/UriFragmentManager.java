package com.pedrero.eclihand.utils.text;

import java.util.Map;

public interface UriFragmentManager {
	public abstract Map<String, String> parse(String propertyFragment);

	public abstract String computePropertyFragment(Map<String, String> properties);
}
