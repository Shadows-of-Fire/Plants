package shadows.plants.common;

import net.minecraft.block.state.IBlockState;

public interface ITemperaturePlant {
	float getTempMax(IBlockState state);

	float getTempMin(IBlockState state);
}
