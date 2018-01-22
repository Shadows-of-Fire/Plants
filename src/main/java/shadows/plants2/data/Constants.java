package shadows.plants2.data;

import net.minecraft.block.properties.PropertyBool;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import shadows.plants2.data.enums.TheBigBookOfEnums.Plants;

public class Constants {

	public static final PropertyBool INV = PropertyBool.create("inventory");
	public static final PropertyBool ZINV = PropertyBool.create("zinventory");
	public static final String BOTANIA_ID = "botania";
	public static final String FORESTRY_ID = "forestry";

	public static final CreativeTabs TAB = new CreativeTabs("plants") {

		@Override
		public ItemStack getTabIconItem() {
			return Plants.ALLIUM_C.getAsStack();
		}

	};

}
