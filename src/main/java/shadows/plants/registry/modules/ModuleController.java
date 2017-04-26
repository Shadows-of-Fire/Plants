package shadows.plants.registry.modules;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import shadows.plants.util.Data;

public class ModuleController {

	public static List<Block> getAllBlocks() {
		List<Block> list = new ArrayList<Block>();
		if (Data.BOTANIA_ENABLED)
			list.addAll(BotaniaModule.getBlockList());
		if (Data.COSMETIC_ENABLED)
			list.addAll(CosmeticModule.getBlockList());
		if (Data.TOOL_ENABLED)
			list.addAll(ToolModule.getBlockList());
		if (Data.ARCANE_ENABLED)
			list.addAll(ArcaneModule.getBlockList());
		return list;
	}

	public static List<Item> getAllItems() {
		List<Item> list = new ArrayList<Item>();
		if (Data.BOTANIA_ENABLED)
			list.addAll(BotaniaModule.getItemList());
		if (Data.COSMETIC_ENABLED)
			list.addAll(CosmeticModule.getItemList());
		if (Data.ARCANE_ENABLED)
			list.addAll(ArcaneModule.getItemList());
		return list;
	}

}
