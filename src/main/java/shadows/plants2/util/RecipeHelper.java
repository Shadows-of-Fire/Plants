package shadows.plants2.util;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.IShapedRecipe;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreIngredient;
import net.minecraftforge.registries.IForgeRegistryEntry;
import shadows.plants2.data.Constants;
import shadows.plants2.init.ModRegistry;

public class RecipeHelper {

	private static int j = 0;
	private static final String MODID = Constants.MODID;
	private static final String MODNAME = Constants.MODNAME;
	public static final List<IRecipe> recipeList = ModRegistry.RECIPES;

	/**
	 * This adds the recipe to the list of crafting recipes.  Since who cares about names, it adds it as recipeX, where X is the current recipe you are adding.
	 */
	public static void addRecipe(int j, IRecipe rec) {
		if (rec.getRegistryName() == null) recipeList.add(rec.setRegistryName(new ResourceLocation(MODID, "recipe" + j)));
		else recipeList.add(rec);
	}

	/**
	 * This adds the recipe to the list of crafting recipes.  Cares about names.
	 */
	public static void addRecipe(String name, IRecipe rec) {
		if (rec.getRegistryName() == null) recipeList.add(rec.setRegistryName(new ResourceLocation(MODID, name)));
		else recipeList.add(rec);
	}

	/**
	 * Adds a shapeless recipe with X output using an array of inputs. Use Strings for OreDictionary support. This array is not ordered.  Can take a List in place of inputs.
	 */
	public static void addShapeless(ItemStack output, Object... inputs) {
		addRecipe(j++, new ShapelessRecipes(MODID + ":" + j, output, createInput(inputs)));
	}

	public static <T extends IForgeRegistryEntry<?>> void addShapeless(T output, Object... inputs) {
		addShapeless(makeStack(output), inputs);
	}

	/**
	 * Adds a shapeless recipe with X output using an array of inputs. Use Strings for OreDictionary support. This array is not ordered.  This has a custom group.
	 */
	public static void addShapeless(String group, ItemStack output, Object... inputs) {
		addRecipe(j++, new ShapelessRecipes(MODID + ":" + group, output, createInput(inputs)));
	}

	public static <T extends IForgeRegistryEntry<?>> void addShapeless(String group, T output, Object... inputs) {
		addShapeless(group, makeStack(output), inputs);
	}

	/**
	 * Adds a shapeless recipe with X output on a crafting grid that is W x H, using an array of inputs.  Use null for nothing, use Strings for OreDictionary support, this array must have a length of width * height.
	 * This array is ordered, and items must follow from left to right, top to bottom of the crafting grid.
	 */
	public static void addShaped(ItemStack output, int width, int height, Object... input) {
		addRecipe(j++, genShaped(output, width, height, input));
	}

	public static <T extends IForgeRegistryEntry<?>> void addShaped(T output, int width, int height, Object... input) {
		addShaped(makeStack(output), width, height, input);
	}

	/**
	 * Adds a shapeless recipe with X output on a crafting grid that is W x H, using an array of inputs.  Use null for nothing, use Strings for OreDictionary support, this array must have a length of width * height.
	 * This array is ordered, and items must follow from left to right, top to bottom of the crafting grid. This has a custom group.
	 */
	public static void addShaped(String group, ItemStack output, int width, int height, Object... input) {
		addRecipe(j++, genShaped(MODID + ":" + group, output, width, height, input));
	}

	public static <T extends IForgeRegistryEntry<?>> void addShaped(String group, T output, int width, int height, Object... input) {
		addShaped(group, makeStack(output), width, height, input);
	}

	/**
	 * Generates a shaped recipe with a specific width and height. The Object[] is the ingredients, in order from left to right, top to bottom.
	 */
	public static ShapedRecipes genShaped(ItemStack output, int width, int height, Object[] input) {
		if (input[0] instanceof List) input = ((List<?>) input[0]).toArray();
		if (width * height != input.length) throw new UnsupportedOperationException("Attempted to add invalid shaped recipe.  Complain to the author of " + MODNAME);
		NonNullList<Ingredient> inputL = NonNullList.create();
		for (int i = 0; i < input.length; i++) {
			Object k = input[i];
			if (k instanceof String) {
				inputL.add(i, new OreIngredient((String) k));
			} else if (k instanceof ItemStack && !((ItemStack) k).isEmpty()) {
				inputL.add(i, Ingredient.fromStacks((ItemStack) k));
			} else if (k instanceof Item) {
				inputL.add(i, Ingredient.fromStacks(new ItemStack((Item) k)));
			} else if (k instanceof Block) {
				inputL.add(i, Ingredient.fromStacks(new ItemStack((Block) k)));
			} else {
				inputL.add(i, Ingredient.EMPTY);
			}
		}

		return new ShapedRecipes(MODID + ":" + j, width, height, inputL, output);
	}

