package com.pedrero.eclihand.utils.text.impl;

import java.util.Locale;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.utils.text.LocaleContainer;

@Component
@Scope(proxyMode = ScopedProxyMode.INTERFACES, value = "session")
public class LocaleContainerImpl implements LocaleContainer {

	private Locale locale;

	@Override
	public Locale getLocale() {
		return locale;
	}

	@Override
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

}
