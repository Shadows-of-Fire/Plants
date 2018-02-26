package shadows.plants2.block.end;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlockSaimbaire extends BlockEndBush {

	public BlockSaimbaire() {
		super("saimbaire");
	}

	@Override
	int getColor() {
		return 0x1612D3;
	}

	@Override
	int getColorFade() {
		return 0x040326;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (!world.isRemote) {
			BlockPos pos2 = pos.add(MathHelper.getInt(rand, -2, 2), -1, MathHelper.getInt(rand, -2, 2));
			if (pos2.equals(pos.down())) return;
			world.destroyBlock(pos2, true);
		}
	}

}
