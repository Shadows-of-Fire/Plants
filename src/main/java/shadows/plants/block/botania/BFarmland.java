package shadows.plants.block.botania;

import net.minecraft.init.Blocks;
import shadows.plants.block.FarmlandBase;
import shadows.plants.common.EnumModule;
import shadows.plants.registry.modules.BotaniaModule;

public class BFarmland extends FarmlandBase{
	public BFarmland(){
		super(Blocks.WATER, "b_farmland", BotaniaModule.b_soil, EnumModule.BOTANICAL);
	}
}
