package shadows.plants2.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants2.data.Constants;
import shadows.plants2.data.enums.IPropertyEnum;
import shadows.plants2.item.base.ItemBase;
import shadows.plants2.util.PlantUtil;

public class ItemBigEnum<E extends Enum<E> & IPropertyEnum> extends ItemBase {

	public final E[] values;

	public ItemBigEnum(String name, E[] values) {
		super(name);
		setHasSubtypes(true);
		this.values = values;
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab))
			for (E e : values)
				items.add(new ItemStack(this, 1, e.ordinal()));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModels(ModelRegistryEvent ev) {
		for (E e : values)
			PlantUtil.sMRL("items", this, e.ordinal(), "item=" + e.getName());
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "item." + Constants.MODID + "." + values[stack.getMetadata()].getName();
	}

}
