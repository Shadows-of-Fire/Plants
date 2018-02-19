package shadows.plants2.gen;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import shadows.plants2.data.PlantConfig;
import shadows.plants2.init.ModRegistry;
import shadows.plants2.util.PlantUtil;

public class NetherGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider instanceof WorldProviderHell && random.nextInt(PlantConfig.patchChance) == 0) {
			int dist = PlantConfig.patchSize - 1;
			BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(chunkX * 16 + MathHelper.getInt(random, -dist, dist) + 8, 0, chunkZ * 16 + MathHelper.getInt(random, -dist, dist) + 8);
			for (int i = 32; i <= 95; i++) {
				pos.setPos(pos.getX(), i, pos.getZ());
				if (ModRegistry.NETHER_HARVEST.canBlockStay(world, pos, ModRegistry.NETHER_HARVEST.getDefaultState()) && world.isAirBlock(pos)) break;
			}
			if (pos.getY() >= 95) return;
			for (int r = 0; r < PlantConfig.numTries; r++)
				PlantUtil.genFlowerPatchForNether(world, pos, random, PlantUtil.NETHER.get(random.nextInt(PlantUtil.NETHER.size())));
		}
	}
}