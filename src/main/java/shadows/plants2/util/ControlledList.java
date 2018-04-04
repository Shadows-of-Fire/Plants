package shadows.plants2.util;

import java.util.ArrayList;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import shadows.placebo.block.IEnumBlock;
import shadows.plants2.Plants2;

public class ControlledList extends ArrayList<IBlockState> {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean add(IBlockState state) {
		if (Plants2.BLOCK_CONFIG.getBoolean("Allow " + translate(state), Configuration.CATEGORY_GENERAL, true, "If this blockstate will generate")) return super.add(state);
		return false;
	}

	@Override
	public void add(int index, IBlockState state) {
		if (Plants2.BLOCK_CONFIG.getBoolean("Allow " + translate(state), Configuration.CATEGORY_GENERAL, true, "If this blockstate will generate")) super.add(index, state);
	}

	private String translate(IBlockState state) {
		Item i = Item.getItemFromBlock(state.getBlock());
		String translate = PlantUtil.sneakyConfigTranslate(i.getUnlocalizedName(new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state))) + ".name");
		if (i == Items.AIR || translate.equals("invalid.name")) return state.getBlock().getRegistryName() + "[type=" + ((IEnumBlock<?>) state.getBlock()).getValue(state).getName() + "]";
		return translate;
	}

}
