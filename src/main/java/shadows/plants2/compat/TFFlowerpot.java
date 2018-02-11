package shadows.plants2.compat;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import shadows.plants2.data.PlantConstants;
import twilightforest.block.BlockTFPlant;
import twilightforest.block.BlockTFSapling;
import twilightforest.block.TFBlocks;

public class TFFlowerpot implements IFlowerpotHandler {

	@Override
	public String handleFlowerpot(IBlockState state, ItemStack stack) {
		if (state.getBlock() == TFBlocks.plant) return state.getValue(BlockTFPlant.VARIANT).getName();
		if (state.getBlock() == TFBlocks.sapling) return state.getValue(BlockTFSapling.TF_TYPE).getName() + "_sapling";
		if (state.getBlock() == TFBlocks.hugeWaterLily) return "waterlily";

		return "none";
	}

	@Override
	public String getModId() {
		return PlantConstants.TF_ID;
	}

	@Override
	public String getStatePrefix() {
		return "tf_";
	}

}
