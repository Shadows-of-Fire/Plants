package shadows.plants2.potion;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import shadows.plants2.data.Constants;

public class PlantsBrewingRecipe implements IBrewingRecipe {

	private Item input;
	private Item ingred;
	private Item output;

	public PlantsBrewingRecipe(Item input, Item ingred, Item output) {
		this.input = input;
		this.ingred = ingred;
		this.output = output;
	}

	@Override
	public boolean isInput(ItemStack input) {
		return PotionUtils.getPotionTypeFromNBT(input.getTagCompound() == null ? new NBTTagCompound() : input.getTagCompound()).getRegistryName().getResourceDomain().equals(Constants.MODID) && input.getItem() == this.input;
	}

	@Override
	public boolean isIngredient(ItemStack ingredient) {
		return ingredient.getItem() == ingred;
	}

	@Override
	public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
		NBTTagCompound tag = input.getTagCompound();
		ItemStack out = new ItemStack(output);
		out.setTagCompound(tag);
		return out;
	}

}