	/**
	 * Same thing as genShaped above, but uses a specific group.
	 */
	public static ShapedRecipes genShaped(String group, ItemStack output, int l, int w, Object[] input) {
		if (input[0] instanceof List) input = ((List<?>) input[0]).toArray();
		if (l * w != input.length) throw new UnsupportedOperationException("Attempted to add invalid shaped recipe.  Complain to the author of " + MODNAME);
		NonNullList<Ingredient> inputL = NonNullList.create();
		for (int i = 0; i < input.length; i++) {
			Object k = input[i];
			if (k instanceof String) inputL.add(i, new OreIngredient((String) k));
			else if (k instanceof ItemStack && !((ItemStack) k).isEmpty()) inputL.add(i, Ingredient.fromStacks((ItemStack) k));
			else if (k instanceof IForgeRegistryEntry) inputL.add(i, Ingredient.fromStacks(makeStack((IForgeRegistryEntry<?>) k)));
			else inputL.add(i, Ingredient.EMPTY);
		}

		return new ShapedRecipes(group, l, w, inputL, output);
	}

	/**
	 * Creates a list of ingredients based on an Object[].  Valid types are {@link String}, {@link ItemStack}, {@link Item}, and {@link Block}.
	 * Used for shapeless recipes.
	 */
	public static NonNullList<Ingredient> createInput(Object[] input) {
		if (input[0] instanceof List) input = ((List<?>) input[0]).toArray();
		else if (input[0] instanceof Object[]) input = (Object[]) input[0];
		NonNullList<Ingredient> inputL = NonNullList.create();
		for (int i = 0; i < input.length; i++) {
			Object k = input[i];
			if (k instanceof String) inputL.add(i, new OreIngredient((String) k));
			else if (k instanceof ItemStack && !((ItemStack) k).isEmpty()) inputL.add(i, Ingredient.fromStacks((ItemStack) k));
			else if (k instanceof IForgeRegistryEntry) inputL.add(i, Ingredient.fromStacks(makeStack((IForgeRegistryEntry<?>) k)));
			else throw new UnsupportedOperationException("Attempted to add invalid shapeless recipe.  Complain to the author of " + MODNAME);
		}
		return inputL;
	}

	/**
	 * Adds a shapeless recipe with one output and x inputs, all inputs are the same.
	 */
	public static void addSimpleShapeless(ItemStack output, ItemStack input, int numInputs) {
		addShapeless(output, NonNullList.withSize(numInputs, input));
	}

	public static <T extends IForgeRegistryEntry<?>> void addSimpleShapeless(T output, T input, int numInputs) {
		addSimpleShapeless(makeStack(output), makeStack(input), numInputs);
	}

	public static <T extends IForgeRegistryEntry<?>> void addSimpleShapeless(T output, ItemStack input, int numInputs) {
		addSimpleShapeless(makeStack(output), input, numInputs);
	}

	public static <T extends IForgeRegistryEntry<?>> void addSimpleShapeless(ItemStack output, T input, int numInputs) {
		addSimpleShapeless(output, makeStack(input), numInputs);
	}

	/**
	 * Helper method to make an {@link ItemStack} from a block or item.
	 */
	public static <T extends IForgeRegistryEntry<?>> ItemStack makeStack(T thing, int size, int meta) {
		if (thing instanceof Item) return new ItemStack((Item) thing, size, meta);
		return new ItemStack((Block) thing, size, meta);
	}

	public static <T extends IForgeRegistryEntry<?>> ItemStack makeStack(T thing, int size) {
		return makeStack(thing, size, 0);
	}

	public static <T extends IForgeRegistryEntry<?>> ItemStack makeStack(T thing) {
		return makeStack(thing, 1, 0);
	}

	/**
	 * Goes through all recipes and replaces anything that matches with just the provided itemstack and replaces it with another {@link Ingredient}.
	 * @param old The itemstack to replace.
	 * @param newThing The ingredient to replace the stack with.
	 */
	public static void replaceInAllRecipes(ItemStack old, Ingredient newThing) {
		for (IRecipe rec : ForgeRegistries.RECIPES) {
			if (rec instanceof IShapedRecipe) {
				NonNullList<Ingredient> list = NonNullList.create();
				for (Ingredient ing : rec.getIngredients()) {
					if (ing.getMatchingStacks().length == 1 && ing.getMatchingStacks()[0].isItemEqual(old)) {
						list.add(newThing);
					} else list.add(ing);
				}
				ResourceLocation regname = rec.getRegistryName();
				int width = ((IShapedRecipe) rec).getRecipeWidth();
				int height = ((IShapedRecipe) rec).getRecipeHeight();
				ForgeRegistries.RECIPES.register(new ShapedRecipes(rec.getGroup(), width, height, list, rec.getRecipeOutput()).setRegistryName(regname));
			}
		}
	}

}