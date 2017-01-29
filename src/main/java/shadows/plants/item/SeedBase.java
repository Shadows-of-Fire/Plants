package shadows.plants.item;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import shadows.plants.block.PlantBase;
import shadows.plants.common.EnumModule;
import shadows.plants.util.Data;
import shadows.plants.util.IModularThing;

public class SeedBase extends ItemSeeds implements IModularThing{

	private EnumModule module;
	
		public SeedBase(String name, PlantBase crop, EnumModule module_){
			super(crop, Blocks.FARMLAND);
			setUnlocalizedName(Data.MODID + "." + name);
			setRegistryName(name);
			setCreativeTab(Data.TAB_I);
			module = module_;
		}
		
		@Override
		public EnumModule getType() {
			return module;
		}
}
