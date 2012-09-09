package com.pedrero.eclihand.utils.text;


public interface MessageResolver {

	public abstract String getMessage(String key, Object... args);

	public abstract String getMessage(String key);

}