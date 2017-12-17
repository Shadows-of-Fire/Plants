package shadows.plants2.proxy;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy implements IProxy {

	@Override
	public void preInit(FMLPreInitializationEvent e) {

	}

	@Override
	public void init(FMLInitializationEvent e) {

	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {

	}

	@Override
	public void doCauldronParticles(IBlockState state, World world, BlockPos pos, Random rand) {
	}

	@Override
	public void doCauldronInputParticles(BlockPos pos) {
	}

}
