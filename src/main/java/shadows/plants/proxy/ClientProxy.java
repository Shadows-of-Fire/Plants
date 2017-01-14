package shadows.plants.proxy;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import shadows.plants.registry.BlockRegistry;


public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		BlockRegistry.initModels();

	}
}

