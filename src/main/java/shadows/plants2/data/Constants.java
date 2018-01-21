package shadows.plants2.data;

import net.minecraft.block.properties.PropertyBool;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import shadows.plants2.data.enums.TheBigBookOfEnums.Plants;
import shadows.plants2.init.ModRegistry;

public class Constants {

	public static final String MODID = "plants2";
	public static final String MODNAME = "Plants";
	public static final String VERSION = "2.3.0";
	public static final String DEPS = "required-after:placebo@[1.1.4,);after:botania;after:forestry;after:twilightforest;after:actuallyadditions;after:botany";
	public static final PropertyBool INV = PropertyBool.create("inventory");
	public static final PropertyBool ZINV = PropertyBool.create("zinventory");
	public static final String BOTANIA_ID = "botania";
	public static final String FORESTRY_ID = "forestry";
	public static final String TF_ID = "twilightforest";
	public static final String AA_ID = "actuallyadditions";
	public static final String BOTANY_ID = "botany";

	public static final CreativeTabs TAB = new CreativeTabs("plants") {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModRegistry.PLANT_1, 1, Plants.ALLIUM_C.ordinal() % 16);
		}

	};

}
