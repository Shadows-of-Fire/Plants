package shadows.plants.item;

import net.minecraft.item.Item;
import shadows.plants.common.EnumModule;
import shadows.plants.common.IModularThing;
import shadows.plants.util.Data;

public class UselessItemBase extends Item implements IModularThing{
	
		private EnumModule module;
	
		public UselessItemBase(String name, EnumModule module_){
			setRegistryName(name);
			setUnlocalizedName(Data.MODID+"."+name);
			module = module_;
		}

		
		@Override
		public EnumModule getType() {
			return module;
		}
}
