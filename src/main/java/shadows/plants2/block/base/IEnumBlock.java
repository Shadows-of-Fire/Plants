package shadows.plants2.block.base;

import java.util.List;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import shadows.plants2.data.enums.IPropertyEnum;

public interface IEnumBlock<E extends Enum<E> & IPropertyEnum> {

	public List<E> getTypes();

	public Item createItemBlock();

	public BlockStateContainer createStateContainer();

	public BlockStateContainer getRealStateContainer();

	public PropertyEnum<E> getProperty();

	IBlockState getStateFor(E e);

	public boolean placeStateAt(IBlockState state, World world, BlockPos pos);

}
