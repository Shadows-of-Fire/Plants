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
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shadows.plants.block.internal.cosmetic.BlockCrop;
import shadows.plants.block.internal.cosmetic.BlockDoubleMetaBush;
import shadows.plants.block.internal.cosmetic.BlockMetaBush;

public final class Decorator {

	private Decorator() {}

	@SuppressWarnings("deprecation")
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onWorldDecoration(DecorateBiomeEvent.Decorate event) {
			boolean genFlowers = false;
			float temp = event.getWorld().getBiome(event.getPos()).getTemperature();
			if(temp <= 1.5F && temp >= 0.2F)
				genFlowers = true;

			if(!genFlowers)
				return;

			int xj = event.getRand().nextInt(Data.GENPLANTS().size());
			Block flower = Data.GENPLANTS().get(xj);
			int xk = 0;
			int maxdata = Util.getMaxMetadata(flower.getRegistryName().getResourcePath());
			if (flower instanceof BlockMetaBush) xk = event.getRand().nextInt(maxdata);
			if (flower instanceof BlockDoubleMetaBush) xk = event.getRand().nextInt(maxdata);
			if (flower instanceof BlockCrop) xk = 7;
			
			int dist = 2 /*config flowerpatchsize*/;
			for(int i = 0; i < 6/*config flowerQuantity*/; i++) {
				if(event.getRand().nextInt(43 /*config patchchance */) == 0) {
					int x = event.getPos().getX() + event.getRand().nextInt(16) + 8;
					int z = event.getPos().getZ() + event.getRand().nextInt(16) + 8;
					int y = event.getWorld().getTopSolidOrLiquidBlock(event.getPos()).getY();
					for(int j = 0; j < 2 * 16 /*config density * patchchance */; j++) {
						int x1 = x + event.getRand().nextInt(dist * 2) - dist;
						int y1 = y + event.getRand().nextInt(4) - event.getRand().nextInt(4);
						int z1 = z + event.getRand().nextInt(dist * 2) - dist;
						BlockPos pos2 = new BlockPos(x1, y1, z1);
						if(!(flower instanceof BlockDoubleMetaBush)){
						if(event.getWorld().isAirBlock(pos2) && (!event.getWorld().provider.getHasNoSky() || y1 < 127) && flower.canPlaceBlockAt(event.getWorld(), pos2)) {
							event.getWorld().setBlockState(pos2, flower.getStateFromMeta(xk), 2);
					}}
						else if (flower instanceof BlockDoubleMetaBush){
						if(event.getWorld().isAirBlock(pos2) && event.getWorld().isAirBlock(pos2.up()) && (!event.getWorld().provider.getHasNoSky() || y1 < 127) && flower.canPlaceBlockAt(event.getWorld(), pos2)) {
						event.getWorld().setBlockState(pos2, flower.getDefaultState().withProperty(BlockDoubleMetaBush.META, (xk)).withProperty(BlockDoubleMetaBush.UPPER,  false), 2);
						event.getWorld().setBlockState(pos2.up(), flower.getDefaultState().withProperty(BlockDoubleMetaBush.META, (xk)).withProperty(BlockDoubleMetaBush.UPPER,  true), 2);
						}
						}
			 }
		  }
	   }
	}
}