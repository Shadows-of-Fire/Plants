package shadows.plants.block.internal.cosmetic;

import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants.block.BushBase;
import shadows.plants.common.EnumModule;
import shadows.plants.common.EnumTempZone;
import shadows.plants.common.IMetaPlant;
import shadows.plants.item.internal.cosmetic.ItemBlockDoubleMetaBush;
import shadows.plants.util.Config;

public class BlockDoubleMetaBush extends BushBase implements IMetaPlant{

    public static final PropertyInteger META = PropertyInteger.create("meta", 0, 7);
    public static final PropertyBool UPPER = PropertyBool.create("upper");
    public Map<Integer, EnumTempZone> tempmap;
    private int max;
    
    public BlockDoubleMetaBush(String name, @Nullable List<Block> soil, Map<Integer, EnumTempZone> map, int maxdata)
    {
        super(name, EnumModule.COSMETIC, soil);
        setDefaultState(this.blockState.getBaseState().withProperty(UPPER, true).withProperty(META, 0));
        tempmap = map;
		max = maxdata;
		GameRegistry.register(new ItemBlockDoubleMetaBush(this));
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return FULL_BLOCK_AABB;
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return super.canPlaceBlockAt(worldIn, pos) && worldIn.isAirBlock(pos.up());
    }

    @Override
    protected void checkAndDropBlock(World world, BlockPos pos, IBlockState state)
    {
        if (!this.canBlockStay(world, pos, state))
        {
            boolean flag = state.getValue(UPPER);
            BlockPos blockpos = flag ? pos : pos.up();
            BlockPos blockpos1 = flag ? pos.down() : pos;
            Block block = (Block)(flag ? this : world.getBlockState(blockpos).getBlock());
            Block block1 = (Block)(flag ? world.getBlockState(blockpos1).getBlock() : this);

            if (!flag) this.dropBlockAsItem(world, pos, state, 0);

            if (block == this)
            {
            	world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
            }

            if (block1 == this)
            {
            	world.setBlockState(blockpos1, Blocks.AIR.getDefaultState(), 3);
            }
        }
    }
    
    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        if (state.getBlock() != this) return super.canBlockStay(world, pos, state); //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
        if (state.getValue(UPPER))
        {
            return world.getBlockState(pos.down()).getBlock() == this;
        }
        else
        {
            IBlockState iblockstate = world.getBlockState(pos.up());
            return iblockstate.getBlock() == this && super.canBlockStay(world, pos, iblockstate);
        }
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    @Nullable @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (state.getValue(UPPER))
        {
            return null;
        }
        else
        {
            return Item.getItemFromBlock(this);
        }
    }

    /**
     * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
     * returns the metadata of the dropped item based on the old metadata of the block.
     */
    @Override
    public int damageDropped(IBlockState state)
    {
    	return state.getValue(META);
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos.up(), state.withProperty(UPPER, true), 2);
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        if (state.getValue(UPPER))
        {
            if (world.getBlockState(pos.down()).getBlock() == this)
            {
                if (player.capabilities.isCreativeMode)
                {
                	world.setBlockToAir(pos.down());
                }
                else
                {
                    world.destroyBlock(pos.down(), true);
                    
                    if (world.isRemote)
                    {
                    	world.setBlockToAir(pos.down());
                    }
                    if (player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() == Items.SHEARS)
                    {
                        world.setBlockToAir(pos.down());
                    }
                }
            }
        }
        else if (world.getBlockState(pos.up()).getBlock() == this)
        {
        	world.setBlockState(pos.up(), Blocks.AIR.getDefaultState(), 2);
        }

        super.onBlockHarvested(world, pos, state, player);
    }
    
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT) @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
    {
        for (int i = 0; i < 8; i++)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public ItemStack getItem(World world, BlockPos pos, IBlockState state)
    {
        return new ItemStack(this, 1, state.getValue(META));
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
    	int k = meta % 2;
        if (k == 0) return this.getDefaultState().withProperty(UPPER, true).withProperty(META, getActualMeta(meta));
        if (k == 1) return this.getDefaultState().withProperty(UPPER, false).withProperty(META, getActualMeta(meta));
        else return null;
    }

    
    public static int getActualMeta(int meta){ //evens are upper half, odds are lower half
    	//needs to return the Actual (IProperty) meta (0-7) for the block from state meta (0-15)
    	int k = meta % 2; //if == 1 this is a lower
    	float j = meta / 2; //if k = 1 this will have a .5 
    	if(k == 0) return (int)j;
    	if(k == 1) return (int)(j-.5);
    	else return 0;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state)
    {
        boolean k = state.getValue(UPPER);
        int j = state.getValue(META);
        
        if(k) return (j * 2);
        if(!k) return (1 + j * 2);
        else return 0;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {META, UPPER});
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest)
    {
        //Forge: Break both parts on the client to prevent the top part flickering as default type for a few frames.
        if (state.getBlock() ==  this && !state.getValue(UPPER) && world.getBlockState(pos.up()).getBlock() == this)
            world.setBlockToAir(pos.up());
        return world.setBlockToAir(pos);
    }

	@Override
	public float getTempMax(IBlockState state) {
		int k = state.getValue(META);
		return tempmap.get(k).getMax();
	}

	@Override
	public float getTempMin(IBlockState state) {
		int k = state.getValue(META);
		return tempmap.get(k).getMin();
	}
	
	@Override
	public int getMaxData() {
		return max;
	}

	@Override
	public int getMetaPropValue(IBlockState state) {
		return state.getValue(META);
	}
	
    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune){
    	if(world.getBlockState(pos).getValue(UPPER)) return getActualDrops(world, pos, world.getBlockState(pos).withProperty(UPPER, false), fortune);
    	if(!world.getBlockState(pos).getValue(UPPER)) return getActualDrops(world, pos, world.getBlockState(pos), fortune);
    	else {System.out.println("Oh something is very very wrong"); return getDrops(world, pos, getDefaultState(), fortune);}
    }
	
}