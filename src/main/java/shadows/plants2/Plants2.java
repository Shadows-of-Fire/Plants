package shadows.plants2;

import java.io.File;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import shadows.plants2.data.Config;
import shadows.plants2.data.Constants;
import shadows.plants2.data.IPostInitUpdate;
import shadows.plants2.gen.Decorator;
import shadows.plants2.gen.forgotten.BushGenerator;
import shadows.plants2.init.ModRegistry;
import shadows.plants2.proxy.IProxy;
import shadows.plants2.util.PlantUtil;

@Mod(modid = Constants.MODID, name = Constants.MODNAME, version = Constants.VERSION, dependencies = Constants.DEPS, acceptedMinecraftVersions = "[1.12, 1.13]")
public class Plants2 {

	@Instance
	public static Plants2 INSTANCE;

	@SidedProxy(clientSide = "shadows.plants2.proxy.ClientProxy", serverSide = "shadows.plants2.proxy.ServerProxy")
	public static IProxy PROXY;

	public static Configuration config;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(new ModRegistry());
		config = new Configuration(new File(e.getModConfigurationDirectory(), "plants.cfg"));
		Config.syncConfig(config);
		PROXY.preInit(e);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.TERRAIN_GEN_BUS.register(new Decorator());
		ModRegistry.oreDict(e);
		GameRegistry.registerWorldGenerator(new BushGenerator(), 25);
		PROXY.init(e);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		PROXY.postInit(e);
		for (IPostInitUpdate toUpdate : Constants.UPDATES) {
			toUpdate.postInit(e);
		}

		ModRegistry.ITEMS.clear();
		ModRegistry.BLOCKS.clear();
		ModRegistry.RECIPES.clear();
		ModRegistry.POTIONS.clear();
		Constants.UPDATES.clear();
		PlantUtil.mergeToDefaultLate();
	}
}
