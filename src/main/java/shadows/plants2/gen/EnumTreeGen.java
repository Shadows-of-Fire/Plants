package shadows.plants2.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
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
import shadows.plants2.block.base.IEnumBlockAccess;
import shadows.plants2.data.enums.ITreeEnum;

public class EnumTreeGen<E extends ITreeEnum> extends WorldGenTrees {

	public EnumTreeGen(boolean notify, int minHeight, IEnumBlockAccess<E> log, IEnumBlockAccess<E> leaf, E assign) {
		super(notify, minHeight, log.getStateFor(assign), leaf.getStateFor(assign), false);
		TreeGenerator.LIST.add(this);
		assign.setTreeGen(this);
	}

	//If you extend this class and override generate, use this for super.
	public EnumTreeGen(E assign) {
		super(false, 0, null, null, false);
		TreeGenerator.LIST.add(this);
		assign.setTreeGen(this);
	}

	public boolean canGen(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos.down());
		return world.getBlockState(pos).getMaterial() != Material.WATER && (state.getBlock().canSustainPlant(state, world, pos.down(), EnumFacing.UP, Blocks.TALLGRASS) || state.getBlock().canSustainPlant(state, world, pos.down(), EnumFacing.UP, Blocks.DEADBUSH));
	}

	public static class TreeGenerator implements IWorldGenerator {

		public static final List<EnumTreeGen<?>> LIST = new ArrayList<>();

		@Override
		public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
			if (new Random(chunkZ ^ 3 + 5 + chunkX ^ 3 + random.nextInt(15060)).nextFloat() >= 0.16F)
				return;
			int posX = chunkX * 16;
			int posZ = chunkZ * 16;
			BlockPos genPos = new BlockPos(posX + MathHelper.getInt(random, 6, 10), 0, posZ + MathHelper.getInt(random, 6, 10));
			genPos = world.getTopSolidOrLiquidBlock(genPos);
			EnumTreeGen<?> toGen = LIST.get(random.nextInt(LIST.size()));
			if (toGen.canGen(world, genPos)) {
				toGen.generate(world, random, genPos);
			}
		}
	}

}
