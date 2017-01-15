package shadows.plants.util;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants.common.EnumModule;
import shadows.plants.registry.modules.AE2Module;

public class Util {

	@SideOnly(Side.CLIENT)
	public static void initModel(Block block){
		if (Config.debug) System.out.println("Registered Model " + block.toString());
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}
	
	@SideOnly(Side.CLIENT)
	public static void initModel(Item item){
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	public static void register(Block block){
		if (Config.debug) System.out.println("Registered " + block.toString());
		GameRegistry.register(block);
		GameRegistry.register(new ItemBlock(block), block.getRegistryName());
	}
	
	public static void register(Item item){
		GameRegistry.register(item);
	}
	
	@Nullable
	public static Block getFarmlandFromSoil(EnumModule module){
		switch(module){
		case APPLIED : return AE2Module.ae_farmland;
		default : return null;
		}
	}
	
	
}
