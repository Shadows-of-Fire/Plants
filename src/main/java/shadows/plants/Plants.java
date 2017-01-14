package shadows.plants;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import shadows.plants.proxy.CommonProxy;
import shadows.plants.util.Data;

@Mod(modid = Data.MODID, version = Data.VERSION, name = Data.MODNAME)


public class Plants {

    @SidedProxy(clientSide = "shadows.plants.proxy.ClientProxy", serverSide = "shadows.plants.proxy.CommonProxy")
    public static CommonProxy proxy;
	
    @Mod.Instance
    public static Plants instance;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	proxy.preInit(event);
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent e){
    	proxy.init(e);
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e)
    {
    	proxy.postInit(e);
    }


}
