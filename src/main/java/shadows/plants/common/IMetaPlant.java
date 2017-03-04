package shadows.plants.common;

import net.minecraft.block.state.IBlockState;

public interface IMetaPlant {

	int getMaxData(); // The max value of a PropertyInteger representing
						// metadata

	int getMetaPropValue(IBlockState state);
}
