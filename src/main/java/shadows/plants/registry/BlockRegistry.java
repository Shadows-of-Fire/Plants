package shadows.plants.registry;


import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants.registry.modules.AE2Module;
import shadows.plants.registry.modules.ModuleController;
import shadows.plants.util.Config;
import shadows.plants.util.Util;


public class BlockRegistry {
	
	public static List<Block> BLOCKS = new ArrayList<Block>();
	
	private static List<Block> composeBlocks(List<Block> list){
		if (!AE2Module.getAE().isEmpty()) list.addAll(AE2Module.getAE());
		/*if (!BloodModule.getBM().isEmpty()) list.addAll(BloodModule.getBM());
		if (!BotaniaModule.getB().isEmpty()) list.addAll(BotaniaModule.getB());
		if (!CosmeticModule.getC().isEmpty()) list.addAll(CosmeticModule.getC());
		if (!EmbersModule.getE().isEmpty()) list.addAll(EmbersModule.getE());
		if (!RootsModule.getR().isEmpty()) list.addAll(RootsModule.getR());*/
		return list;
	}

	public static void init(){
		if (Config.debug) System.out.println("BlockRegistry loaded");
		ModuleController.moduleLoader();
		BLOCKS.addAll(composeBlocks(BLOCKS));
		registerBlocks();
		registerModels();
	}

	
	public static void registerBlocks(){
		for (Block block : BLOCKS){
			if (Config.debug) System.out.println("Tryin shit with " + block.toString());
			Util.register(block);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerModels(){
		for (Block block : BLOCKS){
			Util.initModel(block);
		}
	}
	
	
}
