package shadows.naturalis.client;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import shadows.naturalis.Naturalis;
import shadows.naturalis.registry.RegHandler;
import shadows.placebo.client.IHasModel;

@EventBusSubscriber(modid = Naturalis.MODID, value = Side.CLIENT)
public class ClientHandler {

	@SubscribeEvent
	public void models(ModelRegistryEvent e) {
		for (Item i : RegHandler.ITEMS)
			if (i instanceof IHasModel) ((IHasModel) i).onModelRegister(e);
		for (Block b : RegHandler.BLOCKS)
			if (b instanceof IHasModel) ((IHasModel) b).onModelRegister(e);
	}

}
