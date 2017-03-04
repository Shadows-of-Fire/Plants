package shadows.plants.registry.modules;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import shadows.plants.block.internal.cosmetic.BlockCrop;
import shadows.plants.block.internal.cosmetic.BlockDoubleHarvestable;
import shadows.plants.block.internal.cosmetic.BlockDoubleMetaBush;
import shadows.plants.block.internal.cosmetic.BlockFruitVine;
import shadows.plants.block.internal.cosmetic.BlockHarvestable;
import shadows.plants.block.internal.cosmetic.BlockMetaBush;
import shadows.plants.common.EnumModule;
import shadows.plants.common.EnumTempZone;
import shadows.plants.common.TempMap;
import shadows.plants.item.common.DummyItem;
import shadows.plants.item.common.FoodItem;
import shadows.plants.item.common.SeedItem;
import shadows.plants.item.internal.cosmetic.ItemCompost;
import shadows.plants.util.Data;

public class CosmeticModule {

	/*
	 * The control module for the Cosmetic Module of Plants. This will handle
	 * all registration which is then passed to the respective registry classes.
	 */
	private static EnumModule module = EnumModule.COSMETIC;
	private static EnumTempZone all = EnumTempZone.ALL;

	public static BlockMetaBush cosmetic_1 = new BlockMetaBush("cosmetic_1", TempMap.cosmetic_1(), 15);
	public static BlockMetaBush cosmetic_2 = new BlockMetaBush("cosmetic_2", TempMap.cosmetic_2(), 15);
	public static BlockMetaBush cosmetic_3 = new BlockMetaBush("cosmetic_3", TempMap.cosmetic_3(), 15);
	public static BlockMetaBush cosmetic_4 = new BlockMetaBush("cosmetic_4", TempMap.cosmetic_4(), 15);
	public static BlockDoubleMetaBush cosmetic_5 = new BlockDoubleMetaBush("cosmetic_5", null, TempMap.cosmetic_5(), 5);
	public static BlockMetaBush cosmetic_6 = new BlockMetaBush("cosmetic_6", TempMap.cosmetic_6(), 15);

	public static FoodItem okra = new FoodItem("okra", module, 3, 1.3f, false);
	public static BlockCrop okra_crop = new BlockCrop("okra_crop", okra, 0, all);
	public static SeedItem okra_seed = new SeedItem("okra_seed", okra_crop, module);

	public static FoodItem pineapple = new FoodItem("pineapple", module, 6, 0.6f, false);
	public static BlockCrop pineapple_crop = new BlockCrop("pineapple_crop", pineapple, 1, all);
	public static SeedItem pineapple_seed = new SeedItem("pineapple_seed", pineapple_crop, module);

	public static FoodItem amaranthus_h = new FoodItem("amaranthus_h", module, 5, 0.3F, false);
	public static BlockCrop amaranthus_h_crop = new BlockCrop("amaranthus_h_crop", amaranthus_h, 2, all);
	public static SeedItem amaranthus_h_seed = new SeedItem("amaranthus_h_seed", amaranthus_h_crop, module);

	public static FoodItem ambrosia_a = new FoodItem("ambrosia_a", module, 3, 0.5f, true);
	public static FoodItem apocynum_c = new FoodItem("apocynum_c", module, 1, 2.0f, false);
	public static FoodItem daucus_c = new FoodItem("daucus_c", module, 4, 1.1f, false);
	public static FoodItem phytolacca_a = new FoodItem("phytolacca_a", module, 5, 1.0f, true);
	public static FoodItem plantago_m = new FoodItem("plantago_m", module, 3, 0.4f, false);
	public static FoodItem rubus_o = new FoodItem("rubus_o", module, 6, 0.5f, false);
	public static FoodItem saffron = new FoodItem("saffron", module, 1, 0.2f, false);
	public static FoodItem solanum_c = new FoodItem("solanum_c", module, 2, 0.5f, true);
	public static FoodItem solanum_d = new FoodItem("solanum_d", module, 3, 0.6f, true);
	public static FoodItem solanum_n = new FoodItem("solanum_n", module, 5, 1.1f, true);
	public static FoodItem alyxia_b = new FoodItem("alyxia_b", module, 2, 1.4f, false);
	public static DummyItem apocynum_c_cloth = new DummyItem("apocynum_c_cloth", module);
	public static FoodItem actaea_p = new FoodItem("actaea_p", module, 4, 1.2f, true);
	public static FoodItem alternanthera_f = new FoodItem("alternanthera_f", module, 7, 0.3f, false);
	public static FoodItem ampelopsis_a = new FoodItem("ampelopsis_a", module, 1, 3.0f, false);
	public static FoodItem akebia_q = new FoodItem("akebia_q", module, 4, 1.3f, false);
	public static DummyItem dye_white = new DummyItem("dye_white", module);
	public static DummyItem dye_blue = new DummyItem("dye_blue", module);
	public static DummyItem dye_brown = new DummyItem("dye_brown", module);
	public static DummyItem dye_black = new DummyItem("dye_black", module);
	public static ItemCompost compost = new ItemCompost("compost");

