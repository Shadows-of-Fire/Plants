package shadows.plants.registry;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional.Method;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants.registry.modules.BotaniaModule;
import shadows.plants.registry.modules.CosmeticModule;
import shadows.plants.util.Data;

public class GlobalRegistry {
	/*
		This class handles all registration, following registry inheritance.
		This class is at the top of the list, calling methods from ItemRegistry and BlockRegistry.
		
	*/
	
	public static void init(){
		BlockRegistry.init();
		ItemRegistry.init();
		RecipeRegistry.init();
	}
	
	public static void initModels(){
		BlockRegistry.initModels();
		ItemRegistry.initModels();
	}
	
	public static final CreativeTabs TAB = new CreativeTabs(Data.MODID) {
	    @Override public Item getTabIconItem() {
	        return  Item.getItemFromBlock(CosmeticModule.cosmetic_1);
	    }
		@Override
		@SideOnly(Side.CLIENT)
		public void displayAllRelevantItems(List<ItemStack> list) {
			if(Data.COSMETIC_ENABLED){
			for(int i = 0; i <= 15; i++){
				list.add(new ItemStack(CosmeticModule.cosmetic_1, 1, i));
				list.add(new ItemStack(CosmeticModule.cosmetic_2, 1, i));
				list.add(new ItemStack(CosmeticModule.cosmetic_3, 1, i)); }
			for(int i = 0; i <= 8; i++){ list.add(new ItemStack(CosmeticModule.cosmetic_4, 1, i)); }
			for(int i = 0; i <= 2; i++){ list.add(new ItemStack(CosmeticModule.cosmetic_5, 1, i)); }
			for(Item item : CosmeticModule.getItemList()){ list.add(new ItemStack(item)); }
			}
			if (Data.BOTANIA_ENABLED) addBot(list);
		}};
			
			@Method(modid=Data.BOTANIA)
			public static void addBot(List<ItemStack> list){
				list.add(new ItemStack(BotaniaModule.excalibur));
			}
	
}
