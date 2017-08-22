package shadows.plants2.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants2.block.base.BlockEnum;
import shadows.plants2.client.RenamedStateMapper;
import shadows.plants2.data.IHasRecipe;
import shadows.plants2.data.enums.IPlankEnum;
import shadows.plants2.util.PlantUtil;
import shadows.plants2.util.RecipeHelper;

public class BlockEnumPlanks<E extends Enum<E> & IPlankEnum> extends BlockEnum<E> implements IHasRecipe {

	public BlockEnumPlanks(String name, Class<E> enumClass, int predicateIndex) {
		super(name, Material.WOOD, SoundType.WOOD, 2, 5, enumClass, "type", (e) -> e.getPredicateIndex() == predicateIndex);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModels(ModelRegistryEvent e) {
		for (int i = 0; i < types.size(); i++) {
			PlantUtil.sMRL("blocks", this, i, "type=" + types.get(i).getName() + "_planks");
		}
		ModelLoader.setCustomStateMapper(this, new RenamedStateMapper("blocks", "_planks"));
	}

	@Override
	public void initRecipes(Register<IRecipe> ev) {
		for(E e : getTypes()) RecipeHelper.addShapeless(new ItemStack(this, 4, e.getMetadata()), e.genLogStack());
	}

}
