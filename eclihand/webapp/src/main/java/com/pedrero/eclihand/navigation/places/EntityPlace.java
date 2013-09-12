package com.pedrero.eclihand.navigation.places;

import java.util.Map;

public abstract class EntityPlace extends IdentityPlace {

	public EntityPlace(String placeName) {
		super(placeName);
	}

	public final static String NEW_ENTITY_PROPERTY_FLAG = "new";

	public final static String UPDATE_ENTITY_PROPERTY_FLAG = "upd";

	private Boolean createMode;

	private Boolean updateMode;

	@Override
	protected void feedFromProperties(Map<String, String> properties) {
		super.feedFromProperties(properties);
		String isNew = properties.get(NEW_ENTITY_PROPERTY_FLAG);
		setCreateMode(isNew == null ? false : Boolean.parseBoolean(isNew));
		String isUpdate = properties.get(UPDATE_ENTITY_PROPERTY_FLAG);
		setUpdateMode(isUpdate == null ? false : Boolean.parseBoolean(isUpdate));
	}

	@Override
	protected Map<String, String> generateProperties() {
		Map<String, String> propertiesMap = super.generateProperties();
		if (getCreateMode()) {
			propertiesMap.put(NEW_ENTITY_PROPERTY_FLAG, getCreateMode()
					.toString());
		}
		if (getUpdateMode()) {
			propertiesMap.put(UPDATE_ENTITY_PROPERTY_FLAG, getUpdateMode()
					.toString());
		}
		return propertiesMap;
	}

	/**
	 * @return the createMode
	 */
	protected Boolean getCreateMode() {
		return createMode;
	}

	/**
	 * @param createMode
	 *            the createMode to set
	 */
	protected void setCreateMode(Boolean createMode) {
		this.createMode = createMode;
	}

	/**
	 * @return the updateMode
	 */
	public Boolean getUpdateMode() {
		return updateMode;
	}

	/**
	 * @param updateMode
	 *            the updateMode to set
	 */
	public void setUpdateMode(Boolean updateMode) {
		this.updateMode = updateMode;
	}

}
