package shadows.plants.registry.modules;


import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants.block.BushBase;
import shadows.plants.block.internal.cosmetic.BlockCrop;
import shadows.plants.block.internal.cosmetic.BlockDoubleMetaBush;
import shadows.plants.block.internal.cosmetic.BlockMetaBush;
import shadows.plants.common.EnumModule;
import shadows.plants.item.FoodBase;
import shadows.plants.item.SeedBase;
import shadows.plants.util.Util;

public class CosmeticModule{

	/*
	 * The control module for the Cosmetic Module of Plants.
	 * This will handle all registration which is then passed to the respective registry classes.
	 */
	public static BlockMetaBush cosmetic_1 = new BlockMetaBush("cosmetic_1");
	public static BlockMetaBush cosmetic_2 = new BlockMetaBush("cosmetic_2");
	public static BlockMetaBush cosmetic_3 = new BlockMetaBush("cosmetic_3");
	public static BlockMetaBush cosmetic_4 = new BlockMetaBush("cosmetic_4");
	public static BlockDoubleMetaBush cosmetic_5 = new BlockDoubleMetaBush("cosmetic_5", null);
	
	public static FoodBase okra = new FoodBase("okra", EnumModule.COSMETIC, 3, 1.0f);
	public static BlockCrop okra_crop = new BlockCrop("okra_crop", okra, 0);
	public static SeedBase okra_seed = new SeedBase("okra_seed", okra_crop, EnumModule.COSMETIC);
	
	public static FoodBase pineapple = new FoodBase("pineapple", EnumModule.COSMETIC, 5, 1.8f);
	public static BlockCrop pineapple_crop = new BlockCrop("pineapple_crop", pineapple, 1);
	public static SeedBase pineapple_seed = new SeedBase("pineapple_seed", pineapple_crop, EnumModule.COSMETIC);
	
	public static List<Item> getItemList(){
	List<Item> COSMETIC_I = new ArrayList<Item>();	
		COSMETIC_I.add(okra_seed);
		COSMETIC_I.add(okra);
		COSMETIC_I.add(pineapple);
		COSMETIC_I.add(pineapple_seed);
		return COSMETIC_I;
	}
	
	
	public static List<Block> getBlockList(){
		List<Block> COSMETIC = new ArrayList<Block>();
		COSMETIC.add(cosmetic_1);
		COSMETIC.add(cosmetic_2);
		COSMETIC.add(cosmetic_3);
		COSMETIC.add(cosmetic_4);
		COSMETIC.add(cosmetic_5);
		COSMETIC.add(pineapple_crop);
		COSMETIC.add(okra_crop);
		return COSMETIC;
	}
	
	
	
}
