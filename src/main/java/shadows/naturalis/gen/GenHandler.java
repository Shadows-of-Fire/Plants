package shadows.naturalis.gen;

import net.minecraft.block.BlockGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeBeach;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shadows.naturalis.block.BlockCustomVine;
import shadows.naturalis.data.PlantConfig;
import shadows.naturalis.util.PlantUtil;

public class GenHandler {

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void recieveGenEvent(DecorateBiomeEvent.Decorate event) {
		if (!PlantConfig.gen) return;
		if (PlantConfig.DIM_BL.contains(event.getWorld().provider.getDimension())) return;
		if (PlantConfig.COMPUTED_BIOME_BL.contains(event.getWorld().getBiome(event.getChunkPos().getBlock(0, 0, 0)))) return;
		genFlowers(event);
		genDesertFlowers(event);
		flowerForestDeco(event);
		genVines(event);
	}

	public static void genFlowers(DecorateBiomeEvent.Decorate event) {
		if (PlantConfig.flowerGen && !event.getWorld().isRemote && event.getType() == EventType.FLOWERS) {
			BlockPos pos = event.getChunkPos().getBlock(8, 0, 8);
			for (int ih = PlantConfig.numTries; ih > 0; ih--) {
				if (event.getRand().nextInt(PlantConfig.patchChance) == 0) {
					IBlockState state = PlantUtil.getFlowerState(event.getRand());
					if (state != null) {
						int chance = event.getRand().nextInt(100);
						if (chance > 10) PlantUtil.genFlowerPatch(event.getWorld(), pos, event.getRand(), state);
						else if (chance <= 10 && chance >= 0) PlantUtil.genSmallFlowerPatchNearby(event.getWorld(), pos, event.getRand(), state);
						else if (chance == 0) //Yeah, this can't be fulfilled, this is off for now until genMetaPatch doesn't cause cascading gen.
							PlantUtil.genMegaPatch(event.getWorld(), pos, event.getRand(), state);
					}
				}
			}
		}
	}

	public static void genDesertFlowers(DecorateBiomeEvent.Decorate event) {
		if (event.getWorld().getBiome(event.getChunkPos().getBlock(8, 0, 8)) instanceof BiomeBeach) return;
		if (PlantConfig.desertGen && !event.getWorld().isRemote && (event.getType() == EventType.DEAD_BUSH || event.getType() == EventType.CACTUS)) {
			BlockPos pos = event.getChunkPos().getBlock(8, 0, 8);
			for (int ih = PlantConfig.numTries; ih > 0; ih--) {
				if (event.getRand().nextInt(PlantConfig.patchChance * 4) == 0) {
					IBlockState state = PlantUtil.getDesertFlowerState(event.getRand());
					if (state != null) {
						int chance = event.getRand().nextInt(100);
						if (chance > 10) PlantUtil.genFlowerPatch(event.getWorld(), pos, event.getRand(), state);
						else if (chance <= 10 && chance >= 0) PlantUtil.genSmallFlowerPatchNearby(event.getWorld(), pos, event.getRand(), state);
						else if (chance == 0) //Yeah, this can't be fulfilled, this is off for now until genMetaPatch doesn't cause cascading gen.
							PlantUtil.genMegaPatch(event.getWorld(), pos, event.getRand(), state);
					}
				}
			}
		}
	}

	public static final Biome FLOWER_FOREST = Biome.getBiome(132);

	public static void flowerForestDeco(DecorateBiomeEvent.Decorate event) {
		if (PlantConfig.flowerGen && PlantConfig.literallyTakeoverFlowerForests && !event.getWorld().isRemote && event.getType() == EventType.FLOWERS && event.getWorld().getBiome(event.getChunkPos().getBlock(0, 0, 0)) == FLOWER_FOREST) {
			for (int ih = 8; ih > 0; ih--) {
				IBlockState state = PlantUtil.getFlowerState(event.getRand());
				PlantUtil.genFlowerPatch(event.getWorld(), event.getChunkPos().getBlock(8, 0, 8), event.getRand(), state);
			}
		}
	}

	public static void genVines(DecorateBiomeEvent.Decorate event) {
		if (PlantConfig.vineGen && !event.getWorld().isRemote && event.getRand().nextInt(30) == 0 && event.getType() == EventType.FLOWERS) {
			EnumFacing facing = EnumFacing.HORIZONTALS[event.getRand().nextInt(4)];
			World world = event.getWorld();
			BlockCustomVine vine = PlantUtil.getRandomVine(world.rand);
			BlockPos pos = world.getTopSolidOrLiquidBlock(event.getChunkPos().getBlock(8, 0, 8).add(MathHelper.getInt(event.getRand(), -4, 4), 0, MathHelper.getInt(event.getRand(), -4, 4)));
			if (!(world.getBlockState(pos).getBlock() instanceof BlockGrass)) return;
			for (int i = 0; i < 3; i++) {
				world.setBlockState(pos.up(i), Blocks.MOSSY_COBBLESTONE.getDefaultState(), 2);
				if (vine.canPlaceBlockAt(world, pos.offset(facing).up(i))) world.setBlockState(pos.offset(facing).up(i), vine.getStateForPlacement(world, pos.offset(facing).up(i), facing, 0, 0, 0, 0, null), 2);
			}
		}
	}
}