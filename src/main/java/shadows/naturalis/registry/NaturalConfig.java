package shadows.naturalis.registry;

import net.minecraftforge.common.config.Configuration;

public class NaturalConfig {

	public static boolean reqShears = false;

	public static void load(Configuration c) {
		reqShears = c.getBoolean("reqShears", "general", reqShears, "If Naturalis bushes will require shears to harvest by default.");
		if (c.hasChanged()) c.save();
	}
}
