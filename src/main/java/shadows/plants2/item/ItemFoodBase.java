package shadows.plants2.item;

import net.minecraft.item.ItemFood;
import net.minecraft.potion.PotionEffect;
import shadows.plants2.client.IHasModel;
import shadows.plants2.data.Constants;
import shadows.plants2.init.ModRegistry;

public class ItemFoodBase extends ItemFood implements IHasModel {

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
