package com.pedrero.eclihand.converter.out;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component
public class DateToLocalDate implements Function<Date, LocalDate> {
	@Override
	public LocalDate apply(Date source) {
		if (source == null) {
			return null;
		}
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(source.getTime()), Clock.systemUTC().getZone())
				.toLocalDate();
	}
}
