package shadows.plants2.block.end;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlockAphrireroot extends BlockEndBush {

	public BlockAphrireroot() {
		super("aphrireroot");
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (rand.nextInt(100) == 0) spawnAsEntity(world, pos, new ItemStack(Items.ENDER_PEARL));
	}

	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (rand.nextFloat() < 0.03F) world.spawnParticle(EnumParticleTypes.END_ROD, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, MathHelper.nextFloat(rand, -0.09F, 0.09F), MathHelper.nextFloat(rand, -0.09F, 0.09F), MathHelper.nextFloat(rand, -0.09F, 0.09F));
	}

}
