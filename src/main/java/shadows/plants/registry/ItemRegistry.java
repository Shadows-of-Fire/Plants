package shadows.plants.registry;

import java.util.List;

import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants.registry.modules.ModuleController;
import shadows.plants.util.Config;
import shadows.plants.util.Util;

public class ItemRegistry {
	

	public static void init(){
		if (Config.debug) System.out.println("ItemRegistry loaded");
		register(ModuleController.getAllItems());
	}
	
	public static void register(List<Item> list){
		for (Item item : list){
			Util.register(item);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static void initModels(List<Item> list){
		for (Item item : list){
			Util.initModel(item);
		}
	}
	
}
