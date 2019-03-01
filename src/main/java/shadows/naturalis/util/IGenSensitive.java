package shadows.naturalis.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import shadows.naturalis.gen.GenHandler;

public interface IGenSensitive {

	/**
	 * Called when this block is generated in the world by {@link GenHandler}
	 */
	void onGenerated(World world, BlockPos pos, IBlockState state);

}
