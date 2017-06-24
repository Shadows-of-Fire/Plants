package shadows.plants.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import shadows.plants.registry.GlobalRegistry;
import shadows.plants.registry.OredictRegistry;
import shadows.plants.util.Config;
import shadows.plants.util.Decorator;

public class CommonProxy {

	public static Configuration config;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		config = new Configuration(e.getSuggestedConfigurationFile());
		Config.syncConfig();
		GlobalRegistry.preInit(e);
		MinecraftForge.EVENT_BUS.register(new GlobalRegistry());
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.TERRAIN_GEN_BUS.register(Decorator.class);
		OredictRegistry.init(e);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {

	}

}
