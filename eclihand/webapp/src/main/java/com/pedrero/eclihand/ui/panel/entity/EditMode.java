package com.pedrero.eclihand.ui.panel.entity;

public enum EditMode {
	VIEW,
	EDIT,
	CREATE;
	
	public boolean isReadOnly(){
		return this == VIEW;
	}

}
