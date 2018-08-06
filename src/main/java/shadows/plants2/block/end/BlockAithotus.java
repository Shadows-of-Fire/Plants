package shadows.plants2.block.end;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlockAithotus extends BlockEndBush {

	public BlockAithotus() {
		super("small_flower_aithotus");
	}

	@Override
	public float getEnchantPowerBonus(World world, BlockPos pos) {
		return 7;
	}

	@Override
	public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (world.isRemote) return;
		int x = MathHelper.getInt(world.rand, -4, 4);
		int z = MathHelper.getInt(world.rand, -4, 4);
		if (x == 0) x = 2;
		if (z == 0) z = 2;
		BlockPos pos2 = world.getTopSolidOrLiquidBlock(pos.add(x, 0, z));
		if (this.canPlaceBlockAt(world, pos2)) {
			world.setBlockToAir(pos);
			world.playSound(null, pos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1, 0.8F);
			world.setBlockState(pos2, state);
			if (entity instanceof EntityLivingBase) ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 200, 2));
		}
	}

	@Override
	int getColor() {
		return 0x90D1F9;
	}

	@Override
	int getColorFade() {
		return 0x9092F9;
	}

}
