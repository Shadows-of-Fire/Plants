package shadows.naturalis.registry;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shadows.naturalis.Naturalis;
import shadows.placebo.data.IHasRecipe;
import shadows.placebo.util.RecipeHelper;

/**
 * Handles loading of most game objects.
 * @author Shadows
 *
 */
public class RegHandler extends RecipeHelper {

	public static final List<Block> BLOCKS = new LinkedList<>();
	public static final List<Item> ITEMS = new LinkedList<>();

	public RegHandler() {
		super(Naturalis.MODID, Naturalis.MODNAME);
	}

	@Override
	public void addRecipes() {
		BLOCKS.forEach(this::checkForRecipe);
		ITEMS.forEach(this::checkForRecipe);
	}

	@SubscribeEvent
	public void blocks(Register<Block> e) {
		NaturalBlocks.load();
		BLOCKS.forEach(e.getRegistry()::register);
	}

	@SubscribeEvent
	public void items(Register<Item> e) {
		NaturalItems.load();
		ITEMS.forEach(e.getRegistry()::register);
	}

	void checkForRecipe(Object o) {
		if (o instanceof IHasRecipe) ((IHasRecipe) o).addRecipes(this);
	}

}
