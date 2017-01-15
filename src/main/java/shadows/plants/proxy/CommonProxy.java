package shadows.plants.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import shadows.plants.util.Config;
import shadows.plants.registry.GlobalRegistry;

public class CommonProxy {
	
	
	
	public static Configuration config;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
    	config = new Configuration(e.getSuggestedConfigurationFile());
    	Config.syncConfig();
		GlobalRegistry.init();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(this);

	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {

	}

}
