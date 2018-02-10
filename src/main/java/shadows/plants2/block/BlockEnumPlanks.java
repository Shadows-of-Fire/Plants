package shadows.plants2.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
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
		for (E e : getTypes())
			Plants2.HELPER.addShapeless(new ItemStack(this, 4, e.getMetadata()), e.genLogStack());
	}

}
