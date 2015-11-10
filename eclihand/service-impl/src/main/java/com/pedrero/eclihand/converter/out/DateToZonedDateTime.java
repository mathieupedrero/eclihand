package com.pedrero.eclihand.converter.out;

import java.time.Clock;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.function.Function;

public class DateToZonedDateTime implements Function<Date, ZonedDateTime> {

	@Override
	public ZonedDateTime apply(Date source) {
		if (source == null) {
			return null;
		}
		return ZonedDateTime.ofInstant(Instant.ofEpochMilli(source.getTime()), Clock.systemUTC().getZone());
	}
}
