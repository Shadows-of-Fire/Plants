package shadows.plants2.item;

import net.minecraft.item.ItemFood;
import net.minecraft.potion.PotionEffect;
import shadows.plants2.client.IHasModel;
import shadows.plants2.data.Constants;
import shadows.plants2.init.ModRegistry;

public class ItemFoodBase extends ItemFood implements IHasModel {//I really wanted to just use 1 giant item for this but "other mod compat blah"

	public ItemFoodBase(String name, int amount, float saturationMultiplier) {
		super(amount, saturationMultiplier, false);
		setRegistryName(name);
		setUnlocalizedName(Constants.MODID + "." + name);
		setCreativeTab(ModRegistry.TAB);
		ModRegistry.ITEMS.add(this);
	}

	public ItemFoodBase(String name, int amount, float saturationMultiplier, PotionEffect potion, float potionChance) {
		this(name, amount, saturationMultiplier);
		setPotionEffect(potion, potionChance);
	}

}
