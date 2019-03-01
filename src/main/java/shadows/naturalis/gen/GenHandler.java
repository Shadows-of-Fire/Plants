package shadows.naturalis.gen;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shadows.naturalis.util.EnumGenType;
import shadows.naturalis.util.IGenSensitive;

public class GenHandler {

	@SubscribeEvent
	public void decorate(DecorateBiomeEvent.Decorate event) {
		genFlowers(event);
	}

	public static void genFlowers(DecorateBiomeEvent.Decorate event) {
		if (!event.getWorld().isRemote && event.getType() == EventType.FLOWERS) {
			BlockPos pos = event.getChunkPos().getBlock(7, 0, 7);
			if (event.getRand().nextInt(4) == 0) {
				IBlockState state = getStateFor(event.getWorld(), pos, event.getRand());
				if (state != null) {
					genPatch(event.getWorld(), pos, state, event.getRand());
				}
			}
		}
	}

	private static IBlockState getStateFor(World world, BlockPos pos, Random rand) {
		EnumGenType t = EnumGenType.byBiome(world.getBiome(pos));
		if (t == null || t.getStates().isEmpty()) return null;
		return t.getStates().get(rand.nextInt(t.getStates().size()));
	}

	private static void genPatch(World world, BlockPos chunkCenter, IBlockState bush, Random rand) {
		for (int i = 0; i < 5; i++) {
			int xOffset = MathHelper.getInt(rand, -5, 5);
			int zOffset = MathHelper.getInt(rand, -5, 5);
			BlockPos placePos = world.getHeight(chunkCenter.add(xOffset, 0, zOffset));
			if (bush.getBlock().canPlaceBlockAt(world, placePos)) {
				world.setBlockState(placePos, bush, 2);
				if (bush.getBlock() instanceof IGenSensitive) ((IGenSensitive) bush.getBlock()).onGenerated(world, placePos, bush);
			}
		}
	}

}