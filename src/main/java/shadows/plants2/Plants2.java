package shadows.plants2;

import java.io.File;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
import shadows.plants2.compat.ForestryIntegration;
import shadows.plants2.data.Config;
import shadows.plants2.data.Constants;
import shadows.plants2.data.IPostInitUpdate;
import shadows.plants2.gen.Decorator;
import shadows.plants2.init.ModRegistry;
import shadows.plants2.proxy.IProxy;
import shadows.plants2.util.PlantUtil;

@Mod(modid = Constants.MODID, name = Constants.MODNAME, version = Constants.VERSION, dependencies = Constants.DEPS, acceptedMinecraftVersions = "[1.12, 1.13]")
public class Plants2 {

	@Instance
	public static Plants2 instance;

	@SidedProxy(clientSide = "shadows.plants2.proxy.ClientProxy", serverSide = "shadows.plants2.proxy.ServerProxy")
	public static IProxy proxy;

	public static Configuration config;

	public static final Logger LOGGER = LogManager.getLogger("Plants");

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(new ModRegistry());
		config = new Configuration(new File(e.getModConfigurationDirectory(), "plants.cfg"));
		Config.syncConfig(config);
		proxy.preInit(e);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.TERRAIN_GEN_BUS.register(new Decorator());
		ModRegistry.oreDict(e);
		proxy.init(e);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		ModRegistry.generators(e);

		for (IPostInitUpdate toUpdate : Constants.UPDATES) {
			toUpdate.postInit(e);
		}

		proxy.postInit(e);
		LOGGER.log(Level.INFO, String.format("Plants is using %d block ids and %d item ids", ModRegistry.BLOCKS.size(), ModRegistry.ITEMS.size()));
		ModRegistry.ITEMS.clear();
		ModRegistry.BLOCKS.clear();
		ModRegistry.RECIPES.clear();
		ModRegistry.POTIONS.clear();
		Constants.UPDATES.clear();
		PlantUtil.mergeToDefaultLate();
		
		if (Loader.isModLoaded(Constants.FORESTRY_ID))
			ForestryIntegration.registerFlowersToForestry();
	}
}
