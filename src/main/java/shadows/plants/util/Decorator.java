/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 *
 * File Created @ [Jan 19, 2014, 10:16:49 PM (GMT)]
 */
package shadows.plants.util;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shadows.plants.block.internal.cosmetic.BlockFruitVine;
import shadows.plants.common.ITemperaturePlant;

public final class Decorator {

	private Decorator() {
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onWorldDecoration(DecorateBiomeEvent.Decorate event) {
		if (!event.getWorld().isRemote && Config.generation && event.getType() == EventType.FLOWERS) {
			BlockPos pos = event.getPos();
			for (int ih = Config.numtries; ih > 0; ih--) {
				if (event.getRand().nextInt(Config.patchchance) == 0) {
					Block flower = Util.getFlowerByChance(event.getRand());
					IBlockState state = Util.getStateByChance(event.getRand(), flower);
					if (state != null && flower instanceof ITemperaturePlant) {
						float max = ((ITemperaturePlant) flower).getTempMax(state);
						float min = ((ITemperaturePlant) flower).getTempMin(state);
						float temp = event.getWorld().getBiome(pos).getTemperature();
						if (temp >= min && temp <= max) {
							int chance = event.getRand().nextInt(100) + 1;
							if (chance > 10)
								Util.genFlowerPatch(event.getWorld(), pos.add(8, 0, 8), event.getRand(), state, flower);
							else if (chance <= 10 && chance > 0)
								Util.genSmallFlowerPatchNearby(event.getWorld(), pos.add(8, 0, 8), event.getRand(),
										state, flower);
							else if (chance == 0)
								Util.genMegaPatch(event.getWorld(), pos.add(8, 0, 8), event.getRand(), state, flower);
						}
					}
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onDesertDecoration(DecorateBiomeEvent.Decorate event) {
		if (!event.getWorld().isRemote && Config.generation
				&& (event.getType() == EventType.DEAD_BUSH || event.getType() == EventType.CACTUS)) {
			BlockPos pos = event.getPos();
			for (int ih = Config.numtries; ih > 0; ih--) {
				if (event.getRand().nextInt(Config.patchchance * 4) == 0) {
					Block flower = Util.getDesertFlowerByChance(event.getRand());
					IBlockState state = Util.getStateByChance(event.getRand(), flower);
					if (state != null && flower instanceof ITemperaturePlant) {
						float max = ((ITemperaturePlant) flower).getTempMax(state);
						float min = ((ITemperaturePlant) flower).getTempMin(state);
						float temp = event.getWorld().getBiome(pos).getTemperature();
						if (temp >= min && temp <= max) {
							int chance = event.getRand().nextInt(100) + 1;
							if (chance > 10)
								Util.genFlowerPatch(event.getWorld(), pos.add(8, 0, 8), event.getRand(), state, flower);
							else if (chance <= 10 && chance > 0)
								Util.genSmallFlowerPatchNearby(event.getWorld(), pos.add(8, 0, 8), event.getRand(),
										state, flower);
							else if (chance == 0)
								Util.genMegaPatch(event.getWorld(), pos.add(8, 0, 8), event.getRand(), state, flower);
						}
					}
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void flowerForestDeco(DecorateBiomeEvent.Decorate event) {
		if (!event.getWorld().isRemote && event.getType() == EventType.FLOWERS
				&& event.getWorld().getBiome(event.getPos()) == Biome.getBiome(132)
				&& Config.literallyTakeoverFlowerForests && Config.generation) {
			for (int ih = 4; ih > 0; ih--) {
				Block flower = Util.getFlowerByChance(event.getRand());
				IBlockState state = Util.getStateByChance(event.getRand(), flower);
				if (state != null && flower instanceof ITemperaturePlant) {
					float max = ((ITemperaturePlant) flower).getTempMax(state);
					float min = ((ITemperaturePlant) flower).getTempMin(state);
					float temp = event.getWorld().getBiome(event.getPos()).getTemperature();
					event.getWorld().getBiome(event.getPos()).getTempCategory();
					if (temp >= min && temp <= max) {
						Util.genMegaPatch(event.getWorld(), event.getPos(), event.getRand(), state, flower);
					}
				}
			}
		}
	}
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void vineDecorator(DecorateBiomeEvent.Decorate event) {
		if (!event.getWorld().isRemote && event.getRand().nextInt(30) == 0 && event.getType() == EventType.FLOWERS && Config.generation) {
			EnumFacing facing = EnumFacing.HORIZONTALS[event.getRand().nextInt(4)];
			World world = event.getWorld();
			BlockFruitVine vine = (BlockFruitVine) Util.getRandomVine(world.rand);
			BlockPos pos = world.getTopSolidOrLiquidBlock(event.getPos().add(8, 0, 8).add(MathHelper.getInt(event.getRand(), -4, 4), 0, MathHelper.getInt(event.getRand(), -4, 4)));
			for(int i = 0; i < 3; i++){
			world.setBlockState(pos.up(i), Blocks.MOSSY_COBBLESTONE.getDefaultState());
			if(vine.canPlaceBlockAt(world, pos.offset(facing).up(i))) world.setBlockState(pos.offset(facing).up(i), vine.getStateForPlacement(world, pos.offset(facing).up(i), facing, 0, 0, 0, 0, null));
			}
			}
		}
}