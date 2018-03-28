package shadows.plants2.data;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;

public interface IColorProvider {

	/**
	 * This allows things to return a color to be used in the potion cauldron.
	 * @param state The current state, accessed by getStateForPlacement, or null, if this is an Item.
	 * @return The color associated with this object.
	 */
	public EnumDyeColor getColor(IBlockState state);

}
