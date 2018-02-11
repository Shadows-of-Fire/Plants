package shadows.plants2.compat;

import de.ellpeck.actuallyadditions.mod.blocks.InitBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import shadows.plants2.data.PlantConstants;

public class AAFlowerpot implements IFlowerpotHandler {

	@Override
	public String handleFlowerpot(IBlockState state, ItemStack stack) {
		if (state.getBlock() == InitBlocks.blockBlackLotus) return "black_lotus";

		return "none";
	}

	@Override
	public String getModId() {
		return PlantConstants.AA_ID;
	}

	@Override
	public String getStatePrefix() {
		return "aa_";
	}

}
