package shadows.plants.item.common;

import net.minecraft.item.Item;
import shadows.plants.common.EnumModule;
import shadows.plants.common.IModularThing;
import shadows.plants.util.Data;

public class DummyItem extends Item implements IModularThing {

	private EnumModule module;

	public DummyItem(String name, EnumModule module_) {
		setRegistryName(name);
		setUnlocalizedName(Data.MODID + "." + name);
		setCreativeTab(Data.TAB_I);
		module = module_;
	}

	@Override
	public EnumModule getType() {
		return module;
	}
}
