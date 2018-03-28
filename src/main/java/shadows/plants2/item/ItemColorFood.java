package shadows.plants2.item;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent.Register;
import shadows.placebo.interfaces.IHasRecipe;
import shadows.plants2.Plants2;
import shadows.plants2.data.IColorProvider;
import shadows.plants2.util.PlantUtil;

public class ItemColorFood extends ItemFoodBase implements IColorProvider, IHasRecipe {

	EnumDyeColor color;

	public ItemColorFood(String name, int amount, float saturationMultiplier, EnumDyeColor color) {
		super(name, amount, saturationMultiplier);
		this.color = color;
	}

	@Override
	public EnumDyeColor getColor(IBlockState state) {
		return color;
	}

	@Override
	public void initRecipes(Register<IRecipe> e) {
		Plants2.HELPER.addSimpleShapeless(PlantUtil.getDyeForEnum(color), this, 1);
	}

}
