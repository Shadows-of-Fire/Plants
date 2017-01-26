package shadows.plants.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Loader;
import shadows.plants.registry.GlobalRegistry;
import shadows.plants.registry.modules.CosmeticModule;

public class Data {

    public static final String MODID = "plants";
    public static final String MODNAME = "Plants";
    public static final String VERSION = "dev-0.02";
    public static final CreativeTabs TAB = GlobalRegistry.TAB;
    public static final String BOTANIA = "Botania";
    public static final String AE2 = "appliedenergistics2";
    public static final boolean BOTANIA_ENABLED = (Loader.isModLoaded(BOTANIA) && Config.Botania);
	public static final List<Block> GENPLANTS(){
		List<Block> list = new ArrayList<Block>();
		list.add(CosmeticModule.cosmetic_1);
		list.add(CosmeticModule.cosmetic_2);
		list.add(CosmeticModule.cosmetic_3);
		list.add(CosmeticModule.cosmetic_4);
		return list;
	};
	
}
