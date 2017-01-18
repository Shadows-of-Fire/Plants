package shadows.plants.block.addon.ae2;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants.block.SoilBase;
import shadows.plants.common.EnumModule;
import shadows.plants.util.Util;

public class AESoil extends SoilBase{
	
	public AESoil(){
		super("ae2soil", EnumModule.APPLIED);
		setTickRandomly(true);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World world, BlockPos pos, Random rand)
    {
		for (int i = 0; i < 10; i++) Util.spawnParticlesAtBlock(world, pos, EnumParticleTypes.ENCHANTMENT_TABLE);
	}
	
	
	
	
}




