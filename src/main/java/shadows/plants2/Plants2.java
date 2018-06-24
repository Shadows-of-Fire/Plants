package shadows.plants2;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import shadows.placebo.registry.RegistryInformationV2;
import shadows.placebo.util.RecipeHelper;
import shadows.plants2.compat.CrafttweakerIntegration;
import shadows.plants2.compat.ForestryIntegration;
import shadows.plants2.data.PlantConfig;
import shadows.plants2.data.PlantConstants;
import shadows.plants2.gen.Decorator;
import shadows.plants2.init.ModRegistry;
import shadows.plants2.network.ParticleMessage;
import shadows.plants2.network.ParticleMessage.ParticleMessageHandler;
import shadows.plants2.proxy.IProxy;
import shadows.plants2.util.PlantUtil;

@Mod(modid = Plants2.MODID, name = Plants2.MODNAME, version = Plants2.VERSION, dependencies = Plants2.DEPS, acceptedMinecraftVersions = "[1.12, 1.13)")
public class Plants2 {

	public static final String MODID = "plants2";
	public static final String MODNAME = "Plants";
	public static final String VERSION = "2.9.1";
	public static final String DEPS = "required-after:placebo@[1.3.4,);after:botania@[r1.10-354,);after:forestry;after:inspirations";

	@Instance
	public static Plants2 INSTANCE;

	@SidedProxy(clientSide = "shadows.plants2.proxy.ClientProxy", serverSide = "shadows.plants2.proxy.ServerProxy")
	public static IProxy PROXY;

	public static Configuration CONFIG;
	public static Configuration BLOCK_CONFIG;

	public static final Logger LOGGER = LogManager.getLogger("Plants");

	public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
	private static int disc = 0;

	public static final RegistryInformationV2 INFO = new RegistryInformationV2(MODID, PlantConstants.TAB);

	public static final RecipeHelper HELPER = new RecipeHelper(MODID, MODNAME, INFO.getRecipeList());

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		CONFIG = new Configuration(new File(e.getModConfigurationDirectory(), "plants.cfg"));
		BLOCK_CONFIG = new Configuration(new File(e.getModConfigurationDirectory(), "plants_blocks.cfg"));
		CONFIG.load();
		MinecraftForge.EVENT_BUS.register(new ModRegistry());
		PlantConfig.syncConfig(CONFIG);
		ModRegistry.tiles(e);
		PROXY.preInit(e);
		if (CONFIG.hasChanged()) CONFIG.save();
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		MinecraftForge.TERRAIN_GEN_BUS.register(new Decorator());
		ModRegistry.oreDict(e);
		PROXY.init(e);
		NETWORK.registerMessage(ParticleMessageHandler.class, ParticleMessage.class, disc++, Side.CLIENT);
		if (Loader.isModLoaded("crafttweaker")) CrafttweakerIntegration.processCauldronChanges();
		for (ResourceLocation rl : PlantConfig.BIOME_BL) {
			Biome b = ForgeRegistries.BIOMES.getValue(rl);
			if (b == null) LOGGER.error("Invalid biome entry detected in the Plants biome blacklist, " + rl);
			else PlantConfig.COMPUTED_BIOME_BL.add(b);
		}
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		ModRegistry.generators(e);

		PROXY.postInit(e);
		LOGGER.info(String.format("Plants is using %d block ids and %d item ids", INFO.getBlockList().size(), INFO.getItemList().size()));
		INFO.purge();
		PlantUtil.mergeToDefaultLate();

		if (Loader.isModLoaded(PlantConstants.FORESTRY_ID)) ForestryIntegration.registerFlowersToForestry();
		if (BLOCK_CONFIG.hasChanged()) BLOCK_CONFIG.save();

		if (PlantConfig.crystalForest) BiomeDictionary.addTypes(ModRegistry.CRYSTAL_FOREST, Type.MAGICAL, Type.FOREST, Type.COLD);
	}
}
