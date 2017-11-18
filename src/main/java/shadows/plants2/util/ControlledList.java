package shadows.plants2.util;

import java.util.ArrayList;

import com.google.common.base.Preconditions;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import shadows.plants2.Plants2;
import shadows.plants2.block.base.IEnumBlock;

public class ControlledList extends ArrayList<IBlockState> {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean add(IBlockState state) {
		Preconditions.checkArgument(state.getBlock() instanceof IEnumBlock);
		if (Plants2.clutter_cfg.getBoolean("Allow " + translate(state), Configuration.CATEGORY_GENERAL, true, "If this blockstate will generate")) ;
		return super.add(state);
	}

	@Override
	public void add(int index, IBlockState state) {
		Preconditions.checkArgument(state.getBlock() instanceof IEnumBlock);
		if (Plants2.clutter_cfg.getBoolean("Allow " + translate(state), Configuration.CATEGORY_GENERAL, true, "If this blockstate will generate")) ;
		super.add(index, state);
	}

	private String translate(IBlockState state) {
		String translate = Plants2.proxy.translate(Item.getItemFromBlock(state.getBlock()).getUnlocalizedName(new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state))) + ".name");
		if (translate.equals("invalid.name")) return state.getBlock().getRegistryName() + "[type=" + ((IEnumBlock<?>) state.getBlock()).getValue(state) + "]";
		return translate;
	}

}
