package com.pedrero.eclihand.ui.custom.entity;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pedrero.eclihand.model.dto.PlayerDto;
import com.pedrero.eclihand.ui.custom.GenericPropertyDisplayer;
import com.pedrero.eclihand.ui.custom.config.PropertyDisplayerConfig;

@Component
public class PlayerPropertyDisplayer extends
		GenericPropertyDisplayer<PlayerDto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1322456053617449791L;

	@Resource(name = "playerPropertyDisplayerConfig")
	private PropertyDisplayerConfig config;

	@Override
	public PropertyDisplayerConfig getConfig() {
		return config;
	}

	public void setConfig(PropertyDisplayerConfig config) {
		this.config = config;
	}
}
