package shadows.plants2.block.forgotten;

import net.minecraft.block.BlockNetherrack;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import shadows.plants2.block.tree.BlockEnumSapling;
import shadows.plants2.block.tree.ITreeEnum;
import shadows.plants2.gen.NetherGen;

public class BlockNetherSapling<E extends Enum<E> & ITreeEnum<E>> extends BlockEnumSapling<E> {

	public BlockNetherSapling(E type) {
		super(EnumPlantType.Nether, type);
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos.down());
		return (world.getBlockState(pos).getBlock().isReplaceable(world, pos) && state.getBlock() instanceof BlockNetherrack) || super.canPlaceBlockAt(world, pos);
	}

	@Override
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
		return NetherGen.isValidSoil(world.getBlockState(pos.down()), world, pos.down(), this);
	}

}
