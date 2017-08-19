package shadows.plants2.data;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent.Register;

public interface IHasRecipe {

	public void initRecipes(Register<IRecipe> e);

}
