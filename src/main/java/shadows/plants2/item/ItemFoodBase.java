package shadows.plants2.item;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import shadows.plants2.Plants2;
import shadows.plants2.client.IHasModel;
import shadows.plants2.data.Constants;
import shadows.plants2.init.ModRegistry;

public class ItemFoodBase extends ItemFood implements IHasModel {

	protected final int heal;

	public ItemFoodBase(String name, int amount, float saturationMultiplier) {
		super(amount, saturationMultiplier, false);
		setUnlocalizedName(Constants.MODID + "." + name);
		String tName = Plants2.proxy.translate(getUnlocalizedName() + ".name");
		heal = Plants2.config.getInt("Food Value - " + tName, "Food", amount, 1, 20, "The amount of hunger a " + tName + " will restore.");
		setRegistryName(name);
		setCreativeTab(ModRegistry.TAB);
		ModRegistry.ITEMS.add(this);
	}

	public ItemFoodBase(String name, int amount, float saturationMultiplier, PotionEffect potion, float potionChance) {
		this(name, amount, saturationMultiplier);
		setPotionEffect(potion, potionChance);
	}

	@Override
	public int getHealAmount(ItemStack stack) {
		return heal;
	}
}
