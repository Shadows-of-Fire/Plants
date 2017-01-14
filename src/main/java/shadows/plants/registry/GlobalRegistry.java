package shadows.plants.registry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GlobalRegistry {
	/*
		This class handles all registration, following registry inheritance.
		This class is at the top of the list, calling methods from ItemRegistry and BlockRegistry.
		
	*/
	
	public static void init(){
		BlockRegistry.init();
		ItemRegistry.init();
	}
	
	
	@SideOnly(Side.CLIENT)
	public static void initModels(){
		BlockRegistry.initModels();
		ItemRegistry.initModels();
	}
	
	public static void initRecipes(){
		RecipeRegistry.init();
	}
	
}
