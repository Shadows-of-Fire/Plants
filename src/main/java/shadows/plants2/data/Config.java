package shadows.plants2.data;

import net.minecraftforge.common.config.Configuration;

public class Config {

	public static boolean excalibur = true;
	public static boolean excaliburParty = false;
	public static boolean superExcaliburParty = false;

	public static boolean all_generation = true;
	public static boolean nether_flower_gen = true;
	public static boolean nether_tree_gen = true;
	public static boolean flower_gen = true;
	public static boolean bush_gen = true;
	public static boolean tree_gen = true;
	public static boolean vine_gen = true;
	public static boolean desert_gen = true;

	public static int patchChance = 1;
	public static int density = 6;
	public static int patchSize = 3;
	public static int quantity = 1;
	public static int numTries = 1;
	public static boolean literallyTakeoverFlowerForests = true;

	public static boolean allBushes = true;
	public static boolean needShears = false;
	public static int catapultPower = 2;
	public static boolean flowerpot = true;
	public static boolean harvests = true;
	public static int harvestGrowthChance = 10;
	public static int netherHarvestChance = 20;
	public static int cropGrowthChance = 15;

	public static boolean hardNether = false;

	public static void syncConfig(Configuration config) {

		config.load();

		String g = "Generator Options";
		String f = "Generator Options - Flowers";

		excalibur = config.getBoolean("Excalibur", "Botania", true, "Enable Excalibur.");
		excaliburParty = config.getBoolean("Rainbow Excalibur", "Botania", false, "Rainbow mana bursts!");
		superExcaliburParty = config.getBoolean("Super Rainbow Excalibur", "Botania", false, "(May cause issues) Super rainbow mana bursts!");

		all_generation = config.getBoolean("Enable Generation", g, true, "Toggle for worldgen.");
		nether_flower_gen = config.getBoolean("Nether Flower Generation", g, true, "Toggle for nether plants.");
		nether_tree_gen = config.getBoolean("Nether Tree Generation", g, true, "Toggle for nether trees.");
		flower_gen = config.getBoolean("Flower Generation", g, true, "Toggle for plants.");
		bush_gen = config.getBoolean("Bush Generation", g, true, "Toggle for bushes.");
		tree_gen = config.getBoolean("Tree Generation", g, true, "Toggle for trees.");
		vine_gen = config.getBoolean("Vine Generation", g, true, "Toggle for vines.");
		desert_gen = config.getBoolean("Desert Generation", g, true, "Toggle for desert plants.");

		patchChance = config.getInt("Chance", f, 1, 1, Integer.MAX_VALUE, "A (1/n) chance for plants to try to generate on a given occasion. Lower = More plants.");
		density = config.getInt("Density", f, 6, 0, 25, "The number of plants that try to generate on a given occasion. Higher = More plants.");
		patchSize = config.getInt("Size", f, 3, 1, 5, "The spread of plants in a given generation attempt. Higher = More plant spread.");
		quantity = config.getInt("Quantity", f, 1, 1, 5, "The number of plants per generation attempt. Higher = More plants.");
		numTries = config.getInt("Tries", f, 1, 1, 5, "The number of times we decorate per decoration event.");
		literallyTakeoverFlowerForests = config.getBoolean("Flower Forests", f, true, "Whether or not I make flower forests great again.");

		allBushes = config.getBoolean("All Bushes", "general", true, "Whether or not Coagulated Plant Balls work on every single BlockBush that doesn't have a TileEntity.");
		needShears = config.getBoolean("Shears", "general", false, "Whether or not non-crops require shears to harvest.");
		catapultPower = config.getInt("Catapult Power", "general", 2, 0, 15000, "How much strength the Cataplant has.");
		flowerpot = config.getBoolean("Enable Flowerpot", "general", true, "If enabled, the vanilla flowerpot is overridden with a much more versatile flowerpot.");
		harvests = config.getBoolean("Enable Harvests", "general", true, "If false, all harvestable bushes will not produce anything, and crops will be disabled.");
		harvestGrowthChance = config.getInt("Harvest Growth Chance", "general", 10, 1, 500, "Higher numbers will slow growth of harvestable bushes.");
		netherHarvestChance = config.getInt("Nether Growth Chance", "general", 20, 1, 500, "Higher numbers will slow growth of nether bushes.");
		cropGrowthChance = config.getInt("Crop Growth Chance", "general", 15, 1, 500, "Higher numbers will slow growth of crops.");
		
		hardNether = config.getBoolean("Require Soul Sand", "nether", false, "Enabling this will make all nether harvestables only grow on soul sand");

		if (config.hasChanged()) config.save();

	}

}
