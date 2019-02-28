package shadows.naturalis.registry;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shadows.naturalis.Naturalis;
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
	}

	@SubscribeEvent
	public void blocks(Register<Block> e) {

	}

	@SubscribeEvent
	public void items(Register<Item> e) {

	}

}
