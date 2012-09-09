package com.pedrero.eclihand.utils.text.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.utils.text.Formatter;
import com.pedrero.eclihand.utils.text.LocaleContainer;

@Component
public class EclihandDateFormatter implements Formatter {
	@Resource
	private LocaleContainer localeContainer;

	@Override
	public String format(Object object) {
		DateFormatter form = new DateFormatter();
		return form.print((Date) object, localeContainer.getLocale());
	}

}
