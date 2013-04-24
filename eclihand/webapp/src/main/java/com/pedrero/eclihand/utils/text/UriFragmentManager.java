package com.pedrero.eclihand.utils.text;

import java.util.Map;

public interface UriFragmentManager {
	public abstract Map<String, String> parse(String fragment);

	public abstract String computeFragment(Map<String, String> properties);
}
