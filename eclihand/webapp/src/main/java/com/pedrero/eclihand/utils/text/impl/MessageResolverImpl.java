package com.pedrero.eclihand.utils.text.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.utils.text.LocaleContainer;
import com.pedrero.eclihand.utils.text.MessageResolver;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public class MessageResolverImpl implements MessageResolver, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3832897924669087025L;

	@Resource
	private MessageSource messageSource;

	@Resource
	private LocaleContainer localeContainer;

	@Override
	public String getMessage(String key, Object... args) {
		return messageSource.getMessage(key, args, localeContainer.getLocale());
	}

	@Override
	public String getMessage(String key) {
		return messageSource.getMessage(key, null, localeContainer.getLocale());
	}

}
