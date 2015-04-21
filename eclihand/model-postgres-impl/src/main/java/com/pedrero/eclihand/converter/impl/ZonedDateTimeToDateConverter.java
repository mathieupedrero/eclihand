package com.pedrero.eclihand.converter.impl;

import java.time.Clock;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;

import org.dozer.DozerConverter;

public class ZonedDateTimeToDateConverter extends DozerConverter<ZonedDateTime, Date> {

	public ZonedDateTimeToDateConverter(Class<ZonedDateTime> prototypeA, Class<Date> prototypeB) {
		super(prototypeA, prototypeB);
	}

	@Override
	public Date convertTo(ZonedDateTime source, Date destination) {
		if (source == null) {
			return null;
		}
		return Date.from(source.toInstant());
	}

	@Override
	public ZonedDateTime convertFrom(Date source, ZonedDateTime destination) {
		if (source == null) {
			return null;
		}
		return ZonedDateTime.ofInstant(Instant.ofEpochMilli(source.getTime()), Clock.systemUTC().getZone());
	}
}
