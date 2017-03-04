package shadows.plants.item.internal.cosmetic;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMetaBush extends ItemBlock {
	public ItemBlockMetaBush(Block block) {
		super(block);
		setHasSubtypes(true);
		setMaxDamage(0);
		setRegistryName(block.getRegistryName());
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