	public static BlockHarvestable ambrosia_a_crop = new BlockHarvestable("ambrosia_a_crop", ambrosia_a, all);
	public static BlockHarvestable apocynum_c_crop = new BlockHarvestable("apocynum_c_crop", apocynum_c,
			apocynum_c_cloth, all);
	public static BlockHarvestable daucus_c_crop = new BlockHarvestable("daucus_c_crop", daucus_c, all);
	public static BlockHarvestable phytolacca_a_crop = new BlockHarvestable("phytolacca_a_crop", phytolacca_a, all);
	public static BlockHarvestable plantago_m_crop = new BlockHarvestable("plantago_m_crop", plantago_m, all);
	public static BlockHarvestable rubus_o_crop = new BlockHarvestable("rubus_o_crop", rubus_o, all);
	public static BlockHarvestable saffron_crop = new BlockHarvestable("saffron_crop", saffron, all);
	public static BlockHarvestable solanum_c_crop = new BlockHarvestable("solanum_c_crop", solanum_c, all);
	public static BlockHarvestable solanum_d_crop = new BlockHarvestable("solanum_d_crop", solanum_d, all);
	public static BlockHarvestable solanum_n_crop = new BlockHarvestable("solanum_n_crop", solanum_n, all);
	public static BlockDoubleHarvestable alyxia_b_crop = new BlockDoubleHarvestable("alyxia_b_crop", alyxia_b, all);
	public static BlockHarvestable actaea_p_crop = new BlockHarvestable("actaea_p_crop", actaea_p, all);
	public static BlockHarvestable alternanthera_f_crop = new BlockHarvestable("alternanthera_f_crop", alternanthera_f,
			all);
	public static BlockFruitVine adlumia_f = new BlockFruitVine("adlumia_f", Data.EMPTYSTACK, all);
	public static BlockFruitVine afgekia_m = new BlockFruitVine("afgekia_m", Data.EMPTYSTACK, all);
	public static BlockFruitVine androsace_a = new BlockFruitVine("androsace_a", Data.EMPTYSTACK, all);
	public static BlockFruitVine akebia_q_crop = new BlockFruitVine("akebia_q_crop", new ItemStack(akebia_q), all);
	public static BlockFruitVine ampelopsis_a_crop = new BlockFruitVine("ampelopsis_a_crop",
			new ItemStack(ampelopsis_a), all);

	public static List<Item> getItemList() {
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
		list.add(alyxia_b);
		list.add(actaea_p);
		list.add(alternanthera_f);
		list.add(amaranthus_h);
		list.add(amaranthus_h_seed);
		list.add(ampelopsis_a);
		list.add(akebia_q);
		list.add(dye_black);
		list.add(dye_white);
		list.add(dye_brown);
		list.add(dye_blue);
		list.add(compost);
		return list;
	}

	public static List<Block> getBlockList() {
		List<Block> list = new ArrayList<Block>();
		list.clear();
		list.add(cosmetic_1);
		list.add(cosmetic_2);
		list.add(cosmetic_3);
		list.add(cosmetic_4);
		list.add(cosmetic_5);
		list.add(cosmetic_6);
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
		list.add(alyxia_b_crop);
		list.add(amaranthus_h_crop);
		list.add(actaea_p_crop);
		list.add(alternanthera_f_crop);
		list.add(adlumia_f);
		list.add(afgekia_m);
		list.add(androsace_a);
		list.add(akebia_q_crop);
		list.add(ampelopsis_a_crop);
		return list;
	}

}
