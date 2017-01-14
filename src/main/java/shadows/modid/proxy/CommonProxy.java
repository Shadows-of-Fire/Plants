package shadows.attained.proxy;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shadows.modid.*;
public class CommonProxy {
	
	
	
	public static String configPath;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		ModRegistry.init();
		RecipeRegistry.init();
	}
	

	public void init(FMLInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(this);

	}

	public void postInit(FMLPostInitializationEvent e) {

	}

}
