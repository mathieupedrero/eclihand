package com.pedrero.eclihand.navigation.places;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.navigation.EclihandPlaceImpl;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyAccountPlace extends EclihandPlaceImpl {

	public MyAccountPlace() {
		super("my-account");
	}

	@Override
	protected void feedFromProperties(Map<String, String> properties) {
	}

	@Override
	protected Map<String, String> generateProperties() {
		return null;
	}

}
