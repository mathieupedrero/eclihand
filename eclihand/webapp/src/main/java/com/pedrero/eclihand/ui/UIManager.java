package com.pedrero.eclihand.ui;

import java.util.Set;

public class UIManager {

	private Set<IFrameElement> elements;

	public void refreshFrameElements() {
		for (IFrameElement frameElement : elements) {
			frameElement.refresh();
		}
	}

	public Set<IFrameElement> getElements() {
		return elements;
	}

	public void setElements(Set<IFrameElement> elements) {
		this.elements = elements;
	}

}
