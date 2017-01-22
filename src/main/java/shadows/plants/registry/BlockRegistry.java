package shadows.plants.registry;


import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants.registry.modules.AE2Module;
import shadows.plants.registry.modules.BotaniaModule;
import shadows.plants.registry.modules.ModuleController;
import shadows.plants.util.Config;
import shadows.plants.util.Data;
import shadows.plants.util.Util;


public class BlockRegistry {
	
	public static List<Block> BLOCKS = new ArrayList<Block>();
	
	private static List<Block> composeBlocks(List<Block> list){
		if (!AE2Module.getAE().isEmpty() && Loader.isModLoaded(Data.AE2)) list.addAll(AE2Module.getAE());
		//if (!BloodModule.getBM().isEmpty()) list.addAll(BloodModule.getBM());
		if (!BotaniaModule.getB().isEmpty() && Loader.isModLoaded(Data.BOTANIA)) list.addAll(BotaniaModule.getB());
		//if (!CosmeticModule.getC().isEmpty()) list.addAll(CosmeticModule.getC());
		//if (!HostileModule.getH().isEmpty()) list.addAll(CosmeticModule.getH());
		//if (!MemeModule.getM().isEmpty()) list.addAll(CosmeticModule.getM());
		//if (!ChiselModule.getCM().isEmpty()) list.addAll(ChiselModule.getCM());
		//if (!EmbersModule.getE().isEmpty()) list.addAll(EmbersModule.getE());
		//if (!RootsModule.getR().isEmpty()) list.addAll(RootsModule.getR());
		return list;
	}

	public static void init(){
		if (Config.debug) System.out.println("BlockRegistry loaded");
		ModuleController.blockLoader();
		composeBlocks(BLOCKS);
		register();
	}

	
	public static void register(){
		for (Block block : BLOCKS){
			Util.register(block);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static void initModels(){
		for (Block block : BLOCKS){
			Util.initModel(block);
		}
	}
	
	
}
