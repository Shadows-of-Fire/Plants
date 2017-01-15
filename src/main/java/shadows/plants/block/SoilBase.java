package shadows.plants.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import shadows.plants.common.EnumModule;
import shadows.plants.util.Data;

public class SoilBase extends Block{

	public EnumModule soilType;
	
	public SoilBase(String name, EnumModule module) {
		super(Material.GROUND);
		setRegistryName(name);
		setUnlocalizedName(Data.MODID + "." + name);
		setCreativeTab(Data.TAB);
		setSoundType(SoundType.GROUND);
		soilType = module;
	}
	
	public static EnumModule getType(SoilBase block){
		return block.soilType;
	}

}
