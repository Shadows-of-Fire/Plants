package shadows.plants2.gen;

import java.util.Random;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenFlowers;

public class WorldGenCrystals extends WorldGenFlowers {

	private IBlockState state;

	public WorldGenCrystals() {
		super(null, null);
	}

	@Override
	public void setGeneratedBlock(BlockFlower flower, BlockFlower.EnumFlowerType type) {
		this.state = flower.getDefaultState().withProperty(flower.getTypeProperty(), type);
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos position) {
		for (int i = 0; i < 64; ++i) {
			BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

			if (world.isAirBlock(blockpos) && (!world.provider.isNether() || blockpos.getY() < 255) && this.state.getBlock().canPlaceBlockAt(world, blockpos)) {
				world.setBlockState(blockpos, this.state, 2);
			}
		}

		return true;
	}
}