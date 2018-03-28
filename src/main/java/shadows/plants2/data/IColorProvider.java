package shadows.plants2.data;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;

public interface IColorProvider {

	public EnumDyeColor getColor(IBlockState state);

}
