package shadows.plants.util;

import javax.annotation.Nonnull;

import shadows.plants.common.EnumModule;

public interface IModularThing {
	@Nonnull EnumModule getType();
}
