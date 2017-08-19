package shadows.plants2.item.base;

import net.minecraft.item.Item;
import shadows.plants2.client.IHasModel;
import shadows.plants2.data.Constants;
import shadows.plants2.init.ModRegistry;

public abstract class ItemBase extends Item implements IHasModel {

	public ItemBase(String name) {
		setRegistryName(name);
		setUnlocalizedName(Constants.MODID + "." + name);
		setCreativeTab(ModRegistry.TAB);
		ModRegistry.ITEMS.add(this);
	}

}
