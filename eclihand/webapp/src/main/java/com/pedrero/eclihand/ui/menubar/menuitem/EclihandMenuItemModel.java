package com.pedrero.eclihand.ui.menubar.menuitem;

import java.util.Set;

import com.pedrero.eclihand.model.domain.Credential;

public class EclihandMenuItemModel {

	private String captionKey;

	private String actionFragment;

	private Set<Credential> requiredCredentials;

	public String getCaptionKey() {
		return captionKey;
	}

	public void setCaptionKey(String captionKey) {
		this.captionKey = captionKey;
	}

	public String getActionFragment() {
		return actionFragment;
	}

	public void setActionFragment(String actionFragment) {
		this.actionFragment = actionFragment;
	}

	public Set<Credential> getRequiredCredentials() {
		return requiredCredentials;
	}

	public void setRequiredCredentials(Set<Credential> requiredCredentials) {
		this.requiredCredentials = requiredCredentials;
	}

}
