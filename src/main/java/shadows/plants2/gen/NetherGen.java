package shadows.plants2.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockNetherrack;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.common.IWorldGenerator;
import shadows.plants2.data.Config;
import shadows.plants2.init.ModRegistry;
import shadows.plants2.util.PlantUtil;

public class NetherGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider instanceof WorldProviderHell && random.nextInt(Config.patchChance) == 0) {
			int dist = Config.patchSize - 1;
			BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(chunkX * 16 + MathHelper.getInt(random, -dist, dist) + 8, 0, chunkZ * 16 + MathHelper.getInt(random, -dist, dist) + 8);
			for (int i = 32; i <= 95; i++) {
				pos.setPos(pos.getX(), i, pos.getZ());
				IBlockState state = world.getBlockState(pos);
				int y = pos.getY();
				if (isValidSoil(state, world, pos, ModRegistry.NETHER_HARVEST) && world.isAirBlock(pos.setPos(pos.getX(), y + 1, pos.getZ()))) break;
			}
			if (pos.getY() >= 95) return;
			for(int r = 0; r < Config.numTries; r++) PlantUtil.genFlowerPatchForNether(world, pos, random, PlantUtil.NETHER.get(random.nextInt(PlantUtil.NETHER.size())));
		}
	}

	public static boolean isValidSoil(IBlockState soil, World world, BlockPos pos, IPlantable plant) {
		Block block = soil.getBlock();
		return block instanceof BlockNetherrack || block.canSustainPlant(soil, world, pos, EnumFacing.UP, plant);
	}
}