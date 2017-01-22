package shadows.plants.proxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import shadows.plants.registry.GlobalRegistry;
import shadows.plants.util.Data;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
	GlobalRegistry.initModels();
	}

}
