package shadows.plants.registry.modules;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import shadows.plants.block.ae2.AEFarmland;
import shadows.plants.block.ae2.AESoil;

public class AE2Module {

	/*
	 * The control module for the AE2 Module of Plants.
	 * This will handle all registration which is then passed to the respective registry classes.
	 */
	public static List<Block> AELIST = new ArrayList<Block>();
	
	public static AEFarmland ae_farmland = new AEFarmland();
	public static AESoil ae_soil = new AESoil();
	

	
	public static List<Block> getAE(){
		AELIST.clear();
		AELIST.add(ae_farmland);
		AELIST.add(ae_soil);
		return AELIST;
	}
	
	
	
}
