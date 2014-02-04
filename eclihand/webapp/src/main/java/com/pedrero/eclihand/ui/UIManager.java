package com.pedrero.eclihand.ui;

import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@org.springframework.stereotype.Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
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
