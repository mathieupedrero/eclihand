package com.pedrero.eclihand.controller.panel;

import com.pedrero.eclihand.ui.panel.entity.AbstractEntityComponent;
import com.pedrero.eclihand.utils.UpdatableContentDisplayer;


public abstract class AbstractEntityController {

	public AbstractEntityController() {
		super();
	}

	public void makeUpdatable() {
		getEntityPanel().makeUpdatable();

		if (getEntityPanel().getContentDisplayers() != null) {
			for (UpdatableContentDisplayer contentDisplayer : getEntityPanel().getContentDisplayers()) {
				contentDisplayer.makeUpdatable();
			}
		}
	}

	public void makeReadOnly() {
		getEntityPanel().makeReadOnly();

		if (getEntityPanel().getContentDisplayers() != null) {
			for (UpdatableContentDisplayer contentDisplayer : getEntityPanel().getContentDisplayers()) {
				contentDisplayer.makeReadOnly();
			}
		}
	}

	public void makeCreateMode() {
		getEntityPanel().makeCreateMode();

		if (getEntityPanel().getContentDisplayers() != null) {
			for (UpdatableContentDisplayer contentDisplayer : getEntityPanel().getContentDisplayers()) {
				contentDisplayer.makeCreateMode();
			}
		}
	}

	public abstract AbstractEntityComponent getEntityPanel();

}