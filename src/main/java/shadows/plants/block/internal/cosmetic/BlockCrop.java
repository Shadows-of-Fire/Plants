package shadows.plants.block.internal.cosmetic;

import java.util.Map;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import shadows.plants.block.PlantBase;
import shadows.plants.common.EnumModule;
import shadows.plants.common.EnumTempZone;
import shadows.plants.registry.modules.CosmeticModule;

public class BlockCrop extends PlantBase{
		
		private Item drops;
		private int index;
		private Item seed;
		private EnumTempZone tempzone;
		
		public BlockCrop(String name, Item drop, int seedIndex, EnumTempZone zone){
			super(EnumModule.COSMETIC, name);
			drops = drop;
			index = seedIndex;
			tempzone = zone;
		}
		
		@Override
		public Item getCrop(){
			return drops;
		}
		
		@Override
		public Item getSeed(){
			assignSeed();
			return seed;
		}
		
		private void assignSeed(){
		switch(index){
		case(0): seed = CosmeticModule.okra_seed; break;
		case(1): seed = CosmeticModule.pineapple_seed; break;
		}}

		@Override
		public float getTempMax(IBlockState state) {
			return tempzone.getMax();
		}

		@Override
		public float getTempMin(IBlockState state) {
			return tempzone.getMin();
		}
}
