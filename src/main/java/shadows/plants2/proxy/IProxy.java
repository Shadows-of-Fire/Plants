package shadows.plants2.proxy;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import shadows.plants2.block.BlockFlowerpot;

public interface IProxy {

	default void preInit(FMLPreInitializationEvent e) {
	}

	default void init(FMLInitializationEvent e) {
	}

	default void postInit(FMLPostInitializationEvent e) {
	}

	default void doCauldronParticles(IBlockState state, World world, BlockPos pos, Random rand) {
	}

	default void doCauldronInputParticles(BlockPos pos) {
	}
	
	default void potStateMap(BlockFlowerpot flowerpot) {
		
	}

}
