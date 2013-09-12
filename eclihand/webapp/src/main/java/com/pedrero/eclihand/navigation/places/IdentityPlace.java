package com.pedrero.eclihand.navigation.places;

import java.util.HashMap;
import java.util.Map;

import com.pedrero.eclihand.navigation.EclihandPlaceImpl;

public abstract class IdentityPlace extends EclihandPlaceImpl {

	public IdentityPlace(String placeName) {
		super(placeName);
	}

	public final static String ID_PROPERTY_FLAG = "id";

	private Long id;

	@Override
	protected void feedFromProperties(Map<String, String> properties) {
		String id = properties.get(ID_PROPERTY_FLAG);
		setId(id == null ? null : Long.parseLong(id));
	}

	@Override
	protected Map<String, String> generateProperties() {
		Map<String, String> propertiesMap = new HashMap<String, String>();
		if (getId() != null) {
			propertiesMap.put(ID_PROPERTY_FLAG, getId().toString());
		}
		return propertiesMap;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

}
