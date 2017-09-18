package shadows.plants2.block.forgotten;

import net.minecraft.block.BlockNetherrack;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import shadows.plants2.block.BlockEnumSapling;
import shadows.plants2.data.enums.ITreeEnum;
import shadows.plants2.gen.NetherGen;

public class BlockNetherSapling<E extends Enum<E> & ITreeEnum> extends BlockEnumSapling<E> {

	public BlockNetherSapling(String name, Class<E> clazz, int predicate) {
		super(name, EnumPlantType.Nether, clazz, predicate);
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
