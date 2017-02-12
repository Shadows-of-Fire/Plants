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
import shadows.plants.block.internal.cosmetic.BlockCrop;
import shadows.plants.block.internal.cosmetic.BlockDoubleHarvestable;
import shadows.plants.block.internal.cosmetic.BlockDoubleMetaBush;
import shadows.plants.block.internal.cosmetic.BlockFruitVine;
import shadows.plants.block.internal.cosmetic.BlockMetaBush;
import shadows.plants.common.IMetaPlant;
import shadows.plants.common.ITemperaturePlant;

public final class Decorator {

	private Decorator() {}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onWorldDecoration(DecorateBiomeEvent.Decorate event) {
		for(int ih = Config.numtries; ih > 0; ih--){//Number of runs per event.
		if(event.getRand().nextInt(Config.patchchance) == 0) {//The (1/n) chance for flowers to show up on an event.
			Block flower = Util.getFlowerByChance(event.getRand());
			IBlockState state = Util.getStateByChance(event.getRand(), flower);
			if(state != null && flower instanceof ITemperaturePlant){
			float max = ((ITemperaturePlant) flower).getTempMax(state);
			float min = ((ITemperaturePlant) flower).getTempMin(state);
			float temp = event.getWorld().getBiome(event.getPos()).getTemperature();
			event.getWorld().getBiome(event.getPos()).getTempCategory();
			if(temp >= min && temp <= max){
			Util.genFlowerPatch(event.getWorld(), event.getPos(), event.getRand(), state, flower);
			}}}}}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void flowerForestDeco(DecorateBiomeEvent.Decorate event) {
		if(event.getType() == EventType.FLOWERS && event.getWorld().getBiome(event.getPos()) == Biome.getBiome(132) && Config.literallyTakeoverFlowerForests){
		for(int ih = 32; ih > 0; ih--){
		if(event.getRand().nextInt(Config.patchchance / 5) == 0) {//The (1/n) chance for flowers to show up on an event.
			Block flower = Util.getFlowerByChance(event.getRand());
			int xk = 0;
			IBlockState state = null;
			int maxdata = 0;
			if(flower instanceof IMetaPlant) maxdata = ((IMetaPlant) flower).getMaxData();
			if (flower instanceof BlockMetaBush){ xk = event.getRand().nextInt(maxdata + 1); if(xk == 2) ++xk; if(xk == 3) ++xk; state = flower.getDefaultState().withProperty(BlockMetaBush.BASICMETA, xk);}
			if (flower instanceof BlockDoubleMetaBush){ xk = event.getRand().nextInt(maxdata + 1); state = flower.getDefaultState().withProperty(BlockDoubleMetaBush.META, (xk));}
			if (flower instanceof BlockCrop) state = flower.getDefaultState().withProperty(BlockCrop.AGE, 7);
			if (flower instanceof BlockDoubleHarvestable) state = flower.getDefaultState().withProperty(BlockDoubleHarvestable.UPPER, false);
			if(state != null && flower instanceof ITemperaturePlant){
			float max = ((ITemperaturePlant) flower).getTempMax(state);
			float min = ((ITemperaturePlant) flower).getTempMin(state);
			float temp = event.getWorld().getBiome(event.getPos()).getTemperature();
			event.getWorld().getBiome(event.getPos()).getTempCategory();
			if(temp >= min && temp <= max){
			int dist = Config.patchsize * 14;//Spread of the flowers, a radius of sorts.
			for(int i = 0; i < Config.quantity * 2; i++) {//number of positions selected per event.
					int x = event.getPos().getX() + event.getRand().nextInt(16) + 8;
					int z = event.getPos().getZ() + event.getRand().nextInt(16) + 8;
					int y = event.getWorld().getTopSolidOrLiquidBlock(event.getPos()).getY();
					for(int j = 0; j < Config.density * 2; j++) { //number of placements that occur.
						int x1 = x + event.getRand().nextInt(dist * 2) - dist;
						int y1 = y + event.getRand().nextInt(4) - event.getRand().nextInt(4);
						int z1 = z + event.getRand().nextInt(dist * 2) - dist;
						BlockPos pos2 = new BlockPos(x1, y1, z1);
						if(!(flower instanceof BlockDoubleMetaBush || flower instanceof BlockDoubleHarvestable || flower instanceof BlockFruitVine)){
						if(event.getWorld().isAirBlock(pos2) && (!event.getWorld().provider.getHasNoSky() || y1 < 127) && flower.canPlaceBlockAt(event.getWorld(), pos2)) {
							event.getWorld().setBlockState(pos2, state, 2);
					}}
						else if (flower instanceof BlockDoubleMetaBush){
						if(event.getWorld().isAirBlock(pos2) && event.getWorld().isAirBlock(pos2.up()) && (!event.getWorld().provider.getHasNoSky() || y1 < 127) && flower.canPlaceBlockAt(event.getWorld(), pos2)) {
						event.getWorld().setBlockState(pos2, state.withProperty(BlockDoubleMetaBush.UPPER,  false), 2);
						event.getWorld().setBlockState(pos2.up(), state.withProperty(BlockDoubleMetaBush.UPPER,  true), 2);
						}}
						else if (flower instanceof BlockDoubleHarvestable){
						if(event.getWorld().isAirBlock(pos2) && event.getWorld().isAirBlock(pos2.up()) && (!event.getWorld().provider.getHasNoSky() || y1 < 127) && flower.canPlaceBlockAt(event.getWorld(), pos2)) {
						event.getWorld().setBlockState(pos2, state.withProperty(BlockDoubleHarvestable.UPPER,  false), 2);
						event.getWorld().setBlockState(pos2.up(), state.withProperty(BlockDoubleHarvestable.UPPER,  true), 2);
						}}}}}}}}}}
}