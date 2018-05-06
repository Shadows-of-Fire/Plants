package shadows.plants2.block;

import net.minecraft.block.SoundType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import shadows.placebo.interfaces.ITreeEnum;

public class BlockEnumNetherLog<E extends Enum<E> & ITreeEnum> extends BlockEnumLog<E> {

	public BlockEnumNetherLog(String name, SoundType s, float hard, float res, Class<E> enumClass, int predicate) {
		super(name, s, hard, res, enumClass, predicate);
	}

	public BlockEnumNetherLog(String name, Class<E> enumClass, int predicate) {
		this(name, SoundType.WOOD, 2F, 1F, enumClass, predicate);
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
