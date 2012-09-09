package com.pedrero.eclihand.utils.text.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.utils.text.Formatter;
import com.pedrero.eclihand.utils.text.LocaleContainer;

@Component
public class SeasonFormatter implements Formatter {
	@Resource
	private LocaleContainer localeContainer;

	@Override
	public String format(Object object) {
		Integer seasonYear = (Integer) object;
		return (seasonYear - 1) + "/" + seasonYear;
	}

}
