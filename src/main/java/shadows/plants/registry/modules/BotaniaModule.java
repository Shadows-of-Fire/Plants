	package shadows.plants.registry.modules;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;
import shadows.plants.item.addon.botania.ItemExcalibur;
import shadows.plants.util.Data;

public class BotaniaModule {

	/*
	 * The control module for the Botania Module of Plants.
	 * This will handle all registration which is then passed to the respective registry classes.
	 */
	public static List<Block> BOTANIA = new ArrayList<Block>();

	public static List<Item> BOTANIA_I = new ArrayList<Item>();
	public static ItemExcalibur excalibur;
	
	public static List<Block> getBlockList(){
		BOTANIA.clear();
		return BOTANIA;
	}
		
	public static List<Item> getItemList(){
		if (Loader.isModLoaded(Data.BOTANIA)) assignStrippable();
		BOTANIA_I.clear();
		BOTANIA_I.add(excalibur);
		return BOTANIA_I;
	}
	
	@Optional.Method(modid=Data.BOTANIA)
	public static void assignStrippable(){
	excalibur = new ItemExcalibur();
	}
}
