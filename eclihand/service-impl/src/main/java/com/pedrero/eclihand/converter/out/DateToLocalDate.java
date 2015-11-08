package com.pedrero.eclihand.converter.out;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.dozer.DozerConverter;

public class DateToLocalDate extends DozerConverter<LocalDate, Date> {

	public DateToLocalDate(Class<LocalDate> prototypeA, Class<Date> prototypeB) {
		super(prototypeA, prototypeB);
	}

	@Override
	public Date convertTo(LocalDate source, Date destination) {
		if (source == null) {
			return null;
		}
		Instant instant = source.atStartOfDay().atZone(Clock.systemUTC().getZone()).toInstant();
		return Date.from(instant);
	}

	@Override
	public LocalDate convertFrom(Date source, LocalDate destination) {
		if (source == null) {
			return null;
		}
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(source.getTime()), Clock.systemUTC().getZone())
				.toLocalDate();
	}
}
