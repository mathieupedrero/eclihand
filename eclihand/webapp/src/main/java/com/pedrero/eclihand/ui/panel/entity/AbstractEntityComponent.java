package com.pedrero.eclihand.ui.panel.entity;

import com.pedrero.eclihand.ui.panel.EclihandAbstractComponent;

public abstract class AbstractEntityComponent extends EclihandAbstractComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8108380773148533194L;

	private final EditMode editMode;

	public AbstractEntityComponent(EditMode editMode) {
		super();
		this.editMode = editMode;
	}

	public EditMode getEditMode() {
		return editMode;
	}
}