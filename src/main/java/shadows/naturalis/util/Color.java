package shadows.naturalis.util;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import shadows.placebo.util.StackPrimer;

public enum Color {

	WHITE(new StackPrimer(Items.DYE, 1, 15)),
	ORANGE(new StackPrimer(Items.DYE, 1, 14)),
	MAGENTA(new StackPrimer(Items.DYE, 1, 13)),
	LIGHT_BLUE(new StackPrimer(Items.DYE, 1, 12)),
	YELLOW(new StackPrimer(Items.DYE, 1, 11)),
	LIME(new StackPrimer(Items.DYE, 1, 10)),
	PINK(new StackPrimer(Items.DYE, 1, 9)),
	GRAY(new StackPrimer(Items.DYE, 1, 8)),
	LIGHT_GRAY(new StackPrimer(Items.DYE, 1, 7)),
	CYAN(new StackPrimer(Items.DYE, 1, 6)),
	PURPLE(new StackPrimer(Items.DYE, 1, 5)),
	BLUE(new StackPrimer(Items.DYE, 1, 4)),
	BROWN(new StackPrimer(Items.DYE, 1, 3)),
	GREEN(new StackPrimer(Items.DYE, 1, 2)),
	RED(new StackPrimer(Items.DYE, 1, 1)),
	BLACK(new StackPrimer(Items.DYE, 1, 0));

	StackPrimer item;

	Color(StackPrimer item) {
		this.item = item;
	}

	public ItemStack get() {
		return item.get();
	}
}
