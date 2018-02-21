package shadows.plants2.block.end;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockAphrireroot extends BlockEndBush {

	public BlockAphrireroot() {
		super("aphrireroot");
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (rand.nextInt(100) == 0 && !world.isRemote) {
			EntityItem i = new EntityItem(world, pos.getX() + 0.5, pos.getY() + 1.2, pos.getZ() + 0.5, new ItemStack(Items.ENDER_PEARL));
			i.setAgeToCreativeDespawnTime();
			world.spawnEntity(i);
		}
	}

}
