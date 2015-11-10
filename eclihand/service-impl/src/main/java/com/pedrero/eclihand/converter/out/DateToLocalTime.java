package com.pedrero.eclihand.converter.out;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component
public class DateToLocalTime implements Function<Date, LocalTime> {

	@Override
	public LocalTime apply(Date source) {
		if (source == null) {
			return null;
		}
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(source.getTime()), Clock.systemUTC().getZone())
				.toLocalTime();
	}
}
