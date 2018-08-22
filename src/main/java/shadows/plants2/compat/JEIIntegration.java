package shadows.plants2.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.item.ItemStack;
import shadows.plants2.init.ModRegistry;

@JEIPlugin
public class JEIIntegration implements IModPlugin {

	@Override
	public void register(IModRegistry registry) {
		registry.addIngredientInfo(new ItemStack(ModRegistry.BREWING_CAULDRON), VanillaTypes.ITEM, "info.plants2.cauldron");
		registry.addIngredientInfo(new ItemStack(ModRegistry.FLOWERPOT), VanillaTypes.ITEM, "info.plants2.flowerpot");
		registry.addIngredientInfo(new ItemStack(ModRegistry.JAR), VanillaTypes.ITEM, "info.plants2.jar");
		registry.addIngredientInfo(new ItemStack(ModRegistry.PLANTBALL), VanillaTypes.ITEM, "info.plants2.plantball", "", "", "info.plants2.plantball.2");
	}

}
