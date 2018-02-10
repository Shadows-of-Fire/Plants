package shadows.plants2.block;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent.Register;
import shadows.placebo.client.IHasModel;
import shadows.placebo.interfaces.IHasRecipe;
import shadows.placebo.interfaces.IItemBlock;
import shadows.placebo.interfaces.ITreeEnum;
import shadows.plants2.Plants2;

public class BlockEnumStairs extends BlockStairs implements IHasModel, IHasRecipe, IItemBlock {

	public BlockEnumStairs(ITreeEnum e, IBlockState state) {
		super(state);
		setRegistryName(e.getName() + "_stairs");
		setUnlocalizedName(Plants2.INFO.getID() + "." + e.getName() + "_stairs");
		setCreativeTab(Plants2.INFO.getDefaultTab());
		Plants2.INFO.getBlockList().add(this);
		ItemBlock ib = createItemBlock();
		if (ib != null) Plants2.INFO.getItemList().add(ib);
	}

	@Override
	public ItemBlock createItemBlock() {
		return new ItemBlock(this);
	}

	@Override
	public void initRecipes(Register<IRecipe> e) {
		// TODO Auto-generated method stub

	}

}
