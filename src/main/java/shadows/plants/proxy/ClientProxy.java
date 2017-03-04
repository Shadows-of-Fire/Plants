package shadows.plants.proxy;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import shadows.plants.registry.GlobalRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		GlobalRegistry.initModels();
	}

}
