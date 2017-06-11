package shadows.plants.util;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreIngredient;

public class RecipeHelper {

	private static int j = 0;

	/*
	 * This adds the recipe to the list of crafting recipes.  Since who cares about names, it adds it as recipesX, where X is the current recipe you are adding.
	 */
	public static void addRecipe(int j, IRecipe rec) {
		CraftingManager.func_193372_a(new ResourceLocation(Data.MODID, "recipes" + j), rec);
	}

	/*
	 * Adds a shapeless recipes with X Output using a list of inputs.
	 */
	public static void addShapeless(ItemStack output, List<ItemStack> inputs) {
		addShapeless(output, inputs.toArray());
	}

	/*
	 * Adds a shapeless recipe with X output using an array of inputs. Use Strings for OreDictionary support.
	 */
	public static void addShapeless(ItemStack output, Object[] inputs) {
		addRecipe(j++, new ShapelessRecipes(String.valueOf(j), output, createInput(inputs)));
	}

	/*
	 * Adds a shapeless recipe with X output on a crafting grid that is W x H, using an array of inputs.  Use null for nothing, use Strings for OreDictionary support, this array must have a length of width * height.
	 */
	public static void addShaped(ItemStack output, int width, int height, Object[] input) {
		addRecipe(j++, genShaped(output, width, height, input));
	}

	public static void addShapeless(Item output, List<ItemStack> inputs) {
		addShapeless(output, inputs.toArray());
	}

	public static void addShapeless(Item output, Object[] inputs) {
		addRecipe(j++, new ShapelessRecipes(String.valueOf(j), new ItemStack(output), createInput(inputs)));
	}

	public static void addShaped(Item output, int width, int height, Object[] input) {
		addRecipe(j++, genShaped(new ItemStack(output), width, height, input));
	}

	public static void addShapeless(Block output, List<ItemStack> inputs) {
		addShapeless(output, inputs.toArray());
	}

	public static void addShapeless(Block output, Object[] inputs) {
		addRecipe(j++, new ShapelessRecipes(String.valueOf(j), new ItemStack(output), createInput(inputs)));
	}

	public static void addShaped(Block output, int width, int height, Object[] input) {
		addRecipe(j++, genShaped(new ItemStack(output), width, height, input));
	}

	public static ShapedRecipes genShaped(ItemStack output, int l, int w, Object[] input) {
		if (l * w != input.length)
			throw new UnsupportedOperationException(
					"Attempted to add invalid shaped recipe.  Complain to the author of  " + Data.MODNAME);
		NonNullList<Ingredient> inputL = NonNullList.create();
		for (int i = 0; i < input.length; i++) {
			Object k = input[i];
			if (k instanceof String) {
				inputL.add(i, new OreIngredient((String) k));
			} else if (k instanceof ItemStack) {
				inputL.add(i, Ingredient.func_193369_a((ItemStack) k));
			} else if (k instanceof Item) {
				inputL.add(i, Ingredient.func_193367_a((Item) k));
			} else if (k instanceof Block) {
				inputL.add(i, Ingredient.func_193369_a(new ItemStack((Block) k)));
			} else {
				inputL.add(i, Ingredient.field_193370_a);
			}
		}

		return new ShapedRecipes(String.valueOf(j), l, w, inputL, output);
	}

	public static NonNullList<Ingredient> createInput(Object[] input) {
		NonNullList<Ingredient> inputL = NonNullList.create();
		for (int i = 0; i < input.length; i++) {
			Object k = input[i];
			if (k instanceof String) {
				inputL.add(i, new OreIngredient((String) k));
			} else if (k instanceof ItemStack) {
				inputL.add(i, Ingredient.func_193369_a((ItemStack) k));
			} else if (k instanceof Item) {
				inputL.add(i, Ingredient.func_193367_a((Item) k));
			} else if (k instanceof Block) {
				inputL.add(i, Ingredient.func_193369_a(new ItemStack((Block) k)));
			} else {
				throw new UnsupportedOperationException(
						"Attempted to add invalid shapeless recipe.  Complain to the author of  " + Data.MODNAME);
			}
		}
		return inputL;
	}

}
