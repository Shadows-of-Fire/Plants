package shadows.plants2.proxy;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface IProxy {

	void preInit(FMLPreInitializationEvent e);

	void init(FMLInitializationEvent e);

	void postInit(FMLPostInitializationEvent e);

	void doCauldronParticles(IBlockState state, World world, BlockPos pos, Random rand);

	void doCauldronInputParticles(BlockPos pos);
	
	String translate(String lang, Object... args);

}
