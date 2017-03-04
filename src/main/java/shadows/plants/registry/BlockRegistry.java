package shadows.plants.registry;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants.registry.modules.ModuleController;
import shadows.plants.util.Config;
import shadows.plants.util.Util;

public class BlockRegistry {

	public static void init() {
		if (Config.debug)
			System.out.println("BlockRegistry loaded");
		register(ModuleController.getAllBlocks());
	}

	public static void register(List<Block> list) {
		for (Block block : list) {
			Util.register(block);
		}
	}

	@SideOnly(Side.CLIENT)
	public static void initModels(List<Block> list) {
		for (Block block : list) {
			Util.initModel(block);
		}
	}

}
