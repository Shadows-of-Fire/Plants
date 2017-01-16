package shadows.plants.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import shadows.plants.common.EnumModule;
import shadows.plants.util.Data;

public class BushBase extends BlockBush{
	
		public EnumModule plantType;
		public List<Block> soil;
		
	public BushBase(EnumModule type, String name, List<Block> soilIn){
		setRegistryName(name);
		setUnlocalizedName(Data.MODID + "." + name);
        setCreativeTab(Data.TAB);
        setHardness(0.0F);
        setSoundType(SoundType.PLANT);
        disableStats();
        plantType = type;
        soil = soilIn;
	}
	
	public BushBase(EnumModule type, String name, Block soilIn){
		setRegistryName(name);
		setUnlocalizedName(Data.MODID + "." + name);
        setCreativeTab(Data.TAB);
        setHardness(0.0F);
        setSoundType(SoundType.PLANT);
        disableStats();
        plantType = type;
        soil.add(soilIn);
	}
	
    public static EnumModule getType(BushBase block){
    	return block.plantType;
    }
    
    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {
    	return soil.contains(world.getBlockState(pos.down()).getBlock());
    }

    @Override
    protected boolean canSustainBush(IBlockState state)
    {
    	return soil.contains(state.getBlock());
    }
    
    
    
}
