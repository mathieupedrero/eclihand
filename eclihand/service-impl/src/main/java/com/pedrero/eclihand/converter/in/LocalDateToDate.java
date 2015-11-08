package com.pedrero.eclihand.converter.in;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component
public class LocalDateToDate implements Function<LocalDate, Date> {

	@Override
	public Date apply(LocalDate source) {
		if (source == null) {
			return null;
		}
		Instant instant = source.atStartOfDay()
				.atZone(Clock.systemUTC().getZone()).toInstant();
		return Date.from(instant);
	}
}
