package shadows.plants2.compat;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

public interface IFlowerpotHandler {
	
	public String handleFlowerpot(IBlockState state, ItemStack stack);

	public String getModId();

	public String getStatePrefix();

	public default String getFinalName(IBlockState state, ItemStack stack) {
		return getStatePrefix() + handleFlowerpot(state, stack);
	}

}
