package shadows.plants2.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import shadows.plants2.data.Constants;
import shadows.plants2.data.IPostInitUpdate;
import shadows.plants2.data.enums.ITreeEnum;

public class EnumTreeGen extends WorldGenTrees implements IPostInitUpdate {

	final ITreeEnum k;

	public EnumTreeGen(boolean notify, int minHeight, IBlockState log, IBlockState leaf, ITreeEnum enumToAssignTo) {
		super(notify, minHeight, log, leaf, false);
		Constants.UPDATES.add(this);
		k = enumToAssignTo;
		TreeGenerator.LIST.add(this);
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		k.setTreeGen(this);
	}

	public static class TreeGenerator implements IWorldGenerator {

		public static final List<EnumTreeGen> LIST = new ArrayList<EnumTreeGen>();

		@Override
		public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
			if (random.nextInt(MathHelper.clamp(chunkZ % 10 + 5 + chunkX % 15, 10, 30)) != 0)
				return;
			int posX = chunkX * 16;
			int posZ = chunkZ * 16;
			BlockPos genPos = new BlockPos(posX + MathHelper.getInt(random, 6, 10), 0, posZ + MathHelper.getInt(random, 6, 10));
			genPos = world.getTopSolidOrLiquidBlock(genPos);
			IBlockState state = world.getBlockState(genPos.down());
			if (state.getBlock().canSustainPlant(state, world, genPos.down(), EnumFacing.DOWN, Blocks.TALLGRASS)) {
				LIST.get(random.nextInt(LIST.size())).generate(world, random, genPos);
			}
		}
	}

}
