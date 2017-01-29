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
		//if (!AE2Module.getAE().isEmpty()) list.addAll(AE2Module.getAE());
		//if (!BloodModule.getBM().isEmpty()) list.addAll(BloodModule.getBM());
		if (Data.BOTANIA_ENABLED) list.addAll(BotaniaModule.getItemList());
		if (!CosmeticModule.getItemList().isEmpty()) list.addAll(CosmeticModule.getItemList());
		//if (!HostileModule.getH().isEmpty()) list.addAll(CosmeticModule.getH());
		//if (!MemeModule.getM().isEmpty()) list.addAll(CosmeticModule.getM());
		//if (!ChiselModule.getCM().isEmpty()) list.addAll(ChiselModule.getCM());
		//if (!EmbersModule.getE().isEmpty()) list.addAll(EmbersModule.getE());
		//if (!RootsModule.getR().isEmpty()) list.addAll(RootsModule.getR());
		return list;
	}

	public static void init(){
		if (Config.debug) System.out.println("ItemRegistry loaded");
		ModuleController.itemLoader();
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
