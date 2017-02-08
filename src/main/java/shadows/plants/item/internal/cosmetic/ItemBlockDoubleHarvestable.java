package shadows.plants.item.internal.cosmetic;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import shadows.plants.block.internal.cosmetic.BlockDoubleHarvestable;
import shadows.plants.block.internal.cosmetic.BlockDoubleMetaBush;
import shadows.plants.util.Data;

public class ItemBlockDoubleHarvestable extends ItemBlock{

	public ItemBlockDoubleHarvestable(Block block) {
		super(block);
        setHasSubtypes(false);
        setMaxDamage(0);
        setRegistryName(block.getRegistryName());
        setUnlocalizedName(Data.MODID + "." + getRegistryName());
	}
	
    @Override
    public int getMetadata(int damage)
    {
        return 0;
    }
    
    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if (!block.isReplaceable(world, pos))
        {
            pos = pos.offset(facing);
        }

        if (stack.stackSize != 0 && player.canPlayerEdit(pos, facing, stack) && world.canBlockBePlaced(this.block, pos, false, facing, (Entity)null, stack))
        {
            BlockDoubleHarvestable block2 = (BlockDoubleHarvestable) Block.getBlockFromItem(stack.getItem());
            IBlockState state2 = block2.getBlockState().getBaseState().withProperty(BlockDoubleHarvestable.UPPER, false).withProperty(BlockDoubleHarvestable.FRUIT, false);            
            if (placeBlockAt(stack, player, world, pos, facing, hitX, hitY, hitZ, state2))
            {
            	world.setBlockState(pos.up(), block2.getDefaultState());
            	SoundType soundtype = SoundType.PLANT;
                world.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                --stack.stackSize;
            }

            return EnumActionResult.SUCCESS;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }

}
