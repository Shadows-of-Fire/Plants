package shadows.plants.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import shadows.plants.registry.GlobalRegistry;

public class Data {

	public static final String MODID = "plants";
	public static final String MODNAME = "Plants";
	public static final String VERSION = "1.1.0";
	public static final CreativeTabs TAB = GlobalRegistry.TAB;
	public static final CreativeTabs TAB_I = GlobalRegistry.TAB_I;
	public static final String BOTANIA = "botania";
	public static final String AE2 = "appliedenergistics2";
	public static final boolean BOTANIA_ENABLED = (Loader.isModLoaded(BOTANIA) && Config.Botania);
	public static final boolean COSMETIC_ENABLED = Config.Cosmetic;
	public static final boolean TOOL_ENABLED = Config.Tool;
	public static final ItemStack EMPTYSTACK = ItemStack.EMPTY;
	public static final boolean ARCANE_ENABLED = Config.Arcane;

}
