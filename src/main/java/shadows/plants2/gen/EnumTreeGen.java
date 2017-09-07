package shadows.plants2.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
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
import shadows.plants2.block.BlockEnumLeaves;
import shadows.plants2.block.base.IEnumBlockAccess;
import shadows.plants2.data.enums.ITreeEnum;

public class EnumTreeGen<E extends ITreeEnum> extends WorldGenTrees {

	protected final Block SAP;
	protected final IBlockState leaf;
	protected final IBlockState log;
	protected int minHeight = 0;

	public EnumTreeGen(boolean notify, int minHeight, IEnumBlockAccess<E> log, IEnumBlockAccess<E> leaf, E assign) {
		super(notify, minHeight, log.getStateFor(assign), leaf.getStateFor(assign), false);
		this.log = log.getStateFor(assign);
		this.leaf = leaf.getStateFor(assign);
		this.minHeight = minHeight;
		TreeGenerator.LIST.add(this);
		assign.setTreeGen(this);
		SAP = ((BlockEnumLeaves<?>) leaf).getSapling();
	}

	//If you extend this class and override generate/canGen, use this for super.
	public EnumTreeGen(E assign) {
		super(false, 0, null, null, false);
		TreeGenerator.LIST.add(this);
		assign.setTreeGen(this);
		SAP = Blocks.TALLGRASS;
		leaf = null;
		log = null;
	}

	public boolean canGen(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos.down());
		return world.getBlockState(pos).getMaterial() != Material.WATER && (SAP.canPlaceBlockAt(world, pos) || state.getBlock().canSustainPlant(state, world, pos.down(), EnumFacing.UP, Blocks.DEADBUSH));
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		int i = rand.nextInt(3) + this.minHeight;
		boolean flag = true;

		if (position.getY() >= 1 && position.getY() + i + 1 <= worldIn.getHeight()) {
			for (int j = position.getY(); j <= position.getY() + 1 + i; ++j) {
				int k = 1;

				if (j == position.getY()) {
					k = 0;
				}

				if (j >= position.getY() + 1 + i - 2) {
					k = 2;
				}

				BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

				for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
					for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
						if (j >= 0 && j < worldIn.getHeight()) {
							if (!this.isReplaceable(worldIn, blockpos$mutableblockpos.setPos(l, j, i1))) {
								flag = false;
							}
						} else {
							flag = false;
						}
					}
				}
			}

			if (!flag) {
				return false;
			} else {
				IBlockState state = worldIn.getBlockState(position.down());

				if (SAP.canPlaceBlockAt(worldIn, position) && position.getY() < worldIn.getHeight() - i - 1) {
					state.getBlock().onPlantGrow(state, worldIn, position.down(), position);

					for (int i3 = position.getY() - 3 + i; i3 <= position.getY() + i; ++i3) {
						int i4 = i3 - (position.getY() + i);
						int j1 = 1 - i4 / 2;

						for (int k1 = position.getX() - j1; k1 <= position.getX() + j1; ++k1) {
							int l1 = k1 - position.getX();

							for (int i2 = position.getZ() - j1; i2 <= position.getZ() + j1; ++i2) {
								int j2 = i2 - position.getZ();

								if (Math.abs(l1) != j1 || Math.abs(j2) != j1 || rand.nextInt(2) != 0 && i4 != 0) {
									BlockPos blockpos = new BlockPos(k1, i3, i2);
									state = worldIn.getBlockState(blockpos);

									if (state.getBlock().isAir(state, worldIn, blockpos) || state.getBlock().isLeaves(state, worldIn, blockpos) || state.getMaterial() == Material.VINE) {
										this.setBlockAndNotifyAdequately(worldIn, blockpos, this.leaf);
									}
								}
							}
						}
					}

					for (int j3 = 0; j3 < i; ++j3) {
						BlockPos upN = position.up(j3);
						state = worldIn.getBlockState(upN);

						if (state.getBlock().isAir(state, worldIn, upN) || state.getBlock().isLeaves(state, worldIn, upN) || state.getMaterial() == Material.VINE) {
							this.setBlockAndNotifyAdequately(worldIn, position.up(j3), this.log);
						}
					}
					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
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
