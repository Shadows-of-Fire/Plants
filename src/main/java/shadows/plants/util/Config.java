package shadows.plants.util;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import shadows.plants.proxy.CommonProxy;

public class Config {

	public static boolean debug = false;
	public static boolean Botania = true;
	public static boolean Cosmetic = true;
	public static boolean Tool = true;
	public static boolean disableVanillaFlowers = false;
	public static int vineFruitChance = 5;
	public static int patchchance = 6;
	public static int density = 8;
	public static int patchsize = 3;
	public static int quantity = 2;
	public static int numtries = 1;
	public static boolean literallyTakeoverFlowerForests = true;
	public static boolean allBushes = true;
	public static boolean needShears = false;
	public static int catapultPower = 5;
	public static boolean generation = true;

	public static void syncConfig() { // Gets called from preInit
		try {
			Property PDebug = CommonProxy.config.get(Configuration.CATEGORY_GENERAL, "Debug", "false",
					"Enable Debug Prints", Property.Type.BOOLEAN);

			Property PBotania = CommonProxy.config.get(Configuration.CATEGORY_GENERAL, "Botania", "true",
					"Enable the Botania (addon) module.", Property.Type.BOOLEAN);

			Property PCosmetic = CommonProxy.config.get(Configuration.CATEGORY_GENERAL, "Cosmetic", "true",
					"Enable the Cosmetic (main) module.", Property.Type.BOOLEAN);

			Property PTool = CommonProxy.config.get(Configuration.CATEGORY_GENERAL, "Tool", "true",
					"Enable the Tool (utility) module.", Property.Type.BOOLEAN);

			Property PDisableVanilla = CommonProxy.config.get(Configuration.CATEGORY_GENERAL, "DisableVanilla", "false",
					"(NYI) Disable vanilla flower generation.", Property.Type.BOOLEAN);

			Property PVineFruit = CommonProxy.config.get(Configuration.CATEGORY_GENERAL, "VineFruit", "5",
					"A (1/n) chance for vines to drop fruit when broken.", Property.Type.INTEGER);

			Property PPatchChance = CommonProxy.config.get("Generator Options", "Chance", "6",
					"A (1/n) chance for plants to try to generate on a given occasion. Lower = More plants.",
					Property.Type.INTEGER);

			Property PDensity = CommonProxy.config.get("Generator Options", "Density", "8",
					"The number of plants that try to generate on a given occasion. Higher = More plants.",
					Property.Type.INTEGER);

			Property PPatchSize = CommonProxy.config.get("Generator Options", "Size", "3",
					"The spread of plants in a given generation attempt. Higher = More plant spread.",
					Property.Type.INTEGER);

			Property PQuantity = CommonProxy.config.get("Generator Options", "Quantity", "2",
					"The number of plants per generation attempt. Higher = More plants. Use sparingly.",
					Property.Type.INTEGER);

			Property PTries = CommonProxy.config.get("Generator Options", "Tries", "1",
					"The number of times we decorate per decoration event. Do not make this very big.",
					Property.Type.INTEGER);

			Property PFlowerForests = CommonProxy.config.get("Generator Options", "FlowerForests", "true",
					"Whether or not I make flower forests great again.", Property.Type.BOOLEAN);

			Property PAllBushes = CommonProxy.config.get(Configuration.CATEGORY_GENERAL, "AllBushes", "true",
					"Whether or not Coagulated Plant Balls work on every single BlockBush that doesn't have a TileEntity.",
					Property.Type.BOOLEAN);

			Property PShears = CommonProxy.config.get(Configuration.CATEGORY_GENERAL, "Shears", "false",
					"Whether or not non-crops require shears to harvest.", Property.Type.BOOLEAN);

			Property PGeneration = CommonProxy.config.get("Generator Options", "EnableGeneration", "true",
					"Toggle for worldgen.", Property.Type.BOOLEAN);

			Property PCata = CommonProxy.config.get("Tool Module", "Catapult Power", "5",
					"How much strength the Cataplant has.", Property.Type.INTEGER);

			debug = PDebug.getBoolean();
			Botania = PBotania.getBoolean();
			Cosmetic = PCosmetic.getBoolean();
			vineFruitChance = Math.max(0, PVineFruit.getInt());
			patchchance = Math.max(1, PPatchChance.getInt());
			density = PDensity.getInt();
			patchsize = Math.max(1, PPatchSize.getInt());
			quantity = PQuantity.getInt();
			numtries = PTries.getInt();
			literallyTakeoverFlowerForests = PFlowerForests.getBoolean();
			allBushes = PAllBushes.getBoolean();
			needShears = PShears.getBoolean();
			Tool = PTool.getBoolean();
			generation = PGeneration.getBoolean();
			catapultPower = PCata.getInt();

		} catch (Exception e) {
			// Failed reading/writing, just continue
		} finally {
			// Save props to config IF config changed
			if (CommonProxy.config.hasChanged())
				CommonProxy.config.save();
		}

	}

}
