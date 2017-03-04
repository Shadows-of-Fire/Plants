package shadows.plants.block;

import net.minecraft.block.BlockVine;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import shadows.plants.common.EnumModule;
import shadows.plants.common.EnumTempZone;
import shadows.plants.common.IModularThing;
import shadows.plants.common.ITemperaturePlant;
import shadows.plants.util.Data;

public abstract class VineBase extends BlockVine implements IModularThing, ITemperaturePlant {

	private EnumModule type;
	private EnumTempZone zone;

	public VineBase(String name, EnumModule module, EnumTempZone zone_) {
		setRegistryName(name);
		setUnlocalizedName(Data.MODID + "." + name);
		setCreativeTab(Data.TAB);
		setSoundType(SoundType.PLANT);
		type = module;
		zone = zone_;
	}

	@Override
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
		return false;
	}

	@Override
	public EnumModule getType() {
		return type;
	}

	@Override
	public float getTempMax(IBlockState state) {
		return zone.getMax();
	}

	@Override
	public float getTempMin(IBlockState state) {
		return zone.getMin();
	}

}
