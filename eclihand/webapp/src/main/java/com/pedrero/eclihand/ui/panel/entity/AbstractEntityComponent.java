package com.pedrero.eclihand.ui.panel.entity;

import com.pedrero.eclihand.navigation.AbstractEclihandComponent;

public abstract class AbstractEntityComponent extends AbstractEclihandComponent {

	private final EditMode editMode;

	public AbstractEntityComponent(EditMode editMode) {
		super();
		this.editMode = editMode;
	}

	public EditMode getEditMode() {
		return editMode;
	}
}