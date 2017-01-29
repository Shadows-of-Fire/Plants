package shadows.plants.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import shadows.plants.common.EnumModule;
import shadows.plants.util.Data;
import shadows.plants.util.IModularThing;

public class FarmlandBase extends BlockFarmland implements IModularThing{
		public Block soilWater;
		public Block soil;
		private EnumModule module;
		
		public FarmlandBase(Block fluid, String name, Block soilIn, EnumModule module_){
			setTickRandomly(true);
			setRegistryName(name);
			setUnlocalizedName(Data.MODID + "." + name);
			setCreativeTab(Data.TAB);
			setSoundType(SoundType.GROUND);
			soilWater = fluid;
			soil = soilIn;
			module = module_;
		}
		
	    private boolean hasWater(World worldIn, BlockPos pos)
	    {
	        for (BlockPos.MutableBlockPos blockpos$mutableblockpos : BlockPos.getAllInBoxMutable(pos.add(-4, -1, -4), pos.add(4, 1, 4)))
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
	                worldIn.setBlockState(pos, soil.getDefaultState(), 3);
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
	            worldIn.setBlockState(pos, soil.getDefaultState(), 3);
	            entityIn.setPosition((double)entityIn.posX, (double)pos.getY()+1, (double)entityIn.posZ);
	        }
	    }

		@Override
		public EnumModule getType() {
			return module;
		}
		
}
