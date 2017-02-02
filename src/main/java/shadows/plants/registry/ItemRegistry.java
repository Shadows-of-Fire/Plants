package shadows.plants.registry;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants.registry.modules.BotaniaModule;
import shadows.plants.registry.modules.CosmeticModule;
import shadows.plants.registry.modules.ModuleController;
import shadows.plants.util.Config;
import shadows.plants.util.Data;
import shadows.plants.util.Util;

public class ItemRegistry {
	
	
	public static List<Item> ITEMS = new ArrayList<Item>();
	
	private static List<Item> composeItems(List<Item> list){
		if (Data.BOTANIA_ENABLED) list.addAll(BotaniaModule.getItemList());
		if (Data.COSMETIC_ENABLED) list.addAll(CosmeticModule.getItemList());
		return list;
	}

	public static void init(){
		if (Config.debug) System.out.println("ItemRegistry loaded");
		ModuleController.modularItemLoader();
		composeItems(ITEMS);
		register();
	}
	
	public static void register(){
		for (Item item : ITEMS){
			Util.register(item);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static void initModels(){
		for (Item item : ITEMS){
			Util.initModel(item);
		}
	}
	
}
