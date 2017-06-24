package shadows.plants.proxy;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shadows.plants.registry.GlobalRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onModelRegistry(ModelRegistryEvent e) {
		GlobalRegistry.initModels(e);
	}

}
