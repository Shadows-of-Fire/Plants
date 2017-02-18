package shadows.plants.registry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import shadows.plants.block.internal.cosmetic.BlockDoubleMetaBush;
import shadows.plants.block.internal.cosmetic.BlockMetaBush;
import shadows.plants.common.IMetaPlant;
import shadows.plants.registry.modules.CosmeticModule;

public class OredictRegistry {

	public static void init(){
		OreDictionary.registerOre("dyeBlue", CosmeticModule.dye_blue);
		OreDictionary.registerOre("dye", CosmeticModule.dye_blue);
		OreDictionary.registerOre("dyeBlack", CosmeticModule.dye_black);
		OreDictionary.registerOre("dye", CosmeticModule.dye_black);
		OreDictionary.registerOre("dyeBrown", CosmeticModule.dye_brown);
		OreDictionary.registerOre("dye", CosmeticModule.dye_brown);
		OreDictionary.registerOre("dyeWhite", CosmeticModule.dye_white);
		OreDictionary.registerOre("dye", CosmeticModule.dye_white);
		
		for (Block block : CosmeticModule.getBlockList()){
			if(block instanceof BlockMetaBush || block instanceof BlockDoubleMetaBush){
				for(int i = 0; i <= ((IMetaPlant) block).getMaxData(); i++){
					OreDictionary.registerOre("plant", new ItemStack(block, 1, i));
				}
			}
			else OreDictionary.registerOre("plant", block);
		}
		
		for(int k = 0; k <= 9; k++){
		OreDictionary.registerOre("plant", new ItemStack(Blocks.RED_FLOWER, 1, k));
		}
		OreDictionary.registerOre("plant", new ItemStack(Blocks.YELLOW_FLOWER, 1, 1));
		for(int k = 0; k <= 6; k++){
		OreDictionary.registerOre("plant", new ItemStack(Blocks.DOUBLE_PLANT, 1, k));
		}
		for(int k = 1; k <= 3; k++){
		OreDictionary.registerOre("plant", new ItemStack(Blocks.TALLGRASS, 1, k));
		}
	}
	
	
}
