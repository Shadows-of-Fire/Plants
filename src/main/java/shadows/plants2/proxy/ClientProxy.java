package shadows.plants2.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shadows.plants2.client.IHasModel;
import shadows.plants2.compat.BinnieIntegration;
import shadows.plants2.data.Config;
import shadows.plants2.data.Constants;
import shadows.plants2.init.ModRegistry;
import shadows.plants2.tile.TileFlowerpot;

public class ClientProxy implements IProxy {

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void init(FMLInitializationEvent e) {
		if (Config.flowerpot)
			Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, world, pos, tint) -> {
				if (tint == 1) return world.getBiome(pos).getGrassColorAtPos(pos);

				TileEntity t = world.getTileEntity(pos);

				if (Loader.isModLoaded(Constants.BOTANY_ID) && tint >= 10 && t instanceof TileFlowerpot) {
					return BinnieIntegration.colorMultiplier(state, world, pos, tint);
				}
				return -1;
			}, ModRegistry.FLOWERPOT);
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {

	}

	@SubscribeEvent
	public void onModelRegister(ModelRegistryEvent e) {
		for (Block b : ModRegistry.BLOCKS)
			if (b instanceof IHasModel) ((IHasModel) b).initModels(e);
		for (Item i : ModRegistry.ITEMS)
			if (i instanceof IHasModel) ((IHasModel) i).initModels(e);
	}

}
