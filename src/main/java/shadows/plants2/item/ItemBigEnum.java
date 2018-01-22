package shadows.plants2.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.event.ModelRegistryEvent;
import shadows.placebo.interfaces.IPropertyEnum;
import shadows.placebo.item.base.ItemBase;
import shadows.placebo.util.PlaceboUtil;
import shadows.plants2.Plants2;

public class ItemBigEnum<E extends Enum<E> & IPropertyEnum> extends ItemBase {

	public final E type;

	public ItemBigEnum(E value) {
		super(value.getName(), Plants2.INFO);
		this.type = value;
		value.set(this);
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) items.add(new ItemStack(this));
	}

	@Override
	public void initModels(ModelRegistryEvent ev) {
		PlaceboUtil.sMRL("items", this, type.ordinal(), "item=" + type.getName());
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "item." + Plants2.MODID + "." + type.getName();
	}

}
