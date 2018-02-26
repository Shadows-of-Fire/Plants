package shadows.plants2.block.end;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import shadows.plants2.init.ModRegistry;

public class BlockBriblebum extends BlockEndBush {

	static ItemStack arrow = ItemStack.EMPTY;

	public BlockBriblebum() {
		super("briblebum");
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (arrow.isEmpty()) {
			arrow = new ItemStack(Items.TIPPED_ARROW);
			PotionUtils.addPotionToItemStack(arrow, ModRegistry.LEV);
		}
		if (!world.isRemote && rand.nextInt(4) == 0) {
			EntityTippedArrow a = new EntityTippedArrow(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
			a.setPotionEffect(arrow);
			a.shoot(MathHelper.nextDouble(rand, -1, 1), MathHelper.nextDouble(rand, 0.15, 0.3), MathHelper.nextDouble(rand, -1, 1), MathHelper.nextFloat(rand, 0.8F, 1.5F), rand.nextFloat());
			world.spawnEntity(a);
		}
	}

	@Override
	int getColor() {
		return 0xFC2C33;
	}

	@Override
	int getColorFade() {
		return 0x0377C4;
	}

}
