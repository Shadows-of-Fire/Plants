package shadows.plants.registry.modules;


import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import shadows.plants.block.internal.cosmetic.BlockCrop;
import shadows.plants.block.internal.cosmetic.BlockDoubleMetaBush;
import shadows.plants.block.internal.cosmetic.BlockHarvestable;
import shadows.plants.block.internal.cosmetic.BlockMetaBush;
import shadows.plants.common.EnumModule;
import shadows.plants.common.EnumTempZone;
import shadows.plants.common.TempMap;
import shadows.plants.item.FoodBase;
import shadows.plants.item.SeedBase;
import shadows.plants.item.UselessItemBase;

public class CosmeticModule{

	/*
	 * The control module for the Cosmetic Module of Plants.
	 * This will handle all registration which is then passed to the respective registry classes.
	 */
	private static EnumModule module = EnumModule.COSMETIC;
	private static EnumTempZone all = EnumTempZone.ALL;
	
	public static BlockMetaBush cosmetic_1 = new BlockMetaBush("cosmetic_1", TempMap.cosmetic_1());
	public static BlockMetaBush cosmetic_2 = new BlockMetaBush("cosmetic_2", TempMap.cosmetic_2());
	public static BlockMetaBush cosmetic_3 = new BlockMetaBush("cosmetic_3", TempMap.cosmetic_3());
	public static BlockMetaBush cosmetic_4 = new BlockMetaBush("cosmetic_4", TempMap.cosmetic_4());
	public static BlockDoubleMetaBush cosmetic_5 = new BlockDoubleMetaBush("cosmetic_5", null, TempMap.cosmetic_5());
	
	public static FoodBase okra = new FoodBase("okra", module, 3, 1.3f, false);
	public static BlockCrop okra_crop = new BlockCrop("okra_crop", okra, 0, all);
	public static SeedBase okra_seed = new SeedBase("okra_seed", okra_crop, module);
	
	public static FoodBase pineapple = new FoodBase("pineapple", module, 6, 0.6f, false);
	public static BlockCrop pineapple_crop = new BlockCrop("pineapple_crop", pineapple, 1, all);
	public static SeedBase pineapple_seed = new SeedBase("pineapple_seed", pineapple_crop, module);
	
	public static FoodBase ambrosia_a = new FoodBase("ambrosia_a", module, 3, 0.5f, true);
	public static FoodBase apocynum_c = new FoodBase("apocynum_c", module, 1, 2.0f, false);
	public static FoodBase daucus_c = new FoodBase("daucus_c", module, 4, 1.1f, false);
	public static FoodBase phytolacca_a = new FoodBase("phytolacca_a", module, 5, 1.0f, true);
	public static FoodBase plantago_m = new FoodBase("plantago_m", module, 3, 0.4f, false);
	public static FoodBase rubus_o = new FoodBase("rubus_o", module, 6, 0.5f, false);
	public static FoodBase saffron = new FoodBase("saffron", module, 1, 0.2f, false);
	public static FoodBase solanum_c = new FoodBase("solanum_c", module, 2, 0.5f, true);
	public static FoodBase solanum_d = new FoodBase("solanum_d", module, 3, 0.6f, true);
	public static FoodBase solanum_n = new FoodBase("solanum_n", module, 5, 1.1f, true);
	public static UselessItemBase apocynum_c_cloth = new UselessItemBase("apocynum_c_cloth", module);
	
	public static BlockHarvestable ambrosia_a_crop = new BlockHarvestable("ambrosia_a_crop", ambrosia_a, all);
	public static BlockHarvestable apocynum_c_crop = new BlockHarvestable("apocynum_c_crop", apocynum_c, apocynum_c_cloth, all);
	public static BlockHarvestable daucus_c_crop = new BlockHarvestable("daucus_c_crop", daucus_c, all);
	public static BlockHarvestable phytolacca_a_crop = new BlockHarvestable("phytolacca_a_crop", phytolacca_a, all);
	public static BlockHarvestable plantago_m_crop = new BlockHarvestable("plantago_m_crop", plantago_m, all);
	public static BlockHarvestable rubus_o_crop = new BlockHarvestable("rubus_o_crop", rubus_o, all);
	public static BlockHarvestable saffron_crop = new BlockHarvestable("saffron_crop", saffron, all);
	public static BlockHarvestable solanum_c_crop = new BlockHarvestable("solanum_c_crop", solanum_c, all);
	public static BlockHarvestable solanum_d_crop = new BlockHarvestable("solanum_d_crop", solanum_d, all);
	public static BlockHarvestable solanum_n_crop = new BlockHarvestable("solanum_n_crop", solanum_n, all);
	
	
	public static List<Item> getItemList(){
	List<Item> list = new ArrayList<Item>();
		list.clear();
		list.add(okra_seed);
		list.add(okra);
		list.add(pineapple);
		list.add(pineapple_seed);
		list.add(ambrosia_a);
		list.add(apocynum_c);
		list.add(daucus_c);
		list.add(phytolacca_a);
		list.add(plantago_m);
		list.add(rubus_o);
		list.add(saffron);
		list.add(solanum_c);
		list.add(solanum_d);
		list.add(solanum_n);
		list.add(apocynum_c_cloth);
		return list;
	}
	
	
	public static List<Block> getBlockList(){
		List<Block> list = new ArrayList<Block>();
		list.clear();
		list.add(cosmetic_1);
		list.add(cosmetic_2);
		list.add(cosmetic_3);
		list.add(cosmetic_4);
		list.add(cosmetic_5);
		list.add(pineapple_crop);
		list.add(okra_crop);
		list.add(ambrosia_a_crop);
		list.add(apocynum_c_crop);
		list.add(daucus_c_crop);
		list.add(phytolacca_a_crop);
		list.add(plantago_m_crop);
		list.add(rubus_o_crop);
		list.add(saffron_crop);
		list.add(solanum_c_crop);
		list.add(solanum_d_crop);
		list.add(solanum_n_crop);
		return list;
	}
	
	
	
}
