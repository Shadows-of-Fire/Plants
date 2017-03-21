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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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
							int chance = event.getRand().nextInt(300);
							// if (Config.debug) System.out.println("Attempting
							// generation with chance integer " + chance + " at
							// coordinates " + "(" + event.getPos().getX() + ","
							// + event.getPos().getY() + "," +
							// event.getPos().getZ() + ")");
							if (chance > 50)
								Util.genFlowerPatch(event.getWorld(), pos.add(8, 0, 8), event.getRand(), state, flower);
							else if (chance <= 50 && chance > 0)
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
}