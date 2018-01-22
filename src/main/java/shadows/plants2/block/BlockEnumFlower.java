package shadows.plants2.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.event.RegistryEvent.Register;
import shadows.placebo.interfaces.IFlowerEnum;
import shadows.placebo.interfaces.IHasRecipe;
import shadows.plants2.Plants2;
import shadows.plants2.util.PlantUtil;

public class BlockEnumFlower<E extends Enum<E> & IFlowerEnum> extends BlockEnumBush<E> implements IHasRecipe {

	public BlockEnumFlower(EnumPlantType plantType, E type) {
		super(type.getName(), plantType, type);
	}

	@Override
	public void initRecipes(Register<IRecipe> event) {
		if (type.useForRecipes()) Plants2.HELPER.addShapeless(PlantUtil.getDyeForEnum(type.getColor(), 1), new ItemStack(this));
	}

	public EnumDyeColor getColor(IBlockState state) {
		return type.getColor();
	}

}
