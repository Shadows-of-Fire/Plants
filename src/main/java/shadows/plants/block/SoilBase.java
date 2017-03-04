package shadows.plants.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import shadows.plants.common.EnumModule;
import shadows.plants.common.IModularThing;
import shadows.plants.util.Data;

public class SoilBase extends Block implements IModularThing {

	private EnumModule type;

	public SoilBase(String name, EnumModule module) {
		super(Material.GROUND);
		setRegistryName(name);
		setUnlocalizedName(Data.MODID + "." + name);
		setCreativeTab(Data.TAB);
		setSoundType(SoundType.GROUND);
		type = module;
	}

	@Override
	public EnumModule getType() {
		return type;
	}

}
