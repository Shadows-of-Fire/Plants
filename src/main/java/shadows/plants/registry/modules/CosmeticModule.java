package shadows.plants.registry.modules;


import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants.block.internal.cosmetic.BlockMetaBush;
import shadows.plants.item.internal.cosmetic.ItemBlockMetaBush;

public class CosmeticModule{

	/*
	 * The control module for the AE2 Module of Plants.
	 * This will handle all registration which is then passed to the respective registry classes.
	 */
	public static List<Block> COSMETIC = new ArrayList<Block>();
	public static BlockMetaBush cosmetic_1 = new BlockMetaBush("cosmetic_1");
	public static BlockMetaBush cosmetic_2 = new BlockMetaBush("cosmetic_2");
	public static BlockMetaBush cosmetic_3 = new BlockMetaBush("cosmetic_3");

	public static List<Block> getList(){
		COSMETIC.clear();
		COSMETIC.add(cosmetic_3);
		COSMETIC.add(cosmetic_2);
		COSMETIC.add(cosmetic_1);
		return COSMETIC;
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerMetaModels(){
		for (int i = 0; i < 16; i++){
		ModelLoader.setCustomModelResourceLocation(ItemBlockMetaBush.getItemFromBlock(cosmetic_1), i, new ModelResourceLocation(new ResourceLocation("plants:cosmetic/1/" + "cosmetic" + "." + i), "inventory"));
		ModelLoader.setCustomModelResourceLocation(ItemBlockMetaBush.getItemFromBlock(cosmetic_2), i, new ModelResourceLocation(new ResourceLocation("plants:cosmetic/2/" + "cosmetic" + "." + i), "inventory"));
		ModelLoader.setCustomModelResourceLocation(ItemBlockMetaBush.getItemFromBlock(cosmetic_3), i, new ModelResourceLocation(new ResourceLocation("plants:cosmetic/3/" + "cosmetic" + "." + i), "inventory"));
		}}
	
	
	
}
