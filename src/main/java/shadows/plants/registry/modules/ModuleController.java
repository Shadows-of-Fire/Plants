package shadows.plants.registry.modules;

import shadows.plants.util.Data;

public class ModuleController {

	public static void modularBlockLoader(){
		if (!Data.BOTANIA_ENABLED) BotaniaModule.getBlockList().clear();
		if (!Data.COSMETIC_ENABLED) CosmeticModule.getBlockList().clear();
		
	}
	
	public static void modularItemLoader(){
		if (Data.BOTANIA_ENABLED) BotaniaModule.getItemList().clear();
		if (!Data.COSMETIC_ENABLED) CosmeticModule.getItemList().clear();
		
	}
	
	
}
