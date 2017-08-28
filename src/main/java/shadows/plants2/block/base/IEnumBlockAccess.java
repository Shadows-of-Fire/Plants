package shadows.plants2.block.base;

import net.minecraft.block.state.IBlockState;
import shadows.plants2.data.enums.IPropertyEnum;

public interface IEnumBlockAccess<E extends IPropertyEnum> {

	IBlockState getStateFor(E e);

}
