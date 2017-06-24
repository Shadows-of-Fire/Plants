package shadows.plants.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants.common.IMetaPlant;
import shadows.plants.registry.modules.CosmeticModule;
import shadows.plants.registry.modules.ModuleController;

public class Data {

	public static final String MODID = "plants";
	public static final String MODNAME = "Plants";
	public static final String VERSION = "1.2.0";
	public static final CreativeTabs TAB = new CreativeTabs(Data.MODID) {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(CosmeticModule.cosmetic_1);
		}

		@Override
		@SideOnly(Side.CLIENT)
		public void displayAllRelevantItems(NonNullList<ItemStack> list) {
			for (Block block : ModuleController.getAllBlocks()) {
				int i = 0;
				if (block instanceof IMetaPlant)
					i = ((IMetaPlant) block).getMaxData();
				for (int k = 0; k <= i; k++) {
					list.add(new ItemStack(block, 1, k));
				}
			}
		}
	};
	public static final CreativeTabs TAB_I = new CreativeTabs(Data.MODID + (".items")) {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(CosmeticModule.pineapple);
		}

		@Override
		@SideOnly(Side.CLIENT)
		public void displayAllRelevantItems(NonNullList<ItemStack> list) {
			for (Item item : ModuleController.getAllItems()) {
				list.add(new ItemStack(item));
			}
		}
	};
	public static final String BOTANIA = "botania";
	public static final String AE2 = "appliedenergistics2";
	public static final boolean BOTANIA_ENABLED = (Loader.isModLoaded(BOTANIA) && Config.Botania);
	public static final boolean COSMETIC_ENABLED = Config.Cosmetic;
	public static final boolean TOOL_ENABLED = Config.Tool;
	public static final ItemStack EMPTYSTACK = ItemStack.EMPTY;
	public static final boolean ARCANE_ENABLED = Config.Arcane;
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	public static final List<Item> ITEMS = new ArrayList<Item>();
	public static final List<IRecipe> RECIPES = new ArrayList<IRecipe>();

}
