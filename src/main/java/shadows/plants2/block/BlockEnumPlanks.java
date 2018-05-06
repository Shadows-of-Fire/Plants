package shadows.plants2.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.oredict.OreDictionary;
import shadows.placebo.Placebo;
import shadows.placebo.block.BlockEnum;
import shadows.placebo.interfaces.IHasRecipe;
import shadows.placebo.interfaces.IPlankEnum;
import shadows.placebo.util.PlaceboUtil;
import shadows.plants2.Plants2;

public class BlockEnumPlanks<E extends Enum<E> & IPlankEnum> extends BlockEnum<E> implements IHasRecipe {

	public BlockEnumPlanks(String name, Class<E> enumClass, int predicateIndex) {
		super(name, Material.WOOD, SoundType.WOOD, 2, 5, enumClass, "type", (e) -> e.getPredicateIndex() == predicateIndex, Plants2.INFO);
	}

	@Override
	public void initModels(ModelRegistryEvent e) {
		for (int i = 0; i < types.size(); i++) {
			PlaceboUtil.sMRL("blocks", this, i, "type=" + types.get(i).getName() + "_planks");
		}
		Placebo.PROXY.useRenamedMapper(this, "blocks", "_planks");
	}

	@Override
	public void initRecipes(Register<IRecipe> ev) {
		OreDictionary.registerOre("plankWood", new ItemStack(this, 1, OreDictionary.WILDCARD_VALUE));

		for (E e : getTypes())
			Plants2.HELPER.addShapeless(new ItemStack(this, 4, e.getMetadata()), e.genLogStack());
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(property, types.get(meta));
	}

	@Override
	public String getUnlocalizedName() {
		return "tile.plants2.planks";
	}
	
	@Override
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return world.getBlockState(pos).getValue(property).isNether() ? 0 : 20;
	}
	
	@Override
	public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return world.getBlockState(pos).getValue(property).isNether() ? 0 : 5;
	}

}
