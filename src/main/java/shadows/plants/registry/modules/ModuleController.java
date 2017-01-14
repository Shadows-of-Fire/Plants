package shadows.plants.registry.modules;

public class ModuleController {

	public static void moduleLoader(){
		if (Config.AE2) AE2Module.init();
		if (Config.Botania) BotaniaModule.init();
		if (Config.Cosmetic) CosmeticModule.init();
		if (Config.Embers) EmbersModule.init();
		if (Config.Roots) RootsModule.init();
		
		
	}
	
	
}
