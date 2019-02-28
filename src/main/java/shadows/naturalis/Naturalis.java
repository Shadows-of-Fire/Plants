package shadows.naturalis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Naturalis.MODID, name = Naturalis.MODNAME, version = Naturalis.VERSION, dependencies = Naturalis.DEPS)
public class Naturalis {

	public static final String MODID = "naturalis";
	public static final String MODNAME = "Naturalis";
	public static final String VERSION = "1.0.0";
	public static final String DEPS = "required-after:placebo@[2.0.0,)";
	public static final Logger LOGGER = LogManager.getLogger(MODID);

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
	}
}
