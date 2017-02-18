package shadows.plants.registry.modules;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import shadows.plants.block.internal.tool.BlockCatapultBush;

public class ToolModule{

	public static BlockCatapultBush catapult = new BlockCatapultBush("cataplant");
	
	
	public static List<Block> getBlockList(){
		List<Block> list = new ArrayList<Block>();
		list.clear();
		list.add(catapult);
		
		return list;
	}
	
	public static List<Item> getItemList(){
		List<Item> list = new ArrayList<Item>();
		list.clear();
		
		
		return list;
	}
}
