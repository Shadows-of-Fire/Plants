package shadows.plants.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import shadows.plants.registry.modules.ModuleController;
import shadows.plants.util.Data;

public class GlobalRegistry {
	/*
	 * This class handles all registration, following registry inheritance. This
	 * class is at the top of the list, everything from this mod goes through
	 * here.
	 */

	public static void preInit(FMLPreInitializationEvent e) {
		ItemRegistry.init();
		BlockRegistry.init();
	}

	@SideOnly(Side.CLIENT)
	public static void initModels(ModelRegistryEvent e) {
		BlockRegistry.initModels(ModuleController.getAllBlocks());
		ItemRegistry.initModels(ModuleController.getAllItems());
	}

	@SubscribeEvent
	public void onBlockRegistry(RegistryEvent.Register<Block> e) {
		IForgeRegistry<Block> reg = e.getRegistry();
		for (Block block : Data.BLOCKS) {
			reg.register(block);
		}
	}

	@SubscribeEvent
	public void onItemRegistry(RegistryEvent.Register<Item> e) {
		IForgeRegistry<Item> reg = e.getRegistry();
		for (Item item : Data.ITEMS) {
			reg.register(item);
		}
	}

	@SubscribeEvent
	public void onRecipeRegistry(RegistryEvent.Register<IRecipe> e) {
		IForgeRegistry<IRecipe> reg = e.getRegistry();
		for (IRecipe rec : Data.RECIPES) {
			reg.register(rec);
		}
	}

}
