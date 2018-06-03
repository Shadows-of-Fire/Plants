package shadows.plants2.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.config.Configuration;

public class PlantConfig {

	public static boolean excalibur = true;
	public static boolean excaliburParty = false;
	public static boolean superExcaliburParty = false;

	public static boolean gen = true;
	public static boolean netherFlowerGen = true;
	public static boolean netherTreeGen = true;
	public static boolean flowerGen = true;
	public static boolean bushGen = true;
	public static boolean treeGen = true;
	public static boolean vineGen = true;
	public static boolean desertGen = true;
	public static boolean endGen = true;

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

	public static final ObjectList<ResourceLocation> REGNAME_BL = new ObjectArrayList<>();
	public static final ObjectList<String> MODID_BL = new ObjectArrayList<>();
	public static final ObjectList<Integer> DIM_BL = new ObjectArrayList<>();
	public static final ObjectList<ResourceLocation> BIOME_BL = new ObjectArrayList<>();
	public static final ObjectList<Biome> COMPUTED_BIOME_BL = new ObjectArrayList<>();

	public static boolean crystalForest = true;
	public static int crystalForestWeight = 2;

	public static int aphrirerootChance = 5;

	public static void syncConfig(Configuration config) {

		String g = "Generator Options";
		String f = "Generator Options - Flowers";

		excalibur = config.getBoolean("Excalibur", "Botania", true, "Enable Excalibur.");
		excaliburParty = config.getBoolean("Rainbow Excalibur", "Botania", false, "Rainbow mana bursts!");
		superExcaliburParty = config.getBoolean("Super Rainbow Excalibur", "Botania", false, "(May cause issues) Super rainbow mana bursts!");

		gen = config.getBoolean("Enable Generation", g, true, "Toggle for worldgen.");
		netherFlowerGen = config.getBoolean("Nether Flower Generation", g, true, "Toggle for nether plants.");
		netherTreeGen = config.getBoolean("Nether Tree Generation", g, true, "Toggle for nether trees.");
		flowerGen = config.getBoolean("Flower Generation", g, true, "Toggle for plants.");
		bushGen = config.getBoolean("Bush Generation", g, true, "Toggle for bushes.");
		treeGen = config.getBoolean("Tree Generation", g, true, "Toggle for trees.");
		vineGen = config.getBoolean("Vine Generation", g, true, "Toggle for vines.");
		desertGen = config.getBoolean("Desert Generation", g, true, "Toggle for desert plants.");
		endGen = config.getBoolean("End Generation", g, true, "Toggle for end plants.");

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

		String[] pb = config.getStringList("Plantball Blacklist", "plantball", new String[0], "A list of blacklisted registry names, or modids.  Strings without a \":\" will be treated as modids.");

		for (String s : pb) {
			if (s.contains(":")) REGNAME_BL.add(new ResourceLocation(s));
			else MODID_BL.add(s);
		}

		String[] dimbl = config.getStringList("Dimension Blacklist", g, new String[0], "A list of dimension IDs that Plants will not try to generate in.");
		for (String s : dimbl)
			DIM_BL.add(Integer.parseInt(s));

		crystalForest = config.getBoolean("Crystal Forest", "biomes", true, "Toggle for the Crystal Forest");
		crystalForestWeight = config.getInt("Crystal Forest Weight", "biomes", 2, 1, 2000, "The weight of the Crystal Forest.  Higher is more common.");

		aphrirerootChance = config.getInt("Aphrireroot Pearl Chance", "general", 5, 1, 15, "The (1/n) chance for an Aphrireroot to make a pearl.");

		String[] biomebl = config.getStringList("Biome Blacklist", g, new String[] { "minecraft:river" }, "A list of biomes that Plants will not try to generate in.");
		for (String s : biomebl)
			BIOME_BL.add(new ResourceLocation(s));
	}

}
