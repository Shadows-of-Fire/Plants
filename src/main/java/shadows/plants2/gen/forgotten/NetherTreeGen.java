package shadows.plants2.gen.forgotten;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockNetherrack;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import shadows.plants2.data.enums.ILogBasedPropertyEnum;
import shadows.plants2.gen.EnumBasedTreeGen;

public class NetherTreeGen extends EnumBasedTreeGen implements IWorldGenerator {
	IBlockState leaf;
	IBlockState log;
	int dir = 0;

	public NetherTreeGen(IBlockState log, IBlockState leaf, ILogBasedPropertyEnum enumToAssignTo) {
		super(true, 0, log, leaf, false, enumToAssignTo);
		this.log = log;
		this.leaf = leaf;
	}

	public boolean generate(World world, Random random, BlockPos pos) {
		if (world.isRemote)
			return false;

		int l = random.nextInt(3) + 2;
		int i = pos.getX();
		int j = pos.getY();
		int k = pos.getZ();
		BlockPos.MutableBlockPos m = new BlockPos.MutableBlockPos(pos);

		if (j < 1 || j + l + 1 > 256) {
			return false;
		}

		j++;

		int brightest = world.getLightFromNeighbors(m.setPos(i, j, k));
		int next = 0;
		setBlockAndNotifyAdequately(world, m.setPos(i, j - 1, k), log);

		for (int x = -1; x < 2; x++) {
			for (int y = -1; y < 2; y++) {
				next = world.getLightFromNeighbors(m.setPos(i + x, j, k + y));
				if (next > brightest) {
					brightest = next;
					if (x == -1 && y == x)
						dir = 1;
					else if (x == -1 && y == 0)
						dir = 2;
					else if (x == -1 && y == 1)
						dir = 3;
					else if (x == 0 && y == 1)
						dir = 4;
					else if (x == 1 && y == x)
						dir = 5;
					else if (x == 1 && y == 0)
						dir = 6;
					else if (x == 1 && y == -1)
						dir = 7;
					else if (x == 0 && y == -1)
						dir = 8;
				}
			}
		}

		for (int l1 = 0; l1 < l; l1++) {
			if (dir == 0) {
				if (world.isAirBlock(m.setPos(i, j + l1, k)) || l1 == 0)
					setBlockAndNotifyAdequately(world, m.setPos(i, j + l1, k), log);
				if (l1 == l - 1)
					genLeaves(world, random, m.setPos(i, j + l1, k), leaf);
				for (int j3 = 0; j3 <= 1; j3++) {
					if (random.nextInt(8) == 0) {
						branches(world, random, i, j + j3, k, 1, 0, leaf, log);
					}
					if (random.nextInt(8) == 0) {
						branches(world, random, i, j + j3, k, -1, 0, leaf, log);
					}
					if (random.nextInt(8) == 0) {
						branches(world, random, i, j + j3, k, -1, 1, leaf, log);
					}
					if (random.nextInt(8) == 0) {
						branches(world, random, i, j + j3, k, 0, 1, leaf, log);
					}
					if (random.nextInt(8) == 0) {
						branches(world, random, i, j + j3, k, 0, -1, leaf, log);
					}
					if (random.nextInt(8) == 0) {
						branches(world, random, i, j + j3, k, 1, 1, leaf, log);
					}
					if (random.nextInt(8) == 0) {
						branches(world, random, i, j + j3, k, -1, -1, leaf, log);
					}
					if (random.nextInt(8) == 0) {
						branches(world, random, i, j + j3, k, 1, -1, leaf, log);
					}
				}
			} else if (dir == 1) {
				if (world.isAirBlock(m.setPos(i - 1 * l1, j + l1, k - 1 * l1)) || world.getBlockState(m.setPos(i - 1 * l1, j + l1, k - 1 * l1)) == leaf)
					setBlockAndNotifyAdequately(world, m.setPos(i - 1 * l1, j + l1, k - 1 * l1), log);
				if (l1 == l - 1)
					genLeaves(world, random, m.setPos(i - 1 * l1, j + l1, k - 1 * l1), leaf);
				for (int j3 = 0; j3 <= 1; j3++) {
					if (random.nextInt(2) == 0) {
						branches(world, random, i, j + j3, k, -1, 0, leaf, log);
					}

					if (random.nextInt(2) == 0) {
						branches(world, random, i, j + j3, k, 0, -1, leaf, log);
					}
				}
			} else if (dir == 2) {
				if (world.isAirBlock(m.setPos(i - 1 * l1, j + l1, k)) || world.getBlockState(m.setPos(i - 1 * l1, j + l1, k)) == leaf)
					setBlockAndNotifyAdequately(world, m.setPos(i - 1 * l1, j + l1, k), log);
				if (l1 == l - 1)
					genLeaves(world, random, m.setPos(i - 1 * l1, j + l1, k), leaf);
				for (int j3 = 0; j3 <= 1; j3++) {
					if (random.nextInt(2) == 0) {
						branches(world, random, i, j + j3, k, -1, -1, leaf, log);
					}

					if (random.nextInt(2) == 0) {
						branches(world, random, i, j + j3, k, -1, 1, leaf, log);
					}
				}
			} else if (dir == 3) {
				if (world.isAirBlock(m.setPos(i - 1 * l1, j + l1, k + 1 * l1)) || world.getBlockState(m.setPos(i - 1 * l1, j + l1, k + 1 * l1)) == leaf)
					setBlockAndNotifyAdequately(world, m.setPos(i - 1 * l1, j + l1, k + 1 * l1), log);
				if (l1 == l - 1)
					genLeaves(world, random, m.setPos(i - 1 * l1, j + l1, k + 1 * l1), leaf);
				for (int j3 = 0; j3 <= 1; j3++) {
					if (random.nextInt(2) == 0) {
						branches(world, random, i, j + j3, k, -1, 0, leaf, log);
					}

					if (random.nextInt(2) == 0) {
						branches(world, random, i, j + j3, k, 1, 0, leaf, log);
					}
				}
			} else if (dir == 4) {
				if (world.isAirBlock(m.setPos(i, j + l1, k + 1 * l1)) || world.getBlockState(m.setPos(i, j + l1, k + 1 * l1)) == leaf)
					setBlockAndNotifyAdequately(world, m.setPos(i, j + l1, k + 1 * l1), log);
				if (l1 == l - 1)
					genLeaves(world, random, m.setPos(i, j + l1, k + 1 * l1), leaf);
				for (int j3 = 0; j3 <= 1; j3++) {
					if (random.nextInt(2) == 0) {
						branches(world, random, i, j + j3, k, -1, 1, leaf, log);
					}

					if (random.nextInt(2) == 0) {
						branches(world, random, i, j + j3, k, 1, 1, leaf, log);
					}
				}
			} else if (dir == 5) {
				if (world.isAirBlock(m.setPos(i + 1 * l1, j + l1, k + 1 * l1)) || world.getBlockState(m.setPos(i + 1 * l1, j + l1, k + 1 * l1)) == leaf)
					setBlockAndNotifyAdequately(world, m.setPos(i + 1 * l1, j + l1, k + 1 * l1), log);
				if (l1 == l - 1)
					genLeaves(world, random, m.setPos(i + 1 * l1, j + l1, k + 1 * l1), leaf);
				for (int j3 = 0; j3 <= 1; j3++) {
					if (random.nextInt(2) == 0) {
						branches(world, random, i, j + j3, k, 0, 1, leaf, log);
					}

					if (random.nextInt(2) == 0) {
						branches(world, random, i, j + j3, k, 1, 0, leaf, log);
					}
				}
			} else if (dir == 6) {
				if (world.isAirBlock(m.setPos(i + 1 * l1, j + l1, k)) || world.getBlockState(m.setPos(i + 1 * l1, j + l1, k)) == leaf)
					setBlockAndNotifyAdequately(world, m.setPos(i + 1 * l1, j + l1, k), log);
				if (l1 == l - 1)
					genLeaves(world, random, m.setPos(i + 1 * l1, j + l1, k), leaf);
				for (int j3 = 0; j3 <= 1; j3++) {
					if (random.nextInt(2) == 0) {
						branches(world, random, i, j + j3, k, 1, 1, leaf, log);
					}

					if (random.nextInt(2) == 0) {
						branches(world, random, i, j + j3, k, 1, -1, leaf, log);
					}
				}
			} else if (dir == 7) {
				if (world.isAirBlock(m.setPos(i + 1 * l1, j + l1, k - 1 * l1)) || world.getBlockState(m.setPos(i + 1 * l1, j + l1, k - 1 * l1)) == leaf)
					setBlockAndNotifyAdequately(world, m.setPos(i + 1 * l1, j + l1, k - 1 * l1), log);
				if (l1 == l - 1)
					genLeaves(world, random, m.setPos(i + 1 * l1, j + l1, k - 1 * l1), leaf);
				for (int j3 = 0; j3 <= 1; j3++) {
					if (random.nextInt(2) == 0) {
						branches(world, random, i, j + j3, k, 1, 0, leaf, log);
					}

					if (random.nextInt(2) == 0) {
						branches(world, random, i, j + j3, k, 0, -1, leaf, log);
					}
				}
			} else if (dir == 8) {
				if (world.isAirBlock(m.setPos(i, j + l1, k - 1 * l1)) || world.getBlockState(m.setPos(i, j + l1, k - 1 * l1)) == leaf)
					setBlockAndNotifyAdequately(world, m.setPos(i, j + l1, k - 1 * l1), log);
				if (l1 == l - 1)
					genLeaves(world, random, m.setPos(i, j + l1, k - 1 * l1), leaf);
				for (int j3 = 0; j3 <= 1; j3++) {
					if (random.nextInt(2) == 0) {
						branches(world, random, i, j + j3, k, -1, -1, leaf, log);
					}

					if (random.nextInt(2) == 0) {
						branches(world, random, i, j + j3, k, 1, -1, leaf, log);
					}
				}
			}
		}
		return true;
	}

