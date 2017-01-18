package shadows.plants.block.internal.cosmetic;

import net.minecraft.item.Item;
import shadows.plants.block.PlantBase;
import shadows.plants.common.EnumModule;

public class BlockCosmeticCrop extends PlantBase {
	
		private static Item seed;
		private static Item drop;
	
		public BlockCosmeticCrop(String name, Item drops, Item seeds){
			super(EnumModule.COSMETIC, name);
			seed = seeds;
			drop = drops;
			
		}
		
		
		@Override
	    protected Item getSeed()
	    {
	        return seed;
	    }

		@Override
	    protected Item getCrop()
	    {
	        return drop;
	    }
		
		
		
	
}
