package shadows.plants.util;

import java.util.HashMap;
import javax.annotation.Nonnull;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.oredict.OreIngredient;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RecipeHelper {

	private static int j = 0;
	private static final String MODID = Data.MODID;
	private static final String MODNAME = Data.MODNAME;

	/*
	 * This adds the recipe to the list of crafting recipes.  Since who cares about names, it adds it as recipesX, where X is the current recipe you are adding.
	 */
	public static void addRecipe(int j, IRecipe rec) {
		CraftingManager.register(new ResourceLocation(MODID, "recipes" + j), rec);
	}

	/*
	 * This adds the recipe to the list of crafting recipes.  Cares about names.
	 */
	public static void addRecipe(String name, IRecipe rec) {
		CraftingManager.register(new ResourceLocation(MODID, name), rec);
	}

	/*
	 * This adds a shaped recipe to the list of crafting recipes, using the forge format.
	 */
	public static void addOldShaped(ItemStack output, Object... input) {
		addRecipe(j++, new FixedShapedOreRecipe(new ResourceLocation(MODID, "recipes" + j), output, input));
	}

	/*
	 * This adds a shaped recipe to the list of crafting recipes, using the forge format, with a custom group.
	 */
	public static void addOldShaped(String group, ItemStack output, Object... input) {
		addRecipe(j++, new FixedShapedOreRecipe(new ResourceLocation(MODID, group), output, input));
	}

	/*
	 * This adds a shapeless recipe to the list of crafting recipes, using the forge format.
	 */
	public static void addOldShapeless(ItemStack output, Object... input) {
		addRecipe(j++, new ShapelessOreRecipe(new ResourceLocation(MODID, "recipes" + j), output, input));
	}

	/*
	 * This adds a shapeless recipe to the list of crafting recipes, using the forge format, with a custom group.
	 */
	public static void addOldShapeless(String group, ItemStack output, Object... input) {
		addRecipe(j++, new ShapelessOreRecipe(new ResourceLocation(MODID, group), output, input));
	}

	/*
	 * Adds a shapeless recipe with X output using an array of inputs. Use Strings for OreDictionary support. This array is not ordered.
	 */
	public static void addShapeless(ItemStack output, Object... inputs) {
		addRecipe(j++, new ShapelessRecipes(MODID + ":" + j, output, createInput(inputs)));
	}

	public static void addShapeless(Item output, Object... inputs) {
		addShapeless(new ItemStack(output), inputs);
	}

	public static void addShapeless(Block output, Object... inputs) {
		addShapeless(new ItemStack(output), inputs);
	}

	/*
	 * Adds a shapeless recipe with X output using an array of inputs. Use Strings for OreDictionary support. This array is not ordered.  This has a custom group.
	 */
	public static void addShapeless(String group, ItemStack output, Object... inputs) {
		addRecipe(j++, new ShapelessRecipes(MODID + ":" + group, output, createInput(inputs)));
	}

	public static void addShapeless(String group, Item output, Object... inputs) {
		addShapeless(group, new ItemStack(output), inputs);
	}

	public static void addShapeless(String group, Block output, Object... inputs) {
		addShapeless(group, new ItemStack(output), inputs);
	}

	/*
	 * Adds a shapeless recipe with X output on a crafting grid that is W x H, using an array of inputs.  Use null for nothing, use Strings for OreDictionary support, this array must have a length of width * height.
	 * This array is ordered, and items must follow from left to right, top to bottom of the crafting grid.
	 */
	public static void addShaped(ItemStack output, int width, int height, Object... input) {
		addRecipe(j++, genShaped(output, width, height, input));
	}

	public static void addShaped(Item output, int width, int height, Object... input) {
		addShaped(new ItemStack(output), width, height, input);
	}

	public static void addShaped(Block output, int width, int height, Object... input) {
		addShaped(new ItemStack(output), width, height, input);
	}

	/*
	 * Adds a shapeless recipe with X output on a crafting grid that is W x H, using an array of inputs.  Use null for nothing, use Strings for OreDictionary support, this array must have a length of width * height.
	 * This array is ordered, and items must follow from left to right, top to bottom of the crafting grid. This has a custom group.
	 */
	public static void addShaped(String group, ItemStack output, int width, int height, Object... input) {
		addRecipe(j++, genShaped(MODID + ":" + group, output, width, height, input));
	}

	public static void addShaped(String group, Item output, int width, int height, Object... input) {
		addShaped(group, new ItemStack(output), width, height, input);
	}

	public static void addShaped(String group, Block output, int width, int height, Object... input) {
		addShaped(group, new ItemStack(output), width, height, input);
	}

	public static ShapedRecipes genShaped(ItemStack output, int l, int w, Object[] input) {
		if (input[0] instanceof Object[])
			input = (Object[]) input[0];
		if (l * w != input.length)
			throw new UnsupportedOperationException(
					"Attempted to add invalid shaped recipe.  Complain to the author of  " + MODNAME);
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

		return new ShapedRecipes(MODID + ":" + j, l, w, inputL, output);
	}

	public static ShapedRecipes genShaped(String group, ItemStack output, int l, int w, Object[] input) {
		if (input[0] instanceof Object[])
			input = (Object[]) input[0];
		if (l * w != input.length)
			throw new UnsupportedOperationException(
					"Attempted to add invalid shaped recipe.  Complain to the author of  " + MODNAME);
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

		return new ShapedRecipes(group, l, w, inputL, output);
	}

	public static NonNullList<Ingredient> createInput(Object[] input) {
		if (input[0] instanceof Object[])
			input = (Object[]) input[0];
		NonNullList<Ingredient> inputL = NonNullList.create();
		for (int i = 0; i < input.length; i++) {
			Object k = input[i];
			if (k instanceof String) {
				inputL.add(i, new OreIngredient((String) k));
			} else if (k instanceof ItemStack) {
				inputL.add(i, Ingredient.fromStacks((ItemStack) k));
			} else if (k instanceof Item) {
				inputL.add(i, Ingredient.fromStacks(new ItemStack((Item) k)));
			} else if (k instanceof Block) {
				inputL.add(i, Ingredient.fromStacks(new ItemStack((Block) k)));
			} else {
				throw new UnsupportedOperationException(
						"Attempted to add invalid shapeless recipe.  Complain to the author of  " + MODNAME);
			}
		}
		return inputL;
	}

	//This is ShapedOreRecipe modified to actually work until forge re-fixes it in an update.
	public static class FixedShapedOreRecipe implements IRecipe {
		//Added in for future ease of change, but hard coded for now.
		public static final int MAX_CRAFT_GRID_WIDTH = 3;
		public static final int MAX_CRAFT_GRID_HEIGHT = 3;

		@Nonnull
		protected ItemStack output = ItemStack.EMPTY;
		protected NonNullList<Ingredient> input = null;
		protected int width = 0;
		protected int height = 0;
		protected boolean mirrored = true;
		protected ResourceLocation group;

		public FixedShapedOreRecipe(ResourceLocation group, Block result, Object... recipe) {
			this(group, new ItemStack(result), recipe);
		}

		public FixedShapedOreRecipe(ResourceLocation group, Item result, Object... recipe) {
			this(group, new ItemStack(result), recipe);
		}

		public FixedShapedOreRecipe(ResourceLocation group, @Nonnull ItemStack result, Object... recipe) {
			this.group = group;
			output = result.copy();

			String shape = "";
			int idx = 0;

			if (recipe[idx] instanceof Boolean) {
				mirrored = (Boolean) recipe[idx];
				if (recipe[idx + 1] instanceof Object[]) {
					recipe = (Object[]) recipe[idx + 1];
				} else {
					idx = 1;
				}
			}

			if (recipe[idx] instanceof String[]) {
				String[] parts = ((String[]) recipe[idx++]);

				for (String s : parts) {
					width = s.length();
					shape += s;
				}

				height = parts.length;
			} else {
				while (recipe[idx] instanceof String) {
					String s = (String) recipe[idx++];
					shape += s;
					width = s.length();
					height++;
				}
			}

			if (width * height != shape.length()) {
				String ret = "Invalid shaped ore recipe: ";
				for (Object tmp : recipe) {
					ret += tmp + ", ";
				}
				ret += output;
				throw new RuntimeException(ret);
			}

			HashMap<Character, Ingredient> itemMap = Maps.newHashMap();

			for (; idx < recipe.length; idx += 2) {
				Character chr = (Character) recipe[idx];
				Object in = recipe[idx + 1];

				if (in instanceof ItemStack) {
					itemMap.put(chr, Ingredient.fromStacks(((ItemStack) in).copy()));
				} else if (in instanceof Item) {
					itemMap.put(chr, Ingredient.fromStacks(new ItemStack((Item) in)));
				} else if (in instanceof Block) {
					itemMap.put(chr,
							Ingredient.fromStacks(new ItemStack((Block) in)));
				} else if (in instanceof String) {
					itemMap.put(chr, new OreIngredient((String) in));
				} else if (in instanceof Ingredient) {
					itemMap.put(chr, (Ingredient) in);
				} else {
					String ret = "Invalid shaped ore recipe: ";
					for (Object tmp : recipe) {
						ret += tmp + ", ";
					}
					ret += output;
					throw new RuntimeException(ret);
				}
			}

			this.input = NonNullList.withSize(width * height, Ingredient.EMPTY);
			int x = 0;
			for (char chr : shape.toCharArray()) {
				if (itemMap.get(chr) != null)
					input.set(x, itemMap.get(chr));
				x++;
			}
		}

		/**
		 * Returns an Item that is the result of this recipe
		 */
		@Override
		@Nonnull
		public ItemStack getCraftingResult(@Nonnull InventoryCrafting var1) {
			return output.copy();
		}

		@Override
		@Nonnull
		public ItemStack getRecipeOutput() {
			return output;
		}

		/**
		 * Used to check if a recipe matches current crafting inventory
		 */
		@Override
		public boolean matches(InventoryCrafting inv, World world) {
			for (int x = 0; x <= MAX_CRAFT_GRID_WIDTH - width; x++) {
				for (int y = 0; y <= MAX_CRAFT_GRID_HEIGHT - height; ++y) {
					if (checkMatch(inv, x, y, false)) {
						return true;
					}

					if (mirrored && checkMatch(inv, x, y, true)) {
						return true;
					}
				}
			}

			return false;
		}

		protected boolean checkMatch(InventoryCrafting inv, int startX, int startY, boolean mirror) {
			for (int x = 0; x < MAX_CRAFT_GRID_WIDTH; x++) {
				for (int y = 0; y < MAX_CRAFT_GRID_HEIGHT; y++) {
					int subX = x - startX;
					int subY = y - startY;
					Ingredient target = null;

					if (subX >= 0 && subY >= 0 && subX < width && subY < height) {
						if (mirror) {
							target = input.get(width - subX - 1 + subY * width);
						} else {
							target = input.get(subX + subY * width);
						}
					}

					if (!target.apply(inv.getStackInRowAndColumn(x, y))) {
						return false;
					}
				}
			}

			return true;
		}

		public FixedShapedOreRecipe setMirrored(boolean mirror) {
			mirrored = mirror;
			return this;
		}

		public NonNullList<Ingredient> func_192400_c() {
			return this.input;
		}

		@Override
		public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) //getRecipeLeftovers
		{
			return ForgeHooks.defaultRecipeGetRemainingItems(inv);
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}
		
		@Override
		public String func_193358_e() {
			return this.group.toString();
		}
		
		@Override
		public boolean canFit(int width, int height) {
			return width >= this.width && height >= this.height;
		}
	}

}
