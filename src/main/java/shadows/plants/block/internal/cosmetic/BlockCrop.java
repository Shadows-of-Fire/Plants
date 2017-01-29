package shadows.plants.block.internal.cosmetic;

import net.minecraft.item.Item;
import shadows.plants.block.PlantBase;
import shadows.plants.common.EnumModule;

public class BlockCrop extends PlantBase{
		
		private Item drops;
		private Item seeds;
	
		public BlockCrop(String name, Item drop, Item seed){
			super(EnumModule.COSMETIC, name);
			drops = drop;
			seeds = seed;
		}
		
		@Override
		public Item getCrop(){
			return drops;
		}
		
		@Override
		public Item getSeed(){
			return seeds;
		}
	
}
