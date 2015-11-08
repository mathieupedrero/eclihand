package com.pedrero.eclihand.converter;

import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.pedrero.eclihand.model.domain.DataObject;
import com.pedrero.eclihand.model.dto.DataObjectDto;

public class ConverterUtils {
	private ConverterUtils() {
	}

	public static <T> void map(Supplier<T> from, Consumer<T> to) {
		to.accept(from.get());
	}

	public static <T, U> void map(Supplier<T> from, Consumer<U> to,
			Function<T, U> converter) {
		T t = from.get();
		if (t != null) {
			to.accept(converter.apply(t));
		}
	}

	public static <T, U> Function<Set<T>, Set<U>> setConverter(
			Function<T, U> baseFunction) {
		return s -> s.stream().map(baseFunction).collect(Collectors.toSet());
	}

	public static <T extends DataObjectDto, U extends DataObject> Function<Set<T>, Set<U>> fromRepository(
			Function<Long, U> baseFunction) {
		return s -> s.stream().map(baseFunction).collect(Collectors.toSet());
	}

}
