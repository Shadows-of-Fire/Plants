package shadows.plants.common;

import shadows.plants.util.Data;

public enum EnumModule {
	COSMETIC(Data.COSMETIC_ENABLED),
	BOTANICAL(Data.BOTANIA_ENABLED),
	TOOL(Data.TOOL_ENABLED),
	ARCANE(Data.ARCANE_ENABLED);

	private final boolean enabled;

	EnumModule(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isEnabled() {
		return enabled;
	}

}
