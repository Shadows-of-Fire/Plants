package shadows.attained;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import shadows.attained.proxy.CommonProxy;

@Mod(modid = modid.MODID, version = modid.VERSION, name = modid.MODNAME)


public class AttainedDrops {
    public static final String MODID = "modid";
    public static final String MODNAME = "modname";
    public static final String VERSION = "modversion";

    @SidedProxy(clientSide = "shadows.attained.proxy.ClientProxy", serverSide = "shadows.attained.proxy.CommonProxy")
    public static CommonProxy proxy;
	
    @Mod.Instance
    public static AttainedDrops instance;
    
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
