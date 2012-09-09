package com.pedrero.eclihand.ui.config;

import java.util.Date;

public enum EnumType {
	STRING(String.class), LONG(Long.class), INTEGER(Integer.class), DATE(
			Date.class);
	private Class<?> type;

	private EnumType(Class<?> type) {
		this.type = type;
	}

	public Class<?> getType() {
		return type;
	}
}
