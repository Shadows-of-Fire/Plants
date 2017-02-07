package shadows.plants.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import shadows.plants.registry.GlobalRegistry;
import shadows.plants.registry.modules.CosmeticModule;

public class Data {

    public static final String MODID = "plants";
    public static final String MODNAME = "Plants";
    public static final String VERSION = "beta-0.4.0";
    public static final CreativeTabs TAB = GlobalRegistry.TAB;
    public static final CreativeTabs TAB_I = GlobalRegistry.TAB_I;
    public static final String BOTANIA = "Botania";
    public static final String AE2 = "appliedenergistics2";
    public static final boolean BOTANIA_ENABLED = (Loader.isModLoaded(BOTANIA) && Config.Botania);
    public static final boolean COSMETIC_ENABLED = Config.Cosmetic;
    public static final ItemStack EMPTYSTACK = null; //for 1.11 porting later
	
}