	public void branches(World world, Random random, int ii, int jj, int kk, int iD, int kD, IBlockState leaf, IBlockState log) {
		BlockPos.MutableBlockPos m = new BlockPos.MutableBlockPos(ii, jj, kk);
		for (int br = 0; br < 4; br++) {
			if (iD == -1 && random.nextInt(2) == 0)
				ii--;

			if (iD == 1 && random.nextInt(2) == 0)
				ii++;

			if (kD == -1 && random.nextInt(2) == 0)
				kk--;

			if (kD == 1 && random.nextInt(2) == 0)
				kk++;

			if (world.isAirBlock(m.setPos(ii, jj, kk)) || world.getBlockState(m.setPos(ii, jj, kk)) == leaf)
				setBlockAndNotifyAdequately(world, m.setPos(ii, jj, kk), log);

			if (br == 3)
				genLeaves(world, random, m.setPos(ii, jj, kk), leaf);

			jj++;
		}
	}

	public void genLeaves(World world, Random random, BlockPos.MutableBlockPos pos, IBlockState leaf) {
		int jX = pos.getX();
		int jY = pos.getY();
		int jZ = pos.getZ();

		for (int x = -2; x <= 2; x++) {
			for (int y = -2; y <= 2; y++) {
				if (Math.abs(x) != 2 || Math.abs(y) != 2) {
					if (world.isAirBlock(pos.setPos(jX + x, jY, jZ + y))) {
						setBlockAndNotifyAdequately(world, pos, leaf);
					}
				}

				if ((Math.abs(x) < 2 && Math.abs(y) < 2) && (Math.abs(x) != 1 || Math.abs(y) != 1)) {
					if (world.isAirBlock(pos.setPos(jX + x, jY + 1, jZ + y))) {
						setBlockAndNotifyAdequately(world, pos, leaf);
					}
				}

				if ((Math.abs(x) < 2 && Math.abs(y) < 2) && (Math.abs(x) != 1 || Math.abs(y) != 1)) {
					if (world.isAirBlock(pos.setPos(jX + x, jY - 1, jZ + y))) {
						setBlockAndNotifyAdequately(world, pos, leaf);
					}
				}
			}
		}
	}

	public static boolean isValidSoil(IBlockState state) {
		Block block = state.getBlock();
		return block instanceof BlockNetherrack || state.getMaterial() == Material.GROUND || state.getMaterial() == Material.GRASS;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider instanceof WorldProviderHell && random.nextFloat() < 0.15F) {
			BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(chunkX * 16 + MathHelper.getInt(random, -2, 2) + 8, 0, chunkZ * 16 + MathHelper.getInt(random, -2, 2) + 8);
			for (int i = 32; i <= 80; i++) {
				pos.setPos(pos.getX(), i, pos.getZ());
				IBlockState state = world.getBlockState(pos);
				int y = pos.getY();
				if (isValidSoil(state) && world.isAirBlock(pos.setPos(pos.getX(), y + 7, pos.getZ())) && world.isAirBlock(pos.setPos(pos.getX(), y + 1, pos.getZ())))
					break;
			}
			if (pos.getY() >= 80)
				return;
			generate(world, random, pos.toImmutable());
		}
	}

	@Override
	protected void setBlockAndNotifyAdequately(World world, BlockPos pos, IBlockState state) {
		world.setBlockState(pos, state);
		world.notifyBlockUpdate(pos, Blocks.AIR.getDefaultState(), state, 3);
	}

}
