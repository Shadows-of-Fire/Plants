package shadows.plants.registry;

import net.minecraft.init.Items;
import shadows.plants.registry.modules.CosmeticModule;
import shadows.plants.util.Util;

public class RecipeRegistry {

	
	public static void init(){
		Util.addSimpleShapeless(Items.STRING, 2, 0, CosmeticModule.apocynum_c_cloth, 0, CosmeticModule.apocynum_c_cloth, CosmeticModule.apocynum_c_cloth);
		//Util.addSimpleShapeless(result, resultmeta, reagent);
	}
}


/*
		GameRegistry.addRecipe(new ItemStack(ModRegistry.seed16c), "222", "2K2", "222", '2', Item.getByNameOrId("appliedenergistics2:spatial_storage_cell_128_cubed"), 'K', ModRegistry.seed2c);
	
*/