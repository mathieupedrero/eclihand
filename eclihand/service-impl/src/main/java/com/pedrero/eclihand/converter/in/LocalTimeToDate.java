package com.pedrero.eclihand.converter.in;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component
public class LocalTimeToDate implements Function<LocalTime, Date> {

	@Override
	public Date apply(LocalTime source) {
		if (source == null) {
			return null;
		}
		Instant instant = source.atDate(LocalDate.ofEpochDay(0))
				.atZone(Clock.systemUTC().getZone()).toInstant();
		return Date.from(instant);
	}
}
