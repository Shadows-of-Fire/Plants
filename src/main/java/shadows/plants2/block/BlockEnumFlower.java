package shadows.plants2.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.event.RegistryEvent.Register;
import shadows.plants2.block.base.BlockEnumBush;
import shadows.plants2.data.IHasRecipe;
import shadows.plants2.data.enums.IFlowerEnum;
import shadows.plants2.util.PlantUtil;
import shadows.plants2.util.RecipeHelper;

public class BlockEnumFlower<E extends Enum<E> & IFlowerEnum> extends BlockEnumBush<E> implements IHasRecipe {

	public BlockEnumFlower(String name, EnumPlantType type, Class<E> enumClass, int predicate) {
		super(name, type, enumClass, predicate);
	}

	@Override
	public void initRecipes(Register<IRecipe> event) {
		for (E e : getTypes()) {
			if (e.useForRecipes()) RecipeHelper.addShapeless(PlantUtil.getDyeForEnum(e.getColor(), 1), new ItemStack(this, 1, e.getMetadata()));
		}
	}

	public EnumDyeColor getColor(IBlockState state) {
		return state.getValue(getProperty()).getColor();
	}

}
