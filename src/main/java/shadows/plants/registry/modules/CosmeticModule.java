package shadows.plants.registry.modules;


import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import shadows.plants.block.BushBase;
import shadows.plants.block.FlowerBase;
import shadows.plants.common.EnumModule;

public class CosmeticModule{

	/*
	 * The control module for the AE2 Module of Plants.
	 * This will handle all registration which is then passed to the respective registry classes.
	 */
	public static List<Block> COSMETIC = new ArrayList<Block>();
	public static BushBase allium_c = new BushBase("allium_c", EnumModule.COSMETIC, null);
	public static BushBase ambrosia_a = new BushBase("ambrosia_a", EnumModule.COSMETIC, null);
	public static BushBase ambrosia_t = new BushBase("ambrosia_t", EnumModule.COSMETIC, null);
	public static BushBase apocynum_c = new BushBase("apocynum_c", EnumModule.COSMETIC, null);
	public static BushBase barbarea_v = new BushBase("barbarea_v", EnumModule.COSMETIC, null);
	public static BushBase daucus_c = new BushBase("daucus_c", EnumModule.COSMETIC, null);
	public static BushBase fabaceae = new BushBase("fabaceae", EnumModule.COSMETIC, null);
	public static BushBase rhanterium_e = new BushBase("rhanterium_e", EnumModule.COSMETIC, null);
	public static BushBase rubus_o = new BushBase("rubus_o", EnumModule.COSMETIC, null);
	public static BushBase rudbeckia_h = new BushBase("rudbeckia_h", EnumModule.COSMETIC, null);
	public static BushBase solanum_c = new BushBase("solanum_c", EnumModule.COSMETIC, null);
	public static BushBase solanum_d = new BushBase("solanum_d", EnumModule.COSMETIC, null);
	public static BushBase veratrum_v = new BushBase("veratrum_v", EnumModule.COSMETIC, null);

	

	
	public static List<Block> getList(){
		COSMETIC.clear();
		COSMETIC.add(allium_c);
		COSMETIC.add(ambrosia_a);
		COSMETIC.add(ambrosia_t);
		COSMETIC.add(apocynum_c);
		COSMETIC.add(barbarea_v);
		COSMETIC.add(daucus_c);
		COSMETIC.add(fabaceae);
		COSMETIC.add(rhanterium_e);
		COSMETIC.add(rubus_o);
		COSMETIC.add(rudbeckia_h);
		COSMETIC.add(solanum_c);
		COSMETIC.add(solanum_d);
		COSMETIC.add(veratrum_v);
		return COSMETIC;
	}
	
	
	
}
