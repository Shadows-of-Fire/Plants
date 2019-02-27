package shadows.plants2.gen.forgotten;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import shadows.plants2.block.BlockEnumDoubleFlower;
import shadows.plants2.block.BlockEnumDoubleHarvestBush;
import shadows.plants2.data.PlantConfig;
import shadows.plants2.data.enums.TheBigBookOfEnums.BushSet;
import shadows.plants2.init.ModRegistry;

public class BushGen implements IWorldGenerator {

	private static final IBlockState LOG = Loader.isModLoaded("extratrees") ? ForgeRegistries.BLOCKS.getValue(new ResourceLocation("extratrees:shrub_log")).getDefaultState() : Blocks.LOG.getDefaultState();

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator gen, IChunkProvider prov) {
		if (rand.nextInt(20) != 0) return;
		if (PlantConfig.DIM_BL.contains(world.provider.getDimension())) return;
		int posX = chunkX * 16;
		int posZ = chunkZ * 16;
		BlockPos genPos = new BlockPos(posX + MathHelper.getInt(rand, 2, 14), 0, posZ + MathHelper.getInt(rand, 2, 14));
		if (PlantConfig.COMPUTED_BIOME_BL.contains(world.getBiome(genPos))) return;
		genPos = world.getTopSolidOrLiquidBlock(genPos);
		IBlockState soil = world.getBlockState(genPos.down());
		IBlockState state = world.getBlockState(genPos);
		if (state.getBlock().isReplaceable(world, genPos) && state.getMaterial() != Material.WATER && soil.getBlock().canSustainPlant(soil, world, genPos.down(), EnumFacing.DOWN, Blocks.TALLGRASS)) {
			BUSHGENS[rand.nextInt(6)].generate(world, rand, genPos);
		}
	}

	public static final Bush BLACKBERRY_BUSH = new Bush(LOG, ModRegistry.BUSH.getStateFor(BushSet.BLACKBERRY), false);
	public static final Bush BLUEBERRY_BUSH = new Bush(LOG, ModRegistry.BUSH.getStateFor(BushSet.BLUEBERRY), false);
	public static final Bush DECIDUOUS_BUSH = new Bush(LOG, ModRegistry.BUSH.getStateFor(BushSet.DECIDUOUS), false);
	public static final Bush EVERGREEN_BUSH = new Bush(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, EnumType.SPRUCE), ModRegistry.BUSH.getStateFor(BushSet.EVERGREEN), false);
	public static final Bush HUCKLEBERRY_BUSH = new Bush(LOG, ModRegistry.BUSH.getStateFor(BushSet.HUCKLEBERRY), false);
	public static final Bush RASPBERRY_BUSH = new Bush(LOG, ModRegistry.BUSH.getStateFor(BushSet.RASPBERRY), false);
	public static final Bush[] BUSHGENS = { BLACKBERRY_BUSH, BLUEBERRY_BUSH, DECIDUOUS_BUSH, EVERGREEN_BUSH, HUCKLEBERRY_BUSH, RASPBERRY_BUSH };

	public static class Bush extends WorldGenAbstractTree {

		private final IBlockState log;
		private final IBlockState leaf;

		public Bush(IBlockState log, IBlockState leaf, boolean notify) {
			super(notify);
			this.log = log;
			this.leaf = leaf;
		}

		@Override
		public boolean generate(World world, Random rand, BlockPos pos) {
			setBlockAndNotifyAdequately(world, pos, log);
			for (int i = 0; i < 4; i++) {
				EnumFacing face = EnumFacing.HORIZONTALS[i];
				EnumFacing face2 = EnumFacing.HORIZONTALS[i + 1 == 4 ? 0 : i + 1];
				for (int k = -1; k < 2; k++) {
					checkAndLeaf(world, pos.offset(face).offset(face2, k));
					if (k == 0) checkAndLeaf(world, pos.offset(face, 2));
				}
				checkAndLeaf(world, pos.offset(face).up());
			}
			checkAndLeaf(world, pos.up());
			return true;
		}

		private void checkAndLeaf(World world, BlockPos pos) {
			if (isReplaceable(world, pos) && world.getBlockState(pos.down()).getBlockFaceShape(world, pos.down(), EnumFacing.UP) == BlockFaceShape.SOLID) setBlockAndNotifyAdequately(world, pos, leaf);
		}

		@Override
		public boolean isReplaceable(World world, BlockPos pos) {
			Block block = world.getBlockState(pos).getBlock();
			boolean flag = block.isReplaceable(world, pos) || block instanceof BlockBush;
			if (flag && (block instanceof BlockDoublePlant || block instanceof BlockEnumDoubleFlower || block instanceof BlockEnumDoubleHarvestBush)) world.setBlockToAir(pos.up());
			return flag;
		}

	}

}
