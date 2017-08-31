package shadows.plants2.block.forgotten;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent.Register;
import shadows.plants2.block.base.BlockEnum;
import shadows.plants2.data.IHasRecipe;
import shadows.plants2.data.enums.TheBigBookOfEnums.Crystals;

public class BlockCrystal extends BlockEnum<Crystals> implements IHasRecipe {

	public BlockCrystal() {
		super("crystal", Material.GLASS, SoundType.GLASS, 5F, 20F, Crystals.class);
	}

	@Override
	@Deprecated
	public float getBlockHardness(IBlockState state, World world, BlockPos pos) {
		return state.getValue(property).isShard() ? 0.3F : 5F;
	}

	@Override
	public void initRecipes(Register<IRecipe> e) {
		//TODO: add recipes for crystal tools etc here.
	}

}
