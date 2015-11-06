package com.pedrero.eclihand.converter;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ConverterUtils {
	public static final ConverterUtils INSTANCE = new ConverterUtils();

	private ConverterUtils() {
	}

	public <T> void map(Supplier<T> from, Consumer<T> to) {
		to.accept(from.get());
	}

	public <T, U> void map(Supplier<T> from, Consumer<U> to, Function<T, U> converter) {
		T t = from.get();
		if (t != null) {
			to.accept(converter.apply(t));
		}
	}

	public <T, U> void mapSet(Supplier<Set<T>> from, Consumer<Set<U>> to, Function<T, U> converter) {
		Set<T> t = from.get();
		if (t != null) {
			to.accept(t.stream().map(converter).collect(Collectors.toSet()));
		}
	}

	public <T, U> void mapList(Supplier<List<T>> from, Consumer<List<U>> to, Function<T, U> converter) {
		List<T> t = from.get();
		if (t != null) {
			to.accept(t.stream().map(converter).collect(Collectors.toList()));
		}
	}

}
