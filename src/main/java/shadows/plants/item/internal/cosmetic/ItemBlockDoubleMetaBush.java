package shadows.plants.item.internal.cosmetic;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants.block.internal.cosmetic.BlockDoubleMetaBush;

public class ItemBlockDoubleMetaBush extends ItemBlock {

	private BlockDoubleMetaBush block;

	public ItemBlockDoubleMetaBush(Block block) {
		super(block);
		setHasSubtypes(true);
		setMaxDamage(0);
		setRegistryName(block.getRegistryName());
		this.block = (BlockDoubleMetaBush) block;
	}

	/**
	 * Converts the given ItemStack damage value into a metadata value to be
	 * placed in the world when this Item is placed as a Block (mostly used with
	 * ItemBlocks).
	 */
	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		if (stack.getMetadata() > this.block.getMaxData())
			return EnumActionResult.FAIL;
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();

		if (!block.isReplaceable(world, pos)) {
			pos = pos.offset(facing);
		}

		if (!stack.isEmpty() && player.canPlayerEdit(pos, facing, stack)
				&& world.mayPlace(this.block, pos, false, facing, (Entity) null)) {
			BlockDoubleMetaBush block2 = (BlockDoubleMetaBush) Block.getBlockFromItem(stack.getItem());
			IBlockState state2 = null;
			if (stack.getMetadata() < 8)
				state2 = block2.getDefaultState().withProperty(BlockDoubleMetaBush.UPPER, false)
						.withProperty(BlockDoubleMetaBush.META, stack.getMetadata());
			else if (stack.getMetadata() >= 8)
				state2 = block2.getDefaultState().withProperty(BlockDoubleMetaBush.UPPER, false)
						.withProperty(BlockDoubleMetaBush.META, (stack.getMetadata() / 2));

			if (placeBlockAt(stack, player, world, pos, facing, hitX, hitY, hitZ, state2)) {
				SoundType soundtype = SoundType.PLANT;
				world.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS,
						(soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
				stack.shrink(1);
			}

			return EnumActionResult.SUCCESS;
		} else {
			return EnumActionResult.FAIL;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
		if(isInCreativeTab(tab)){
		for (int i = 0; i <= this.block.getMaxData(); i++) {
			list.add(new ItemStack(this, 1, i));
		}
		}
	}

	/**
	 * Returns the unlocalized name of this item. This version accepts an
	 * ItemStack so different stacks can have different names based on their
	 * damage or NBT.
	 */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "." + stack.getMetadata();
	}
}