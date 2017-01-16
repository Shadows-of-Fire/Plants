package shadows.plants.registry.modules;

import net.minecraftforge.fml.common.Loader;
import shadows.plants.util.Config;

public class ModuleController {

	public static void moduleLoader(){
		if (!Config.AE || !Loader.isModLoaded("appliedenergistics2")) AE2Module.getAE().clear();
		if (!Config.Botania || !Loader.isModLoaded("botania")) BotaniaModule.getB().clear();
		if (!Config.Embers || !Loader.isModLoaded("embers"));
		if (!Config.Roots || !Loader.isModLoaded("roots"));
		if (!Config.Cosmetic);
		if (!Config.Hostile);
		if (!Config.Meme);
		if (Config.AE && Config.debug && Loader.isModLoaded("appliedenergistics2")) System.out.println("AE Loaded");
		
	}
	
	
}
