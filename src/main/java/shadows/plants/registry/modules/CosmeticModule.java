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
import shadows.plants.block.internal.cosmetic.BlockDoubleMetaBush;
import shadows.plants.block.internal.cosmetic.BlockMetaBush;
import shadows.plants.util.Util;

public class CosmeticModule{

	/*
	 * The control module for the Cosmetic Module of Plants.
	 * This will handle all registration which is then passed to the respective registry classes.
	 */
	public static List<Block> COSMETIC = new ArrayList<Block>();
	public static BlockMetaBush cosmetic_1 = new BlockMetaBush("cosmetic_1");
	public static BlockMetaBush cosmetic_2 = new BlockMetaBush("cosmetic_2");
	public static BlockMetaBush cosmetic_3 = new BlockMetaBush("cosmetic_3");
	public static BlockMetaBush cosmetic_4 = new BlockMetaBush("cosmetic_4");
	public static BlockDoubleMetaBush cosmetic_5 = new BlockDoubleMetaBush("cosmetic_5", null);
	
	public static List<Block> getList(){
		COSMETIC.clear();
		COSMETIC.add(cosmetic_1);
		COSMETIC.add(cosmetic_2);
		COSMETIC.add(cosmetic_3);
		COSMETIC.add(cosmetic_4);
		COSMETIC.add(cosmetic_5);
		return COSMETIC;
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerMetaModels(){
		for (Block block : getList()){
		if (block instanceof BlockMetaBush){
		for (int i = 0; i < 16; i++){
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), i, new ModelResourceLocation(new ResourceLocation("plants:cosmetic/" + Util.getBlockNumber((BushBase) block) + "/" + "cosmetic" + "." + i), "inventory"));
		}}
		if (block instanceof BlockDoubleMetaBush){
		for (int i = 0; i < 8; i++){
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), i, new ModelResourceLocation(new ResourceLocation("plants:cosmetic/" + Util.getBlockNumber((BushBase) block) + "/" + "cosmetic" + "." + i), "inventory"));
		}}
		
		}}
	
	
	
}
