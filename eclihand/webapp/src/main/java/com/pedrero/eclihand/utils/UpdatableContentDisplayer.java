package com.pedrero.eclihand.utils;

public interface UpdatableContentDisplayer {

	static final String VALIDATE_CHANGES_KEY = "validate.changes";

	static final String DISCARD_CHANGES_KEY = "discard.changes";

	static final String MAKE_UPDATABLE_KEY = "make.updatable";

	public abstract void makeUpdatable();

	public abstract void makeReadOnly();

	public abstract void validateChanges();

}