package shadows.plants2.data;

import net.minecraft.block.properties.PropertyBool;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import shadows.plants2.data.enums.TheBigBookOfEnums.Plants;
import shadows.plants2.init.ModRegistry;

public class PlantConstants {

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
