package shadows.plants.registry.modules;

import net.minecraftforge.fml.common.Loader;
import shadows.plants.util.Config;
import shadows.plants.util.Data;

public class ModuleController {

	public static void blockLoader(){
		if (!Config.AE) AE2Module.getList().clear();
		if (!Data.BOTANIA_ENABLED) BotaniaModule.getB().clear();
		if (!Config.Embers || !Loader.isModLoaded("embers"));
		if (!Config.Roots || !Loader.isModLoaded("roots"));
		if (!Config.Cosmetic) CosmeticModule.getList().clear();
		if (!Config.Hostile);
		if (!Config.Meme);
		if (Config.debug && Config.AE && Loader.isModLoaded("appliedenergistics2")) System.out.println("AE Loaded");
		
	}
	
	public static void itemLoader(){
		//if (!Config.AE || !Loader.isModLoaded("appliedenergistics2")) AE2Module.getAE_I().clear();
		if (!Config.Botania || !Loader.isModLoaded("botania")) BotaniaModule.getB_I().clear();
		if (!Config.Embers || !Loader.isModLoaded("embers"));
		if (!Config.Roots || !Loader.isModLoaded("roots"));
		if (!Config.Cosmetic);
		if (!Config.Hostile);
		if (!Config.Meme);
		
	}
	
	
}
