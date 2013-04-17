package com.pedrero.eclihand.utils;

public interface UpdatableContentDisplayer {

	static final String VALIDATE_CHANGES_KEY = "common.validate.changes";

	static final String DISCARD_CHANGES_KEY = "common.discard.changes";

	static final String MAKE_UPDATABLE_KEY = "common.make.updatable";

	static final String DELETE_KEY = "common.delete";

	public abstract void makeUpdatable();

	public abstract void makeReadOnly();

	public abstract void validateChanges();

}