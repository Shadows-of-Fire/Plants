package shadows.plants2.data;

import net.minecraftforge.common.config.Configuration;

public class Config {

	public static boolean excalibur = true;

	public static boolean generation = true;
	public static int patchChance = 1;
	public static int density = 6;
	public static int patchSize = 3;
	public static int quantity = 1;
	public static int numTries = 1;
	public static boolean literallyTakeoverFlowerForests = true;

	public static boolean allBushes = true;
	public static boolean needShears = false;

	public static int catapultPower = 5;

	public static void syncConfig(Configuration config) {

		config.load();

		excalibur = config.getBoolean("Addons", "Botania", true, "Enable the Excalibur.");

		generation = config.getBoolean("Generator Options", "EnableGeneration", true, "Toggle for worldgen.");
		patchChance = config.getInt("Generator Options", "Chance", 1, 1, Integer.MAX_VALUE, "A (1/n) chance for plants to try to generate on a given occasion. Lower = More plants.");
		density = config.getInt("Generator Options", "Density", 6, 0, 25, "The number of plants that try to generate on a given occasion. Higher = More plants.");
		patchSize = config.getInt("Generator Options", "Size", 3, 1, 5, "The spread of plants in a given generation attempt. Higher = More plant spread.");
		quantity = config.getInt("Generator Options", "Quantity", 1, 0, 5, "The number of plants per generation attempt. Higher = More plants. Use sparingly.");
		numTries = config.getInt("Generator Options", "Tries", 1, 0, 5, "The number of times we decorate per decoration event. Do not make this very big.");
		literallyTakeoverFlowerForests = config.getBoolean("Generator Options", "FlowerForests", true, "Whether or not I make flower forests great again.");

		allBushes = config.getBoolean(Configuration.CATEGORY_GENERAL, "AllBushes", true, "Whether or not Coagulated Plant Balls work on every single BlockBush that doesn't have a TileEntity.");
		needShears = config.getBoolean(Configuration.CATEGORY_GENERAL, "Shears", false, "Whether or not non-crops require shears to harvest.");

		catapultPower = config.getInt("Tool Module", "Catapult Power", 5, 0, 400, "How much strength the Cataplant has.");

		if (config.hasChanged())
			config.save();

	}

}
