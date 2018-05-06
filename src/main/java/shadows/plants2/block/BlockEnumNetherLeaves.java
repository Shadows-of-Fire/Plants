package shadows.plants2.block;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shadows.placebo.interfaces.IParticleProvider;
import shadows.placebo.interfaces.ITreeEnum;

public class BlockEnumNetherLeaves<E extends Enum<E> & ITreeEnum & IParticleProvider> extends BlockEnumLeaves<E> {

	public BlockEnumNetherLeaves(String name, BlockEnumSapling<E> sapling, Class<E> clazz, int predicate) {
		super(name, sapling, clazz, predicate);
	}

	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (rand.nextFloat() < 0.1F) world.spawnParticle(state.getValue(property).getParticle(), pos.getX() + 0.5, pos.getY() - 0.8, pos.getZ() + 0.5, getDouble(rand), -0.1, getDouble(rand));
	}

	public static double getDouble(Random rand) {
		return MathHelper.nextDouble(rand, -0.05, 0.05);
	}

	@Override
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 0;
	}

	@Override
	public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 0;
	}

}
