package shadows.plants.block.internal.tool;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import shadows.plants.block.FacingBush;
import shadows.plants.common.EnumModule;
import shadows.plants.common.EnumTempZone;
import shadows.plants.util.Config;

public class BlockCatapultBush extends FacingBush{
	
	public BlockCatapultBush(String name) {
		super(name, EnumModule.TOOL, null);
	}

	@Override
	public float getTempMax(IBlockState state) {
		return EnumTempZone.ALL.getMax();
	}

	@Override
	public float getTempMin(IBlockState state) {
		return EnumTempZone.ALL.getMin();
	}

	@Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity){
		EnumFacing face = state.getValue(FACING);
		int x = face.getDirectionVec().getX();
		int y = face.getDirectionVec().getY();
		int z = face.getDirectionVec().getZ();
		entity.setVelocity(x * Config.catapultPower, y * Config.catapultPower + 1, z * Config.catapultPower);
    }
	
	
}
