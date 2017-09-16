package shadows.plants2.block.base;

import java.util.List;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import shadows.plants2.data.enums.IPropertyEnum;
import shadows.plants2.util.ISpecialPlacement;

public interface IEnumBlock<E extends Enum<E> & IPropertyEnum> extends IEnumBlockAccess<E>, ISpecialPlacement {

	public List<E> getTypes();

	public Item createItemBlock();

	public BlockStateContainer createStateContainer();

	public BlockStateContainer getRealStateContainer();

	public PropertyEnum<E> getProperty();

	default public E getValue(IBlockState state) {
		return state.getValue(getProperty());
	}

}
