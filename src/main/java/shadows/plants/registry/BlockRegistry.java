package shadows.plants.registry;


import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants.registry.modules.BotaniaModule;
import shadows.plants.registry.modules.CosmeticModule;
import shadows.plants.registry.modules.ModuleController;
import shadows.plants.util.Config;
import shadows.plants.util.Data;
import shadows.plants.util.Util;


public class BlockRegistry {
	
	
	private static List<Block> BLOCKS(){
		List<Block> list = new ArrayList<Block>();
		list.clear();
		if (Data.BOTANIA_ENABLED) list.addAll(BotaniaModule.getBlockList());
		if (Data.COSMETIC_ENABLED) list.addAll(CosmeticModule.getBlockList());
		return list;
	}

	public static void init(){
		if (Config.debug) System.out.println("BlockRegistry loaded");
		ModuleController.modularBlockLoader();
		register();
	}

	
	public static void register(){
		for (Block block : BLOCKS()){
			Util.register(block);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static void initModels(){
		for (Block block : BLOCKS()){
			Util.initModel(block);
		}
	}
	
	
}
