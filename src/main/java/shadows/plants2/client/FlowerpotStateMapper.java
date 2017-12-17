package shadows.plants2.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import shadows.plants2.block.BlockFlowerpot;

public class FlowerpotStateMapper implements IStateMapper {

	@Override
	public Map<IBlockState, ModelResourceLocation> putStateModelLocations(Block block) {
		if (!(block instanceof BlockFlowerpot)) throw new IllegalArgumentException("pls stop");
		BlockFlowerpot flowerpot = (BlockFlowerpot) block;
		Map<IBlockState, ModelResourceLocation> map = new HashMap<>();
		List<IBlockState> states = flowerpot.getRealStateContainer().getValidStates();
		for (IBlockState state : states) {
			map.put(state, state.getValue(BlockFlowerpot.PROP).genMRL());
		}
		return map;
	}

}
