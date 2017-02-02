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
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shadows.plants.block.internal.cosmetic.BlockCrop;
import shadows.plants.block.internal.cosmetic.BlockDoubleMetaBush;
import shadows.plants.block.internal.cosmetic.BlockMetaBush;
import shadows.plants.common.ITemperaturePlant;

public final class Decorator {

	private Decorator() {}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onWorldDecoration(DecorateBiomeEvent.Decorate event) {
			int xj = event.getRand().nextInt(Data.GENPLANTS().size());
			Block flower = Data.GENPLANTS().get(xj);
			int xk = 0;
			IBlockState state = null;
			int maxdata = Util.getMaxMetadata(flower.getRegistryName().getResourcePath());
			if (flower instanceof BlockMetaBush){ xk = event.getRand().nextInt(maxdata); state = flower.getDefaultState().withProperty(BlockMetaBush.BASICMETA, xk);}
			if (flower instanceof BlockDoubleMetaBush){ xk = event.getRand().nextInt(maxdata); state = flower.getDefaultState().withProperty(BlockDoubleMetaBush.META, (xk));}
			if (flower instanceof BlockCrop){ xk = 7; state = flower.getDefaultState().withProperty(BlockCrop.AGE, (xk));}
			if(flower instanceof ITemperaturePlant){
			if(state != null){
			float max = ((ITemperaturePlant) flower).getTempMax(state);
			float min = ((ITemperaturePlant) flower).getTempMin(state);
			float temp = event.getWorld().getBiome(event.getPos()).getTemperature();
			if(temp >= min && temp <= max){
			int dist = 2 /*config flowerpatchsize*/;
			for(int i = 0; i < 3/*config flowerQuantity*/; i++) {
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
							event.getWorld().setBlockState(pos2, state, 2);
					}}
						else if (flower instanceof BlockDoubleMetaBush){
						if(event.getWorld().isAirBlock(pos2) && event.getWorld().isAirBlock(pos2.up()) && (!event.getWorld().provider.getHasNoSky() || y1 < 127) && flower.canPlaceBlockAt(event.getWorld(), pos2)) {
						event.getWorld().setBlockState(pos2, state.withProperty(BlockDoubleMetaBush.UPPER,  false), 2);
						event.getWorld().setBlockState(pos2.up(), state.withProperty(BlockDoubleMetaBush.UPPER,  true), 2);
						}}}}}}}}}
}