package shadows.plants2.data;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.properties.PropertyBool;

public class Constants {

	public static final String MODID = "plants2";
	public static final String MODNAME = "Plants";
	public static final String VERSION = "2.0.0";
	public static final String DEPS = "after:botania;after:forestry";
	public static final PropertyBool INV = PropertyBool.create("inventory");
	public static final PropertyBool ZINV = PropertyBool.create("zinventory");
	public static final List<IPostInitUpdate> UPDATES = new ArrayList<IPostInitUpdate>();
	public static final String BOTANIA_ID = "botania";
	public static final String FORESTRY_ID = "forestry";
	public static final String TF_ID = "twilightforest";

}
