package shadows.plants.block;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shadows.plants.common.PlantType;

public class PlantBase extends BlockBush implements IGrowable{
	
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 7);
    private static final AxisAlignedBB[] CROPS_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};
    private static String plantType;
    
    
	protected PlantBase(PlantType type){
		
        this.setDefaultState(this.blockState.getBaseState().withProperty(this.getAgeProperty(), Integer.valueOf(0)));
        this.setTickRandomly(true);
        this.setCreativeTab(null);
        this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
        this.disableStats();
        plantType = type.toString();
	}
	
	
	 public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	    {
	        return CROPS_AABB[((Integer)state.getValue(this.getAgeProperty())).intValue()];
	    }

	    /**
	     * Return true if the block can sustain a Bush
	     */
	    protected boolean canSustainBush(IBlockState state)
	    {

	        return state.getBlock() == getFarmland();
	    }
	    
	    public static Block getFarmland(){
    	switch(plantType){
    	case("applied"):  AE2Module.Farmland;
    	case("cosmetic"): CosmeticModule.Farmland;
    	case("botanical"): BotaniaModule.Farmland;
    	case("ember"): EmbersModule.Farmland;
    	case("rooted"): RootsModule.Farmland;
    	case("blood"): BloodModule.Farmland;
    	}
	    }
    	
	    protected PropertyInteger getAgeProperty()
	    {
	        return AGE;
	    }

	    public int getMaxAge()
	    {
	        return 7;
	    }

	    protected int getAge(IBlockState state)
	    {
	        return (state.getValue(this.getAgeProperty())).intValue();
	    }

	    public IBlockState withAge(int age)
	    {
	        return this.getDefaultState().withProperty(this.getAgeProperty(), age);
	    }

	    public boolean isMaxAge(IBlockState state)
	    {
	        return (state.getValue(this.getAgeProperty())).intValue() >= this.getMaxAge();
	    }

	    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	    {
	        super.updateTick(world, pos, state, rand);

	        if (world.getLightFromNeighbors(pos.up()) > 5)
	        {
	            int i = this.getAge(state);

	            if (i < this.getMaxAge())
	            {

	                if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(world, pos, state, rand.nextInt((10)) <= 2))
	                {
	                    world.setBlockState(pos, this.withAge(i + 1), 2);
	                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(world, pos, state, world.getBlockState(pos));
	                }
	            }
	        }
	    }

	    public void grow(World worldIn, BlockPos pos, IBlockState state)
	    {
	        int i = this.getAge(state) + this.getBonemealAgeIncrease(worldIn);
	        int j = this.getMaxAge();

	        if (i > j)
	        {
	            i = j;
	        }

	        worldIn.setBlockState(pos, this.withAge(i), 2);
	    }

	    protected int getBonemealAgeIncrease(World worldIn)
	    {
	        return MathHelper.getRandomIntegerInRange(worldIn.rand, 0, 2);
	    }

	    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
	    {
	        IBlockState soil = worldIn.getBlockState(pos.down());
	        return (worldIn.getLight(pos) >= 8 || worldIn.canSeeSky(pos)) && soil.getBlock().canSustainPlant(soil, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this);
	    }

	    protected Item getSeed()
	    {
	        return null;
	    }

	    protected Item getCrop()
	    {
	        return null;
	    }

	    @Override
	    public java.util.List<ItemStack> getDrops(net.minecraft.world.IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune)
	    {
	    	World world = (World) worldIn;
	        java.util.List<ItemStack> drops = super.getDrops(worldIn, pos, state, fortune);
	        int age = getAge(state);
	        

	        if (age >= getMaxAge())
	        {
	            for (int i = 0; i < 3 + fortune; ++i)
	            {
	                if (world.rand.nextInt(2 * getMaxAge()) <= age)
	                {
	                	drops.add(new ItemStack(this.getSeed(), 1, 0));
	                }
	            }
	        }
	        return drops;
	    }

	    @Nullable
	    public Item getItemDropped(IBlockState state, Random rand, int fortune)
	    {
	        return this.isMaxAge(state) ? this.getCrop() : this.getSeed();
	    }


	    /**
	     * Whether this IGrowable can grow
	     */
	    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
	    {
	        return !this.isMaxAge(state);
	    }

	    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
	    {
	        return true;
	    }

	    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
	    {
	        this.grow(worldIn, pos, state);
	    }

	    /**
	     * Convert the given metadata into a BlockState for this Block
	     */
	    public IBlockState getStateFromMeta(int meta)
	    {
	        return this.withAge(meta);
	    }

	    /**
	     * Convert the BlockState into the correct metadata value
	     */
	    public int getMetaFromState(IBlockState state)
	    {
	        return this.getAge(state);
	    }

	    protected BlockStateContainer createBlockState()
	    {
	        return new BlockStateContainer(this, new IProperty[] {AGE});
	    }
	
	
	
	
}
