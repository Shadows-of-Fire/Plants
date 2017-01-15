package shadows.plants.registry;


import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants.registry.modules.AE2Module;
import shadows.plants.util.PlantMethods;


public class BlockRegistry {
	
	public static List<Block> BLOCKS = new ArrayList<Block>();
	
	private static List<Block> composeBlocks(List<Block> list){
		if (Loader.isModLoaded("appliedenergistics2")) list.addAll(AE2Module.getAE());
		
		return list;
	}


	
	public static void init(){
		composeBlocks(BLOCKS);
		
		
	}
	
	@SideOnly(Side.CLIENT)
	public static void initModels(){
		for (Block block : BLOCKS){
			PlantMethods.initModel(block);
		}
	}
	
	
}
