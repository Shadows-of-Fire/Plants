package shadows.plants2.compat;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.IForgeRegistryEntry;
import shadows.placebo.block.base.IEnumBlock;
import shadows.placebo.util.PlaceboUtil;
import shadows.plants2.block.BlockEnumBush;
import shadows.plants2.data.Constants;

public class DefaultFlowerpot implements IFlowerpotHandler {

	@Override
	public String handleFlowerpot(IBlockState state, ItemStack stack) {
		String name = "none";
		Block block = state.getBlock();
		if (block instanceof BlockEnumBush<?>) {
			name = ((IEnumBlock<?>) block).getValue(state).getName();
		} else if (block == Blocks.RED_FLOWER || block == Blocks.YELLOW_FLOWER) name = state.getValue(((BlockFlower) block).getTypeProperty()).getName();
		else if (block == Blocks.RED_MUSHROOM) name = "mushroom_red";
		else if (block == Blocks.BROWN_MUSHROOM) name = "mushroom_brown";
		else if (block == Blocks.SAPLING) name = state.getValue(BlockSapling.TYPE).getName() + "_sapling";
		else if (block == Blocks.DEADBUSH) name = "dead_bush";
		else if (block == Blocks.CACTUS) name = "cactus";
		else if (block == Blocks.TALLGRASS && state.getValue(BlockTallGrass.TYPE) == BlockTallGrass.EnumType.FERN) name = "fern";
		return name;
	}

	@Override
	public String getModId() {
		return Constants.MODID;
	}

	@Override
	public String getStatePrefix() {
		return "";
	}

	@Override
	public boolean owns(IForgeRegistryEntry<?> entry) {
		return PlaceboUtil.isOwnedBy(entry, getModId()) || PlaceboUtil.isOwnedBy(entry, "minecraft");
	}

}
