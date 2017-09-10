package shadows.plants2.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistryEntry;
import shadows.plants2.util.PlantUtil;

public interface IHasModel {

	@SideOnly(Side.CLIENT)
	default public void initModels(ModelRegistryEvent e) {
		if (this instanceof Item)
			PlantUtil.sMRL("items", (Item) this, 0, "item=" + ((Item) this).getRegistryName().getResourcePath());
		else if (this instanceof Block)
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock((Block) this), 0, new ModelResourceLocation(((IForgeRegistryEntry<?>) this).getRegistryName(), "inventory"));
		else throw new IllegalStateException("wat are u doin");
	}

}
