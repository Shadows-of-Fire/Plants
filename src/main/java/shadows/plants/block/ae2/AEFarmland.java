package shadows.plants.block.ae2;

import net.minecraft.block.Block;
import shadows.plants.block.FarmlandBase;
import shadows.plants.common.EnumModule;
import shadows.plants.registry.modules.AE2Module;

public class AEFarmland extends FarmlandBase{
		public AEFarmland(){
			super(Block.getBlockFromName("appliedenergistics2:fluix_block"), "ae2farmland", AE2Module.ae_soil, EnumModule.APPLIED);
		}
}
