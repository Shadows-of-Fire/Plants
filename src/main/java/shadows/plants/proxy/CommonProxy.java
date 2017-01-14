package shadows.plants.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import shadows.plants.util.Config;
import shadows.plants.registry.BlockRegistry;
import shadows.plants.registry.GlobalRegistry;
import shadows.plants.registry.ItemRegistry;
import shadows.plants.registry.RecipeRegistry;

public class CommonProxy {
	
	
	
	public static Configuration config;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
    	config = new Configuration(e.getSuggestedConfigurationFile());
    	Config.syncConfig();
		GlobalRegistry.init();
		GlobalRegistry.initRecipes();
	}
	

	public void init(FMLInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(this);

	}

	public void postInit(FMLPostInitializationEvent e) {

	}

}
