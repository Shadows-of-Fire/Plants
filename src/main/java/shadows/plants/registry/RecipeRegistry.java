package shadows.plants.registry;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import shadows.plants.block.internal.cosmetic.BlockCrop;
import shadows.plants.registry.modules.CosmeticModule;
import shadows.plants.registry.modules.ModuleController;
import shadows.plants.util.Util;

public class RecipeRegistry {
	public static final Item dye = Items.DYE;

	public static void init() {
		Util.addSimpleShapeless(Items.STRING, 4, 0, CosmeticModule.apocynum_c_cloth, 0, CosmeticModule.apocynum_c_cloth,
				CosmeticModule.apocynum_c_cloth);

		Util.addSimpleShapeless(dye, 6, CosmeticModule.cosmetic_1, 0);
		Util.addSimpleShapeless(dye, 9, CosmeticModule.cosmetic_1, 1);
		Util.addSimpleShapeless(dye, 11, CosmeticModule.cosmetic_1, 2);
		Util.addSimpleShapeless(dye, 14, CosmeticModule.cosmetic_1, 3);
		Util.addSimpleShapeless(dye, 2, 11, CosmeticModule.cosmetic_1, 4);
		Util.addSimpleShapeless(dye, 2, CosmeticModule.cosmetic_1, 5);
		Util.addSimpleShapeless(dye, 5, CosmeticModule.cosmetic_1, 6);
		Util.addSimpleShapeless(dye, 10, CosmeticModule.cosmetic_1, 7);
		Util.addSimpleShapeless(dye, 8, CosmeticModule.cosmetic_1, 8);
		Util.addSimpleShapeless(dye, 11, CosmeticModule.cosmetic_1, 9);
		Util.addSimpleShapeless(dye, 13, CosmeticModule.cosmetic_1, 10);
		Util.addSimpleShapeless(dye, 9, CosmeticModule.cosmetic_1, 11);
		Util.addSimpleShapeless(dye, 3, 2, CosmeticModule.cosmetic_1, 12);
		Util.addSimpleShapeless(dye, 1, CosmeticModule.cosmetic_1, 13);
		Util.addSimpleShapeless(CosmeticModule.dye_white, CosmeticModule.cosmetic_1, 14);
		Util.addSimpleShapeless(dye, 11, CosmeticModule.cosmetic_1, 15);

		Util.addSimpleShapeless(dye, 9, CosmeticModule.cosmetic_2, 0);
		Util.addSimpleShapeless(dye, 14, CosmeticModule.cosmetic_2, 1);
		Util.addSimpleShapeless(CosmeticModule.dye_white, CosmeticModule.cosmetic_2, 2);
		Util.addSimpleShapeless(dye, 2, 9, CosmeticModule.cosmetic_2, 3);
		Util.addSimpleShapeless(CosmeticModule.dye_brown, CosmeticModule.cosmetic_2, 4);
		Util.addSimpleShapeless(dye, 5, CosmeticModule.cosmetic_2, 5);
		Util.addSimpleShapeless(dye, 11, CosmeticModule.cosmetic_2, 6);
		Util.addSimpleShapeless(CosmeticModule.dye_white, CosmeticModule.cosmetic_2, 7);
		Util.addSimpleShapeless(dye, 5, CosmeticModule.cosmetic_2, 8);
		Util.addSimpleShapeless(CosmeticModule.dye_blue, CosmeticModule.cosmetic_2, 9);
		Util.addSimpleShapeless(dye, 14, CosmeticModule.cosmetic_2, 10);
		Util.addSimpleShapeless(dye, 11, CosmeticModule.cosmetic_2, 11);
		Util.addSimpleShapeless(dye, 3, 1, CosmeticModule.cosmetic_2, 12);
		Util.addSimpleShapeless(dye, 3, 6, CosmeticModule.cosmetic_2, 13);
		Util.addSimpleShapeless(dye, 12, CosmeticModule.cosmetic_2, 14);
		Util.addSimpleShapeless(dye, 11, CosmeticModule.cosmetic_2, 15);

		Util.addSimpleShapeless(dye, 13, CosmeticModule.cosmetic_3, 0);
		Util.addSimpleShapeless(CosmeticModule.dye_white, CosmeticModule.cosmetic_3, 1);
		Util.addSimpleShapeless(dye, 2, 1, CosmeticModule.cosmetic_3, 2);
		Util.addSimpleShapeless(CosmeticModule.dye_white, 2, 0, CosmeticModule.cosmetic_3, 3);
		Util.addSimpleShapeless(dye, 2, 9, CosmeticModule.cosmetic_3, 4);
		Util.addSimpleShapeless(CosmeticModule.dye_black, CosmeticModule.cosmetic_3, 5);
		Util.addSimpleShapeless(dye, 1, CosmeticModule.cosmetic_3, 6);
		Util.addSimpleShapeless(dye, 14, CosmeticModule.cosmetic_3, 7);
		Util.addSimpleShapeless(CosmeticModule.dye_brown, 0, CosmeticModule.cosmetic_3, 8);
		Util.addSimpleShapeless(dye, 1, CosmeticModule.cosmetic_3, 9);
		Util.addSimpleShapeless(CosmeticModule.dye_white, CosmeticModule.cosmetic_3, 10);
		Util.addSimpleShapeless(dye, 1, CosmeticModule.cosmetic_3, 11);
		Util.addSimpleShapeless(CosmeticModule.dye_white, 3, 0, CosmeticModule.cosmetic_3, 12);
		Util.addSimpleShapeless(CosmeticModule.dye_white, 4, 0, CosmeticModule.cosmetic_3, 13);
		Util.addSimpleShapeless(dye, 1, CosmeticModule.cosmetic_3, 14);
		Util.addSimpleShapeless(CosmeticModule.dye_white, CosmeticModule.cosmetic_3, 15);

		Util.addSimpleShapeless(dye, 1, CosmeticModule.cosmetic_4, 0);
		Util.addSimpleShapeless(dye, 9, CosmeticModule.cosmetic_4, 1);
		Util.addSimpleShapeless(dye, 2, 9, CosmeticModule.cosmetic_4, 2);
		Util.addSimpleShapeless(dye, 2, 1, CosmeticModule.cosmetic_4, 3);
		Util.addSimpleShapeless(CosmeticModule.dye_blue, 2, 0, CosmeticModule.cosmetic_4, 4);
		Util.addSimpleShapeless(CosmeticModule.dye_white, CosmeticModule.cosmetic_4, 5);
		Util.addSimpleShapeless(dye, 9, CosmeticModule.cosmetic_4, 6);
		Util.addSimpleShapeless(dye, 6, CosmeticModule.cosmetic_4, 7);
		Util.addSimpleShapeless(dye, 14, CosmeticModule.cosmetic_4, 8);
		Util.addSimpleShapeless(dye, 4, 1, CosmeticModule.cosmetic_4, 9);
		Util.addSimpleShapeless(dye, 4, CosmeticModule.cosmetic_4, 10);
		Util.addSimpleShapeless(CosmeticModule.dye_white, 5, 0, CosmeticModule.cosmetic_4, 11);
		Util.addSimpleShapeless(dye, 2, 13, CosmeticModule.cosmetic_4, 12);
		Util.addSimpleShapeless(dye, 7, CosmeticModule.cosmetic_4, 13);
		Util.addSimpleShapeless(dye, 12, CosmeticModule.cosmetic_4, 14);
		Util.addSimpleShapeless(CosmeticModule.dye_white, CosmeticModule.cosmetic_4, 15);

		Util.addSimpleShapeless(CosmeticModule.dye_white, 6, 0, CosmeticModule.cosmetic_5, 0);
		Util.addSimpleShapeless(dye, 6, 9, CosmeticModule.cosmetic_5, 1);
		Util.addSimpleShapeless(CosmeticModule.dye_blue, 6, 0, CosmeticModule.cosmetic_5, 2);
		Util.addSimpleShapeless(dye, 4, 11, CosmeticModule.cosmetic_5, 3);
		Util.addSimpleShapeless(CosmeticModule.dye_white, 5, 0, CosmeticModule.cosmetic_5, 4);
		Util.addSimpleShapeless(dye, 3, 1, CosmeticModule.cosmetic_5, 5);

		Util.addSimpleShapeless(dye, 5, CosmeticModule.cosmetic_6, 0);
		Util.addSimpleShapeless(CosmeticModule.dye_white, CosmeticModule.cosmetic_6, 1);
		Util.addSimpleShapeless(dye, 7, CosmeticModule.cosmetic_6, 2);
		Util.addSimpleShapeless(dye, 13, CosmeticModule.cosmetic_6, 3);
		Util.addSimpleShapeless(dye, 1, CosmeticModule.cosmetic_6, 4);
		Util.addSimpleShapeless(dye, 2, CosmeticModule.cosmetic_6, 5);
		Util.addSimpleShapeless(CosmeticModule.dye_white, 2, 0, CosmeticModule.cosmetic_6, 6);
		Util.addSimpleShapeless(dye, 9, CosmeticModule.cosmetic_6, 7);
		Util.addSimpleShapeless(dye, 6, CosmeticModule.cosmetic_6, 8);
		Util.addSimpleShapeless(CosmeticModule.dye_white, CosmeticModule.cosmetic_6, 9);
		Util.addSimpleShapeless(CosmeticModule.dye_white, CosmeticModule.cosmetic_6, 10);
		Util.addSimpleShapeless(CosmeticModule.dye_white, CosmeticModule.cosmetic_6, 11);
		Util.addSimpleShapeless(dye, 2, 14, CosmeticModule.cosmetic_6, 12);
		Util.addSimpleShapeless(dye, 1, CosmeticModule.cosmetic_6, 13);
		Util.addSimpleShapeless(CosmeticModule.dye_blue, 3, 0, CosmeticModule.cosmetic_6, 14);
		Util.addSimpleShapeless(dye, 3, 7, CosmeticModule.cosmetic_6, 15);

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(CosmeticModule.compost, 5), "plant", "plant",
				"plant", "plant", "plant", "plant", "plant", "plant", "plant"));

		GameRegistry.addRecipe(
				new ShapelessOreRecipe(new ItemStack(CosmeticModule.compost, 2), "plant", "plant", "plant", "plant"));

		for (Block crop : ModuleController.getAllBlocks()) {
			if (crop instanceof BlockCrop) {
				Util.addSimpleShapeless(((BlockCrop) crop).getSeed(), ((BlockCrop) crop).getCrop());
			}
		}

	}
}

/*
 * GameRegistry.addRecipe(new ItemStack(ModRegistry.seed16c), "222", "2K2",
 * "222", '2',
 * Item.getByNameOrId("appliedenergistics2:spatial_storage_cell_128_cubed"),
 * 'K', ModRegistry.seed2c);
 * 
 */