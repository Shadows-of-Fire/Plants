package shadows.plants2.data;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class StackPrimer {

	final Item item;
	final int size;
	final int meta;

	public StackPrimer(Item item, int size, int meta) {
		this.item = item;
		this.size = size;
		this.meta = meta;
	}

	public StackPrimer(Item item, int size) {
		this(item, size, 0);
	}

	public StackPrimer(Item item) {
		this(item, 1, 0);
	}

	public ItemStack genStack() {
		return new ItemStack(item, size, meta);
	}

	public boolean isEmpty() {
		return item == null || size <= 0;
	}

}
