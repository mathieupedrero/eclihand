package com.pedrero.eclihand.converter.in;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component
public class ZonedDateTimeToDate implements Function<ZonedDateTime, Date> {

	@Override
	public Date apply(ZonedDateTime source) {
		if (source == null) {
			return null;
		}
		return Date.from(source.toInstant());
	}
}
