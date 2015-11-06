package com.pedrero.eclihand.converter.in;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import org.dozer.DozerConverter;

public class LocalTimeToDate extends DozerConverter<LocalTime, Date> {

	public LocalTimeToDate(Class<LocalTime> prototypeA, Class<Date> prototypeB) {
		super(prototypeA, prototypeB);
	}

	@Override
	public Date convertTo(LocalTime source, Date destination) {
		if (source == null) {
			return null;
		}
		Instant instant = source.atDate(LocalDate.ofEpochDay(0)).atZone(Clock.systemUTC().getZone()).toInstant();
		return Date.from(instant);
	}

	@Override
	public LocalTime convertFrom(Date source, LocalTime destination) {
		if (source == null) {
			return null;
		}
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(source.getTime()), Clock.systemUTC().getZone())
				.toLocalTime();
	}
}
