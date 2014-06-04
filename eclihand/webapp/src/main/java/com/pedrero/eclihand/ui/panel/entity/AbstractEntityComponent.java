package com.pedrero.eclihand.ui.panel.entity;

import com.pedrero.eclihand.ui.panel.EclihandAbstractComponent;

public abstract class AbstractEntityComponent extends EclihandAbstractComponent {

	private final EditMode editMode;

	public AbstractEntityComponent(EditMode editMode) {
		super();
		this.editMode = editMode;
	}

	public EditMode getEditMode() {
		return editMode;
	}
}