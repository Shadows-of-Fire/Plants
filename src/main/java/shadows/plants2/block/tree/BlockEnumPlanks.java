package shadows.plants2.block.tree;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import shadows.placebo.Placebo;
import shadows.placebo.block.base.BlockEnum;
import shadows.placebo.interfaces.IHasRecipe;
import shadows.placebo.util.PlaceboUtil;
import shadows.plants2.Plants2;

public class BlockEnumPlanks<E extends Enum<E> & ITreeEnum<E>> extends BlockEnum<E> implements IHasRecipe {

	public BlockEnumPlanks(Material mat, SoundType sound, float hard, float res, E type) {
		super(type.getName() + "_planks", mat, sound, hard, res, type, Plants2.INFO);
	}

	public BlockEnumPlanks(E type) {
		this(Material.WOOD, SoundType.WOOD, 2, 5, type);
	}

	@Override
	public void initModels(ModelRegistryEvent e) {
		PlaceboUtil.sMRL("blocks", this, 0, "type=" + type.getName() + "_planks");
		Placebo.PROXY.useRenamedMapper(this, "blocks", "_planks");
	}

	@Override
	public void initRecipes(Register<IRecipe> e) {
		Plants2.HELPER.addShapeless(new ItemStack(this, 4), type.getTree().getLog());
	}

}
