package shadows.plants.item;

import net.minecraft.item.ItemFood;
import shadows.plants.common.EnumModule;
import shadows.plants.util.Data;
import shadows.plants.util.IModularThing;

public class FoodBase extends ItemFood implements IModularThing{

	private EnumModule module;
	
	public FoodBase(String name, EnumModule module_, int amount, float saturation) {
		super(amount, saturation, false);
		setUnlocalizedName(Data.MODID + "." + name);
		setRegistryName(name);
		setCreativeTab(Data.TAB);
		module = module_;
	}

	@Override
	public EnumModule getType() {
		return module;
	}

}
