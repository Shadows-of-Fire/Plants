package shadows.plants2.compat;

import net.minecraft.block.state.IBlockState;

public interface IFlowerpotHandler {
	
	public String handleFlowerpot(IBlockState state);

	public String getModId();

	public String getStatePrefix();

	public default String getFinalName(IBlockState state) {
		return getStatePrefix() + handleFlowerpot(state);
	}

}
