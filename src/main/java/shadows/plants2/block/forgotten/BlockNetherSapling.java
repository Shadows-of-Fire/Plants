package shadows.plants2.block.forgotten;

import net.minecraft.block.BlockNetherrack;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import shadows.plants2.block.BlockEnumSapling;
import shadows.plants2.data.enums.ILogBasedPropertyEnum;

public class BlockNetherSapling<E extends Enum<E> & ILogBasedPropertyEnum> extends BlockEnumSapling<E> {

	public BlockNetherSapling(String name, Class<E> clazz, int predicate) {
		super(name, clazz, predicate);
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos.down());
		return world.getBlockState(pos).getBlock().isReplaceable(world, pos) && (state.getBlock() instanceof BlockNetherrack || state.getMaterial() == Material.GROUND || state.getMaterial() == Material.GRASS);
	}

}
