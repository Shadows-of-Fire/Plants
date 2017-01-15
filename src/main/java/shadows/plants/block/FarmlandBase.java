package shadows.plants.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import shadows.plants.util.Data;

public class FarmlandBase extends BlockFarmland{
		public Block soilWater;
		public Block soil;
		
		public FarmlandBase(Block fluid, String name, Block soilIn){
			setRegistryName(name);
			setUnlocalizedName(Data.MODID + "." + name);
			setCreativeTab(Data.TAB);
			soilWater = fluid;
			soil = soilIn;
			
		}
		
	    private boolean hasWater(World worldIn, BlockPos pos)
	    {
	        for (BlockPos.MutableBlockPos blockpos$mutableblockpos : BlockPos.getAllInBoxMutable(pos.add(-4, 0, -4), pos.add(4, 1, 4)))
	        {
	            if (worldIn.getBlockState(blockpos$mutableblockpos).getBlock() == soilWater)
	            {
	                return true;
	            }
	        }

	        return false;
	    }
	
		@Override
		 public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	    {
	        int i = ((Integer)state.getValue(MOISTURE)).intValue();

	        if (!this.hasWater(worldIn, pos) && !worldIn.isRainingAt(pos.up()))
	        {
	            if (i > 0)
	            {
	                worldIn.setBlockState(pos, state.withProperty(MOISTURE, Integer.valueOf(i - 1)), 2);
	            }
	            else if (!this.hasCrops(worldIn, pos))
	            {
	                worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState());
	            }
	        }
	        else if (i < 7)
	        {
	            worldIn.setBlockState(pos, state.withProperty(MOISTURE, Integer.valueOf(7)), 2);
	        }
	    }
		
	    private boolean hasCrops(World worldIn, BlockPos pos)
	    {
	        Block block = worldIn.getBlockState(pos.up()).getBlock();
	        return block instanceof net.minecraftforge.common.IPlantable && canSustainPlant(worldIn.getBlockState(pos), worldIn, pos, net.minecraft.util.EnumFacing.UP, (net.minecraftforge.common.IPlantable)block);
	    }
	    
	    @Override
	    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance)
	    {
	        if (!worldIn.isRemote && 2.0 < fallDistance && entityIn instanceof EntityLivingBase && (entityIn instanceof EntityPlayer || worldIn.getGameRules().getBoolean("mobGriefing")) && entityIn.width * entityIn.width * entityIn.height > 0.512F)
	        {
	            worldIn.setBlockState(pos, soil.getDefaultState());
	        }

	        super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
	    }
		
}
