package shadows.plants.registry.modules;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Optional;
import shadows.plants.item.addon.botania.ItemExcalibur;
import shadows.plants.util.Data;
import shadows.plants.util.Util;

public class BotaniaModule {

	/*
	 * The control module for the Botania Module of Plants. This will handle all
	 * registration which is then passed to the respective registry classes.
	 */
	public static ItemExcalibur excalibur;

	@Optional.Method(modid = Data.BOTANIA)
	public static List<Block> getBlockList() {
		List<Block> list = new ArrayList<Block>();
		list.clear();
		return list;
	}

	@Optional.Method(modid = Data.BOTANIA)
	public static List<Item> getItemList() {
		List<Item> list = new ArrayList<Item>();
		list.clear();
		list.add(excalibur);
		return list;
	}

	@Optional.Method(modid = Data.BOTANIA)
	public static void assignStrippable() {
		excalibur = new ItemExcalibur();
		if (Data.BOTANIA_ENABLED)
			Util.addSimpleShapeless(excalibur, vazkii.botania.common.item.ModItems.kingKey);
	}
}
