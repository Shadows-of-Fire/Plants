package shadows.naturalis.block;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import shadows.naturalis.util.Color;
import shadows.naturalis.util.EnumGenType;

public class BlockDesertBush extends BlockNaturalBush {

	public BlockDesertBush(String name, Color color) {
		super(name, color);
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Desert;
	}

	@Override
	public EnumGenType getGenType() {
		return EnumGenType.DESERT;
	}

}
