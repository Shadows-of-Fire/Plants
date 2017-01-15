package shadows.plants.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import shadows.plants.util.Data;

public class SoilBase extends Block{

	public SoilBase(String name) {
		super(Material.GROUND);
		setRegistryName(name);
		setUnlocalizedName(Data.MODID + "." + name);
		setCreativeTab(Data.TAB);
	}

}
