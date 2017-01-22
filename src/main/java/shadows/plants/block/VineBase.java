package shadows.plants.block;

import net.minecraft.block.BlockVine;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import shadows.plants.common.EnumModule;
import shadows.plants.util.Data;

public class VineBase extends BlockVine{
	
    private EnumModule plantType;
	
	
	public VineBase(String name, EnumModule module){
		
		setCreativeTab(Data.TAB);
		plantType = module;
	}
	
    public static EnumModule getType(VineBase block){
    	return block.plantType;
    }
    
    @Override
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return false;
    }

}
