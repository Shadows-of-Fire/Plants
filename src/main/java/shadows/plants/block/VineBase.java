package shadows.plants.block;

import net.minecraft.block.BlockVine;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import shadows.plants.common.EnumModule;
import shadows.plants.util.Data;
import shadows.plants.util.IModularThing;

public class VineBase extends BlockVine implements IModularThing{
	
    private EnumModule type;
	
	
	public VineBase(String name, EnumModule module){
		
		setCreativeTab(Data.TAB);
		type = module;
	}
    
    @Override
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return false;
    }

	@Override
	public EnumModule getType() {
		return type;
	}

}
