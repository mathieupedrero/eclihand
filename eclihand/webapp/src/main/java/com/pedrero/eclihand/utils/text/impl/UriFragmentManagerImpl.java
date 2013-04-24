package com.pedrero.eclihand.utils.text.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.utils.text.UriFragmentManager;

@Component
public class UriFragmentManagerImpl implements UriFragmentManager {
	private static final String PROPERTIES_REGEX = "(((/;)|[^;])*);?";
	private static final Pattern PROPERTIES_PATTERN = Pattern.compile(PROPERTIES_REGEX);
	private static final String PROPERTY_REGEX = "(((/=)|[^=])*)=(((/=)|[^=])*)";
	private static final Pattern PROPERTY_PATTERN = Pattern.compile(PROPERTY_REGEX);

	private static final String UNESCAPE_CHARACTERS_REGEX = "/([/=;])";
	private static final Pattern UNESCAPE_CHARACTERS_PATTERN = Pattern.compile(UNESCAPE_CHARACTERS_REGEX);

	private static final String ESCAPE_CHARACTERS_REGEX = "/([/=;])";
	private static final Pattern ESCAPE_CHARACTERS_PATTERN = Pattern.compile(ESCAPE_CHARACTERS_REGEX);

	@Override
	public Map<String, String> parse(String fragment) {
		Map<String, String> properties = new HashMap<String, String>();

		Matcher parsingMatcher = PROPERTIES_PATTERN.matcher(fragment);

		while (parsingMatcher.find()) {
			String property = parsingMatcher.group(1);

			Matcher propertyMatcher = PROPERTY_PATTERN.matcher(property);
			if (propertyMatcher.find()) {
				properties.put(unescape(propertyMatcher.group(1)), unescape(propertyMatcher.group(4)));
			}
		}
		return properties;
	}

	@Override
	public String computeFragment(Map<String, String> properties) {
		if (properties == null) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		for (Entry<String, String> property : properties.entrySet()) {
			builder.append(escape(property.getKey())).append('=').append(escape(property.getValue())).append(';');
		}
		return builder.toString();
	}

	private String unescape(String toUnescape){
		return UNESCAPE_CHARACTERS_PATTERN.matcher(toUnescape).replaceAll("$1");
	}

	private String escape(String toUnescape) {
		return ESCAPE_CHARACTERS_PATTERN.matcher(toUnescape).replaceAll("/$1");
	}

}
