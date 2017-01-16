package shadows.plants.registry.modules;


import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;

public class MemeModule{

	/*
	 * The control module for the AE2 Module of Plants.
	 * This will handle all registration which is then passed to the respective registry classes.
	 */
	public static List<Block> DANK = new ArrayList<Block>();

	

	
	public static List<Block> getList(){
		DANK.clear();
		
		
		return DANK;
	}
	
	
	
}
