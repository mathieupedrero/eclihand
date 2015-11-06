package com.pedrero.eclihand.converter;

import java.io.Serializable;
import java.util.function.Function;

public interface Converter<F extends Serializable, T extends Serializable> extends Function<F, T> {
}
