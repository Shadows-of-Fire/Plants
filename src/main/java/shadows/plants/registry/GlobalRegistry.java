package shadows.plants.registry;


public class GlobalRegistry {
	/*
		This class handles all registration, following registry inheritance.
		This class is at the top of the list, calling methods from ItemRegistry and BlockRegistry.
		
	*/
	
	public static void init(){
		BlockRegistry.init();
		ItemRegistry.init();
		BlockRegistry.initModels();
		ItemRegistry.initModels();
	}
	
	public static void initRecipes(){
		RecipeRegistry.init();
	}
	
}
