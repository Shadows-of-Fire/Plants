package shadows.plants.item;

import net.minecraft.item.ItemFood;
import shadows.plants.common.EnumModule;
import shadows.plants.util.IModularThing;

public class FoodBase extends ItemFood implements IModularThing{

	private EnumModule module;
	
	public FoodBase(String name, EnumModule module_, int amount, float saturation, boolean isWolfFood) {
		super(amount, saturation, isWolfFood);
		module = module_;
	}

	@Override
	public EnumModule getType() {
		return module;
	}

}
