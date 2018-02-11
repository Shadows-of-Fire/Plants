package shadows.plants2.gen;

import java.util.Random;

import net.minecraft.block.BlockFlower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import shadows.plants2.data.enums.TheBigBookOfEnums.Crystals;
import shadows.plants2.init.ModRegistry;

public class WorldGenCrystals extends WorldGenFlowers {

	private IBlockState dark_shard = ModRegistry.CRYSTAL.getStateFor(Crystals.DARK_CRYSTAL_SHARD);
	private IBlockState shard = ModRegistry.CRYSTAL.getStateFor(Crystals.CRYSTAL_SHARD);

	public WorldGenCrystals() {
		super(null, null);
	}

	@Override
	public void setGeneratedBlock(BlockFlower flower, BlockFlower.EnumFlowerType type) {
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos position) {
		BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
		blockpos = world.getTopSolidOrLiquidBlock(blockpos);
		if (world.isAirBlock(blockpos) && world.getBlockState(blockpos.down()).isSideSolid(world, blockpos.down(), EnumFacing.DOWN)) world.setBlockState(blockpos, rand.nextFloat() >= 0.7F ? dark_shard : shard, 2);

		return true;
	}
}