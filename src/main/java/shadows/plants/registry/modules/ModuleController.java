package shadows.plants.registry.modules;

import net.minecraftforge.fml.common.Loader;
import shadows.plants.util.Config;

public class ModuleController {

	public static void moduleLoader(){
		if (!Config.AE || !Loader.isModLoaded("appliedenergistics2")) AE2Module.getAE().clear();;
		/*if (!Config.Botania) BotaniaModule.init();
		if (!Config.Cosmetic) CosmeticModule.init();
		if (!Config.Embers) EmbersModule.init();
		if (!Config.Roots) RootsModule.init();*/
		if (Config.AE && Config.debug) System.out.println("All modules enabled");
		if (Config.AE && Loader.isModLoaded("appliedenergistics2")) System.out.println("AE Loaded");
		
	}
	
	
}
